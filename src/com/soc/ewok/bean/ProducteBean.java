package com.soc.ewok.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.model.Producte;

@ManagedBean
@ViewScoped
public class ProducteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Producte> productes;

    @PostConstruct
    public void init() {
        try {

        ProducteDAO dao =
            new ProducteDAO(EWokController.getGlobalDatasource());

			productes = dao.obtenirTots();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public List<Producte> getProductes() {
        return productes;
    }

    public void setProductes(List<Producte> productes) {
        this.productes = productes;
    }
}