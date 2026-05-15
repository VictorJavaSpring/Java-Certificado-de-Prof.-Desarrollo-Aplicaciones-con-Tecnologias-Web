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
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.dao.TipusProducteDAO;
import com.soc.ewok.model.Producte;
import com.soc.ewok.model.TipusProducte;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

public class GTipusProducteController implements ISectionController {


public class GProducteController extends SeccioController {
	
	private static final String ACCIO_GTIPUSPRODUCTE = "accioGTipusProducte";
	private static final String ACCIO_VEURE = "veure";
	private static final String ACCIO_ESBORRAR = "esborrar";
	private static final String ACCIO_PREPARARALTA = "prepararAlta";
	private static final String ACCIO_ALTA = "alta";
	private static final String ACCIO_PREPARARMODIFICACIO = "prepararModificacio";
	private static final String ACCIO_MODIFICACIO = "modificacio";
	private static final String ACCIO_LLISTAT = "llistat";
	
	private static final String MODEL_GTIPUSPRODUCTE = "productes";
	
	private static final String REQ_ERRORGENERIC = "No s'ha trobat el producte amb id ";
	private static final String REQ_ERRORLLISTAT ="No s'ha pogut obtenir la llista de productes";
	private static final String REQ_ERRORPARAM ="Error en el paràmetre";
	private static final String REQ_ERRORMODIFICACIO = "No s'ha pogut modificar la informació";
	private static final String REQ_ERRORALTA = "No s'ha pogut donar d'alta el producte.";
	private static final String REQ_ERRORSQL = "No s'ha pogut esborrar el producte.Possiblement ha estat esborrat amb anterioritat.";

	private static final String PARAM_ID = "id";
	private static final String PARAM_NOM = "nom";
	private static final String PARAM_DESCRIPCIO = "descripcio";
	private static final String PARAM_CODI = "codi";

	
	// ewok.
	TipusProducteDAO daoTipusProducte = new TipusProducteDAO(EWokController.getGlobalDatasource());

	@Override
	public String getNomAccio() {
		return ACCIO_GTIPUSPRODUCTE;
	}

	@Override
	public void doGet(
			String accio, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		request.setAttribute("msg",
				"He passat pel controlador de gestió tipus de productes");
		
		
		/**
		 * Mètode principal per servir totes les peticions
		 * relatives a Comandes
		 */
		if (accio.equals(ACCIO_VEURE)) {
			doVeureTipusProducte(request, response);
		}
		else if (accio.equals(ACCIO_ESBORRAR)) {
			doEsborrarTipusProducte(request, response);
		}
		else if (accio.equals(ACCIO_PREPARARALTA)) {
			doPreparaAltaTipusProducte(request, response);
		}
		else if (accio.equals(ACCIO_ALTA)) {
			doAltaTipusProducte(request, response);
		}
		else if (accio.equals(ACCIO_PREPARARMODIFICACIO)) {
			doPreparaModificaProducte(request, response);
		}
		else if (accio.equals(ACCIO_MODIFICACIO)) {
			doModificaTipusProducte(request, response);
		}
		else if (accio.equals(ACCIO_LLISTAT)) {
			doLlistatTipusProducte(request, response);
		}
	}
	

private void doPreparaModificaProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	/**
	 *  Obtenim l'id
	 */
	Long nId = getId(request, response, "modificar");
	/**
	 *  Si no hem obtingut id, hem acabat, perquè el getId,
	 * ja haurà redirigit cap al llistat
	 */
	if (nId == null) {
		return;
	}
	/**
	 * Obtenim el producte a través del DAO
	 */
	TipusProducte TipusProd = null;
	try {
		TipusProd = daoTipusProducte.obtenirPerId(nId);
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORGENERIC, request);
	}
	if (TipusProd == null) {
		/**
		 * Si no hem trobat el producte, redirigim al llistat
		 * posant l'error adequat
		 */
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORGENERIC + nId, request);
		doLlistatTipusProducte(request, response);
		return;
	}
	/**
	 *  Posem el producte al contexte adequat
	 */
	request.setAttribute(MODEL_GTIPUSPRODUCTE, TipusProd);
	/**
	 * Redirigim al formulari
	 */
	EWokController.forward("staff/gTipusProducte/gTipusProducte.jsp", request, response);	
	}

private void doLlistatTipusProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	List<TipusProducte> TipusProducte = null;
	/**
	 * Obtenim les dades del DAO
	 */
	try {
		TipusProducte = daoTipusProducte.obtenirTots();
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORLLISTAT, request);
	}
	
	/**
	 * Posem les dades al request
	 */
	request.setAttribute(MODEL_GTIPUSPRODUCTE, TipusProducte);
	
	/**
	 *  Redirigim cap al llistatProducte.jsp
	 */
	EWokController.forward("staff/gTipusProducte/llistatGTipusProducte.jsp", request, response);
	}

private void doModificaTipusProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	/**
	 *  Intento obtenir el producte
	 */
	TipusProducte TipusProd = null;
	try {
		TipusProd = extreureGTipusProducte(request, response, true);
	} catch (ParameterException e1) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORPARAM, request);
	}
	
	/**
	 * Si no l'he obtingut no faig res
	 * (extreureProducte ja haurà redirigit on calgui)
	 */
	if (TipusProd == null) {
		return;
	}
	
	/** Si tot ha anat bé
	 * Donem d'alta el producte a través del DAO
	 */
	try {
		daoTipusProducte.modificar(TipusProd);
	} catch (SQLException e) {
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORMODIFICACIO, request);
	}
	
	/**
	 *  Redirigim cap al llistat de tipus productes
	 */
	doLlistatTipusProducte(request, response);
	}


private void doAltaTipusProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	/**
	 *  Intentem obtenir el producte
	 */
	TipusProducte TipusProd = null;
			try {
				TipusProd = extreureGTipusProducte(request, response, false);
			} catch (ParameterException e1) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORPARAM, request);
			}
			/**
			 *  Si no l'he obtingut no faig res
			 *  (extreure Producte ja haurà redirigit on calgui)
			 */
			if (TipusProd == null) {
				return;
			}
			
			// Si tot ha anat bé
			// Donem d'alta el producte a través del DAO
			try {
				daoTipusProducte.alta(TipusProd);
			} catch (SQLException e) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORALTA, request);
				}
			
			/**
			 * Redirigim cap al llistat de productes
			 */
			doLlistatTipusProducte(request, response);
		
	}

private TipusProducte extreureGTipusProducte(
		HttpServletRequest request,
		HttpServletResponse response,
		boolean extreureId) throws ParameterException {
	
	/**
	 * Validem i obtenim els valors del formulari
	 * Id
	 */
	Long nId = null;
	if (extreureId) {
		nId = RequestValidationUtils.getMandatoryLong(
				PARAM_ID, 
				request);
		}
	/**
	 *  Nom
	 */
	String sNom = RequestValidationUtils.getMandatoryString(
			PARAM_NOM, 
			request);
	
	/**
	 *  Descripcio
	 */
	String sDescripcio = RequestValidationUtils.getNonMandatoryString(
			PARAM_DESCRIPCIO, 
			request);
	/**
	 * Codi
	 */
	String sCodi = RequestValidationUtils.getMandatoryString(
			PARAM_CODI, 
			request);
	
	/**
	 * Creem un tipus producte amb els valors del formulari
	 */
	TipusProducte tipusProd = new TipusProducte();
	tipusProd.setId(nId);
	tipusProd.setNom(sNom);
	tipusProd.setDescripcio(sDescripcio);
	tipusProd.setCodi(sCodi);
	
	if (			(extreureId && nId == null) || 
		sNom == null )
		 {
		// Si hi ha algun error i era una modificació
		// (extreuId == true), llavor necessitem posar un producte
		// com a flag
		if (extreureId) {
			request.setAttribute(MODEL_GTIPUSPRODUCTE, tipusProd);
		}
		try {
			request.
				getRequestDispatcher("staff/gTipusProducte/gTipusProducteFormulari.jsp").
				forward(request,  response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipusProd;
	}
	return tipusProd;
	}


private void doPreparaAltaTipusProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	/**
	 * No cal fer res amb el model, 
	 *  simplement redirigim cap al JSP formulari
	 */
	EWokController.forward("staff/gTipusProducte/gTipusProducteFormulari.jsp", request, response);
	}

private void doEsborrarTipusProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	/** 
	 * Obtenim l'id que ens passen
	 */
	Long nId = getId(request, response, "esborrar");
	if (nId == null) {
		/**
		 * Si no tinc Id, no he de fer res
		 */
		return;
	}
	/**
	 * Esborrem el Producte
	 */
	try {
		daoTipusProducte.esborrar(nId);
	} catch (SQLException e) {
		/**
		 * I si ha anat malament, pintarem un error
		 */
		EWokController.addMessage(ETipusMissatge.error,
				REQ_ERRORSQL,
				request);
		}
	/**
	 *  Hagi anat bé o malament, pintem el llistat de tipus productes
	 */
	doLlistatTipusProducte(request, response);
	}



private void doVeureTipusProducte(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	/** 
	 * Obtenim l'id que ens passen
	 */
			Long nId = getId(request, response, "veure");
			if (nId == null) {
				/**
				 * Si no tinc Id, no he de fer res
				 */
				return;
			}
			/**
			 * Obtenim les dades del DAO
			 */
			TipusProducte TipusProd = null;
			try {
				TipusProd = daoTipusProducte.obtenirPerId(nId);
			} catch (SQLException e) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORSQL, request);
			}
			if (TipusProd == null) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORSQL, request);
				doLlistatTipusProducte(request, response);
				return;
			}
			
			/**
			 *  Fiquem les dades al request
			 */
			request.setAttribute(MODEL_GTIPUSPRODUCTE, TipusProd);
			
			/**
			 *  Redirigim cap a la JSP
			 */
			EWokController.forward("staff/GTipusProducte/veureGTipusProducte.jsp", request, response);

	}

private Long getId(
		HttpServletRequest request,
		HttpServletResponse response,
		String accioMissatge) throws ServletException, IOException {
	/**
	 * Obtenim l'id de la url
	 */
			Long nId = null;
			try {
				nId = RequestValidationUtils.getMandatoryLong(
						PARAM_ID, 
						request);
			} catch (ParameterException e) {
				EWokController.addMessage(ETipusMissatge.error,
						REQ_ERRORPARAM, request);
			}
			/**
			 *  comprobem que no es null
			 */
			if (nId == null) {
				/**
				 *  Si ha hagut algun error, vaig al llistat de persones
				 */
				doLlistatTipusProducte(request, response);
				return null;
				}
			/**
			 *  Si tot ha anat bé, retorno l'id
			 */
			return nId;	}
}

@Override
public String getNomAccio() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<String> getRolsValids(String accio) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void doGet(String accio, HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
}
}
