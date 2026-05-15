package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.RolDAO;
import com.soc.ewok.model.Rol;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GRolController extends SeccioController {
	
	private static final String ACCIO_ROL = "accioRol";
	private static final String MODEL_ROL = "rol";
	
	private static final String REQ_ERRORLLISTAT = "No s'ha pogut obtenir el llistat de Rols";
	private static final String REQ_ERRORPARAM = "Error en el paràmetre";
	private static final String REQ_ERRORGENERIC = "No s'ha pogut trobar el Rol mitjançant la id";
	private static final String REQ_ERRORBORRAR = "No s'ha pogut esborrar el Rol";
	private static final String REQ_ERRORALTA = "No s'ha pogut donar d'alta el Rol";
	private static final String REQ_ERRORMODIFICACIO = "No s'ha pogut modificar la informació";
	
	private static final String PARAM_ID = "id";
	private static final String PARAM_NOM = "nom";
	

	
	RolDAO daoRol = new RolDAO(EWokController.getGlobalDatasource());

	@Override
	public String getNomAccio() {
		return ACCIO_ROL;
	}
	
	// Mètode principal per servir totes les peticions relatives a Rols
	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("msg",	"He passat pel controlador de Rols");
		
		
		if (accio.equals(ACCIO_VEURE)) {
			doVeureRol(request, response);
		}
		else if (accio.equals(ACCIO_ESBORRAR)) {
			doEsborrarRol(request, response);
		}
		else if (accio.equals(ACCIO_PREPARARALTA)) {
			doPreparaAltaRol(request, response);
		}
		else if (accio.equals(ACCIO_ALTA)) {
			doAltaRol(request, response);
		}
		else if (accio.equals(ACCIO_PREPARARMODIFICACIO)) {
			doPreparaModificaRol(request, response);
		}
		else if (accio.equals(ACCIO_MODIFICACIO)) {
			doModificaRol(request, response);
		}
		else if (accio.equals(ACCIO_LLISTAT)) {
			doLlistatRol(request, response);
		}
		
	}

	private void doLlistatRol(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<Rol> rols = null;
		// Obtenim les dades del DAO
		try {
			rols = daoRol.obtenirTots();
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORLLISTAT, request);
		}
		// Posem les dades al request
		request.setAttribute (MODEL_ROL, rols);
		
		// Redirigim cap al llistatRol.jsp
		EWokController.forward("staff/gRol/llistatRol.jsp", request, response);
	}
	

	private void doModificaRol(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Intentem obtenir el Rol
		Rol rol = null;
		try {
			rol = extreureRol(request, response, true);
		} catch (ParameterException e) {
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORPARAM, request);
		}
		// Si no l'hem obtingut no fem res, extreureRol ja haurà redirigit on calgui
		if (rol == null) {
			return;
		}
		// Si tot ha anat bé donem d'alta el Rol mitjançant el DAO
		try {
			daoRol.modificar(rol);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORMODIFICACIO, request);
		}
		// Redirigim cap al llistat de Rols
		doLlistatRol(request, response);
	}
	
	
	private void doPreparaModificaRol(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtenim l'id
		Long nId = getId(request, response, "prepararModificacio");
		// Si no obtenim id no cal fer res ja que el getId haurà redirigit cap al llistat
		if (nId == null) {
			return;
		}
		// Obtenim el Rol mitjançant el DAO
		Rol rol = null;
		try {
			rol = daoRol.obtenirPerId(nId);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORGENERIC, request);
		}
		if (rol == null) {
			// Si no hem trobat el rol redirigim al llistat amb l'error adequat
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORGENERIC, request);
			doLlistatRol(request, response);
			return;
		}
		// Posem el rol en el request
		request.setAttribute(MODEL_ROL, rol);
		// Redirigim al formulari
		EWokController.forward("staff/gRol/formRol.jsp", request, response);
	}
	

	private void doAltaRol(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Intentem obtenir el Rol
		Rol rol = null;
		try {
			rol = extreureRol(request, response, false);
		} catch (ParameterException e) {
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORPARAM, request);
		}
		// Si no hem obtingut el Rol no fem res, estreureRol ja redirigirà on calgui
		if (rol == null) {
			return;
		}
		// Si tot ha anat bé donem d'alta el Rol mitjançant el DAO
		try {
			daoRol.alta(rol);
		} catch (SQLException e1) {
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORALTA, request);
		}
		// Redirigim cap al llistat de rols
		doLlistatRol(request, response);
	}
	

	private Rol extreureRol(HttpServletRequest request,
			HttpServletResponse response, boolean extreureId) throws ParameterException {
		
		// Obtenim i validem els valors del formulari
		//Id
		Long nId = null;
		if (extreureId) {
			nId = RequestValidationUtils.getMandatoryLong(PARAM_ID, request);
		}
		
		//Nom
		String sNom = RequestValidationUtils.getMandatoryString(PARAM_NOM, request);
	
		// Creem un Rol amb els valors del formulari
		Rol rol = new Rol();
		rol.setId(nId);
		rol.setNom(sNom);
		
		return rol;
	}

	
	private void doPreparaAltaRol(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//No cal fer res amb el model, redirigim cap al formulari JSP
		EWokController.forward("staff/gRol/formRol.jsp", request, response);
		
	}
	

	private void doEsborrarRol(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtenim la id que ens passen
		Long nId = getId(request, response, "esborrar");
		if (nId == null) {
			// Si no tenim id no hem de fer res
			return;
		}
		// A l'obtenir la id esborrem el Rol
		try {
			daoRol.esborrar(nId);
		} catch (SQLException e) {
			//Si quelcom va malament avisem amb un missatge d'error
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORBORRAR, request);
		}
		//En qualsevol cas, pintem la llista de Rols
		doLlistatRol(request, response);
	}

	
	private void doVeureRol(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Obtenim l'id que ens passen
		Long nId = getId(request, response, "veure");
		if (nId == null) {
			// Si no tinc Id no he de fer res
			return;
		}
		// Obtenim les dades del DAO
		Rol rol = null;
		try {
			rol = daoRol.obtenirPerId(nId);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error,	REQ_ERRORGENERIC, request);
		  }
		if (rol == null) {
			EWokController.addMessage(ETipusMissatge.error, REQ_ERRORGENERIC, request);
			return;
		}
		
		// Posem les dades al request
		request.setAttribute(MODEL_ROL, rol);
		
		// Redirigim a la JSP
		EWokController.forward("staff/gRol/VeureRol.jsp", request, response);
	}


	private Long getId(HttpServletRequest request,
			HttpServletResponse response, String accioMissatge) throws ServletException, IOException {
		
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
		// comprobem que no es null
		if (nId == null) {
			// Si ha hagut algun error, vaig al llistat de Rols
			doLlistatRol(request, response);
			return null;
			}
		// Si tot ha anat bé, retorno l'id
		return nId;	}
	}



