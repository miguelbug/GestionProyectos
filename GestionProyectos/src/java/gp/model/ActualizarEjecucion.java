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
public class ActualizarEjecucion implements Serializable {

    private Integer idejecu;
    private Double montoP;
    private Double montoE;
    private String documento;
    private Integer tiporor;

    public ActualizarEjecucion() {
    }

    public Integer getIdejecu() {
        return idejecu;
    }

    public void setIdejecu(Integer idejecu) {
        this.idejecu = idejecu;
    }

    public Double getMontoP() {
        return montoP;
    }

    public void setMontoP(Double montoP) {
        this.montoP = montoP;
    }

    public Double getMontoE() {
        return montoE;
    }

    public void setMontoE(Double montoE) {
        this.montoE = montoE;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getTiporor() {
        return tiporor;
    }

    public void setTiporor(Integer tiporor) {
        this.tiporor = tiporor;
    }

}
