/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class RIdatosProyecto implements Serializable{

    private Integer proyectoCodigo;
    private String nombreProyecto;
    private Double montoviab;
    private Double montoModif;
    private String etapa;
    
    public RIdatosProyecto() {
    }

    public Integer getProyectoCodigo() {
        return proyectoCodigo;
    }

    public void setProyectoCodigo(Integer proyectoCodigo) {
        this.proyectoCodigo = proyectoCodigo;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Double getMontoviab() {
        return montoviab;
    }

    public void setMontoviab(Double montoviab) {
        this.montoviab = montoviab;
    }

    public Double getMontoModif() {
        return montoModif;
    }

    public void setMontoModif(Double montoModif) {
        this.montoModif = montoModif;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
    
}
