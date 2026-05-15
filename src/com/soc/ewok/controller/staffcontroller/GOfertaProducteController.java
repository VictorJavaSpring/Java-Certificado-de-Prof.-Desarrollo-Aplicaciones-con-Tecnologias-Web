package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.OfertaProducteDAO;
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.dao.TipusProducteDAO;
import com.soc.ewok.model.OfertaProducte;
import com.soc.ewok.model.Producte;
import com.soc.ewok.model.Rol;
import com.soc.ewok.model.TipusProducte;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;


/**
 * Classe que gestiona les accions amb l'entitat OfertaProducte 
 * @author JordiM.
 *
 */
public class GOfertaProducteController extends SeccioController {


	//** Constants de la classe **//
	//nom de la acció del controller
	private static final String ACCIO_OFERTAPRODUCTE = "accioOfertaProducte";

	// Constants de model
	private static final String MODEL_OFERPROLIST = "ofertaproductellista";
	private static final String MODEL_OFERPRO = "ofertaproducte";
	private static final String MODEL_OFERPRODUCTEALTA = "oferproductealta";
	private static final String MODEL_PRODUCTE_NOM = "nomProducte";
	private static final String MODEL_TIPUSPRO = "tipusprodlist";
	private static final String MODEL_PRODUCTE = "producte";

	//path per afegir a totes les pagines de secció staff OfertaProducte
	private static final String PATH2STAFFOFERTAPRODUCTE = "staff/gOfertaProducte/";
	//path per al directori del bundle de internacionalitzacio de l'entitat
	private static final String PATH_BUNDLE_OFERTAPRODUCTE = "com.soc.ewok.recursos.jsp.ofertaproducte.";

	// Constants d'error
	//Errors de SQL
	private static final String REQ_ERRORLLISTAT = "errorLlistat";
	private static final String REQ_ERRORVEURE = "errorVista";
	private static final String REQ_ERRORESBORRAT = "No s'ha pogut eliminar de la base de dades";
	private static final String REQ_ERRORMODIFICAR = "No s'ha pogut modificar a la base de dades";
	private static final String REQ_ERRORALTA = "ErrorAlta: Hi ha errors a les dades del formulari";
	//Errors de validació -missatge genèric
	private static final String REQ_ERRORMODIFICACIO = "Hi ha errors a les dades del formulari";
	

	//noms de var error per a jsp
	private static final String REQ_ERROR_DESCOMPTE = "errorDescompte";
	private static final String REQ_ERROR_VALOR_DESCOMPTE = "errorValorDescompte";
	private static final String REQ_ERROR_INIVIGENCIA = "errorIniVigencia";
	private static final String REQ_ERROR_FIVIGENCIA = "errorFiVigencia";
	private static final String REQ_ERROR_IDPRODUCTE = "errorIdProducte";
	private static final String REQ_ERROR_PRODUCTENOTROBAT = "errorProducte";

	
	//errors de paràmetres a formulari - missatge de camp
	private static final String REQ_ERROR_DESCOMPTE_TEXT = "No hi ha valor descompte";
	private static final String REQ_ERROR_VALOR_DESCOMPTE_TEXT = "El valor no pot ser negatiu";
	private static final String REQ_ERROR_INIVIGENCIA_TEXT = "El valor Inici Vigència no és vàlid";
	private static final String REQ_ERROR_FIVIGENCIA_TEXT = "El valor Fi Vigència no és vàlid";
	private static final String REQ_ERROR_IDPRODUCTE_TEXT = "No s'ha trobat el Producte amb id: ";
	private static final String REQ_ERROR_PRODUCTENOTROBAT_TEXT = "Cal informar un Producte";
	
	// Constants de Parametres
	private static final String PARAM_PRODUCTE = "inputProducte";
	private static final String PARAM_DESCRIPCIO = "inputDescripcio";
	private static final String PARAM_FI_VIGENCIA = "inputFiVigencia";
	private static final String PARAM_INI_VIGENCIA = "inputIniVigencia";
	private static final String PARAM_NOM = "inputNom";
	private static final String PARAM_DESCOMPTE = "inputDescompte";

	

	
	//vars membre 
	OfertaProducteDAO daoOP = null;
	
	public GOfertaProducteController() {
		// Creo un DAO que anirà contra BD
		daoOP = new OfertaProducteDAO(EWokController.getGlobalDatasource());
		//afegeixo els rols que poden usar aquest controller
		addRols2Controller(Rol.ROL_CODI_ADMINISTRADOR);
	}
	
	@Override
	public String getNomAccio() {
		return ACCIO_OFERTAPRODUCTE;
	}


	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//Mètode per servir les peticions relatives a OfertaProducte
		if (ACCIO_LLISTAT.equals(accio)){
			doLlistatOfertaProducte(request, response);
		}else if(ACCIO_VEURE.equals(accio)){
			doVeureOfertaProducte(request, response);
		}else if(ACCIO_ESBORRAR.equals(accio)){
			doEsborraOfertaProducte(request, response);
		}else if(ACCIO_PREPARARALTA.equals(accio)){
			doPreparaAltaOfertaProducte(request, response);
		}else if(ACCIO_ALTA.equals(accio)){
			doAltaOfertaProducte(request, response);
		}else if(ACCIO_PREPARARMODIFICACIO.equals(accio)){
			doPreparaModifOfertaProducte(request, response);
		}else if(ACCIO_MODIFICACIO.equals(accio)){
			doModificacioOfertaProducte(request, response);
		}
	}

	private void doModificacioOfertaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Intento obtenir l'objecte del request
		OfertaProducte op = extreureOfertaProducte(request, response, true);
		
		if(op == null){
			return;
		}
		
		// Si tot ha anat bé
		// modifiquem l'objecte amb el dao
		try {
			daoOP.modificar(op);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORMODIFICAR, request);
		}
		
		// Redirigim cap al llistat
		doLlistatOfertaProducte(request, response);
	}
	
	/**
	 * Funció d'utilitat per omplir un Objecte OfertaProducte amb les dades
	 * del request actual.
	 * @param request La request actual
	 * @param response La response actual
	 * @return un Objecte OfertaProducte amb les dades del request o null en cas
	 * de error en les dades obligatòries
	 * @throws IOException
	 * @throws ServletException 
	 */
	private OfertaProducte extreureOfertaProducte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Aquesta versió s'usa quan estem fent una alta nova  i no cal obtenir un id de l'objecte
		//crida a la versió amb implementació completa amb el paràmetre extreureId false
		return	extreureOfertaProducte(request, response, false);
	}
	/**
	 * Funció d'utilitat per omplir un Objecte OfertaProducte amb les dades
	 * del request actual
	 * @param request La request actual
	 * @param response La response actual
	 * @param extreureId Boleà que indica si cal extreure l'id del request
	 * @return un Objecte OfertaProducte amb les dades del request o null en cas
	 * de error en les dades obligatòries
	 * @throws IOException
	 * @throws ServletException 
	 */
	private OfertaProducte extreureOfertaProducte(HttpServletRequest request, HttpServletResponse response, boolean extreureId ) throws ServletException, IOException {
		//obtenir tots els valors del request
		
		//id	(SI és obligatori per a modificació)
		Long nId = null;
		if (extreureId){
			nId = getidFromRequest(request, null);
		}

		//descompte (SI és obligatori)
		Float nPctDescompte = null;
		try {
			nPctDescompte = RequestValidationUtils.getMandatoryFloat(PARAM_DESCOMPTE, request);
			if(nPctDescompte < 0){
				request.setAttribute(REQ_ERROR_VALOR_DESCOMPTE, REQ_ERROR_VALOR_DESCOMPTE_TEXT);
			}
		} catch (ParameterException e) {
				request.setAttribute(REQ_ERROR_DESCOMPTE, REQ_ERROR_DESCOMPTE_TEXT);
		}
		
		
		//nom (no és obligatori)
		String sOnom = RequestValidationUtils.getNonMandatoryString(PARAM_NOM, request);

		//inici vigencia (no és obligatori)
		Date dIniciVigencia = null;
		try {
			dIniciVigencia = RequestValidationUtils.getNonMandatoryDate(PARAM_INI_VIGENCIA, request);
		} catch (ParameterException e) {
			request.setAttribute(REQ_ERROR_INIVIGENCIA, REQ_ERROR_INIVIGENCIA_TEXT);
		}
		//fi vigencia (no és obligatori)
		Date dFiVigencia = null;
		try {
			dFiVigencia = RequestValidationUtils.getNonMandatoryDate(PARAM_FI_VIGENCIA, request);
		} catch (ParameterException e) {
			request.setAttribute(REQ_ERROR_FIVIGENCIA, REQ_ERROR_FIVIGENCIA_TEXT);
		}
		//descripcio (no és obligatori)
		String sOtext = RequestValidationUtils.getNonMandatoryString(PARAM_DESCRIPCIO, request);;
		
		//id del producte relacionat (SI és obligatori)
		Long nIdP = null;
		try{
			nIdP = RequestValidationUtils.getMandatoryLong(PARAM_PRODUCTE, request);
			
		}catch(ParameterException e){
			if (e.getValorTrobat() == null || e.getValorTrobat().trim().equals("")){
				request.setAttribute(REQ_ERROR_PRODUCTENOTROBAT,REQ_ERROR_PRODUCTENOTROBAT_TEXT);
			}else{
				request.setAttribute(REQ_ERROR_IDPRODUCTE, REQ_ERROR_IDPRODUCTE_TEXT);
			}
		}
		
		//crear l'objecte i omplir-lo amb les dades del request rebudes
		OfertaProducte op = new OfertaProducte();

		//Obligatoris. Si: els camps obligatoris son correctes els inserim
		if(nId != null){
			op.setId(nId);}
		if (nPctDescompte != null && nPctDescompte > 0){
			op.setPctDescompte(nPctDescompte);
		}
		if(nIdP != null){
			op.setIdProducte(nIdP);}
	
		//Opcionals. els inserim si o sí atès que l'objecte accepta nulls en aquest camps
		op.setNom(sOnom);
		op.setIniciVigencia(dIniciVigencia);
		op.setFiVigencia(dFiVigencia);
		op.setOtext(sOtext);
		
		// Si hi ha algun error de validació, retornem al formulari
		if (
				(extreureId && nId == null) ||
				nPctDescompte == null ||
				nPctDescompte < 0 ||
				nIdP == null
			){
			// hi ha algun error i era una modificació
						// (extreuId == true), llavor necessitem posar un objecte
						// com a flag per al formulari jsp
			if (extreureId) {
				request.setAttribute(MODEL_OFERPRO, op);
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORMODIFICACIO, request);
			}
			//és error en dades de alta
			request.setAttribute(MODEL_OFERPRODUCTEALTA, op);

			//inserim dades de tipus de productes
			inserirEntitatRelacionadaRequest(request);

			// Redirigim cap a la pàgina corresponent
			
			forward("formOfertaProducte.jsp", request, response);
			return null;
		}
		
		//retornem l'objecte actualitzat amb les dades del request
		return op;
	}

	/**
	 * @param request
	 */
	private void inserirEntitatRelacionadaRequest(HttpServletRequest request) {
		//1. busquem i inserim tots els tipus de producte per al select
		TipusProducteDAO tpdao = new TipusProducteDAO(EWokController.getGlobalDatasource());
		List<TipusProducte> ltp = null;
		try {
			ltp = tpdao.obtenirTots();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//inserim el llistat al request per  al jsp
		request.setAttribute(MODEL_TIPUSPRO, ltp);
	}

	private void doPreparaModifOfertaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// carregar el formulari amb les dades de l'objecte sol·licitat
		Long nId = getidFromRequest(request, response);
		if (nId == null){
			doLlistatOfertaProducte(request, response);
		}
		// no és null: intentem recuperar-lo de la bd
		OfertaProducte op = null;
		try{
			op = daoOP.obtenirPerId(nId);
			if (op == null){
				// Redirigim cap a la pàgina de llistat
				doLlistatOfertaProducte(request, response);
			}
		}catch(SQLException e){
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORVEURE, request);
		}
		
		// l'inserim al request
		request.setAttribute(MODEL_OFERPRO, op);
		
		//obtenim les dades de la relació ofertaproducte-> producte -> tipus producte
		//1.- busquem el producte relacionat que ensenyarem al select
		ProducteDAO pdao = new ProducteDAO(EWokController.getGlobalDatasource());
		Producte p = null;
		try {
			p = pdao.obtenirPerId(op.getIdProducte());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//l'inserim al request
		request.setAttribute(MODEL_PRODUCTE, p);
		
		inserirEntitatRelacionadaRequest(request);
		// Redirigim cap a la pàgina corresponent
		forward("formOfertaProducte.jsp", request, response);

		
		
	}

	private void doAltaOfertaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Intento obtenir l'objecte del request
		OfertaProducte op = extreureOfertaProducte(request, response);
		
		if(op == null){
			return;
		}
		
		// Si tot ha anat bé
		// modifiquem l'objecte amb el dao
		try {
			daoOP.alta(op);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORALTA, request);
		}
		
		// Redirigim cap al llistat
		doLlistatOfertaProducte(request, response);
		
	}

	private void doPreparaAltaOfertaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//inserim al request les dades de Tipus producte que necessitem a jsp
		inserirEntitatRelacionadaRequest(request);
		//redirigim a la jsp adient
		forward("formOfertaProducte.jsp", request, response);
		
	}

	private void doEsborraOfertaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// obtenir l'identificador d'objecte
		Long nId = getidFromRequest(request, response);
		if(nId == null){
			// Redirigim cap a la pàgina de llistat
			doLlistatOfertaProducte(request, response);
			return;
		}
		// eliminar-lo correctament de la bd
		try{
			daoOP.esborrar(nId);
		}catch(SQLException e){
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORESBORRAT, request);
		}		
		// tornar al llistat
		doLlistatOfertaProducte(request, response);
	}

	private void doVeureOfertaProducte(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// obtenir l'identificador d'objecte i carregar les dades 
		// al pàgina de presentació.
		
		//provem d'obtenir un id del request
		Long nId = getidFromRequest(request, response);
		if (nId == null){
			// Redirigim cap a la pàgina de llistat
			doLlistatOfertaProducte(request, response);
			return;
		}
		// no és null: intentem recuperar-lo de la bd
		OfertaProducte op = null;
		try{
			op = daoOP.obtenirPerId(nId);
			if (op == null){
				//
				String[] params = {nId.toString()};
				EWokController.addI18nMessage(ETipusMissatge.error,
						PATH_BUNDLE_OFERTAPRODUCTE+"veureOfertaProducte",
						REQ_ERRORVEURE,params, request);
				// Redirigim cap a la pàgina de llistat
				doLlistatOfertaProducte(request, response);
			}
		}catch(SQLException e){
			EWokController.addI18nMessage(ETipusMissatge.error,
					PATH_BUNDLE_OFERTAPRODUCTE+"veureOfertaProducte",
					REQ_ERRORVEURE, null, request);
		}
		
		// l'inserim al request
		request.setAttribute(MODEL_OFERPRO, op);
		//obtinc el nom del producte relacionat i el poso al request
		//per mostrar-lo a la pàgina
		ProducteDAO daoP = new ProducteDAO(EWokController.getGlobalDatasource());
		try {
			request.setAttribute(MODEL_PRODUCTE_NOM, daoP.obtenirPerId(op.getIdProducte()).getNom());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Redirigim cap a la pàgina corresponent
		forward("veureOfertaProducte.jsp", request, response);
	}


	private void doLlistatOfertaProducte(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// redirigir a pàgina de llistat de ofertes de productes amb
		//les dades de totes les ofertaproducte
		
		// Obtenim les dades del DAO
		List<OfertaProducte> ofps = null;
		try {
			ofps = daoOP.obtenirTots();
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.error,
					PATH_BUNDLE_OFERTAPRODUCTE+"llistaOfertaProducte",
					REQ_ERRORLLISTAT, null, request);

		}
		// Fiquem les dades al request
		request.setAttribute(MODEL_OFERPROLIST, ofps);
		
		// Redirigim cap a la pàgina corresponent
		forward("llistatOfertaProducte.jsp", request, response);		
	}
	
	/**
	 * Utilitat per redirigir a la pàgina jsp corresponent amb el nom de 
	 * la pàgina
	 * @param pagNom Cal posar el nom de la pàgina jsp.
	 * @param request El request usat pel forward
	 * @param response El response usat pel forward
	 * @throws ServletException Error standard d'HttpServlet
	 * @throws IOException Error standard d'HttpServlet
	 */
	private void forward(String pagNom, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EWokController.forward(PATH2STAFFOFERTAPRODUCTE + pagNom,request, response);
	}

}
