// Versió jQuery
$(document).ready(function() {
	
	$("#idProd").change(function () {
			mostraImatge(300);
		}
	);
	
	mostraImatge(0);
});

function mostraImatge(time) {
	$("#imatgeProd").fadeOut(time, function() {
		if ($("#idProd").val() == -1) {
			$("#imatgeProd").html("Escull un producte");
		} else {
			$("#imatgeProd").html(
				"<img src='staff?accioFoto=get&idProd="+ 
				$("#idProd").val() + 
				"' class='img-responsive img-thumbnail' alt='Producte " + 
				$("#idProd").val() + "' /><br/>" + 
				"Url pública: eWok/img?accioFoto=get&idProd=" + 	
				$("#idProd").val()
				);
		}
		$("#imatgeProd").fadeIn(300);
	});
}

// Codi antic sense usar jQuery
var nodes = {
	imatge : null,
	select : null,
	carrega : function() {
		this.select = document.getElementById("idProd");
		this.imatge = document.getElementById("imatgeProd");
	}
};

function onProductSelect() {
	if (nodes.imatge == null) {
		nodes.carrega();
	}
	if (nodes.select.value == "-1") {
		nodes.imatge.innerHTML = "Escull un producte";
	} else {
		nodes.imatge.innerHTML = 
			"<img src='staff?accioFoto=get&idProd="+ 
			nodes.select.value + 
			"' class='img-responsive img-thumbnail' alt='Producte " + 
			nodes.select.value + "' />";
	}
}
