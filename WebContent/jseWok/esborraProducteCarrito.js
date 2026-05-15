
idProducteEsborrar = 0;

// Funció que es grida quan el client clica a la x per esborrar un producte del seu shopping cart
function onClickEsborrar(idProducte) {
	idProducteEsborrar = idProducte;
// Utilitzem el Web Service 
	req = new XMLHttpRequest();
	req.onreadystatechange = controlEstatEsborrar;
	req.open("GET", "data/comAct/eliminaLinia/num/" + idProducteEsborrar, true);
	req.send();
}

function controlEstatEsborrar() {
	if (req.readyState == 4 && req.status == 200){
		esborraProducte(req.responseText);
    }
}

// Si tot va be (el producte és esborrat de la comanda), treuem del shopping cart 
// el list item del producte esborrat
function esborraProducte() {
	prodAEsborrar = document.getElementById("liProducte" + idProducteEsborrar);
	prodAEsborrar.parentNode.removeChild(prodAEsborrar);
}

