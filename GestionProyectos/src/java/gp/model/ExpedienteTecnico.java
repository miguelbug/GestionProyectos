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
public class ExpedienteTecnico implements Serializable{

    private String documento;
    private Date fecha;
    private Double monto;
    private String resolucion;
    private Integer idpExptecn;
    private Integer idhistorial;
    private Integer cantidad;
    
    
    public ExpedienteTecnico() {
    }

    public ExpedienteTecnico(String documento, Date fecha, Double monto, String resolucion) {
        this.documento = documento;
        this.fecha = fecha;
        this.monto = monto;
        this.resolucion = resolucion;
    }

    public ExpedienteTecnico(String documento, Date fecha, Double monto, String resolucion, Integer idpExptecn) {
        this.documento = documento;
        this.fecha = fecha;
        this.monto = monto;
        this.resolucion = resolucion;
        this.idpExptecn = idpExptecn;
    }

    public ExpedienteTecnico(String documento, Date fecha, Double monto, String resolucion, Integer idpExptecn, Integer idhistorial) {
        this.documento = documento;
        this.fecha = fecha;
        this.monto = monto;
        this.resolucion = resolucion;
        this.idpExptecn = idpExptecn;
        this.idhistorial = idhistorial;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public Integer getIdpExptecn() {
        return idpExptecn;
    }

    public void setIdpExptecn(Integer idpExptecn) {
        this.idpExptecn = idpExptecn;
    }

    public Integer getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Integer idhistorial) {
        this.idhistorial = idhistorial;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
