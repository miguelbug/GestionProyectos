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
public class MostrarTablaEjecucion implements Serializable{

    private String mes;
    private Double monto;
    private String tipodocu;
    private Integer idejecu;
    private String numerEjecu;
    private String anio;
    private String etapa;
    private Double c1;
    private Double c2;
    private Double c3;
    private Double c4;
    private Double c5;
    private Double c6;
    
    
    public MostrarTablaEjecucion() {
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getTipodocu() {
        return tipodocu;
    }

    public void setTipodocu(String tipodocu) {
        this.tipodocu = tipodocu;
    }

    public Integer getIdejecu() {
        return idejecu;
    }

    public void setIdejecu(Integer idejecu) {
        this.idejecu = idejecu;
    }

    public String getNumerEjecu() {
        return numerEjecu;
    }

    public void setNumerEjecu(String numerEjecu) {
        this.numerEjecu = numerEjecu;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Double getC1() {
        return c1;
    }

    public void setC1(Double c1) {
        this.c1 = c1;
    }

    public Double getC2() {
        return c2;
    }

    public void setC2(Double c2) {
        this.c2 = c2;
    }

    public Double getC3() {
        return c3;
    }

    public void setC3(Double c3) {
        this.c3 = c3;
    }

    public Double getC4() {
        return c4;
    }

    public void setC4(Double c4) {
        this.c4 = c4;
    }

    public Double getC5() {
        return c5;
    }

    public void setC5(Double c5) {
        this.c5 = c5;
    }

    public Double getC6() {
        return c6;
    }

    public void setC6(Double c6) {
        this.c6 = c6;
    }
    
}
