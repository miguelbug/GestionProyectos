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
public class RIdatosEjecucion implements Serializable {

    private String mes;
    private String anio;
    private Double montoEjecu;
    private String componente;
    private String rordr;
    private String fecha;
    private Double montoPreli;

    public RIdatosEjecucion() {
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Double getMontoEjecu() {
        return montoEjecu;
    }

    public void setMontoEjecu(Double montoEjecu) {
        this.montoEjecu = montoEjecu;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getRordr() {
        return rordr;
    }

    public void setRordr(String rordr) {
        this.rordr = rordr;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMontoPreli() {
        return montoPreli;
    }

    public void setMontoPreli(Double montoPreli) {
        this.montoPreli = montoPreli;
    }

}
