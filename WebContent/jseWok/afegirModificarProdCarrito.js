// Aquesta funció es crida quan el client fa click a "afegir al carro" i serveix per afegir el producte
// escullit a la comanda (a través del web service) i després pintar el producte al shopping cart.
// També es podrà fer servir per modificar les cuantitats (afegir o restar més unitats).

var idProdModificar = 0;
var quantitatProdModificar = 0;
var totalProductes = 0;

//Funció que es grida quan el client clica a la x per esborrar un producte del seu shopping cart
function onClickAfegirModificar(idProducte, quantitat) {
	idProdModificar = idProducte;
	quantitatProdModificar = quantitat;
	
	// Utilitzem el Web Service 
	req = new XMLHttpRequest();
	req.onreadystatechange = controlEstatModificar;
	req.open("GET", "data/comAct/modificaLinia/id/" + idProdModificar + "/qu/" + quantitatProdModificar, true);
	req.send();
}

function controlEstatModificar() {
	if (req.readyState == 4 && req.status == 200){
		afegeixModifica(req.responseText);
    }
}

// Si tot va be (el producte és afegit o esborrat o la cantitat modificada) rebrem 
// la llista de linees de comanda actualitzada. Amb la funció afegeixModifica()
// pintarem els productes a la página web (al shopping cart)
function afegeixModifica(JSONliniesComanda) {
	var stringCarrito = "";
	var liniesComanda = JSON.parse(JSONliniesComanda);
	
	// Si la lista linies de comanda té length vol dir que és un vector, o sigui que en la
	// comanda hi ha més d'un producte. Fem un bucle per crear tants list items <li>
	// quants són els productes a pintar 
	if (liniesComanda.liniaComanda.length) {
		for (var i = 0; i < liniesComanda.liniaComanda.length; i++) {
			stringCarrito += creaStringProductes(liniesComanda.liniaComanda[i]);
		}
		totalProductes = liniesComanda.liniaComanda.length;
	} else {
	// Si la lista linies de comanda NO té length, vol dir que és un objecte (y NO un vector),
	// i que només hi és un producte a la llista. No fem bucle, sino que el pintem directament.
		stringCarrito += creaStringProductes(liniesComanda.liniaComanda);
		totalProductes = 1;
	}
	// Després de crear el string amb tots els producte, tornem a pintar el botó de Checkout
	stringCarrito += 
		"<li>"
			+ "<!-- Cart items for shopping list -->"
			+ "<div class='cart-item'>"
			+ "   <a class='btn btn-danger' data-toggle='modal' href='#shoppingcart1'>Checkout</a>"
			+ "</div>"
		+ "</li>";
	document.getElementById('carrito').innerHTML = stringCarrito;
	document.getElementById('totalProductes').innerHTML = totalProductes;
}


// Aquesta funció serveix per crear el string per pintar els productes al shopping cart
// Si li passa la linia de comanda i retorna el string
function creaStringProductes(liniaCom) {
	return "<li id='liProducte" + liniaCom.idProducte + "'>"
	+ "<!-- Cart items for shopping list -->"
	+ "<div class='cart-item' >"
		+ "<!-- Item remove icon -->"
		+ "	<i class='fa fa-times' onclick='onClickEsborrar(" + liniaCom.idProducte + ")' style='cursor: pointer'></i>"
		+ "	<!-- Image -->"
		+ " <img class='img-responsive' src='img?accioFoto=get&idProd=" + liniaCom.idProducte + "' alt='producte" + liniaCom.idProducte + "' />"
		+ "	<!-- Title for purchase item -->"
		+ "	<span class='cart-title'><a href='#'>" + liniaCom.producte.nom + "</a></span>"
		+ "	<!-- Quantity -->"
		+ "	<span><a href='#'>Quantitat: " + liniaCom.quantitat + "</a></span>"
		+ "	<!-- Cart item price -->"
		+ "	<span><a href='#'>Preu unitari: " + liniaCom.preuVenda + "€</a></span>"
		+ "	<!-- Cart item total price -->"
		+ "	<span class='cart-price pull-right red'>Preu total producte: " + liniaCom.preuVenda * liniaCom.quantitat + "€</span>"
		+ "	<div class='clearfix'></div>"
	+ "	</div>"
+ "	</li>";
}