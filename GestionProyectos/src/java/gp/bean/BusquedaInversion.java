/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import gp.dao.BusquedaInversionDAO;
import gp.dao.ListasGeneralesDAO;
import gp.dao.RegistroInversionDAO;
import gp.daoImpl.BusquedaInversionDaoImpl;
import gp.daoImpl.ListasGeneralesDaoImpl;
import gp.daoImpl.RegistroInversionDaoImpl;
import gp.model.DetalleExpTecnico;
import gp.model.MostrarEjecucion;
import gp.model.MostrarExpedientesTecnicos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class BusquedaInversion {

    private String codigo;
    private boolean estado;
    private String nombreProy;
    private List<MostrarExpedientesTecnicos> met;
    private List<MostrarEjecucion> mej;
    private List<DetalleExpTecnico> det;
    private BusquedaInversionDAO bid;
    private RegistroInversionDAO rid;
    private String fecha;
    private String mes;
    private String anio;
    private ListasGeneralesDAO lgd;
    private List<String> resoluciones;
    //para editado
    private Double montoD = null;
    private String resolucionD;
    private String fechaD;

    public BusquedaInversion() {
        met = new ArrayList<MostrarExpedientesTecnicos>();
        bid = new BusquedaInversionDaoImpl();
        lgd= new ListasGeneralesDaoImpl();
        resoluciones = new ArrayList<String>();
        estado = false;
        rid = new RegistroInversionDaoImpl();
        mej = new ArrayList<MostrarEjecucion>();
        det = new ArrayList<DetalleExpTecnico>();
        resoluciones=lgd.getResoluciones();
    }

    public void buscar() {
        FacesMessage message = null;
        try {
            met.clear();
            mej.clear();
            met = bid.getListaExpedientesTecnicos(codigo);
            nombreProy = bid.getNombreProy(codigo);
            System.out.println("Dimension: " + met.size() + " " + nombreProy);
            mej = bid.getEjecucion(codigo);
            fecha = mej.get(0).getFecha();
            mes = mej.get(0).getMes();
            anio = mej.get(0).getAnio();
            estado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("mal");
            if (nombreProy == null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO NO REGISTRADO");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO SIN HISTORIAL DE EJECUCION");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

            estado = false;
        }

    }

    public Double getMonto2(String dato) {
        montoD = Double.parseDouble(dato);
        return montoD;
    }

    public String getRR(String dato) {
        resolucionD = dato;
        return resolucionD;
    }

    public String getFecha2(String dato) {
        fechaD = dato;
        return fechaD;
    }

    public void onRowEdit(RowEditEvent event) {
        System.out.println(((MostrarExpedientesTecnicos)event.getObject()).getDocumento()+" "+((MostrarExpedientesTecnicos)event.getObject()).getFecha()+" "+((MostrarExpedientesTecnicos)event.getObject()).getMonto()+" "+((MostrarExpedientesTecnicos)event.getObject()).getRr() );
    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("NO SE HIZO NADA");
    }

    public void onRowToggle(ToggleEvent event) {
        System.out.println("ENTRA: " + codigo + " " + ((MostrarExpedientesTecnicos) event.getData()).getDocumento());
        try {
            det = bid.getDetalleExpedTecnico(codigo, ((MostrarExpedientesTecnicos) event.getData()).getDocumento());
            System.out.println(det.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> coincidencias(String query) {

        List<String> cadena = new ArrayList<String>();
        try {
            cadena = rid.getCoincidencias(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cadena;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<MostrarExpedientesTecnicos> getMet() {
        return met;
    }

    public void setMet(List<MostrarExpedientesTecnicos> met) {
        this.met = met;
    }

    public BusquedaInversionDAO getBid() {
        return bid;
    }

    public void setBid(BusquedaInversionDAO bid) {
        this.bid = bid;
    }

    public RegistroInversionDAO getRid() {
        return rid;
    }

    public void setRid(RegistroInversionDAO rid) {
        this.rid = rid;
    }

    public List<MostrarEjecucion> getMej() {
        return mej;
    }

    public void setMej(List<MostrarEjecucion> mej) {
        this.mej = mej;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public List<DetalleExpTecnico> getDet() {
        return det;
    }

    public void setDet(List<DetalleExpTecnico> det) {
        this.det = det;
    }

    public String getNombreProy() {
        return nombreProy;
    }

    public void setNombreProy(String nombreProy) {
        this.nombreProy = nombreProy;
    }

    public Double getMontoD() {
        return montoD;
    }

    public void setMontoD(Double montoD) {
        this.montoD = montoD;
    }

    public String getResolucionD() {
        return resolucionD;
    }

    public void setResolucionD(String resolucionD) {
        this.resolucionD = resolucionD;
    }

    public String getFechaD() {
        return fechaD;
    }

    public void setFechaD(String fechaD) {
        this.fechaD = fechaD;
    }

    public ListasGeneralesDAO getLgd() {
        return lgd;
    }

    public void setLgd(ListasGeneralesDAO lgd) {
        this.lgd = lgd;
    }

    public List<String> getResoluciones() {
        return resoluciones;
    }

    public void setResoluciones(List<String> resoluciones) {
        this.resoluciones = resoluciones;
    }

}
