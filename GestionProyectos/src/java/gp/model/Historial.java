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
public class Historial implements Serializable {

    private Double monto;
    private String resolucion;
    private Date fecha;
    private String urlRR;
    private Integer idproy;
    private Integer idnuevodoc;
    
    public Historial() {
    }

    public Historial(Double monto, String resolucion, Date fecha, String urlRR, Integer idproy, Integer idnuevodoc) {
        this.monto = monto;
        this.resolucion = resolucion;
        this.fecha = fecha;
        this.urlRR = urlRR;
        this.idproy = idproy;
        this.idnuevodoc = idnuevodoc;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUrlRR() {
        return urlRR;
    }

    public void setUrlRR(String urlRR) {
        this.urlRR = urlRR;
    }

    public Integer getIdproy() {
        return idproy;
    }

    public void setIdproy(Integer idproy) {
        this.idproy = idproy;
    }

    public Integer getIdnuevodoc() {
        return idnuevodoc;
    }

    public void setIdnuevodoc(Integer idnuevodoc) {
        this.idnuevodoc = idnuevodoc;
    }
    
    
}
