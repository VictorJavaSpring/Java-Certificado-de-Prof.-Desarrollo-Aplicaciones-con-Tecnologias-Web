package com.soc.ewok.model;

/**
 * Classe que representa les Unitats de medició del producte
 * 
 * @author Edixon
 *
 */
public class Unitat {
	private Long nId;
	private String sNom;
	private String sAcron;
	
	/**
	 * Retorna l'id del producte.
	 * @return
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id del producte.
	 * @param id L'id a assignar.
	 */
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * Retorna el nom del producte
	 * @return El nom del producte
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
	 * Retorna el acrònim que ha de tenir cada producte
	 * @return El Acrònim del producte
	 */
	public String getAcron() {
		return sAcron;
	}
	
	
	public void setAcron(String acron) {
		if (acron == null || acron.trim().equals("")) {
			throw new IllegalArgumentException("El nom ha d'estar informat");
		}
		sAcron = acron;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nId == null) ? 0 : nId.hashCode());
		result = prime * result + ((sAcron == null) ? 0 : sAcron.hashCode());
		result = prime * result + ((sNom == null) ? 0 : sNom.hashCode());
		return result;
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
		Unitat other = (Unitat) obj;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (sAcron == null) {
			if (other.sAcron != null)
				return false;
		} else if (!sAcron.equals(other.sAcron))
			return false;
		if (sNom == null) {
			if (other.sNom != null)
				return false;
		} else if (!sNom.equals(other.sNom))
			return false;
		return true;
	}
	
	
	
	
	
}
