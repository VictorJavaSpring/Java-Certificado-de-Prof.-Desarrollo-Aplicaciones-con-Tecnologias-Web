package com.soc.ewok.model;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que representa una element de l'entitat Producte
 * Un producte representa aliments base, condiments, complements,
 * plats o menus en l'aplicació eWok  
 * @author JordiM
 *
 */
@XmlRootElement
public class Producte {
	//vars membre de l'entitat producte
	private Long nId;
	private String sNom;
	private String sDescripcioCurta;
	private String sDescripcio;
	private Date dIniciVigencia;
	private Date dFiVigencia;
	private Long nIdUnitat; //FK amb l'entitat tipusUnitat
	private Long nIdTipusProducte; //FK amb l'entitat tipusProducte
	private List<ProducteComponent> components; //llistat de productes que composen un Producte de tipus Menu o Plat
	

	/**
	 * obté l'id d'un producte
	 * @return l'id del producte. Pot ser Null si el producte no s'ha gravat encara a BD 
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * assigna l'id d'un producte
	 * @param id. l'Id a assignar al producte
	 */
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * obté el nom del producte 
	 * @return el nom del producte
	 */
	public String getNom() {
		return sNom;
	}
	
	/**
	 * assigna el nom del producte
	 * @param nom. nom a assignar al producte
	 */
	public void setNom(String nom) {
		if (nom == null || nom.trim().equals("")) {
			throw new IllegalArgumentException("El nom ha d'estar informat");
		}
		sNom = nom;
	}
	
	/**
	 * obté la Descripcio Curta del producte
	 * @return la Descripcio Curta del producte
	 */
	public String getDescripcioCurta() {
		return sDescripcioCurta;
	}
	
	/**
	 * assigna la Descripcio Curta del producte
	 * @param descripcioCurta. Descripcio Curta a assignar al producte. Pot ser null
	 */
	public void setDescripcioCurta(String descripcioCurta) {
		sDescripcioCurta = descripcioCurta;
	}
	
	/**
	 * obté la descripció del producte
	 * @return la descripció del producte
	 */
	public String getDescripcio() {
		return sDescripcio;
	}
	
	/**
	 * assigna la descripció (llarga) del producte
	 * @param descripcio. descripció a assignar al producte. Pot ser null
	 */
	public void setDescripcio(String descripcio) {
		sDescripcio = descripcio;
	}
	
	/**
	 * obté la data d'inici de vigència del producte.
	 * @return la data d'inici de vigència del producte. Pot ser null quan el producte 
	 * està en implementació
	 */
	public Date getIniciVigencia() {
		return dIniciVigencia;
	}
	
	/**
	 * assigna la data d'inici de vigència del producte
	 * @param iniciVigencia. data d'inici de vigència del producte.
	 */
	public void setIniciVigencia(Date iniciVigencia) {
		dIniciVigencia = iniciVigencia;
	}
	
	/**
	 * obté la data de fi de vigència del producte.
	 * @return data de fi de vigència del producte.
	 */
	public Date getFiVigencia() {
		return dFiVigencia;
	}
	
	/**
	 * assigna la data de fi de vigència del producte. Pot ser null en els productes permanentment
	 * en catàleg
	 * @param fiVigencia. data de fi de vigència del producte.
	 */
	public void setFiVigencia(Date fiVigencia) {
		this.dFiVigencia = fiVigencia;
	}
	
	/**
	 * obté l'id de relació amb l'entitat unitat de mesura del producte
	 * @return l'id de relació amb l'entitat unitat de mesura del producte
	 */
	public Long getIdUnitat() {
		return nIdUnitat;
	}
	
	/**
	 * assigna l'id de relació amb l'entitat unitat de mesura del producte. No pot ser null i
	 * ha d'existir a la BD
	 * @param idUnitat. l'id de relació amb l'entitat 'Unitat' 
	 */
	public void setIdUnitat(Long idUnitat) {
		if (idUnitat == null){
			throw new IllegalArgumentException("El producte ha de tenir id d'Unitat");
		}
		this.nIdUnitat = idUnitat;
	}
	
	/**
	 * obté l'Id de relació de tipus de producte
	 * @return Id de 'TipusProducte'
	 */
	public Long getIdTipusProducte() {
		return nIdTipusProducte;
	}
	
	/**
	 * assigna id de relació amb tipus de producte
	 * @param idTipusProducte. l'id de relació amb l'entitat 'TipusProducte'
	 */
	public void setIdTipusProducte(Long idTipusProducte) {
		if(idTipusProducte == null){
			throw new IllegalArgumentException("El producte ha de tenir id de TipusProducte");
		}
		this.nIdTipusProducte = idTipusProducte;
	}
	
	/**
	 * Afegeix un ProducteComponent a la llista de compnents del Producte
	 * @param p Objecte de tipus ProducteComponent
	 * @return True si s'ha pogut afegir, False si l'objecte passat era null
	 */
	public boolean addComponent(ProducteComponent p){
		//comprovar si hi ha llista de components
		if (components == null){
			//si no n'hi ha. crear-la
			components = new Vector<ProducteComponent>();
		}
		//comprovar si ens passen producte o no
		if (p == null){
			// si és null retornem fals i no fem res més
			return false;
		}
		//afegim el producte amb el métode add de la classe List i
		//retornem el boolean resultat del metode.
		return components.add(p);
	}
	/**
	 * obtenir tots els components de un producte
	 * @return List de Productes de tipus ProducteComponent
	 */
	public List<ProducteComponent> getComponents() {
		//si l'objecte no té llista de components llençar excepció illegalState
		if (components == null){
			//throw new IllegalStateException("El producte "+ this.toString() +" no té components carregats");
		}
		return components;
	}

	/**
	 * Assignar una llista de components a un producte
	 * @param components List de Productes de tipus ProducteComponent
	 */
	public void setComponents(List<ProducteComponent> components) {
		//si l'objecte no té llista de components llençar excepció illegalState
		if (this.components == null){
			this.components = new Vector<ProducteComponent>();
		}
		this.components = components;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Producte other = (Producte) obj;
		if (components == null) {
			if (other.components != null) {
				return false;
			}
		} else if (!components.equals(other.components)) {
			return false;
		}
		if (dFiVigencia == null) {
			if (other.dFiVigencia != null) {
				return false;
			}
		} else if (!dFiVigencia.equals(other.dFiVigencia)) {
			return false;
		}
		if (dIniciVigencia == null) {
			if (other.dIniciVigencia != null) {
				return false;
			}
		} else if (!dIniciVigencia.equals(other.dIniciVigencia)) {
			return false;
		}
		if (nId == null) {
			if (other.nId != null) {
				return false;
			}
		} else if (!nId.equals(other.nId)) {
			return false;
		}
		if (nIdTipusProducte == null) {
			if (other.nIdTipusProducte != null) {
				return false;
			}
		} else if (!nIdTipusProducte.equals(other.nIdTipusProducte)) {
			return false;
		}
		if (nIdUnitat == null) {
			if (other.nIdUnitat != null) {
				return false;
			}
		} else if (!nIdUnitat.equals(other.nIdUnitat)) {
			return false;
		}
		if (sDescripcio == null) {
			if (other.sDescripcio != null) {
				return false;
			}
		} else if (!sDescripcio.equals(other.sDescripcio)) {
			return false;
		}
		if (sDescripcioCurta == null) {
			if (other.sDescripcioCurta != null) {
				return false;
			}
		} else if (!sDescripcioCurta.equals(other.sDescripcioCurta)) {
			return false;
		}
		if (sNom == null) {
			if (other.sNom != null) {
				return false;
			}
		} else if (!sNom.equals(other.sNom)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Producte [nId=" + nId + ", sNom=" + sNom
				+ ", sDescripcioCurta=" + sDescripcioCurta + ", sDescripcio="
				+ sDescripcio + ", dIniciVigencia=" + dIniciVigencia
				+ ", dFiVigencia=" + dFiVigencia + ", nIdUnitat=" + nIdUnitat
				+ ", nIdTipusProducte=" + nIdTipusProducte + ", components="
				+ components + "]";
	}
	

}
