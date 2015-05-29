/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class AspectosGenerales implements Serializable{

    private Integer codigo;
    private String nombre;
    private Integer origen;
    private Double montoViabilidad;
    private Date fechaViabilidad;
    private String informeTecnico;
    private String expedAdm;
    private String numRR;
    private Integer opiResp;
    private Integer nivEstud;
    private Integer usuario;
    private String monto;
    
    public AspectosGenerales() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Double getMontoViabilidad() {
        return montoViabilidad;
    }

    public void setMontoViabilidad(Double montoViabilidad) {
        this.montoViabilidad = montoViabilidad;
    }
    
    public Date getFechaViabilidad() {
        return fechaViabilidad;
    }

    public void setFechaViabilidad(Date fechaViabilidad) {
        this.fechaViabilidad = fechaViabilidad;
    }

    public String getInformeTecnico() {
        return informeTecnico;
    }

    public void setInformeTecnico(String informeTecnico) {
        this.informeTecnico = informeTecnico;
    }

    public String getExpedAdm() {
        return expedAdm;
    }

    public void setExpedAdm(String expedAdm) {
        this.expedAdm = expedAdm;
    }

    public String getNumRR() {
        return numRR;
    }

    public void setNumRR(String numRR) {
        this.numRR = numRR;
    }

    public Integer getOpiResp() {
        return opiResp;
    }

    public void setOpiResp(Integer opiResp) {
        this.opiResp = opiResp;
    }

    public Integer getNivEstud() {
        return nivEstud;
    }

    public void setNivEstud(Integer nivEstud) {
        this.nivEstud = nivEstud;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    
}
