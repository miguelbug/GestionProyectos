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
public class Ejecucion implements Serializable {

    private Integer idEjecucion;
    private String mes;
    private Integer anio;
    private Double monto;
    private Integer idTiposDocus;
    private Integer idRORDR;
    private Date fecha;
    private Double monto2;
    private Integer idProyectoExpt;
    private Integer numEjecu;
    private Double c1E;
    private Double c2E;
    private Double c3E;
    private Double c4E;
    private Double c5E;
    private Double c6E;
    private Double c1P;
    private Double c2P;
    private Double c3P;
    private Double c4P;
    private Double c5P;
    private Double c6P;
    private String rordr1;
    private String rordr2;
    private String rordr3;
    private String rordr4;
    private String rordr5;
    private String rordr6;
    
    

    public Ejecucion() {
    }

    public Ejecucion(String mes, Integer anio, Double monto, Integer idTiposDocus, Integer idRORDR, Date fecha, Double monto2, Integer idProyectoExpt) {
        this.mes = mes;
        this.anio = anio;
        this.monto = monto;
        this.idTiposDocus = idTiposDocus;
        this.idRORDR = idRORDR;
        this.fecha = fecha;
        this.monto2 = monto2;
        this.idProyectoExpt = idProyectoExpt;
    }

    public Ejecucion(String mes, Integer anio, Double monto, Integer idTiposDocus, Integer idRORDR, Date fecha, Double monto2, Integer idProyectoExpt, Integer numEjecu) {
        this.mes = mes;
        this.anio = anio;
        this.monto = monto;
        this.idTiposDocus = idTiposDocus;
        this.idRORDR = idRORDR;
        this.fecha = fecha;
        this.monto2 = monto2;
        this.idProyectoExpt = idProyectoExpt;
        this.numEjecu = numEjecu;
    }

    public Integer getIdEjecucion() {
        return idEjecucion;
    }

    public void setIdEjecucion(Integer idEjecucion) {
        this.idEjecucion = idEjecucion;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getIdTiposDocus() {
        return idTiposDocus;
    }

    public void setIdTiposDocus(Integer idTiposDocus) {
        this.idTiposDocus = idTiposDocus;
    }

    public Integer getIdRORDR() {
        return idRORDR;
    }

    public void setIdRORDR(Integer idRORDR) {
        this.idRORDR = idRORDR;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto2() {
        return monto2;
    }

    public void setMonto2(Double monto2) {
        this.monto2 = monto2;
    }

    public Integer getIdProyectoExpt() {
        return idProyectoExpt;
    }

    public void setIdProyectoExpt(Integer idProyectoExpt) {
        this.idProyectoExpt = idProyectoExpt;
    }

    public Integer getNumEjecu() {
        return numEjecu;
    }

    public void setNumEjecu(Integer numEjecu) {
        this.numEjecu = numEjecu;
    }

    public Double getC1E() {
        return c1E;
    }

    public void setC1E(Double c1E) {
        this.c1E = c1E;
    }

    public Double getC2E() {
        return c2E;
    }

    public void setC2E(Double c2E) {
        this.c2E = c2E;
    }

    public Double getC3E() {
        return c3E;
    }

    public void setC3E(Double c3E) {
        this.c3E = c3E;
    }

    public Double getC4E() {
        return c4E;
    }

    public void setC4E(Double c4E) {
        this.c4E = c4E;
    }

    public Double getC5E() {
        return c5E;
    }

    public void setC5E(Double c5E) {
        this.c5E = c5E;
    }

    public Double getC6E() {
        return c6E;
    }

    public void setC6E(Double c6E) {
        this.c6E = c6E;
    }

    public Double getC1P() {
        return c1P;
    }

    public void setC1P(Double c1P) {
        this.c1P = c1P;
    }

    public Double getC2P() {
        return c2P;
    }

    public void setC2P(Double c2P) {
        this.c2P = c2P;
    }

    public Double getC3P() {
        return c3P;
    }

    public void setC3P(Double c3P) {
        this.c3P = c3P;
    }

    public Double getC4P() {
        return c4P;
    }

    public void setC4P(Double c4P) {
        this.c4P = c4P;
    }

    public Double getC5P() {
        return c5P;
    }

    public void setC5P(Double c5P) {
        this.c5P = c5P;
    }

    public Double getC6P() {
        return c6P;
    }

    public void setC6P(Double c6P) {
        this.c6P = c6P;
    }

    public String getRordr1() {
        return rordr1;
    }

    public void setRordr1(String rordr1) {
        this.rordr1 = rordr1;
    }

    public String getRordr2() {
        return rordr2;
    }

    public void setRordr2(String rordr2) {
        this.rordr2 = rordr2;
    }

    public String getRordr3() {
        return rordr3;
    }

    public void setRordr3(String rordr3) {
        this.rordr3 = rordr3;
    }

    public String getRordr4() {
        return rordr4;
    }

    public void setRordr4(String rordr4) {
        this.rordr4 = rordr4;
    }

    public String getRordr5() {
        return rordr5;
    }

    public void setRordr5(String rordr5) {
        this.rordr5 = rordr5;
    }

    public String getRordr6() {
        return rordr6;
    }

    public void setRordr6(String rordr6) {
        this.rordr6 = rordr6;
    }
}
