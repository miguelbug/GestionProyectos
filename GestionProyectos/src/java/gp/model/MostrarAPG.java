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
public class MostrarAPG implements Serializable{

    private String codigo;
    private String nombre;
    private String origen;
    private Double montoViabilidad;
    private String fechaViabilidad;
    private String nivelEstudio;
    
    public MostrarAPG() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getFechaViabilidad() {
        return fechaViabilidad;
    }

    public void setFechaViabilidad(String fechaViabilidad) {
        this.fechaViabilidad = fechaViabilidad;
    }

    public Double getMontoViabilidad() {
        return montoViabilidad;
    }

    public void setMontoViabilidad(Double montoViabilidad) {
        this.montoViabilidad = montoViabilidad;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }
    
}
