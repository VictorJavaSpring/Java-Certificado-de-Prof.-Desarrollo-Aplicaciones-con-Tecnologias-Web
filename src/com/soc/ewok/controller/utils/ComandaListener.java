package com.soc.ewok.controller.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.soc.ewok.controller.publiccontroller.PublicController;
import com.soc.ewok.model.Comanda;

/**
 * Application Lifecycle Listener implementation class ComandaListener
 *
 */
public class ComandaListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public ComandaListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent sess)  { 
         // Associem una comanda nova a la sessió
    	Comanda comanda = new Comanda();
    	comanda.addLiniaComanda(null);
    	PublicController.setComanda(comanda, sess.getSession());
    }

    
	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }


	
}
