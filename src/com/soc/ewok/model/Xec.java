package com.soc.ewok.model;

import java.util.Date;
 
/**
 * Classe que representa els xecs que té cada client.
 * Cada registre és un xec i està relacionat amb el client al qui pertany i 
 * a la comanda en que s'haurà gastat.
 * @author Sara
 */

public class Xec {
	private Long nId;
	private Short nNumPunts;
	private Date dDataCaducitat;
	private Long nIdComanda;
	private Long nIdClient;
	
	/**
	 * Retorna l'id del xec (cada registre/cada id és un xec).
	 * @return l'id del xec.
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id del xec.
	 * @param nId L'id a assignar.
	 */
	public void setId(Long nId) {
		this.nId = nId;
	}
	
	/**
	 * Retorna el número de punts per cada xec.
	 * @return Número de punts per cada xec. No pot ser null.
	 */
	public Short getNumPunts() {
		return nNumPunts;
	}
	
	/**
	 * Assigna el número de punts per cada xec, 
	 * @param nNumPunts El num de punts a assignar a cada xec
	 * @throws IllegalArgumentException En cas que el número de punts sigui null
	 */
	public void setNumPunts(Short nNumPunts) {
		if (nNumPunts == null) {
			throw new IllegalArgumentException("El número de punts per cada xec ha d'estar informat");
		}
		this.nNumPunts = nNumPunts;
	}
	
	/**
	 * Retorna la data de caducitat del xec
	 * @return La data de caducitat del xec. No pot ser null
	 */
	public Date getDataCaducitat() {
		return dDataCaducitat;
	}
	
	/**
	 * Assigna una data de caducitat al xec
	 * @param dDataCaducitat La data de caducitat del xec
	 * @throws IllegalArgumentException En cas que la data sigui null
	 */
	public void setDataCaducitat(Date dDataCaducitat) {
		if (dDataCaducitat == null) {
			throw new IllegalArgumentException("La data de caducitat del xec ha d'estar informada");
		}
		this.dDataCaducitat = dDataCaducitat;
	}
	
	/**
	 * Retorna l'id de la comanda en que s'ha gastat el xec.
	 * @return l'id de la comanda en que s'ha gastat el xec. 
	 * Pot ser null (si el xec encara no s'ha gastat)
	 */
	public Long getIdComanda() {
		return nIdComanda;
	}
	
	/**
	 * Assigna l'id de la comanda en que s'ha gastat el xec.
	 * @param nId L'id a assignar
	 */
	public void setIdComanda(Long nIdComanda) {
		this.nIdComanda = nIdComanda;
	}
	/**
	 * Retorna l'id del client al qui pertany el xec
	 * @return L'id del client al qui pertany el xec. No pot retornar null
	 */
	public Long getIdClient() {
		return nIdClient;
	}
	
	/**
	 * Assigna l'id del client al qui pertany el xec
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
		Xec other = (Xec) obj;
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
		if (nIdComanda == null) {
			if (other.nIdComanda != null)
				return false;
		} else if (!nIdComanda.equals(other.nIdComanda))
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
		return "Xec [nId=" + nId + ", nNumPunts=" + nNumPunts
				+ ", dDataCaducitat=" + dDataCaducitat + ", nIdComanda="
				+ nIdComanda + ", nIdClient=" + nIdClient + "]";
	}

}
