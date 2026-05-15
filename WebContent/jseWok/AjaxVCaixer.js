/**
* Funcions js per la Vista del Caixer
*/

//variables globals per assignar els events d'Ajax
var req; 
var req2;
var req3;
var req4;
var req5;

// Objecte amb els valors de la comanda
var objComanda={
	Comanda:null,
	Linia:null,
	Estat:null,
	NodoComanda:null,
	NodoDelComanda:null,  // Node pel l'icone de borrar comanda
	LiniesComanda:null,
	NodoIconComanda:null  // Node del icone del estat de la comanda
};

// Objecte amb els valors de la Linia de comanda
var objLiniaComanda={
	lComanda:null,
	Estat:null,
	Linia:null,
	Nodo:null,
	NodoDelLinia:null,  // Node pel l'icone de borrar linia de comanda
	NodoIconLinia:null // Node del icone del estat de la linia de comanda
};

// Service per pintar la Taula de les linies de comanda de la comanda clickada
function requestLiniesComanda(Idcomanda){
	objComanda.Comanda = Idcomanda;
	req = new XMLHttpRequest();
	req.onreadystatechange = controlLListesLComanda;
	req.open("GET", "data/comanda/getLiniesComanda/" + objComanda.Comanda, true);
	req.send();
}

// Service per Canviar l'estat de la comanda clickada, si totes les seves linies de comanda s'han entregat
function canviEstatComanda(IdComanda){
	// inserim la llista de linies de comanda de la comanda clickada al jsp amb mode visual ocult
	// per poder contar les linies de comanda eentregades avans de canviar l'estat de la comanda a entregada
	objComanda.Comanda = IdComanda;  
	objComanda.NodoValue = document.getElementById("value_" + objComanda.Comanda);
	objComanda.NodoIcon = document.getElementById("icon_" + objComanda.Comanda);
	objComanda.Estat = objComanda.NodoValue.value;
	objComanda.NodoDel = document.getElementById("del_" + objComanda.Comanda);
	objComanda.NodoLinia = document.getElementById("estatComanda_" + objComanda.Comanda);
	objLiniaComanda.LiniesEntregades = 0;
	// si la comanda té l'estat pagada / preparadaIPagada, pot canviar a 
	// entregada si les seves linies de com estan entregats

	// AVANS--
//	if (objComanda.Estat == 'validada'){
//		objComanda.Estat = 'pagada';
//	}else if  (objComanda.Estat == 'preparada'){
//		objComanda.Estat = 'preparadaIPagada';
//	}else if  (objComanda.Estat == 'pagada'){
//				objComanda.Estat = 'entregada';
//	}else if  (objComanda.Estat = 'preparadaIPagada'){
//				objComanda.Estat = 'entregada';
//	}
	// FI AVANS ---
	
	
	// Enviem al webservice perque canvii l'estat a la bbdd getComanda/{id}/stat/{stat}
	req2 = new XMLHttpRequest();
	req2.onreadystatechange = controlEstatComanda;
	req2.open("GET", "data/comanda/getComanda/" + objComanda.Comanda + "/stat/" + objComanda.Estat , true);
	req2.send();
}

// Service per Canviar l'estat de la Linia de comanda clickada, 
// OJO!!i si totes les linies de comanda de la comanda estan en entregada, 
// canvia l'estat de la comanda a entregat ??? / o preparada per poder fer click per cambia l'estat a entregat ¿?¿?
function canviEstatLineaComanda(IdLcomanda,linia){
//	alert("En canvi Estat Linia de Comanda");
	objLiniaComanda.Id = IdLcomanda;
	objLiniaComanda.linia = linia;
	objLiniaComanda.NodoValue = document.getElementById("value_"+IdLcomanda+","+linia);
	objLiniaComanda.Estat = objLiniaComanda.NodoValue.value;
	objLiniaComanda.estatLComanda =  document.getElementById("estatLComanda_" +IdLcomanda+","+linia);

	switch(objLiniaComanda.Estat){
	case('preparat'):
		objLiniaComanda.Estat = 'entregat';
		break;
	}
	// Enviem al webservice perque canvii l'estat a la bbdd
	req4 = new XMLHttpRequest();
	req4.onreadystatechange = controlEstatLinia;
	req4.open("GET", "data/comanda/getLComanda/" + objLiniaComanda.Id + "/num/" + objLiniaComanda.linia + "/stat/" + objLiniaComanda.Estat, true);
	req4.send();
}

function canviEstatLineaComandaAEntregat(IdLcomanda,linia){
//	alert("En canvi Estat Linia de Comanda");
	objLiniaComanda.Id = IdLcomanda;
	objLiniaComanda.linia = linia;
	objLiniaComanda.NodoValue = document.getElementById("value_"+IdLcomanda+","+linia);
	objLiniaComanda.Estat = 'entregat';
	objLiniaComanda.estatLComanda =  document.getElementById("estatLComanda_" +IdLcomanda+","+linia);
	// Enviem al webservice perque canvii l'estat a la bbdd
	req6 = new XMLHttpRequest();
	req6.onreadystatechange = controlEstatLiniaEntregada;
	req6.open("GET", "data/comanda/getLComanda/" + objLiniaComanda.Id + "/num/" + objLiniaComanda.linia + "/stat/" + objLiniaComanda.Estat, true);
	req6.send();
}

// Service per obtenir les comandes amb estat validada o preparada
function requestLlistaComandesVP(){
	//alert("En requestLlistaComandesVP");
	req3 = new XMLHttpRequest();
	req3.onreadystatechange = controlComandesVP;
	req3.open("GET", "data/comanda/getComandesVP", true);
	req3.send();
}

//////////////

function controlLListesLComanda(){
	if (req.readyState == 4 && req.status == 200){
		veureLListesLComanda(req.responseText);
    }
}

function controlEstatComanda(){
	if (req2.readyState == 4 && req2.status == 200){
		canviAspecteEstatComanda(req2.responseText);
    }
}

function controlComandesVP(){
	if (req3.readyState == 4 && req3.status == 200){
		llistaComandesVP(req3.responseText);
	}
}

function controlEstatLinia(){
	if (req4.readyState == 4 && req4.status == 200){
		canviaAspecteEstatLiniaComanda(req4.responseText);
    }
}

function controlEstatLiniaEntregada(){
	if (req6.readyState == 4 && req6.status == 200){
		canviaAspecteEstatLiniaComanda(req6.responseText);
		// canviaLCEntregada(req6.responseText);
	    }
}

//////////////////////////////
function veureLListesLComanda(data){
	//	{"liniaComanda": [{"estat":"enPreparacio","id":"1","idProducte":"1","linia":"1","preuBrut":"1.11","preuVenda":"1.11","quantitat":"11"}
	//	,{"estat":"enPreparacio","id":"1","idProducte":"2","linia":"2","preuBrut":"2.22","preuVenda":"2.22","quantitat":"22"}
	//	]}
	var TbodyLC = "";
	// obtenim les dades en format JSON 
	var dadesRebudes = JSON.parse(data); // [object Object]
	// alert ("data " + data);
	// si hi han dades pintem la taula
	// Canviem l'estil del Thead de la taula de les Linees de Comanda de style="display:none;" a inline
	document.getElementById("TaulaLC").style.display = 'inline-block';
	document.getElementById("TbodyLC").style.display = 'table-row-group';
	
	if (dadesRebudes != null){
		// fiquem el color de fons de tots els tr de id=TbodyC_[id comanda] a blanc
		colorFonsBlanc();
		// obtenim les dades en forma de llista JSON de les Linies de comanda de la comanda actual
		var arrayLineas = dadesRebudes.liniaComanda;
		// Mirem si només conte una linia de comanda, quan només es una linia de comanda no te propietat lenght!!
		// i a  més, l'arrayLineas es directament l'objecte, així que no es pot recorrer: arrayLineas[x] = undefined
		// Si hi ha més d'una linia, el seu length serà true, sino, es un objecte
		if (arrayLineas.length){
			// Obtenim Id comanda / Linia comanda
			var idComanda = arrayLineas[0].id;
			// Posem el fons en color de select Comanda seleccionada  238, 232, 170
			colorFonsSelect(idComanda);
			// construim un array per introduir la cadena de texte de la taula de linies de comanda tBodyLC_id
			var numLin = arrayLineas.length;
			var strArray = new Array(numLin);
			strArray[0] ="";
			objLiniaComanda.LiniesEntregades = 0;
			// recorrem la array de Linies de comanda pel seu index i obtenim les id de producte i de Linia de comanda
			for (var x=0;x < numLin;x++){
				var producteId = arrayLineas[x].idProducte;
				var lComandaId = arrayLineas[x].id;
				var lComandaEstat = arrayLineas[x].estat;
				var lComandaLinia = arrayLineas[x].linia;
				var lComandaNomProd = arrayLineas[x].producte.nom;
				var lComandaTipusProd = arrayLineas[x].producte.idTipusProducte;
				// construim la taula TbodyLC_id_liniadecomanda/comanda de Linies de comanda 
				// si tipus producte == plt 1: inicial o entregat :sense botó, preparat amb botó a entregat
				// si tipus producte == art 3: inicial amb botó a entregat
				
				strArray[x] = "<tr id='linia_" + lComandaId + "," + lComandaLinia + "'><td>" + lComandaLinia + "</td>";
				strArray[x] += "<td><img class='img-responsive' width='100' height='100' src='img?accioFoto=get&idProd="+ producteId + "' alt='" + producteId + "' /></a></td>";
				strArray[x] += "<td id='producteNom' >" + lComandaNomProd + "</td><td id='estatLComanda_" + lComandaId  + "," + lComandaLinia + "'  value='" + lComandaEstat +  "' >" + lComandaEstat + "</td><td>" + arrayLineas[x].preuVenda + "</td><td>" + arrayLineas[x].quantitat + "</td>";
				var strAccioEstatLinia = "";

				switch(lComandaEstat){
				case('inicial'):
					if (lComandaTipusProd == '3'){
						strAccioEstatLinia +="<button onclick='canviEstatLineaComandaAEntregat(" + lComandaId + "," + lComandaLinia + ")'><i  class='fa fa-spinner'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i></button>";
					} else {
						strAccioEstatLinia +="<i  class='fa fa-spinner'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
					}
					strAccioEstatLinia += "<input name= 'liniaComanda' type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
					break;
				case('enPreparacio'):
//					strAccioEstatLinia +="<button onclick='canviEstatLineaComanda(" + lComandaId + "," + lComandaLinia + ")'><i  class='fa fa-spinner fa-spin'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i></button>";
					strAccioEstatLinia +="<i  class='fa fa-spinner fa-spin'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
					strAccioEstatLinia += "<input name= 'liniaComanda' type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
					break;
				case('preparat'):
					if (lComandaTipusProd == '1'){
						strAccioEstatLinia +="<button onclick='canviEstatLineaComanda(" + lComandaId + "," + lComandaLinia + ")'><i  class='fa fa-check'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i></button>";
					} else {
						strAccioEstatLinia +="<i  class='fa fa-check'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
					}
					strAccioEstatLinia += "<input name= 'liniaComanda' type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
					break;
				case('entregat'):
					strAccioEstatLinia +="<i  class='fa fa-paper-plane'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
					strAccioEstatLinia += "<input name= 'liniaComanda' type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
					break;
				}
				strArray[x] += "<td>" + strAccioEstatLinia + "</td>";
			}
			// Juntem les arrays de Linies de comanda obtingudes 
			for (var x=0;x < strArray.length;x++){
				TbodyLC += strArray[x];
			}
			// si arrayLineas.length es false, arrayLineas es un objecte d'una sola Linia de comanda amb els seus camps
		} else {
			// obtenim idProducte ,lComandaId, lComandaLinia i lComandaEstat 
			var producteId = arrayLineas.idProducte;
			var lComandaId = arrayLineas.id;
			var lComandaLinia = arrayLineas.linia;
			// var lComandaEstat = arrayLineas.AliasEstatLC;
			var lComandaEstat = arrayLineas.estat;
			var lComandaNomProd = arrayLineas.producte.nom;
			var lComandaTipusProd = arrayLineas.producte.idTipusProducte;
			// canviem l'estil del TbodyC_[idLiniaComanda] seleccionat
			colorFonsSelect(lComandaId);
			var arrayLineas = dadesRebudes.liniaComanda;
			var nodeTbodyC = document.getElementById('TbodyC_' + lComandaId);
			// construim la cadena de texte del TbodyLC
			TbodyLC += "<tr id='linia_" + lComandaId + "," + lComandaLinia + "'><td>" + arrayLineas.linia + "</td><td>" + 
			"<img class='img-responsive' width='100' height='100' src='img?accioFoto=get&idProd=" + producteId + "' alt='" + producteId + "' /></a>"
			+ "</td>";
			TbodyLC += "<td id='producteNom' >" + lComandaNomProd + "</td><td id='estatLComanda_" + lComandaId +"," + lComandaLinia + "'  value='" + lComandaEstat +  "' >" + arrayLineas.estat + "</td><td>" + arrayLineas.preuVenda + "</td><td>" + arrayLineas.quantitat + "</td>";
			var strAccioEstatLinia = "";

			switch(lComandaEstat){
			case('inicial'):
				if (lComandaTipusProd == '3'){
					strAccioEstatLinia +="<button onclick='canviEstatLineaComandaAEntregat(" + lComandaId + "," + lComandaLinia + ")'><i  class='fa fa-spinner'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i></button>";
				} else {
					strAccioEstatLinia +="<i class='fa fa-spinner'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
				}
				strAccioEstatLinia += "<input name= 'liniaComanda'  type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
				break;
			case('enPreparacio'):
				strAccioEstatLinia +="<i class='fa fa-spinner fa-spin'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
				strAccioEstatLinia += "<input name= 'liniaComanda'  type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
				break;
			case('preparat'):
				if (lComandaTipusProd == '1'){
					strAccioEstatLinia +="<button onclick='canviEstatLineaComanda(" + lComandaId + "," + lComandaLinia + ")'><i  class='fa fa-check'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i></button>";
				} else {
					strAccioEstatLinia +="<i  class='fa fa-check'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
				}
				strAccioEstatLinia += "<input name= 'liniaComanda' type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
				break;
			case('entregat'):
				strAccioEstatLinia +="<i class='fa fa-paper-plane'  id='icon_" + lComandaId + "," + lComandaLinia + "'></i>";
				//strAccioEstatLinia += "<input name= 'liniaComanda' type='text' hidden='true' id='value_" +lComandaId + "," + lComandaLinia + "' value='" + lComandaEstat + "'/>";
				break;
			}
			
			TbodyLC += "<td>" + strAccioEstatLinia + "</td></tr>";
		}
		if (numLin == objLiniaComanda.LiniesEntregades){
			objComanda.checkEntregat = true;
		} else objComanda.checkEntregat = false;
		// Canviem l'estil del TaulaLC de la taula de les Linees de Comanda de style="display:none;" a inline
		document.getElementById("TaulaLC").style.display = 'inline';
		// pintem la Taula de linees de comanda
		document.getElementById("TbodyLC").innerHTML=TbodyLC;
	} // -->	if (dadesRebudes != null)
	return objComanda.checkEntregat;
}

function colorFonsBlanc() {
	//alert("entrem en colorFonsBlanc");
	var elements = document.getElementsByClassName("actiu");
    for (var i = 0; i < elements.length; i++) {
    	var strId = elements[i].id;
    	var strNode = document.getElementById(strId);
    	var a = 255;
		var b = 255;
		var c = 255;
		strNode.style.backgroundColor = 'rgb(' + a + ',' + b + ',' + c + ')';
		// canviem la classe a normal
		strNode.classList.remove("actiu");
		strNode.classList.add("normal");
    }
}

function colorFonsSelect(idComanda){
	//	alert("entrem en colorFonsSelect");
	var str = "TbodyC_" + idComanda;
	var nodeTbodyC = document.getElementById(str);
	var a = 238;
	var b = 232;
	var c = 170;
	nodeTbodyC.style.backgroundColor = 'rgb(' + a + ',' + b + ',' + c + ')';
	// canviem la classe a actiu millor addclass removeclass:
	nodeTbodyC.classList.remove("normal");
	nodeTbodyC.classList.add("actiu");
}

function llistaComandesVP(data) {
	var dadesRebudes = JSON.parse(data);
	if(dadesRebudes != null){
		var llistaComandesVP = dadesRebudes.Comanda;
		// var numComandesVP = llistaComandesVP.length;
		}
	return llistaComandesVP;
}

function canviAspecteEstatComanda(data){
//data = "" +
//"{"adreca":"10","comentaris":"Timo de comentaris1","data":"1974-03-22T01:02:03+01:00","descompte":"15.0","estatComanda":"validada","id":"4","idClient":"1","preuFinal":"8.2","punts":"5"}"
//dadesRebudes = 
//Object {adreca: "10", comentaris: "Timo de comentaris1", data: "1974-03-22T01:02:03+01:00", descompte: "15.0", estatComanda: "validada"…}
	//	alert(data);
	var dadesRebudes = JSON.parse(data);
	if(dadesRebudes){
		var status = null;
		status = dadesRebudes.estat;
		var IdComanda = dadesRebudes.id;
	}
	// Segons l'estat, cambio l'icone 
	objComanda.NodoValue = document.getElementById("value_" + IdComanda);
	objComanda.NodoEstat = document.getElementById("estatComanda_" + IdComanda);

	switch(status){
//		case('enConstruccio'):
//			objComanda.NodoIcon.className="fa fa-wrench";
//			break;
	case('validada'):
		objComanda.NodoIcon.className="fa fa-spinner"; // fa-spinner
		objComanda.NodoValue.value = "validada";
		objComanda.NodoEstat.innerHTML = "validada";
		break;
	case('pagada'):
		objComanda.NodoIcon.className="fa fa-paper-plane-o";
		objComanda.NodoValue.value = "pagada";
		objComanda.NodoEstat.innerHTML = "pagada";
		break;		
	case('preparada'):
		objComanda.NodoIcon.className="fa fa-check"; //fa-check
		objComanda.NodoValue.value = "preparada";
		objComanda.NodoEstat.innerHTML = "preparada";
		break;
	case('preparadaIPagada'):
		objComanda.NodoIcon.className="fa fa-paper-plane-o";
		objComanda.NodoValue.value = "preparadaIPagada";
		objComanda.NodoEstat.innerHTML = "preparadaIPagada";
		break;
	case('entregada'):
		objComanda.NodoIcon.className="fa fa-check-square-o";
		objComanda.NodoValue.value = "entregada";
		objComanda.NodoEstat.innerHTML = "entregada";
		break;
	}
	// fico el valor del nou estat
	objComanda.NodoValue.value = status;
}

function canviaAspecteEstatLiniaComanda(data){
	//data = "" +
	//"{"adreca":"10","comentaris":"Timo de comentaris1","data":"1974-03-22T01:02:03+01:00","descompte":"15.0","estatComanda":"validada","id":"4","idClient":"1","preuFinal":"8.2","punts":"5"}"
	//dadesRebudes = 
	//Object {adreca: "10", comentaris: "Timo de comentaris1", data: "1974-03-22T01:02:03+01:00", descompte: "15.0", estatComanda: "validada"…}
		var dadesRebudes = JSON.parse(data);
		var status = null;
		if(dadesRebudes){
			var status = null;
			status = dadesRebudes.estat;
			var IdLcomanda = dadesRebudes.id;
			var linia = dadesRebudes.linia;
		}
		objLiniaComanda.NodoIcon = document.getElementById("icon_"+IdLcomanda+","+linia);
		objLiniaComanda.NodoEstat = document.getElementById("estatLComanda_"+IdLcomanda+","+linia);
		// Segons l'estat, cambio l'icone 
		switch(status){
		case('inicial'):
			objLiniaComanda.NodoIcon.className="fa fa-spinner";
			objLiniaComanda.NodoValue.value = "inicial";
			objLiniaComanda.NodoEstat.innerHTML = "inicial";
			break;
		case('enPreparacio'):
			objLiniaComanda.NodoIcon.className="fa fa-spinner  fa-spin";
			objLiniaComanda.NodoValue.value = "enPreparacio";
			objLiniaComanda.NodoEstat.innerHTML = "enPreparacio";
			break;
		case('preparat'):
			objLiniaComanda.NodoIcon.className="fa fa-check";
			objLiniaComanda.NodoValue.value = "entregat";
			objLiniaComanda.NodoEstat.innerHTML = "entregat";
			break;
		case('entregat'):
			objLiniaComanda.NodoIcon.className="fa fa-paper-plane"; // external-link
			objLiniaComanda.NodoValue.value = "entregat";
			objLiniaComanda.NodoEstat.innerHTML = "entregat";
			break;
		}
		// Assigno el valor del nou estat
		objLiniaComanda.NodoValue.value = status;
	}
//
//function active(id) {
//    document.getElementById('boton1').disabled=false;
//    document.getElementById('boton2').disabled=false;
//    document.getElementById('boton3').disabled=false;
//    document.getElementById(id).disabled=true;
//}  

//http://javascript.espaciolatino.com/trucos/t29.htm

//function deleteComanda(){
//	objComanda.NodoComanda.hidden = "true";
//}
//////////////////////////////////////////////////
//onload amb jQuery
//$(document).ready(inicialitzar());
//function inicialitzar(){
//// actualitzar dades de comandes i inicialitzar objectes nodes...
//	objComanda.Comanda = document.getElementById("areaProdCompost");
//	nodes.areaComponents = document.getElementById("areaComponents");
//	nodes.componentsActuals = document.getElementById("componentsActuals");
//	requestColorFonsBlancComandesVP();
//}

//Funcions per recarga de la llista de linias de comanda
//Jquery per llençar el timer i carregar la llista inicial de linies de comanda
//$(document).ready(function(){
//	setInterval(inicialitzar,10000);
//	$("t")
//});
////Funcio per demanar al web service que ens envii una llista de linies de comanda
//function inicialitzar(){
//    console.log("Requesting data to the webservice!!!");
//    req5 = new XMLHttpRequest();
//    req5.onreadystatechange = reloadEstat;
//    req5.open("GET", "data/comanda/getLComandes" , true);
//    req5.send();
//}
//
////Funcio que controla la resposta del webservice 
//function reloadEstat(){
//	//Si tot ok, que canvii els valors al jsp
//	if (req5.readyState == 4 && req5.status == 200){
//		cambiaLlistaComandes(req5.responseText);
//    }
//}