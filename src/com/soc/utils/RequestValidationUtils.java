package com.soc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe d'utilitats per extraure par�metres del request
 *
 */
public class RequestValidationUtils {
	
	/**
	 * Busca un par�metre de tipus string i obligatori
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi
	 * el par�metre o sigui espais en blanc
	 */
	public static String getMandatoryString(
			String paramName,
			HttpServletRequest request
			) throws ParameterException {
		
		// Intento obtenir el par�metre demanat
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// Si detecto un error, construeixo una excepci� i la llen�o
			throw createParameterException("S'ha de introduir una cadena de texte. ", paramName, str);
//			throw createParameterException("No s'ha trobat el par�metre indicat", paramName, str);
		}
		// Si tot ha anat b�, retorno el valor trobat
		return str;
	}
	
	/**
	 * Busca un par�metre de tipus String i no obligatori
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor String trobat o null
	 */
	public static String getNonMandatoryString(
			String paramName,
			HttpServletRequest request ) {

		// Intento obtenir el par�metre demanat
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// No hi ha error, per� no tinc valor a retornar
			return null;
		}
		// No hi ha error i tinc valor per retornar
		return str;
	}
	
	/**
	 * Busca un par�metre de tipus Long i no obligatori
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que es trobi el valor
	 * per� no pugui ser convertit a long
	 */
	public static Long getNonMandatoryLong(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// No hi ha error, per� no tinc valor a retornar
			return null;
		}
		Long l;
		try {
			l = Long.parseLong(str);
		} catch(NumberFormatException ex) {
			// Hi ha error
			throw createParameterException("El valor no s'ha pogut convertir a un n�mero v�lid", paramName, str);
		}
		// No hi ha error i tinc valor per retornar
		return l;
	}
	
	/**
	 * Busca un par�metre de tipus Long i obligatori
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o b� que
	 * es trobi per� no pugui ser convertit a long
	 */
	public static Long getMandatoryLong(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el par�metre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el par�metre
			// escric el missatge d'error i retorno
			throw createParameterException("No s'ha trobat el par�metre indicat", paramName, sParam);
		}
		Long nValor;
		try { 
			// Intentem parsejar el n�mero
			nValor = Long.parseLong(sParam);
		} catch(NumberFormatException e) {
			// Si no ho aconseguim, cal indicar l'error i acabar
			throw createParameterException("El valor no s'ha pogut convertir a un n�mero v�lid", paramName, sParam);
		}
		// Si tot ha anat b�, retornem el valor obtingut
		return nValor;
	}

	/**
	 * Busca un par�metre de tipus float i no obligatori
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que es trobi el valor
	 * per� no pugui ser convertit a float
	 */
	public static Float getNonMandatoryFloat(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// No hi ha error, per� no tinc valor a retornar
			return null;
		}
		Float f;
		try {
			f = Float.parseFloat(str);
		} catch(NumberFormatException ex) {
			// Hi ha error
			throw createParameterException("El valor no s'ha pogut convertir a un n�mero v�lid", paramName, str);
		}
		// No hi ha error i tinc valor per retornar
		return f;
	}
	
	/**
	 * Busca un par�metre de tipus Float i obligatori
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o b� que
	 * es trobi per� no pugui ser convertit a float
	 */
	public static Float getMandatoryFloat(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el par�metre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el par�metre
			// escric el missatge d'error i retorno
			throw createParameterException("No s'ha trobat el par�metre indicat", paramName, sParam);
		}
		Float nValor;
		try { 
			// Intentem parsejar el n�mero
			nValor = Float.parseFloat(sParam);
		} catch(NumberFormatException e) {
			// Si no ho aconseguim, cal indicar l'error i acabar
			throw createParameterException("El valor no s'ha pogut convertir a un n�mero v�lid", paramName, sParam);
		}
		// Si tot ha anat b�, retornem el valor obtingut
		return nValor;
	}
	
	/**
	 * Funci� de soport per crear una excepci� de par�metre
	 * @param message El missatge explicatiu
	 * @param paramName El par�metre buscat
	 * @param paramValue El valor trobat o null si no se n'ha trobat
	 * @return L'excepci� generada
	 */
	private static ParameterException createParameterException(
			String message, String paramName, String paramValue) {
		ParameterException e = 
			new ParameterException(message);
		e.setNomParametre(paramName);
		e.setValorTrobat(paramValue);
		return e;
	}
	
	/**
	 * Busca un par�metre de tipus Date i obligatori
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o b� que
	 * es trobi per� no pugui ser convertit a Date
	 */
	public static Date getMandatoryDate(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el par�metre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el par�metre
			// escric el missatge d'error i retorno
			throw createParameterException("Cal indicar una data", paramName, sParam);
		}
		// Validem el format de la data
		Date dParam = null;	    
	    try {
	    	dParam = new SimpleDateFormat("yyyy-MM-dd").parse(sParam.trim());
	    } catch (ParseException pe) {
	    	// Si no ho aconseguim, indiquem l'error i acabem
	    	throw createParameterException("La data ha de tenir un format v�lid (dd-mm-yyyy)", paramName, sParam);	     
	    }

	    return dParam;
	  }
	
	/**
	 * Rep un Date obligatori i torna true si es v�lida i fals si no
	 * @param paramName El nom del par�metre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o b� que
	 * es trobi per� no pugui ser convertit a Date
	 */
	public static boolean isMandatoryDateValid(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el par�metre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el par�metre
			// escric el missatge d'error i retorno
			throw createParameterException("Cal indicar una data", paramName, sParam);
		}
		// Validem el format de la data
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(sParam.trim());
	    } catch (ParseException pe) {
	    	// Si no ho aconseguim, indiquem l'error i acabem
	    	throw createParameterException("La data ha de tenir un format v�lid (dd-MM-yyyy)", paramName, sParam);
	     
	    }
	    return true;
	  }
	/**
	 * 
	 * @param paramName Nom del par�metre a buscar al request
	 * @param request La request actual
	 * @return Una Date o null
	 * @throws ParameterException Si el format de la data no es v�lid
	 */
	public static Date getNonMandatoryDate(String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el par�metre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// No hi ha error, es retorna null
			return null;
		}
		// Validem el format de la data
		Date dParam = null;
	    try {
	    	dParam = new SimpleDateFormat("yyyy-MM-dd").parse(sParam.trim());
	    } catch (ParseException pe) {
	    	// Si no ho aconseguim, indiquem l'error i acabem
	    	throw createParameterException("La data ha de tenir un format v�lid (yyyy-MM-dd)", paramName, sParam);	     
	    }
	    return dParam;
	}
	
	/**
	 * 
	 * @param paramName Nom del par�metre a buscar al request
	 * @param request La request actual
	 * @return Una Date o null
	 * @throws ParameterException Si el format de la data no es v�lid
	 */
	public static Date getNonMandatoryTime(String paramName,
			HttpServletRequest request) throws ParameterException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		// Obtenim el par�metre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// No hi ha error, es retorna null
			return null;
		}
		// Validem el format de la hora
		Date dParam = null;
	    try {
	    	sdf.applyPattern("HH:mm");
	    	dParam = sdf.parse(sParam.trim());
	    } catch (ParseException pe) {
	    	// Si no ho aconseguim, indiquem l'error i acabem
	    	throw createParameterException("L'hora ha de tenir un format v�lid (HH:mm)", paramName, sParam);	     
	    }
	    return dParam;
	}
	
	/**
	 * 
	 * @param paramName Nom del par�metre a buscar al request
	 * @param request La request actual
	 * @return Una Date
	 * @throws ParameterException Si es null o el format de la data no es v�lid
	 */
	public static Date getMandatoryTime(String paramName,
			HttpServletRequest request) throws ParameterException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		// Obtenim el par�metre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// No ens passen el par�metre
			// escric el missatge d'error i retorno
			throw createParameterException("Cal indicar una hora", paramName, sParam);
		}
		// Validem el format de la hora
		Date dParam = null;
	    try {
	    	sdf.applyPattern("HH:mm");
	    	dParam = sdf.parse(sParam.trim());
	    } catch (ParseException pe) {
	    	// Si no ho aconseguim, indiquem l'error i acabem
	    	throw createParameterException("L'hora ha de tenir un format v�lid (HH:mm)", paramName, sParam);	     
	    }
	    return dParam;
	}
	
		/**
		 * 
		 * @param paramData Nom del par�metre per buscar la data al formulari
		 * @param paramHora Nom del par�metre per buscar la hora al formulari
		 * @param request La request actual
		 * @return null o un Date: data + hora (si no ens informen de la hora, retornar� 00:00 per defecte)
		 * @throws ParameterException Si el format de la data no es v�lid
		 */
	public static Date obtenirDateTime(String paramData, String paramHora, HttpServletRequest request) 
			throws ParameterException {
		// Creem un objecte SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat();
		
		// Agafem la data (sense hora) del formulari
		Date dData = getNonMandatoryDate(paramData, request);
		
		String sData = "";
		if (dData != null) {
			// Transformem la data en String
			sdf.applyPattern("yyyy-MM-dd");
			sData = sdf.format(dData);
		}
		
		// Agafem la hora del formulari
		Date dHora = getNonMandatoryTime(paramHora, request);
				
		String sHora = "";
		if (dHora != null) {
		// Transformem la hora en String
		sdf.applyPattern("HH:mm:ss");
		sHora = sdf.format(dHora);
		}
		
		// Unim data y hora en un sol String
		String sDataIHora = sData + " " + sHora;
		
		// Mirem la llargada del String
		int l = sDataIHora.length();

		// Transformem el String DataIHora en una Data
		Date dDataIHora = null;
		
		// Si el string cont� nomes la data (perqu� la hora es null), li appliquem el pattern yyyy-MM-dd 
		// Aixo al parsejar no obtindrem null sino la data amb hora 00:00 com hora per defecte
		if (l > 9 && l < 12) {
			sdf.applyPattern("yyyy-MM-dd");
			try {
				dDataIHora = sdf.parse(sDataIHora);
			} catch (ParseException e) {}
		} else {
			// Si el string cont� data i hora, li apliquem el pattern yyyy-MM-dd HH:mm:ss
			// Aixo obtindrem un objecte Date amb la data i hora que hem rebut pel formulari
			sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
			try {
				dDataIHora = sdf.parse(sDataIHora);
			} catch (ParseException e) {}
		}
		// Si el string cont� nomes la hora, retornar� null
		return dDataIHora;
	}
	
	/**
	 * 
	 * @param paramData Nom del par�metre per buscar la data al formulari
	 * @param paramHora Nom del par�metre per buscar la hora al formulari
	 * @param request La request actual
	 * @return un Date: data + hora (si no ens informen de la hora, retornar� 00:00 per defecte)
	 * @throws ParameterException Si es null o el format de la data no es v�lid
	 */
public static Date obtenirMandatoryDateTime(String paramData, String paramHora, HttpServletRequest request) 
		throws ParameterException {
	// Creem un objecte SimpleDateFormat
	SimpleDateFormat sdf = new SimpleDateFormat();
	
	// Agafem la data (sense hora) del formulari
	Date dData = getMandatoryDate(paramData, request);
	
	String sData = "";
	if (dData != null) {
		// Transformem la data en String
		sdf.applyPattern("yyyy-MM-dd");
		sData = sdf.format(dData);
	} 
	
	// Agafem la hora del formulari
	Date dHora = getNonMandatoryTime(paramHora, request);
			
	String sHora = "";
	if (dHora != null) {
	// Transformem la hora en String
	sdf.applyPattern("HH:mm:ss");
	sHora = sdf.format(dHora);
	}
	
	// Unim data y hora en un sol String
	String sDataIHora = sData + " " + sHora;
	
	// Mirem la llargada del String
	int l = sDataIHora.length();

	// Transformem el String DataIHora en una Data
	Date dDataIHora = null;
	
	// Si el string cont� nomes la data (perqu� la hora es null), li appliquem el pattern yyyy-MM-dd 
	// Aixo al parsejar no obtindrem null sin� la data amb hora 00:00 com hora per defecte
	if (l > 9 && l < 12) {
		sdf.applyPattern("yyyy-MM-dd");
		try {
			dDataIHora = sdf.parse(sDataIHora);
		} catch (ParseException e) {}
	} else {
		// Si el string cont� data i hora, li apliquem el pattern yyyy-MM-dd HH:mm:ss
		// Aixo obtindrem un objecte Date amb la data i hora que hem rebut pel formulari
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		try {
			dDataIHora = sdf.parse(sDataIHora);
		} catch (ParseException e) {}
	}
	// Si el string cont� nomes la hora, retornar� null
	return dDataIHora;
}
}

//	public static boolean getMandatoryBoolean(
//			String paramActiu,
//			HttpServletRequest request) throws ParameterException {
//		
//		String sParam = request.getParameter(paramActiu);
//		if (sParam == null || sParam.trim().equals("")) {
//			// Si no em passen el par�metre
//			// escric el missatge d'error i retorno
//			throw createParameterException("Cal indicar un Rol", paramActiu, sParam);
//		}
//		
//		// Validem
//		
//		
//		if (sParam.equals("true")) {
//			return true;
//		}
//		else return false;
//	} 
//}
	













