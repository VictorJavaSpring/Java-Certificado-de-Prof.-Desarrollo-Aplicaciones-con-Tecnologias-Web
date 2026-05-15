package com.soc.ewok.controller.staffcontroller;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.utils.ParameterException;
import com.soc.utils.RequestValidationUtils;

/**
 * Classe base per a tots els controladors de
 * la zona privada
 * @author Administrador
 *
 */
public abstract class SeccioController implements ISectionController {
	//constants de la classe
	protected static final String ACCIO_LLISTAT = "llistat";
	protected static final String ACCIO_VEURE = "veure";
	protected static final String ACCIO_ESBORRAR = "esborrar";
	protected static final String ACCIO_PREPARARALTA = "prepararAlta";
	protected static final String ACCIO_ALTA = "alta";
	protected static final String ACCIO_PREPARARMODIFICACIO = "prepararModificacio";
	protected static final String ACCIO_MODIFICACIO = "modificacio";
	
	//constatnts de parametre id
	private static final String PARAM_ID = "id";
	private static final String REQ_ERRORPARAM = "No s'ha trobat el paràmetre";

	//var membre de la classe
	protected List<String> llistaCodisRols = null;
	
	/**
	 * @see com.soc.ewok.controller.staffcontroller.ISectionController#getRolsValids(String)
	 * @return Retorna una llista de tipus string amb els codis dels rols que poden 
	 * usar aquest controlador
	 */
	@Override
	public List<String> getRolsValids(String accio) {
		return llistaCodisRols;
	}
	
	/**
	 * mètode d'utilitat que afegeix els rols d'usuari que poden usar 
	 * aquest controller a la var membre llistaCodisRols
	 */
	protected void addRols2Controller(String codirol) {
		if (llistaCodisRols==null){
			llistaCodisRols = new Vector<String>();
		}
		llistaCodisRols.add(codirol);
	}

	/**
	 * utilitat per a obtenir l'id del request
	 * el literal buscat és 'id' (tot minúscula)
	 * @param request La request actual
	 * @param response La response actual
	 * @return id en format Long o null
	 */
	protected Long getidFromRequest(HttpServletRequest request,
			HttpServletResponse response) {
		Long nId = null;
		try {
			nId = RequestValidationUtils.getMandatoryLong(
					PARAM_ID, 
					request);
		} catch (ParameterException e) {
			EWokController.addMessage(ETipusMissatge.error,
					REQ_ERRORPARAM, request);
		}
		return nId;
	}
	
}
