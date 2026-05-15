package com.soc.ewok.model;

/**
 * Classe que representa els tipus de producte.
 * @author Edixon
 *
 */

public class TipusProducte {
	public static final String CODI_PLAT = "plt";
	public static final String CODI_PLAT0 = "plt0";
	public static final String CODI_INGREDIENT = "igr";
	public static final String CODI_ARTICLE = "art";
	public static final String CODI_MENU = "menu";
	public static final String CODI_SERVEI = "srv";

	private Long nId;
	private String sNom;
	private String sDescripcio;
	private String sCodi;
	
	/**
	 * Retorna l'id del tipus producte.
	 * @return
	 */
	public Long getId() {
		return nId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nId == null) ? 0 : nId.hashCode());
		result = prime * result + ((sCodi == null) ? 0 : sCodi.hashCode());
		result = prime * result
				+ ((sDescripcio == null) ? 0 : sDescripcio.hashCode());
		result = prime * result + ((sNom == null) ? 0 : sNom.hashCode());
		return result;
	}

	

	/**
	 * Assigna l'id del producte.
	 * @param id L'id a assignar
	 */
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * Retorna el nom del Tipus de Producte
	 * @return el nom del tipo de Producte
	 */
	public String getNom() {
		return sNom;
	}
	
	/**
	 * Assigna un nom al producte.
	 * @param nom El nom a assignar
	 * @param IllegalArgumentException En cas que el nom sigui null o buit
	 */
	public void setNom(String nom) {
		if (nom == null || nom.trim().equals("")) {
			throw new IllegalArgumentException("El nom ha d'estar informat");
		}
		sNom = nom;
	}
	
	/**
	 * Retorna la descripcio del Tipus de Producte.
	 * @return
	 */
	public String getDescripcio() {
		return sDescripcio;
	}
	
	/**
	 * Assigna una descripcio  al Tipus de Producte
	 * @param descripcio
	 */
	public void setDescripcio(String descripcio) {
		
		this.sDescripcio = descripcio;
	}
	
	/**
	 * Retorna el codi del Tipus de Producte.
	 * @return
	 */
	public String getCodi() {
		return sCodi;
	}
	
	/**
	 * Assigna el codic del Tipus de Producte
	 * @param codi
	 */
	public void setCodi(String codi) {
		this.sCodi = codi;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipusProducte other = (TipusProducte) obj;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (sCodi == null) {
			if (other.sCodi != null)
				return false;
		} else if (!sCodi.equals(other.sCodi))
			return false;
		if (sDescripcio == null) {
			if (other.sDescripcio != null)
				return false;
		} else if (!sDescripcio.equals(other.sDescripcio))
			return false;
		if (sNom == null) {
			if (other.sNom != null)
				return false;
		} else if (!sNom.equals(other.sNom))
			return false;
		return true;
	}
	

}
