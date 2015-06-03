/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import gp.dao.ListasGeneralesDAO;
import gp.dao.RegistroInversionDAO;
import gp.daoImpl.ListasGeneralesDaoImpl;
import gp.daoImpl.RegistroInversionDaoImpl;
import gp.model.Ejecucion;
import gp.model.NuevosDocumentos;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class RegistroInversion {

    private String monto;
    private Double montoD =null;
    private String resolucion;
    private Date fecha;
    private String fechaaux;
    private String horaaux;
    private List listaExpedientes;

    private String monto_adic;
    private Double monto_adicD = null;
    private String resolucion_adic;
    private Date fecha_adic;
    private String fechaaux_adic;

    private String monto_deduc;
    private Double monto_deducD = null;
    private String resolucion_deduc;
    private Date fecha_deduc;
    private String fechaaux_deduc;

    private String cod_proy;
    private boolean mostrar;
    private String adicional=" ";
    private String deductivo=" ";

    private String monto_contrato;
    private Double monto_contratoD = null;
    private String fecha_contrato;
    private Date fecha_cont;

    private String mes;
    private List lista_mes;
    private String anio;
    private List lista_anio;

    private Double exp_tecnicoD = null;
    private Double infraestructuraD = null;
    private Double equip_mobiliD = null;
    private Double supervisionD = null;
    private Double capacitacionD = null;
    private Double otrosD = null;
    private String total;
    private Double totalD = null;
    private String contrato;

    private List lista_T;
    private String t1;
    private String t2;
    private String t3;
    private String t4;
    private String t5;
    private String t6;
    private RegistroInversionDAO rid;

    private String nombreExp;
    private String nombredocu1;
    private String nombredocu2;
    private String nombredocu3;
    private ListasGeneralesDAO lgd;

    private List<String> resoluciones;

    public RegistroInversion() {
        fecha = new Date();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String currentPage = facesContext.getViewRoot().getViewId();
        lista_T = new ArrayList<String>();
        listaExpedientes = new ArrayList<String>();
        lista_mes = new ArrayList<String>();
        lista_anio = new ArrayList<String>();
        listarAnios_mes();
        rid = new RegistroInversionDaoImpl();
        mostrar = false;
        resoluciones = new ArrayList<String>();
        lgd = new ListasGeneralesDaoImpl();
        boolean isdocumentos = (currentPage.lastIndexOf("reg_Inversion.xhtml") > -1);
        if (isdocumentos) {
            getResolucionesLista();
        }
        nombredocu1 = "1";
        nombredocu2 = "1";

    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("REGISTRO DE ADICIONAL(ES)")) {
            if (listaExpedientes.size() == 0) {
                iniciarListaExpediente();
            }
        } else {
            if (event.getTab().getTitle().equals("REGISTRO DE DEDUCTIVO(S)")) {
                if (listaExpedientes.size() == 0) {
                    iniciarListaExpediente();
                }
            } else {
                if (event.getTab().getTitle().equals("REGISTRO DE CONTRATO(S)")) {
                    if (listaExpedientes.size() == 0) {
                        iniciarListaExpediente();
                    }
                } else {
                    if (event.getTab().getTitle().equals("REGISTRO DE EJECUCIÓN")) {
                        if (listaExpedientes.size() == 0) {
                            iniciarListaExpediente();
                        }
                    }
                }
            }
        }
    }

    public void getResolucionesLista() {
        try {
            resoluciones = lgd.getResoluciones();
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

    public BigDecimal pasarADecimal(String valor) {
        BigDecimal numero = null;
        try {
            numero = BigDecimal.valueOf(Double.parseDouble(valor));
        } catch (Exception e) {
            numero = new BigDecimal("0.0");
        }
        return numero;

    }

    public void registroEjecucion() {
        FacesMessage message = null;
        Date date = new Date();
        try {
            List<Ejecucion> ejecu = new ArrayList<Ejecucion>();
            ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), exp_tecnicoD, Integer.parseInt(cod_proy), 1, rid.getIDRoRdR(t1), date));
            ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), infraestructuraD, Integer.parseInt(cod_proy), 5, rid.getIDRoRdR(t2), date));
            ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), equip_mobiliD, Integer.parseInt(cod_proy), 8, rid.getIDRoRdR(t3), date));
            ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), supervisionD, Integer.parseInt(cod_proy), 9, rid.getIDRoRdR(t4), date));
            ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), capacitacionD, Integer.parseInt(cod_proy), 6, rid.getIDRoRdR(t5), date));
            ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), otrosD, Integer.parseInt(cod_proy), 7, rid.getIDRoRdR(t6), date));
            rid.guardarEjecucion(ejecu);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HAN GUARDADO LOS NUEVOS MONTOS EJECUTADOS");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NUEVOS COMPONENTES NO GUARDADOS");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        mes = "Enero";

    }

    public void sumaComponentes() {
        Double[] cadena = new Double[6];
        BigDecimal valor = new BigDecimal("0.0");
        int i = 0;
        cadena[0] = exp_tecnicoD == null ? 0.0 : exp_tecnicoD;
        cadena[1] = infraestructuraD == null ? 0.0 : infraestructuraD;
        cadena[2] = equip_mobiliD == null ? 0.0 : equip_mobiliD;
        cadena[3] = supervisionD == null ? 0.0 : supervisionD;
        cadena[4] = capacitacionD == null ? 0.0 : capacitacionD;
        cadena[5] = otrosD == null ? 0.0 : otrosD;
        while (i < cadena.length) {
            if (cadena[i].equals(0.0)) {
                cadena[i] = 0.0;
            }
            BigDecimal nuevo = new BigDecimal(cadena[i]);
            valor = valor.add(nuevo);
            i++;
        }
        total = String.valueOf(valor);
    }

    public void mostrar() {
        FacesMessage message = null;
        boolean es = true;
        try {
            System.out.println("ESTE ES EL CODIGO: " + cod_proy);
            String casa = rid.validarProy(cod_proy);
            casa = casa.toUpperCase();
            es = true;
        } catch (Exception e) {
            es = false;
        }
        if (es) {
            getNombreExpediente();
            getAdicionales();
            getDeductivos();
            mostrar = true;
            this.listaExpedientes.clear();
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO NO REGISTRADO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void guardarNuevoExpTecn() {
        FacesMessage message = null;
        String nombreExpaux = "";
        try {
            NuevosDocumentos nd = new NuevosDocumentos();
            nd.setNumeroDocu(Integer.parseInt(nombreExp));
            nombreExpaux = nombreExp;
            nd.setMonto(montoD);
            nd.setResolucion(resolucion);
            nd.setFecha(getDate(fechaaux));
            nd.setTipodocu(1);
            nd.setIdProy(cod_proy);
            rid.guardarNuevoExpTecn(nd);
            getNombreExpediente();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA GUARDADO EL EXPEDIENTE TÉCNICO N°: " + nombreExpaux);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA GUARDADO EL EXPEDIENTE");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        montoD = 0.00;
        resolucion = " ";
        fechaaux = "";

    }

    public String partirCadena(String valor) {
        StringTokenizer stk = new StringTokenizer(valor, " ");
        String[] cadena;
        String retorna="";
        if(valor.equals("Seleccione Expediente")){
            cadena = new String[2];
        }else{
            cadena = new String[4];
        }
        int i = 0;
        while (stk.hasMoreElements()) {
            cadena[i] = stk.nextToken();
            i++;
        }
        if(cadena.length==2){
            retorna=cadena[1];
        }else{
            if(cadena.length==4){
                retorna=cadena[3];
            }
        }
        return retorna;
    }

    public void guardarNuevoAdicional() {
        FacesMessage message = null;
        String nombredocu1aux = "";
        try {
            NuevosDocumentos nd = new NuevosDocumentos();
            nd.setNumeroDocu(Integer.parseInt(nombredocu1));
            nombredocu1aux = nombredocu1;
            nd.setExptecn(rid.getIdExpedienteTecn(cod_proy, "1", partirCadena(adicional)));
            nd.setMonto(monto_adicD);
            nd.setResolucion(resolucion_adic);
            nd.setFecha(getDate(fechaaux_adic));
            nd.setTipodocu(2);
            rid.guardarNuevoDocumento(nd);
            getAdicionales();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA GUARDADO EL ADICIONAL N°: " + nombredocu1aux);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA GUARDADO EL ADICIONAL");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public void guardarNuevoDeductivo() {
        FacesMessage message = null;
        String nombredocu2aux = "";
        try {
            NuevosDocumentos nd = new NuevosDocumentos();
            nd.setNumeroDocu(Integer.parseInt(nombredocu2));
            nombredocu2aux = nombredocu2;
            nd.setMonto(monto_deducD);
            nd.setResolucion(resolucion_deduc);
            nd.setFecha(getDate(fechaaux_deduc));
            nd.setExptecn(rid.getIdExpedienteTecn(cod_proy, "1", partirCadena(deductivo)));
            nd.setTipodocu(3);
            rid.guardarNuevoDocumento(nd);
            getDeductivos();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA GUARDADO EL DEDUCTIVO N°: " + nombredocu2aux);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA GUARDADO EL DEDUCTIVO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        monto_deducD = 0.00;
        resolucion_deduc = " ";
        fechaaux_deduc = "";
    }

    public void guardarNuevoContrato() {
        FacesMessage message = null;
        try {
            NuevosDocumentos nd = new NuevosDocumentos();
            nd.setExptecn(rid.getIdExpedienteTecn(cod_proy, "1", partirCadena(contrato)));
            nd.setCodigoContrato(nombredocu3);
            nd.setMonto(monto_contratoD);
            nd.setFecha(getDate(fecha_contrato));
            nd.setTipodocu(4);
            rid.guardarNuevoDocumentoContrato(nd);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA GUARDADO EL CONTRATO: " + nombredocu3);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        nombredocu3 = "";
        monto_contratoD = 0.00;
        fecha_contrato = "";
    }

    public Date getDate(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = sdf.parse(fecha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public void getNombreExpediente() {
        try {
            System.out.println("CODIGO PROY EXP: "+cod_proy);
            nombreExp = rid.getNombreExpediente(cod_proy, "1");
            if (nombreExp == null) {
                nombreExp = "1";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void getAdicionales() {
        try {
            System.out.println("CODIGO PROY ADICIONALES: "+cod_proy+" ADICIONAL: "+adicional);
            if(adicional.equals(" ") || adicional.equals("")){
                System.out.println("por defecto Ad");
                nombredocu1 = "1";
            }else{
                System.out.println("consulta BD AD");
                nombredocu1 = rid.getNombreDocumentos(cod_proy, "2", partirCadena(adicional));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public void getDeductivos() {
        try {
            System.out.println("CODIGO PROY DEDUCTIVOS: "+cod_proy+" DEDUCTIVO: "+deductivo);
            if(deductivo.equals(" ") || deductivo.equals("")){
                System.out.println("por defecto ded");
                nombredocu2 = "1";
            }else{
                System.out.println("consulta BD ded");
                nombredocu2 = rid.getNombreDocumentos(cod_proy, "3", partirCadena(deductivo));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarAnios_mes() {
        lista_anio.clear();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        int i = Integer.parseInt(sdf.format(date)), j = 0;
        while (j < 15) {
            lista_anio.add(String.valueOf(i - j));
            j++;
        }
        lista_mes.clear();
        lista_mes.add("Enero");
        lista_mes.add("Febrero");
        lista_mes.add("Marzo");
        lista_mes.add("Abril");
        lista_mes.add("Mayo");
        lista_mes.add("Junio");
        lista_mes.add("Julio");
        lista_mes.add("Agosto");
        lista_mes.add("Setiembre");
        lista_mes.add("Octubre");
        lista_mes.add("Noviembre");
        lista_mes.add("Diciembre");

        lista_T.clear();
        lista_T.add("RO");
        lista_T.add("RDR");
    }

    public void iniciarListaExpediente() {
        listaExpedientes.clear();
        List listaaux = rid.getExpedientes(cod_proy, "1");
        for (int i = 0; i < listaaux.size(); i++) {
            listaExpedientes.add(listaaux.get(i).toString());
        }
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
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

    public String getFechaaux() {
        return fechaaux;
    }

    public void setFechaaux(String fechaaux) {
        this.fechaaux = fechaaux;
    }

    public List getListaExpedientes() {
        return listaExpedientes;
    }

    public void setListaExpedientes(List listaExpedientes) {
        this.listaExpedientes = listaExpedientes;
    }

    public String getMonto_adic() {
        return monto_adic;
    }

    public void setMonto_adic(String monto_adic) {
        this.monto_adic = monto_adic;
    }

    public String getResolucion_adic() {
        return resolucion_adic;
    }

    public void setResolucion_adic(String resolucion_adic) {
        this.resolucion_adic = resolucion_adic;
    }

    public Date getFecha_adic() {
        return fecha_adic;
    }

    public void setFecha_adic(Date fecha_adic) {
        this.fecha_adic = fecha_adic;
    }

    public String getFechaaux_adic() {
        return fechaaux_adic;
    }

    public void setFechaaux_adic(String fechaaux_adic) {
        this.fechaaux_adic = fechaaux_adic;
    }

    public String getMonto_deduc() {
        return monto_deduc;
    }

    public void setMonto_deduc(String monto_deduc) {
        this.monto_deduc = monto_deduc;
    }

    public String getResolucion_deduc() {
        return resolucion_deduc;
    }

    public void setResolucion_deduc(String resolucion_deduc) {
        this.resolucion_deduc = resolucion_deduc;
    }

    public Date getFecha_deduc() {
        return fecha_deduc;
    }

    public void setFecha_deduc(Date fecha_deduc) {
        this.fecha_deduc = fecha_deduc;
    }

    public String getFechaaux_deduc() {
        return fechaaux_deduc;
    }

    public void setFechaaux_deduc(String fechaaux_deduc) {
        this.fechaaux_deduc = fechaaux_deduc;
    }

    public String getCod_proy() {
        return cod_proy;
    }

    public void setCod_proy(String cod_proy) {
        this.cod_proy = cod_proy;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public String getDeductivo() {
        return deductivo;
    }

    public void setDeductivo(String deductivo) {
        this.deductivo = deductivo;
    }

    public String getMonto_contrato() {
        return monto_contrato;
    }

    public void setMonto_contrato(String monto_contrato) {
        this.monto_contrato = monto_contrato;
    }

    public String getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(String fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public Date getFecha_cont() {
        return fecha_cont;
    }

    public void setFecha_cont(Date fecha_cont) {
        this.fecha_cont = fecha_cont;
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

    public List getLista_mes() {
        return lista_mes;
    }

    public void setLista_mes(List lista_mes) {
        this.lista_mes = lista_mes;
    }

    public List getLista_anio() {
        return lista_anio;
    }

    public void setLista_anio(List lista_anio) {
        this.lista_anio = lista_anio;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public List getLista_T() {
        return lista_T;
    }

    public void setLista_T(List lista_T) {
        this.lista_T = lista_T;
    }

    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public RegistroInversionDAO getRid() {
        return rid;
    }

    public void setRid(RegistroInversionDAO rid) {
        this.rid = rid;
    }

    public String getNombredocu1() {
        return nombredocu1;
    }

    public void setNombredocu1(String nombredocu1) {
        this.nombredocu1 = nombredocu1;
    }

    public String getNombredocu2() {
        return nombredocu2;
    }

    public void setNombredocu2(String nombredocu2) {
        this.nombredocu2 = nombredocu2;
    }

    public String getNombredocu3() {
        return nombredocu3;
    }

    public void setNombredocu3(String nombredocu3) {
        this.nombredocu3 = nombredocu3;
    }

    public String getHoraaux() {
        return horaaux;
    }

    public void setHoraaux(String horaaux) {
        this.horaaux = horaaux;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Double getMontoD() {
        return montoD;
    }

    public void setMontoD(Double montoD) {
        this.montoD = montoD;
    }

    public Double getMonto_adicD() {
        return monto_adicD;
    }

    public void setMonto_adicD(Double monto_adicD) {
        this.monto_adicD = monto_adicD;
    }

    public Double getMonto_deducD() {
        return monto_deducD;
    }

    public void setMonto_deducD(Double monto_deducD) {
        this.monto_deducD = monto_deducD;
    }

    public Double getMonto_contratoD() {
        return monto_contratoD;
    }

    public void setMonto_contratoD(Double monto_contratoD) {
        this.monto_contratoD = monto_contratoD;
    }

    public Double getExp_tecnicoD() {
        return exp_tecnicoD;
    }

    public void setExp_tecnicoD(Double exp_tecnicoD) {
        this.exp_tecnicoD = exp_tecnicoD;
    }

    public Double getInfraestructuraD() {
        return infraestructuraD;
    }

    public void setInfraestructuraD(Double infraestructuraD) {
        this.infraestructuraD = infraestructuraD;
    }

    public Double getEquip_mobiliD() {
        return equip_mobiliD;
    }

    public void setEquip_mobiliD(Double equip_mobiliD) {
        this.equip_mobiliD = equip_mobiliD;
    }

    public Double getSupervisionD() {
        return supervisionD;
    }

    public void setSupervisionD(Double supervisionD) {
        this.supervisionD = supervisionD;
    }

    public Double getCapacitacionD() {
        return capacitacionD;
    }

    public void setCapacitacionD(Double capacitacionD) {
        this.capacitacionD = capacitacionD;
    }

    public Double getOtrosD() {
        return otrosD;
    }

    public void setOtrosD(Double otrosD) {
        this.otrosD = otrosD;
    }

    public Double getTotalD() {
        return totalD;
    }

    public void setTotalD(Double totalD) {
        this.totalD = totalD;
    }

    public List<String> getResoluciones() {
        return resoluciones;
    }

    public void setResoluciones(List<String> resoluciones) {
        this.resoluciones = resoluciones;
    }

}
