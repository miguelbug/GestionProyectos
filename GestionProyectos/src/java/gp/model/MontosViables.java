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
public class MontosViables implements Serializable {

    private Double montoViab;
    private String fecha;
    
    public MontosViables() {
    }

    public Double getMontoViab() {
        return montoViab;
    }

    public void setMontoViab(Double montoViab) {
        this.montoViab = montoViab;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
