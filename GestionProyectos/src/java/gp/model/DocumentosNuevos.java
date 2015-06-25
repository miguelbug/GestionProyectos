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
public class DocumentosNuevos implements Serializable{

    private Date fecha;
    private Double monto;
    private String resolucion;
    private Integer idhistorial;
    private String documento;
    private Integer idnuevodocu;
    
    public DocumentosNuevos() {
    }

    public DocumentosNuevos(Date fecha, Double monto, String resolucion, Integer idhistorial, String documento) {
        this.fecha = fecha;
        this.monto = monto;
        this.resolucion = resolucion;
        this.idhistorial = idhistorial;
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

    public Integer getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Integer idhistorial) {
        this.idhistorial = idhistorial;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getIdnuevodocu() {
        return idnuevodocu;
    }

    public void setIdnuevodocu(Integer idnuevodocu) {
        this.idnuevodocu = idnuevodocu;
    }
    
}
