package com.soc.ewok.controller.publiccontroller;

import java.io.IOException;
import java.sql.SQLException;

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

/**
 * Servlet implementation class ViewMenuServlet
 */
public class ViewMenuServlet extends PublicController {
	private static final long serialVersionUID = 1L;
  
	// Constants de model
	private static final String MODEL_PROD = "producte";
	
	// Constants informatives
	private static final String IDPROD = "idProd";
	private static final String PARAM_IDFOUND = "idF";
	private static final String PARAM_PRODFOUND = "prodF";
	private static final String PARAM_TIPUSPRODFOUND = "tipusProdF";
	// Constants de tipus de codi
	private static final String CODI_PLAT = "plt";
	private static final String CODI_ARTICLE = "art";
	private static final String CODI_MENU = "menu";
	// Constants d'error
	private static final String PARAM_ERROR_IDPROD = "idProd";
	private static final String PARAM_ERROR_PROD = "prod";
	private static final String PARAM_ERROR_PRODNOTFOUND = "prodNF";
	private static final String PARAM_ERROR_IDPRODNOTFOUND = "idProdNF";
	private static final String PARAM_ERROR_TIPUSPRODNOTFOUND = "tipusProdNF";
	private static final String PARAM_ERROR_TIPUSPROD = "error_tipusProd";

	
		
	// Variable membre (del tipus "ProducteDAO")
	ProducteDAO daoProducte = null;
	// Variable membre (del tipus "TipusProducteDAO")
	TipusProducteDAO daoTipusProducte = null;
	
	// Constructor
    public ViewMenuServlet() {
    	// El DAO creat (anteriorment) anirà contra BD ("ProducteDAO")
    	daoProducte = new ProducteDAO(EWokController.getGlobalDatasource());
    	// El DAO creat (anteriorment) anirà contra BD ("TipusProducteDAO")
    	daoTipusProducte = new TipusProducteDAO(EWokController.getGlobalDatasource());
    }
    
       
	/** Mètode doGet (autogenerat)
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String sCodi = getCodiTipusProd(request, response);
		// Si el codi "sCodi" és null no cal fer res doncs ja ho haurà fet
		// el m�tode "getCodiTipusProd"
		
		if (sCodi != null) {
			// Si tinc codi de tipus de producte, tamb� tinc un producte,
			// perqu� el codi de tipus de producte l'obtinc a partir del producte
			if (CODI_PLAT.equals(sCodi)) {
				// Deso el codi al request
				request.setAttribute(CODI_PLAT, sCodi);
				// Redirigeixo cap a la jsp
				EWokController.forward("public/viewMenuServlet/viewPlat.jsp", request, response);
			} else if (CODI_ARTICLE.equals(sCodi)) {
				// Deso el codi al request
				request.setAttribute(CODI_ARTICLE, sCodi);
				// Redirigeixo cap a la jsp
				EWokController.forward("public/viewMenuServlet/viewArticle.jsp", request, response);
			} else if (CODI_MENU.equals(sCodi)) {
				// Deso el codi al request
				request.setAttribute(CODI_MENU, sCodi);
				// Redirigeixo cap a la jsp
				EWokController.forward("public/viewMenuServlet/viewMenu.jsp", request, response);
			}
		}
		return;
	}
	
	private String getCodiTipusProd(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{

		// Obtinc l'id del tipus de producte
		Long nId = getIdTipusProd(request, response);
		
		if (nId == null) {
			// Si no tinc Id, no llen�o error perqu� ja ho fa el m�tode "getIdTipusProd"
			// a trav�s del m�tode "getIdProd"
			// Llavors redirigeixo cap a la jsp de men� i retorno "null"
			EWokController.forward("/viewMenuServlet/viewMenuServlet.jsp", request, response);
			return null;
		}
		// Obtinc les dades del DAO (TipusProducteDAO)
		TipusProducte tp = null;
		try {
			tp = daoTipusProducte.obtenirPerId(nId);
			EWokController.addI18nMessage(ETipusMissatge.info, PARAM_TIPUSPRODFOUND, request);
		} catch (SQLException e1) {
			// Si hi ha error, el capturo, llen�o l'excepci�
			// i retorno un missatge d'error i un "null"
			EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_TIPUSPRODNOTFOUND, 
										  new String[]{nId.toString()}, request);
			// Redirigeixo a la jsp de men�
			EWokController.forward("/viewMenuServlet/viewMenuServlet.jsp", request, response);
			return null;
		}
		if (tp == null) {
			// Si el tipus de producte �s null, retorno un missatge d'error i un "null"
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_TIPUSPROD, 
										  new String[]{nId.toString()}, request);
			// Redirigeixo a la jsp
			EWokController.forward("/viewMenuServlet/viewMenuServlet.jsp", request, response);
			return null;
		}
		// Intento obtenir el codi del tipus de producte
		// a partir de l'objecte TipusProducte "tp"
		String sCodi = tp.getCodi();
		if (sCodi == null) {
			// Redirigeixo a la jsp
			EWokController.forward("/viewMenuServlet/viewMenuServlet.jsp", request, response);
			return null;
		}
		// Retorno el codi del tipus de producte
		return sCodi;
	}
	
	
	private Long getIdTipusProd(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		// Intento obtenir el producte
		Producte prod = getProd(request, response);
		
		// Si no l'he obtingut, no retorno un missatge d'error
		// perqu� ja ho fa el m�tode "doGetProd"
		if (prod == null) {
			// Llavors redirigeixo a la jsp de men�
			//EWokController.forward("/viewMenuServlet/viewMenuServlet.jsp", request, response);
			return null;
		}
		// Intento obtenir l'id del tipus de producte
		// a partir de l'objecte Producte "p"
		Long nId = prod.getIdTipusProducte();
//		if (nId == null) {
//			// Si l'id del tipus de producte �s "null",
//			// retorno un missatge d'error 
//			EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_IDNOTFOUND, 
//										  new String[]{"null"}, request);
//			// Redirigeixo a la jsp de men�
//			EWokController.forward("/viewMenuServlet/viewMenuServlet.jsp", request, response);
//			return null;
//		}
		// Si tot ha anat b�, obtinc l'id del tipus de producte
		EWokController.addI18nMessage(ETipusMissatge.info, PARAM_IDFOUND, request);
		return nId;		
	}


	/**
	 * Mètode per visualitzar el tipus de producte
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private Producte getProd(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		// Obtinc l'id de producte
		Long nId = getIdProd(request, response);
		
		if (nId == null) {
			// Si no tinc Id, no llen�o error perqu� ja ho fa el m�tode "getIdProd"
			// Llavors redirigeixo cap a la jsp de men� i retorno "null"
//			EWokController.forward("/viewMenuServlet/viewMenuServlet.jsp", request, response);
			return null;
		}
		// Obtinc les dades del DAO (ProducteDAO)
		Producte p = null;
		try {
			p = daoProducte.obtenirPerId(nId);
			EWokController.addI18nMessage(ETipusMissatge.info, PARAM_PRODFOUND, request);
		} catch (SQLException e1) {
			// Si hi ha error, el capturo, llen�o l'excepci�
			// i retorno un missatge d'error i un "null"
			EWokController.addI18nMessage(ETipusMissatge.error, null, PARAM_ERROR_PRODNOTFOUND, 
										  new String[]{nId.toString()}, request);
			return null;
		}
		if (p == null) {
			// Si el producte �s null, retorno un missatge d'error i un "null"
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_PROD, 
										  new String[]{nId.toString()}, request);
			return null;
		}
		// Guardo el producte al request
		request.setAttribute(MODEL_PROD, p);
		
		// Retorno l'objecte del tipus "Producte"
		return p;
	}
	
	/**
	 * Mètode específic per obtenir l'id de producte
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private Long getIdProd(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long nId = null;
		try {
			nId = RequestValidationUtils.getMandatoryLong(IDPROD, request);
		} catch (ParameterException e) {
			// Si hi ha error, el capturo, llen�o l'excepci�
			// i retorno un missatge d'error
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_IDPRODNOTFOUND, 
										  new String[]{"null"}, request);
			return null;
		}
		if (nId == null) {
			// Si l'id de producte �s null, retorno un missatge d'error i un "null"
			EWokController.addI18nMessage(ETipusMissatge.error,	null, PARAM_ERROR_IDPROD, 
													  new String[]{"null"}, request);
			return null;
		}
		return nId;
	}
	
	
	
	
	/** Mètode doPost (autogenerat)
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
