
/**
 * funcions per a fer un request AJAX 
 */
var req; //variable global per assignar l'event d'Ajax

function requestAjaxProductes(id){
	req = new XMLHttpRequest();
	req.onreadystatechange = controlEstat;
	req.open("GET", "data/productesfiltrats/unTipusdeProductes/" + id, true);
	req.send();
}

function controlEstat(){
//	console.log("stateChange amb readyState= " + req.readyState + 
//			" i status = " + req.status);
	
	if (req.readyState == 4 && req.status == 200){
		dadesProductes(req.responseText);
		//un cop obtingudes les dades activar l'adequat
		activaOptionCorrecta();
		//assignar event change a select de productes per 
		//actualitzar id de producte per a SQL
		$("#productlist").change(function(){
			$("#inputProducte").val($("#productlist").val());
		})
		
		
    }
}

function dadesProductes(dades){
	var dadesRebudes = JSON.parse(dades);

	var options = "<SELECT id='productlist' class='form-control'><option id='' value=''> </option>";
	if (dadesRebudes != null){
		var productes = dadesRebudes.producte;
			
		//cas 1 rebem un array de productes
		if (productes.length){
			for (x in productes){
				options += "<option id='producte"+ productes[x].id + "'" + " value='"+ productes[x].id + "'>" + productes[x].nom + "</option>";
			}
		}
		//cas 2 només hi ha un producte: un sol objecte producte
		else if(productes.id){
			options += "<option id='producte"+ productes.id + "'" + " value='"+ productes.id + "'>" + productes.nom + "</option>";
		}
	}
	//cas 3 no hi ha dades de productes: null object
	
	options += "</SELECT>";
	document.getElementById("llistatproductesfiltrat").innerHTML = options;
}

//onload amb jquery
$(document).ready(inicialitzar());

function inicialitzar(){
	//actualitzar dades de select de productes
	requestAjaxProductes(document.getElementById("inputTipusProducte").value);

}

function activaOptionCorrecta(){
	//1.- quan venim de 1a càrrega de la pàgina:
	//seleccionar el producte corresponent a l'objecte carregat
	//obtenir la option i assignar-li selected
	var d = document.getElementById("inputProducte").value;
	var e = document.getElementById("producte"+d);
	if (e != null){	e.selected = 'selected';}

	//2.- al event change de tipus de producte haig d'assignar el 1r valor de producte 
	// de la llista de productes al camp hidden de producte
	$("#inputProducte").val($("#productlist").val());
}


