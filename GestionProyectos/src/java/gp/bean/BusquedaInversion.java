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
import gp.model.Ejecucion;
import gp.model.EjecucionMostrado;
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
    
    private Integer idEjecu;
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
    private boolean mostrar2;
    //para busqueda ejecucion
    private Double exp_tecnicoD = null;
    private Double exp_tecnicoPre = null;
    private Double infraestructuraD = null;
    private Double infraestructuraPre = null;
    private Double equip_mobiliD = null;
    private Double equip_mobiliPre = null;
    private Double supervisionD = null;
    private Double supervisionPre = null;
    private Double capacitacionD = null;
    private Double capacitacionPre = null;
    private Double otrosD = null;
    private Double otrosPre = null;
    private List lista_T;
    private String t1;
    private String t2;
    private String t3;
    private String t4;
    private String t5;
    private String t6;
    private Double totalD = null;
    private List<String> etapas;
    private String etapa;
    private boolean esta;

    public BusquedaInversion() {
        etapas = new ArrayList<String>();
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
        mostrar2 = false;
        lista_T = new ArrayList<String>();

    }

    public void buscar() {
        FacesMessage message = null;
        try {
            met.clear();
            meses.clear();
            anios.clear();
            etapas.clear();
            met = bid.getListaExpedientesTecnicos(codigo);
            nombreProy = bid.getNombreProy(codigo);
            meses = bid.getEjecucionMeses(codigo);
            anios = bid.getEjecucionAnios(codigo);
            etapas = rid.getListaEtapas(codigo);
            lista_T.clear();
            lista_T.add("RO");
            lista_T.add("RDR");
            if (meses.isEmpty() && anios.isEmpty()) {
                meses.add("Sin Meses");
                anios.add("Sin Años");
                mostrar = true;
            } else {
                mostrar = false;
            }
            if (met.isEmpty() && !nombreProy.equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO SIN HISTORIAL DE EJECUCION");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                estado = false;
            }
            if (!met.isEmpty()) {
                estado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("mal");
            System.out.println("NO REGISTRADO");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO NO REGISTRADO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            estado = false;

        }

    }

    public void llenarMontos() {
        int i = -1;
        if (!etapa.equals(" ") && !mes.equals(" ") && !anio.equals(" ")) {
            i = rid.validarProyecto(codigo, etapa, mes, anio);
            if (i > 0) {
                System.out.println("iterador: " + i);
                System.out.println("VALIDACION ACTUALIZA: " + i);
                esta = true;//se actualiza
                getMontosEjecucion(codigo, mes, anio, etapa);
            } else {
                System.out.println("iterador: " + i);
                if (i == 0) {
                    limpiarEjecucion();
                    System.out.println("VALIDACION INGRESA: " + i);
                    esta = false;//se ingresa nuevos registros
                }
            }
        }
    }

    public void getMontosEjecucion(String codigo, String mesi, String anioi, String etapai) {
        List<EjecucionMostrado> lista = rid.getMontosEjecutados(codigo, mesi, anioi, etapai);
        System.out.println("Dimension: " + lista.size());
        if (!lista.isEmpty()) {
            this.idEjecu = lista.get(0).getIdEjecucion();
            this.exp_tecnicoD = (lista.get(0).getC1E());
            exp_tecnicoPre = (lista.get(0).getC1P());
            this.t1 = lista.get(0).getRordr1();
            this.infraestructuraD = (lista.get(0).getC2E());
            this.infraestructuraPre = (lista.get(0).getC2P());
            this.t2 = lista.get(0).getRordr2();
            this.equip_mobiliD = (lista.get(0).getC3E());
            this.equip_mobiliPre = (lista.get(0).getC3P());
            this.t3 = lista.get(0).getRordr3();
            this.supervisionD = (lista.get(0).getC4E());
            this.supervisionPre = (lista.get(0).getC4P());
            this.t4 = lista.get(0).getRordr4();
            this.capacitacionD = (lista.get(0).getC5E());
            this.capacitacionPre = (lista.get(0).getC5P());
            this.t5 = lista.get(0).getRordr5();
            this.otrosD = (lista.get(0).getC6E());
            this.otrosPre = (lista.get(0).getC6P());
            this.t6 = lista.get(0).getRordr6();
            this.fecha = (lista.get(0).getFecha());
            this.totalD = exp_tecnicoD + infraestructuraD + equip_mobiliD + supervisionD + capacitacionD + otrosD;
        } else {
            limpiarEjecucion();
        }
    }

    public void registroEjecucion() {
        FacesMessage message = null;
        Date date = new Date();
        try {

            if (esta == true) {
                System.out.println("SE ACTUALIZA");
                Ejecucion ejecu = new Ejecucion();
                ejecu.setMes(mes);
                ejecu.setAnio(Integer.parseInt(anio));
                ejecu.setIdEjecucion(idEjecu);
                ejecu.setNumEjecu(rid.getNumeroEjecu(codigo, anio));
                ejecu.setC1E(exp_tecnicoD == null ? 0.0 : exp_tecnicoD);
                ejecu.setC2E(infraestructuraD == null ? 0.0 : infraestructuraD);
                ejecu.setC3E(equip_mobiliD == null ? 0.0 : equip_mobiliD);
                ejecu.setC4E(supervisionD == null ? 0.0 : supervisionD);
                ejecu.setC5E(capacitacionD == null ? 0.0 : capacitacionD);
                ejecu.setC6E(otrosD == null ? 0.0 : otrosD);
                ejecu.setC1P(exp_tecnicoPre == null ? 0.0 : this.exp_tecnicoPre);
                ejecu.setC2P(this.infraestructuraPre == null ? 0.0 : this.infraestructuraPre);
                ejecu.setC3P(equip_mobiliPre == null ? 0.0 : this.equip_mobiliPre);
                ejecu.setC4P(supervisionPre == null ? 0.0 : this.supervisionPre);
                ejecu.setC5P(capacitacionPre == null ? 0.0 : this.capacitacionPre);
                ejecu.setC6P(otrosPre == null ? 0.0 : this.otrosPre);
                ejecu.setRordr1(t1);
                ejecu.setRordr2(t2);
                ejecu.setRordr3(t3);
                ejecu.setRordr4(t4);
                ejecu.setRordr5(t5);
                ejecu.setRordr6(t6);
                rid.ActualizarMontosEjecutados(ejecu);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HAN ACTUALIZADO LOS MONTOS");
                RequestContext.getCurrentInstance().showMessageInDialog(message);

            }
            limpiarEjecucion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NUEVOS COMPONENTES NO GUARDADOS");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void limpiarEjecucion() {
        exp_tecnicoD = null;
        infraestructuraD = null;
        equip_mobiliD = null;
        supervisionD = null;
        capacitacionD = null;
        otrosD = null;
        exp_tecnicoPre = null;
        infraestructuraPre = null;
        equip_mobiliPre = null;
        supervisionPre = null;
        capacitacionPre = null;
        otrosPre = null;
        t1 = " ";
        t2 = " ";
        t3 = " ";
        t4 = " ";
        t5 = " ";
        t6 = " ";
        etapa = " ";
        mes = " ";
        anio = " ";
    }

    public void llenarMontosEjecucion() {
        /*
         int i = -1;
         if (!etapAux.equals(" ") && !mesaux.equals(" ") && !anio.equals(" ")) {
         i = rid.validarProyecto(cod_proy, etapAux, mesaux, anio);
         if (i > 0) {
         System.out.println("iterador: " + i);
         System.out.println("VALIDACION ACTUALIZA: " + i);
         esta = true;//se actualiza
         getMontosEjecucion(cod_proy, mesaux, anio, etapAux);
         } else {
         System.out.println("iterador: " + i);
         if (i == 0) {
         limpiarEjecucion();
         System.out.println("VALIDACION INGRESA: " + i);
         esta = false;//se ingresa nuevos registros
         }
         }
         }
         */
        try {
            int i = -1;
            if (!anio.equals(" ") && !mes.equals(" ")) {
                System.out.println("ENTRA A FECHA");
                fecha = mej.get(0).getFecha();
                idproy = String.valueOf(mej.get(0).getIdproy());
                mostrar2 = true;
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

    public Double obtenerMonto(Integer idexp) {
        Double montaux = 0.0;
        for (int i = 0; i < met.size(); i++) {
            if (met.get(i).getIdhistorial() == idexp) {
                montaux = met.get(i).getMonto();
                break;
            }
        }
        return montaux;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage message = null;
        String documento = "";
        Double montoaux = 0.0, montoaux2 = 0.0;
        System.out.println(((MostrarExpedientesTecnicos) event.getObject()).getDocumento() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getFecha() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getMonto() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getRr() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getIdhistorial() + " " + ((MostrarExpedientesTecnicos) event.getObject()).getMontoModificado());
        documento = ((MostrarExpedientesTecnicos) event.getObject()).getDocumento();
        ExpedienteTecnico exp = new ExpedienteTecnico();
        exp.setFecha(getFecha(((MostrarExpedientesTecnicos) event.getObject()).getFecha()));
        exp.setResolucion(((MostrarExpedientesTecnicos) event.getObject()).getRr());
        exp.setMonto(((MostrarExpedientesTecnicos) event.getObject()).getMonto());
        exp.setIdhistorial(((MostrarExpedientesTecnicos) event.getObject()).getIdhistorial());
        System.out.println("Resultado modificado: " + exp.getMontoModif());
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
        /*FacesMessage message = null;
         System.out.println(" " + ((MostrarEjecucion) event.getObject()).getRdrror() + " " + ((MostrarEjecucion) event.getObject()).getIdejecucion());
         try {
         ActualizarEjecucion save = new ActualizarEjecucion();
         save.setMontoP(((MostrarEjecucion) event.getObject()).getMontoPre());
         save.setMontoE(((MostrarEjecucion) event.getObject()).getMontoEje());
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
         }*/

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

    public void historialExpTecn() {
        System.out.println("EL CODIGO: " + codigo);
        if (codigo != null) {
            met2 = bid.getListaExpedientes(codigo, selectedMET.getDocumento());
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

    public boolean isMostrar2() {
        return mostrar2;
    }

    public void setMostrar2(boolean mostrar2) {
        this.mostrar2 = mostrar2;
    }

    public Double getExp_tecnicoD() {
        return exp_tecnicoD;
    }

    public void setExp_tecnicoD(Double exp_tecnicoD) {
        this.exp_tecnicoD = exp_tecnicoD;
    }

    public Double getExp_tecnicoPre() {
        return exp_tecnicoPre;
    }

    public void setExp_tecnicoPre(Double exp_tecnicoPre) {
        this.exp_tecnicoPre = exp_tecnicoPre;
    }

    public Double getInfraestructuraD() {
        return infraestructuraD;
    }

    public void setInfraestructuraD(Double infraestructuraD) {
        this.infraestructuraD = infraestructuraD;
    }

    public Double getInfraestructuraPre() {
        return infraestructuraPre;
    }

    public void setInfraestructuraPre(Double infraestructuraPre) {
        this.infraestructuraPre = infraestructuraPre;
    }

    public Double getEquip_mobiliD() {
        return equip_mobiliD;
    }

    public void setEquip_mobiliD(Double equip_mobiliD) {
        this.equip_mobiliD = equip_mobiliD;
    }

    public Double getEquip_mobiliPre() {
        return equip_mobiliPre;
    }

    public void setEquip_mobiliPre(Double equip_mobiliPre) {
        this.equip_mobiliPre = equip_mobiliPre;
    }

    public Double getSupervisionD() {
        return supervisionD;
    }

    public void setSupervisionD(Double supervisionD) {
        this.supervisionD = supervisionD;
    }

    public Double getSupervisionPre() {
        return supervisionPre;
    }

    public void setSupervisionPre(Double supervisionPre) {
        this.supervisionPre = supervisionPre;
    }

    public Double getCapacitacionD() {
        return capacitacionD;
    }

    public void setCapacitacionD(Double capacitacionD) {
        this.capacitacionD = capacitacionD;
    }

    public Double getCapacitacionPre() {
        return capacitacionPre;
    }

    public void setCapacitacionPre(Double capacitacionPre) {
        this.capacitacionPre = capacitacionPre;
    }

    public Double getOtrosD() {
        return otrosD;
    }

    public void setOtrosD(Double otrosD) {
        this.otrosD = otrosD;
    }

    public Double getOtrosPre() {
        return otrosPre;
    }

    public void setOtrosPre(Double otrosPre) {
        this.otrosPre = otrosPre;
    }

    public List getLista_T() {
        return lista_T;
    }

    public void setLista_T(List lista_T) {
        this.lista_T = lista_T;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }

    public String getT5() {
        return t5;
    }

    public void setT5(String t5) {
        this.t5 = t5;
    }

    public String getT6() {
        return t6;
    }

    public void setT6(String t6) {
        this.t6 = t6;
    }

    public Double getTotalD() {
        return totalD;
    }

    public void setTotalD(Double totalD) {
        this.totalD = totalD;
    }

    public List<String> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<String> etapas) {
        this.etapas = etapas;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Integer getIdEjecu() {
        return idEjecu;
    }

    public void setIdEjecu(Integer idEjecu) {
        this.idEjecu = idEjecu;
    }

    public boolean isEsta() {
        return esta;
    }

    public void setEsta(boolean esta) {
        this.esta = esta;
    }

}
