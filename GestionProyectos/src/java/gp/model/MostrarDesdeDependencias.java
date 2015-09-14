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
public class MostrarDesdeDependencias implements Serializable {

    private String codigo;
    private String nombre;
    private String nivelEstud;
    private Double montoViab;
    private Double montoTotal;
    
    public MostrarDesdeDependencias() {
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

    public String getNivelEstud() {
        return nivelEstud;
    }

    public void setNivelEstud(String nivelEstud) {
        this.nivelEstud = nivelEstud;
    }

    public Double getMontoViab() {
        return montoViab;
    }

    public void setMontoViab(Double montoViab) {
        this.montoViab = montoViab;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
    
}
