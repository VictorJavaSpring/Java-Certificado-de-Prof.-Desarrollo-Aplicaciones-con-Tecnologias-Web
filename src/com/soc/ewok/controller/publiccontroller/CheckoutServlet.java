package com.soc.ewok.controller.publiccontroller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.LiniaComanda;
import com.soc.ewok.model.Producte;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutServlet extends PublicController {
	private static final long serialVersionUID = 1L;

	private static final String ACCIO_COMANDA = "accioComanda";
	private static final String ACCIO_CHECKOUT = "checkout";
	private static final String MODEL_COMANDA = "liniescomanda";
	private static final String PARAM_IDIOMA = "idioma";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String accio = request.getParameter(ACCIO_COMANDA);
		if (ACCIO_CHECKOUT.equals(accio)) {
			doLlistaCheckout(request, response);
		}
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private void doLlistaCheckout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Gestionem l'idioma
		String idioma = EWokController.getIdioma(request);
		// request.setAttribute("msg", "Idioma "+idioma);
		request.getSession().setAttribute(PARAM_IDIOMA, idioma);

		// Agafem la comanda en curs de la sessio
		Comanda com = PublicController.getComanda(request);

		// Aixo es per treure per pantalla informacio mentre no hi ha comandes a la BdDd
		try { 
			com.getLiniesComanda();
		} catch(IllegalStateException e) {
			LiniaComanda linCom = new LiniaComanda(); // Creem una linia de
														// comanda
			Producte prod = new Producte(); // Creem un producte
			prod.setId(1L); // Li posem un Id
			prod.setNom("Wok complet"); // Li posem un nom
			prod.setDescripcio("Especialitat de la casa"); // Afegim una descripcio al producte
			linCom.setProducte(prod); // Posem el producte a la linia de comanda			
			linCom.setQuantitat(2); // Posem una quantitat d'unitats
			linCom.setPreuVenda((float) 9.5); // Posem un preu de venda
			com.addLiniaComanda(linCom); // Afegim la linia de comanda a la llista
			// Creem una altra linia de comanda
			LiniaComanda linCom2 = new LiniaComanda(); 
			Producte pr = new Producte(); // Creem un producte
			pr.setId(2L); // Li posem un Id
			pr.setNom("Coca Cola"); // Li posem un nom
			pr.setDescripcio("Ampolla de 2 litres"); // Afegim una descripcio al producte
			linCom2.setProducte(pr); // Posem el producte a la linia de comanda			
			linCom2.setQuantitat(1); // Posem una quantitat d'unitats
			linCom2.setPreuVenda((float) 2.5); // Posem un preu de venda
			com.addLiniaComanda(linCom); // Afegim la primera linia de comanda a la llista
			com.addLiniaComanda(linCom2); // Afegim la segona linia de comanda a la llista
		}
		// Fins aqui. Aquesta part anterior s'haura d'eliminar.

		// Recuperem la llista de linies de comanda corresponents a la comanda
		// de la sessio
//		try {
//			// Fiquem al request el que volem que es pinti si han demanat
//			// checkout
//			request.setAttribute(MODEL_COMANDA, com.getLiniesComanda());
//		} catch (IllegalStateException ex) {
//		}
		
		// Ja tenim 'comandaActual' a la sessio
		// Pintem la pagina
		forward("checkout/checkout.jsp", request, response);
		// request.getRequestDispatcher("exemples/checkout.jsp").forward(request,
		// response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
