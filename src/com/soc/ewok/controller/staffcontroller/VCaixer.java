package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
//import com.soc.ewok.dao.LiniaComandaDAO;
import com.soc.ewok.dao.ComandaDAO;
//import com.soc.ewok.model.LiniaComanda;
import com.soc.ewok.model.Comanda;
import com.soc.ewok.model.Rol;

public class VCaixer extends SeccioController {
	private static final String PARAM_IDIOMA = "idioma";
	private static final String ACCIO_VISTACAIXER = "accioVistaCaixer";
	//private static final String MODEL_LLISTAT_COMANDES = "comandes";
	private static final String MODEL_LLISTAT_COMANDES_VALIDPREPA = "comandesVP";
	//private static final Object ACCIO_CANVIESTAT = "canviEstat";
	//private static final Object ACCIO_VEURELINIACOMANDA = "veureLiniaComanda";
	
	// LiniaComandaDAO lComandaDAO = null;
	ComandaDAO ComandaDAO = null;
	
	public void VVistaCaixer(){
		// ewok.
		ComandaDAO = new ComandaDAO(EWokController.getGlobalDatasource());
		//afegeixo QUAN PUGUI els rols que poden usar aquest controller
		addRols2Controller(Rol.ROL_CODI_CAIXER);
	}
	
	@Override
	public String getNomAccio() {
		return ACCIO_VISTACAIXER;
	}

	@Override
	public void doGet(String accio, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Accio "+accio); 
		if (accio.equals(ACCIO_LLISTAT)) {
			doVeureLlista(request, response);
		} 
//		else if (accio.equals(ACCIO_CANVIESTAT)) {
//			doCanviEstatComanda(request, response);
//		} 
//		else if (accio.equals(ACCIO_VEURELINIACOMANDA)) {
//			doVeureLiniaComanda(request, response);
//		}
	}
	
	public void doVeureLlista (HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Idioma
		String idioma = EWokController.getIdioma(request);
		//request.setAttribute("msg", "Idioma "+idioma);
		request.getSession().setAttribute(PARAM_IDIOMA, idioma);
		
		/* Selecciono totes les comandes amb Estat = validada AND preparada
		 * Despres canviarem l'Estat a: validada -> pagada, preparada -> preparada i pagada
		 *  */
    	// Comandes validades i preparades
		List<Comanda> comandesVP = new Vector<Comanda>(); // La llista de comandes validades
    	ComandaDAO ComandaDAOV = new ComandaDAO(EWokController.getGlobalDatasource()); // Qui fa el select
    	    	try {
    	    		// obtenirTots
    	    		// comandesVP = ComandaDAOV.obtenirComandesEstatsValidada_Preparada();
    	    		comandesVP = ComandaDAOV.obtenirTots();
    	}catch (SQLException e){
    		System.out.println(e);
    		EWokController.addI18nMessage(ETipusMissatge.error, "Caixer001.Comanda", request);
    	}
    	
//    	// Comandes preparades
//    	List<Comanda> ComandaP = new Vector<Comanda>(); // La llista de comandes preparades
//    	ComandaDAO ComandaDAOP = new ComandaDAO(EWokController.getGlobalDatasource()); // Qui fa el select
//    	    	try {
//    		ComandaP = ComandaDAOP.obtenirFiltreEstatPreparada();
//    	}catch (SQLException e){
//    		System.out.println(e);
//    		EWokController.addI18nMessage(ETipusMissatge.error, "Caixer001.Comanda", request);
//    	}
    	// juntem les dues llistes
//    	List<Comanda> ComandaVP = new ArrayList<Comanda>();
//    	ComandaVP.addAll(comandesVP);
    	//ComandaVP.addAll(ComandaP);
    	
    	// Fiquem les dades al request
    	request.setAttribute(MODEL_LLISTAT_COMANDES_VALIDPREPA, comandesVP);

		//Redirigeixo cap al jsp
		EWokController.forward("staff/vistaCaixer/llistatVistaCaixer.jsp", request, response);
	}
	
}
