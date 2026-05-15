package com.soc.ewok.model;

import java.sql.Timestamp;

/**
 * Clase que representa el pagament de la comanda.
 * Els usuaris podran tenir informacio sobre el pagamente de la comanda, com
 * Quantitat, forma de pagament i data del pagament. 
 * @author CarlosM
 * 
 *
 */
public class Pagament {
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagament other = (Pagament) obj;
		if (Data == null) {
			if (other.Data != null)
				return false;
		} else if (!Data.equals(other.Data))
			return false;
		if (IdFormaPagament == null) {
			if (other.IdFormaPagament != null)
				return false;
		} else if (!IdFormaPagament.equals(other.IdFormaPagament))
			return false;
		if (Quantitat == null) {
			if (other.Quantitat != null)
				return false;
		} else if (!Quantitat.equals(other.Quantitat))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (nIdComanda == null) {
			if (other.nIdComanda != null)
				return false;
		} else if (!nIdComanda.equals(other.nIdComanda))
			return false;
		return true;
	}

	private Long nId;
	private Long nIdComanda;
	private Float Quantitat;
	private Long IdFormaPagament;
	private Timestamp Data;
	
	/**
	 * Retorna la id del Pagament
	 * @return L'id del Pagament, si el pagament no s'ha gravat a la bbdd,
	 *  retornara null
	 */
	public Long getnId() {
		return nId;
	}
	
	/**
	 * Assigna la id del Pagament
	 * @param nId L'id a assingar
	 */
	public void setnId(Long nId) {
		this.nId = nId;
	}
	
	/**
	 * Retorna la id de la Commanda que genera el pagament
	 * @return L'id de la Commanda que genera el pagament. 
	 */
	public Long getnIdComanda() {
		return nIdComanda;
	}
	
	/**
	 * Assigna la id de la Commanda que genera el pagament
	 * @param nIdComanda L'id a assignar
	 */
	public void setnIdComanda(Long nIdComanda) {
		if (nIdComanda == null) {
			throw new IllegalArgumentException("La Id de la Comanda ha d'estar informada");
		}
		this.nIdComanda = nIdComanda;
	}
	
	/**
	 * Retorna la Quantitat del Pagament
	 * @return La Quantitat del Pagament
	 */
	public Float getQuantitat() {
		return Quantitat;
	}
	
	/**
	 * Assigna la Quantitat del Pagament
	 * @param quantitat del Pagament
	 */
	public void setQuantitat(Float quantitat) {
		if (quantitat == null) {
			throw new IllegalArgumentException("La Quantitat ha d'estar informada");
		}
		Quantitat = quantitat;
	}
	
	/**
	 * Retorna la id de la Forma del Pagament
	 * @return la id de la Forma del Pagament del Pagament
	 */
	public Long getIdFormaPagament() {
		return IdFormaPagament;
	}
	
	/**
	 * Assigna la id de la Forma del Pagament
	 * @param La id de la Forma del Pagament
	 */
	public void setIdFormaPagament(Long idFormaPagament) {
		if (idFormaPagament == null) {
			throw new IllegalArgumentException("La forma de pagament ha d'estar informada");
		}
		IdFormaPagament = idFormaPagament;
	}
	
	/**
	 * Retorna la data del Pagament 
	 * @return Data la data del Pagament
	 */
	public Timestamp getData() {
		return Data;
	}
	
	/**
	 * Assigna la data en la que s'ha fet el Pagament 
	 * @param data La data en que s'ha fet el Pagament.
	 */
	public void setData(Timestamp data) {
		if (data == null) {
			throw new IllegalArgumentException("La data ha d'estar informada");
		}
		Data = data;
	
	}

	@Override
	public String toString() {
		return "Pagament [nId=" + nId + ", nIdComanda=" + nIdComanda
				+ ", Quantitat=" + Quantitat + ", IdFormaPagament="
				+ IdFormaPagament + ", Data=" + Data + "]";
	}
}
