package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.UsuariDAO;
import com.soc.ewok.model.Rol;
import com.soc.utils.AutentificadorBD;
import com.soc.utils.IAutentificador;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;
import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.model.MenuItem;

/**
 * MainController de la zona privada.
 * Gestiona el login i reparteix la resta d'accions
 * cap al controlador de secció adecuat
 */
public class StaffController extends EWokController {
	private static final long serialVersionUID = 1L;
	
	private static final String CT_PARAM_ACCIO = "accio";
	private static final String CT_ACCIO_LOGIN = "login";
	private static final String CT_ACCIO_LOGOUT = "logout";
	
	public static final String PARAM_USUARI = "usuari";
	public static final String PARAM_PWD = "pwd";
	public static final String PARAM_IDIOMA = "idioma";
	
	public static final String PARAM_ERR_USUARI = "hiddenUser";
	public static final String PARAM_ERR_PASSWD = "hiddenPwd";
	
	private static final String SESS_USUARI = "nomUsuari";
	private static final String COOKIE_LASTUSER = "lastUser";

	private static final String CONF_AUTENTIFICADOR = "autentificador";
	private static final String REQ_FLAG = "flag";
	
	private static final String APP_MENU_ITEMS = "items";

	

	/**
	 * Llista de controladors de secció actuals
	 */
    List<ISectionController> controllers;
    
    /**
     * llista de items del menu ADMINISTRACIO de Staff
     */
    List<MenuItem> items;
    
    /**
     *  Autentificador d'usuaris
     */
 	IAutentificador autentificador = null;
    
 	/**
     * @see EWokController#EWokController()
     */
 	
 	/**
 	 * El DataSource
 	 */
 	DataSource ds = EWokController.getGlobalDatasource();
 	
    public StaffController() {
        super();
        
        // inicialitzem les llistes
        controllers = new Vector<ISectionController>();
        items = new Vector<MenuItem>();
        
        //afegim Controladors a la llista i a la llista "items" de menu
        
        // 0
        this.addController(new GProducteController(), "productes");
        // 1
        this.addController(new GPreuController(), "preus");
        // 2
        this.addController(new GUsuariController(), "usuaris");
        // 3
        this.addController(new GFormaPagamentController(), "pagaments");
        // 4
        this.addController(new GUnitatController(), "unitats");
        // 5
        this.addController(new GOfertaProducteController(), "ofProductes");
        // 6
        this.addController(new GOfertaPuntsController(), "punts");
        // 7
        this.addController(new GOfertaComandaController(), "ofComandes");
        // 8
        this.addController(new GTipusProducteController(), "tipusProductes");
        // 9
        this.addController(new GRolController(), "rols");
        // 10
        this.addController(new GFotoController(),"staff?accioFoto=formulari", "fotos");
        
        controllers.add(new GClientController());
        
        
        // afegim CUINER i CAIXER a la llista de CONTROLLERS
        // 11
        controllers.add(new VCaixer());
        // 12
        controllers.add(new VCuiner());
      

    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		// Obtinc del fitxer de configuració el nom de la classe
		// a usar per autentificar usuaris
		String classeAut = config.getInitParameter(CONF_AUTENTIFICADOR);

		// Usem el paràmetre:
		// creem un autentificador a partir del nom de la seva classe
		@SuppressWarnings("rawtypes")
		Class clAut;
		try {
			clAut = Class.forName(classeAut);
			autentificador = (IAutentificador) clAut.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// Si falla la configuració de l'autentificador,
		if (autentificador == null) {
			autentificador = new AutentificadorBD();
		}
		
		// posem els items del MENU al context de la APPLICATION
		config.getServletContext().setAttribute(APP_MENU_ITEMS, items);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(REQ_FLAG, true);
				
		// Idioma
		request.getSession().setAttribute(PARAM_IDIOMA, EWokController.getIdioma(request));
				
		// Mirem si tenim usuari loguejat
		if(getUsuariActual(request) == null) {
			processarSessioNoLoguejada(request, response);
		} else {
			// Mirem si volen fer logout
			String accioNom = request.getParameter(CT_PARAM_ACCIO);
			if (CT_ACCIO_LOGOUT.equals(accioNom)) {
				request.getSession().invalidate();
				EWokController.forward("staff/login.jsp", request, response);
				return;
			}
			processarSessioLoguejada(request, response);
		}
	}

	/**
	 * Processa una request en una sessió d'un usuari loguejat
	 * Mira si es pretén fer logout, i si no és així redirigeix
	 * cap al controlador de secció adequat
	 * @param request La request actual
	 * @param response La response actual
	 * @throws ServletException Excepció genèrica de servlet
	 * @throws IOException Excepció genèrica de servlet
	 */
	private void processarSessioLoguejada(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Busquem el controlador que respon a l'acció
		// que ens hagin passat
		for(ISectionController contr : controllers) {
			String accioNom = contr.getNomAccio();
			String accioValor = request.getParameter(accioNom);
			if (accioValor != null) {
				//Ja hem trobat el controlador
				//Comprovem si l'acció és permesa per al controller i
				//usuari actual
				if(esAccioPermesa(request, contr, accioValor)){				
					// Enviem l'accio al controlador
					contr.doGet(accioValor, request, response);
					return;
				}
				//si arriba a aquest punt és accio no permesa
				EWokController.addMessage(ETipusMissatge.error, "No tens permisos per realitzar aquesta acció", request);
				EWokController.forward("staff/home.jsp", request, response);
				return;
			}
		}
		
		// Si hi ha usuari
		request.setAttribute("msg", "Sense accio coneguda, vaig a la home");
		EWokController.forward("staff/home.jsp", request, response);
		return;
	}

	/**
	 * Funció d'utilitat que comprova que una acció és permesa
	 * per a un parell controller-usuari
	 * @param request petició request actual
	 * @param contr controller actual
	 * @param accioValor acció a realitzar per l'usuari
	 * @return True si l'acció és permesa, False si no és permesa 
	 */
	private boolean esAccioPermesa(HttpServletRequest request,
			ISectionController contr, String accioValor) {
		//comparem rols de controller amb rols de usuari
		//per autoritzar o no l'acció
		List<String> ctrollerRols = contr.getRolsValids(accioValor);
		if(ctrollerRols == null){
			//si contr no te rols associats: permetem l'acció
			return true;
		}else{
			//si contr té rols associats: comparem amb els rols d'usuari
			//si hi ha coincidència pot fer l'acció
			List<Rol> userRols = getUsuariActual(request).getRols();
			for(String rl : ctrollerRols ){
				for(Rol r : userRols){
					if(r.getCodi().equals(rl)){
						//coincidencia rol: accio permesa
						return true;
					}
				}
			}
		}
		//arribats aquest punt és que el controller requereix rols
		// o no hi ha coincidència: no es permet l'acció
		return false;
	}
	
	/**
	 * Processa una request d'una sessió no loguejada
	 * Mira si es demana fer un login, i si no és així
	 * redirigeix cap a la part pública
	 * @param request La request actual
	 * @param response La response actual
	 * @throws ServletException Excepció genèrica de servlet
	 * @throws IOException Excepció genèrica de servlet
	 */
	private void processarSessioNoLoguejada(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Si ens demanen fer login, el processem
		String accio = request.getParameter(CT_PARAM_ACCIO);
		if(CT_ACCIO_LOGIN.equals(accio)) {
			processaLogin(request, response);
		} else {
			EWokController.forward("staff/login.jsp", request, response);
		}
	}
	
	private void processaLogin(
			HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		// He de veure si rebo informaciósuficient per intentar
		// fer un login
		String sUsuari = null;
		String sPwd = null;
		try {
			sUsuari = RequestValidationUtils.getMandatoryString(PARAM_USUARI, request);
			request.setAttribute(PARAM_ERR_USUARI , false);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERR_USUARI, true);
		}
		
		try {
			sPwd = RequestValidationUtils.getMandatoryString(PARAM_PWD, request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERR_PASSWD, true);
		}
		if (sUsuari == null || sPwd == null) {
			// Hi ha error de validació decideixo redirigir a login
			// No he de fer res amb el model
			// Redirigeixo a login
			EWokController.forward("staff/login.jsp", request, response);
			return;
		}
		// Intentar fer login
		// Demanem al model si usr/pwd està autoritzat
		if (autentificador.isUsuariAutoritzat(sUsuari, sPwd)) {
			//Fico l'usuari al EWokController
			UsuariDAO usu = new UsuariDAO(ds);
			try {
				setUsuariActual(usu.obtenirPerEmail(sUsuari), request);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
			// Fico l'usuari a la sessió
			request.getSession().setAttribute(SESS_USUARI, sUsuari);
			// Creem una cookie amb el darrer usuari loguejat
			Cookie cuqui = new Cookie(COOKIE_LASTUSER, sUsuari);
			cuqui.setMaxAge(10 * 24 * 60 * 60);
			response.addCookie(cuqui);
			EWokController.forward("staff/home.jsp", request, response);
		} else {
			// Error de validació
			EWokController.addI18nMessage(ETipusMissatge.error, "log001.UserPwd", request);
			EWokController.forward("staff/login.jsp", request, response);
		}
	}
	
	
	/**
	 * funcio per afegir el controlador a la llista de controladors + afegir-lo després al menu ADMINISTRACIO de Staff
	 * @param url String amb la url per accedir al controlador
	 * @param iKey clau del Tag d´internacionalització de l´element del Menu
	 */
	
	private void addController (ISectionController controller,String url, String key) {
		
	// afegim el controlador a la llista de "controllers"
		controllers.add(controller);
		
	// creem un nou objecte MenuItem
		MenuItem nou = new MenuItem();
		
	// omplim els valors de l´objecte MenuItem
		nou.setUrl(url);
		nou.setKey(key);
	//afegim el MenuItem a la llista "items"
		items.add(nou);
		

	
	}
	
	
	
	
	
	/**
	 * funcio per afegir el controlador a la llista de controladors + afegir-lo després al menu ADMINISTRACIO de Staff
	 * @param controller String amb el nom del controlador
	 * @param url String amb la url per accedir al controlador
	 * @param iKey clau del Tag d´internacionalització de l´element del Menu
	 */
	private void addController(ISectionController controller, String key) {
	
	// afegim el controlador a la llista de "controllers"
		controllers.add(controller);
	
	// obtenim l´accio del controlador
		String accio = controller.getNomAccio();
	// creem la linia de la url
		StringBuilder linia = new StringBuilder();
		linia.append("staff?");
		linia.append(accio);
		linia.append("=llistat");
		String sLinia = linia.toString();
	// creem un nou objecte MenuItem
		MenuItem nou = new MenuItem();
	// omplim els valors de l´objecte MenuItem
		nou.setUrl(sLinia);
		nou.setKey(key);
	//afegim el MenuItem a la llista "items"
		items.add(nou);
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

