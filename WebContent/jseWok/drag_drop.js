
/**
 * funcions funcions per a fer drag and drop 
 */


//funcions per als events del drag&drop contenidor → ondragenter,ondragover, ondragleave, ondrop
function drop(ev,desti){
	//funció per gestionar el drop: rep les dades de l'event.
	//cal introduïr en l'event (a la funció dragstart) les dades que necessitem aquí 
	ev.preventDefault();
	var el = ev.dataTransfer.getData("idDiv");
	var nod = document.getElementById(el);

	(desti == null)? nod.divProducte.esborraDiv() : nod.divProducte.migraA(desti);  
	ev.currentTarget.classList.remove("dropencurs");
}

function permetDrop(ev){
	ev.preventDefault(); //funció estandar (veure w3c drag&drop) per a desactivar comportament per defecte
	ev.currentTarget.classList.add("dropencurs");
}

function mostradropable(ev,nod){
	//accions per a event ondragENTER. actua sobre l'element contenidor del que s'està movent
	ev.currentTarget.classList.add("dropencurs");
}
function surtdropable(ev,nod){
	//accions per a event ondragLEAVE. actua sobre l'element contenidor del que s'està movent
	ev.currentTarget.classList.remove("dropencurs");
}
//funcions per a l'element arrossegat → ondragstart, ondrag, ondragend 
//les funcions per a l'element draggable son membres del divProducte.js