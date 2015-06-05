/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.model;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class EjecucionMostrado {

    private String mes;
    private String anio;
    private String monto;
    private String nombreTipoDocu;
    private String nombRoRdR;
    private String fecha;
    private String monto2;

    public EjecucionMostrado() {
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

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getNombreTipoDocu() {
        return nombreTipoDocu;
    }

    public void setNombreTipoDocu(String nombreTipoDocu) {
        this.nombreTipoDocu = nombreTipoDocu;
    }

    public String getNombRoRdR() {
        return nombRoRdR;
    }

    public void setNombRoRdR(String nombRoRdR) {
        this.nombRoRdR = nombRoRdR;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto2() {
        return monto2;
    }

    public void setMonto2(String monto2) {
        this.monto2 = monto2;
    }

}
