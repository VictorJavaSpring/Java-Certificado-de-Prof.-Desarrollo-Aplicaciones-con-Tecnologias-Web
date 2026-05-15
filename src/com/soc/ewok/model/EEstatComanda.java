package com.soc.ewok.model;

public enum EEstatComanda {
	/** La comanda s'està introduint */
	enConstruccio,
	/** La comanda està finalitzada i validada */
	validada,
	/** La comanda s'ha pagat */
	pagada,
	/** La comanda està llesta per entregar */
	preparada,
	/** Comanda llesta per entregar i pagada */
	preparadaIPagada,
	/** Estat final. La comanda s'ha entregat i ja està pagada */
	entregada
}
