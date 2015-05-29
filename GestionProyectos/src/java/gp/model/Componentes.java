/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class Componentes implements Serializable{

    private Double montoExpTec;
    private Double montoInfra;
    private Double montoEquipMov;
    private Double montoSuperv;
    private Double montoCapac;
    private Double montoOtros;
    private Date fecharegistro;
    private String tipoRegistro;
    private Integer codigoProy;
    private Double montoModif;
    private Integer numMonto;
    private Double montoaux;
    private Date fechaaux;
    private Integer idcomp;
    
    public Componentes() {
    }

    public Double getMontoExpTec() {
        return montoExpTec;
    }

    public void setMontoExpTec(Double montoExpTec) {
        this.montoExpTec = montoExpTec;
    }

    public Double getMontoInfra() {
        return montoInfra;
    }

    public void setMontoInfra(Double montoInfra) {
        this.montoInfra = montoInfra;
    }

    public Double getMontoEquipMov() {
        return montoEquipMov;
    }

    public void setMontoEquipMov(Double montoEquipMov) {
        this.montoEquipMov = montoEquipMov;
    }

    public Double getMontoSuperv() {
        return montoSuperv;
    }

    public void setMontoSuperv(Double montoSuperv) {
        this.montoSuperv = montoSuperv;
    }

    public Double getMontoCapac() {
        return montoCapac;
    }

    public void setMontoCapac(Double montoCapac) {
        this.montoCapac = montoCapac;
    }

    public Double getMontoOtros() {
        return montoOtros;
    }

    public void setMontoOtros(Double montoOtros) {
        this.montoOtros = montoOtros;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Integer getCodigoProy() {
        return codigoProy;
    }

    public void setCodigoProy(Integer codigoProy) {
        this.codigoProy = codigoProy;
    }

    public Double getMontoModif() {
        return montoModif;
    }

    public void setMontoModif(Double montoModif) {
        this.montoModif = montoModif;
    }

    public Integer getNumMonto() {
        return numMonto;
    }

    public void setNumMonto(Integer numMonto) {
        this.numMonto = numMonto;
    }

    public Double getMontoaux() {
        return montoaux;
    }

    public void setMontoaux(Double montoaux) {
        this.montoaux = montoaux;
    }

    public Date getFechaaux() {
        return fechaaux;
    }

    public void setFechaaux(Date fechaaux) {
        this.fechaaux = fechaaux;
    }

    public Integer getIdcomp() {
        return idcomp;
    }

    public void setIdcomp(Integer idcomp) {
        this.idcomp = idcomp;
    }

    @Override
    public String toString() {
        return "Componentes{" + "montoExpTec=" + montoExpTec + ", montoInfra=" + montoInfra + ", montoEquipMov=" + montoEquipMov + ", montoSuperv=" + montoSuperv + ", montoCapac=" + montoCapac + ", montoOtros=" + montoOtros + ", fecharegistro=" + fecharegistro + ", tipoRegistro=" + tipoRegistro + ", codigoProy=" + codigoProy + ", montoModif=" + montoModif + ", numMonto=" + numMonto + ", montoaux=" + montoaux + ", fechaaux=" + fechaaux + '}';
    }

   
}
