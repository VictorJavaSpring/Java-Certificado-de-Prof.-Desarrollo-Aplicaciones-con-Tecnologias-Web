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
import com.soc.ewok.dao.PreuDAO;
import com.soc.ewok.model.Preu;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GPreuController extends SeccioController {

	private static final String PARAM_ACCIOPREU = "accioPreu";
	
	private static final String MODEL_LLISTAPREUS = "preus";
	private static final String MODEL_PREU = "preu";
	
	private static final String PARAM_ID = "id";
	private static final String PARAM_PREU = "preu";
	private static final String PARAM_DATA_INICI = "dataInici";
	private static final String PARAM_HORA_INICI = "horaInici";
	private static final String PARAM_DATA_FINAL = "dataFinal";
	private static final String PARAM_HORA_FINAL = "horaFinal";
	private static final String PARAM_ID_PRODUCTE = "idProducte";
	
	private static final String PARAM_ERROR_PREU = "errorPreu";
	private static final String PARAM_ERROR_DATAINICI = "errorDataInici";
	private static final String PARAM_ERROR_DATAINICI_NN = "errorDataIniciNN";
	private static final String PARAM_ERROR_HORAINICI =	"errorHoraInici";
	private static final String PARAM_ERROR_DATAFINAL = "errorDataFinal";
	private static final String PARAM_ERROR_DATAFINAL_NN = "errorDataFinalNN";
	private static final String PARAM_ERROR_HORAFINAL =	"errorHoraFinal";
	private static final String PARAM_ERROR_IDPRODUCTE = "errorIdProducte";
	
	private static final String ERROR_PREU_NO_TROBAT_AMBPARAM = "preu001.errorPreuNoTrobatAmbPar";
	private static final String ERROR_PREU_NO_TROBAT = "preu002.errorPreuNoTrobat";
	private static final String ERROR_MODIFICACIO = "preu003.errorPreuNoModificat";
	private static final String ERROR_ALTA = "preu004.errorPreuNoAlta";
	private static final String ERROR_ESBORRAR = "preu005.errorPreuNoEsborrat";
	private static final String ERROR_VEURE = "preu006.errorPreuNoObert";
	private static final String ERROR_LLISTA_PREUS = "preu007.errorObtenirLlistaPreus";
	private static final String INFO_ESBORRAR_AMBPARAM = "preu008.infoPreuEsborratAmbPar";

	PreuDAO preuDao = new PreuDAO(EWokController.getGlobalDatasource());
	boolean isModificacioPreparata = false;

	@Override
	public String getNomAccio() {
		return PARAM_ACCIOPREU;
	}

	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", "he passat per GPreuController");
		switch(accio) {
		case ACCIO_VEURE:
			doVeurePreu(request, response);
			break;
		case ACCIO_ESBORRAR:
			doEsborrarPreu(request, response);
			break;
		case ACCIO_PREPARARALTA:
			doPreparaAltaPreu(request, response);
			break;
		case ACCIO_ALTA:
			doAltaPreu(request, response);
			break;
		case ACCIO_PREPARARMODIFICACIO:
			doPreparaModificacioPreu(request, response);
			break;
		case ACCIO_MODIFICACIO:
			doModificaPreu(request, response);
			break;
		case ACCIO_LLISTAT:
		default:
			doLlistatPreu(request, response);

		}
	}

	private void doPreparaModificacioPreu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtenim l'id
		Long nId = null;
		
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			EWokController.addI18nMessage(ETipusMissatge.error, ERROR_PREU_NO_TROBAT, request);
			doLlistatPreu(request, response);			
			return;
		}
		// Si no hem obtingut id, hem acabat, perquè el getId,
		// ja haurà redirigit cap al llistat
		if (nId == null) {
			return;
		}
		// Obtenim el preu a través del DAO
		Preu p;
		try {
			p = preuDao.obtenirPerId(nId);
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.error, null, ERROR_PREU_NO_TROBAT_AMBPARAM, new String[]{nId.toString()}, request);
			doLlistatPreu(request, response);
			return;
		}
		if (p == null) {
			// Si no hem trobat el preu, redirigim al llistat
			// posant l'error adequat
			EWokController.addI18nMessage(ETipusMissatge.error, null, ERROR_PREU_NO_TROBAT_AMBPARAM, new String[]{nId.toString()}, request);
			doLlistatPreu(request, response);
			return;
		}
		// Posem el preu al contexte adequat
		request.setAttribute(MODEL_PREU, p);
		
		// Poso true al flag
		isModificacioPreparata = true;
		
		// Redirigim al formulari
		EWokController.forward("staff/gPreu/formPreu.jsp", request, response);
	}
	
	private void doModificaPreu(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		if (isModificacioPreparata) {
			// Intento obtenir el preu
			Preu p = extreurePreu(request, response, true);
			
			// Si no l'he obtingut no fem res, extreurePreu ja haurà redirigit on calgui
			if (p == null) {
				return;
			}
			
			// Modifiquem el preu a través del DAO
			try {
				preuDao.modificar(p);
			} catch (SQLException e) {
				EWokController.addI18nMessage(ETipusMissatge.error, ERROR_MODIFICACIO, request);
				doLlistatPreu(request, response);
				return;
			}
			
			// Tornem a posar el flag en false
			isModificacioPreparata = false;
			
			// Redirigim cap al llistat de preus
			doLlistatPreu(request, response);
			return;
		} else {
			doPreparaModificacioPreu(request, response);
		}
	}

	private void doPreparaAltaPreu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Redirigim cap al formulari per fer l'alta d'un nou preu	
		EWokController.forward("staff/gPreu/formPreu.jsp", request, response);
	}
	
	private void doAltaPreu(
			HttpServletRequest request,	
			HttpServletResponse response) throws ServletException, IOException {
			
			// Intento obtenir el preu del formulari 
			Preu p = extreurePreu(request, response, false);
			
			// Si no l'he obtingu no faig res
			// (extrePreu ja haurà redirigit on calgui)
			if (p == null) {
				return;
			}

			// Si tot ha anat bé
			// Donem d'alta la Preu a través del DAO
			try {
				preuDao.alta(p);
			} catch (SQLException e) {
				EWokController.addI18nMessage(ETipusMissatge.error, ERROR_ALTA, request);
				doLlistatPreu(request, response);
				return;
			}

			// Redirigim cap al llistat de preus
			doLlistatPreu(request, response);
			return;
	}

	private Preu extreurePreu(
			HttpServletRequest request, 
			HttpServletResponse response, 
			boolean extreureId) throws IOException, ServletException {
		// Validem i obtenim els valors del formulari

		System.out.println("He passat per extreurePreu");
		Long nId = null;
		if (extreureId) {
			try {
				nId = RequestValidationUtils.getMandatoryLong(
						PARAM_ID, 
						request);
			} catch (ParameterException e) {
				EWokController.addI18nMessage(ETipusMissatge.error, ERROR_PREU_NO_TROBAT, request);
				doLlistatPreu(request, response);
				return null;
			}
			
			Preu preu = new Preu();
			try {
				preu = preuDao.obtenirPerId(nId);
			} catch (SQLException e) {
				EWokController.addI18nMessage(ETipusMissatge.error, null, ERROR_PREU_NO_TROBAT_AMBPARAM, new String[]{nId.toString()}, request);
				doLlistatPreu(request, response);
				return null;
			}
			if (preu == null) {
				EWokController.addI18nMessage(ETipusMissatge.error, null, ERROR_PREU_NO_TROBAT_AMBPARAM, new String[]{nId.toString()}, request);
				doLlistatPreu(request, response);
				return null;
			}
		}

		// Preu
		Float nPreu = null;
		try {
			nPreu = RequestValidationUtils.getMandatoryFloat(
					PARAM_PREU, 
					request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERROR_PREU, true);
		}

		// Agafem la data i la hora d'inici vigencia del formulari i obtenim un Datetime
		Date dDataIHoraInici = null;
		try {
			dDataIHoraInici = RequestValidationUtils.obtenirDateTime(PARAM_DATA_INICI, PARAM_HORA_INICI, request);
		} catch (ParameterException pe) {
			request.setAttribute(PARAM_ERROR_DATAINICI, true);
		}

		// Agafem la data i la hora final de vigencia del formulari i obtenim un Datetime
		Date dDataIHoraFinal = null;
		try {
			dDataIHoraFinal = RequestValidationUtils.obtenirDateTime(PARAM_DATA_FINAL, PARAM_HORA_FINAL, request);
		} catch (ParameterException pe) {
			request.setAttribute(PARAM_ERROR_DATAFINAL, true);
		}


		// Agafem l'id producte del formulari
		Long nIdProducte = null;
		try {
			nIdProducte = RequestValidationUtils.getMandatoryLong(
					PARAM_ID_PRODUCTE, 
					request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERROR_IDPRODUCTE, true);
		}

		// Creem una Preu amb els valors del formulari
		Preu p = new Preu();
		if (nId != null) {
			p.setId(nId);
		}
		if (nPreu != null) {
			p.setPreu(nPreu);
		}
		p.setIniciVigencia(dDataIHoraInici);
		p.setFinalVigencia(dDataIHoraFinal);
		if (nIdProducte != null) {
			p.setIdProducte(nIdProducte);
		}
		
		// VALIDACIONS
		// Les dades i hores d'inici i de fi vigencia no són obligatories però
		// si ens informen de la hora ens han d'informar també del dia
		
		// Agafem la data d'inici vigencia del formulari per fer les validacions
		Date dDataInici = null;
		try {
			dDataInici = RequestValidationUtils.getNonMandatoryDate(PARAM_DATA_INICI, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_DATAINICI, true);
		}
		
		// Agafem l'hora d'inici vigencia del formulari per fer les validacions
		Date dHoraInici = null;
		try {
			dHoraInici = RequestValidationUtils.getNonMandatoryTime(PARAM_HORA_INICI, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_HORAINICI, true);
		}
		
		// Si ens informen de la hora però no de la data, pintem un error
		if (dDataInici == null && dHoraInici != null) {
			request.setAttribute(PARAM_ERROR_DATAINICI_NN, true);
		}
		
		// Fem el mateix amb la data i la hora final de vigencia
		
		// Agafem la data final de vigencia del formulari per fer les validacions
		Date dDataFinal = null;
		try {
			dDataFinal = RequestValidationUtils.getNonMandatoryDate(PARAM_DATA_FINAL, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_DATAFINAL, true);
		}
		
		// Agafem l'hora final de vigencia del formulari per fer les validacions
		Date dHoraFinal = null;
		try {
			dHoraFinal = RequestValidationUtils.getNonMandatoryTime(PARAM_HORA_FINAL, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_HORAFINAL, true);
		}
		
		// Si ens informen de la hora però no de la data, pintem un error
		if (dDataFinal == null && dHoraFinal != null) {
			request.setAttribute(PARAM_ERROR_DATAFINAL_NN, true);
		}
		
		// Si hi ha algun error de validació, retornem al formulari
		if ((extreureId && nId == null) || 
				nPreu == null || 
				nIdProducte == null || 
				(dDataInici == null && dHoraInici != null) || 
				(dDataFinal == null && dHoraFinal != null)) {
			// Si hi ha algun error i era una modificació (extreuId == true),
			// llavors necessitem posar un Preu com a flag
			if (extreureId) {
				request.setAttribute(MODEL_PREU, p);
			}
			EWokController.forward("staff/gPreu/formPreu.jsp", request, response);
			return null;
		}
		return p;
	}

	private void doEsborrarPreu(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// Obtenim l'id que ens passen
		Long nId = null;
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			// Si getId em llença un ParameterException retorno un missatge d'error
			// i redirigim al llistat de preus
			EWokController.addI18nMessage(ETipusMissatge.error, ERROR_ESBORRAR, request);
			doLlistatPreu(request, response);
			return;
		}

		try {
			preuDao.esborrar(nId);
			EWokController.addI18nMessage(ETipusMissatge.info, null, INFO_ESBORRAR_AMBPARAM, new String[]{nId.toString()}, request);
		} catch (SQLException e) {
			// Si hi ha error, pintem el missatge d'error i redirigim al llistat de preus
			EWokController.addI18nMessage(ETipusMissatge.error, ERROR_ESBORRAR, request);
			doLlistatPreu(request, response);
			return;
		}
		doLlistatPreu(request, response);
		return;
	}

	private void doVeurePreu(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Obtenim l'id que ens passen
		Long nId = null;
		
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			EWokController.addI18nMessage(ETipusMissatge.error, ERROR_VEURE, request);
			doLlistatPreu(request, response);
			return;
		}

		// Obtenim les dades del DAO
		Preu p = null;
		
		try {
			p = preuDao.obtenirPerId(nId);
		} catch (SQLException e) {
			// Si hi ha error, pintem el missatge d'error i redirigim al llistat de preus
			EWokController.addI18nMessage(ETipusMissatge.error, ERROR_VEURE, request);
			doLlistatPreu(request, response);
			return;
		}
		

		if (p == null) {
			// Si el preu p és null retorno un missatge d'error i redirigim al llistat de preus
			EWokController.addI18nMessage(ETipusMissatge.error, null, ERROR_PREU_NO_TROBAT_AMBPARAM, new String[]{nId.toString()}, request);
			doLlistatPreu(request, response);
			return;
		}
		
		// Fiquem les dades al request
		request.setAttribute(MODEL_PREU, p);
		
		// Redirigim cap al veurePreu.jsp
		EWokController.forward("staff/gPreu/veurePreu.jsp", request, response);
	}

	private void doLlistatPreu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenim les dades del DAO
		List<Preu> preus = null;
		try {
			preus = preuDao.obtenirTots();
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.error, ERROR_LLISTA_PREUS, request);
		}

		// Fiquem les dades al request
		request.setAttribute(MODEL_LLISTAPREUS, preus);

		// Redirigim cap al llistatPreu.jsp
		EWokController.forward("staff/gPreu/llistatPreu.jsp", request, response);
	}

	private Long getId(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException, ParameterException {
		// Obtenim l'id de la url
		Long nId = RequestValidationUtils.getMandatoryLong(PARAM_ID, request);
		if (nId == null) {
			// Si ha hagut algun error torno al llistat de preus
			doLlistatPreu(request, response);
			return null;
		}
		// Si tot ha anat bé, retorno l'id
		return nId;
	}
	
}
