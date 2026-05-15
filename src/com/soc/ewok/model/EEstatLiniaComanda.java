package com.soc.ewok.model;

public enum EEstatLiniaComanda {
	/** Estat inicial de la linia. 
	 * Encara no s'ha fet res amb la linia 
	 * */
	inicial,
	/** S'està cuinant el producte */
	enPreparacio,
	/** El producte està cuinat */
	preparat,
	/** El producte s'ha ficat al paquet */
	entregat
}
