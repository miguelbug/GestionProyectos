/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class Etapa {

    private String etapa;
    private Integer idproyexpt;
    public Etapa() {
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Integer getIdproyexpt() {
        return idproyexpt;
    }

    public void setIdproyexpt(Integer idproyexpt) {
        this.idproyexpt = idproyexpt;
    }
    
}
