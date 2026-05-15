package com.soc.ewok.restws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.soc.ewok.controller.publiccontroller.PublicController;
import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.LiniaComanda;

@Path("/comAct")
public class TancaLiniaWS {
	/**
	 * WS per eliminar de la Comanda Actual una línia de comanda
	 * @param idProd La id del producte de la línia que cal eliminar
	 * @return String OK quan la línia s'ha pogut eliminar  
	 */
	@GET
	@Path("/eliminaLinia/num/{idProd}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<LiniaComanda> EsborrarLinia(@PathParam("idProd") Long id, @Context HttpServletRequest request) {
		
	// Agafem la comandaActual, comanda en curs de la sessió
		Comanda com = PublicController.getComanda(request);
	
	// Recuperem la línia que es vol eliminar i l'esborrem amb l'id del producte
		com.eliminarProducte(id);		
		
	// Retornem "ok" un cop eliminada la línia
		return com.getLiniesComanda();
	}
	
	/**
	 * WS per modificar una línia de comanda (afegir o treure productes) de la Comanda Actual
	 * @param idProducte La id del producte que es vol afegir o eliminar de la línia de comanda
	 * @param quantitat La quantitat del producte que es vol afegir o eliminar de la línia de comanda
	 * @return Si tot ha anat bé, retornarà la comanda actualitzada  
	 */
	@GET
	@Path("/modificaLinia/id/{idProducte}/qu/{quantitat}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<LiniaComanda> afegirModificarLinia(@PathParam("idProducte") Long id, 
			@PathParam("quantitat") Integer qu, @Context HttpServletRequest request) {
		
	// Agafem la comandaActual, comanda en curs de la sessió
		Comanda com = PublicController.getComanda(request);
	
	// Recuperem la línia que es vol actualitzar (si no existeix, el mètode la crearà) i fem les modificacions
		com.afegirOEliminarProducte(id, qu);		
		
	// Retornem la comanda actualitzada un cop hem fet les modificacions a la linia de comanda
		return com.getLiniesComanda();
	}
	
	
}
