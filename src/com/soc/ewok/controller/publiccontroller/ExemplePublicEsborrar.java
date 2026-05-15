package com.soc.ewok.controller.publiccontroller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.ETipusMissatge;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.UsuariDAO;

/**
 * Servlet implementation class ExemplePublicEsborrar
 */
public class ExemplePublicEsborrar extends PublicController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see PublicController#PublicController()
     */
    public ExemplePublicEsborrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuariDAO daoUsu = new UsuariDAO(getGlobalDatasource());
		int nUsuaris;
		try {
			nUsuaris = daoUsu.obtenirTots().size();
			request.setAttribute("msg", "Hola, això és un exemple. A la base de dades hi ha " + nUsuaris + " usuaris.");
			addMessage(ETipusMissatge.info, "L'accés a base de dades ha estat correcte", request);						
			addI18nMessage(ETipusMissatge.warning, "ex1.normal", request);
			addI18nMessage(ETipusMissatge.warning, null, "ex1.ambparams", new String[]{"hola", "adeu"}, request);
			addI18nMessage(ETipusMissatge.warning, "ex1.nohies", request);
			addMessage(ETipusMissatge.error, "Primer error", request);
			addMessage(ETipusMissatge.error, "Segon error", request);
		} catch (SQLException e) {
			addMessage(ETipusMissatge.error, "S'ha produit un error accedint a base de deades", request);
		}
		forward("exemples/plantillaFormulari.jsp", request, response);
	}

}
