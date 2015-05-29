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
public class Ejecucion {

    private Integer idEjecucion;
    private String mes;
    private Integer anio;
    private Double monto;
    private Integer idProyecto;
    private Integer idTiposDocus;
    private Integer idRORDR;
    private Date fecha;  
    
    
    public Ejecucion() {
    }

    public Ejecucion(String mes, Integer anio, Double monto, Integer idProyecto, Integer idTiposDocus, Integer idRORDR, Date fecha) {
        this.mes = mes;
        this.anio = anio;
        this.monto = monto;
        this.idProyecto = idProyecto;
        this.idTiposDocus = idTiposDocus;
        this.idRORDR = idRORDR;
        this.fecha = fecha;
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

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
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

    
}
