package com.soc.ewok.model;

/**
 * Classe que representa un component
 * @author Xavier
 *
 */
public class Components {
	private Long nIdProducte;
	private Long nIdComponent;
	private Integer nQuantitat;
	private Integer nOrdre;
	
	/**
	 * Retorna l'id de producte.
	 * @return l'id de producte. Si el producte no s'ha grabat a base de dades,
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
	 * Retorna l'id de component.
	 * @return l'id de component. 
	 */
	public Long getIdComponent() {
		return nIdComponent;
	}
	
	/**
	 * Assigna l'id de component.
	 * @param idComponent. L'id de component a assignar.
	 */
	public void setIdComponent(Long idComponent) {
		this.nIdComponent = idComponent;
	}
	
	/**
	 * Retorna la quantitat.
	 * @return la quantitat.
	 */
	public Integer getQuantitat() {
		return nQuantitat;
	}
	
	/**
	 * Assigna la quantitat.
	 * @param quantitat. La quantitat a assignar.
	 */
	public void setQuantitat(Integer quantitat) {
		if (quantitat == null) {
			throw new IllegalArgumentException("La quantitat ha d'estar informada");
		}
		this.nQuantitat = quantitat;
	}
	
	/**
	 * Retorna l'ordre.
	 * @return l'ordre.
	 */
	public Integer getOrdre() {
		return nOrdre;
	}
	
	/**
	 * Assigna l'ordre.
	 * @param ordre. L'ordre a assignar.
	 */
	public void setOrdre(Integer ordre) {
		if (ordre == null) {
			throw new IllegalArgumentException("L'ordre ha d'estar informat");
		}
		this.nOrdre = ordre;
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
		Components other = (Components) obj;
		if (nIdComponent == null) {
			if (other.nIdComponent != null)
				return false;
		} else if (!nIdComponent.equals(other.nIdComponent))
			return false;
		if (nIdProducte == null) {
			if (other.nIdProducte != null)
				return false;
		} else if (!nIdProducte.equals(other.nIdProducte))
			return false;
		if (nOrdre == null) {
			if (other.nOrdre != null)
				return false;
		} else if (!nOrdre.equals(other.nOrdre))
			return false;
		if (nQuantitat == null) {
			if (other.nQuantitat != null)
				return false;
		} else if (!nQuantitat.equals(other.nQuantitat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Components [nIdProducte=" + nIdProducte + ", nIdComponent="
				+ nIdComponent + ", nQuantitat=" + nQuantitat + ", nOrdre="
				+ nOrdre + "]";
	} 
	
	
	
}
