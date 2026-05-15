/**
 * Funcions javascript.
 */

/** Constants **/
elementUsuari = "usuari";
errorUsuari = "errUsuari";
elementPwd = "pwd";
errorPwd = "errPwd";

/**
 * Funcio per agafar els valors dels camps de usuari i pass
 * i cridar la validacio
 */
function isLoginValid(){
	var Error=false;
	
	//Esborro els missatges dels camps.
	putError(errorUsuari,true);
	putError(errorPwd,true);
	
	
	//Comprovo l'usuari
	if(isUsuariOK(document.getElementById(elementUsuari).value) == false){
		putError(errorUsuari,false);
		Error = true;
	}

	//Comprovo la contrasenya
	if(isPwdOK(document.getElementById(elementPwd).value) == false){
		putError(errorPwd,false);
		Error = true;
	}
	//Si tinc Error cancel.lo el submit
	if(Error){
		return false;
	}
	return true;
}

/**
 * Funcio per comprovar la validacio de l'usuari
 * @param str el valor del camp d'usuari
 * @returns {Boolean} L'exit de la comprovació
 */
function isUsuariOK(str){
	if (str.trim() == null || str.trim() == "")	return false;
	
	var atPos = false;
    var dotPos = false;
    atPos = str.indexOf("@");
    dotPos = str.lastIndexOf(".");	  
    if (atPos<0 || dotPos<0 || dotPos < atPos) return false;
    return true;
}
/**
 * Funcion per comprovar la valiació de la contrasenya
 * @param str el valor del camp de contrasenya
 * @returns {Boolean} L'exit de la comprovació
 */
function isPwdOK(str){
	if(str == null || str == "") return false;
	return true;
}
/**
 * Fica un missatge al node
 * @param node on surt el missatge
 * @param msg el missatge
 */
function putError(node,hidden){
	var div = node+"Div";
	document.getElementById(div).hidden = hidden;
	return;
}

