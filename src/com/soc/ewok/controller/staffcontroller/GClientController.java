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
import com.soc.ewok.dao.ClientDAO;
import com.soc.ewok.model.Client;
import com.soc.ewok.model.Rol;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GClientController extends SeccioController {
	
	//Constants de la classe, nom de l'acció del controller//
	private static final String ACCIO_CLIENT = "accioClients";
	
	//Constants de model//
	private static final String MODEL_CLIENT = "client";
		
	//Constants de paràmetres//
	private static final String PARAM_NOM = "nom";
	private static final String PARAM_COGNOMS = "cognom";
	private static final String PARAM_TELEFON = "telefon";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_DNI = "dni";
	private static final String PARAM_ID = "id";
	private static final String PARAM_DATALTA = "datalta";
	private static final String PARAM_ID_USUARI = "idusuari";
	
	//Constant d'error//
	private static final String REQ_ERRORGENERIC = "errorGeneric";
	private static final String PARAM_ERROR_NOM = "errornom";
	private static final String PARAM_ERROR_DATALTA = "erroralta";
	
	
		
	ClientDAO daoclient = null;
	
	
	// Constructor
	public GClientController() {
		// ewok.
		daoclient = new ClientDAO(EWokController.getGlobalDatasource());
		// Afegir els rols que poden usar aquest controller
		addRols2Controller(Rol.ROL_CODI_ADMINISTRADOR);
		addRols2Controller(Rol.ROL_CODI_CAIXER);
	}

	@Override
	public String getNomAccio() {
		// TODO Auto-generated method stub
		return ACCIO_CLIENT;
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
			doVeureClient(request, response);
		}
		else if(accio.equals(ACCIO_ESBORRAR)){
			doEsborrarClient(request, response);
		}
		else if(accio.equals(ACCIO_PREPARARALTA)){
			doPreparaAltaClient(request, response);
		}
		else if(accio.equals(ACCIO_ALTA)){
			doAltaClient(request, response);
		}
		else if(accio.equals(ACCIO_PREPARARMODIFICACIO)){
			doPreparaModificacioClient(request, response);
		}
		else if(accio.equals(ACCIO_MODIFICACIO)){
			doModificaClient(request, response);
		}
		else if(accio.equals(ACCIO_LLISTAT)){
			doLlistatClient(request, response);
		}
	
	}

	private void doLlistatClient(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		// redirigir a pàgina de llistat de clients amb
				//les dades de tots els clients
				
				// Obtenim les dades del DAO
				List<Client> client = null;
				
				try {
					// Obtenim les dades del DAO
					client = daoclient.obtenirTots();	
				
				} catch(SQLException e) {
					// Si no ho aconseguim, cal indicar l'error i acabar
					EWokController.addMessage(ETipusMissatge.error,
							"No s'ha pogut obtenir la llista de clients", request);
				}
						
				// Fiquem les dades al request
				request.setAttribute(MODEL_CLIENT, client);
				
				// Redirigim cap al llistatClients.jsp
				EWokController.forward("staff/gClient/LlistatClients.jsp", request, response);
	
	}


	

				
/**
 * Funció d'utilitat per omplir un Client amb les dades del request
 * @param request
 * @param response
 * @param extreureId
 * @return
 * @throws ServletException
 * @throws IOException
 */
	private Client extreureClient(
			HttpServletRequest request,
			HttpServletResponse response, boolean extreureId)
					throws ServletException, IOException {
		// Obtenim els valors del formulari
				Long nId = null;
				Client c = null;
				if (extreureId) {
					nId = getidFromRequest(request, null);
				}
		// Nom NOTNULL 
				String sNom = null;
				try {
					sNom = RequestValidationUtils
							.getMandatoryString(PARAM_NOM, request);
				} catch (ParameterException e) {
					request.setAttribute(PARAM_ERROR_NOM, true);
				}
		// Cognoms
				String sCognoms = RequestValidationUtils.getNonMandatoryString(
						PARAM_COGNOMS, request);
		// Telefon
				String sTelefon = RequestValidationUtils.getNonMandatoryString(
						PARAM_TELEFON, request);
		//Data Alta
				Date dDataAlta = null;
				try {
					dDataAlta = RequestValidationUtils.getNonMandatoryDate(
							PARAM_DATALTA, request);
				} catch (ParameterException e) {
					request.setAttribute(PARAM_ERROR_DATALTA, true);
				}
		//Dni
				String sDni = RequestValidationUtils.getNonMandatoryString(
						PARAM_DNI, request);
		//Email
				String sEmail = RequestValidationUtils.getNonMandatoryString(
						PARAM_EMAIL, request);
		//IsUsuari
				String sIdUsuari = RequestValidationUtils.getNonMandatoryString(
						PARAM_ID_USUARI, request);
		
		//Creem un client amb els valors recuperats
				
				Client client = new Client();
				client.setId(nId);
				client.setNom(sNom);
				client.setCognoms(sCognoms);
				client.setEmail(sEmail);
				client.setDni(sDni);
				client.setDataAlta(dDataAlta);
				client.setTelefon(sTelefon);
				client.setIdUsuari(sIdUsuari);
							
				
				// Si hi ha algun error de validació, retornem al formulari
				if (
						(extreureId && nId == null) || 
						sNom == null 
					) {
					// Si hi ha algun error i era una modificació
					// (extreuId == true), llavor necessitem posar un client
					// com a flag
					if (extreureId) {
						request.setAttribute(MODEL_CLIENT, c);
					}
					request.
					getRequestDispatcher("staff/gClient/formClient.jsp").
					forward(request,response);
					return null;
				}
				return client;
			}
				
				
				

	private void doPreparaModificacioClient(
			HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// Obtenim l'id
		Long nId = getId(request, response);
		
		// Si no hem obtingut id, hem acabat, perquè el getId,
		// ja haurà redirigit cap al llistat
		if (nId == null) {
			return;
		}
		
		// Obtenim el client a través del DAO
		Client c = null;
		
		try {
			c = daoclient.obtenirPerId(nId);
		
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error, 
					"No s'ha trobat el client amb id", request);
		}
		
		if (c == null) {
			// Si no hem trobat el client, redirigim al llistat
			// posant l'error adequat
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha trobat el client amb id " + nId, request);
			doLlistatClient(request, response);
			return;
		}
		
		// Posem el Client al contexte adequat
		request.setAttribute(MODEL_CLIENT, c);
		
		// Redirigim cap al formulari
		EWokController.forward("staff/gClient/formClient.jsp", request, response);
		
	}

	private void doModificaClient(
			HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		
		Client c = extreureClient(request, response, true);
		
		// Si no l'he obtingu no faig res
		// (extreureClient ja haurà redirigit on calgui)
		if (c == null) {
			return;
		}
				
		// Si tot ha anat bé
		// Donem d'alta la persona a través del DAO
		try {
			daoclient.modificar(c);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error, 
					"No s'ha pogut modificar el client", request);
		}
				
		// Redirigim cap al llistat de Clients
		doLlistatClient(request, response);
		
	}
	private void doAltaClient(
			HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// Intento obtenir el client
				Client client = extreureClient(request, response);
				// Si no l'he obtingut no faig res
				// (extreure Client ja haura redirigit on calgui)
				if (client == null) {
					return;
				}
				// Si tot ha anat be
				// Donem d'alta el client a traves del DAO
				try {
					daoclient.alta(client);
				} catch (SQLException e) {
					// Si hi ha error al donar d'alta, printem error
					EWokController.addI18nMessage(ETipusMissatge.error,
							REQ_ERRORGENERIC, request);
					// Redirigim cap al llistat de productes
					doLlistatClient(request, response);
					return;
				}
				EWokController.addI18nMessage(ETipusMissatge.info,
						REQ_ERRORGENERIC, request);
				// Redirigim cap al llistat de productes
				doLlistatClient(request, response);
		
	}
/**
 * 
 * @param request
 * @param response
 * @return
 */
	private Client extreureClient(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return extreureClient(request, response, false);
	}


	private void doPreparaAltaClient(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// No cal fer res amb el model, 
		// simplement redirigim cap al JSP formulari
				EWokController.forward("staff/gClient/formClient.jsp", request, response);
		
	}

	private void doEsborrarClient(
			HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// Obtenim l'id que ens passen
				Long nId = getId(request, response);
				if (nId == null) {
					// Si no tinc Id, no he de fer res
					return;
				}
				// Esborrem el client
				try {
					daoclient.esborrar(nId);
				} catch (SQLException e) {
					// I si ha anat malament, pintarem un error
					EWokController.addMessage(ETipusMissatge.error,
							"No s'ha pogut esborrar el client d'Id " + nId + 
							".Possiblement ha estat esborrat amb anterioritat.",
							request);
					}
				// Hagi anat bé o malament, pintem el llistat de clients
				doLlistatClient(request, response);
		
	}

	private void doVeureClient(
			HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		
		Long nId = getId(request,response);
		Client c = null;
		if (nId == null) {
			// Si no tinc Id, no he de fer res
			return;
		// compruebo que no se lanza excepción
		} try {
		// Obtenim les dades del DAO
		c = daoclient.obtenirPerId(nId);	
		} catch(SQLException e) {
			// Si no ho aconseguim, cal indicar l'error i acabar
			EWokController.addMessage(ETipusMissatge.error, 
					"No s'ha trobat el client amb id " + nId, request);
			doLlistatClient(request, response);
			return;
		}
		
		if (c == null) {
			EWokController.addMessage(ETipusMissatge.error,
					"No s'ha trobat el client amb id " + nId, request);
			doLlistatClient(request, response);
			return;
		}
		
		// Fiquem les dades al request
		request.setAttribute(MODEL_CLIENT, c);
		
		// Redirigim cap a la JSP
		EWokController.forward("staff/gClient/veureClient.jsp", request, response);
	}


	private Long getId(
			HttpServletRequest request, 
			HttpServletResponse response)throws ServletException, IOException {
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
							"No s'ha trobat el client amb id" + nId, request);
				}
				// Si no ha habido problemas con el nId, comprobamos que no sea null
				if (nId == null) {
					// Si es null, vaig al llistat de Clients y retorno null
					doLlistatClient(request, response);
					return null;
				}
				// Si tot ha anat bé, retorno l'id
				return nId;
	
	}

}
