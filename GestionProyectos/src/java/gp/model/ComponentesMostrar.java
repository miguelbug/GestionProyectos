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
public class ComponentesMostrar implements Serializable {

    private Integer idcomp;
    private Double expTecn;
    private Double infraEstruct;
    private Double equipMob;
    private Double superVision;
    private Double capaciT;
    private Double otros;
    private String fechaModif;
    private Double montoModif;

    public ComponentesMostrar() {
    }

    public Integer getIdcomp() {
        return idcomp;
    }

    public void setIdcomp(Integer idcomp) {
        this.idcomp = idcomp;
    }

    public Double getExpTecn() {
        return expTecn;
    }

    public void setExpTecn(Double expTecn) {
        this.expTecn = expTecn;
    }

    public Double getInfraEstruct() {
        return infraEstruct;
    }

    public void setInfraEstruct(Double infraEstruct) {
        this.infraEstruct = infraEstruct;
    }

    public Double getEquipMob() {
        return equipMob;
    }

    public void setEquipMob(Double equipMob) {
        this.equipMob = equipMob;
    }

    public Double getSuperVision() {
        return superVision;
    }

    public void setSuperVision(Double superVision) {
        this.superVision = superVision;
    }

    public Double getCapaciT() {
        return capaciT;
    }

    public void setCapaciT(Double capaciT) {
        this.capaciT = capaciT;
    }

    public Double getOtros() {
        return otros;
    }

    public void setOtros(Double otros) {
        this.otros = otros;
    }

    public String getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(String fechaModif) {
        this.fechaModif = fechaModif;
    }

    public Double getMontoModif() {
        return montoModif;
    }

    public void setMontoModif(Double montoModif) {
        this.montoModif = montoModif;
    }

}
