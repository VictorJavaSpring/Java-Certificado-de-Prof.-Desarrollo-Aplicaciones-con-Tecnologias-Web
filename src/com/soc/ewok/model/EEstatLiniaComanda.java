package com.soc.ewok.model;

public enum EEstatLiniaComanda {
	/** Estat inicial de la linia. 
	 * Encara no s'ha fet res amb la linia 
	 * */
	inicial,
	/** S'este cuinant el producte */
	enPreparacio,
	/** El producte este cuinat */
	preparat,
	/** El producte s'ha ficat al paquet */
	entregat
}
