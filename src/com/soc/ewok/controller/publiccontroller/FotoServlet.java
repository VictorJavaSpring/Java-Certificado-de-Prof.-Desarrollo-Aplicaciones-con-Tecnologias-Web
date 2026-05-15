package com.soc.ewok.controller.publiccontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.staffcontroller.GFotoController;

/**
 * Servlet implementation class FotoServlet
 */
public class FotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String ACCIO_FOTO = "accioFoto";
	private static final String ACCIO_GET = "get";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accio = request.getParameter(ACCIO_FOTO);
		if (ACCIO_GET.equals(accio)) {
			GFotoController.downloadFoto(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
