package com.soc.utils;

import java.sql.SQLException;
import javax.sql.DataSource;
import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.UsuariDAO;
import com.soc.ewok.model.Usuari;

public class AutentificadorBD implements IAutentificador {
	
	@Override
	/**
	 * Autentifica un usuari contra la taula Usuari
	 * @param usu l'usuari, pwd la contrasenya a autentificar
	 * @return true o false segons l'exit de l'autentificacio
	 */
	public boolean isUsuariAutoritzat(String usu, String pwd) {
		DataSource ds = EWokController.getGlobalDatasource();
		
		UsuariDAO dao = new UsuariDAO(ds);
		
		Usuari usuari;
		try {
			usuari = dao.obtenirPerEmail(usu); 
			if (usuari == null) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		
		if (usuari.getActiu()) { 
			if(usuari.getPassword().equals(pwd)) return true;
		}
		
		return false;
	}
}
