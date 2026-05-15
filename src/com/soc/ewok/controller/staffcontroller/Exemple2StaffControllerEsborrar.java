package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.model.Rol;

public class Exemple2StaffControllerEsborrar extends SeccioController {

	/**
	 * CONSTRUCTOR per defecte
	 */
	public Exemple2StaffControllerEsborrar(){
		//afegim els rols que poden usar aquest controlador
		addRols2Controller(Rol.ROL_CODI_ADMINISTRADOR);
		addRols2Controller(Rol.ROL_CODI_CAIXER);
	}
	
	@Override
	public String getNomAccio() {
		return "accioClients";
	}

	@Override
	public void doGet(String accio, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", "He passat pel controlador de clients");
		EWokController.forward("staff/homeClientsEsborrar.jsp", request, response);
	}
	
	

}
