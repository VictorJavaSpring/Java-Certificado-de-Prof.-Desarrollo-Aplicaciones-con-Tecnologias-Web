package com.soc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe d'utilitats per extraure paràmetres del request
 *
 */
public class RequestValidationUtils {
	
	/**
	 * Busca un paràmetre de tipus string i obligatori
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi
	 * el paràmetre o sigui espais en blanc
	 */
	public static String getMandatoryString(
			String paramName,
			HttpServletRequest request
			) throws ParameterException {
		
		// Intento obtenir el paràmetre demanat
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// Si detecto un error, construeixo una excepció i la llenço
			throw createParameterException("S'ha de introduir una cadena de texte. ", paramName, str);
//			throw createParameterException("No s'ha trobat el paràmetre indicat", paramName, str);
		}
		// Si tot ha anat bé, retorno el valor trobat
		return str;
	}
	
	/**
	 * Busca un paràmetre de tipus String i no obligatori
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor String trobat o null
	 */
	public static String getNonMandatoryString(
			String paramName,
			HttpServletRequest request ) {

		// Intento obtenir el paràmetre demanat
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// No hi ha error, però no tinc valor a retornar
			return null;
		}
		// No hi ha error i tinc valor per retornar
		return str;
	}
	
	/**
	 * Busca un paràmetre de tipus Long i no obligatori
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que es trobi el valor
	 * però no pugui ser convertit a long
	 */
	public static Long getNonMandatoryLong(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// No hi ha error, però no tinc valor a retornar
			return null;
		}
		Long l;
		try {
			l = Long.parseLong(str);
		} catch(NumberFormatException ex) {
			// Hi ha error
			throw createParameterException("El valor no s'ha pogut convertir a un número vàlid", paramName, str);
		}
		// No hi ha error i tinc valor per retornar
		return l;
	}
	
	/**
	 * Busca un paràmetre de tipus Long i obligatori
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o bé que
	 * es trobi però no pugui ser convertit a long
	 */
	public static Long getMandatoryLong(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el paràmetre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el paràmetre
			// escric el missatge d'error i retorno
			throw createParameterException("No s'ha trobat el paràmetre indicat", paramName, sParam);
		}
		Long nValor;
		try { 
			// Intentem parsejar el número
			nValor = Long.parseLong(sParam);
		} catch(NumberFormatException e) {
			// Si no ho aconseguim, cal indicar l'error i acabar
			throw createParameterException("El valor no s'ha pogut convertir a un número vàlid", paramName, sParam);
		}
		// Si tot ha anat bé, retornem el valor obtingut
		return nValor;
	}

	/**
	 * Busca un paràmetre de tipus float i no obligatori
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que es trobi el valor
	 * però no pugui ser convertit a float
	 */
	public static Float getNonMandatoryFloat(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		String str = request.getParameter(paramName);
		if (str == null || str.trim().equals("")) {
			// No hi ha error, però no tinc valor a retornar
			return null;
		}
		Float f;
		try {
			f = Float.parseFloat(str);
		} catch(NumberFormatException ex) {
			// Hi ha error
			throw createParameterException("El valor no s'ha pogut convertir a un número vàlid", paramName, str);
		}
		// No hi ha error i tinc valor per retornar
		return f;
	}
	
	/**
	 * Busca un paràmetre de tipus Float i obligatori
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o bé que
	 * es trobi però no pugui ser convertit a float
	 */
	public static Float getMandatoryFloat(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el paràmetre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el paràmetre
			// escric el missatge d'error i retorno
			throw createParameterException("No s'ha trobat el paràmetre indicat", paramName, sParam);
		}
		Float nValor;
		try { 
			// Intentem parsejar el número
			nValor = Float.parseFloat(sParam);
		} catch(NumberFormatException e) {
			// Si no ho aconseguim, cal indicar l'error i acabar
			throw createParameterException("El valor no s'ha pogut convertir a un número vàlid", paramName, sParam);
		}
		// Si tot ha anat bé, retornem el valor obtingut
		return nValor;
	}
	
	/**
	 * Funció de soport per crear una excepció de paràmetre
	 * @param message El missatge explicatiu
	 * @param paramName El paràmetre buscat
	 * @param paramValue El valor trobat o null si no se n'ha trobat
	 * @return L'excepció generada
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
	 * Busca un paràmetre de tipus Date i obligatori
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o bé que
	 * es trobi però no pugui ser convertit a Date
	 */
	public static Date getMandatoryDate(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el paràmetre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el paràmetre
			// escric el missatge d'error i retorno
			throw createParameterException("Cal indicar una data", paramName, sParam);
		}
		// Validem el format de la data
		Date dParam = null;	    
	    try {
	    	dParam = new SimpleDateFormat("yyyy-MM-dd").parse(sParam.trim());
	    } catch (ParseException pe) {
	    	// Si no ho aconseguim, indiquem l'error i acabem
	    	throw createParameterException("La data ha de tenir un format vàlid (dd-mm-yyyy)", paramName, sParam);	     
	    }

	    return dParam;
	  }
	
	/**
	 * Rep un Date obligatori i torna true si és vàlida i fals si no
	 * @param paramName El nom del paràmetre a buscar
	 * @param request La request actual
	 * @return El valor trobat
	 * @throws ParameterException En cas que no es trobi o el valor o bé que
	 * es trobi però no pugui ser convertit a Date
	 */
	public static boolean isMandatoryDateValid(
			String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el paràmetre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// Si no em passen el paràmetre
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
	    	throw createParameterException("La data ha de tenir un format vàlid (dd-MM-yyyy)", paramName, sParam);
	     
	    }
	    return true;
	  }
	/**
	 * 
	 * @param paramName Nom del paràmetre a buscar al request
	 * @param request La request actual
	 * @return Una Date o null
	 * @throws ParameterException Si el format de la data no és vàlid
	 */
	public static Date getNonMandatoryDate(String paramName,
			HttpServletRequest request) throws ParameterException {
		// Obtenim el paràmetre desitjat
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
	    	throw createParameterException("La data ha de tenir un format vàlid (yyyy-MM-dd)", paramName, sParam);	     
	    }
	    return dParam;
	}
	
	/**
	 * 
	 * @param paramName Nom del paràmetre a buscar al request
	 * @param request La request actual
	 * @return Una Date o null
	 * @throws ParameterException Si el format de la data no és vàlid
	 */
	public static Date getNonMandatoryTime(String paramName,
			HttpServletRequest request) throws ParameterException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		// Obtenim el paràmetre desitjat
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
	    	throw createParameterException("L'hora ha de tenir un format vàlid (HH:mm)", paramName, sParam);	     
	    }
	    return dParam;
	}
	
	/**
	 * 
	 * @param paramName Nom del paràmetre a buscar al request
	 * @param request La request actual
	 * @return Una Date
	 * @throws ParameterException Si és null o el format de la data no és vàlid
	 */
	public static Date getMandatoryTime(String paramName,
			HttpServletRequest request) throws ParameterException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		// Obtenim el paràmetre desitjat
		String sParam = request.getParameter(paramName);
		if (sParam == null || sParam.trim().equals("")) {
			// No ens passen el paràmetre
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
	    	throw createParameterException("L'hora ha de tenir un format vàlid (HH:mm)", paramName, sParam);	     
	    }
	    return dParam;
	}
	
		/**
		 * 
		 * @param paramData Nom del paràmetre per buscar la data al formulari
		 * @param paramHora Nom del paràmetre per buscar la hora al formulari
		 * @param request La request actual
		 * @return null o un Date: data + hora (si no ens informen de la hora, retornarà 00:00 per defecte)
		 * @throws ParameterException Si el format de la data no és vàlid
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
		
		// Si el string conté només la data (perquè la hora és null), li appliquem el pattern yyyy-MM-dd 
		// així al parsejar no obtindrem null sino la data amb hora 00:00 com hora per defecte
		if (l > 9 && l < 12) {
			sdf.applyPattern("yyyy-MM-dd");
			try {
				dDataIHora = sdf.parse(sDataIHora);
			} catch (ParseException e) {}
		} else {
			// Si el string conté data i hora, li apliquem el pattern yyyy-MM-dd HH:mm:ss
			// així obtindrem un objecte Date amb la data i hora que hem rebut pel formulari
			sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
			try {
				dDataIHora = sdf.parse(sDataIHora);
			} catch (ParseException e) {}
		}
		// Si el string conté només la hora, retornarà null
		return dDataIHora;
	}
	
	/**
	 * 
	 * @param paramData Nom del paràmetre per buscar la data al formulari
	 * @param paramHora Nom del paràmetre per buscar la hora al formulari
	 * @param request La request actual
	 * @return un Date: data + hora (si no ens informen de la hora, retornarà 00:00 per defecte)
	 * @throws ParameterException Si és null o el format de la data no és vàlid
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
	
	// Si el string conté només la data (perquè la hora és null), li appliquem el pattern yyyy-MM-dd 
	// així al parsejar no obtindrem null sinó la data amb hora 00:00 com hora per defecte
	if (l > 9 && l < 12) {
		sdf.applyPattern("yyyy-MM-dd");
		try {
			dDataIHora = sdf.parse(sDataIHora);
		} catch (ParseException e) {}
	} else {
		// Si el string conté data i hora, li apliquem el pattern yyyy-MM-dd HH:mm:ss
		// així obtindrem un objecte Date amb la data i hora que hem rebut pel formulari
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		try {
			dDataIHora = sdf.parse(sDataIHora);
		} catch (ParseException e) {}
	}
	// Si el string conté només la hora, retornarà null
	return dDataIHora;
}
}

//	public static boolean getMandatoryBoolean(
//			String paramActiu,
//			HttpServletRequest request) throws ParameterException {
//		
//		String sParam = request.getParameter(paramActiu);
//		if (sParam == null || sParam.trim().equals("")) {
//			// Si no em passen el paràmetre
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
	













