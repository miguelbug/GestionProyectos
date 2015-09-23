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
public class MostrarExpedientesTecnicos implements Serializable {

    private Integer idhistorial;
    private String documento;
    private Double monto;
    private String rr;
    private String fecha;
    private String montoModificado;
    private String numMonto;

    public MostrarExpedientesTecnicos() {
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Integer idhistorial) {
        this.idhistorial = idhistorial;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMontoModificado() {
        return montoModificado;
    }

    public void setMontoModificado(String montoModificado) {
        this.montoModificado = montoModificado;
    }

    public String getNumMonto() {
        return numMonto;
    }

    public void setNumMonto(String numMonto) {
        this.numMonto = numMonto;
    }
}
