package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.OfertaComandaDAO;
import com.soc.ewok.model.OfertaComanda;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;
import com.soc.ewok.controller.staffcontroller.SeccioController;

public class GOfertaComandaController extends SeccioController {
	
	private static final String ACCIO_OFERTA_COMANDA = "accioOfertaComanda";
	private static final String PARAM_ID = "id";
	private static final String PARAM_LIMIT_INFERIOR = "limitInferior";
	private static final String PARAM_PERCENTATGE = "pctDescompte";
	private static final String PARAM_INICI_VIGENCIA = "iniciVigencia";
	private static final String PARAM_FI_VIGENCIA = "fiVigencia";
	
	private static final String MODEL_OFERTA_COMANDA = "ofertaComanda";
	private static final String REQ_ERRORGENERIC = "errorGeneric";
	
	OfertaComandaDAO daoOfertaComanda = null;
	
	public GOfertaComandaController() {
		// De moment creem un dao que anirà contra memòria,
		// en un futur hauriem d'agafar del web.xml
		// la classe amb la que volem treballar i crear
		// l'objecte a partir d'aquell nom
		//daoOfertaComanda = new OfertaComandaDAO(ds);
		daoOfertaComanda = new OfertaComandaDAO(EWokController.getGlobalDatasource());
	}
	
	@Override
	public String getNomAccio() {
		// TODO Auto-generated method stub
		return ACCIO_OFERTA_COMANDA;
	}

	@Override
	public void doGet(
			String accio, 
			HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
	
		// Mètode principal per servir totes les peticions
		// relatives a ofertaComanda
				
			if(accio.equals(ACCIO_VEURE)){
				doVeureOfertaComanda(request, response);
			}
			else if(accio.equals(ACCIO_ESBORRAR)){
				doEsborrarOfertaComanda(request, response);
			}
			else if(accio.equals(ACCIO_PREPARARALTA)){
				doPreparaAltaOfertaComanda(request, response);
			}
			else if(accio.equals(ACCIO_ALTA)){
				doAltaOfertaComanda(request, response);
			}
			else if(accio.equals(ACCIO_PREPARARMODIFICACIO)){
				doPreparaModificacioOfertaComanda(request, response);
			}
			else if(accio.equals(ACCIO_MODIFICACIO)){
				doModificaOfertaComanda(request, response);
			}
			else if(accio.equals(ACCIO_LLISTAT)){
				doLlistatOfertaComanda(request, response);
			}
	}

	private void doPreparaModificacioOfertaComanda(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
				// Obtenim l'id
				Long nId = getId(request, response, "modificar");
				
				// Si no hem obtingut id, hem acabat, perquè el getId,
				// ja haurà redirigit cap al llistat
				if (nId == null) {
					return;
				}
				
				// Obtenim la ofertaComanda a través del DAO
				OfertaComanda oc = null;
				
				try {
					oc = daoOfertaComanda.obtenirPerId(nId);
				
				} catch (SQLException e) {
					EWokController.addMessage(ETipusMissatge.error, 
							"No s'ha trobat el producte amb id", request);
				}
				
				if (oc == null) {
					// Si no hem trobat la ofertaComanda, redirigim al llistat
					// posant l'error adequat
					EWokController.addMessage(ETipusMissatge.error,
							"No s'ha trobat el producte amb id " + nId, request);
					doLlistatOfertaComanda(request, response);
					return;
				}
				
				// Posem la ofertaComanda al contexte adequat
				request.setAttribute(MODEL_OFERTA_COMANDA, oc);
				
				// Redirigim cap al formulari
				EWokController.forward("staff/gOfertaComanda/formOfertaComanda.jsp", request, response);
	}
	
	private void doLlistatOfertaComanda(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<OfertaComanda> ofertaComanda = null;
		
		try {
			// Obtenim les dades del DAO
			ofertaComanda = daoOfertaComanda.obtenirTots();	
		
		} catch(SQLException e) {
			// Si no ho aconseguim, cal indicar l'error i acabar
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha pogut obtenir la llista de oferta comandas", request);
		}
				
		// Fiquem les dades al request
		request.setAttribute(MODEL_OFERTA_COMANDA, ofertaComanda);
		
		// Redirigim cap al llistatOfertaComanda.jsp
		EWokController.forward("staff/gOfertaComanda/llistatOfertaComanda.jsp", request, response);
	}

	private void doModificaOfertaComanda(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Intento obtenir la persona 
		OfertaComanda oc = extreureOfertaComanda(request, response, true);
				
		// Si no l'he obtingu no faig res
		// (extreOfertaComanda ja haurà redirigit on calgui)
		if (oc == null) {
			return;
		}
				
		// Si tot ha anat bé
		// Donem d'alta la persona a través del DAO
		try {
			daoOfertaComanda.modificar(oc);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error, 
					"No s'ha pogut modificar la oferta comanda", request);
		}
				
		// Redirigim cap al llistat de OfertaComanda
		doLlistatOfertaComanda(request, response);
	}

	private void doAltaOfertaComanda(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Intento obtenir la persona 
		OfertaComanda oc = extreureOfertaComanda(request, response, false);
		// Si no l'he obtingu no faig res
		// (extrePersona ja haurà redirigit on calgui)
		if (oc == null) {
			return;
		}
		
		// Si tot ha anat bé
		// Donem d'alta la persona a través del DAO
		
		try {
			daoOfertaComanda.alta(oc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha pogut donar d'alta la oferta comanda", request);
		}
		
		// Redirigim cap al llistat de persones
		doLlistatOfertaComanda(request, response);
	}
	
	private OfertaComanda extreureOfertaComanda(
			HttpServletRequest request,
			HttpServletResponse response,
			boolean extreureId) throws ServletException, IOException {
		
		Long nId = null;
		OfertaComanda oc = null;
		
		// Id
		if (extreureId) {
			try {
				nId = RequestValidationUtils.getMandatoryLong(
					PARAM_ID, 
					request);
			} catch (ParameterException e) {
				EWokController.addMessage(ETipusMissatge.error,
						"No s'ha pogut obtenir la oferta comanda d'Id " + nId,
						request);
			}
		}
				
		try {
			oc = daoOfertaComanda.obtenirPerId(nId);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			
		// LimitInferior
		Float nLimitInferior = null;
		try {
			nLimitInferior = RequestValidationUtils.getMandatoryFloat(
					PARAM_LIMIT_INFERIOR,  
					request);
		} catch (ParameterException e1) {
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha pogut obtenir el limit inferior de la comanda amb l'Id " + nId,
					request);
		}
		
		// PctDescompte
		Float nPctDescompte = null;
		try {
			nPctDescompte = RequestValidationUtils.getMandatoryFloat(
					PARAM_PERCENTATGE, 
					request);
		} catch (ParameterException e) {
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha pogut obtenir el percentatge de descompte de la comanda amb l'Id " + nId,
					request);
		}
		
		//IniciVigencia
		Date dIniciVigencia = null;
		try {
			dIniciVigencia = RequestValidationUtils.getNonMandatoryDate(
					PARAM_INICI_VIGENCIA, 
					request);
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha pogut obtenir el inici de vigencia de la comanda amb l'Id " + nId,
					request);
		}
		
		// FiVigencia
		Date dFiVigencia = null;
		try {
			dFiVigencia = RequestValidationUtils.getNonMandatoryDate(
					PARAM_FI_VIGENCIA, 
					request);
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha pogut obtenir el fi de vigencia de la comanda amb l'Id " + nId,
					request);
		}
		
		// Creem una comanda amb els valors recuperats
		OfertaComanda ofertaComanda = new OfertaComanda();
		ofertaComanda.setId(nId);
		ofertaComanda.setLimitInferior(nLimitInferior);
		ofertaComanda.setPctDescompte(nPctDescompte);
		/** Quitar comentario cuando tengamos los metodos getNonMandatoryDate**/
		ofertaComanda.setIniciVigencia(dIniciVigencia);
		ofertaComanda.setFiVigencia(dFiVigencia);
		
		// Si hi ha algun error de validació, retornem al formulari
		if (
				(extreureId && nId == null) || 
				nLimitInferior == null || 
				nPctDescompte == null || 
				dIniciVigencia == null || 
				dFiVigencia == null
			) {
			// Si hi ha algun error i era una modificació
			// (extreuId == true), llavor necessitem posar una persona
			// com a flag
			if (extreureId) {
				request.setAttribute(MODEL_OFERTA_COMANDA, oc);
			}
			request.
				getRequestDispatcher("paginesPersones/formPersona.jsp").
				forward(request,  response);
			return null;
		}
		return ofertaComanda;
	}

	private void doPreparaAltaOfertaComanda(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// No cal fer res amb el model, 
		// simplement redirigim cap al JSP formulari
		EWokController.forward("staff/gOfertaComanda/formOfertaComanda.jsp", request, response);	
	}

	private void doEsborrarOfertaComanda(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtenim l'id que ens passen
		Long nId = getId(request, response, "esborrar");
		if (nId == null) {
			// Si no tinc Id, no he de fer res
			return;
		}
		// Esborrem la oferta comanda
		try {
			daoOfertaComanda.esborrar(nId);
		} catch (SQLException e) {
			// I si ha anat malament, pintarem un error
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha pogut esborrar la oferta comanda d'Id " + nId + 
					".Possiblement ha estat esborrat amb anterioritat.",
					request);
			}
		// Hagi anat bé o malament, pintem el llistat de productes
		doLlistatOfertaComanda(request, response);
		}

	private void doVeureOfertaComanda(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Long nId = getId(request, response, "veure");
		OfertaComanda oc = null;
		if (nId == null) {
			// Si no tinc Id, no he de fer res
			return;
		// compruebo que no se lanza excepción
		} try {
		// Obtenim les dades del DAO
		oc = daoOfertaComanda.obtenirPerId(nId);	
		} catch(SQLException e) {
			// Si no ho aconseguim, cal indicar l'error i acabar
			EWokController.addMessage(ETipusMissatge.error, 
					"No s'ha trobat el producte amb id " + nId, request);
			doLlistatOfertaComanda(request, response);
			return;
		}
		
		if (oc == null) {
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha trobat el producte amb id " + nId, request);
			doLlistatOfertaComanda(request, response);
			return;
		}
		
		// Fiquem les dades al request
		request.setAttribute(MODEL_OFERTA_COMANDA, oc);
		
		// Redirigim cap a la JSP
		EWokController.forward("staff/gOfertaComanda/veureOfertaComanda.jsp", request, response);
	}
	
	private Long getId(
			HttpServletRequest request,
			HttpServletResponse response,
			String accioMissatge) throws ServletException, IOException {
		
		// Iniciamos la variable nId
		Long nId = null;
		// Comprobamos que no lanza excepción sql
		try {
			nId = RequestValidationUtils.getMandatoryLong(
					PARAM_ID, 
					request);
		} catch (ParameterException e) {
		// Si hay errores, lazamos excepción
			EWokController.addMessage(ETipusMissatge.error, 
					"No s'ha trobat el producte amb id" + nId, request);
		}
		// Si no ha habido problemas con el nId, comprobamos que no sea null
		if (nId == null) {
			// Si es null, vaig al llistat de ofertaComandas y retorno null
			doLlistatOfertaComanda(request, response);
			return null;
		}
		// Si tot ha anat bé, retorno l'id
		return nId;
	}
}