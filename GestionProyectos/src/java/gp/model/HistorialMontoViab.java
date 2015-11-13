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
public class HistorialMontoViab implements Serializable{

    private Integer idhmv;
    private Integer num_monto;
    private Double  monto_viab;
    private Integer id_proy;
    private Date fecha;
    
    public HistorialMontoViab() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdhmv() {
        return idhmv;
    }

    public void setIdhmv(Integer idhmv) {
        this.idhmv = idhmv;
    }

    public Integer getNum_monto() {
        return num_monto;
    }

    public void setNum_monto(Integer num_monto) {
        this.num_monto = num_monto;
    }

    public Double getMonto_viab() {
        return monto_viab;
    }

    public void setMonto_viab(Double monto_viab) {
        this.monto_viab = monto_viab;
    }

    public Integer getId_proy() {
        return id_proy;
    }

    public void setId_proy(Integer id_proy) {
        this.id_proy = id_proy;
    }
    
}
