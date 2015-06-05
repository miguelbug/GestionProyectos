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
public class Ejecucion implements Serializable {

    private Integer idEjecucion;
    private String mes;
    private Integer anio;
    private Double monto;
    private Integer idTiposDocus;
    private Integer idRORDR;
    private Date fecha;
    private Double monto2;
    private Integer idProyectoExpt;

    public Ejecucion() {
    }

    public Ejecucion(String mes, Integer anio, Double monto, Integer idTiposDocus, Integer idRORDR, Date fecha, Double monto2, Integer idProyectoExpt) {
        this.mes = mes;
        this.anio = anio;
        this.monto = monto;
        this.idTiposDocus = idTiposDocus;
        this.idRORDR = idRORDR;
        this.fecha = fecha;
        this.monto2 = monto2;
        this.idProyectoExpt = idProyectoExpt;
    }
    
    public Integer getIdEjecucion() {
        return idEjecucion;
    }

    public void setIdEjecucion(Integer idEjecucion) {
        this.idEjecucion = idEjecucion;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getIdTiposDocus() {
        return idTiposDocus;
    }

    public void setIdTiposDocus(Integer idTiposDocus) {
        this.idTiposDocus = idTiposDocus;
    }

    public Integer getIdRORDR() {
        return idRORDR;
    }

    public void setIdRORDR(Integer idRORDR) {
        this.idRORDR = idRORDR;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto2() {
        return monto2;
    }

    public void setMonto2(Double monto2) {
        this.monto2 = monto2;
    }

    public Integer getIdProyectoExpt() {
        return idProyectoExpt;
    }

    public void setIdProyectoExpt(Integer idProyectoExpt) {
        this.idProyectoExpt = idProyectoExpt;
    }

}
