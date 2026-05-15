package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.LiniaComandaDAO;
import com.soc.ewok.model.LiniaComanda;
import com.soc.ewok.model.Rol;

public class GVistaCuiner extends SeccioController {
	private static final String PARAM_IDIOMA = "idioma";
	private static final String ACCIO_VISTACUINER = "accioVistaCuiner";
	private static final String MODEL_LLISTAT_PLATS = "comandes";
	LiniaComandaDAO lComandaDAO = null;
	
	public void GVistaCuiner(){
		// ewok.
		lComandaDAO = new LiniaComandaDAO(EWokController.getGlobalDatasource());
		//afegeixo QUAN PUGUI els rols que poden usar aquest controller
		addRols2Controller(Rol.ROL_CODI_CUINER);
	}
	
	@Override
	public String getNomAccio() {
		return ACCIO_VISTACUINER;
	}

	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Accio "+accio);
		switch(accio) {
		case ACCIO_VEURE:
			doVeureLlista(request, response);
			break;
		}
	}
	
	public void doVeureLlista (HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Idioma
		String idioma = EWokController.getIdioma(request);
		request.setAttribute("msg", "Idioma "+idioma);
		request.getSession().setAttribute(PARAM_IDIOMA, idioma);
		
		/* Selecciono totes les lineas de comanda amb els tipus de producte plt i plt0 */
    	List<LiniaComanda> lComanda = new Vector<LiniaComanda>(); //La llista de comandes
    	LiniaComandaDAO lComandaDAO = new LiniaComandaDAO(EWokController.getGlobalDatasource()); //Qui fa el select
    	
    	try {
    		//lComanda = lComandaDAO.obtenirLiniaComandaTipus("plt","plt0"); //Faig el select i ho paso a la llista de comandes
    		lComanda = lComandaDAO.obtenirTots();
    	}catch (SQLException e){
    		System.out.println(e);
    		EWokController.addI18nMessage(ETipusMissatge.error, "Cuiner001.LiniaComanda", request);
    	}
    	
    	// Fiquem les dades al request
    	request.setAttribute(MODEL_LLISTAT_PLATS, lComanda);

		//Redirigeixo cap al jsp
		EWokController.forward("staff/vistacuiner.jsp", request, response);
	}
}
