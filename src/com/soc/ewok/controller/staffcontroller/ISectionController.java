package com.soc.ewok.controller.staffcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interficie a implementar per tots els controladors
 * de la part privada
 *
 */
public interface ISectionController {
	/**
	 * Retorn el nom de l'acció per discernir si la request
	 * actual ha de ser gestionada per aquest controlador
	 * @return
	 */
	public String getNomAccio();

	/**
	 * Retorna una llista dels rols que poden executar l'acció indicada
	 * @param accio L'acció que es vol executar
	 * @return La llista de rols autoritzats
	 */
	public List<String> getRolsValids(String accio);
	

	/**
	 * Processa una request concreta
	 * @param accio L'acció demanada
	 * @param request La request en curs
	 * @param response La response en curs
	 * @throws ServletException Excepció genèrica de servlet
	 * @throws IOException Excepció genèrica de servlet
	 */
	public void doGet(String accio, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}
