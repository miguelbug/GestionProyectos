/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import gp.dao.BusqPreInversionDAO;
import gp.dao.ListasGeneralesDAO;
import gp.dao.MontosDAO;
import gp.dao.RegistroInversionDAO;
import gp.dao.RegistroPreInversionDAO;
import gp.daoImpl.BusqPreInversionDaoImpl;
import gp.daoImpl.ListasGeneralesDaoImpl;
import gp.daoImpl.MontosDaoImpl;
import gp.daoImpl.RegistroInversionDaoImpl;
import gp.daoImpl.RegistroPreInversionDaoImpl;
import gp.model.AspectosGenerales;
import gp.model.BusqPreInversion;
import gp.model.Componentes;
import gp.model.GuardarNuevComp;
import gp.model.HistorialMontos;
import gp.model.Montos;
import gp.model.Opi_responsable;
import gp.model.Origen;
import gp.model.busquedaPreInversionMontos;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class BusquedaPreInversion {

    private String b1;
    private String b2;
    private String b3;
    private String b4;
    private Double b4D = null;
    private String b5;
    private String b6;
    private Double b6D = null;
    private String b7;
    private String b8;
    private String b9;
    private String b10;
    private String b11;
    private String b12;
    private Double b13D = null;
    private Double b14D = null;
    private Double b15D = null;
    private Double b16D = null;
    private Double b17D = null;
    private Double b18D = null;
    private String b19;
    private String b20;
    private String b21;
    private String b22;
    private String b23;
    private Double b24D = null;
    private String b24Daux;
    private String b25;
    private String b25aux;
    private Double b26D = null;
    private boolean estado;
    private boolean estado2;
    private boolean estado3;
    private boolean estado4;
    private boolean estado5;
    private boolean estado6;
    private boolean estado7;
    private boolean estado8;
    private boolean estado9;
    private boolean estado11;
    private boolean estado12;
    private int estado10;
    private List<BusqPreInversion> listaBPI_1;
    private List<BusqPreInversion> listaBPI_2;
    private String cantidad;
    private BusqPreInversionDAO bpi;
    private MontosDAO montd;
    private List<Montos> mdf;
    private String[] montos = {"Expediente Tecnico", "Infraestructura", "Equipamiento y Mobilidad", "Supervision", "Capacitacion", "Otros"};
    private MontosDAO mont;
    private String widhtcomp;
    private String tipo_origen;
    private String origen;
    private List lista_origen;
    private List lista_opi;
    private List lista_nivel;
    private RegistroPreInversionDAO rpd;
    private String opi_resp;
    private String nivel_estud;
    private RegistroInversionDAO rid;
    private List<busquedaPreInversionMontos> bqpim;
    private String expediente;
    private String informe;
    private String resolucion;
    private List<String> expedientes;
    private List<String> informes;
    private List<String> resoluciones;
    private ListasGeneralesDAO lgd;
    private List<String> montosModif;
    private String color;
    private String nuevaFecha;
    private String aux1;
    private String aux2;
    private Integer idcomp;
    private String fmm;
    private String fmmaux;
    private List<HistorialMontos> historialMontos;

    public BusquedaPreInversion() {
        cantidad = "2";
        listaBPI_1 = new ArrayList<BusqPreInversion>();
        listaBPI_2 = new ArrayList<BusqPreInversion>();
        bpi = new BusqPreInversionDaoImpl();
        mdf = new ArrayList<Montos>();
        montd = new MontosDaoImpl();
        lista_origen = new ArrayList<String>();
        estado = true;
        estado2 = true;
        mont = new MontosDaoImpl();
        widhtcomp = "";
        rpd = new RegistroPreInversionDaoImpl();
        rid = new RegistroInversionDaoImpl();
        historialMontos = new ArrayList<HistorialMontos>();
        estado10 = -1;
        estado = true;
        estado4 = false;
        estado3 = true;
        estado5 = true;
        estado6 = false;
        estado2 = true;
        estado7 = true;
        estado8 = true;
        estado9 = true;
        estado12 = true;
        b20 = "";
        color = "black";
        b24Daux = " ";
        origen = " ";
        lista_opi = new ArrayList<String>();
        lista_nivel = new ArrayList<String>();
        bqpim = new ArrayList<busquedaPreInversionMontos>();
        expedientes = new ArrayList<String>();
        informes = new ArrayList<String>();
        resoluciones = new ArrayList<String>();
        montosModif = new ArrayList<String>();
        lgd = new ListasGeneralesDaoImpl();

        llenar_OPI();
        llenar_NE();
    }

    public void guardarFechaModificacion() {
        fmmaux = fmm;
    }

    public void guardarMontoModificado() {
        b24Daux = String.valueOf(b24D);
    }

    public String partirMonto(int j) {
        String[] cadena = new String[3];
        StringTokenizer stk = new StringTokenizer(b25, " ");
        int i = 0;
        while (stk.hasMoreElements()) {
            cadena[i] = stk.nextToken();
            i++;
        }
        return cadena[j];
    }

    public void getComponentesDeMonto() {
        List<busquedaPreInversionMontos> lista = new ArrayList<busquedaPreInversionMontos>();
        lista = bpi.getComponentesDeMonto(b25, b20);
        for (int i = 0; i < lista.size(); i++) {
            b13D = Double.parseDouble(lista.get(i).getExp_tecn());
            b14D = Double.parseDouble(lista.get(i).getInfraestructura());
            b15D = Double.parseDouble(lista.get(i).getEquip_mobil());
            b16D = Double.parseDouble(lista.get(i).getSupervision());
            b17D = Double.parseDouble(lista.get(i).getCapacitacion());
            b18D = Double.parseDouble(lista.get(i).getOtros());
            idcomp = lista.get(i).getIdcomp();
        }

        BigDecimal suma1 = new BigDecimal(b13D);
        BigDecimal suma2 = new BigDecimal(b14D);
        BigDecimal suma3 = new BigDecimal(b15D);
        BigDecimal suma4 = new BigDecimal(b16D);
        BigDecimal suma5 = new BigDecimal(b17D);
        BigDecimal suma6 = new BigDecimal(b18D);
        BigDecimal suma = suma1.add(suma2).add(suma3).add(suma4).add(suma5).add(suma6);
        b19 = String.valueOf(suma);
        b25aux = partirMonto(0);
        b26D = Double.parseDouble(partirMonto(0));
        nuevaFecha = partirMonto(2);

    }

    public void guardarMontoSeleccionado() {
        aux1 = String.valueOf(b26D);
        System.out.println(aux1);
    }

    public void guardarFechaSeleccionada() {
        aux2 = nuevaFecha;
    }

    public void getExpedientesLista() {
        try {
            expedientes = lgd.getExpedientes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void getInformesLista() {
        try {
            informes = lgd.getInformes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
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

    public void mostrarHistorial() {
        historialMontos.clear();
        bqpim.clear();
        try {
            historialMontos.add(lgd.getMontoViable(b20));
            List<HistorialMontos> listaaux = new ArrayList<HistorialMontos>();
            listaaux = lgd.getMontosHistorial(b20);
            for (int i = 0; i < listaaux.size(); i++) {
                historialMontos.add(listaaux.get(i));
            }
            bqpim = bpi.getMontoHistorial(b20);
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

    public void llenar_OPI() {
        lista_opi.clear();
        List<Opi_responsable> aux = new ArrayList<Opi_responsable>();
        aux = rpd.obtenerLista_opi();
        int i = 0;
        while (i < aux.size()) {
            lista_opi.add(aux.get(i).getNombre());
            i++;
        }

    }

    public void llenar_NE() {
        lista_nivel.clear();
        int i = 0;
        while (i < rpd.obtenerList_nivEst().size()) {
            lista_nivel.add(rpd.obtenerList_nivEst().get(i).getNombre());
            i++;
        }

    }

    public void agregar_Actualizar() {

        if (estado10 == 1) {
            guardarNuevosComponentes();
        } else {
            if (estado10 == 0) {
                modificarComponentes();
            }
        }
    }

    public Date getDate(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(fecha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public void modificarAspectosGenerales() {
        try {
            AspectosGenerales ag = new AspectosGenerales();
            ag.setCodigo(Integer.parseInt(b20));
            ag.setNombre(b2.toUpperCase());
            if (origen.equals(" ")) {
                ag.setOrigen(rpd.getId_Origen(b3));
            } else {
                ag.setOrigen(rpd.getId_Origen(origen));
            }
            ag.setMontoViabilidad(b4D == null ? 0.0 : b4D);
            ag.setFechaViabilidad(getDate(b5));
            ag.setNivEstud(rpd.getId_Nivel(b12));
            ag.setInformeTecnico(b8.toUpperCase());
            ag.setExpedAdm(b9.toUpperCase());
            ag.setNumRR(b10.toUpperCase());
            ag.setOpiResp(rpd.getId_Opi(b11));
            bpi.actualizarAspectosGenerales(ag);
            estado8 = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void modificarComponentes() {
        FacesMessage message = null;
        try {
            Componentes c = new Componentes();
            c.setNumMonto(Integer.parseInt(mont.getNumMonto(b1)));
            c.setMontoExpTec(b13D);
            c.setMontoInfra(b14D);
            c.setMontoEquipMov(b15D);
            c.setMontoSuperv(b16D);
            c.setMontoCapac(b17D);
            c.setMontoOtros(b18D);
            c.setCodigoProy(Integer.parseInt(b20));
            c.setFecharegistro(getDate(nuevaFecha));
            c.setMontoModif(b26D);
            c.setMontoaux(Double.parseDouble(aux1));
            c.setFechaaux(getDate(aux2));
            c.setIdcomp(idcomp);
            bpi.actualizarComponentes(c);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HAN MODIFICADO LOS COMPONENTES");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            mdf.clear();
            mdf = montd.getMontosModificados(b20);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "NO SE HAN MODIFICADO LOS COMPONENTES");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public void llenar_origen() {
        lista_origen.clear();
        List<Origen> aux = new ArrayList<Origen>();
        aux = rpd.ObtenerLista_Origen(tipo_origen);
        int i = 0;
        while (i < aux.size()) {
            lista_origen.add(aux.get(i).getNombre());
            i++;
        }
    }

    public void cambiarAlBuscar() {
        limpiarComponentes();
        FacesMessage message = null;
        if (b20 == "") {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "DEBE LLENAR EL CAMPO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else if (bpi.validarProyecto(b20) == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PROYECTO NO ENCONTRADO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            estado = false;
            estado8 = true;
            estado11 = true;

            listaBPI_1 = bpi.listaBusqPI(b20);
            System.out.println("LISTA1: "+listaBPI_1.size());
            listaBPI_2 = bpi.listaBusqPI_2(b20);
            for (int i = 0; i < listaBPI_1.size(); i++) {
                b1 = listaBPI_1.get(i).getC1();
                b2 = listaBPI_1.get(i).getC2();
                b3 = listaBPI_1.get(i).getC3();
                b4D = Double.parseDouble(listaBPI_1.get(i).getC4());
                b5 = listaBPI_1.get(i).getC5();
                b8 = listaBPI_1.get(i).getC6();
                b9 = listaBPI_1.get(i).getC7();
                b10 = listaBPI_1.get(i).getC8();
                b11 = listaBPI_1.get(i).getC9();
                b12 = listaBPI_1.get(i).getC10();
            }
            for (int i = 0; i < listaBPI_2.size(); i++) {
                b13D = Double.parseDouble(listaBPI_2.get(i).getC11() == null ? "0.0" : listaBPI_2.get(i).getC11());
                b14D = Double.parseDouble(listaBPI_2.get(i).getC12() == null ? "0.0" : listaBPI_2.get(i).getC12());
                b15D = Double.parseDouble(listaBPI_2.get(i).getC13() == null ? "0.0" : listaBPI_2.get(i).getC13());
                b16D = Double.parseDouble(listaBPI_2.get(i).getC14() == null ? "0.0" : listaBPI_2.get(i).getC14());
                b17D = Double.parseDouble(listaBPI_2.get(i).getC15() == null ? "0.0" : listaBPI_2.get(i).getC15());
                b18D = Double.parseDouble(listaBPI_2.get(i).getC16() == null ? "0.0" : listaBPI_2.get(i).getC16());
                b7 = listaBPI_2.get(i).getC17();
                b6D = Double.parseDouble(listaBPI_2.get(i).getC18() == null ? "0.0" : listaBPI_2.get(i).getC18());
            }
            BigDecimal suma1 = new BigDecimal(b13D == null ? 0.0 : b13D);
            BigDecimal suma2 = new BigDecimal(b14D == null ? 0.0 : b14D);
            BigDecimal suma3 = new BigDecimal(b15D == null ? 0.0 : b15D);
            BigDecimal suma4 = new BigDecimal(b16D == null ? 0.0 : b16D);
            BigDecimal suma5 = new BigDecimal(b17D == null ? 0.0 : b17D);
            BigDecimal suma6 = new BigDecimal(b18D == null ? 0.0 : b18D);
            BigDecimal suma = suma1.add(suma2).add(suma3).add(suma4).add(suma5).add(suma6);
            b19 = String.valueOf(suma);
        }

    }

    public void cambiarCanelar() {
        estado4 = false;
        estado3 = true;
        estado5 = true;
        estado6 = false;
        estado2 = true;
        estado7 = true;
        estado8 = true;
        estado9 = true;
        estado11 = true;
    }

    public void sumaComponentes() {
        Double[] cadena = new Double[6];
        int i = 0;
        BigDecimal valor = new BigDecimal("0.0");
        cadena[0] = b13D == null ? 0.0 : b13D;
        cadena[1] = b14D == null ? 0.0 : b14D;
        cadena[2] = b15D == null ? 0.0 : b15D;
        cadena[3] = b16D == null ? 0.0 : b16D;
        cadena[4] = b17D == null ? 0.0 : b17D;
        cadena[5] = b18D == null ? 0.0 : b18D;
        while (i < cadena.length) {
            System.out.println(cadena[i]);
            if (cadena[i].equals(0.00)) {
                cadena[i] = 0.00;
            }
            BigDecimal nuevo = new BigDecimal(cadena[i]);
            valor = valor.add(nuevo);
            i++;
        }
        b19 = String.valueOf(valor);
        if (estado10 == 0) {
            if (Double.parseDouble(b19) == Double.parseDouble(aux1)) {
                color = "clase1";
                estado11 = false;
            } else {
                color = "clase2";
                estado11 = true;
            }
        } else {
            if (estado10 == 1) {
                if (Double.parseDouble(b19) == Double.parseDouble(b24Daux)) {
                    color = "clase1";
                    estado11 = false;
                } else {
                    color = "clase2";
                    estado11 = true;
                }
            }
        }

    }

    public String getMontoConcatenado(List<Montos> lista) {
        String cadena = "";
        for (int i = 0; i < lista.size(); i++) {
            cadena = cadena + lista.get(i).getFecha() + " - " + lista.get(i).getMonto();
            if (i < lista.size() - 1) {
                cadena = cadena + ", ";
            }
        }
        cadena = cadena + "\n";
        return cadena;
    }

    public void guardarNuevosComponentes() {
        GuardarNuevComp gnc = new GuardarNuevComp();
        FacesMessage message = null;
        try {
            String numero = montd.getNumMonto(b20);
            if(numero==null || numero==""){
                numero="0";
            }
            int numero_parse = Integer.parseInt(numero) + 1;
            gnc.setNum_monto(Double.parseDouble(String.valueOf(numero_parse)));
            gnc.setExp_tecn(b13D == null ? 0.0 : b13D);
            gnc.setInfraestructura(b14D == null ? 0.0 : b14D);
            gnc.setEquip_mobil(b15D == null ? 0.0 : b15D);
            gnc.setSupervision(b16D == null ? 0.0 : b16D);
            gnc.setCapacitacion(b17D == null ? 0.0 : b17D);
            gnc.setOtros(b18D == null ? 0.0 : b18D);
            gnc.setFecha_reg(getDate(fmmaux));
            gnc.setMonto_modif(b24Daux == " " ? 0.0 : Double.parseDouble(b24Daux));
            gnc.setTipo_reg("1");
            gnc.setId_proyecto(b20);
            this.bpi.guardarNuevosComponentes(gnc);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "SE HAN GUARDADO LOS COMPONENTES Y EL MONTO MODIFICADO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            mdf.clear();
            mdf = montd.getMontosModificados(b20);
            b24D = 0.0;
            fmm = "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HAN GUARDADO LOS COMPONENTES");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public void cambiarEstado() {
        estado2 = true;
        estado4 = false;
        estado3 = true;
        estado5 = false;
        estado6 = true;
        estado7 = false;
        estado8 = true;
        estado11 = false;
        estado9 = true;
        estado10 = 1;
        limpiarComponentes();
        System.out.println("AGREGAR");
        mdf.clear();
        mdf = montd.getMontosModificados(b20);

    }

    public void cambiarEstado2() {
        estado2 = false;
        estado3 = false;
        estado4 = true;
        estado5 = false;
        estado6 = false;
        estado7 = false;
        estado9 = true;
        estado8 = false;
        estado10 = 0;
        estado11 = false;
        limpiarComponentes();
        getExpedientesLista();
        getInformesLista();
        getResolucionesLista();
        System.out.println("MODIFICAR");
        montosModif.clear();
        List<Montos> listaaux = montd.getMontosModificados(b20);
        for (int i = 0; i < listaaux.size(); i++) {
            this.montosModif.add(listaaux.get(i).getMonto() + " - " + listaaux.get(i).getFecha());
        }

    }

    public void limpiarComponentes() {
        b13D = 0.0;
        b14D = 0.0;
        b15D = 0.0;
        b16D = 0.0;
        b17D = 0.0;
        b18D = 0.0;
        b19 = "";
    }

    public boolean isEstado4() {
        return estado4;
    }

    public void setEstado4(boolean estado4) {
        this.estado4 = estado4;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public String getB4() {
        return b4;
    }

    public void setB4(String b4) {
        this.b4 = b4;
    }

    public String getB5() {
        return b5;
    }

    public void setB5(String b5) {
        this.b5 = b5;
    }

    public String getB6() {
        return b6;
    }

    public void setB6(String b6) {
        this.b6 = b6;
    }

    public String getB7() {
        return b7;
    }

    public void setB7(String b7) {
        this.b7 = b7;
    }

    public String getB8() {
        return b8;
    }

    public void setB8(String b8) {
        this.b8 = b8;
    }

    public String getB9() {
        return b9;
    }

    public void setB9(String b9) {
        this.b9 = b9;
    }

    public String getB10() {
        return b10;
    }

    public void setB10(String b10) {
        this.b10 = b10;
    }

    public String getB11() {
        return b11;
    }

    public void setB11(String b11) {
        this.b11 = b11;
    }

    public String getB12() {
        return b12;
    }

    public void setB12(String b12) {
        this.b12 = b12;
    }

    public String getB19() {
        return b19;
    }

    public void setB19(String b19) {
        this.b19 = b19;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado2() {
        return estado2;
    }

    public void setEstado2(boolean estado2) {
        this.estado2 = estado2;
    }

    public boolean isEstado3() {
        return estado3;
    }

    public void setEstado3(boolean estado3) {
        this.estado3 = estado3;
    }

    public String getB20() {
        return b20;
    }

    public void setB20(String b20) {
        this.b20 = b20;
    }

    public String getB21() {
        return b21;
    }

    public void setB21(String b21) {
        this.b21 = b21;
    }

    public String getB22() {
        return b22;
    }

    public void setB22(String b22) {
        this.b22 = b22;
    }

    public String getB23() {
        return b23;
    }

    public void setB23(String b23) {
        this.b23 = b23;
    }

    public boolean isEstado5() {
        return estado5;
    }

    public void setEstado5(boolean estado5) {
        this.estado5 = estado5;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public List<BusqPreInversion> getListaBPI_1() {
        return listaBPI_1;
    }

    public void setListaBPI_1(List<BusqPreInversion> listaBPI_1) {
        this.listaBPI_1 = listaBPI_1;
    }

    public List<BusqPreInversion> getListaBPI_2() {
        return listaBPI_2;
    }

    public void setListaBPI_2(List<BusqPreInversion> listaBPI_2) {
        this.listaBPI_2 = listaBPI_2;
    }

    public BusqPreInversionDAO getBpi() {
        return bpi;
    }

    public void setBpi(BusqPreInversionDAO bpi) {
        this.bpi = bpi;
    }

    public MontosDAO getMontd() {
        return montd;
    }

    public void setMontd(MontosDAO montd) {
        this.montd = montd;
    }

    public List<Montos> getMdf() {
        return mdf;
    }

    public void setMdf(List<Montos> mdf) {
        this.mdf = mdf;
    }

    public String[] getMontos() {
        return montos;
    }

    public void setMontos(String[] montos) {
        this.montos = montos;
    }

    public MontosDAO getMont() {
        return mont;
    }

    public void setMont(MontosDAO mont) {
        this.mont = mont;
    }

    public String getWidhtcomp() {
        return widhtcomp;
    }

    public void setWidhtcomp(String widhtcomp) {
        this.widhtcomp = widhtcomp;
    }

    public boolean isEstado6() {
        return estado6;
    }

    public void setEstado6(boolean estado6) {
        this.estado6 = estado6;
    }

    public String getTipo_origen() {
        return tipo_origen;
    }

    public void setTipo_origen(String tipo_origen) {
        this.tipo_origen = tipo_origen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public List getLista_origen() {
        return lista_origen;
    }

    public void setLista_origen(List lista_origen) {
        this.lista_origen = lista_origen;
    }

    public boolean isEstado7() {
        return estado7;
    }

    public void setEstado7(boolean estado7) {
        this.estado7 = estado7;
    }

    public boolean isEstado8() {
        return estado8;
    }

    public void setEstado8(boolean estado8) {
        this.estado8 = estado8;
    }

    public boolean isEstado9() {
        return estado9;
    }

    public void setEstado9(boolean estado9) {
        this.estado9 = estado9;
    }

    public RegistroPreInversionDAO getRpd() {
        return rpd;
    }

    public void setRpd(RegistroPreInversionDAO rpd) {
        this.rpd = rpd;
    }

    public int getEstado10() {
        return estado10;
    }

    public void setEstado10(int estado10) {
        this.estado10 = estado10;
    }

    public List getLista_opi() {
        return lista_opi;
    }

    public void setLista_opi(List lista_opi) {
        this.lista_opi = lista_opi;
    }

    public List getLista_nivel() {
        return lista_nivel;
    }

    public void setLista_nivel(List lista_nivel) {
        this.lista_nivel = lista_nivel;
    }

    public String getNivel_estud() {
        return nivel_estud;
    }

    public void setNivel_estud(String nivel_estud) {
        this.nivel_estud = nivel_estud;
    }

    public String getOpi_resp() {
        return opi_resp;
    }

    public void setOpi_resp(String opi_resp) {
        this.opi_resp = opi_resp;
    }

    public boolean isEstado11() {
        return estado11;
    }

    public void setEstado11(boolean estado11) {
        this.estado11 = estado11;
    }

    public Double getB13D() {
        return b13D;
    }

    public void setB13D(Double b13D) {
        this.b13D = b13D;
    }

    public Double getB14D() {
        return b14D;
    }

    public void setB14D(Double b14D) {
        this.b14D = b14D;
    }

    public Double getB15D() {
        return b15D;
    }

    public void setB15D(Double b15D) {
        this.b15D = b15D;
    }

    public Double getB16D() {
        return b16D;
    }

    public void setB16D(Double b16D) {
        this.b16D = b16D;
    }

    public Double getB17D() {
        return b17D;
    }

    public void setB17D(Double b17D) {
        this.b17D = b17D;
    }

    public Double getB18D() {
        return b18D;
    }

    public void setB18D(Double b18D) {
        this.b18D = b18D;
    }

    public RegistroInversionDAO getRid() {
        return rid;
    }

    public void setRid(RegistroInversionDAO rid) {
        this.rid = rid;
    }

    public List<busquedaPreInversionMontos> getBqpim() {
        return bqpim;
    }

    public void setBqpim(List<busquedaPreInversionMontos> bqpim) {
        this.bqpim = bqpim;
    }

    public List<String> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(List<String> expedientes) {
        this.expedientes = expedientes;
    }

    public List<String> getInformes() {
        return informes;
    }

    public void setInformes(List<String> informes) {
        this.informes = informes;
    }

    public List<String> getResoluciones() {
        return resoluciones;
    }

    public void setResoluciones(List<String> resoluciones) {
        this.resoluciones = resoluciones;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public ListasGeneralesDAO getLgd() {
        return lgd;
    }

    public void setLgd(ListasGeneralesDAO lgd) {
        this.lgd = lgd;
    }

    public Double getB24D() {
        return b24D;
    }

    public void setB24D(Double b24D) {
        this.b24D = b24D;
    }

    public String getB25() {
        return b25;
    }

    public void setB25(String b25) {
        this.b25 = b25;
    }

    public List<String> getMontosModif() {
        return montosModif;
    }

    public void setMontosModif(List<String> montosModif) {
        this.montosModif = montosModif;
    }

    public String getB24Daux() {
        return b24Daux;
    }

    public void setB24Daux(String b24Daux) {
        this.b24Daux = b24Daux;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getB25aux() {
        return b25aux;
    }

    public void setB25aux(String b25aux) {
        this.b25aux = b25aux;
    }

    public Double getB26D() {
        return b26D;
    }

    public void setB26D(Double b26D) {
        this.b26D = b26D;
    }

    public void setB25D(Double b26D) {
        this.b26D = b26D;
    }

    public String getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(String nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public boolean isEstado12() {
        return estado12;
    }

    public void setEstado12(boolean estado12) {
        this.estado12 = estado12;
    }

    public Double getB4D() {
        return b4D;
    }

    public void setB4D(Double b4D) {
        this.b4D = b4D;
    }

    public String getAux1() {
        return aux1;
    }

    public void setAux1(String aux1) {
        this.aux1 = aux1;
    }

    public String getAux2() {
        return aux2;
    }

    public void setAux2(String aux2) {
        this.aux2 = aux2;
    }

    public Integer getIdcomp() {
        return idcomp;
    }

    public void setIdcomp(Integer idcomp) {
        this.idcomp = idcomp;
    }

    public String getFmm() {
        return fmm;
    }

    public void setFmm(String fmm) {
        this.fmm = fmm;
    }

    public String getFmmaux() {
        return fmmaux;
    }

    public void setFmmaux(String fmmaux) {
        this.fmmaux = fmmaux;
    }

    public Double getB6D() {
        return b6D;
    }

    public void setB6D(Double b6D) {
        this.b6D = b6D;
    }

    public List<HistorialMontos> getHistorialMontos() {
        return historialMontos;
    }

    public void setHistorialMontos(List<HistorialMontos> historialMontos) {
        this.historialMontos = historialMontos;
    }

}
