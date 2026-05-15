package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.EWokController;

public class Exemple1StaffControllerEsborrar extends SeccioController {

	@Override
	public String getNomAccio() {
		// TODO Auto-generated method stub
		return "accioProductes";
	}

	@Override
	public void doGet(String accio, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", "He passat pel controlador de productes");
		EWokController.forward("staff/homeProductesEsborrar.jsp", request, response);
	}

}







