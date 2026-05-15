package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.UnitatDAO;
import com.soc.ewok.model.Preu;
import com.soc.ewok.model.Rol;
import com.soc.ewok.model.Unitat;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GUnitatController extends SeccioController {
	// Constants d'acció
	private static final String ACCIO_UNITAT = "accioUnitat";
	// Constants de models
	private static final String MODEL_LLISTAUNITATS = "unitats";
	private static final String MODEL_UNITAT = "unitat";
	// Constants de paràmetres
	private static final String PARAM_ID = "id";
	private static final String PARAM_NOM = "nom";
	private static final String PARAM_ACRONIM = "acron";
	// Constant de missatge del controlador
	private static final String MISSATGE = "msg";
	// Constants d'error de mètodes
	private static final String PARAM_ERROR_ID = "unitat001.errorId";
	private static final String PARAM_ERROR_UNITAT = "unitat002.errorUnitat";
	private static final String PARAM_ERROR_ESBORRAR = "unitat003.errorEsborrar";
	private static final String PARAM_INFO_ESBORRAR = "unitat004.infoEsborrar";
	private static final String PARAM_ERROR_ALTA = "unitat005.errorAlta";
	private static final String PARAM_INFO_ALTA = "unitat006.infoAlta";
	private static final String PARAM_ERROR_SQL = "unitat007.errorSql";
	private static final String PARAM_INFO_MODIFICACIO = "unitat008.infoModificacio";
	private static final String PARAM_INFO_LLISTA = "unitat009.infoLlista";
	private static final String PARAM_ERROR_MODIFICACIO = "unitat010.errorModificacio";
	private static final String PARAM_ERROR_NOM = "errorNom";
	private static final String PARAM_ERROR_ACRONIM = "errorAcron";
	
	UnitatDAO daoUnitat = null;
		
	public GUnitatController() {
		// Creo un DAO que anirà contra BD
		daoUnitat = new UnitatDAO(EWokController.getGlobalDatasource());
		// Afegeixo els rols que poden gestionar les unitats 
		//addRols2Controller(Rol.ROL_CODI_ADMINISTRADOR);
		//addRols2Controller(Rol.ROL_CODI_CUINER);
		//addRols2Controller(Rol.ROL_CODI_CAIXER);
	}
	
	@Override
	public String getNomAccio() {
		return ACCIO_UNITAT;
	}

	@Override
	public void doGet(
			String accio,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException{
		request.setAttribute(MISSATGE,
				"He passat pel controlador d'unitats");
		
		//Mètode per servir les peticions
		//relatives a Unitats
		if (accio.equals(ACCIO_VEURE)){
			doVeureUnitat(request, response);
		}
		else if (accio.equals(ACCIO_ESBORRAR)){
			doEsborrarUnitat(request, response);
		}
		else if (accio.equals(ACCIO_PREPARARALTA)){
			doPreparaAltaUnitat(request, response);
		}
		else if (accio.equals(ACCIO_ALTA)){
			doAltaUnitat(request, response);
		}	
		else if (accio.equals(ACCIO_PREPARARMODIFICACIO)){
			doPreparaModificacioUnitat(request, response);
		}
		else if (accio.equals(ACCIO_MODIFICACIO)){
			doModificaUnitat(request, response);
		}
		else if (accio.equals(ACCIO_LLISTAT)){
			doLlistatUnitat(request, response);
		}
	}

	
	// Els mètodes:
	// Mètode per veure la unitat triada:
	private void doVeureUnitat(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		// Obtinc l'id que ens passen
		Long nId = null;
		
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			// Optimitzo la gestió d'errors: invoco el mètode "addI18nMessage()"
			// que alhora invoca implícitament el mètode "addMessage()":
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_ID, 
										  new String[]{nId.toString()}, request);
			doLlistatUnitat(request, response);
			return;
		}		
		if (nId == null) {
			// Si no tinc Id, no he de fer res
			return;
		}
		// Obtinc les dades del DAO
		Unitat u = null;
		try {
			u = daoUnitat.obtenirPerId(nId);
		} catch (SQLException e) {
			// Si hi ha error, retorno un missatge d'error i redirigeixo al llistat d'unitats
			EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_ID, 
										  new String[]{nId.toString()}, request);
			doLlistatUnitat(request, response);
			return;
		}
		if (u == null) {
			// Si el preu �s null, retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_UNITAT, 
										  new String[]{nId.toString()}, request);
			// i redirigeixo al llistat d'Unitat
			doLlistatUnitat(request, response);
			return;
		}
		// Introdueixo les dades al request
		request.setAttribute(MODEL_UNITAT, u);
		// Redirigeixo cap a la JSP
		EWokController.forward("staff/gUnitat/veureUnitat.jsp", request, response);
	}
	
	// Mètode per esborrar la unitat trobada
	private void doEsborrarUnitat(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// Obtinc l'id que em passen
		Long nId = null;
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			// Si es produeix un error "e1" (del tipus ParameterException),
			// el capturo, retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_ESBORRAR, 
										  new String[]{nId.toString()}, request);
			// i redirigeixo al llistat d'unitats
			doLlistatUnitat(request, response);
			return;
		}
		
		// Esborro la unitat
		try {
			daoUnitat.esborrar(nId);
			// Si s'ha esborrat la unitat d'id "nId",
			// retorno un missatge informatiu
			EWokController.addI18nMessage(ETipusMissatge.info, null, PARAM_INFO_ESBORRAR, 
					  new String[]{nId.toString()}, request);
		} catch (SQLException e) {
			// Si es produeix un error "e" (del tipus SQLException),
			// el capturo, retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_ESBORRAR, 
										  new String[]{nId.toString()}, request);
			// i redirigeixo cap al llistat d'unitats
			doLlistatUnitat(request, response);
			return;
		}
		
		// Hagi anat b� o malament, redirigeixo cap al llistat d'unitats
		doLlistatUnitat(request, response);
		return;
	}
	
	// Mètode per preparar una alta d'unitat:
	private void doPreparaAltaUnitat(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// No cal fer res amb el model, 
		// simplement redirigeixo cap al formulari
		EWokController.forward("staff/gUnitat/formUnitat.jsp", request, response);
	}
	
	// Mètode per donar d'alta una unitat:
	private void doAltaUnitat(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		// Intento obtenir la unitat
		Unitat unitat = extreureUnitat(request, response, false);
		
		// Si no l'he obtingut no faig res
		// (extreureUnitat ja haur� redirigit on calgui)
		if (unitat == null) {
			return;
		}		
		
		// Si tot ha anat b�
		// dono d'alta la unitat a trav�s del DAO
		try {
			daoUnitat.alta(unitat);
			// Si s'ha donat d'alta la unitat,
			// retorno un missatge informatiu
			EWokController.addI18nMessage(ETipusMissatge.info, PARAM_INFO_ALTA, request);
		} catch (SQLException e2) {
			// Si s'ha produ�t un error "e2" (tipus SQLException),
			// el capturo, retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error,	PARAM_ERROR_ALTA, request);
			// i redirigeixo cap al llistat d'unitats
			doLlistatUnitat(request, response);
			return;
		}
		
		// Redirigeixo cap al llistat d'unitats
		doLlistatUnitat(request, response);
		
	}
	
	// Mètode per preparar una modificació:
	private void doPreparaModificacioUnitat(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		// Obtenim l'id
		Long nId = null;
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			// Si es produeix un error "e1" (tipus ParameterException),
			// capturo l'excepci�, retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_ID, 
										  new String[]{nId.toString()}, request);
			// i redirigeixo cap al llistat d'unitats
			doLlistatUnitat(request, response);			
			return;
		}
		// Si no he obtingut id, he acabat, perqu� el getId
		// ja haur� redirigit cap al llistat
		if (nId == null) {
			return;
		}
		// Obtinc la unitat a través del DAO
		Unitat u;
		try {
			u = daoUnitat.obtenirPerId(nId);
		} catch (SQLException e) {
			// Si es produeix un error "e" (tipus SQLException),
			// el capturo, retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_ID, 
										  new String[]{nId.toString()}, request);
			// i redirigeixo cap al llistat d'unitats
			doLlistatUnitat(request, response);
			return;
		}
		if (u == null) {
			// si no s'ha trobat cap unitat,
			// retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_ID, 
										  new String[]{nId.toString()}, request);
			// i redirigeixo cap al llistat d'unitats
			doLlistatUnitat(request, response);
			return;
		}
		// Poso la unitat al context adequat
		request.setAttribute(MODEL_UNITAT, u);
		
		// Redirigeixo cap al formulari
		EWokController.forward("staff/gUnitat/formUnitat.jsp", request, response);
	}
	
	// Mètode per modificar la unitat:
	private void doModificaUnitat(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// Intento obtenir la unitat
		Unitat unitat = extreureUnitat(request, response, true);
		// Si no l'he obtingut no faig res
		// (extreureUnitat ja haur� redirigit on calgui)
		if (unitat == null) {
			return;
		}
		// Si tot ha anat b�,
		// dono d'alta la unitat a través del DAO
		try {
			daoUnitat.modificar(unitat);
			// Si s'ha pogut modificar la unitat,
			// retorno un missatge informatiu
			EWokController.addI18nMessage(ETipusMissatge.info, null, PARAM_INFO_MODIFICACIO, 
					  new String[]{unitat.getId().toString()}, request);
		} catch (SQLException e) {
			// Si s'ha produ�t un error "e" (tipus SQLException),
			// el capturo, retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_SQL, 
										  new String[]{unitat.getId().toString()}, request);
			// i redirigeixo cap al llistat de unitats
			doLlistatUnitat(request, response);
			return;
		}
		
		// Redirigeixo cap al llistat de unitats
		doLlistatUnitat(request, response);
		return;
	}
	
	// Mètode per presentar la llista d'unitats
	private void doLlistatUnitat(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// Obtinc les dades del DAO
		List<Unitat> unitats = null;
		try {
			unitats = daoUnitat.obtenirTots();
		} catch (SQLException e) {
			// Si es produeix un error "e" (tipus SQLException),
			// el capturo i retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.info, PARAM_INFO_LLISTA, request);
		}
		
		// Introdueixo les dades al request
		request.setAttribute(MODEL_LLISTAUNITATS, unitats);
		
		// Redirigeixo cap al llistatUnitats.jsp
		EWokController.forward("staff/gUnitat/llistatUnitat.jsp", request, response);		
	}
	
	// Mètode per extreure una unitat
	private Unitat extreureUnitat(
			HttpServletRequest request,
			HttpServletResponse response, 
			boolean extreureId) throws ServletException, IOException{
		// Valido i obtinc els valors del formulari
		// Id
		Long nId = null;
		
		if (extreureId) {
			try {
				nId = RequestValidationUtils.getMandatoryLong(
						PARAM_ID, 
						request);
			} catch (ParameterException e) {
				EWokController.addI18nMessage(ETipusMissatge.error, PARAM_ERROR_UNITAT, request);
				doLlistatUnitat(request, response);
				return null;
			}
			
			Unitat unitat1 = new Unitat();
			try {
				unitat1 = daoUnitat.obtenirPerId(nId);
			} catch (SQLException e) {
				EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_ID, new String[]{nId.toString()}, request);
				doLlistatUnitat(request, response);
				return null;
			}
			if (unitat1 == null) {
				EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_ID, new String[]{nId.toString()}, request);
				doLlistatUnitat(request, response);
				return null;
			}
		}
		
		// Nom
		String sNom = null;
		try {
			sNom = RequestValidationUtils.getMandatoryString(PARAM_NOM, request);
		} catch (ParameterException e) {
			// Si es produeix un error ("e" tipus ParameterException) en la validació del nom ("sNom"),
			// capturo l'excepció, retorno un error
			//EWokController.addI18nMessage(ETipusMissatge.error, PARAM_ERROR_NOM, request);
			request.setAttribute(PARAM_ERROR_NOM,true);
		}
		
		// Acrònim
		String sAcronim = null;
		try {
			sAcronim = RequestValidationUtils.getMandatoryString(PARAM_ACRONIM, request);
		} catch (ParameterException e2) {
			// Si es produeix un error ("e2" tipus ParameterException) en la validació de l'acrònim ("sAcronim"),
			// capturo l'excepció, retorno un error
			//EWokController.addI18nMessage(ETipusMissatge.error, PARAM_ERROR_ACRONIM, request);
			request.setAttribute(PARAM_ERROR_ACRONIM,true);
		}
		
		// Creo una unitat amb els valors del formulari
		Unitat unitat2 = new Unitat();
		if (nId != null) {
			unitat2.setId(nId);
		}
		if (sNom != null) {
			unitat2.setNom(sNom);
		}
		if (sAcronim != null) {
			unitat2.setAcron(sAcronim);
		}
		
		// Si hi ha algun error de validació, retorno al formulari
		if ((extreureId && nId == null) ||
			 sNom == null ||
			 sAcronim == null) {
			// Si hi ha algun error i era una modificació (extreureId == true),
			// llavors necessito posar una unitat com a flag
			if (extreureId) {	
				//request.setAttribute(MODEL_UNITAT, unitat2);	
			}
			// Si hi ha algun error i era una alta (extreureId == false),
			// llavors redirigeixo cap al formulari
			EWokController.forward("staff/gUnitat/formUnitat.jsp", request, response);
			return null;
		}	
		return unitat2;
	}
	
	// Mètode per obtenir l'id d'1 unitat
	private Long getId(
			HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException, ParameterException {
		// Obtinc l'id de la url
		Long nId = RequestValidationUtils.getMandatoryLong(PARAM_ID, request);
		
		// Miro que l'id no sigui null
		if (nId == null) {
			// Si ha hagut algun error, vaig al llistat d'unitats
			doLlistatUnitat(request, response);
			return null;
		}
		// Si tot ha anat bé, retorno l'id
		return nId;
	}
}
