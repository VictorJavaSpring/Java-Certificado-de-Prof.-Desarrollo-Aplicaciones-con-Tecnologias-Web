/**
 * funcions per a fer un request AJAX de producteCompost
 */

// var GLOBALS
var req; // variable global per assignar l'event d'Ajax
var req2;
var req3;
	
var objProdCompost; // objecte ProducteCompost es pobla amb càrrega d dades per AJAX
var llistaProductes; // Array de productes compatibles es pobla mitjançant AJAX
var objPreu = { 	// objecte Preu
		preu : 0,
		iniciVigencia : null,
		finalVigencia : null,
		idProducte : null
}; 

// objecte per accedir als nodes útils de la pàgina
var nodes = {
	areaProdCompost : null,
	areaComponents : null,
	componentsActuals : null
};

// ////////////////////////////////////////////////
// onload amb jquery
$(document).ready(inicialitzar());

function inicialitzar() {
	// actualitzar dades de productes i inicialitzar objectes nodes...
	nodes.areaProdCompost = document.getElementById("areaProdCompost");
	nodes.areaComponents = document.getElementById("areaComponents");
	nodes.componentsActuals = document.getElementById("componentsActuals");
	// obtenir dades
	requestAjaxProducteCompost(document.getElementById("prodcompostid").value);

}

function requestAjaxProducteCompost(id) {
	req = new XMLHttpRequest();
	req.onreadystatechange = controlEstatPC;
	req.open("GET", "data/productesfiltrats/unProducteCompost/" + id, true);
	req.send();
}

function controlEstatPC() {

	if (req.readyState == 4 && req.status == 200) {
		dadesProducteCompost(req.responseText);
	}
}

function dadesProducteCompost(dades) {
	// només rebo un objecte
	objProdCompost = JSON.parse(dades);

	if (objProdCompost != null) {
		// hem aconseguit l'objecte
		// assigno var membres a usar
		objProdCompost.costTotal = 0;
		objProdCompost.preuFinal = 0;
		if(!objProdCompost.components){
			objProdCompost.components = new Array();
		}
		objProdCompost.actualitzaCostTotal = function() {
			// obté els cost total a partir de la llista de
			// components activa de l'objecte
			objProdCompost.costTotal = 0;
			for ( var x in objProdCompost.components) {
				objProdCompost.costTotal += parseFloat(objProdCompost.components[x].preu
						* objProdCompost.components[x].quantitat);
			}
			return parseFloat(objProdCompost.costTotal).toFixed(2);
		};
		// busquem la llista de productes components possibles que se li poden
		// afegir
		requestProductesCompatiblesAjax(objProdCompost.idTipusProducte);

	} else {
		// cas: no hi ha dades de producte: null object
	}
}

function requestProductesCompatiblesAjax(id) {
	req2 = new XMLHttpRequest();
	req2.onreadystatechange = controlEstatLlistatProductes;
	req2.open("GET", "data/productesfiltrats/totsComponentsTipusdeProducte/"
			+ id, true);
	req2.send();
}

function controlEstatLlistatProductes() {
	if (req2.readyState == 4 && req2.status == 200) {
		dadesLlistatProductes(req2.responseText);
	}
}
function dadesLlistatProductes(dades) {
	var dadesRebudes = JSON.parse(dades);

	if (dadesRebudes != null) {
		llistaProductes = dadesRebudes.producteComponent;

		// cas 1 rebem un array de productes
		if (llistaProductes.length) {
			for ( var x in llistaProductes) {
				// processar productes
				var dp = new divProducte(llistaProductes[x],
						nodes.areaComponents);
				dp.pintat();
			}
		}
		// cas 2 només hi ha un producte: un sol objecte producte
		else if (llistaProductes.id) {
			// processar un producte
			var dp = new divProducte(llistaProductes, nodes.areaComponents);
			dp.pintat();
			// i converteixo a array
			var tmp = llistaProductes;
			llistaProductes = [ tmp ];
		}
	} else {
		// cas 3 no hi ha dades de productes
		llistaProductes = new Array();
		
	}
	// pintem l'objecte compost
	creaDivCompost(objProdCompost, nodes.componentsActuals);
}

function creaDivCompost(prodcomp, contenidor) {
	// 1.- presentar les dades del producte compost a la seva àrea
	// 2.- presentar els productes que el composen
	// 3.- actualitzar la llista de areaComponents

	// presentar nom
	document.getElementById("nomProdCompost").innerHTML = prodcomp.nom;
	// presentar descripció
	document.getElementById("descripcioProdCompost").innerHTML = prodcomp.descripcioCurta;

	// presentar els productes que el composen
	if(prodcomp.components){
		for ( var x = prodcomp.components.length-1; x >= 0 ;x--) {
			// crear i presentar aquesta iteració
			var dp = new divProducte(prodcomp.components[x], contenidor);
			dp.pintat();
			// treure aquesta iteració de areaComponents i de llistaProductes
			var xtreure = cercaProducteAcontenidor(prodcomp.components[x].id,
					"div#areaComponents div.fitxa");
			if (xtreure != null)
				xtreure.divProducte.esborraDiv();
			if (llistaProductes.id) {
				llistaProductes = null;
			} else {
				// buscar l'index i treure el producte de l'array
				for ( var t in llistaProductes) {
					if (llistaProductes[t].id == prodcomp.components[x].id)
						break;
				}
				llistaProductes.splice(t, 1);
			}
		}
		// inicialitza valor de cost total i posar-lo a pàgina
		$("#costTot").val(objProdCompost.actualitzaCostTotal());
	}
	//obtenir preu vigent
	requestAjaxPreuProducte(objProdCompost.id);
}

function cercaProducteAcontenidor(id, contenidor) {
	// buscar el descendent de contenidor amb id passat
	var els = document.querySelectorAll(contenidor);
	for (var x = 0; x < els.length; x++) {
		if (els[x].divProducte.producte.id == id) {
			return els[x];
		}
	}
	return null;
}

function requestAjaxPreuProducte(idProducte){
	req3 = new XMLHttpRequest();
	req3.onreadystatechange = controlEstatPreu;
	req3.open("GET", "data/productesfiltrats/unPreuProducte/" + idProducte, true);
	req3.send();	
}

function controlEstatPreu(){

	if (req3.readyState == 4 && req3.status == 200) {
		dadesPreu(req3.responseText);
	}	
} 

function dadesPreu(dades){
	// només haig de rebre un objecte
	var dadesrebudes = JSON.parse(dades);

	if (dadesrebudes != null) {
		//el service JSON posa els nom d'atributs en minuscula
		objPreu = dadesrebudes

		//provem d'assignar les valors als nodes corresponents
		$("#preuFinal").val(objPreu.preu);
		$("#iniVigenciaPF").val(parseDate(objPreu.iniciVigencia));
		$("#fiVigenciaPF").val(parseDate(objPreu.finalVigencia));
	}
}

function parseDate(datestring){
	//rep un string tipus '2015-12-31T00:00:00+01:00'
	//retorna una data(yyyy-MM-dd)
	return datestring.split("T")[0];
}
/**
 * Funció per enviar les dades de l'objecte compost al servidor
 */
function enviarDades() {
	// comprovar preuFinal i asssignar-lo
	if (!actualitzaPreuFinal()){
		return;
	}else {
		// enviar
		// obtenir ordre de productes components i assignar-lo a l'objecte
		obtenirOrdreComponents();
		// inserir objectes stringificats i enviar
		$("#inputObjectePC").val(JSON.stringify(objProdCompost));
		$("#inputObjectePreu").val(JSON.stringify(objPreu));
		$("#formObjecte").submit();
	}
}

function actualitzaPreuFinal() {
	//no pot ser null ni negatiu
	if ($("#preuFinal").val() == "" || $("#preuFinal").val() < 0) {
		$("#preuFinal").focus();
		$("#labelpreuFinal").removeClass("label-info");
		$("#labelpreuFinal").addClass("label-warning");
		return false;
	}
	//reset estils
	$("#labelpreuFinal").removeClass("label-warning");
	$("#labelpreuFinal").addClass("label-info");

	//actualitzar dades a obj adients
	objProdCompost.preuFinal = $("#preuFinal").val();
	objPreu.preu = $("#preuFinal").val();
	objPreu.idProducte = objProdCompost.id;
	return objProdCompost.preuFinal;
}

function actualitzaDataIni() {
	//pot ser null
//	if ($("#iniVigenciaPF").val() == "") {
//		$("#iniVigenciaPF").focus();
//		$("#labeliniVigenciaPF").removeClass("label-info");
//		$("#labeliniVigenciaPF").addClass("label-warning");
//		return false;
//	}
	//reset estils
//	$("#labeliniVigenciaPF").removeClass("label-warning");
//	$("#labeliniVigenciaPF").addClass("label-info");
	
	//actualitzar dades a obj adients
	objPreu.iniciVigencia = $("#iniVigenciaPF").val();
	return objPreu.iniciVigencia;
}
function actualitzaDataFinal() {
	//pot ser null
//	if ($("#fiVigenciaPF").val() == "") {
//		$("#fiVigenciaPF").focus();
//		$("#labelfiVigenciaPF").removeClass("label-info");
//		$("#labelfiVigenciaPF").addClass("label-warning");
//		return false;
//	}
	//reset estils
//	$("#labelfiVigenciaPF").removeClass("label-warning");
//	$("#labelfiVigenciaPF").addClass("label-info");
	
	//actualitzar dades a obj adients
	objPreu.finalVigencia = $("#fiVigenciaPF").val();
	return objPreu.finalVigencia;
}

function obtenirOrdreComponents() {
	/**
	 * recòrrer el contenidor componentsActuals obtenir els idproducte i
	 * assignar #ordre a les var corresponents de l'array de
	 * objProdCompost.components
	 */
	var elmProd = $("#componentsActuals > div[id^='prod']");
	for (var i = 0; i < elmProd.length; i++) {
		var compActual = buscaProdObjComponentsId(elmProd[i].divProducte.producte.id);
		compActual.ordre = i;
	}
}

function buscaProdObjComponentsId(id) {
	for ( var i in objProdCompost.components) {
		if (objProdCompost.components[i].id == id) {
			return objProdCompost.components[i];
		}
	}
}
