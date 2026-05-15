package com.soc.ewok.model;

import java.util.Date;
 
/**
 * Classe que representa els punts pendents dels usuaris.
 * Als usuaris se li assignarà uns punts per cada comanda (si gasta un mínim).
 * Els punts del mateix usuari i que tinguin la mateixa data de caducitat
 * estaràn agrupats amb un mateix id. 
 * @author Sara
 */

public class PuntsPendents {
	private Long nId;
	private Short nNumPunts;
	private Date dDataCaducitat;
	private Long nIdClient;
	
	/**
	 * Retorna l'id dels punts pendents
	 * @return l'id dels punts pendents
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id dels punts pendents
	 * @param nId L'id a assignar
	 */
	public void setId(Long nId) {
		this.nId = nId;
	}
	
	/**
	 * Retorna el número de punts pendents (relacionats amb un mateix id).
	 * @return Número de punts pendents. No pot ser null.
	 */
	public Short getNumPunts() {
		return nNumPunts;
	}
	
	/**
	 * Assigna el número de punts pendents, dela mateix client amb
	 * la mateixa data de caducitat (agrupats amb el mateix id).
	 * @param nNumPunts El num de punts a assignar
	 * @throws IllegalArgumentException En cas que el número de punts sigui null
	 */
	public void setNumPunts(Short nNumPunts) {
		if (nNumPunts == null) {
			throw new IllegalArgumentException("El número de punts ha d'estar informat");
		}
		this.nNumPunts = nNumPunts;
	}
	
	/**
	 * Retorna la data de caducitat dels punts
	 * @return La data de caducitat dels punts. No pot ser null
	 */
	public Date getDataCaducitat() {
		return dDataCaducitat;
	}
	
	/**
	 * Assigna una data de caducitat als punts
	 * @param dDataCaducitat La data de caducitat dels punts
	 * @throws IllegalArgumentException En cas que la data sigui null
	 */
	public void setDataCaducitat(Date dDataCaducitat) {
		if (dDataCaducitat == null) {
			throw new IllegalArgumentException("La data ha d'estar informada");
		}
		this.dDataCaducitat = dDataCaducitat;
	}
	
	/**
	 * Retorna l'id del client al qui pertanyen els punts
	 * @return L'id del client al qui pertanyen els punts. Pot retornar null
	 */
	public Long getIdClient() {
		return nIdClient;
	}
	
	/**
	 * Assigna l'id del client al qui pertanyen els punts
	 * @param nIdClient És l'id del client
	 * @throws IllegalArgumentException En cas que l'id del client sigui null
	 */
	public void setIdClient(Long nIdClient) {
		if (nIdClient == null) {
			throw new IllegalArgumentException("L'id del client ha d'estar informat");
		}
		this.nIdClient = nIdClient;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntsPendents other = (PuntsPendents) obj;
		if (dDataCaducitat == null) {
			if (other.dDataCaducitat != null)
				return false;
		} else if (!dDataCaducitat.equals(other.dDataCaducitat))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (nIdClient == null) {
			if (other.nIdClient != null)
				return false;
		} else if (!nIdClient.equals(other.nIdClient))
			return false;
		if (nNumPunts == null) {
			if (other.nNumPunts != null)
				return false;
		} else if (!nNumPunts.equals(other.nNumPunts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PuntsPendents [nId=" + nId + ", nNumPunts=" + nNumPunts
				+ ", dDataCaducitat=" + dDataCaducitat + ", nIdClient="
				+ nIdClient + "]";
	}
}
