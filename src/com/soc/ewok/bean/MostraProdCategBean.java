package com.soc.ewok.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.soc.ewok.controller.EWokController;
import com.soc.ewok.dao.ProducteDAO;
import com.soc.ewok.model.Producte;

@ManagedBean
@ViewScoped
public class MostraProdCategBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codi;
    private List<Producte> productesFiltrats;
    private boolean errorNoProd;
    private boolean errorParamCodi;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        codi = params.get("codi");// 01, 02 , 03
        if (codi == null || codi.trim().isEmpty()) {
            errorParamCodi = true;
            productesFiltrats = Collections.emptyList();
            errorNoProd = true;
            return;
        }

        try {
            ProducteDAO dao = new ProducteDAO(EWokController.getGlobalDatasource());
            productesFiltrats = dao.obtenirTotsXTipusIdVigents(codi);
            errorNoProd = productesFiltrats == null || productesFiltrats.isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            productesFiltrats = Collections.emptyList();
            errorNoProd = true;
        }
    }

    public String getCodi() {
        return codi;
    }

    public List<Producte> getProductesFiltrats() {
        return productesFiltrats;
    }

    public boolean isErrorNoProd() {
        return errorNoProd;
    }

    public boolean isErrorParamCodi() {
        return errorParamCodi;
    }
}
