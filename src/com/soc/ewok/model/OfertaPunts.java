package com.soc.ewok.model;

import java.util.Date;

/**
 * @author Edu
 * Clase que permet modificar l'oferta per punts i xecs 
 * L'usuari pot modificar 
 * els euros per punt, 
 * els punts per xec,
 * els dies de vigencia del punts i dels xecs,
 * el inici i el fi de la vigencia de l'oferta.
 */

public class OfertaPunts {
	
	
	private Long nId;
	private Float fEurosPerPunt;
	private Short nPuntsPerXec;
	private Long dDiesVigenciaPunts;
	private Long dDiesVigenciaXecs;
	private Date dIniciVigencia;
	private Date dFiVigencia;
	
	/**
	 * Retorna l'id de OfertaPunts.
	 * @return L'id de OferaPunts. Si OfertaPunts no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id de OfertaPunts.
	 * @param id L'id a assignar.
	 * 
	 */
	
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * Retorna Euros Per punt 
	 * @return EurosPerPunt. Si OfertaPunts no s'ha grabat a base de dades,
	 * retornarà null. 
	 */

	
	public Float getEurosPerPunt() {
		return fEurosPerPunt;
	}
	
	/**
	 * Assigna: Euros Per punt 
	 * @param : EurosPerPunt.
	 * 
	 */
	
	
	public void setEurosPerPunt(Float eurosPerPunt) {
		
		if (eurosPerPunt == null) {
			throw new IllegalArgumentException("Euros Per Punt ha d'estar informat");
		}
		this.fEurosPerPunt = eurosPerPunt;
				
		}
		
	/**
	 * Retorna Punts per Xec
	 * @return Punts per Xec. Si OfertaPunts no s'ha grabat a base de dades,
	 * retornarà null. 
	 */

	public Short getPuntsPerXec() {
		return nPuntsPerXec;
	}
	
	/**
	 * Assigna: PuntsPerXec
	 * @param PuntsPerXec 
	 * 
	 */
	
	public void setPuntsPerXec(Short puntsPerXec) {
	
		if (puntsPerXec == null) {
			throw new IllegalArgumentException("Punts per Xec ha d'estar informat");
		}
		
		this.nPuntsPerXec = puntsPerXec ;
	}
	
	/**
	 * Retorna DiesVigenciaPunts
	 * @return DiesVigenciaPunts Si OfertaPunts no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getDiesVigenciaPunts() {
		return dDiesVigenciaPunts;
	}
	
	/**
	 * Assigna: DiesVigenciaPunts
	 * @param DiesVigenciaPunts
	 * 
	 */
	public void setDiesVigenciaPunts(Long diesVigenciaPunts) {
		
		if (diesVigenciaPunts == null) {
			throw new IllegalArgumentException("Dies Vigencia Punts ha d'estar informat");
		}
		
		this.dDiesVigenciaPunts= diesVigenciaPunts;
		}
	
	/**
	 * Retorna : DiesVigenciaXecs
	 * @return DiesVigenciaXecs. Si OfertaPunts no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getDiesVigenciaXecs() {
		return dDiesVigenciaXecs;
	}
	
	/**
	 * Assigna: DiesVigenciaXecs
	 * @param DiesVigenciaXecs
	 * 
	 */
	public void setDiesVigenciaXecs(Long diesVigenciaXecs) {
	
		if (diesVigenciaXecs == null) {
			throw new IllegalArgumentException("Dies Vigencia Xecs ha d'estar informat");
		
		}
		
		this.dDiesVigenciaXecs = diesVigenciaXecs;
	}
	/**
	 * Retorna : DiesIniciVigencia.
	 * @return DiesIniciVigencia. Si OfertaPunts no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Date getIniciVigencia() {
		return dIniciVigencia;
	}
	
	/**
	 * Assigna: DiesIniciVigencia.
	 * @param DiesIniciVigencia
	 * 
	 */
	public void setIniciVigencia(Date iniciVigencia) {
		
		/*if (iniciVigencia == null) {
			throw new IllegalArgumentException("Inici de Vigència ha d'estar informat");
		}*/
	
		dIniciVigencia = iniciVigencia;
	}
	
	/**
	 * Retorna : FiVigencia
	 * @return FiVigencia. Si OfertaPunts no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Date getFiVigencia() {
		return dFiVigencia;
	}
	
	/**
	 * Assigna: FiVigencia.FiVigencia
	 * @param FiVigencia
	 * 
	 */
	public void setFiVigencia(Date fiVigencia) {
		
		if (fiVigencia == null) {
			throw new IllegalArgumentException("Fi Vigencia ha d'estar informat");
		}
		
		dFiVigencia = fiVigencia;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertaPunts other = (OfertaPunts) obj;
		if (dDiesVigenciaPunts == null) {
			if (other.dDiesVigenciaPunts != null)
				return false;
		} else if (!dDiesVigenciaPunts.equals(other.dDiesVigenciaPunts))
			return false;
		if (dDiesVigenciaXecs == null) {
			if (other.dDiesVigenciaXecs != null)
				return false;
		} else if (!dDiesVigenciaXecs.equals(other.dDiesVigenciaXecs))
			return false;
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
		if (fEurosPerPunt == null) {
			if (other.fEurosPerPunt != null)
				return false;
		} else if (!fEurosPerPunt.equals(other.fEurosPerPunt))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (nPuntsPerXec == null) {
			if (other.nPuntsPerXec != null)
				return false;
		} else if (!nPuntsPerXec.equals(other.nPuntsPerXec))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfertaPunts [nId=" + nId + ", fEurosPerPunt=" + fEurosPerPunt
				+ ", nPuntsPerXec=" + nPuntsPerXec + ", dDiesVigenciaPunts="
				+ dDiesVigenciaPunts + ", dDiesVigenciaXecs="
				+ dDiesVigenciaXecs + ", dIniciVigencia=" + dIniciVigencia
				+ ", dFiVigencia=" + dFiVigencia + "]";
	}


}
