package com.soc.ewok.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que exten Producte i hi afegeix var membre necessàries
 * per a mostrar les dades dels Productes Compostos [Menu, Plat]
 * @author JordiM
 *
 */
@XmlRootElement
public class ProducteComponent extends Producte {

	private Float nPreu; //preu del producte
	private Integer quantitat; //quantitat de component
	
	public Float getPreu() {
		return nPreu;
	}

	public void setPreu(Float nPreu) {
		this.nPreu = nPreu;
	}

	
	public Integer getQuantitat() {
		return quantitat;
	}

	public void setQuantitat(Integer quantitat) {
		this.quantitat = quantitat;
	}

	@Override
	public String toString() {
		return super.toString() + "ProducteComponent [nPreu=" + nPreu + ", quantitat=" + quantitat
				+ "]";
	}

}
