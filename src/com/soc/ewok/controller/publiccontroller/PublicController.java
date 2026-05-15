package com.soc.ewok.controller.publiccontroller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.model.Comanda;

/**
 * Classe base per a tots els servlets
 * (controladors) de la zona ública
 * @author Administrador
 *
 */
public class PublicController extends EWokController {
	private static final long serialVersionUID = 1L;
	
	public static final String COMANDA_ACTUAL = "comandaActual";
       
    /**
     * @see EWokController#EWokController()
     */
    public PublicController() {
        super();
    }    

	public static void setComanda(Comanda comanda, HttpSession sess) {
		// Posem a la sessió la comanda que rebem del listener
		sess.setAttribute(COMANDA_ACTUAL, comanda);
	}
	
	public static Comanda getComanda(HttpServletRequest request){
		// Demanem la comanda que tenim a la sessió
		Comanda com = (Comanda) ((HttpServletRequest) request).getSession().getAttribute(COMANDA_ACTUAL);
		
		if (com == null) {
	    	com = new Comanda();
	    	com.addLiniaComanda(null);
	    	setComanda(com, request.getSession());
			
		}
		return com;
	}
	
}
