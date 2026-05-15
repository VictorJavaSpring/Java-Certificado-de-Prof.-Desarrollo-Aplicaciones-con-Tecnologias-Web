package com.soc.ewok.restws;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.ComandaDAO;
import com.soc.ewok.dao.LiniaComandaDAO;
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.model.EEstatComanda;
import com.soc.ewok.model.EEstatLiniaComanda;
import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.LiniaComanda;
import com.soc.ewok.model.Producte;

@Path("/comanda")
public class VCuinerws {
	/**
	 * Service que retorna un JSON amb les dades d'una linia de comanda
	 * @param id La id de la comanda
	 * @param id2 El num de linia de la comanda
	 * @return Objecte linia de comanda
	 */
	@GET
	@Path("/getLComanda/{id}/num/{id2}")
	@Produces({MediaType.APPLICATION_JSON})
	public LiniaComanda getLiniaComanda(@PathParam("id") Long id, @PathParam ("id2") Long id2) {
		LiniaComandaDAO lDAO = new LiniaComandaDAO(EWokController.getGlobalDatasource());
		try {
			return lDAO.obtenirPerId(id, id2);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Service que retorna una llista de Linies de comandes
	 * @return Objecte List linies de comandes
	 * @author Carlos Tomas
	 */
	@GET
	@Path("/getLComandes")
	@Produces({MediaType.APPLICATION_JSON})
	public List<LiniaComanda> getLiniesComanda(){
		LiniaComandaDAO lDAO = new LiniaComandaDAO(EWokController.getGlobalDatasource());
		List<LiniaComanda> l = null;
		try {
			l = lDAO.obtenirLiniaComandaEstatComanda();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
	 * Service que retorna una linia de comanda amb l'estat cambiat al estat superior
	 * ¡¡¡AL LORO!!! modifica el status de la Linia de comanda de la BBDD
	 * ¡¡¡AL LORO!!! Si totes les linies de comanda de la comanda estan en estat entregat, 
	 * canvia l'estat de la comanda a entregada, sino, no
	 * @param id de Comanda
	 * @param id2 Num de linia de comanda
	 * @param status Estat de la comanda
	 * @return objecte Linia de comanda
	 */
	@GET
	@Path("/getLComanda/{id}/num/{id2}/stat/{stat}")
	@Produces({MediaType.APPLICATION_JSON})
	public LiniaComanda changeLiniaComandaStatus(@PathParam("id") Long id, @PathParam ("id2") Long id2, @PathParam("stat") EEstatLiniaComanda status) {
		LiniaComandaDAO lDAO = new LiniaComandaDAO(EWokController.getGlobalDatasource());
		LiniaComanda l = null;
		try {
			l = lDAO.obtenirPerId(id, id2);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		if(!(l.getEstat().equals(status))){
			l.setEstat(status);
			try {
				lDAO.modificar(l);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		// Si estem canviant la linia de comanda a entregat, carregem la llista de linies de comanda de la comanda
		// i mirem si TOTES les linies de comanda estan en estat entregat, i si ho est�n, canviem l'estat de la comanda a entregada 
		
		//		EEstatComanda estatComanda = com.getEstat();
		
		// Chequejem si estem canviant la Linia d comanda a entregat
		EEstatLiniaComanda variableEntregat = EEstatLiniaComanda.entregat;
		System.out.println ("variableEntregat.equals(status):" + variableEntregat.equals(status)); // true
		if (variableEntregat.equals(status)) {
			// si es aixo, mirem si l'estat totes les linies de comanda de la comanda es entregat
			
			// obtenim la comanda de la que forma part la Linia de comanda
			ComandaDAO cDAO = new ComandaDAO(EWokController.getGlobalDatasource());
			Comanda com = null;
			try {
				com = cDAO.obtenirPerIdLC(id);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			System.out.println ("com.getLiniesComanda():" + com.getLiniesComanda());
			List<LiniaComanda> llistaLinies = null;
			llistaLinies = com.getLiniesComanda(); // OK!
			System.out.println ("llistaLinies:" + llistaLinies);
			// recorrem la llista de Linies de comanda o la linia de comanda, si nomes hi ha una.
			int numLinies = llistaLinies.size();
			System.out.println ("numLinies:" + numLinies);
			int contadorEntregats = 0;
			int i = 0;
			while (i < numLinies ) {
				LiniaComanda linia = llistaLinies.get(i);
				System.out.println ("linia:" + linia);
				EEstatLiniaComanda estatLinia = linia.getEstat();
				System.out.println ("estatLinia :" + estatLinia );
				if (variableEntregat.equals(estatLinia)){
					contadorEntregats += 1;
				}
				i++;
			}
			// si l'estat totes les linies de comanda de la comanda es entregat, fiquem la comanda a entregada
			if (numLinies == contadorEntregats){
				EEstatComanda estatComandaE = EEstatComanda.entregada;
				com.setEstat(estatComandaE);
			}
		}
		return l;
	}
	
	/**
	 * Service que retorna una comanda amb l'estat cambiat al estat superior
	 * AL LORO!!! modifica el status de la comanda de la BBDD
	 * @param id de Comanda
	 * @param status de la Comanda
	 * @return objecte comanda
	 */
	@GET
	@Path("/getComanda/{id}/stat/{stat}")
	@Produces({MediaType.APPLICATION_JSON})
	public Comanda changeComandaStatus(@PathParam("id") Long id, @PathParam("stat") EEstatComanda status) {
		ComandaDAO cDAO = new ComandaDAO(EWokController.getGlobalDatasource());
		Comanda c = null;
		try {
			c = cDAO.obtenirPerId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		if(!(c.getEstat().equals(status))){
			c.setEstat(status);
			try {
				cDAO.modificar(c);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return c;
	}
	
	/**
	 * Service que retorna les Linies de Comanda d'una comanda i el objecte producte de cada linia
	 * @param id de la comanda
	 * @return llista de Linies Comanda(si hi han) i el objecte producte de cada linia
	 */
	@GET
	@Path("/getLiniesComanda/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<LiniaComanda> getComanda(@PathParam ("id") Long id) {
		// http://localhost:8080/eWok/data/comanda/getLiniesComanda/1
		ComandaDAO cDAO = new ComandaDAO(EWokController.getGlobalDatasource());
		Comanda c = null;
		try {
			c = cDAO.obtenirPerIdLC(id);
		} catch (SQLException e) {
			return null;
		}
		
		ProducteDAO pDAO = new ProducteDAO(EWokController.getGlobalDatasource());
		Producte p = null;
			// bucle per recorrer les linies de comanda , extreure el producte i fer un setProducte a la linia
			List<LiniaComanda> llista = c.getLiniesComanda();
			int i = 0;
			while (i < llista.size() ) {
				LiniaComanda linia = llista.get(i);
				Long idProducte = linia.getIdProducte();
				try {
					p = pDAO.obtenirPerId(idProducte);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				linia.setProducte(p);
				i++;
			}
		return c.getLiniesComanda();
	}
	
	/**
	 * Service que retorna la llista de comandes en curs d'estats validades i preparades 
	 * @return llista de comandes d'estats validades i preparades 
	 */
	@GET
	@Path("/getComandesVP")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Comanda> getComandes() {
		// http://localhost:8080/eWok/data/comanda/getComandesVP
		ComandaDAO cDAO = new ComandaDAO(EWokController.getGlobalDatasource());
		List<Comanda> c = null;
		try {
			c = cDAO.obtenirComandesEstatsValidada_Preparada();
			// c = cDAO.obtenirPerIdLC(id);
		} catch (SQLException e) {
			return null;
		}
		return c;
	}
	

	
}



