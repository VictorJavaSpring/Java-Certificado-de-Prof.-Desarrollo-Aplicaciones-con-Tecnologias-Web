/**
 * Funcions javascript.
 */

/** Constants **/
var nodes = {
// Constants Oferta de Punts 
eurosPerPunt : "eurosPerPunt",
errorEPP : "errorEPP",
puntsPerXec : "puntsPerXec",
errorPPX : "errorPPX",
diesVigenciaPunts : "diesVigenciaPunts",
errorDVP : "errorDVP",
diesVigenciaXecs : "diesVigenciaXecs",
errorDVX : "errorDVX",
iniciVigencia : "inVig",
errorInVig : "errorInVig",
inVigTime : "inVigTime",
errorInVigTime : "errorInVigTime",
fiVigencia : "fiVig",
errorFiVig : "errorFiVig",
fiVigTime : "fiVigTime",
errorFiVigTime : "errorFiVigTime"
};

/* validació no implementada

function valida() {
	
	
	
	if (validaBuit(nodes.eurosPerPunt) && validaBuit(nodes.puntsPerXec) && validaBuit(diesVigenciaPunts) && 
			validaBuit(diesVigenciaXecs) && validaBuit(inVig) && validaBuit(inVigTime)
			&& validaBuit(fiVig) && validaBuit(fiVigTime)) {
		return true;
	} 
	return false;
}

function validaBuit(idForm){
	// obtenim els noms del form
	var valor = documentidForm.value;
	//	nom = nom.value;
	// si el nom es buit donarem error
	var str = "";
	if (valor == "") {
		// ensenyem l´span error pero encara BUIT
		// construir el span amb la variable idForm : nom --> spanErrorNom
		str = "spanError" + idForm;
		str.hidden = false;
//		spanErrorNom.hidden = false;
		//es mostra el msg d´error internacionalitzat
		return false;
	}
	return true;
}

*/



/**
 * Funció per agafar els valors dels camps 
 * i cridar la validació
 */
function isOfertaPuntsValid(){	
	var Error=false;
	
	//Esborro els missatges dels camps.
	putError(nodes.errorEPP,true);
	putError(nodes.errorPPX,true);
	putError(nodes.errorDVP,true);
	putError(nodes.errorDVX,true);
	putError(nodes.errorInVig,true);
	putError(nodes.errorInVigTime,true);
	putError(nodes.errorFiVig,true);
	putError(nodes.errorFiVigTime,true);
	
	
	//Comprovo Euros Per Punt
	if(isDataOK(document.getElementById(nodes.eurosPerPunt).value) == false){
		putError(nodes.errorEPP,false);
		Error = true;
	}

	//Comprovo Punts Per Xec
	 if(isDataOK(document.getElementById(nodes.puntsPerXec).value) == false){
		putError(nodes.errorPPX,false);
		Error = true;
	}
	
	//Comprovo Dies Vigencia Punts
	 if(isDataOK(document.getElementById(nodes.diesVigenciaPunts).value) == false){
		putError(nodes.errorDVP,false);
		Error = true;
	}
	
	//Comprovo Dies Vigencia Xecs
	 if(isDataOK(document.getElementById(nodes.diesVigenciaXecs).value) == false){
		putError(nodes.errorDVX,false);
		Error = true;
	}
	
	//Comprovo Data d'inici de Vigencia
	 if(isDataOK(document.getElementById(nodes.iniciVigencia).value) == false){
		putError(nodes.errorInVig,false);
		Error = true;
	}
	
	//Comprovo Hora d'inici de Vigencia
	 if(isDataOK(document.getElementById(nodes.inVigTime).value) == false){
		putError(nodes.errorInVigTime,false);
		Error = true;
	}
	
	//Comprovo Data de fi de Vigencia
	 if(isDataOK(document.getElementById(nodes.fiVigencia).value) == false){
		putError(nodes.errorFiVig,false);
		Error = true;
	}
	
	//Comprovo Hora de fi de Vigencia
	 if(isDataOK(document.getElementById(nodes.fiVigTime).value) == false){
		putError(nodes.errorFiVigTime,false);
		Error = true;
	}
	
	//Si tinc Error cancel.lo el submit
	if(Error){
		return false;
	}
	return true;
}

/** NO IMPLEMENTADA
 * Funció per comprovar la validació dels camps numèrics not null
 * @param sNum el valor (string) del camp que validem
 * @returns {Boolean} L'exit de la comprovació
 
function isCampNumeric(sNum){
	if (sNum.trim() == null || sNum.trim() == "") return false;
	
	var nNum = parseInt(sNum);
	if (isNaN(nNum) == true || nNum != parseInt(sNum) || nNum < 1) {
		// nodes.errorNumero.innerHTML = "Cal un número vàlid";
		return false;
    
    return true;
	}
}
*/

/**
 * Funcion per comprovar la valiació de camps not null
 * @param str el valor del camp
 * @returns {Boolean} L'exit de la comprovació
 */
function isDataOK(str){
	if(str == null || str.trim() == "") return false;
	return true;
}
/**
 * Fica un missatge al node
 * @param node on surt el missatge
 * @param msg el missatge
 */
function putError(node, hidden){
	var div = node + "Div";
	document.getElementById(div).hidden = hidden;
	return;
	}

