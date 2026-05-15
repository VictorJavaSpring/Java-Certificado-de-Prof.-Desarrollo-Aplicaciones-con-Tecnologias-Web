package com.soc.ewok.model;

/**
 * Classe que representa una comanda.
 * Cada Client pot tenir una o mes comandes.
 * Cada comanda nomes te un client.
 * Informacio de la Taula:
 * Table: Comanda
 * Columns:
 * 1 Id				int(10) UN AI PK
 * 2 IdClient			int(10) UN
 * 3 PuntsGenerats	tinyint(3) UN
 * 4 PctDescompte		decimal(5,2) UN
 * 5 Data				datetime
 * 6 AdrecaEntrega	int(10) UN
 * 7 Comentaris		text
 * 8 Estat			char(1)
 * 9 PreuFinal		decimal(6,2)
 * 
 * FK: CAAdreca
 * Def:Target Adreca (AdrecaEntrega -> Id)
 * FK: CAClient
 * Def:Target Client (IdClient -> Id)
 *
 * @author Victor
 * */

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.ProducteDAO;

@XmlRootElement
public class Comanda {
	private Long nId;
	private Long nIdClient;
	private Short sPunts;
	private Float fDescompte;
	private Date dData;
	private Long lAdreca;
	private String sComentaris;
	private EEstatComanda eEstatComanda;
	private Float fPreuFinal;
	private List<LiniaComanda> llistaLiniesComanda = null;
	private Producte producte = new Producte();
	private ProducteDAO producteDao = new ProducteDAO(EWokController.getGlobalDatasource());

	
	/**
	 * Constructor per defecte per posar l'estat inicial
	 */
	public Comanda() {
		eEstatComanda = EEstatComanda.enConstruccio;
	}
	
	/**
	 * Retorna l'id de la Comanda
	 * @return l'id de la Comanda. Si el rol no s'ha grabat a base de dades,
	 * retornara null.
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id de la Comanda.
	 * @param id L'id a assignar.
	 */
	public void setId(Long id) {
		this.nId = id;
	}
	
	/**
	 * Retorna l'id del Client que fa la Comanda
	 * @return l'id del Client que fa la Comanda
	 */
	public Long getIdClient() {
		return nIdClient;
	}
	
	/**
	 * Assigna l'id del Client que fa la Comanda
	 * @param idClient l'id del Client que fa la Comanda
	 */
	public void setIdClient(Long idClient) {
		nIdClient = idClient;
	}
	
	/**
	 * Retorna els punts generats per la Comanda
	 * @return els punts generats per la Comanda
	 */
	public Short getPunts() {
		return sPunts;
	}
	
	/**
	 * Assigna els punts que genera la Comanda
	 * @param punts que genera la Comanda
	 */
	public void setPunts(Short punts) {
		if (punts == null || punts.equals("")) {
			throw new IllegalArgumentException("Punts ha d'estar informat");
		}
		sPunts = punts;
	}
	
	/**
	 * Retorna el percentatge de descompte actual de la comanda
	 * @return el percentatge de descompte actual de la comanda
	 */
	public Float getDescompte() {
		return fDescompte;
	}
	
	/**
	 * Assigna el percentatge de descompte actual de la comanda
	 * @param el percentatge de descompte actual de la comanda
	 */
	public void setDescompte(Float descompte) {
		fDescompte = descompte;
	}
	
	/**
	 * Retorna la data de creacio de la Comanda
	 * @return la data de creacio
	 */
	public Date getData() {
		return dData;
	}
	
	/**
	 * Assigna la Data de creacio de la Comanda
	 * @param data de creacio
	 */
	public void setData(Date data) {
		if (data == null || data.equals("")) {
			throw new IllegalArgumentException("La data ha d'estar informat");
		}
		
		dData = data;
	}
	
	/**
	 * Retorna l'Adreca de la comanda
	 * @return l'Adreca de la comanda
	 */
	public Long getAdreca() {
		return lAdreca;
	}
	
	/**
	 * Assigna l'Adreca de la comanda
	 * @param adreca de la comanda
	 */
	public void setAdreca(Long adreca) {
		lAdreca = adreca;
	}
	
	/**
	 * Retorna els Comentaris sobre el Client que fa la comanda
	 * @return Comentaris sobre el Client que fa la comanda
	 */
	public String getComentaris() {
		return sComentaris;
	}
	
	/**
	 * Assigna els Comentaris del Client que fa la comanda
	 * @param els Comentaris del Client que fa la comanda
	 */
	public void setComentaris(String comentaris) {
		sComentaris = comentaris;
	}
	
	/**
	 * Retorna l'Estat de la comanda
	 * @return l'Estat de la comanda
	 */
	public EEstatComanda getEstat() {
		return eEstatComanda;
	}
	
	/**
	 * Assigna l'Estat de la comanda
	 * @param l'Estat de la comanda
	 */
	public void setEstat(EEstatComanda estatComanda){
		if (estatComanda == null) {
			throw new IllegalArgumentException("L Estat de la Comanda ha d'estar informada");
		}
		eEstatComanda = estatComanda;
	}
	
	/**
	 * Retorna el preu final de la comanda
	 * @return el preu final de la comanda
	 */
	public Float getPreuFinal() {
		return fPreuFinal;
	}
	
	/**
	 * Retorna el preu final de la comanda
	 * @param el preu final de la comanda
	 */
	public void setPreuFinal(Float preuFinal) {
		if (preuFinal == null || preuFinal.equals("")) {
			throw new IllegalArgumentException("El Preu Final ha d'estar informat");
		}
		fPreuFinal = preuFinal;
	}

	/**
	 * 
	 * @param prod
	 * @param quantitat
	 */
	public Integer afegirOEliminarProducte(Long producteId, Integer quantitat) {
		// Primer miro si hi ha linees de comanda a la llista, o si està buida
		llistaLiniesComanda = getLiniesComanda();
		if (llistaLiniesComanda == null) {
			// Si està buida, creo una linea de comanda amb el producte i la afegeixo a la llista
			addLiniaComanda(crearNovaLiniaComanda(producteId, quantitat));
			return quantitat;
		}
		Integer quantitatActual = -1;
		// Si no està buida, miro si ja existeix una linia de comanda per aquest producte
		for (LiniaComanda liComanda: llistaLiniesComanda) {
			if (liComanda.getIdProducte() == producteId) {
				// Si la tinc, actualitzo la quantitat
				quantitatActual = liComanda.getQuantitat() + quantitat;
				liComanda.setQuantitat(quantitatActual);
				// Si la quantitat queda a 0, elimino la linia
				if (quantitat < 0 && quantitatActual <= 0) {
					eliminarProducte(producteId);
					quantitatActual = 0;
					return quantitatActual;
				} else {
					return quantitatActual;
				}
			}
		}
		if (quantitat > 0) {
			// Si no tinc una linia de comanda pel producte, la creo i la afegeixo a la llista
			addLiniaComanda(crearNovaLiniaComanda(producteId, quantitat));
			return quantitat;
		} else {
			// Si la quantitat és < 0 vol dir que l'usuari ha intentat disminuir la cantitat
			// d'un producte que no existeix, per això retornem quantitatActual que serà < 0.
			return quantitatActual;
		}
	}
	
	private LiniaComanda crearNovaLiniaComanda(Long producteId, Integer quantitat) {
		LiniaComanda liniaComanda = new LiniaComanda();
		liniaComanda.setIdProducte(producteId);
		liniaComanda.setQuantitat(quantitat);
		// TODO: caldria calcular el preu bé a partir de les taules de preu
		Random r = new Random();
		liniaComanda.setPreuVenda(r.nextInt(12) + 1.0f);
		
		Producte producte = null;
		try {
			producte = producteDao.obtenirPerId(producteId);
			liniaComanda.setProducte(producte);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return liniaComanda;
	}

	public Boolean eliminarProducte(Long producteId) {
		// Busco la linia de comanda del producte
		llistaLiniesComanda = getLiniesComanda();
		for (LiniaComanda liComanda: llistaLiniesComanda) {
			if (liComanda.getIdProducte() == producteId) {
				// Si la trobo, l'elimino i retorno true
				llistaLiniesComanda.remove(liComanda);
				return true;
			}
		}
		// Si no s'ha esborrat, retornem false
		return false;
	}
	
	/**
	 * @param liniaComanda, és la linia de comanda que volem afegir a la llistaLiniesComanda
	 * @return TRUE si s'ha pogut afegir la liniaComanda a la llistaLiniesComanda, o FALSE, si no s'ha pogut afegir
	 */
	public boolean addLiniaComanda(LiniaComanda liniaComanda){
		// Comprovem si ja existeix la llistaLiniesComanda
		if (llistaLiniesComanda == null){
			// Si no existeix, la creem
			llistaLiniesComanda = new Vector<LiniaComanda>();
		}
		// Comprovem si ens passen la liniaComanda que hem d'afegir a la llista
		if (liniaComanda == null) {
			// Si no ens la passen, retornem false
			return false;
		}
		// Si ens la passen, afegim la liniaComanda a la llistaLiniesComanda
		// amb el métode add de la classe List i retornem el boolean resultant
		return llistaLiniesComanda.add(liniaComanda);
	}
	
	/**
	 * @return la llistaLiniesComanda si existeix
	 * si llistaLiniesComanda no existeix, llença una IllegalStateException
	 */
	public List<LiniaComanda> getLiniesComanda(){
		// Comprovem si existeix la llistaLiniesComanda
		if (llistaLiniesComanda == null) {
			// Si no existeix, llençem una IllegalStateException
			throw new IllegalStateException("La comanda amb id "+ this.getId() + " no té cap linia de comanda");
		}
		// Si existeix, la retornem
		return llistaLiniesComanda;
	}
	
	public Integer getLiniesComandaSize(){
		// Comprovem si existeix la llistaLiniesComanda
		if (llistaLiniesComanda == null) {
			// Si no existeix, llençem una IllegalStateException
			return 0;
		}
		// Si existeix, la retornem
		return llistaLiniesComanda.size();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dData == null) ? 0 : dData.hashCode());
		result = prime * result
				+ ((eEstatComanda == null) ? 0 : eEstatComanda.hashCode());
		result = prime * result
				+ ((fDescompte == null) ? 0 : fDescompte.hashCode());
		result = prime * result
				+ ((fPreuFinal == null) ? 0 : fPreuFinal.hashCode());
		result = prime * result + ((lAdreca == null) ? 0 : lAdreca.hashCode());
		result = prime
				* result
				+ ((llistaLiniesComanda == null) ? 0 : llistaLiniesComanda
						.hashCode());
		result = prime * result + ((nId == null) ? 0 : nId.hashCode());
		result = prime * result
				+ ((nIdClient == null) ? 0 : nIdClient.hashCode());
		result = prime * result
				+ ((sComentaris == null) ? 0 : sComentaris.hashCode());
		result = prime * result + ((sPunts == null) ? 0 : sPunts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comanda other = (Comanda) obj;
		if (dData == null) {
			if (other.dData != null)
				return false;
		} else if (!dData.equals(other.dData))
			return false;
		if (eEstatComanda != other.eEstatComanda)
			return false;
		if (fDescompte == null) {
			if (other.fDescompte != null)
				return false;
		} else if (!fDescompte.equals(other.fDescompte))
			return false;
		if (fPreuFinal == null) {
			if (other.fPreuFinal != null)
				return false;
		} else if (!fPreuFinal.equals(other.fPreuFinal))
			return false;
		if (lAdreca == null) {
			if (other.lAdreca != null)
				return false;
		} else if (!lAdreca.equals(other.lAdreca))
			return false;
		if (llistaLiniesComanda == null) {
			if (other.llistaLiniesComanda != null)
				return false;
		} else if (!llistaLiniesComanda.equals(other.llistaLiniesComanda))
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
		if (sComentaris == null) {
			if (other.sComentaris != null)
				return false;
		} else if (!sComentaris.equals(other.sComentaris))
			return false;
		if (sPunts == null) {
			if (other.sPunts != null)
				return false;
		} else if (!sPunts.equals(other.sPunts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comanda [nId=" + nId + ", nIdClient=" + nIdClient + ", sPunts="
				+ sPunts + ", fDescompte=" + fDescompte + ", dData=" + dData
				+ ", lAdreca=" + lAdreca + ", sComentaris=" + sComentaris
				+ ", eEstatComanda=" + eEstatComanda + ", fPreuFinal="
				+ fPreuFinal + ", llistaLiniesComanda=" + llistaLiniesComanda
				+ "]";
	}

}
