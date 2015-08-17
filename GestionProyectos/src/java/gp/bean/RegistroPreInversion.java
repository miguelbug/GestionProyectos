/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import gp.dao.BusqPreInversionDAO;
import gp.dao.ListasGeneralesDAO;
import gp.dao.RegistroPreInversionDAO;
import gp.daoImpl.BusqPreInversionDaoImpl;
import gp.daoImpl.ListasGeneralesDaoImpl;
import gp.daoImpl.RegistroPreInversionDaoImpl;
import gp.model.AspectosGenerales;
import gp.model.Componentes;
import gp.model.Opi_responsable;
import gp.model.Origen;
import gp.model.Usuario;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class RegistroPreInversion {

    private Integer codigo_int = null;
    private String nombre_proy;
    private String origen;
    private String monto;
    private Double montoD = null;
    private String fecha_viab_aux;
    private String monto_modif;
    private String fecha_montomodif_aux;
    private String informe_tecnico;
    private String exped_administ;
    private String numero_RR;
    private String opi_resp;
    private String nivel_estud;
    private Date fecha_registro_prei;
    private List lista_origen;
    ////////////////////////
    private String exp_tecnico;
    private Double exp_tecnicoD = null;
    private String infraestructura;
    private Double infraestructuraD = null;
    private String equip_mobili;
    private Double equip_mobiliD = null;
    private String supervision;
    private Double supervisionD = null;
    private String capacitacion;
    private Double capacitacionD = null;
    private String otros;
    private Double otrosD = null;
    private String total;
    private Double totalD = null;
    ///////////////////////////
    private List lista_opi;
    private List lista_nivel;
    private String tipo_origen;
    private FacesContext contex;
    private String hora;
    private Usuario usu;
    private Date fecha;
    private RegistroPreInversionDAO rpd;
    private boolean estado;
    private final FacesContext faceContext;
    private boolean estado2;
    private boolean estado3;
    private boolean estado4;
    private String color;
    private Double montoauxiliar = new Double(0);
    private int numerotab;
    private ListasGeneralesDAO lgd;
    private List<String> expedientes;
    private List<String> informes;
    private List<String> resoluciones;
    private BusqPreInversionDAO bpi;
    public boolean error;

    public RegistroPreInversion() {
        faceContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(true);
        usu = (Usuario) session.getAttribute("sesionUsuario");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String currentPage = facesContext.getViewRoot().getViewId();
        fecha = new Date();
        rpd = new RegistroPreInversionDaoImpl();
        lista_opi = new ArrayList<String>();
        lista_nivel = new ArrayList<String>();
        lista_origen = new ArrayList<String>();
        expedientes = new ArrayList<String>();
        informes = new ArrayList<String>();
        resoluciones = new ArrayList<String>();
        bpi = new BusqPreInversionDaoImpl();
        llenar_OPI();
        llenar_NE();
        estado = true;
        ObtenerHora();
        estado2 = false;
        color = "classTwo";
        exp_tecnico = "";
        infraestructura = "";
        equip_mobili = "";
        supervision = "";
        capacitacion = "";
        otros = "";
        estado3 = true;
        estado4 = false;
        numerotab = 0;
        total = "0.0";
        lgd = new ListasGeneralesDaoImpl();
        boolean isdocumentos = (currentPage.lastIndexOf("reg_preInversion.xhtml") > -1);
        if (isdocumentos) {
            System.out.println("DEBERIA ENTRAR");
            getExpedientesLista();
            getInformesLista();
            getResolucionesLista();
        }
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

    public void almacenarMonto() {
        montoauxiliar = montoD;
    }

    public void validarCodigo() {
        FacesMessage message = null;
        boolean esta = rpd.validarProyecto(codigo_int);
        if (esta == true) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "EL PROYECTO YA SE ENCUENTRA REGISTRADO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            estado4 = true;
        } else if (esta == false) {
            estado4 = false;
        }

    }

    public void onTabChange(TabChangeEvent event) {
        /*if (event.getTab().getTitle().equals("COMPONENTES")) {
            System.out.println(monto);
        }*/
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

    public Date pasar_A_Date() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = sdf.parse(fecha_viab_aux + " " + hora);
        return date;
    }

    public void ObtenerHora() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        hora = sdf.format(date);
    }

    public void limpiarAG() {
        codigo_int = null;
        nombre_proy = "";
        montoD = null;
        fecha_viab_aux = "";
        informe_tecnico = "";
        exped_administ = "";
        numero_RR = "";
        origen = " ";
        opi_resp = " ";
        nivel_estud = " ";
    }

    public void limpiarComp() {
        exp_tecnicoD = null;
        infraestructuraD = null;
        equip_mobiliD = null;
        supervisionD = null;
        capacitacionD = null;
        otrosD = null;
        totalD = null;
    }

    public void GuardarAG() throws ParseException {
        FacesMessage message = null;
        try {
            AspectosGenerales ag = new AspectosGenerales();
            ag.setCodigo(codigo_int);
            ag.setNombre(this.nombre_proy.toUpperCase());
            ag.setMontoViabilidad(montoD);
            ag.setFechaViabilidad(pasar_A_Date());
            ag.setInformeTecnico(informe_tecnico.toUpperCase());
            ag.setExpedAdm(this.exped_administ.toUpperCase());
            ag.setNumRR(numero_RR.toUpperCase());
            ag.setOrigen(rpd.getId_Origen(origen));
            ag.setOpiResp(rpd.getId_Opi(this.opi_resp));
            ag.setNivEstud(rpd.getId_Nivel(this.nivel_estud));
            ag.setUsuario(rpd.getId_Usuario(usu.getUsuario()));
            rpd.RegistrarAspecGeneral(ag);
            estado = false;
            estado2 = true;
            estado4 = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA GUARDADO EL PROYECTO " + nombre_proy.toUpperCase() + ", CON SNIP: " + codigo_int);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!!!ERROR¡¡¡", "NO SE HA PODIDO GUARDAR EL PROYECTO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.println("ERROR GUARDAR AG");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void sumaComponentes() {
        Double[] cadena = new Double[6];
        BigDecimal valor = new BigDecimal("0.0");
        int i = 0;
        cadena[0] = this.exp_tecnicoD == null ? 0.0 : this.exp_tecnicoD;
        cadena[1] = this.infraestructuraD == null ? 0.0 : this.infraestructuraD;
        cadena[2] = this.equip_mobiliD == null ? 0.0 : this.equip_mobiliD;
        cadena[3] = this.supervisionD == null ? 0.0 : this.supervisionD;
        cadena[4] = this.capacitacionD == null ? 0.0 : this.capacitacionD;
        cadena[5] = this.otrosD == null ? 0.0 : this.otrosD;
        while (i < cadena.length) {
            if (cadena[i].equals(0.0)) {
                cadena[i] = 0.0;
            }
            BigDecimal nuevo = new BigDecimal(cadena[i]);
            valor = valor.add(nuevo);
            i++;
        }
        totalD = valor.doubleValue();
        if (totalD.equals(montoauxiliar)) {
            color = "clase1";
            estado3 = false;
        } else {
            if (!totalD.equals(montoauxiliar)) {
                color = "clase2";
                estado3 = true;
            }

        }
    }

    public void Guardar_Comp() throws ParseException {
        FacesMessage message = null;
        try {
            Componentes comp = new Componentes();
            comp.setCodigoProy(codigo_int);
            comp.setMontoExpTec(this.exp_tecnicoD);
            comp.setMontoInfra(this.infraestructuraD);
            comp.setMontoEquipMov(this.equip_mobiliD);
            comp.setMontoSuperv(this.supervisionD);
            comp.setMontoCapac(this.capacitacionD);
            comp.setMontoOtros(this.otrosD);
            comp.setFecharegistro(pasar_A_Date());
            comp.setMontoModif(0.0);
            comp.setNumMonto(0);
            comp.setEtapa(0);
            comp.setNumeroExp(0);
            comp.setTipoDocu(1);
            comp.setNumeroRR("Sin RR");
            comp.setEstado(0);
            rpd.RegistrarComponentes(comp);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "REALIZADO", "SE HA GUARDADO HAN GUARDADO TODOS LOS COMPONENTES");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            limpiarAG();
            limpiarComp();
            estado2 = false;
            estado4 = false;
            estado = true;
            numerotab = 0;
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "!!!ERROR¡¡¡", "NO SE HA PODIDO GUARDAR LOS COMPONENTES");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.println("ERROR GUARDAR COMP");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public void validarProyecto() {
        System.out.println("Validacion: " + bpi.validarProyecto(String.valueOf(codigo_int)));
        if (bpi.validarProyecto(String.valueOf(codigo_int)) != null) {
            estado2 = true;
            estado4 = true;
            error = true;
        } else {
            if (bpi.validarProyecto(String.valueOf(codigo_int)) == null) {
                estado2 = false;
                estado4 = false;
                error = false;
            }
        }
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFecha_viab_aux() {
        return fecha_viab_aux;
    }

    public void setFecha_viab_aux(String fecha_viab_aux) {
        this.fecha_viab_aux = fecha_viab_aux;
    }

    public String getMonto_modif() {
        return monto_modif;
    }

    public void setMonto_modif(String monto_modif) {
        this.monto_modif = monto_modif;
    }

    public String getFecha_montomodif_aux() {
        return fecha_montomodif_aux;
    }

    public void setFecha_montomodif_aux(String fecha_montomodif_aux) {
        this.fecha_montomodif_aux = fecha_montomodif_aux;
    }

    public String getInforme_tecnico() {
        return informe_tecnico;
    }

    public void setInforme_tecnico(String informe_tecnico) {
        this.informe_tecnico = informe_tecnico;
    }

    public String getExped_administ() {
        return exped_administ;
    }

    public void setExped_administ(String exped_administ) {
        this.exped_administ = exped_administ;
    }

    public String getNumero_RR() {
        return numero_RR;
    }

    public void setNumero_RR(String numero_RR) {
        this.numero_RR = numero_RR;
    }

    public String getOpi_resp() {
        return opi_resp;
    }

    public void setOpi_resp(String opi_resp) {
        this.opi_resp = opi_resp;
    }

    public String getNivel_estud() {
        return nivel_estud;
    }

    public void setNivel_estud(String nivel_estud) {
        this.nivel_estud = nivel_estud;
    }

    public Date getFecha_registro_prei() {
        return fecha_registro_prei;
    }

    public void setFecha_registro_prei(Date fecha_registro_prei) {
        this.fecha_registro_prei = fecha_registro_prei;
    }

    public String getNombre_proy() {
        return nombre_proy;
    }

    public void setNombre_proy(String nombre_proy) {
        this.nombre_proy = nombre_proy;
    }

    public String getExp_tecnico() {
        return exp_tecnico;
    }

    public void setExp_tecnico(String exp_tecnico) {
        this.exp_tecnico = exp_tecnico;
    }

    public String getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(String infraestructura) {
        this.infraestructura = infraestructura;
    }

    public String getEquip_mobili() {
        return equip_mobili;
    }

    public void setEquip_mobili(String equip_mobili) {
        this.equip_mobili = equip_mobili;
    }

    public String getSupervision() {
        return supervision;
    }

    public void setSupervision(String supervision) {
        this.supervision = supervision;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getTipo_origen() {
        return tipo_origen;
    }

    public void setTipo_origen(String tipo_origen) {
        this.tipo_origen = tipo_origen;
    }

    public FacesContext getContex() {
        return contex;
    }

    public void setContex(FacesContext contex) {
        this.contex = contex;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Origen> getLista_origen() {
        return lista_origen;
    }

    public void setLista_origen(List<Origen> lista_origen) {
        this.lista_origen = lista_origen;
    }

    public RegistroPreInversionDAO getRpd() {
        return rpd;
    }

    public void setRpd(RegistroPreInversionDAO rpd) {
        this.rpd = rpd;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isEstado2() {
        return estado2;
    }

    public void setEstado2(boolean estado2) {
        this.estado2 = estado2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEstado3() {
        return estado3;
    }

    public void setEstado3(boolean estado3) {
        this.estado3 = estado3;
    }

    public Double getMontoauxiliar() {
        return montoauxiliar;
    }

    public void setMontoauxiliar(Double montoauxiliar) {
        this.montoauxiliar = montoauxiliar;
    }

    public boolean isEstado4() {
        return estado4;
    }

    public void setEstado4(boolean estado4) {
        this.estado4 = estado4;
    }

    public int getNumerotab() {
        return numerotab;
    }

    public void setNumerotab(int numerotab) {
        this.numerotab = numerotab;
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

    public String getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(String capacitacion) {
        this.capacitacion = capacitacion;
    }

    public Double getCapacitacionD() {
        return capacitacionD;
    }

    public void setCapacitacionD(Double capacitacionD) {
        this.capacitacionD = capacitacionD;
    }

    public Double getMontoD() {
        return montoD;
    }

    public void setMontoD(Double montoD) {
        this.montoD = montoD;
    }

    public ListasGeneralesDAO getLgd() {
        return lgd;
    }

    public void setLgd(ListasGeneralesDAO lgd) {
        this.lgd = lgd;
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

    public Integer getCodigo_int() {
        return codigo_int;
    }

    public void setCodigo_int(Integer codigo_int) {
        this.codigo_int = codigo_int;
    }

    public BusqPreInversionDAO getBpi() {
        return bpi;
    }

    public void setBpi(BusqPreInversionDAO bpi) {
        this.bpi = bpi;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
