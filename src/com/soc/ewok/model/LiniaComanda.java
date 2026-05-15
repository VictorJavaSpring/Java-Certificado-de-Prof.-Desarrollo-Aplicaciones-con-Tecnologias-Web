package com.soc.ewok.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * class corresponent a la taula "Linia Comanda" de la BD "eWok"
 * 
 * @author JordiF
 *
 */
@XmlRootElement
public class LiniaComanda {
	private Long id;
	private Long linia;
	private Long idProducte;
	private Integer quantitat;
	private Float preuBrut;
	private Float preuVenda;
	private EEstatLiniaComanda eEstat;
	private Producte producte;
	private Comanda comanda;

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Producte getProducte() {
		return producte;
	}

	public void setProducte(Producte producte) {
		this.producte = producte;
	}
	
	/**
	 * constructor per defecte SENSE parametres
	 */
	public LiniaComanda() {
		eEstat = EEstatLiniaComanda.inicial;
	}

	/**
	 * constructor amb tots els parametres de la classe
	 * 
	 * @param id
	 * @param linia
	 * @param idProducte
	 * @param quantitat
	 * @param preuBrut
	 * @param preuVenda
	 * @param estat
	 * @return
	 */
	public LiniaComanda(Long id, Long linia, Long idProducte,
			Integer quantitat, Float preuBrut, Float preuVenda, EEstatLiniaComanda estat) {
		this.id = id;
		this.linia = linia;
		this.idProducte = idProducte;
		this.quantitat = quantitat;
		this.preuBrut = preuBrut;
		this.preuVenda = preuVenda;
		this.eEstat = estat;

	}

	/**
	 * retorna el Long Id de Linia Comanda si encara no s�havia guardat a BD pot
	 * retornar NULL
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * assigna l�Id de Linia (Comanda)
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("l�id ha de ser un long v�lid");
		}
		this.id = id;
	}

	/**
	 * retorna el Long de linia (comanda)
	 * 
	 * @return
	 */
	public Long getLinia() {

		return linia;
	}

	/**
	 * assigna el Long n�mero de linia(comanda)
	 * 
	 * @param linia
	 */
	public void setLinia(Long linia) {
		if (linia == null || linia.equals("")) {
			throw new IllegalArgumentException(
					"la linia ha de ser un long v�lid");
		}
		this.linia = linia;
	}

	/**
	 * retorna el Long IdProducte
	 * 
	 * @return
	 */
	public Long getIdProducte() {
		return idProducte;
	}

	/**
	 * assigna el Long idProducte
	 * 
	 * @param idProducte
	 */
	public void setIdProducte(Long idProducte) {
		if (idProducte == null || idProducte.equals("")) {
			throw new IllegalArgumentException(
					"idProducte ha de ser un long v�lid");
		}
		this.idProducte = idProducte;
	}

	/**
	 * retorna el Integer quantitat(de producte)
	 * 
	 * @return
	 */
	public Integer getQuantitat() {
		return quantitat;
	}

	/**
	 * assigna el Integer quantitat (de producte)
	 * 
	 * @param quantitat
	 */
	public void setQuantitat(Integer quantitat) {
		if (quantitat == null || quantitat.equals("")) {
			throw new IllegalArgumentException(
					"la quantitat ha de ser un Integer v�lid");
		}
		this.quantitat = quantitat;
	}

	/**
	 * retorna el Float preuBrut(de la linia)
	 * 
	 * @return
	 */
	public Float getPreuBrut() {
		return preuBrut;
	}

	/**
	 * assigna el Float preuBrut(de la linia)
	 * 
	 * @param preuBrut
	 */
	public void setPreuBrut(Float preuBrut) {
		if (preuBrut == null || preuBrut.equals("")) {
			throw new IllegalArgumentException(
					"el preuBrut ha de ser un Float v�lid");
		}
		this.preuBrut = preuBrut;
	}

	/**
	 * retorna el Float preuVenta (de la linia)
	 * 
	 * @return
	 */
	public Float getPreuVenda() {
		return preuVenda;
	}

	/**
	 * assigna el Float preuVenta (de la linia)
	 * 
	 * @param preuVenta
	 */
	public void setPreuVenda(Float preuVenda) {
		if (preuVenda == null || preuVenda.equals("")) {
			throw new IllegalArgumentException(
					"el preuVenta ha de ser un Float v�lid");
		}
		this.preuVenda = preuVenda;
	}

	/**
	 * retorna el Boolean estat(de la linia)
	 * 
	 * @return
	 */
	public EEstatLiniaComanda getEstat() {

		return eEstat;
	}

	/**
	 * assigna el Boolean estat(de la linia)
	 * 
	 * @param estat
	 */
	public void setEstat(EEstatLiniaComanda estat) {
		if (linia == null) {
			throw new IllegalArgumentException(
					"l�estat ha de ser un caracter v�lid");
		}
		this.eEstat = estat;
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
		LiniaComanda other = (LiniaComanda) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (linia == null) {
			if (other.linia != null)
				return false;
		} else if (!linia.equals(other.linia))
			return false;

		if (idProducte == null) {
			if (other.idProducte != null)
				return false;
		} else if (!idProducte.equals(other.idProducte))
			return false;

		if (quantitat == null) {
			if (other.quantitat != null)
				return false;
		} else if (!quantitat.equals(other.quantitat))
			return false;

		if (preuBrut == null) {
			if (other.preuBrut != null)
				return false;
		} else if (!preuBrut.equals(other.preuBrut))
			return false;

		if (preuVenda == null) {
			if (other.preuVenda != null)
				return false;
		} else if (!preuVenda.equals(other.preuVenda))
			return false;

		if (eEstat == null) {
			if (other.eEstat != null)
				return false;
		} else if (!eEstat.equals(other.eEstat))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "LiniaComanda [id=" + id + ", linia=" + linia + ", idProducte="
				+ idProducte + ", quantitat=" + quantitat + ", preuBrut="
				+ preuBrut + ", preuVenta=" + preuVenda + ", estat=" + eEstat
				+ "]";
	}

}
