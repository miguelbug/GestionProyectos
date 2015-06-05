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
public class NuevosDocumentos implements Serializable{

    private Double monto;
    private String resolucion;
    private Date fecha;
    private String exptecn;
    private Integer tipodocu;
    private Integer numeroDocu;
    private String idProy;
    private String codigoContrato;
    private Integer etapa;
    
    public NuevosDocumentos() {
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getExptecn() {
        return exptecn;
    }

    public void setExptecn(String exptecn) {
        this.exptecn = exptecn;
    }

    public Integer getTipodocu() {
        return tipodocu;
    }

    public void setTipodocu(Integer tipodocu) {
        this.tipodocu = tipodocu;
    }

    public Integer getNumeroDocu() {
        return numeroDocu;
    }

    public void setNumeroDocu(Integer numeroDocu) {
        this.numeroDocu = numeroDocu;
    }

    public String getIdProy() {
        return idProy;
    }

    public void setIdProy(String idProy) {
        this.idProy = idProy;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    @Override
    public String toString() {
        return "NuevosDocumentos{" + "monto=" + monto + ", resolucion=" + resolucion + ", fecha=" + fecha + ", exptecn=" + exptecn + ", tipodocu=" + tipodocu + ", numeroDocu=" + numeroDocu + ", idProy=" + idProy + ", codigoContrato=" + codigoContrato + '}';
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    
    
}
