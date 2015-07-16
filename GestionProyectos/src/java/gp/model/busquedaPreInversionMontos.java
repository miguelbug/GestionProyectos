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
public class busquedaPreInversionMontos implements Serializable{

    private String exp_tecn;
    private String infraestructura;
    private String equip_mobil;
    private String supervision;
    private String capacitacion;
    private String otros;
    private String fecha_reg;
    private Integer idcomp;
    private Integer num_monto;
    
    public busquedaPreInversionMontos() {
    }

    public String getExp_tecn() {
        return exp_tecn;
    }

    public void setExp_tecn(String exp_tecn) {
        this.exp_tecn = exp_tecn;
    }

    public String getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(String infraestructura) {
        this.infraestructura = infraestructura;
    }

    public String getEquip_mobil() {
        return equip_mobil;
    }

    public void setEquip_mobil(String equip_mobil) {
        this.equip_mobil = equip_mobil;
    }

    public String getSupervision() {
        return supervision;
    }

    public void setSupervision(String supervision) {
        this.supervision = supervision;
    }

    public String getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(String capacitacion) {
        this.capacitacion = capacitacion;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public Integer getIdcomp() {
        return idcomp;
    }

    public void setIdcomp(Integer idcomp) {
        this.idcomp = idcomp;
    }

    public Integer getNum_monto() {
        return num_monto;
    }

    public void setNum_monto(Integer num_monto) {
        this.num_monto = num_monto;
    }
    
      
}
