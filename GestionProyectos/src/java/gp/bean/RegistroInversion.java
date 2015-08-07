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
import gp.model.Componentes;
import gp.model.Ejecucion;
import gp.model.EjecucionMostrado;
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
    private Double montoD = null;
    private Double montoD2 = null;
    private String resolucion;
    private String resolucion2;
    private Date fecha;
    private String fechaaux;
    private String fechaaux2;
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
    private String adicional = " ";
    private String deductivo = " ";

    private String monto_contrato;
    private Double monto_contratoD = null;
    private String fecha_contrato;
    private Date fecha_cont;

    private String mes;
    private List lista_mes;
    private String anio;
    private List lista_anio;

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
    private String total;
    private Double totalD = null;
    private Double totalPre = null;
    private String contrato;
    private String contrato2;
    private String expAgreg;

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
    private List<String> etapas;
    private String etapa;
    private List<String> resoluciones;
    private boolean esta;
    private boolean aparece;
    private boolean aparece2;
    private String idproyExpt;

    public RegistroInversion() {
        aparece = false;
        aparece2 = false;
        etapas = new ArrayList<String>();
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
            iniciarListaExpediente();
        } else {
            if (event.getTab().getTitle().equals("REGISTRO DE DEDUCTIVO(S)")) {
                iniciarListaExpediente();
            } else {
                if (event.getTab().getTitle().equals("REGISTRO DE CONTRATO(S)")) {
                    iniciarListaExpediente();
                } else {
                    if (event.getTab().getTitle().equals("REGISTRO DE EJECUCIÓN")) {
                        iniciarListaExpediente();
                        getListaEtapas();
                    }
                }
            }

        }

    }

    public void llenarMontos() {
        System.out.println("LLENAR MONTOS");
        int i = -1;
        if (!etapa.equals(" ")) {
            i = rid.validarProyecto(cod_proy, etapa);
            if (i > 0) {
                System.out.println("VALIDACION: " + i);
                esta = true;//se actualiza
                getMontosEjecucion(cod_proy);
            } else {
                if (i == 0) {
                    limpiarEjecucion();
                    System.out.println("VALIDACION: " + i);
                    esta = false;//se ingresa nuevos registros
                }
            }
        }
    }

    public void getMontosEjecucion(String codigo) {
        List<EjecucionMostrado> lista = rid.getMontosEjecutados(codigo, String.valueOf(rid.getIdProyExpt(cod_proy, etapa)));
        System.out.println("Dimension: " + lista.size());
        if (lista.size() != 0) {
            this.exp_tecnicoD = Double.parseDouble(lista.get(0).getMonto());
            exp_tecnicoPre = Double.parseDouble(lista.get(0).getMonto2());
            this.t1 = lista.get(0).getNombRoRdR();

            this.infraestructuraD = Double.parseDouble(lista.get(1).getMonto());
            this.infraestructuraPre = Double.parseDouble(lista.get(1).getMonto2());
            this.t2 = lista.get(1).getNombRoRdR();

            this.equip_mobiliD = Double.parseDouble(lista.get(2).getMonto());
            this.equip_mobiliPre = Double.parseDouble(lista.get(2).getMonto2());
            this.t3 = lista.get(2).getNombRoRdR();

            this.supervisionD = Double.parseDouble(lista.get(3).getMonto());
            this.supervisionPre = Double.parseDouble(lista.get(3).getMonto2());
            this.t4 = lista.get(3).getNombRoRdR();

            this.capacitacionD = Double.parseDouble(lista.get(4).getMonto());
            this.capacitacionPre = Double.parseDouble(lista.get(4).getMonto2());
            this.t5 = lista.get(4).getNombRoRdR();

            this.otrosD = Double.parseDouble(lista.get(5).getMonto());
            this.otrosPre = Double.parseDouble(lista.get(5).getMonto2());
            this.t6 = lista.get(5).getNombRoRdR();

            this.mes = lista.get(0).getMes();
            this.anio = lista.get(0).getAnio();
        } else {
            limpiarEjecucion();
        }

    }

    public void getListaEtapas() {
        etapas.clear();
        try {
            System.out.println(cod_proy);
            etapas = rid.getListaEtapas(cod_proy);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            if (esta == false) {
                System.out.println("SE GUARDA");
                List<Ejecucion> ejecu = new ArrayList<Ejecucion>();
                ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), exp_tecnicoD == null ? 0.0 : exp_tecnicoD, 1, rid.getIDRoRdR(t1), date, exp_tecnicoPre == null ? 0.0 : this.exp_tecnicoPre, rid.getIdProyExpt(cod_proy, etapa)));
                ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), infraestructuraD == null ? 0.0 : infraestructuraD, 5, rid.getIDRoRdR(t2), date, this.infraestructuraPre == null ? 0.0 : this.infraestructuraPre, rid.getIdProyExpt(cod_proy, etapa)));
                ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), equip_mobiliD == null ? 0.0 : equip_mobiliD, 8, rid.getIDRoRdR(t3), date, equip_mobiliPre == null ? 0.0 : this.equip_mobiliPre, rid.getIdProyExpt(cod_proy, etapa)));
                ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), supervisionD == null ? 0.0 : supervisionD, 9, rid.getIDRoRdR(t4), date, supervisionPre == null ? 0.0 : this.supervisionPre, rid.getIdProyExpt(cod_proy, etapa)));
                ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), capacitacionD == null ? 0.0 : capacitacionD, 6, rid.getIDRoRdR(t5), date, capacitacionPre == null ? 0.0 : this.capacitacionPre, rid.getIdProyExpt(cod_proy, etapa)));
                ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), otrosD == null ? 0.0 : otrosD, 7, rid.getIDRoRdR(t6), date, otrosPre == null ? 0.0 : this.otrosPre, rid.getIdProyExpt(cod_proy, etapa)));

                rid.guardarEjecucion(ejecu);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HAN GUARDADO LOS NUEVOS MONTOS");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                if (esta == true) {
                    System.out.println("SE ACTUALIZA");
                    System.out.println(exp_tecnicoPre + " " + infraestructuraPre + " " + equip_mobiliPre + " " + supervisionPre + " " + capacitacionPre + " " + otrosPre);
                    List<Ejecucion> ejecu = new ArrayList<Ejecucion>();
                    ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), exp_tecnicoD == null ? 0.0 : exp_tecnicoD, 1, rid.getIDRoRdR(t1), date, exp_tecnicoPre == null ? 0.0 : this.exp_tecnicoPre, rid.getIdProyExpt(cod_proy, etapa)));
                    ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), infraestructuraD == null ? 0.0 : infraestructuraD, 5, rid.getIDRoRdR(t2), date, this.infraestructuraPre == null ? 0.0 : this.infraestructuraPre, rid.getIdProyExpt(cod_proy, etapa)));
                    ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), equip_mobiliD == null ? 0.0 : equip_mobiliD, 8, rid.getIDRoRdR(t3), date, equip_mobiliPre == null ? 0.0 : this.equip_mobiliPre, rid.getIdProyExpt(cod_proy, etapa)));
                    ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), supervisionD == null ? 0.0 : supervisionD, 9, rid.getIDRoRdR(t4), date, supervisionPre == null ? 0.0 : this.supervisionPre, rid.getIdProyExpt(cod_proy, etapa)));
                    ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), capacitacionD == null ? 0.0 : capacitacionD, 6, rid.getIDRoRdR(t5), date, capacitacionPre == null ? 0.0 : this.capacitacionPre, rid.getIdProyExpt(cod_proy, etapa)));
                    ejecu.add(new Ejecucion(mes, Integer.parseInt(anio), otrosD == null ? 0.0 : otrosD, 7, rid.getIDRoRdR(t6), date, otrosPre == null ? 0.0 : this.otrosPre, rid.getIdProyExpt(cod_proy, etapa)));
                    System.out.println(ejecu.get(0).getMonto2() + "  " + ejecu.get(0).getMonto());
                    rid.ActualizarMontosEjecutados(ejecu, String.valueOf(rid.getIdProyExpt(cod_proy, etapa)));
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HAN ACTUALIZADO LOS MONTOS");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
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
        getListaEtapas();
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

    public void agregarMontoAExpTecn() {
        FacesMessage message = null;
        try {
            Componentes c = new Componentes();
            if (rid.getCantidadExpTecn(cod_proy) == 1) {
                c = rid.getExpediente0(cod_proy);
            } else {
                c = rid.getMontosPorEtapa(cod_proy, partirCadena(expAgreg));
            }
            c.setMontoInfra(montoD2);
            c.setFecharegistro(getDate(this.fechaaux2));
            c.setMontoModif(montoD2 + c.getMontoExpTec() + c.getMontoEquipMov() + c.getMontoCapac() + c.getMontoOtros() + c.getMontoSuperv());
            c.setNumMonto(rid.getNumeroMonto(cod_proy));
            c.setNumeroRR(resolucion2);
            rid.nuevaInfraestructura(c);
            rid.actualizarEstadoExpTecn(partirCadena(expAgreg), cod_proy);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA AGREGADO EL MONTO AL : " + expAgreg);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            limpiarAgregar();
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA AGREGADO CORRECTAMENTE");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.println(e.getMessage());
        }
    }

    public void guardarNuevoExpTecn() {
        FacesMessage message = null;
        String nombreExpaux = "";
        try {
            Componentes c = new Componentes();
            c.setNumMonto(rid.getNumeroMonto(cod_proy));
            c.setNumeroExp(Integer.parseInt(nombreExp));
            c.setEtapa(Integer.parseInt(nombreExp));
            c.setTipoDocu(1);
            c.setCodigoProy(Integer.parseInt(cod_proy));
            c.setNumeroRR(resolucion);
            c.setFecharegistro(getDate(fechaaux));
            c.setMontoExpTec(0.0);
            c.setMontoInfra(montoD);
            c.setMontoEquipMov(0.0);
            c.setMontoSuperv(0.0);
            c.setMontoCapac(0.0);
            c.setMontoOtros(0.0);
            c.setMontoModif(montoD);
            c.setEstado(2);
            System.out.println(nombreExp + " " + cod_proy + " " + nombreExp);
            rid.guardarExpTecn(c);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA GUARDADO EL EXPEDIENTE TÉCNICO N°: " + nombreExp);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            getNombreExpediente();

        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA GUARDADO EL EXPEDIENTE");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        montoD = null;
        resolucion = " ";
        fechaaux = "";

    }

    public String partirCadena(String valor) {
        StringTokenizer stk = new StringTokenizer(valor, " ");
        String[] cadena;
        String retorna = "";
        if (valor.equals("Seleccione Expediente")) {
            cadena = new String[2];
        } else {
            cadena = new String[4];
        }
        int i = 0;
        while (stk.hasMoreElements()) {
            cadena[i] = stk.nextToken();
            i++;
        }
        if (cadena.length == 2) {
            retorna = cadena[1];
        } else {
            if (cadena.length == 4) {
                retorna = cadena[3];
            }
        }
        return retorna;
    }

    public void guardarNuevoAdicional() {
        FacesMessage message = null;
        String nombredocu1aux = "";
        try {
            NuevosDocumentos nd = new NuevosDocumentos();
            Componentes c = rid.getMontosPorEtapa(cod_proy, partirCadena(adicional));
            c.setMontoInfra(c.getMontoInfra() + monto_adicD);
            c.setMontoModif(c.getMontoModif() + monto_adicD);
            c.setNumMonto(c.getNumMonto() + 1);
            System.out.println("nuevo numero monto:" + c.getNumMonto());
            nd.setNumeroDocu(Integer.parseInt(nombredocu1));
            nombredocu1aux = nombredocu1;
            nd.setExptecn(rid.getIdExpedienteTecn(cod_proy, "1", partirCadena(adicional)));
            nd.setTipodocu(2);
            nd.setMonto(monto_adicD);
            nd.setResolucion(resolucion_adic);
            nd.setFecha(getDate(fechaaux_adic));
            rid.guardarDocumento(nd);
            rid.nuevaInfraestructura(c);
            rid.actualizarEstadoExpTecn(partirCadena(adicional), cod_proy);
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
            Componentes c = rid.getMontosPorEtapa(cod_proy, partirCadena(deductivo));
            c.setMontoInfra(c.getMontoInfra() - monto_deducD);
            c.setMontoModif(c.getMontoModif() - monto_deducD);
            c.setNumMonto(c.getNumMonto() + 1);
            nombredocu2aux = nombredocu2;
            nd.setExptecn(rid.getIdExpedienteTecn(cod_proy, "1", partirCadena(deductivo)));
            nd.setTipodocu(3);
            nd.setMonto(monto_deducD);
            nd.setResolucion(resolucion_deduc);
            nd.setFecha(getDate(fechaaux_deduc));
            rid.guardarDocumento(nd);
            rid.nuevaInfraestructura(c);
            rid.actualizarEstadoExpTecn(partirCadena(deductivo), cod_proy);
            getDeductivos();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA GUARDADO EL DEDUCTIVO N°: " + nombredocu2aux);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA GUARDADO EL DEDUCTIVO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        monto_deducD = null;
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
            rid.guardarContrato(nd);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE  HA GUARDADO EL CONTRATO: " + nombredocu3);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        nombredocu3 = "";
        monto_contratoD = null;
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
            System.out.println("CODIGO PROY EXP: " + cod_proy);
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
        String numeroAdicional = "";
        try {
            System.out.println("CODIGO PROY ADICIONALES: " + cod_proy + " ADICIONAL: " + adicional + "-");
            if (!adicional.equals(" ")) {
                numeroAdicional = rid.getNombreDocumentos(cod_proy, "2", partirCadena(adicional));
            }

            System.out.println("numeroAdicional: " + numeroAdicional);
            if (adicional.equals(" ") || adicional.equals("") || numeroAdicional == null) {
                System.out.println("por defecto Ad");
                nombredocu1 = "1";
            } else {
                System.out.println("consulta BD AD");
                nombredocu1 = numeroAdicional;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public void getDeductivos() {
        String numeroDeductivo = "";
        try {
            System.out.println("CODIGO PROY DEDUCTIVOS: " + cod_proy + " DEDUCTIVO: " + deductivo);
            if (!deductivo.equals(" ")) {
                numeroDeductivo = rid.getNombreDocumentos(cod_proy, "3", partirCadena(deductivo));
            }
            if (deductivo.equals(" ") || deductivo.equals("") || numeroDeductivo == null) {
                System.out.println("por defecto ded");
                nombredocu2 = "1";
            } else {
                System.out.println("consulta BD ded");
                nombredocu2 = numeroDeductivo;
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
        System.out.println(listaExpedientes.size());
    }

    public void se_Registrara() {
        this.aparece = true;
        this.aparece2 = false;
        montoD = null;
        resolucion = " ";
        fechaaux = "";
    }

    public void se_Agregara() {
        this.aparece = false;
        this.aparece2 = true;
        contrato2 = " ";
        montoD2 = null;
        resolucion2 = " ";
        fechaaux2 = "";
        iniciarListaExpediente();
    }

    public void obtenerIdProyExpt() {
        this.idproyExpt = rid.getIdExpedienteTecn(cod_proy, "1", partirCadena(expAgreg));
        System.out.println("idexpediente: " + idproyExpt);
    }

    public void limpiarAgregar() {
        expAgreg = " ";
        montoD2 = null;
        resolucion2 = " ";
        fechaaux2 = "";
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

    public Double getExp_tecnicoPre() {
        return exp_tecnicoPre;
    }

    public void setExp_tecnicoPre(Double exp_tecnicoPre) {
        this.exp_tecnicoPre = exp_tecnicoPre;
    }

    public Double getInfraestructuraPre() {
        return infraestructuraPre;
    }

    public void setInfraestructuraPre(Double infraestructuraPre) {
        this.infraestructuraPre = infraestructuraPre;
    }

    public Double getEquip_mobiliPre() {
        return equip_mobiliPre;
    }

    public void setEquip_mobiliPre(Double equip_mobiliPre) {
        this.equip_mobiliPre = equip_mobiliPre;
    }

    public Double getSupervisionPre() {
        return supervisionPre;
    }

    public void setSupervisionPre(Double supervisionPre) {
        this.supervisionPre = supervisionPre;
    }

    public Double getCapacitacionPre() {
        return capacitacionPre;
    }

    public void setCapacitacionPre(Double capacitacionPre) {
        this.capacitacionPre = capacitacionPre;
    }

    public Double getOtrosPre() {
        return otrosPre;
    }

    public void setOtrosPre(Double otrosPre) {
        this.otrosPre = otrosPre;
    }

    public Double getTotalPre() {
        return totalPre;
    }

    public void setTotalPre(Double totalPre) {
        this.totalPre = totalPre;
    }

    public ListasGeneralesDAO getLgd() {
        return lgd;
    }

    public void setLgd(ListasGeneralesDAO lgd) {
        this.lgd = lgd;
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

    public boolean isEsta() {
        return esta;
    }

    public void setEsta(boolean esta) {
        this.esta = esta;
    }

    public Double getMontoD2() {
        return montoD2;
    }

    public void setMontoD2(Double montoD2) {
        this.montoD2 = montoD2;
    }

    public String getResolucion2() {
        return resolucion2;
    }

    public void setResolucion2(String resolucion2) {
        this.resolucion2 = resolucion2;
    }

    public String getFechaaux2() {
        return fechaaux2;
    }

    public void setFechaaux2(String fechaaux2) {
        this.fechaaux2 = fechaaux2;
    }

    public String getContrato2() {
        return contrato2;
    }

    public void setContrato2(String contrato2) {
        this.contrato2 = contrato2;
    }

    public boolean isAparece() {
        return aparece;
    }

    public void setAparece(boolean aparece) {
        this.aparece = aparece;
    }

    public boolean isAparece2() {
        return aparece2;
    }

    public void setAparece2(boolean aparece2) {
        this.aparece2 = aparece2;
    }

    public String getIdproyExpt() {
        return idproyExpt;
    }

    public void setIdproyExpt(String idproyExpt) {
        this.idproyExpt = idproyExpt;
    }

    public String getExpAgreg() {
        return expAgreg;
    }

    public void setExpAgreg(String expAgreg) {
        this.expAgreg = expAgreg;
    }

}
