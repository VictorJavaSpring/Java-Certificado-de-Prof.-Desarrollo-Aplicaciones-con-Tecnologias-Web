package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.model.Components;
import com.soc.ewok.model.Preu;
import com.soc.ewok.model.Producte;
import com.soc.ewok.model.Rol;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GProducteController extends SeccioController {


	// Constants
	private static final String ACCIO_PRODUCTE = "accioProductes";

	private static final String MODEL_PRODUCTE = "productes";
	private static final String MODEL_PRODUCTE_ALTA = "productesAlta";

	private static final String PARAM_ID = "id";
	private static final String PARAM_NOM = "nom";
	private static final String PARAM_DESCRIPCIOCURTA = "descripcioCurta";
	private static final String PARAM_DESCRIPCIO = "descripcio";
	private static final String PARAM_IDUNITAT = "idUnitat";
	private static final String PARAM_IDTIPUSPRODUCTE = "idTipusProducte";
	private static final String PARAM_INICIVIGENCIA_DATA = "inVig";
	private static final String PARAM_INICIVIGENCIA_HORA = "inVigTime";
	private static final String PARAM_FIVIGENCIA_DATA = "fiVig";
	private static final String PARAM_FIVIGENCIA_HORA = "fiVigTime";

	private static final String PARAM_ERROR_NOM = "errorNomJAVA";
	private static final String PARAM_ERROR_INICIVIGENCIA_DATA = "errorIniciVigenciaJAVA";
	private static final String PARAM_ERROR_FIVIGENCIA_DATA = "errorFiVigenciaJAVA";
	private static final String PARAM_ERROR_INICIVIGENCIA_HORA_SENSE_DATA = "errorIniciVigenciaSenseDataJAVA";
	private static final String PARAM_ERROR_FIVIGENCIA_HORA_SENSE_DATA = "errorFiVigenciaSenseDataJAVA";
	private static final String PARAM_ERROR_IDUNITAT = "errorIdUnitatJAVA";
	private static final String PARAM_ERROR_IDTIPUSPRODUCTE = "errorIdTipusProducteJAVA";

	private static final String PARAM_ERRORGENERAL_ID = "producte001.errorId";
	private static final String PARAM_ERRORGENERAL_PRODUCTE = "producte002.errorProducte";
	private static final String PARAM_ERRORGENERAL_ESBORRAR = "producte003.errorProducteEsborrar";
	private static final String PARAM_INFOGENERAL_ESBORRAR = "producte004.infoEsborrar";
	private static final String PARAM_ERRORGENERAL_SQLALTA = "producte005.errorSqlAlta";
	private static final String PARAM_INFOGENERAL_ALTA = "producte006.infoAlta";
	private static final String PARAM_ERRORGENERAL_SQL = "producte007.errorSql";
	private static final String PARAM_INFOGENERAL_MODIF = "producte008.infoModif";
	private static final String PARAM_INFOGENERAL_LLISTA = "producte009.infoLlista";
	private static final String PARAM_ERRORGENERAL_MODIF = "producte010.errorModif";

	// CONSTANTS per modificacio producte compost
	private static final String ACCIO_COMPOST = "compost";
	private static final String MODEL_COMPOST = "prodcompost";
	private static final String ACCIO_GRAVARCOMPOST = "gravacompost";
	private static final String PARAM_GRAVARCOMPOST = "inputObjectePC";
	private static final String PARAM_GRAVARPREU = "inputObjectePreu";

	private static final Object PARSE_PREU = "preu";
	private static final String PARSE_IDCOMPONENT = "id";
	private static final String PARSE_IDPRODUCTE = "idProducte";
	private static final String PARSE_FI_VIGENCIA = "finalVigencia";
	private static final String PARSE_INI_VIGENCIA = "iniciVigencia";
	private static final String PARSE_ORDRE = "ordre";
	private static final String PARSE_QUANTITAT = "quantitat";

	// Variable membre
	ProducteDAO daoProducte = null;

	// Constructor
	public GProducteController() {
		// ewok.
		daoProducte = new ProducteDAO(EWokController.getGlobalDatasource());
		// Afegir els rols que poden usar aquest controller
		addRols2Controller(Rol.ROL_CODI_ADMINISTRADOR);
		addRols2Controller(Rol.ROL_CODI_CUINER);
		addRols2Controller(Rol.ROL_CODI_CAIXER);
	}

	@Override
	public String getNomAccio() {
		return ACCIO_PRODUCTE;
	}

	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// MÃ¨tode principal per servir totes les peticions
		// relatives a Comandes
		if (accio.equals(ACCIO_VEURE)) {
			doVeureProducte(request, response);
		} else if (accio.equals(ACCIO_ESBORRAR)) {
			doEsborrarProducte(request, response);
		} else if (accio.equals(ACCIO_PREPARARALTA)) {
			doPreparaAltaProducte(request, response);
		} else if (accio.equals(ACCIO_ALTA)) {
			doAltaProducte(request, response);
		} else if (accio.equals(ACCIO_PREPARARMODIFICACIO)) {
			doPreparaModificaProducte(request, response);
		} else if (accio.equals(ACCIO_MODIFICACIO)) {
			doModificaProducte(request, response);
		} else if (accio.equals(ACCIO_LLISTAT)) {
			doLlistatProducte(request, response);
		} else if (accio.equals(ACCIO_COMPOST)) {
			doModificaProducteCompost(request, response);
		} else if (accio.equals(ACCIO_GRAVARCOMPOST)) {
			doGravaProducteCompost(request, response);
		}
	}

	/**
	 * métode per Gravar a base de dades les dades forànees d'un producte compost
	 * Un producte compost es el que esta compost per altres productes
	 * @author JordiM
	 */
	private void doGravaProducteCompost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * obtenir dades i actualitzar entitat: Producte, no s'ha de gravar res
		 * de l'entitat producte. Components, esborrar dades de relacio actual i
		 * inserir les noves. Preu, inserir un nou preu
		 * */

		String sPC = request.getParameter(PARAM_GRAVARCOMPOST);
		String sPreu = request.getParameter(PARAM_GRAVARPREU);

//		JSONObject objPC = null;
//		try {
//			objPC = new JSONObject(sPC);
//		} catch (JSONException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		JSONObject objPreu= null;
//		try {
//			objPreu = new JSONObject(sPreu);
//		} catch (JSONException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		
		// Parsejar Strings rebuts per obtenir les dades per fer alta

		// poblar objecte preu
		Preu objPr = new Preu();
		parsePreuFromJSONStringified(sPreu, objPr);

		// parsejar dades d'objecte compost. necessitem obtenir una llista de
		// Components
		List<Components> lc = new Vector<Components>();
		parseComponentsFromJSONStringified(objPr.getIdProducte(), sPC, lc);

		try {
			daoProducte.modificaProducteCompost(objPr, lc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//torno a staff home TODO canviar pàgina de retorn quan se sapiga
		//des don s'accedeix a la modificacio de producte compost
		EWokController.forward("staff/home.jsp", request, response);
		return;

	}

	/**
	 * utilitat per obtenir una llista de components a partir de un string JSON
	 * 
	 * @param idProducte Identificador del producte compost 
	 * @param sProdCompost
	 *            String provinent de JSON amb totes les dades d'un Producte
	 *            Compost
	 * @param listCompo
	 *            llista de objectes Components que sera poblada
	 * @author JordiM
	 */
	private void parseComponentsFromJSONStringified(Long idProducte,
			String sProdCompost, List<Components> listCompo) {

		String compn = "";
		int i, j;
		// obtinc substrinc de components
		i = sProdCompost.indexOf("[") + 1;
		j = sProdCompost.indexOf("]");
		compn = sProdCompost.substring(i, j);
		// netejo caracters sobrants
		compn = compn.replace("},", "}");
		compn = compn.replace("{", "");
		compn = compn.replace("\"", "");
		// split per obtenir array amb els components separats
		String[] compnArr = compn.split("}");

		// a cada element de l'array hi ha un component en format string amb
		// tots els parells camp:valor,camp:valor..
		for (String sComponent : compnArr) { // bucle 1 component
			String[] componentAtrArr = sComponent.split(","); // split per
																// obtenir un
																// array amb els
																// parells
																// camp:valor de
																// aquesta
																// iteració
			Components c = new Components();// creem un nou component a
			// cada iteració per
			// afegir-lo a la llista
			// paràmetre. l'omplirem amb
			// les dades de l'string
			c.setIdProducte(idProducte);

			for (String s : componentAtrArr) { // bucle 2 atributs del comp.
				String[] parN_V = s.split(":"); // obtinc els parells camp:valor
												// d'aquest string
				if (parN_V[0].equals(PARSE_IDCOMPONENT)) {
					c.setIdComponent(Long.parseLong(parN_V[1]));
				} else if (parN_V[0].equals(PARSE_QUANTITAT)) {
					c.setQuantitat(Integer.parseInt(parN_V[1]));
				} else if (parN_V[0].equals(PARSE_ORDRE)) {
					c.setOrdre(Integer.parseInt(parN_V[1]));
				}
			}
			if (c.getIdComponent() != null) {
				listCompo.add(c);
			}
		}
	}

	/**
	 * utilitat per obtenir una entitat preu a partir de un string JSON
	 * @param preu
	 *            String JSON amb els valors a parsejar
	 * @param pr
	 *            Objecte Preu poblat amb les dades
	 * @author JordiM
	 */
	private void parsePreuFromJSONStringified(String preu, Preu pr) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

		preu = preu.replace("{", "");
		preu = preu.replace("}", "");
		preu = preu.replace("\"", "");
		String[] preuArr = preu.split(",");

		for (String s : preuArr) {
			String[] parN_V = s.split(":");
			if (parN_V[0].equals(PARSE_PREU)) {
				pr.setPreu(Float.parseFloat(parN_V[1]));
			} else if (parN_V[0].equals(PARSE_INI_VIGENCIA)) {
				try {
					pr.setIniciVigencia(sd.parse(parN_V[1]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (parN_V[0].equals(PARSE_FI_VIGENCIA)) {
				try {
					pr.setFinalVigencia(sd.parse(parN_V[1]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (parN_V[0].equals(PARSE_IDPRODUCTE)) {
				pr.setIdProducte(Long.parseLong(parN_V[1]));
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @author JordiM
	 */
	private void doModificaProducteCompost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// obtenir l'identificador d'objecte i carregar les dades
		// al p�gina de presentaci�.

		// provem d'obtenir un id del request
		Long nId = getidFromRequest(request, response);
		if (nId == null) {
			// Redirigim cap a la pagina de llistat
			doLlistatProducte(request, response);
			return;
		}
		// no es null: intentem recuperar-lo de la bd
		Producte p = null;
		try {
			p = daoProducte.obtenirProducteCompostPerId(nId);
			if (p == null) {
				//
				doLlistatProducte(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// l'inserim al request
		request.setAttribute(MODEL_COMPOST, p);

		// Redirigim cap a la p�gina corresponent
		EWokController.forward("staff/gProducte/modificaProducteCompost.jsp",
				request, response);

	}

	private void doVeureProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtenim l'id que ens passen
		Long nId = null;
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_ID, new String[] { nId.toString() },
					request);
			doLlistatProducte(request, response);
			return;
		}
		if (nId == null) {
			// Si no tinc Id, no he de fer res
			return;
		}
		// Obtenim les dades del DAO
		Producte p = null;
		try {
			p = daoProducte.obtenirPerId(nId);
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_ID, new String[] { nId.toString() },
					request);
			doLlistatProducte(request, response);
			return;
		}
		if (p == null) {
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_PRODUCTE,
					new String[] { nId.toString() }, request);
			doLlistatProducte(request, response);
			return;
		}
		// Fiquem les dades al request
		request.setAttribute(MODEL_PRODUCTE, p);
		// Redirigim cap a la JSP
		EWokController.forward("staff/gProducte/veureProducte.jsp", request,
				response);
	}

	private void doEsborrarProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtenim l'id que ens passen
		Long nId = null;
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_ESBORRAR,
					new String[] { nId.toString() }, request);
			doLlistatProducte(request, response);
			return;
		}
		// Esborrem el Producte
		try {
			daoProducte.esborrar(nId);
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_ESBORRAR,
					new String[] { nId.toString() }, request);
			doLlistatProducte(request, response);
			return;
		}
		EWokController.addI18nMessage(ETipusMissatge.info, null,
				PARAM_INFOGENERAL_ESBORRAR, new String[] { nId.toString() },
				request);
		doLlistatProducte(request, response);
		return;
	}

	private void doPreparaAltaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// No cal fer res amb el model,
		// simplement redirigim cap al JSP formulari
		EWokController.forward("staff/gProducte/formProducte.jsp", request,
				response);
	}

	private void doAltaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Intento obtenir el producte
		Producte producte = extreureProducte(request, response);
		// Si no l'he obtingut no faig res
		// (extreure Producte ja haurÃ  redirigit on calgui)
		if (producte == null) {
			return;
		}
		// Si tot ha anat bÃ©
		// Donem d'alta el producte a travÃ©s del DAO
		try {
			daoProducte.alta(producte);
		} catch (SQLException e) {
			// Si hi ha error al donar d'alta, printem error
			EWokController.addI18nMessage(ETipusMissatge.error,
					PARAM_ERRORGENERAL_SQLALTA, request);
			// Redirigim cap al llistat de productes
			doLlistatProducte(request, response);
			return;
		}
		EWokController.addI18nMessage(ETipusMissatge.info,
				PARAM_INFOGENERAL_ALTA, request);
		// Redirigim cap al llistat de productes
		doLlistatProducte(request, response);
	}

	private void doPreparaModificaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtenim l'id
		Long nId = null;
		try {
			nId = getId(request, response);
		} catch (ParameterException e1) {
			// JAVA
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_ID, new String[] { nId.toString() },
					request);
		}

		// Si no hem obtingut id, hem acabat, perque el getId
		// ja haura redirigit cap al llistat
		if (nId == null) {
			return;
		}
		// Obtenim el producte a travï¿½s del DAO
		Producte p = null;
		try {
			p = daoProducte.obtenirPerId(nId);
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_ID, new String[] { nId.toString() },
					request);
			doLlistatProducte(request, response);
			return;
		}
		if (p == null) {
			// Si no hem trobat el producte, redirigim al llistat
			// posant l'error adequat
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_PRODUCTE,
					new String[] { nId.toString() }, request);
			doLlistatProducte(request, response);
			return;
		}
		// Posem el producte al contexte adequat
		request.setAttribute(MODEL_PRODUCTE, p);
		// Redirigim al formulari
		EWokController.forward("staff/gProducte/formProducte.jsp", request,
				response);
	}

	private void doModificaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Intento obtenir l objecte producte del request
		Producte producte = extreureProducte(request, response, true);
		// Si no l'he obtingut no faig res
		// (extreureProducte ja haurÃ  redirigit on calgui)
		if (producte == null) {
			return;
		}
		// Si tot ha anat bÃ©
		// Donem d'alta el producte a travÃ©s del DAO
		try {
			daoProducte.modificar(producte);
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.error, null,
					PARAM_ERRORGENERAL_SQL, new String[] { producte.getId()
							.toString() }, request);
			doLlistatProducte(request, response);
		}
		EWokController.addI18nMessage(ETipusMissatge.info, null,
				PARAM_INFOGENERAL_MODIF, new String[] { producte.getId()
						.toString() }, request);
		// Redirigim cap al llistat de productes
		doLlistatProducte(request, response);
	}

	private void doLlistatProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Producte> productes = null;
		// Obtenim les dades del DAO
		try {
			productes = daoProducte.obtenirTots();
		} catch (SQLException e) {
			EWokController.addI18nMessage(ETipusMissatge.info,
					PARAM_INFOGENERAL_LLISTA, request);
		}
		// Fiquem les dades al request
		request.setAttribute(MODEL_PRODUCTE, productes);
		// Redirigim cap al llistatProducte.jsp
		EWokController.forward("staff/gProducte/llistatProducte.jsp", request,
				response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private Producte extreureProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return extreureProducte(request, response, false);
	}

	/**
	 * FunciO d'utilitat per omplir un Producte amb les dades del request
	 * 
	 * @param request
	 * @param response
	 * @param extreureId
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private Producte extreureProducte(HttpServletRequest request,
			HttpServletResponse response, boolean extreureId)
			throws ServletException, IOException {
		// Obtenim els valors del formulari
		Long nId = null;
		if (extreureId) {
			nId = getidFromRequest(request, null);
		}
		// ////////////////////// Nom NOTNULL //////////////////////
		String sNom = null;
		try {
			sNom = RequestValidationUtils
					.getMandatoryString(PARAM_NOM, request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERROR_NOM, true);
		}
		// ////////////////////// DescripcioCurta //////////////////////
		String sDescripcioCurta = RequestValidationUtils.getNonMandatoryString(
				PARAM_DESCRIPCIOCURTA, request);
		// ////////////////////// Descripcio //////////////////////
		String sDescripcio = RequestValidationUtils.getNonMandatoryString(
				PARAM_DESCRIPCIO, request);
		// ////////////////////// IniciVigencia DATE //////////////////////
		// si ens informen de la hora ens han d'informar del dia
		Date dIniciVigencia = null;
		try {
			dIniciVigencia = RequestValidationUtils
					.obtenirDateTime(PARAM_INICIVIGENCIA_DATA,
							PARAM_INICIVIGENCIA_HORA, request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERROR_INICIVIGENCIA_DATA, true);
		}
		// ////////////////////// FiVigencia DATE //////////////////////
		Date dFiVigencia = null;
		try {
			dFiVigencia = RequestValidationUtils.obtenirDateTime(
					PARAM_FIVIGENCIA_DATA, PARAM_FIVIGENCIA_HORA, request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERROR_FIVIGENCIA_DATA, true);
		}
		// ////////////////////// IdUnitat NOTNULL //////////////////////
		Long nIdUnitat = null;
		try {
			nIdUnitat = RequestValidationUtils.getMandatoryLong(PARAM_IDUNITAT,
					request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERROR_IDUNITAT, true);
		}
		// ////////////////////// IdTipusProducte NOTNULL //////////////////////
		Long nIdTipusProducte = null;
		try {
			nIdTipusProducte = RequestValidationUtils.getMandatoryLong(
					PARAM_IDTIPUSPRODUCTE, request);
		} catch (ParameterException e) {
			request.setAttribute(PARAM_ERROR_IDTIPUSPRODUCTE, true);
		}
		// Creem un producte amb els valors del formulari
		Producte producte = new Producte();
		if (nId != null) {
			producte.setId(nId);
		}
		if (sNom != null) {
			producte.setNom(sNom);
		}
		producte.setDescripcioCurta(sDescripcioCurta);
		producte.setDescripcio(sDescripcio);
		producte.setIniciVigencia(dIniciVigencia);
		producte.setFiVigencia(dFiVigencia);
		if (nIdUnitat != null) {
			producte.setIdUnitat(nIdUnitat);
		}
		if (nIdTipusProducte != null) {
			producte.setIdTipusProducte(nIdTipusProducte);
		}
		// VALIDACIONS
		// Les dades i hores d'inici i de fi vigencia no sï¿½n obligatories
		// perï¿½
		// si ens informen de la hora ens han d'informar tambï¿½ del dia
		// Agafem la data d'inici vigencia del formulari per fer les validacions
		Date dDataInici = null;
		try {
			dDataInici = RequestValidationUtils.getNonMandatoryDate(
					PARAM_INICIVIGENCIA_DATA, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_INICIVIGENCIA_DATA, true);
		}
		// Agafem l'hora d'inici vigencia del formulari per fer les validacions
		Date dHoraInici = null;
		try {
			dHoraInici = RequestValidationUtils.getNonMandatoryTime(
					PARAM_INICIVIGENCIA_HORA, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_INICIVIGENCIA_DATA, true);
		}
		// Si ens informen de la hora perï¿½ no de la data, pintem un error
		if (dDataInici == null && dHoraInici != null) {
			request.setAttribute(PARAM_ERROR_INICIVIGENCIA_HORA_SENSE_DATA,
					true);
		}
		// Si ens informen de la data perï¿½ no de la hora, simplement posarem
		// 00:00 com horari per defecte
		if (dDataInici != null && dHoraInici == null) {
			// Set dHoraInici a 00:00:00
		}
		// Fem el mateix amb la data i la hora final de vigencia
		// Agafem la data final de vigencia del formulari per fer les
		// validacions
		Date dDataFinal = null;
		try {
			dDataFinal = RequestValidationUtils.getNonMandatoryDate(
					PARAM_FIVIGENCIA_DATA, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_FIVIGENCIA_DATA, true);
		}
		// Agafem l'hora final de vigencia del formulari per fer les validacions
		Date dHoraFinal = null;
		try {
			dHoraFinal = RequestValidationUtils.getNonMandatoryTime(
					PARAM_FIVIGENCIA_HORA, request);
		} catch (ParameterException e1) {
			request.setAttribute(PARAM_ERROR_FIVIGENCIA_DATA, true);
		}
		// Si ens informen de la hora perï¿½ no de la data, pintem un error
		if (dDataFinal == null && dHoraFinal != null) {
			request.setAttribute(PARAM_ERROR_FIVIGENCIA_HORA_SENSE_DATA, true);
		}
		// Si ens informen de la data perï¿½ no de la hora, simplement posarem
		// 00:00 com horari per defecte
		if (dDataFinal != null && dHoraFinal == null) {
			// Set dHoraFinal a 00:00:00
		}
		// Si hi ha algun error de validaciï¿½, retornem al formulari
		if ((extreureId && nId == null) || sNom == null || nIdUnitat == null
				|| nIdTipusProducte == null
				|| (dDataInici == null && dHoraInici != null)
				|| (dDataFinal == null && dHoraFinal != null)) {
			// Si hi ha algun error i era una modificaciÃ³
			// (extreuId == true), necessitem posar un producte com a flag
			if (extreureId) {
				EWokController.addI18nMessage(ETipusMissatge.error, null,
						PARAM_ERRORGENERAL_MODIF, new String[] { producte
								.getId().toString() }, request);
			}
			// Si hi ha errors i era una alta, enviem MODEL_PRODUCTE_ALTA i
			// redirigim cap al formulari
			request.setAttribute(MODEL_PRODUCTE_ALTA, producte);
			EWokController.forward("staff/gProducte/formProducte.jsp", request,
					response);
			return null;
		}
		return producte;
	}

	private Long getId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParameterException {
		// Obtenim l'id de la url
		Long nId = RequestValidationUtils.getMandatoryLong(PARAM_ID, request);
		// comprobem que no es null
		if (nId == null) {
			// Si ha hagut algun error, vaig al llistat de persones
			doLlistatProducte(request, response);
			return null;
		}
		// Si tot ha anat bÃ©, retorno l'id
		return nId;
	}
}
