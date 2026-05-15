package com.soc.ewok.controller.publiccontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.model.Producte;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

/**
 * Classe que gestiona accions de vista de productes per categoria
 * a la part publica de la web.
 * 	Quan l'usuari clica a shoping categoria x, ha de veure una galeria
 * amb els productes de la categoria i poder-los afegir al cart
 * @author JordiM
 *
 */
public class PMostraProductesCategoria extends PublicController {
	

	private static final long serialVersionUID = 1L;
	
	//CONSTANTS 
	private static final String MODEL_PRODUCTES_FILTRATS = "productesFiltrats";
	private static final String PARAM_CODI = "codi";
	
	//error
	private static final String REQ_ERROR_NO_PROD = "errorNoProd";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.-obtenir el paràmetre codi (de categoria) del request
		String codi = null;
		try {
			codi = RequestValidationUtils.getMandatoryString(PARAM_CODI, request);
		} catch (ParameterException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (codi==null)return;
		//2.- obtenir el llistat amb els productes de tipus/categoria que estiguin vigents
		// a partir del DAO
		ProducteDAO pdao = new ProducteDAO(EWokController.getGlobalDatasource());
		List<Producte> lp = null;
		try {
			lp = pdao.obtenirTotsXTipusIdVigents(codi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//si no hi ha productes d'aquesta categoria posar missatge
		if(lp.size()==0){
			request.setAttribute(REQ_ERROR_NO_PROD, true);
		}
		//3.- posar les dades al request
		request.setAttribute(MODEL_PRODUCTES_FILTRATS, lp);

		//4.- redirigim a pàgina
		EWokController.forward("/mostraProdCateg/mostraProdCateg.jsp", request, response);
	}
}
