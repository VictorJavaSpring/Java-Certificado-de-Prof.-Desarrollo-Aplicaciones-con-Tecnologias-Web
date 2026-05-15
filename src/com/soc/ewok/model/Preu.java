package com.soc.ewok.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que representa un preu
 * @author Xavier
 *
 */
@XmlRootElement
public class Preu {
	private Long nId;
	private Float nPreu;
	private Date dIniciVigencia;
	private Date dFinalVigencia;
	private Long nIdProducte;
	
	
	/**
	 * Retorna l'id del preu.
	 * @return L'id del preu. Si el preu no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id de preu.
	 * @param id L'id a assignar.
	 */
	public void setId(Long id) {
		this.nId = id;
	}
	
	/**
	 * Retorna el preu.
	 * @return El preu.
	 */
	public Float getPreu() {
		return nPreu;
	}
	
	/**
	 * Assigna el preu.
	 * @param preu. El preu a assignar.
	 */
	public void setPreu(Float preu) {
		if (preu == null) {
			throw new IllegalArgumentException("El preu ha d'estar informat");
		}
		this.nPreu = preu;
	}
	
	/**
	 * Retorna la data d'inici de vigència.
	 * @return la data d'incici de vigència.
	 */
	public Date getIniciVigencia() {
		return dIniciVigencia;
	}
	
	/**
	 * Assigna la data d'inici de vigència.
	 * @param iniciVigencia. La data d'inici de vigència a assignar.
	 */
	public void setIniciVigencia(Date iniciVigencia) {
		this.dIniciVigencia = iniciVigencia;
	}
	
	/**
	 * Retorna la data de final de vigència.
	 * @return la data de final de vigència.
	 */
	public Date getFinalVigencia() {
		return dFinalVigencia;
	}
	
	/**
	 * Assigna la data de final de vigència.
	 * @param finalVigencia. La data de final de vigència a assignar.
	 */
	public void setFinalVigencia(Date finalVigencia) {
		this.dFinalVigencia = finalVigencia;
	}
	
	/**
	 * Retorna l'id del producte.
	 * @return l'id del producte. Si el producte no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getIdProducte() {
		return nIdProducte;
	}
	
	/**
	 * Assigna l'id de producte.
	 * @param idProducte. L'id de producte a assignar.
	 */
	public void setIdProducte(Long idProducte) {
		this.nIdProducte = idProducte;
	}

	
	/**
	 *  @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preu other = (Preu) obj;
		if (dFinalVigencia == null) {
			if (other.dFinalVigencia != null)
				return false;
		} else if (!dFinalVigencia.equals(other.dFinalVigencia))
			return false;
		if (dIniciVigencia == null) {
			if (other.dIniciVigencia != null)
				return false;
		} else if (!dIniciVigencia.equals(other.dIniciVigencia))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (nIdProducte == null) {
			if (other.nIdProducte != null)
				return false;
		} else if (!nIdProducte.equals(other.nIdProducte))
			return false;
		if (nPreu == null) {
			if (other.nPreu != null)
				return false;
		} else if (!nPreu.equals(other.nPreu))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Preu [nId=" + nId + ", nPreu=" + nPreu + ", dIniciVigencia="
				+ dIniciVigencia + ", dFinalVigencia=" + dFinalVigencia
				+ ", nIdProducte=" + nIdProducte + "]";
	}
	
	
}
