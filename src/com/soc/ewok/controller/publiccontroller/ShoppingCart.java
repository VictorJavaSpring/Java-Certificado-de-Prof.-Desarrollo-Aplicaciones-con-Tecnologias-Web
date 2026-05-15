package com.soc.ewok.controller.publiccontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.Producte;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

/**
 * Servlet implementation class ShoppingCart
 */
public class ShoppingCart extends PublicController {
	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_ACCIO = "accio";
	private static final String ACCIO_MODIFICAR = "modificar";
	private static final String ACCIO_ESBORRAR = "esborrar";
	private static final String PARAM_ID = "id";
	private static final String PARAM_QUANTITAT = "qu";
    
	private static final String ERROR_ID = "errorNoId";
	private static final String ERROR_QUANTITAT = "errorNoQuantitat";
	
	/**
     * @see PublicController#PublicController()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String accio = null;
		try {
			accio = RequestValidationUtils.getMandatoryString(PARAM_ACCIO, request);
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(accio) {
			case (ACCIO_MODIFICAR):
				doModificarProducte(request, response);
				break;
			case (ACCIO_ESBORRAR):
				doEsborrarProducte(request, response);
				break;
		}
		EWokController.forward("/shoppingCart/shoppingCart.jsp", request, response);
	}

	private void doModificarProducte(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		Comanda comanda = getComanda(request);
		Long producteId = getProducteId(request, response);
		Integer quantitat = getQuantitatProd(request, response);
		
		if (producteId == null || quantitat == null) {
			// No faig res i redirigeixo a la pàgina.
			// getQuantitatProd i getProducteId ja hauràn afegit l'error / els errors
			EWokController.forward("/shoppingCart/shoppingCart.jsp", request, response);
		}
 
		Integer quantitatFinal = comanda.afegirOEliminarProducte(producteId, quantitat);
		if (quantitat == 0) {
			EWokController.addMessage(ETipusMissatge.error, "Cal informar una quantitat", request);
		}
		if (quantitat < 0 && quantitatFinal > 0) {
			EWokController.addMessage(ETipusMissatge.info, "Quantitat disminuita correctament", request);
		}
		if (quantitat < 0 && quantitatFinal == 0) {
			EWokController.addMessage(ETipusMissatge.info, "Producte esborrat correctament", request);
		}
		if (quantitat < 0 && quantitatFinal < 0) {
			EWokController.addMessage(ETipusMissatge.error, "El producte que intentes esborrar no existeix", request);
		}
		if (quantitat > 0 && quantitatFinal > quantitat) {
			EWokController.addMessage(ETipusMissatge.info, "Quantitat augmentada correctament", request);
		}
		if (quantitat > 0 && quantitatFinal == quantitat) {
			EWokController.addMessage(ETipusMissatge.info, "Producte afegit correctament", request);
		}
		EWokController.forward("/shoppingCart/shoppingCart.jsp", request, response);
	}
	
	private Integer getQuantitatProd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer nQuantitatProd = null;
		try {
			nQuantitatProd = (Integer)(int)(long) RequestValidationUtils.getMandatoryLong(PARAM_QUANTITAT, request);
		} catch (ParameterException e) {
			EWokController.addMessage(ETipusMissatge.error, "S'ha d'informar la quantitat", request);
		}
		return nQuantitatProd;
	}

	private Long getProducteId(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		Long nProducteId = null;
		try {
			nProducteId = RequestValidationUtils.getMandatoryLong(PARAM_ID, request);
		} catch (ParameterException e) {
			EWokController.addMessage(ETipusMissatge.error, "S'ha d'informar l'id del producte", request);
		}
		return nProducteId;
	}

	private void doEsborrarProducte(HttpServletRequest request, 
				HttpServletResponse response) throws ServletException, IOException {
		Comanda comanda = getComanda(request);
		Long producteId = getProducteId(request, response);
				
		if (producteId == null) {
			// No faig res i redirigeixo a la pàgina.
			// getQuantitatProd i getProducteId ja hauràn afegit l'error / els errors
			EWokController.forward("/shoppingCart/shoppingCart.jsp", request, response);
			return;
		}
		
		// Si no hi ha comanda, pintem un error i redirigim a la pàgina Shopping Cart
		if (comanda == null) {
			EWokController.addMessage(ETipusMissatge.error, "El producte que intentes esborrar no existeix", request);
			EWokController.forward("/shoppingCart/shoppingCart.jsp", request, response);
			return;
		}
		
		Boolean isEsborrat = comanda.eliminarProducte(producteId);
		if (isEsborrat) {
			EWokController.addMessage(ETipusMissatge.info, "Producte esborrat correctament", request);
			EWokController.forward("/shoppingCart/shoppingCart.jsp", request, response);
			return;
		} else {
			EWokController.addMessage(ETipusMissatge.error, "El producte que intentes esborrar no existeix", request);
			EWokController.forward("/shoppingCart/shoppingCart.jsp", request, response);
			return;
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
