package com.soc.ewok.controller.staffcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.ProducteDAO;

public class GFotoController extends SeccioController {
	private static final String ACCIO = "accioFoto";
	private static final String ACCIO_FORMULARI = "formulari";
	private static final String ACCIO_UPLOAD = "upload";
	private static final String ACCIO_GET = "get";
	private static final String REQUEST_PRODUCTES = "productes";
	private static final String REQUEST_CURRENTID = "currId";	
	private static final String PARAM_IDPROD = "idProd";
	private static final String PARAM_FITXER = "fitxer";

//	private static final String PATH_FITXERS = "C:" + File.separator
//			+ "eWokFotos";
	private static final String PATH_FITXERS = "/media/windows7/eWokFotos";

	/** Dao d'acc�s als productes */
	ProducteDAO prDao = null;
	
	public GFotoController() {
		// Inicialitzaci� del dao de productes
		prDao = new ProducteDAO(EWokController.getGlobalDatasource());
	}
	
	@Override
	public String getNomAccio() {
		return ACCIO;
	}

	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (ACCIO_FORMULARI.equals(accio)) {
			carregaProductes(request, null);
		} else if (ACCIO_UPLOAD.equals(accio)) {
			String idProd = uploadFoto(request, response);
			carregaProductes(request, idProd);
		} else if (ACCIO_GET.equals(accio)) {
			downloadFoto(request, response);
			return;
		} else {
			EWokController.addMessage(ETipusMissatge.error,
					"No es reconeix l'accio " + accio, request);
		}
		EWokController.forward("staff/gFoto/upldFoto.jsp", request, response);
	}

	private void carregaProductes(HttpServletRequest request, String idCurrent) {
		try {
			request.setAttribute(REQUEST_PRODUCTES, prDao.obtenirTots());
			if (idCurrent == null) {
				idCurrent = "-1";
			}
			request.setAttribute(REQUEST_CURRENTID, idCurrent);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.warning,
				"No s'han pogut obtenir els productes", request);			
		}
	}

	public static void downloadFoto(HttpServletRequest request,
			HttpServletResponse response) {
		// Busquem el par�metre
		final String idProd = request.getParameter(PARAM_IDPROD);
		// Si no em passen par�metre, no puc seguir
		if (idProd == null || idProd.trim().equals("")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		// Busco el fitxer
		File f = getCurrentFile(idProd);
		if (f == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		response.setContentType(
				request.getSession().getServletContext()
				.getMimeType(f.getName())
				);
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentLength((int) f.length());
		
		FileInputStream in = null;
		OutputStream out = null;
		try {
			 in = new FileInputStream(PATH_FITXERS + File.separator + f.getName());
			 out = response.getOutputStream();

			// Copy the contents of the file to the output stream
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {}
			}
			if (out != null) {
				try {
					out.flush();
				} catch (Exception e) {}
			}
		}

	}

	private String uploadFoto(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html");

		boolean isMultipartContent = ServletFileUpload
				.isMultipartContent(request);
		if (!isMultipartContent) {
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha trobat el fitxer", request);
			return null;
		}
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = request.getSession()
				.getServletContext();
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		String idProd = null;
		FileItem file = null;
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					if (PARAM_IDPROD.equals(item.getFieldName())) {
						idProd = item.getString();
						if ("".equals(idProd.trim())) {
							idProd = null;
						}
					}
				} else {
					if (PARAM_FITXER.equals(item.getFieldName())) {
						file = item;
						long s = file.getSize();
						if (file.getSize() <= 0) {
							file = null;
						}
					}
				}
			}
			if (idProd != null && !idProd.equals("-1") && file != null) {
				// Abans de pujar el nou fitxer, esborro l'antic, si existia
				// (per si me'n pugen un amb extensi� diferent a l'antic)
				File f = getCurrentFile(idProd);
				if (f != null) {
					Files.deleteIfExists(f.toPath());
				}

				// TODO Atenci� amb l'extensi�
				// Per obtenir el path base hauriem de treballar amb:
				// request.getSession().getServletContext().getRealPath("/")
				// aix� d�na el directori a partir del webcontent.
				// per� com que estem en desenvolupament i no producci�, usem un
				// path fix del disc dur
				String name = PATH_FITXERS + File.separator
						+ getNomFitxer(idProd, file.getName());
				file.write(new File(name));
				EWokController.addMessage(ETipusMissatge.info,
						"S'ha pujat el fitxer " + file.getName()
								+ " per al producte " + idProd, request);
			} else {
				if (idProd == null || idProd.equals("-1")) {
					EWokController.addMessage(ETipusMissatge.error,
							"No s'ha trobat l'id de producte", request);
				}
				if (file == null) {
					EWokController.addMessage(ETipusMissatge.error,
							"No s'ha trobat el fitxer", request);
				}
			}
		} catch (FileUploadException e) {
			EWokController.addMessage(ETipusMissatge.error,
					"Error parsejant la request: " + e.getMessage(), request);
		} catch (Exception e) {
			EWokController.addMessage(ETipusMissatge.error,
					"Error gen�ric processant el fitxer: " + e.getMessage(),
					request);
			EWokController.addMessage(ETipusMissatge.error,
					"Tens creat el directori c:\\eWokFotos ?",
					request);
		}
		return idProd;
	}

	private static File getCurrentFile(final String idProd) {
		File f = new File(PATH_FITXERS);
		File[] files = f.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith("p-" + idProd.trim() + "-p.");
			}
		});
		// Si no el trobo, no puc seguir
		if (files.length == 0) {
			return null;
		}
		return files[0];
		
	}
	
	private String getNomFitxer(String idProd, String name) {
		// TODO: Si no t� extensi� o si hi ha punts al nom, no funcionar� b�
		return "p-" + idProd.trim() + "-p" + name.substring(name.indexOf("."));
	}

}
