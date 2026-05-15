/**
 * Codi JS de validació del formulari de Formes de Pagament
 * 
 */

function validarForm() {
	
	// mirem si entrem aki
	console.log ("Has entrat a l´arxiu JS");
		nom = "";
	// obtenim els noms del form
	
	
		nom = inputNom.value;
		// si el nom es buit donarem error
		if (nom == "") {
			// ensenyem l´span error pero encara BUIT
			spanErrorNom.hidden = false;
			//es mostra el msg d´error internacionalitzat
			return false;
		}
		
		return true;

}



