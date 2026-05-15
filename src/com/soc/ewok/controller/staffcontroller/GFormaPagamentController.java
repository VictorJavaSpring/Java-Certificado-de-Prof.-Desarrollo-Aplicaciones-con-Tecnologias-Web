package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.FormaPagamentDAO;
import com.soc.ewok.model.FormaPagament;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GFormaPagamentController extends SeccioController{
	
	private static final String ACCIO_FP = "accioForPag";
	private static final String ACCIO_VEURE = "veure";
	private static final String ACCIO_PREPNOVA = "prepararnova";
	private static final String ACCIO_NOVA = "nova";
	private static final String ACCIO_PREPMODIFICAR = "prepararmodificar";
	private static final String ACCIO_MODIFICAR = "modificar";
	private static final String ACCIO_ESBORRAR = "esborrar";
	private static final String ACCIO_LLISTAT = "llistat";
	
	
	private static final String PARAM_ID = "id";
	private static final String PARAM_NOM = "nom";
	private static final String REQ_ERRORGENERIC = "errorgeneric";
	private static final String REQ_ERRORNOM = "errorNom";
	
	private static final String REQ_FORMAPAGAMENT = "fp";
	private static final String REQ_FORMAPAGAMENTLLISTAT = "llistat";

	
	private static final String NOU_REGISTRE = "nou";
	private static final String MODIF_REGISTRE = "modif";
	
	
		
	// ewok.
	FormaPagamentDAO dao = new FormaPagamentDAO(EWokController.getGlobalDatasource());
	
	/**
	 * funcio que retorna ACCIO_FP 	
	 */
	@Override
	public String getNomAccio() {
		
		return ACCIO_FP;
	}
	
	/**
	 * funcio que rep l´accio del Servlet StaffController i la processa
	 */
	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
							
			// Mètode principal per servir totes les peticions
				// relatives a Forma Pagament
				if (accio.equals(ACCIO_VEURE)) {
					doVeureFP(request, response);
					return;
				}
				if (accio.equals(ACCIO_PREPNOVA)) {
					doPreparaNovaFP(request, response);
					return;
				}
				if (accio.equals(ACCIO_NOVA)) {
				doNovaModificarFP(NOU_REGISTRE,request, response);
				return;		
				}
								
				if (accio.equals(ACCIO_PREPMODIFICAR)) {
					doPreparaModificarFP(request, response);
					return;
				}	
				if (accio.equals(ACCIO_MODIFICAR)) {
					doNovaModificarFP(MODIF_REGISTRE,request, response);
					return;
				}
				if (accio.equals(ACCIO_ESBORRAR)) {
					doEsborraFP(request, response);
					return;
				}
				if (accio.equals(ACCIO_LLISTAT)) {
					doLlistatFP(request, response);
					return;
				}
				
				// si no hi ha accio o l´accio no es cap de les anteriors enviem a llistat de Formes Pagament
				doLlistatFP(request,response);
				
			}
	
	/**
	 * funcio per mostrar una Forma de Pagament
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doVeureFP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// obtenim l´objecte Forma Pagament a mostrar
		FormaPagament fp = null;
		
		try {
			fp = doObtenirPerId(request,response);
			// el posem al REQUEST si no hi ha hagut error
			request.setAttribute(REQ_FORMAPAGAMENT, fp);
			
		} catch (ParameterException e) {
			// pintem l´error
			EWokController.addI18nMessage(ETipusMissatge.error, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
					"fp002.error", null, request);
		} catch (SQLException e) {
			//pintem l´error
			EWokController.addI18nMessage(ETipusMissatge.info, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
			"fp002.error", null, request);
		}
				
		
		// carreguem la pagina tant si hi ha hagut error com si no
		EWokController.forward("staff/gFormaPagament/FPVeure.jsp", request, response);
	}

	

	/**
	 * funcio per llistar totls els registres Forma Pagament de la BD 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doLlistatFP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		// obtenim tots els registres de la llista Forma Pagament	
		try {
			List<FormaPagament> llistafp = dao.obtenirTots();
			// els posem a la sessio si ha anat be
			request.setAttribute(REQ_FORMAPAGAMENTLLISTAT,llistafp );
			
		} catch (SQLException e) {
			// pintem l´error
			EWokController.addI18nMessage(ETipusMissatge.error, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
					"fp003.error", null, request);
		}
		//redirigim a la pagina de Llistat tant si falla com si no
		EWokController.forward("staff/gFormaPagament/FPLlistat.jsp", request, response);
//		
	}

	/**
	 * funcio per esborrar un registre Forma Pagament de la BD
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParameterException 
	 */
	private void doEsborraFP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
		Long nId = null;
		String[] sArray = null;
		
		// obtenim l´id de la Forma Pagament a esborrar del formulari
		try {
			nId = doObtenirId(request,response);
		} catch (ParameterException e1) {
			// pintem l´error
			sArray[0] = nId.toString(); 
			EWokController.addI18nMessage(ETipusMissatge.info, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
			"fp004.error", sArray, request);	
			}
		
		try {
			dao.esborrar(nId);
			} catch (SQLException e) {
			//pintem l´error
			sArray[0] = nId.toString(); 
			EWokController.addI18nMessage(ETipusMissatge.info, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
			"fp004.error", sArray, request);	
			}
		//redirigim a la funcio de Llistat
		doLlistatFP(request,response);
		
	}
	
	/**
	 * funcio per agafar l´objecte de la web i buscar a la BD quin es per modificarlo
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParameterException 
	 */
	private void doPreparaModificarFP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		FormaPagament fp = null;
		try {
			fp = doObtenirPerId(request,response);
		} catch (ParameterException e) {
			// pintem l´error
			EWokController.addI18nMessage(ETipusMissatge.error, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
			"fp002.error", null, request);
		} catch (SQLException e) {
			// pintem l´error
			EWokController.addI18nMessage(ETipusMissatge.error, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
			"fp002.error", null, request);
		}
						
		// el posem a la sessio
		request.setAttribute(REQ_FORMAPAGAMENT, fp);
		
		
		// Redirigim al formulari
		EWokController.forward("staff/gFormaPagament/FPNovaModificar.jsp", request, response);
				
	}
	
	/**
	 * funcio que dona d´alta un nou registre Forma Pagament a la BD
	 * @param request
	 * @param response
	 * @throws ParameterException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doNovaModificarFP(String accio,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Creem un objecte forma pagament
		FormaPagament fp = new FormaPagament();
		
		// inicialitzem a NULL
				Long nId = null;
				String sNom = null;
				
				// si es modificacio obtenim ID
				if (MODIF_REGISTRE.equals(accio)) {
					try {
						nId = RequestValidationUtils.getMandatoryLong(
								PARAM_ID, 
								request);
					} catch (ParameterException e) {
						// pintem l´error
						EWokController.addI18nMessage(ETipusMissatge.error, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
						"fp005.error", null, request);
						// redirigir a la pagina i return
						EWokController.forward("staff/gFormaPagament/FPLlistat.jsp", request, response);
						return;
					}
				}
				// obtenim el camp NOM
				try {
					sNom = RequestValidationUtils.getMandatoryString(
							PARAM_NOM, 
							request);
				} catch (ParameterException e1) {
					// pintem l´error
					request.setAttribute(REQ_ERRORNOM,"error");
					// tornem a executar la funcio o carreguem la pagina segons toqui i return
					if (MODIF_REGISTRE.equals(accio)) {
						doPreparaModificarFP(request,response);
					} else {
						EWokController.forward("staff/gFormaPagament/FPNovaModificar.jsp", request, response);
					}
					return;
					}
								
								
				// assignem els nous valors a l´objecte Forma de Pagament
				fp.setnId(nId);
				fp.setsNom(sNom);
				
				
				
				// introduiem la NOVA Forma Pagament a BD
				if (NOU_REGISTRE.equals(accio)) {
					try {
						dao.alta(fp);
					} catch (SQLException e) {
						// pintem l´error
						EWokController.addI18nMessage(ETipusMissatge.error, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
						"fp007.error", null, request);
						
					}
				
				// introduiem la Forma Pagament MODIFICADA a BD
				} else {
						String[] sArray = null;
					try {
						dao.modificar(fp);
						} catch (SQLException e) {
							// pintem l´error
							String sId = (fp.getnId()).toString();
							sArray[0]= sId;
							
							EWokController.addI18nMessage(ETipusMissatge.error, "com.soc.ewok.recursos.jsp.formapagament.FPErrors",
							"fp008.error",sArray, request);
							
						}
					
				}
				
				//redirigim a la funcio de Llistat tant si hi ha hagut errors com si no
				doLlistatFP(request,response);
				}
				
	
	
	/**
	 * funcio que envia a la pagina de Nova/Modificar Forma Pagament
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doPreparaNovaFP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		// Redirigim al formulari Nova Forma Pagament
		
		EWokController.forward("staff/gFormaPagament/FPNovaModificar.jsp", request, response);
	
	}
	
		
	/**
	 * funcio que retorna l´objecte Forma Pagament a modificar/posar a la pagina 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParameterException 
	 * @throws SQLException 
	 */
	private FormaPagament doObtenirPerId (HttpServletRequest request,
			HttpServletResponse response) throws ParameterException, SQLException {
		
		// creem un objecte Forma Pagament
		FormaPagament fp = null;
		
		// obtenim l´ID de lobjecte a modificar	de la pagina
		Long nId = null;
		nId = RequestValidationUtils.getMandatoryLong(
			PARAM_ID,
			request);
		fp = dao.obtenirPerId(nId);
		
		return fp;
	}
	
	
	/**
	 * funcio per obtenir l´Id de l´objecte a tractar del formulari
	 * @param request
	 * @param response
	 * @return
	 * @throws ParameterException 
	 */
	private Long doObtenirId (HttpServletRequest request,
			HttpServletResponse response) throws ParameterException{
		
				
		// obtenim l´ID de lobjecte a modificar	de la pagina
		Long nId = null;
					
		nId = RequestValidationUtils.getMandatoryLong(
			PARAM_ID,
			request);
			
			
		return nId;
	}
	
	/**
	 * funcio per desPintar els Msg que ja hi hagi pintats a la pagina
	 * @param request
	 * @param response
	 */
	
		
		
		
		
}
		



