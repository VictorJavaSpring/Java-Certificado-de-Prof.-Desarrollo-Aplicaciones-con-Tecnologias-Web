package com.soc.ewok.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.soc.ewok.model.Usuari;

/**
 * Servlet base de tots els servlets del projecte
 * Inclou funcionalitat standard per a tots els servlets
 * i funcions estàtiques per ser usades pels controladors 
 * que no siguin servlets
 * @author Administrador
 *
 */
public class EWokController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CT_PATH_PREFIX = "/WEB-INF/jsp/";
	private static final String SESS_USUARI_ACTUAL = "usuActual";
	private static final String REQ_MSG_INFO = "infoMsg";
	private static final String REQ_MSG_WARN = "warnMsg";
	private static final String REQ_MSG_ERROR = "errorMsg";
	private static final String SESS_IDIOMA = "idioma";
	
	public static final String GENERAL_BUNDLE = "com.soc.ewok.recursos.controlador.general";
	static protected Map<String, ResourceBundle> mBundles = new Hashtable<String, ResourceBundle>();
	
	/** Connexió a base de dades compartida per tots els daos */
	static DataSource globalDatasource = null;

	/**
	 * Funció per a fer forward d'una pàgina
	 * @param path Adreça de la pàgina a redirigir. Cal posar 
	 * el path a partir de /WEB-INF/jsp
	 * @param request El request usat pel forward
	 * @param response El response usat pel forward
	 * @throws ServletException Error standard d'HttpServlet
	 * @throws IOException Error standard d'HttpServlet
	 */
	public static void forward(String path, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Forward a: " + path);
		request.getRequestDispatcher(CT_PATH_PREFIX + path).forward(request, response);
	}

	/**
	 * Assigna el datasource a usar a tota la aplicació
	 * @param ds El datasource
	 */
	public static void setGlobalDatasource(DataSource ds) {
		globalDatasource = ds;
	}
	
	/**
	 * Retorna el datasuource a usar desde tota la aplicació
	 * @return El datasource a usar
	 */
	public static DataSource getGlobalDatasource() {
		return globalDatasource;
	}
	
	/**
	 * Afegeix un missatge a la llista de missatges
	 * que es mostraran a la propera pàgina
	 * @param tipus El tipus del missatge a mostrar
	 * @param message El text del missatge 
	 * @param request La request en curs
	 */
	public static void addMessage(ETipusMissatge tipus, String message, HttpServletRequest request) {
		List<String> llista = null;
		switch(tipus) {
			case info: llista = obtenirLlista(REQ_MSG_INFO, request); break;
			case warning: llista = obtenirLlista(REQ_MSG_WARN, request); break;
			case error: llista = obtenirLlista(REQ_MSG_ERROR, request); break;
		}
		llista.add(message);
	}

	/**
	 * Afegeix un missatge a la llista de missatges.
	 * El missatge s'obté un missatge internacionalitzat del bundle com.soc.ewok.recursos.controlador.general
	 * @param tipus El tipus del missatge a mostrar
	 * @param idMessage L'id del missatge dins el fitxer de bundle
	 * @param request La request actual
	 */
	public static void addI18nMessage(ETipusMissatge tipus, String idMessage, HttpServletRequest request) {
		addI18nMessage(tipus, null, idMessage, null, request);
	}
	
	/**
	 * Afegeix un missatge a la llista de missatges.
	 * El missatge s'obté un missatge internacionalitzat del indicat 
	 * i amb la possibilitat d'afegir paràmetres
	 * @param tipus El tipus del missatge a mostrar
	 * @param bundle El bundle d'on s'ha d'obtenir el missatge. Si es passa null
	 * s'usarà com.soc.ewok.recursos.controlador.general
	 * @param idMessage L'id del missatge dins el fitxer de bundle
	 * @param params Els paràmetres a aplicar. Pot ser null si no hi ha paràmetres
	 * @param request La request actual
	 */
	public static void addI18nMessage(ETipusMissatge tipus, String bundle, String idMessage, String []params, HttpServletRequest request) {
		if (bundle == null) {
			bundle = GENERAL_BUNDLE;
		}
		String idioma = getIdioma(request);
		String name = getBundleName(bundle, idioma);
		String message;
		// Busquem el bundle
		ResourceBundle bnd = mBundles.get(name);
		try {
			// Si no està al mapa, l'obrim i l'afegim al mapa de bundles
			if (bnd == null) {
				bnd = ResourceBundle.getBundle(bundle, new Locale(idioma));
				mBundles.put(getBundleName(bundle, idioma), bnd);
			}
			// Obtenim el missatge
			message = bnd.getString(idMessage);
			// Si ens passen paràmetres els hem d'aplicar
			if (params != null) {
				MessageFormat msf = new MessageFormat(message);
				message = msf.format(params);
			}
		} catch (Exception e) {
			System.out.println("Error obtenint missatge internacionalitzat: " + e.getMessage());
			message = "!!!" + bundle + "." + idMessage + "#" + idioma + "!!!";
		}
		// Afegim el missatge resultant
		addMessage(tipus, message, request);
	}
	
	private static String getBundleName(String bundle, String idioma) {
		return bundle + "#" + idioma;
	}

	/**
	 * Funció de soport per obtenir una llista de missatges
	 * a partir del nom que tindrà al request. La llista és creada
	 * si no existeix encara
	 * @param name El nom de la llista a buscar
	 * @param request La request actual
	 * @return La llista trobada o una llista buida si no existia
	 */
    private static List<String> obtenirLlista(String name, HttpServletRequest request) {
    	@SuppressWarnings("unchecked")
		List<String> llista = (List<String>)request.getAttribute(name);
    	if (llista == null) {
    		llista = new Vector<String>();
    		request.setAttribute(name, llista);
    	}
    	return llista;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public EWokController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Obté l'usuari actual o null si no hi ha cap usuari loguejat
     * @param request La request actual
     * @return L'usuari actual o null si no hi ha cap usuari loguejat
     */
    protected Usuari getUsuariActual(HttpServletRequest request) {
		return (Usuari)request.getSession().getAttribute(SESS_USUARI_ACTUAL);
	}

    /**
     * Assigna l'usuari loguejat a la sessió actual
     * @param usuari L'usuari que s'acaba de loguejar
     * @param request La request actual
     */
	protected void setUsuariActual(Usuari usuari, HttpServletRequest request) {
		request.getSession().setAttribute(SESS_USUARI_ACTUAL, usuari);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static void setIdioma(String sIdioma, ServletRequest request) {
		// Posem a la sessió l'idioma que rebem del filtre
		((HttpServletRequest) request).getSession().setAttribute(SESS_IDIOMA, sIdioma);
	}

	public static String getIdioma(ServletRequest request) {
		// Demanem a la sessió quin és l'idioma que tenim a la sessió
		return (String) ((HttpServletRequest) request).getSession().getAttribute(SESS_IDIOMA);
	}
	

	

}
