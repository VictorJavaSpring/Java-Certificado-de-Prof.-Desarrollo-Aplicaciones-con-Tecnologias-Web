package com.soc.ewok.model;

public class ProducteRelacions extends Producte {

	private Float nPreu; //preu del producte
	
	public Float getPreu() {
		return nPreu;
	}

	public void setPreu(Float nPreu) {
		this.nPreu = nPreu;
	}

	@Override
	public String toString() {
		return super.toString() + " [nPreu=" + nPreu + "]";
	}

}
