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
public class GuardarNuevComp implements Serializable {

    private Double exp_tecn;
    private Double infraestructura;
    private Double equip_mobil;
    private Double supervision;
    private Double capacitacion;
    private Double otros;
    private Date fecha_reg;
    private Double monto_modif;
    private String tipo_reg;
    private String id_proyecto;
    private Integer num_monto;
    private Integer numeroExp;
    private String numeroRR;
    private String estado;
    private Integer tipoDocu;
    private Integer etapa;

    public GuardarNuevComp() {
    }

    public Integer getNumeroExp() {
        return numeroExp;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Integer getTipoDocu() {
        return tipoDocu;
    }

    public void setTipoDocu(Integer tipoDocu) {
        this.tipoDocu = tipoDocu;
    }

    public void setNumeroExp(Integer numeroExp) {
        this.numeroExp = numeroExp;
    }

    public String getNumeroRR() {
        return numeroRR;
    }

    public void setNumeroRR(String numeroRR) {
        this.numeroRR = numeroRR;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public String getTipo_reg() {
        return tipo_reg;
    }

    public void setTipo_reg(String tipo_reg) {
        this.tipo_reg = tipo_reg;
    }

    public String getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(String id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public Double getExp_tecn() {
        return exp_tecn;
    }

    public void setExp_tecn(Double exp_tecn) {
        this.exp_tecn = exp_tecn;
    }

    public Double getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(Double infraestructura) {
        this.infraestructura = infraestructura;
    }

    public Double getEquip_mobil() {
        return equip_mobil;
    }

    public void setEquip_mobil(Double equip_mobil) {
        this.equip_mobil = equip_mobil;
    }

    public Double getSupervision() {
        return supervision;
    }

    public void setSupervision(Double supervision) {
        this.supervision = supervision;
    }

    public Double getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(Double capacitacion) {
        this.capacitacion = capacitacion;
    }

    public Double getOtros() {
        return otros;
    }

    public void setOtros(Double otros) {
        this.otros = otros;
    }

    public Double getMonto_modif() {
        return monto_modif;
    }

    public void setMonto_modif(Double monto_modif) {
        this.monto_modif = monto_modif;
    }

    public Integer getNum_monto() {
        return num_monto;
    }

    public void setNum_monto(Integer num_monto) {
        this.num_monto = num_monto;
    }

}
