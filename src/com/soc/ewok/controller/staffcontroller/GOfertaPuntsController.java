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
import com.soc.ewok.dao.OfertaPuntsDAO;
import com.soc.ewok.model.OfertaProducte;
import com.soc.ewok.model.OfertaPunts;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;


public class GOfertaPuntsController extends SeccioController {
	
	//path dels jsp de la secció oferta de punts
	private static final String PATH_OFERTA_PUNTS = "staff/gOfertaPunts/";
	
	// Constants de les accions
	private static final String ACCIO_OFERTA_PUNTS = "accioOfertaPunts";
	
	private static final String MODEL_OFERTA_PUNTS = "ofertapunts";
	private static final String MODEL_OFERTA_PUNTS_ALTA = "ofertapuntsAlta";
	
	// Constants dels errors
	private static final String REQ_ERRORGENERIC = "No s'ha trobat l'oferta de punts amb id ";
	private static final String REQ_ERRORLLISTAT ="No hem pogut obtenir la llista d'ofertes de punts";
	private static final String REQ_ERRORPARAM ="Error en el paràmetre";
	private static final String REQ_ERRORMODIFICACIO = "No s'ha pogut modificar la informació";
	private static final String REQ_ERRORALTA = "No s'ha pogut donar d'alta l'oferta de punts.";
	private static final String REQ_ERRORSQL = "No s'ha pogut esborrar l'oferta de punts. Potser ja havia estat esborrada.";
	private static final String REQ_ERRORDATE = "error date";
	private static final String REQ_ERRORTIME = "error time";
	private static final String PARAM_ERR_EPP = "hiddenUser";
	private static final String REQ_ERROR_CALPOS = "Cal introduir un valor positiu";
	private static final String REQ_ERROR_PUNTSXEC = "Els punts per xec han d'estar informats";
	private static final String REQ_ERROR_EUROSPUNT = "Els euros per punt han d'estar informats";
	private static final String REQ_ERROR_INIVIGENCIA = "L'Inici de Vigència ha d'estar informat en un format vàlid";
	private static final String REQ_ERROR_FIVIGENCIA = "El Fi de Vigència ha d'estar informat en un format vàlid";
	private static final String REQ_ERROR_VIG_PUNT = "Els dies de vigència han d'estar informats";
	private static final String REQ_ERROR_VIG_XEC = "Els dies de vigència han d'estar informats";
	private static final String REQ_ERROR_TIMEIN = "L'hora d'inici de vigència ha d'estar informada en format correcte";
	private static final String REQ_ERROR_TIMEFI = "L'hora de final de vigència ha d'estar informada en format correcte";
	private static final String PARAM_ERROR_VIG_PUNT = "errorDiesVigPunt";
	private static final String PARAM_ERROR_VIG_XEC = "errorDiesVigXec";
	private static final String PARAM_ERROR_PUNTSXEC = "errorPuntsXec";
	private static final String PARAM_ERROR_EUROSPUNT = "errorEurosPunt";
	private static final String PARAM_ERROR_DATAINICI = "errorIniciVigencia";
	private static final String PARAM_ERROR_DATAFI = "errorFiVigencia";
	private static final String PARAM_ERROR_TIMEIN = "errorinVigTime";
	private static final String PARAM_ERROR_TIMEFI = "errorfiVigTime";
	
	// Constants dels paràmetres
	private static final String PARAM_ID = "id";
	private static final String PARAM_EUROS_PER_PUNT = "eurosPerPunt";
	private static final String PARAM_PUNTS_PER_XEC = "puntsPerXec";
	private static final String PARAM_DIES_VIGENCIA_PUNT = "diesVigenciaPunt";
	private static final String PARAM_DIES_VIGENCIA_XEC = "diesVigenciaXec";
	private static final String PARAM_INICI_VIGENCIA = "iniciVigencia";
	private static final String PARAM_FI_VIGENCIA = "fiVigencia";
	private static final String PARAM_INICIVIGENCIA = "inVig";
	private static final String PARAM_INICIVIGENCIATIME = "inVigTime";
	private static final String PARAM_FIVIGENCIA = "fiVig";
	private static final String PARAM_FIVIGENCIATIME = "fiVigTime";
	
	OfertaPuntsDAO daoOfertaPunts = null;
	
	public GOfertaPuntsController() {
		// Creem un DAO que anirà contra BdD
		daoOfertaPunts = new OfertaPuntsDAO(EWokController.getGlobalDatasource());
	}
	
	@Override
	public String getNomAccio() {
		return ACCIO_OFERTA_PUNTS;
	}

	@Override
	public void doGet(
			String accio, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		request.setAttribute("msg",
				"He passat pel controlador d'Oferta de Punts");
		
		// Mètode principal per servir totes les peticions
		// relatives a OfertaPunts
		if (ACCIO_LLISTAT.equals(accio)){
			doLlistaOfertaPunts(request, response);
		} else if(ACCIO_VEURE.equals(accio)){
			doVeureOfertaPunts(request, response);
		} else if(ACCIO_ESBORRAR.equals(accio)){
			doEsborrarOfertaPunts(request, response);
		} else if(ACCIO_PREPARARALTA.equals(accio)){
			doPreparaAltaOfertaPunts(request, response);
		} else if(ACCIO_ALTA.equals(accio)){
			doAltaOfertaPunts(request, response);
		} else if(ACCIO_PREPARARMODIFICACIO.equals(accio)){
			doPreparaModificaOfertaPunts(request, response);
		} else if(ACCIO_MODIFICACIO.equals(accio)){
			doModificaOfertaPunts(request, response);
		}		
	}


private void doVeureOfertaPunts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			// Obtenim l'id que ens passen
			Long nId = getId(request, response, "veure");
			if (nId == null) {
				// Si no tinc Id, no he de fer res
				return;
			}
			// Obtenim les dades del DAO
			OfertaPunts op = null;
			try {
				op = daoOfertaPunts.obtenirPerId(nId);
			} catch (SQLException e) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORSQL, request);
			}
			if (op == null) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORSQL, request);
				doLlistaOfertaPunts(request, response);
				return;
			}
			
			// Fiquem les dades al request
			request.setAttribute(MODEL_OFERTA_PUNTS, op);
			
			// Redirigim a la JSP
			EWokController.forward("staff/gOfertaPunts/veureOfertaPunts.jsp", request, response);

	}

private void doPreparaModificaOfertaPunts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// Obtenim l'id
	Long nId = getId(request, response, "modificar");
	// Si no hem obtingut id, hem acabat, perquè el getId,
	// ja haurà redirigit cap al llistat
	if (nId == null) {
		return;
	}
	// Obtenim el producte a través del DAO
	OfertaPunts op = null;
	try {
		op = daoOfertaPunts.obtenirPerId(nId);
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORGENERIC, request);
	}
	if (op == null) {
		// Si no hem trobat l'Oferta de Punts, redirigim a la llista
		// posant l'error adient
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORGENERIC + nId, request);
		doLlistaOfertaPunts(request, response);
		return;
	}
	// Posem l'Oferta de Punts al context adequat
	request.setAttribute(MODEL_OFERTA_PUNTS, op);
	// Redirigim al formulari
	EWokController.forward("staff/gOfertaPunts/formOfertaPunts.jsp", request, response);	
	}

private void doLlistaOfertaPunts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	List<OfertaPunts> ofertaPunts = null;
	// Obtenim les dades del DAO
	try {
		ofertaPunts = daoOfertaPunts.obtenirTots();
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORLLISTAT, request);
	}
	
	// Fiquem les dades al request
	request.setAttribute(MODEL_OFERTA_PUNTS, ofertaPunts);
	
	// Redirigim cap al jsp amb la llista	
	EWokController.forward("staff/gOfertaPunts/llistaOfertaPunts.jsp", request, response);
	}

private void doModificaOfertaPunts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// Intento obtenir l'oferta de Punts
	OfertaPunts ofertaPunts = extreureOfertaPunts(request, response, true);
	
	// Si no l'he obtingut no faig res
	// (extreureOfertaPunts s'encarrega de la redirecció
	if (ofertaPunts == null) {
		return;
	}
	
	// Si tot és correcte donem d'alta l'oferta de punts a través del DAO
	try {
		daoOfertaPunts.modificar(ofertaPunts);
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORMODIFICACIO, request);
	}
	
	// Redirigim cap a la llista
	EWokController.addMessage(ETipusMissatge.info,
			"L'item amb ID " + ofertaPunts.getId() + " ha estat modificat correctament", request);
	doLlistaOfertaPunts(request, response);
	}


private void doAltaOfertaPunts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// Intento obtenir l'oferta de punts
		OfertaPunts ofertaPunts = extreureOfertaPunts(request, response, false);
			// Si no l'obtenim no fem res
			// extreureOfertaPunts el redigirà
			if (ofertaPunts == null) {
				return;
			}
			
			// Si tot ha anat bé
			// Fem l'alta mitjançant del DAO
			try {
				daoOfertaPunts.alta(ofertaPunts);
			} catch (SQLException exx) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORALTA, request);
				}
			
			// Redirigim cap a la llista
			EWokController.addMessage(ETipusMissatge.info,
					"L'item amb ID " + ofertaPunts.getId() + " ha estat donat d'alta correctament", request);
			doLlistaOfertaPunts(request, response);
		}

private OfertaPunts extreureOfertaPunts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//Aquesta versió s'usa quan estem fent una alta nova  i no cal obtenir un id de l'objecte
	//crida a la versió amb implementació completa amb el paràmetre extreureId false
	return	extreureOfertaPunts(request, response, false);
}

private OfertaPunts extreureOfertaPunts(
		HttpServletRequest request,
		HttpServletResponse response,
		boolean extreureId) throws ServletException, IOException {
	
	// Validem i obtenim els valors del formulari
	// Id 
	Long nId = null;
	if (extreureId) {
		nId = getidFromRequest(request, null);
	}
//	} catch (ParameterException e){		EWokController.addMessage(ETipusMissatge.error, "Hi ha hagut un error amb l'id.", request);}
	
	// EurosPerPunt - Not Null
	Float fEurosPerPunt = null;
	try {
		fEurosPerPunt = RequestValidationUtils.getMandatoryFloat(PARAM_EUROS_PER_PUNT, request);
		if(fEurosPerPunt <= 0) {
			request.setAttribute(PARAM_ERROR_EUROSPUNT, REQ_ERROR_CALPOS);
		}
	} catch (ParameterException e){
		request.setAttribute(PARAM_ERROR_EUROSPUNT, REQ_ERROR_EUROSPUNT);
		request.setAttribute(PARAM_ERR_EPP, true);
	}
	
	// PuntsPerXec - Not Null
	Long fPuntsPerXec = null;
	try {
		fPuntsPerXec = RequestValidationUtils.getMandatoryLong(PARAM_PUNTS_PER_XEC, request);
		if(fPuntsPerXec <= 0) {
			request.setAttribute(PARAM_ERROR_PUNTSXEC, REQ_ERROR_CALPOS);
		}
	} catch (ParameterException e){
		request.setAttribute(PARAM_ERROR_PUNTSXEC, REQ_ERROR_PUNTSXEC);
	}
	
	// DiesVigenciaPunt - Not Null
	Long nDiesVigenciaPunt = null;
	try {
		nDiesVigenciaPunt = RequestValidationUtils.getMandatoryLong(
			PARAM_DIES_VIGENCIA_PUNT, 
			request);
		if(nDiesVigenciaPunt <= 0) {
			request.setAttribute(PARAM_ERROR_VIG_PUNT, REQ_ERROR_CALPOS);
		}
	} catch (ParameterException e){
		request.setAttribute(PARAM_ERROR_VIG_PUNT, REQ_ERROR_VIG_PUNT);
	}
	
	// DiesVigenciaXec - Not Null
	Long nDiesVigenciaXec = null;
	try {
		nDiesVigenciaXec = RequestValidationUtils.getMandatoryLong(
				PARAM_DIES_VIGENCIA_XEC, 
				request);
		if(nDiesVigenciaXec <= 0) {
			request.setAttribute(PARAM_ERROR_VIG_XEC, REQ_ERROR_CALPOS);
		}
	} catch (ParameterException e){
		request.setAttribute(PARAM_ERROR_VIG_XEC, REQ_ERROR_VIG_XEC);
	}
	
	// IniciVigencia - Not Null	
		Date dIniciVigencia = null;
		try {
			dIniciVigencia = RequestValidationUtils.obtenirMandatoryDateTime(
					PARAM_INICIVIGENCIA, 
					PARAM_INICIVIGENCIATIME, 
					request);
		} catch (ParameterException e1) {
			// EWokController.addMessage(ETipusMissatge.error, REQ_ERRORDATE, request);
			request.setAttribute(PARAM_ERROR_DATAINICI, REQ_ERROR_INIVIGENCIA);
			
		}

		// FiVigencia - Not Null
		Date dFiVigencia = null;
		try {
			dFiVigencia = RequestValidationUtils.obtenirMandatoryDateTime(
					PARAM_FIVIGENCIA, 
					PARAM_FIVIGENCIATIME, 
					request);
		} catch (ParameterException e1) {
			// EWokController.addMessage(ETipusMissatge.error,	REQ_ERRORTIME, request);
			request.setAttribute(PARAM_ERROR_DATAFI, REQ_ERROR_FIVIGENCIA);
			
		}	
		
	// Creem una oferta de punts amb els valors del formulari
	OfertaPunts ofertaPunts = new OfertaPunts();
	if(nId != null)
		ofertaPunts.setId(nId);
	if(fEurosPerPunt != null && fEurosPerPunt > 0)
		ofertaPunts.setEurosPerPunt(fEurosPerPunt);
	if(fPuntsPerXec != null && fPuntsPerXec > 0)
		ofertaPunts.setPuntsPerXec((short)(long)fPuntsPerXec);
	if(nDiesVigenciaPunt != null && nDiesVigenciaPunt > 0)
		ofertaPunts.setDiesVigenciaPunts(nDiesVigenciaPunt);
	if(nDiesVigenciaXec != null && nDiesVigenciaXec > 0)
		ofertaPunts.setDiesVigenciaXecs(nDiesVigenciaXec);
	if(dIniciVigencia != null)
		ofertaPunts.setIniciVigencia(dIniciVigencia);
	if(dFiVigencia != null)
	ofertaPunts.setFiVigencia(dFiVigencia);
	
	
	
	// Si hi ha algun error de validació, retornem al formulari
	if (
			(extreureId && nId == null) || 
			fEurosPerPunt == null || fEurosPerPunt <= 0 ||
			fPuntsPerXec == null || fPuntsPerXec <= 0 || 
			nDiesVigenciaPunt == null || nDiesVigenciaPunt <= 0 ||
			nDiesVigenciaXec == null || nDiesVigenciaXec <= 0 ||
			dIniciVigencia == null || 
			dFiVigencia == null
		) {
		// Si hi ha algun error i era una modificació
		// (extreuId == true), llavors cal posar una ofertaPunts
//		// com a flag
		if (extreureId) {
			request.setAttribute(MODEL_OFERTA_PUNTS, ofertaPunts);
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORMODIFICACIO, request);
		}
		//és error en dades de alta
		request.setAttribute(MODEL_OFERTA_PUNTS_ALTA, ofertaPunts);
		
		// Redirigim 
		forward("formOfertaPunts.jsp", request, response);
		return null;
	}
	
	// Retornem l'objecte amb les dades actualitzades
	return ofertaPunts;
	
}



private void doPreparaAltaOfertaPunts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// No cal fer res amb el model, 
	// simplement redirigim cap al formulari
	EWokController.forward("staff/gOfertaPunts/formOfertaPunts.jsp", request, response);
	}

private void doEsborrarOfertaPunts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// Obtenim l'id que ens passen
	Long nId = getId(request, response, "esborrar");
	if (nId == null) {
		// Si no tinc Id, no he de fer res
		return;
	}
	// Esborrem
	try {
		daoOfertaPunts.esborrar(nId);
		EWokController.addMessage(ETipusMissatge.info,
				"L'item ha estat eliminat correctament", request);
	} catch (SQLException e) {
		// I si ha anat malament, pintem un error
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORSQL,
				request);
		}
	// Hagi anat bé o malament, pintem la llista
	doLlistaOfertaPunts(request, response);
	}




private Long getId(
		HttpServletRequest request,
		HttpServletResponse response,
		String accioMissatge) throws ServletException, IOException {
	// Obtenim l'id de la url
			Long nId = null;
			try {
				nId = RequestValidationUtils.getMandatoryLong(
						PARAM_ID, 
						request);
			} catch (ParameterException e) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORPARAM, request);
			}
			// comprovem que no és null
			if (nId == null) {
				// Si hi ha hagut error, vaig a la llista
				doLlistaOfertaPunts(request, response);
				return null;
				}
			// Si tot ha anat bé, retorno l'id
			return nId;
			}

	private void forward(String pagNom, HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	EWokController.forward(PATH_OFERTA_PUNTS + pagNom, request, response);
	}
}
