package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.UsuariDAO;
import com.soc.ewok.model.Rol;
import com.soc.ewok.model.Usuari;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GUsuariController extends SeccioController{

	private static final String MODEL_USUARI = "usuaris";
	private static final String MODEL_LLISTAUSUARI = "usuarisLlista";
	private static final String MODEL_USUARIALTA = "usuariAlta";
	
	private static final String ACCIO_MODIFICACIO = "modificar";
	private static final String ACCIO_PREPARARMODIFICACIO = "prepararModificacio";
	private static final String ACCIO_ALTA = "alta";
	private static final String ACCIO_PREPARARALTA = "prepararAlta";
	private static final String ACCIO_VEURE = "veure";
	private static final String ACCIO_ESBORRAR = "esborrar";
	private static final String ACCIO_LLISTAT = "llistat";
	private static final String ACCIO_USUARI = "accioUsuari";
	private static final String ACCIO_MISSATGE = "accioMissatge";
	
	private static final String PARAM_EMAIL = "mail";
	private static final String PARAM_ERROR_EMAIL = "paramErrorMail";
	private static final String PARAM_PW = "pw";
	private static final String PARAM_ERROR_PW = "paramErrorPw";
	
	private static final String PARAM_ACTIU = "actiu";
	private static final String PARAM_ERROR_ACT = "paramErrorActiu";
	
	private static final String REQ_ERROREMAIL = "errorEmail";
	private static final String REQ_ERRORLLISTAT = "errorLlistat";
	private static final String REQ_ERRORGENERIC = "errorGeneric";
	private static final String REQ_MODIFICACIOALTA = "modificacioAlta";
	private static final String REQ_MODIFICACIOERROR = "modificacioError";
	private static final String REQ_MODIFICACIO = "reqModificacio";
	private static final String REQ_ERRORMODIFICACIO = "errorModificacio";
	private static final String REQ_ERRORALTA = "errorAlta";
	private static final String REQ_ERRORSQL = "errorSql";
	
	
	
	
	//vars membre 
	UsuariDAO daoUsuari = null;
	
	
	public GUsuariController() {
		// Creo un DAO que anirà contra BD
	daoUsuari = new UsuariDAO(EWokController.getGlobalDatasource());
	// Afegir els rols que poden usar aquest controller
//			addRols2Controller(Rol.ROL_CODI_ADMINISTRADOR);
//			addRols2Controller(Rol.ROL_CODI_CUINER);
//			addRols2Controller(Rol.ROL_CODI_CAIXER);
		}
	
	

	@Override
	public String getNomAccio() {
		// TODO Auto-generated method stub
		return ACCIO_USUARI;
	}
	
	

	@Override
	public void doGet (String accio, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Mètode principal per servir totes les peticions
				// relatives a OfertaPunts
		if (ACCIO_VEURE.equals(accio)) {
			doVeureUsuari(request, response);
		} else if (ACCIO_ESBORRAR.equals(accio)) {
			doEsborrarUsuari(request, response);
		} else if (ACCIO_PREPARARALTA.equals(accio)) {
			doPreparaAltaUsuari(request, response);
		} else if (ACCIO_ALTA.equals(accio)) {
			doAltaUsuari(request, response);
		} else if (ACCIO_PREPARARMODIFICACIO.equals(accio)) {
			doPreparaModificaUsuari(request, response);
		} else if (ACCIO_MODIFICACIO.equals(accio)) {
			doModificaUsuari(request, response);
		} else if (ACCIO_LLISTAT.equals(accio)) {
			doLlistatUsuari(request, response);
		
		}
	}
	
					 
					
	

	


	private void doAltaUsuari(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Intento obtenir la persona 
				Usuari usuari = extreureUsuari(request, response, false);
				
				// Si no l'he obtingu no faig res
				// (extreureusuari ja haurà redirigit on calgui)
				if (usuari == null) {
					return;
				}
				
				// Si tot ha anat bé
				// Donem d'alta la persona a través del DAO
				try {
					daoUsuari.alta(usuari);
				} catch (SQLException e) {
					EWokController.addMessage(ETipusMissatge.error,
							REQ_ERRORALTA, request);
				}
				
				// Redirigim cap al llistat de persones
				doLlistatUsuari(request, response);
			}
		
	


	private void doPreparaModificaUsuari(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// Obtenim l'email
	
	String mail = obtenirPerEmail(request, response, "modificar");
	//String pw = getPw(request, response, "modificar");
	
	
	
	// Si no hem obtingut l'email, hem acabat, perque el obtenirPerEmail
	// ja haura redirigit cap al llistat
	if (mail == null) {
		return;
	}
	// Obtenim el producte a traves del DAO
	Usuari u = null;
	try {
		u = daoUsuari.obtenirPerEmail(mail);
		
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORGENERIC, request);
	}
	if (u == null) {
		// Si no hem trobat l'usuari, redirigim al llistat
		// posant l'error adequat
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORGENERIC + mail, request);
		doLlistatUsuari(request, response);
		return;
	}
	
	// Posem el usuari al contexte adequat
	request.setAttribute(MODEL_USUARI, u);
	
	// Redirigim al formulari
	EWokController.forward("staff/gUsuari/formUsuari.jsp", request, response);	
	
	
	}
	
	
//	private String getPw(
//			HttpServletRequest request,
//			HttpServletResponse response, String accioMissatge) throws ServletException, IOException {
//		
//		// Obtenim ep password
//		String pw = null;
//		try {
//			pw = RequestValidationUtils.getMandatoryString(
//					PARAM_PW,  
//					request);
//		} catch (ParameterException e) {
//			EWokController.addMessage(ETipusMissatge.error, "Cal especificar un password per " + ACCIO_MISSATGE + " un usuari", request);
//		}
//		if (pw == null || pw.trim().equals("")) {
//			// Si ha hagut algun error, vaig al llistat de persones
//			doLlistatUsuari(request, response);
//			return null;
//		}
//		// Si tot ha anat bé, retorno l'id
//		return pw;
//	}



	private void doLlistatUsuari(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Obtenim les dades del DAO
				List<Usuari> usuarisLlistat = null;
				try {
					usuarisLlistat = daoUsuari.obtenirTots();
				} catch (SQLException e) {
					EWokController.addMessage(ETipusMissatge.error,
							REQ_ERRORLLISTAT, request);
				}
				
				// Fiquem les dades al request
				request.setAttribute(MODEL_USUARI, usuarisLlistat);
				
				// Redirigim cap al llistatusuaris.jsp
				EWokController.forward("staff/gUsuari/llistatUsuari.jsp", request, response);
					
			}
	


	private void doModificaUsuari(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	// Intento obtenir l objecte usuari del request
	Usuari usuari = extreureUsuari(request, response, true);
	
	// Si no l'he obtingut no faig res
	// (extreureUsuari ja haurÃ  redirigit on calgui)
	if (usuari == null) {
		return;
	}
	
	// Si tot ha anat bÃ©
	// Donem d'alta el usuari a travÃ©s del DAO
	try {
		daoUsuari.modificar(usuari);
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORMODIFICACIO, request);
		
		}
	
	// Redirigim cap a la llista
		EWokController.addMessage(ETipusMissatge.info,
				"L'usuari amb Email " + usuari.getMail() + "i password " + usuari.getPassword() + " ha estat modificat correctament", request);
		doLlistatUsuari(request, response);
		}
		
	
	
	
	private Usuari extreureUsuari(
			HttpServletRequest request,
			HttpServletResponse response,
			boolean extreureEmail
			) throws ServletException, IOException {
		
		
		
		String mail = null;
		try {
			mail = RequestValidationUtils.getMandatoryString(
					PARAM_EMAIL, 
					request);
			} catch (ParameterException em) {
			request.setAttribute(PARAM_ERROR_EMAIL,true);
	
			}
		
		String pw = null;
		try {
			pw = RequestValidationUtils.getMandatoryString(
					PARAM_PW, 
					request);
			} catch (ParameterException pasw) {
			request.setAttribute(PARAM_ERROR_PW,true);
	
		}
			
	
	// Creem un usuari amb els valors del formulari
		Usuari usuari = new Usuari();
		
		
		if (mail != null)
			usuari.setMail(mail);
		
		if (pw != null)
			usuari.setPassword(pw);
		
		if (extreureEmail == true)
			usuari.setActiu(extreureEmail);
		
		
		// Si hi ha algun error de validació, retornem al formulari
		if (extreureEmail && mail == null) {
			if (extreureEmail) {
				// Si hi ha algun error i era una modificació
				// (extreureEmail == true), llavors cal posar un usuari
//				// com a flag
				request.setAttribute(MODEL_USUARI, usuari);
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORMODIFICACIO, request);
				
			}
			
			// Si hi ha errors i era una alta, enviem MODEL_USUARI_ALTA i
			request.setAttribute(MODEL_USUARI, usuari);
			
			
		
		// redirigim cap al formulari
		EWokController.forward("staff/gUsuari/formUsuari.jsp", request, response);
		
		return null;
			}
	
	// Retornem l'objecte amb les dades actualitzades
		return usuari;

}
	

		
		
	private void doPreparaAltaUsuari(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// No cal fer res amb el model, 
	// simplement redirigim cap al JSP formulari
	EWokController.forward("staff/gUsuari/formUsuari.jsp", request, response);
	}

	
	
	
	
	private void doEsborrarUsuari(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		// Obtenim l'email que ens passen
		
		String sEmail = obtenirPerEmail(request, response, "esborrar");
		
		if (sEmail == null ) {
			// Si no tinc Email, no he de fer res
			return;
		}
		
		
		// Esborrem el Usuari
		
			try {
				daoUsuari.esborrar(sEmail) ;
			} catch (SQLException e) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORSQL,
						request);
			}
			
			// Hagi anat bé o malament, pintem el llistat de persones
			doLlistatUsuari(request, response);
			}
	
	
	
	



	private void doVeureUsuari(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Obtenim l'email que ens passen
		
		String mail = obtenirPerEmail(request, response, "veure");
		if (mail == null){
			// Redirigim cap a la pàgina de llistat
			doLlistatUsuari(request, response);
			return;
		}
		
		// no és null: intentem recuperar-lo de la bd
		Usuari u = null;
		try {
			u = daoUsuari.obtenirPerEmail(mail);
		} catch (SQLException e) {
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORSQL, request);
		}
		
		if (u == null) {
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORSQL, request);
			
			doLlistatUsuari(request, response);
			return;
		}
		
		// Fiquem les dades al request
		request.setAttribute(MODEL_USUARI, u);
		
		// Redirigim cap a la JSP
		
			EWokController.forward("staff/gUsuari/veureUsuari.jsp", request, response);
	}



	private String obtenirPerEmail(
			HttpServletRequest request,
			HttpServletResponse response, String accioMissatge) throws ServletException, IOException {
		
		// Obtenim l'email d
				String mail = null;
				try {
					mail = RequestValidationUtils.getMandatoryString(
							PARAM_EMAIL,  
							request);
				} catch (ParameterException e) {
					EWokController.addMessage(ETipusMissatge.error, "Cal especificar un email per " + ACCIO_MISSATGE + " una persona i L'Email especificat no és correcte", request);
				}
				if (mail == null || mail.trim().equals("")) {
					// Si ha hagut algun error, vaig al llistat de persones
					doLlistatUsuari(request, response);
					return null;
				}
				// Si tot ha anat bé, retorno l'id
				return mail;
		
	}

	

}
					
