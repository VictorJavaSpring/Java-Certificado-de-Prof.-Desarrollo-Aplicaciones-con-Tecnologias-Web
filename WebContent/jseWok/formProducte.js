///**
// * Validacio del formulari de Productes
// */

//<div hidden="true"> is not allowed in HTML5.
//Correct would be either <div hidden> or <div hidden=""> or  
// <div hidden="hidden"> or case-insensitive and single quotes/unquoted variations of any of them.
// http://stackoverflow.com/questions/4139786/what-does-it-mean-in-html-5-when-an-attribute-is-a-boolean-attribute
// }
var nodes = {
		// elements id
		elementNom : "nom",
		elementInVig : "inVig",
		elementInVigTime : "inVigTime",
		elementInVigDateTime : "",
		elementFiVig : "fiVig",
		elementFiVigTime : "fiVigTime",
		elementFiVigDateTime : "",
		elementIdUnitat : "idUnitat",
		elementIdTipusProducte : "idTipusProducte",
		
		// errors
		errorNom : "errorNom",
		errorIniciVigencia : "errorIniciVigencia",
		errorIniciVigenciaTime : "errorIniciVigenciaTime",
		errorIniciVigenciaSenseData : "errorIniciVigenciaSenseData",
		errorFiVigencia : "errorFiVigencia",
		errorFiVigenciaTime : "errorFiVigenciaTime",
		errorFiVigenciaSenseData : "errorFiVigenciaSenseData",
		errorIdUnitat : "errorIdUnitat",
		errorIdTipusProducte : "errorIdTipusProducte",
		DataInVig : false,
		DataFiVig : false,
		HoraInVig: false,
		HoraFiVig : false

}

function isFormValid(){
	var Error=false;
	
	//Esborro els missatges dels camps.
	putError(nodes.errorNom,true);
	putError(nodes.errorIniciVigencia,true);
	putError(nodes.errorIniciVigenciaTime,true);
	putError(nodes.errorIniciVigenciaSenseData,true);
	putError(nodes.errorFiVigencia,true);
	putError(nodes.errorFiVigenciaTime,true);
	putError(nodes.errorFiVigenciaSenseData,true);
	putError(nodes.errorIdUnitat,true);
	putError(nodes.errorIdTipusProducte,true);
	
	//Comprovo nom NOTNULL
	if(isStrOK(document.getElementById(nodes.elementNom).value) == false){
		putError(nodes.errorNom,false);
		Error = true;
	}

	//Comprovo la IdUnitat NOTNULL
	// Validem q no es cadena buida ni isNan(parseInt(valor))  amb el isNumOK
	if(isNumOK(document.getElementById(nodes.elementIdUnitat).value) == false){
		// si es veritat que no es un numero, printem error
		putError(nodes.errorIdUnitat,false);
		Error = true;
		} else { 
			// Si es un numero, validem rang de Id's Unitats
			// convertim en numero
			var valor = parseInt(document.getElementById(nodes.elementIdUnitat).value);
			// mirem si esta en el rang
			if (valor < 1 || valor > 10) {
				// si no esta en el rang, printem error
				putError(nodes.errorIdUnitat,false);
				Error = true;
				} 
			} // tot ha anat be amb el rang del valor, seguim comprovant el formulari.
	
	
	// Comprovo la idTipusProducte NOTNULL
	if(isNumOK(document.getElementById(nodes.elementIdTipusProducte).value) == false){
		putError(nodes.errorIdTipusProducte,false);
		Error = true;
	}
	
	// Comprovo la InVig date només si hi ha dades
	// mirem si hi ha dades () si no es null ni cadena buida
	if (isStrOK(document.getElementById(nodes.elementInVig).value) == true) {
		// Si hi ha dades, mirem si hi ha errors
		if(isDateOK(document.getElementById(nodes.elementInVig).value) == false){
			putError(nodes.errorIniciVigencia,false);
			Error = true;
			}	
		nodes.DataInVig = true;
		}
	
	// validem InVigTime de inici de vigencia
	if(isTimeOK(document.getElementById(nodes.elementInVigTime).value) == true) {
		// si hi ha hora i és vàlida, mirem si hi ha data
		if (nodes.DataInVig == false) {
			putError(nodes.errorIniciVigenciaSenseData,false);
			Error = true;
			}
		}

	// Comprovo la FiVig date només si hi ha dades
	var sdataFiVig = document.getElementById(nodes.elementFiVig).value
	if (isStrOK(sdataFiVig) == true) {
		// Mirem si hi ha error
		if(isDateOK(sdataFiVig) == false){
			putError(nodes.errorFiVigencia,false);
			Error = true;
			}
		if (existeixData(sdataFiVig)){
			nodes.DataFiVig = true;
			} else {
				putError(nodes.errorFiVigencia,false);
				Error = true; 
				}
	}
	
	// Comprovo la FiVigTime 
	if(isTimeOK(document.getElementById(nodes.elementFiVigTime).value) == true){
		// si hi ha hora i és vàlida, mirem si hi ha data
		if (nodes.DataInVig == false) {
			putError(nodes.errorFiVigenciaSenseData,false);
			Error = true;
			}
		}
	
	// Si tinc Error cancel.lo el submit
	if(Error){
		return false;
		}
	// Si no hi ha Error fem el submit
	return true;
	}

// FUNCIONS PER VALIDAR DADES 
// valida nom
function isStrOK(str){
	if (str.trim() == null || str.trim() == "")	return false;
    return true;
}

// valida Numero
function isNumOK(str){
	if(str == null || str == "" || str.length == 0) return false; // si es cadena buida, false
	// Intentem parsejar el valor
    var valor = parseInt(str);
    // "123456asd": este String retorna el número 123456
    // "": este String vacío retorna NaN
    // " 123 123 asd" este String (que contiene espacios y letras) retorna el número 123
	//    Retornará un número válido si: El String empieza por un número.
	//    El String empieza por espacio(s) seguido de un número.
	//    Ejemplos de números válidos: "123456"
	//    " 123456"
	//    "12345asdasd"
	//    " 12345 asdd"
	//    Todo String que cumpla con las 2 reglas anteriores (ser un número válido), será truncado: cuando se encuentre una letra, espacio o caracteres especiales (comas, acentos,...) dentro del String. Como resultado, retornará los dígitos que estén más a la izquierda de la primera letra (espacio o caracter) encontrada.
	//    Ejemplos de números válidos truncados: "123456" retorna como resultado 123456
	//    " 123456" retorna como resultado 123456
	//    "12345asdasd" retorna como resultado 12345
	//    " 123.. asdd" retorna como resultado 123
    
    // Comprobem si es un valor numéric 
    if (isNaN(valor)) {
	//    	isNaN(3);          // false
	//    	isNaN("3");        // false
	//    	isNaN(3.3545);     // false
	//    	isNaN(32323.345);  // false
	//    	isNaN(+23.2);      // false
	//    	isNaN("-23.2");    // false
	//    	isNaN("23a");      // true
	//    	isNaN("23.43.54"); // true

    // si no és un numero retornem false
    	return false;
    	}
    return true;
}

// valida format data
function isDateOK(str) {
	// validar el format data ...
	var RegExPattern = /^\d{2,4}\-\d{1,2}\-\d{1,2}$/;
    if ((str.match(RegExPattern)) && (str!='')) {
          return true;
    } else {
          return false;
    }
}

//valida es una data real
function existeixData(data){
    var dataf = data.split("-");
//    var day = dataf[0];
//    var month = dataf[1];
//    var year = dataf[2];
    var year = dataf[0];
    var month = dataf[1];
    var day = dataf[2];
    var date = new Date(year,month,'0');
    if((day-0)>(date.getDate()-0)){
          return false;
    }
    return true;
}

// valida format Hora HH:mm
function isTimeOK(hora){
	// validem la llargada
	if (hora.length != 5 ) 	return false;
		//valida Hora real de 00:00 a 23:59
		var a=hora.charAt(0); //<=2 
		var b=hora.charAt(1); //<4 
		var c=hora.charAt(2); //: 
		var d=hora.charAt(3); //<=5 
		if ((a==2 && b>3) || (a>2)) { 
			return false; 
		} 
		if (d>5) { 
		return false; 
		}
		if (c!=':') { 
			return false;
		}
	return true;

}
// funcio per cambiar la variable booleana hidden en el div del error
function putError(node,hidden){
	var div = node + "Div";// errorNomDiv errorIdUnitatDiv
	document.getElementById(div).hidden = hidden;
	return;
}