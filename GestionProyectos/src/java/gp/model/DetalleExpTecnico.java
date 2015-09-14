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
public class DetalleExpTecnico implements Serializable {

    private String documento;
    private String fecha;
    private Double monto;
    private String resolucion;
    private Integer idhistorial;
    private Integer idnuevodoc;
    private Double montoDocu;

    public DetalleExpTecnico() {
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public Integer getIdnuevodoc() {
        return idnuevodoc;
    }

    public void setIdnuevodoc(Integer idnuevodoc) {
        this.idnuevodoc = idnuevodoc;
    }

    public Double getMontoDocu() {
        return montoDocu;
    }

    public void setMontoDocu(Double montoDocu) {
        this.montoDocu = montoDocu;
    }

}
