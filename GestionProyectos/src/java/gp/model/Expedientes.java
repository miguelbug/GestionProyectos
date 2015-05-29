/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class Expedientes implements Serializable{
    private String tramnum;
    private String fecha;
    private String asunto;
    private String destino;
    private String origen;
    private String siglas;
    
    public Expedientes(){
        
    }
    
    public Expedientes(String tramnum, String fecha, String asunto, String destino, String origen, String siglas) {
        this.tramnum = tramnum;
        this.fecha = fecha;
        this.asunto = asunto;
        this.destino = destino;
        this.origen = origen;
        this.siglas = siglas;
    }

    public String getTramnum() {
        return tramnum;
    }

    public void setTramnum(String tramnum) {
        this.tramnum = tramnum;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    
}
