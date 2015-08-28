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
import gp.model.ActualizarEjecucion;
import gp.model.DetalleExpTecnico;
import gp.model.ExpedienteTecnico;
import gp.model.MostrarEjecucion;
import gp.model.MostrarExpedientesTecnicos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private List<MostrarExpedientesTecnicos> met2;
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
    private List<String> meses;
    private List<String> anios;
    private RegistroInversionDAO ri;
    private String idproy;
    private String nuevoAnio;
    private String nuevaFecha;
    private List<String> nuevosanios;
    private List<String> nuevosmeses;
    private boolean mostrar;
    private MostrarExpedientesTecnicos selectedMET;

    public BusquedaInversion() {
        meses = new ArrayList<String>();
        anios = new ArrayList<String>();
        nuevosanios = new ArrayList<String>();
        nuevosmeses = new ArrayList<String>();
        met = new ArrayList<MostrarExpedientesTecnicos>();
        met2 = new ArrayList<MostrarExpedientesTecnicos>();
        bid = new BusquedaInversionDaoImpl();
        lgd = new ListasGeneralesDaoImpl();
        resoluciones = new ArrayList<String>();
        estado = false;
        fecha = "--";
        rid = new RegistroInversionDaoImpl();
        mej = new ArrayList<MostrarEjecucion>();
        det = new ArrayList<DetalleExpTecnico>();
        resoluciones = lgd.getResoluciones();
        ri = new RegistroInversionDaoImpl();
    }

    public void buscar() {
        FacesMessage message = null;
        try {
            met.clear();
            meses.clear();
            anios.clear();
            met = bid.getListaExpedientesTecnicos(codigo);
            nombreProy = bid.getNombreProy(codigo);
            meses = bid.getEjecucionMeses(codigo);
            anios = bid.getEjecucionAnios(codigo);
            if(meses.isEmpty() && anios.isEmpty()){meses.add("Sin Meses");anios.add("Sin Años"); mostrar=true;}else{mostrar=false;}
            if (met.isEmpty() && !nombreProy.equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO SIN HISTORIAL DE EJECUCION");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                estado = false;
            }
            if (!met.isEmpty()) {
                estado = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("mal");
            System.out.println("NO REGISTRADO");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO NO REGISTRADO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            estado = false;

        }

    }

    public void llenarMontosEjecucion() {
        mej.clear();
        try {
            mej = bid.getEjecucionMontos(codigo, mes, anio);
            if (!mej.isEmpty()) {
                System.out.println("ENTRA A FECHA");
                fecha = mej.get(0).getFecha();
                idproy = String.valueOf(mej.get(0).getIdproy());
            } else {
                fecha = "";
            }

        } catch (Exception e) {
            System.out.println("ERROR EJECU MONTOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Date getFecha(String f) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d = sdf.parse(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return d;
    }
    public Double obtenerMonto(Integer idexp){
        Double montaux=0.0;
        for(int i=0;i<met.size();i++){
            if(met.get(i).getIdhistorial()==idexp){
                montaux=Double.parseDouble(met.get(i).getMonto());
                break;
            }
        }
        return montaux;
    }
    public void onRowEdit(RowEditEvent event) {
        FacesMessage message = null;
        String documento = "";
        Double montoaux=0.0,montoaux2=0.0;
        System.out.println(((MostrarExpedientesTecnicos) event.getObject()).getDocumento() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getFecha() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getMonto() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getRr() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getIdhistorial()+ " "+((MostrarExpedientesTecnicos) event.getObject()).getMontoModificado());
        documento = ((MostrarExpedientesTecnicos) event.getObject()).getDocumento();
        ExpedienteTecnico exp = new ExpedienteTecnico();
        exp.setFecha(getFecha(((MostrarExpedientesTecnicos) event.getObject()).getFecha()));
        exp.setResolucion(((MostrarExpedientesTecnicos) event.getObject()).getRr());
        exp.setMonto(Double.parseDouble(((MostrarExpedientesTecnicos) event.getObject()).getMonto()));
        exp.setIdhistorial(((MostrarExpedientesTecnicos) event.getObject()).getIdhistorial());
        System.out.println("Resultado modificado: "+exp.getMontoModif());
        try {
            bid.ActualizarExpedienteTecnico(exp);
            bid.actualizarMontoModif(exp);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "SE HA ACTUALIZADO EL: " + documento);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE PUDO ACTUALIZAR");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void onRowEdit2(RowEditEvent event) {
        System.out.println(((DetalleExpTecnico) event.getObject()).getDocumento() + " " + ((DetalleExpTecnico) event.getObject()).getFecha() + " " + ((DetalleExpTecnico) event.getObject()).getMonto() + " " + ((DetalleExpTecnico) event.getObject()).getResolucion() + " " + ((DetalleExpTecnico) event.getObject()).getIdhistorial() + " " + ((DetalleExpTecnico) event.getObject()).getIdnuevodoc());
        /*DocumentosNuevos d = new DocumentosNuevos();
        d.setFecha(getFecha(((DetalleExpTecnico) event.getObject()).getFecha()));
        d.setMonto(Double.parseDouble(((DetalleExpTecnico) event.getObject()).getMonto()));
        d.setResolucion(((DetalleExpTecnico) event.getObject()).getResolucion());
        d.setIdhistorial(((DetalleExpTecnico) event.getObject()).getIdhistorial());
        d.setDocumento(((DetalleExpTecnico) event.getObject()).getDocumento());
        d.setIdnuevodocu(((DetalleExpTecnico) event.getObject()).getIdnuevodoc());
        try {
            if (d.getDocumento().indexOf("CONTRATO") != -1) {
                String nuevoDocu = d.getDocumento().substring(12, 18);
                d.setDocumento(nuevoDocu);
                bid.ActualizarContrato(d);
            } else {
                bid.ActualizarDocumentos(d);
            }
        } catch (Exception e) {

        }*/

    }

    public void onRowEdit3(RowEditEvent event) {
        FacesMessage message = null;
        System.out.println(((MostrarEjecucion) event.getObject()).getDocumento() + " " + ((MostrarEjecucion) event.getObject()).getMontoPre() + " " + ((MostrarEjecucion) event.getObject()).getMontoEje() + " " + ((MostrarEjecucion) event.getObject()).getRdrror() + " " + ((MostrarEjecucion) event.getObject()).getIdejecucion());
        try {
            ActualizarEjecucion save = new ActualizarEjecucion();
            save.setMontoP(Double.parseDouble(((MostrarEjecucion) event.getObject()).getMontoPre()));
            save.setMontoE(Double.parseDouble(((MostrarEjecucion) event.getObject()).getMontoEje()));
            save.setTiporor((ri.getIDRoRdR(((MostrarEjecucion) event.getObject()).getRdrror())));
            save.setIdejecu(Integer.valueOf(((MostrarEjecucion) event.getObject()).getIdejecucion()));
            bid.actualizarMontoEjecucion(save);
            mej.clear();
            mej = bid.getEjecucionMontos(codigo, mes, anio);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE HA ACTUALIZADO EL MONTO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA PODIDO ACTUALIZADO EL MONTO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("NO SE HIZO NADA");
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "NO SE HA ACTUALIZADO NINGUN DOCUMENTO");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void onRowCancel2(RowEditEvent event) {
        System.out.println("NO SE HIZO NADA");
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "NO SE HA ACTUALIZADO NINGUN DOCUMENTO");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void onRowCancel3(RowEditEvent event) {

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

    public void llenarnuevosmeses() {
        nuevosmeses.add("Enero");
        nuevosmeses.add("Febrero");
        nuevosmeses.add("Marzo");
        nuevosmeses.add("Abril");
        nuevosmeses.add("Mayo");
        nuevosmeses.add("Junio");
        nuevosmeses.add("Julio");
        nuevosmeses.add("Agosto");
        nuevosmeses.add("Septiembre");
        nuevosmeses.add("Octubre");
        nuevosmeses.add("Noviembre");
        nuevosmeses.add("Diciembre");
    }

    public void llenarnuevosanios() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        int j = Integer.parseInt(sdf.format(d));
        for (int i = 0; i < (int) (j % 100); i++) {
            nuevosanios.add(String.valueOf(i));
        }
    }
    
    public void historialExpTecn(){
        System.out.println("EL CODIGO: "+codigo);
        if(codigo!=null){
            met2=bid.getListaExpedientes(codigo,selectedMET.getDocumento());
        }
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

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    public RegistroInversionDAO getRi() {
        return ri;
    }

    public void setRi(RegistroInversionDAO ri) {
        this.ri = ri;
    }

    public String getIdproy() {
        return idproy;
    }

    public void setIdproy(String idproy) {
        this.idproy = idproy;
    }

    public String getNuevoAnio() {
        return nuevoAnio;
    }

    public void setNuevoAnio(String nuevoAnio) {
        this.nuevoAnio = nuevoAnio;
    }

    public String getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(String nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public List<String> getNuevosanios() {
        return nuevosanios;
    }

    public void setNuevosanios(List<String> nuevosanios) {
        this.nuevosanios = nuevosanios;
    }

    public List<String> getNuevosmeses() {
        return nuevosmeses;
    }

    public void setNuevosmeses(List<String> nuevosmeses) {
        this.nuevosmeses = nuevosmeses;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public MostrarExpedientesTecnicos getSelectedMET() {
        return selectedMET;
    }

    public void setSelectedMET(MostrarExpedientesTecnicos selectedMET) {
        this.selectedMET = selectedMET;
    }

    public List<MostrarExpedientesTecnicos> getMet2() {
        return met2;
    }

    public void setMet2(List<MostrarExpedientesTecnicos> met2) {
        this.met2 = met2;
    }

}
