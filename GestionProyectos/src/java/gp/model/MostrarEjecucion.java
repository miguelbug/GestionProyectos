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
public class MostrarEjecucion implements Serializable{

    private String mes;
    private String anio;
    private String montoPre;
    private String montoEje;
    private String documento;
    private String rdrror;
    private String fecha;
    private Integer idejecucion;
    
    public MostrarEjecucion() {
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

    public String getMontoPre() {
        return montoPre;
    }

    public void setMontoPre(String montoPre) {
        this.montoPre = montoPre;
    }

    public String getMontoEje() {
        return montoEje;
    }

    public void setMontoEje(String montoEje) {
        this.montoEje = montoEje;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRdrror() {
        return rdrror;
    }

    public void setRdrror(String rdrror) {
        this.rdrror = rdrror;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdejecucion() {
        return idejecucion;
    }

    public void setIdejecucion(Integer idejecucion) {
        this.idejecucion = idejecucion;
    }
    
}
