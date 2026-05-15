package com.soc.ewok.model;

import java.util.Date;

/**
 * Classe que representa les ofertes que s'aplicaran a la comanda
 * S'expressa en un % de descompte en funció del preu
 * i cada oferta tindrà data d'inici i fi de vigència
 * @author Nacho
 *
 */
public class OfertaComanda {
	private Long nId;
	private Float nLimitInferior;
	private Float nPctDescompte;
	private Date dIniciVigencia;
	private Date dFiVigencia;
	
	/**
	 * Retorna l'id de l'oferta que s'aplica a la comanda.
	 * @return L'id de l'ofertaComanda. 
	 * Retornarà null si l'oferta no queda en la base de dades,
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id a l'oferta.
	 * @param id és l'id que s'assignarà a la oferta.
	 * 
	 */
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * Retorna el límit inferior de l'oferta
	 * @return limitInferior és el límit inferior que té l'oferta
	 */
	public Float getLimitInferior() {
		return nLimitInferior;
	}
	
	/**
	 * Assigna un límit inferior a la oferta de comanda.
	 * @param limitInferior és el límit inferior que s'assignarà
	 * @throws IllegalArgumentException En cas que el límit sigui null o negatiu
	 */
	public void setLimitInferior(Float limitInferior) {
		if (limitInferior == null || limitInferior < 0) {
			throw new IllegalArgumentException("El límit inferior ha de ser un número positiu");
		}
		nLimitInferior = limitInferior;
	}
	
	/**
	 * Retorna el percentatge de descompte de l'oferta
	 * @return pctDescompte és el % de descompte que té l'oferta
	 */
	public Float getPctDescompte() {
		return nPctDescompte;
	}
	
	/**
	 * Assigna un percentatge de descompte de l'oferta
	 * @param pctDescompte és el % de descompte que s'assignarà
	 * @throws IllegalArgumentException En cas que el % sigui null o negatiu
	 */
	public void setPctDescompte(Float pctDescompte) {
		if (pctDescompte == null || pctDescompte < 0) {
			throw new IllegalArgumentException("El percentatge de descompte ha de ser un número positiu");
		}
		nPctDescompte = pctDescompte;
	}
	
	/**
	 * 
	 * Retorna la data d'inici de vigència de l'oferta
	 * @return iniciVigencia és la data d'inici de vigència de l'oferta
	 */
	public Date getIniciVigencia() {
		return dIniciVigencia;
	}
	
	/**
	 * Assigna una data d'inici de vigència a l'oferta
	 * @param iniciVigencia és la data en que entra en vigor l'oferta
	 * @throws IllegalArgumentException En cas que la data sigui null o buida
	 */
	public void setIniciVigencia(Date iniciVigencia) {
		if (iniciVigencia == null || iniciVigencia.equals("")) {
			throw new IllegalArgumentException("La data d'inici de vigència ha d'estar informada");
		}
		dIniciVigencia = iniciVigencia;
	}
	
	/**
	 * 
	 * Retorna la data de fi de vigència de l'oferta
	 * @return fiVigencia és la data de finalització de vigència de l'oferta
	 */
	public Date getFiVigencia() {
		return dFiVigencia;
	}
	
	/**
	 * Assigna una data de fi de vigència a l'oferta
	 * @param fiVigencia és la data en que finalitza l'oferta
	 * @throws IllegalArgumentException En cas que la data sigui null o buida
	 */
	public void setFiVigencia(Date fiVigencia) {
		if (fiVigencia == null || fiVigencia.equals("")) {
			throw new IllegalArgumentException("La data de fi de vigència ha d'estar informada");
		}
		dFiVigencia = fiVigencia;
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
		OfertaComanda other = (OfertaComanda) obj;
		if (dFiVigencia == null) {
			if (other.dFiVigencia != null)
				return false;
		} else if (!dFiVigencia.equals(other.dFiVigencia))
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
		if (nLimitInferior == null) {
			if (other.nLimitInferior != null)
				return false;
		} else if (!nLimitInferior.equals(other.nLimitInferior))
			return false;
		if (nPctDescompte == null) {
			if (other.nPctDescompte != null)
				return false;
		} else if (!nPctDescompte.equals(other.nPctDescompte))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfertaComanda [nId=" + nId + ", nLimitInferior="
				+ nLimitInferior + ", nPctDescompte=" + nPctDescompte
				+ ", dIniciVigencia=" + dIniciVigencia + ", dFiVigencia="
				+ dFiVigencia + "]";
	}
	
}