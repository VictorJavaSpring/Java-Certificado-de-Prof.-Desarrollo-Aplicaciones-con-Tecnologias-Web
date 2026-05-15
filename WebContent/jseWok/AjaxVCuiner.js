/**
* Funcions js per la Vista del Cuiner
*/

// Objecte amb els valors de la linia de comanda
var objLinia={
	Comanda:null,   //Num de comanda
	Linia:null,     //Linia de comanda
	Estat:null,     //Estat de la comanda
	NodoValue:null, //Node del value del estat
	NodoIcon:null,   //Node del icone del estat
	NodoDel:null,  //Node pel l'icone de borrar linia de comanda
	NodoLinia:null
};

//Funcio que cambia l'estat per enviar.lo al webservice 
function requestPlat(comanda, linia){	
	objLinia.Comanda = comanda;  
	objLinia.Linia = linia;
	objLinia.NodoValue = document.getElementById("value_"+comanda+","+linia);
	objLinia.NodoIcon = document.getElementById("icon_"+comanda+","+linia);
	objLinia.Estat = objLinia.NodoValue.value;
	objLinia.NodoDel = document.getElementById("del_"+comanda+","+linia);
	objLinia.NodoLinia = document.getElementById("linia_"+comanda+","+linia);
	
	//Segons l'estat, cambio aquest
	switch(objLinia.Estat){
		case('inicial'):
			objLinia.Estat = 'enPreparacio';
			break;
		case('enPreparacio'):
			objLinia.Estat = 'preparat';
			break;
	}
	
	//L'envio al webservice perque canvii l'estat a la bbdd
	req = new XMLHttpRequest();
	req.onreadystatechange = controlEstat;
	req.open("GET", "data/comanda/getLComanda/" + objLinia.Comanda + "/num/" + objLinia.Linia + "/stat/" + objLinia.Estat , true);
	req.send();
}

//Funcio que controla l'estat de la resposta del webservice
function controlEstat(){
	//Si tot ok, que canvii els valors al jsp
	if (req.readyState == 4 && req.status == 200){
		cambiaEstat(req.responseText);
    }
}

//Funcio que cambia l'estat de la llista de linias de comanda
function cambiaEstat(data){
	var status = null;
	//Les dades rebudes pel webservice
	var dadesRebudes = JSON.parse(data);
	if(dadesRebudes){
		//El estat rebut
		status = dadesRebudes.estat;
	}
	//Segons l'estat, cambio l'icone 
	switch(status){
		case('enPreparacio'):
			objLinia.NodoIcon.className="fa fa-spinner fa-spin";
			break;
		case('preparat'):
			objLinia.NodoIcon.className="fa fa-check-square";
			objLinia.NodoDel.className="fa fa-times pull-right";
			objLinia.NodoDel.removeAttribute("hidden");
			break;
	}
	//I fico el valor del nou estat
	objLinia.NodoValue.value = status;
}

//Funcion que borra la linia de comanda
function deletePlat(){
	objLinia.NodoLinia.hidden = "true";
}

//Funcions per recarga de la llista de linias de comanda
//Jquery per llençar el timer i carregar la llista inicial de linies de comanda
$(document).ready(function(){
	setInterval(reload,10000);
	$("t")
});

//Funcio per demanar al web service que ens envii una llista de linies de comanda
function reload(){
    console.log("Requesting data to the webservice!!!");
    req = new XMLHttpRequest();
	req.onreadystatechange = reloadEstat;
	req.open("GET", "data/comanda/getLComandes" , true);
	req.send();
}

//Funcio que controla la resposta del webservice 
function reloadEstat(){
	//Si tot ok, que canvii els valors al jsp
	if (req.readyState == 4 && req.status == 200){
		cambiaLlista(req.responseText);
    }
}

//Funcio per mostrar les dades rebudes del web service
function cambiaLlista(data){
	console.log("Updating data...");
	var dadesRebudes = JSON.parse(data);
	var i = 0;
	var nodeTable = document.getElementById("table");
	var tableHead = document.getElementById("tableHead");
	var html = null;
	var button = null;
	//Si obtinc dades
	if(dadesRebudes){
		//Começa l'accio!
		html = '<tbody>'
		html += '<tr id="tableHead">' + tableHead.innerHTML + '</tr>';
		for(i in dadesRebudes.liniaComanda){
			console.log(dadesRebudes.liniaComanda[i].id);
			
			var comanda = dadesRebudes.liniaComanda[i].id;
			var linia = dadesRebudes.liniaComanda[i].linia;
			var Producte = dadesRebudes.liniaComanda[i].producte.id;
			var ProducteNom = dadesRebudes.liniaComanda[i].producte.nom;
			var qty = dadesRebudes.liniaComanda[i].quantitat;
			var data = dadesRebudes.liniaComanda[i].comanda.data;
			var estat = dadesRebudes.liniaComanda[i].estat; 
			switch (estat){
				case ('inicial'):
					button = 'fa fa-play';
					break;
				case ('enPreparacio'):
					button = 'fa fa-spinner fa-spin';
					break;
				case ('preparat'):
					button = 'fa check-square';
					break;
			}

			html += '<tr id="linia_'+ comanda + ',' + linia + '">';
			html +='<td>' + comanda + '</td>';
			html +='<td>' + linia + '</td>';
			html +='<td>' + Producte + '</td>';
			html +='<td>' + ProducteNom + '</td>';
			html +='<td>' + qty + '</td>';
			html +='<td>' + data + '</td>';
			html +='<td>';
			html +='<button onclick="requestPlat('+comanda+','+linia+')">';
			html +='<i class="' + button + '" id="icon_' + comanda + ',' + linia + '"></i>';
			html +='</button><input type="text" hidden="true" id="value_' + comanda + ',' + linia + '" value="' + estat + '"/>';
			html +='<i onclick="deletePlat()" hidden="true" id="del_'+ comanda + ',' + linia +'"></i>';
			html +='</td>';
			html +='</tr>';

		}
		html +='</tbody>';
		nodeTable.innerHTML = html;
	}else{
		console.log("NO DATA");
	}
}




