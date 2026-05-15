/**
 * Validació per javascript del formulari d'unitat (formUnitat).
 */ 

// Defineixo els nodes 
var nodes = {
		// Node del camp nom
		nom : "nom",
		// Node d'error del nom (span)
		errorNom : "errorNomSpan",
		// Node del contenidor (div) de l'error del nom:
		contErrorNom : "errorNomDiv",
		acronim : "acron",
		errorAcronim : "errorAcronSpan",
		contErrorAcronim : "errorAcronDiv"
}


/**
 * Funció per validar els camps de nom i acrònim
 */
function valida(){
	// Mostro un missatge d'error per consola
	// per controlar l'inici de l'execució de "valida()"
	console.log("Inici de la validació per javascript");
	
	// Declaro una variable d'error
	var isError = false;
	
	// Obtinc els valors dels nodes (inputs de nom i d'acrònim)
	var valorNom = document.getElementById(nodes.nom).value;
	var valorAcron = document.getElementById(nodes.acronim).value;
	
	// Amago els camps dels missatges d'errors
	putError(nodes.contErrorNom, true);
	putError(nodes.contErrorAcronim, true);
	
	// Comprovo el nom i l'acrònim
	if(isNomOK(valorNom) == false){
		// mostro el missatge del camp error
		putError(nodes.contErrorNom, false);
		isError = true;
	} 
	
	if(isAcronOK(valorAcron) == false){
		putError(nodes.contErrorAcronim, false);
		isError = true;
	} 
	
	// Mostro un missatge d'error per consola
	// per controlar la fi de l'execució de "valida()"
	console.log("Fi de la validació per javascript");
	
	// Si tinc error cancel·lo el submit
	// (El sentit de la validació és contrari al valor de l'error "isError")
	
	return !isError;
}

/**
 * Funció genèrica per validar un string 
 * @param str   El valor de l'string
 * @returns {Boolean}   El que es retorna finalment ("true" => validació Ok)
 */
function isStringOK(str){
	if(str.trim() == null || str.trim() == "") {
		return false;
	}
	return true;
}

/**
 * Funció específica per validar un nom
 * @param strNom   El valor del camp nom
 */
function isNomOK(strNom){
	return isStringOK(strNom);
}

/**
 * Funció específica per validar un acrònim
 * @param strAcron  El valor del camp acrònim
 */
function isAcronOK(strAcron){
	return isStringOK(strAcron);
}

/**
 * Introdueix un missatge (d'error) al node d'error (span)
 * @param node   On es mostra el missatge 
 * @param msg    El missatge
 */
function putError(nodeContError, estatHidden){
	document.getElementById(nodeContError).hidden = estatHidden;
	return;
}

