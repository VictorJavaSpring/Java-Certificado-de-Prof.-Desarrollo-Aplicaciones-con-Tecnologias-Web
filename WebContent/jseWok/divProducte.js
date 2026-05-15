
/**
 * funcions per a crear un contenidor div amb les dades d'un producte 
 */


function divProducte(producte, contenidor){
	//rebem un objecte producte i un contenidor destí
	
	//variables membre de l'objecte divProducte
	this.producte = producte;
	this.contenidor = contenidor;	//ref al node html que contindrà el divProducte (és el parent de divProducte)
	this.divPrincipal = null;		//ref al node divPrincipal que contindrà la informació
	this.calcQuantitat = null;
	this.calcPreu = null;
	
	this.pintat = function(){ //funció que crea l'objecte HTML que es mostrarà a la pàgina
		this.divPrincipal = document.createElement("div");
		this.divPrincipal.id = "prod"+producte.id;				//li assignem un identificador unic
		this.divPrincipal.divProducte = this;	//ens assignem l'objecte per tenir accès a ell desde l'html div.

		//insertem el divPrincipal com a primer fill de contenidor 
		contenidor.insertBefore(this.divPrincipal,contenidor.firstChild);
		
		//assignem propietats i events a cada part de la fitxa de persona
		this.divPrincipal.className = "col-md-12 col-sm-6 fitxa";
		this.divPrincipal.draggable = true;


		//afegim event dragstart per a poder fer drag&drop
		this.divPrincipal.addEventListener("dragstart", function(ev){
			// We'll handle this event so first stop bubbling up
			ev.stopPropagation();
			//afegim dades a l'objecte event drag per a usar-les al moment del drop
			ev.dataTransfer.setData("idDiv", this.id);
			this.classList.add("dropencurs");
			}
		);
		this.divPrincipal.addEventListener("dragend", function(ev){
			//treim la classe dropencurs.
			this.classList.remove("dropencurs");
			}
		);
		//drags per reordenar
		this.divPrincipal.addEventListener("dragover", function(){
			permetDrop(event);
			}
		);
		this.divPrincipal.addEventListener("drop", function(){
			drop(event,this);
			}
		);
		this.divPrincipal.addEventListener("dragenter", function(){
			mostradropable(event);
			}
		);
		this.divPrincipal.addEventListener("dragleave", function(){
			surtdropable(event);
			}
		);
		//
		var divDadesProducte = document.createElement("div");
		divDadesProducte.className = "dadesproducte col-md-8 col-sm-6";
		divDadesProducte.innerHTML = "Nom: " + producte.nom + "<br>" +
		"Descripció: " + producte.descripcioCurta;
		this.divPrincipal.appendChild(divDadesProducte);
		
		//div de quantitat
		var divQuantitat = document.createElement("div");
		divQuantitat.className = "quantitat dadesproducte col-md-2 col-sm-3";
		//input de quantitat
		this.calcQuantitat = document.createElement("input");
		this.calcQuantitat.type = "number";
		this.calcQuantitat.name = "quantitat"+producte.id;
		this.calcQuantitat.id = "quantitat"+producte.id;
		this.calcQuantitat.value = producte.quantitat;
		this.calcQuantitat.className = "form-control";
		this.calcQuantitat.divPare = this;
		//afegim funció de tancar de manera inserida (la definim en aquesta sentència)
		this.calcQuantitat.addEventListener("change", function(){
			//actualitzar valor de producte i cost total 
			if(this.divPare.calcQuantitat.value <= 0){
				this.divPare.calcQuantitat.value = 1;
				alert("quantitat: No pot ser zero. \n Treu el producte de la llista");
				return;
			}
			this.divPare.calcPreu.value= 
				this.divPare.calcQuantitat.value*producte.preu;
			
			this.divPare.producte.quantitat = this.divPare.calcQuantitat.value;
			$("#costTot").val(objProdCompost.actualitzaCostTotal());
			
		});
		//l'afegim
		divQuantitat.appendChild(this.calcQuantitat);
		this.divPrincipal.appendChild(divQuantitat);
		
		//div de preu
		var divPreu = document.createElement("div");
		divPreu.className = "preu dadesproducte col-md-2 col-sm-3 pull-right";
		//input de preu
		this.calcPreu = document.createElement("input");
		this.calcPreu.type = "number";
		this.calcPreu.readOnly = true;
		this.calcPreu.name = "preu"+producte.id;
		this.calcPreu.id = "preu"+producte.id;
		this.calcPreu.value = parseFloat(parseFloat(producte.quantitat)*parseFloat(producte.preu));
		this.calcPreu.className = "form-control";
		this.calcPreu.divPare = this;
		//l'afegim
		divPreu.appendChild(this.calcPreu);
		this.divPrincipal.appendChild(divPreu);
		
	};
	
	this.migraA = function(nouContenidor){
		/** mou una fitxa a un nou contenidor */

		// és reordenació quan migra a un producte. mirar id prod  
		if(nouContenidor.id.indexOf("prod")!= -1){
			//és reordenació
			if(nouContenidor.divProducte.contenidor.id == this.contenidor.id){
				//reordenació dins el mateix contenidor. no hi ha canvi de dades.
				var desti = nouContenidor.nextElementSibling ? nouContenidor.nextElementSibling : null;
				if (desti == null) {
					nouContenidor.parentNode.appendChild(this.divPrincipal);
				} else {
					nouContenidor.parentNode.insertBefore(this.divPrincipal, desti);
				}
				return;
			}else{
				//drop des de altra àrea i sobre un producte -> canvi dades i area
				this.contenidor.removeChild(this.divPrincipal);
				this.contenidor = nouContenidor.divProducte.contenidor;
				this.contenidor.insertBefore(this.divPrincipal,nouContenidor);
				//si es sobre components actuals
				if(this.contenidor.id=="componentsActuals"){
					this.insertDadesObjCompost();
				}else{
					this.deleteDadesObjCompost();
				}
			}
		}else{ 
			// és migració a contenidor diferent
			if(nouContenidor.id == this.contenidor.id){
				//no fem res xq no és reordenació ni moviment entre àrees
				return;
			}
			//desplaçament des de altra area sobre el contenidor, posem el producte al començament
			this.contenidor.removeChild(this.divPrincipal);
			this.contenidor = nouContenidor;
			this.contenidor.insertBefore(this.divPrincipal,this.contenidor.firstChild);
			
			//canvi dades: si es sobre components actuals
			if(nouContenidor.id =="componentsActuals"){
				this.insertDadesObjCompost();
			}else{//es sobre area de llistat de productes compatibles 
				this.deleteDadesObjCompost();
			}
		}
	};
	 
	this.esborraDiv = function(){
		/** funcio per esborrar el divProducte del contenidor */
		//elimina aquest node de l'html
		this.contenidor.removeChild(this.divPrincipal);
	};
	
	this.insertDadesObjCompost = function(){
		/** actualitza les dades de l'objecte compost i del
		* llistat de productes compatibles quan s'AFEGEIX
		* un nou producte a l'objecte compost */
		
		//inicio els valors quantitat i preu dels inputs
		this.calcQuantitat.value = 1,00;
		this.producte.quantitat = 1,00;
		this.calcPreu.value=parseFloat(this.producte.preu);

		//actualitzar components del producte compost
		objProdCompost.components.push(this.producte);
		$("#costTot").val(objProdCompost.actualitzaCostTotal());
		
		//actualitzar llistaproductes
		if(llistaProductes.id){
			llistaProductes = null;
		}else{
			//buscar l'index i treure el producte de l'array
			for(var t in llistaProductes){
				if(llistaProductes[t].id==this.producte.id) break;
			}
			llistaProductes.splice(t,1);
		}
		
	};
	this.deleteDadesObjCompost = function(){
		/**actualitza les dades de l'objecte compost i del
		* llistat de productes compatibles quan s'ELIMINA
		* un producte de l'objecte compost i es passa al llistat 
		* de components disponibles */
		
		//trobar l'index de l'element a l'array i eliminar-lo dels
		//components de l'objProdCompost
		for(var x in objProdCompost.components){
			if(objProdCompost.components[x].id==this.producte.id) break;
		}
		objProdCompost.components.splice(x,1);
		$("#costTot").val(objProdCompost.actualitzaCostTotal());
		//actualitzar llistaProductes
		if(llistaProductes.length){
			llistaProductes.push(this.producte);
		}else{
			llistaProductes = [this.producte];
		}
	};
}

