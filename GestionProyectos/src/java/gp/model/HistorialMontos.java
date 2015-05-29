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
public class HistorialMontos implements Serializable{

    private String montoviable;
    private String fecha;
    private String nombreMonto;
    
    public HistorialMontos() {
    }

    public String getMontoviable() {
        return montoviable;
    }

    public void setMontoviable(String montoviable) {
        this.montoviable = montoviable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreMonto() {
        return nombreMonto;
    }

    public void setNombreMonto(String nombreMonto) {
        this.nombreMonto = nombreMonto;
    }
    
}
