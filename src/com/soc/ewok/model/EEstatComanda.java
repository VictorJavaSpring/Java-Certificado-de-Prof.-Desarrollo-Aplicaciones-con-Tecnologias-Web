package com.soc.ewok.model;

public enum EEstatComanda {
	/** La comanda s'este introduint */
	enConstruccio,
	/** La comanda este finalitzada i validada */
	validada,
	/** La comanda s'ha pagat */
	pagada,
	/** La comanda este llesta per entregar */
	preparada,
	/** Comanda llesta per entregar i pagada */
	preparadaIPagada,
	/** Estat final. La comanda s'ha entregat i ja este pagada */
	entregada
}
