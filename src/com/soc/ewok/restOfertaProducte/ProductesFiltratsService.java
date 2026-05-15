package com.soc.ewok.restOfertaProducte;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.PreuDAO;
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.dao.TipusProducteDAO;
import com.soc.ewok.model.Preu;
import com.soc.ewok.model.Producte;
import com.soc.ewok.model.TipusProducte;

/**
 * 
 * @author JordiM
 *
 */
@Path("/productesfiltrats")
public class ProductesFiltratsService {

	/**
	 * Service que retorna la colecció de productes de una categoria 
	 * @param id Identificador de la categoria
	 * @return Retorna una List de Productes, o un objecte Producte 
	 * quan només ni a un a la bd, o null si no n'hi ha cap
	 * @throws SQLException
	 * s'arriba a aquest mètode per eWok/data/productesfiltrats/unTipusdeProductes
	 */
	@GET
	@Path("/unTipusdeProductes/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Producte> getunTipusdeProductes(@PathParam ("id") Long id) throws SQLException {
		ProducteDAO pdao = new ProducteDAO(EWokController.getGlobalDatasource());
		return pdao.obtenirTotsXTipusId(id);
	}
	
	/**
	 * Service que retorna un producte i els seus components 
	 * @param id Identificador del producte
	 * @return Retorna una un objecte Producte 
	 * o null si no existeix
	 * @throws SQLException
	 * s'arriba a aquest mètode per eWok/data/productesfiltrats/unProducteCompost
	 */
	@GET
	@Path("/unProducteCompost/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Producte getunProducteCompost(@PathParam ("id") Long id) throws SQLException {
		ProducteDAO pdao = new ProducteDAO(EWokController.getGlobalDatasource());
		Producte p = pdao.obtenirProducteCompostPerId(id); 
		return p;
	}
	/**
	 * Service que retorna la colecció de productes que un Producte compost
	 * pot usar 
	 * @param id Identificador de la categoria del producte compost
	 * @return Retorna una List de Productes, o un objecte Producte 
	 * quan només ni a un a la bd, o null si no n'hi ha cap
	 * @throws SQLException
	 * s'arriba a aquest mètode per eWok/data/productesfiltrats/totsComponentsTipusdeProducte/id
	 */
	@GET
	@Path("/totsComponentsTipusdeProducte/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Producte> totsComponentsTipusdeProducte(@PathParam ("id") Long id) throws SQLException {
		ProducteDAO pdao = new ProducteDAO(EWokController.getGlobalDatasource());
		TipusProducteDAO tpdao = new TipusProducteDAO(
				EWokController.getGlobalDatasource());
		String codi = null;
		List<Producte> lpafegir = null;
		try {
			codi = (tpdao.obtenirPerId(id)).getCodi();
			// avaluar codi i afegir productes
			if (TipusProducte.CODI_MENU.equals(codi)) {
				// obtenir plats, articles i serveis
				lpafegir = pdao
						.obtenirTotsXTipusIdVigents(TipusProducte.CODI_PLAT);
				lpafegir.addAll(pdao
						.obtenirTotsXTipusIdVigents(TipusProducte.CODI_ARTICLE));
				lpafegir.addAll(pdao
						.obtenirTotsXTipusIdVigents(TipusProducte.CODI_SERVEI));

			} else if (TipusProducte.CODI_PLAT.equals(codi)) {
				// obtenir ingredients

				lpafegir = pdao
						.obtenirTotsXTipusIdVigents(TipusProducte.CODI_INGREDIENT);

			}else{
				//no és un tipus de producte que pugui tenir components
				//retornarem una llista buida
				lpafegir = new Vector<Producte>();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return lpafegir;
	}
	
	/**
	 * Service que retorna la colecció de productes de una categoria 
	 * @param id Identificador de la categoria
	 * @return Retorna una List de Productes, o un objecte Producte 
	 * quan només ni a un a la bd, o null si no n'hi ha cap
	 * @throws SQLException
	 * s'arriba a aquest mètode per eWok/data/productesfiltrats/unTipusdeProductes
	 */
	@GET
	@Path("/unPreuProducte/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Preu getPreudeProducte(@PathParam ("id") Long id) throws SQLException {
		PreuDAO pdao = new PreuDAO(EWokController.getGlobalDatasource());
		return pdao.obtenirVigentPerId(id);
	}
}
