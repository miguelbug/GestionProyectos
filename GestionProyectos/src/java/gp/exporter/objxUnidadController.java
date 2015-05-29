/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//ESTE ES PARA EL REPORTE
package gp.exporter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
//

@ManagedBean
@ViewScoped
public class objxUnidadController implements Serializable {

    private static final long serialVersionUID = 8797816477254175229L;
    FacesContext context;
    ServletContext serveltcontext;
    private int anioActual;
    private String docueliminar;
    private String ideliminar;
    private int opcionFormato;
    private int mesInicio;
    private int mesFin;
    private int mesActual;

    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    private Date date5;
    private Date date6;
    private String USUARIO;

    private String tipodocumento, tipodocumento1, tipodocumento2;
    private List docselec, docselec1, docselec2, docselec3, docselec4;

    private String loteinput;

    public objxUnidadController() {

    }

    public void abrirConfirmacion() {
        Map<String, String> hm = (HashMap<String, String>) docselec4.get(0);
        docueliminar = hm.get("documento").toString();
        ideliminar = hm.get("id").toString();
    }

    public void mostrarReporteNotasDeriv() throws SQLException {

        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        ReporteController repor;
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.clear();
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("context" + context);
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        System.out.println("sc = " + sc.getRealPath("/Reportes/"));
        repor = ReporteController.getInstance("NotasDerivadas");
        categoriaServicio categoriaServicio = new categoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);   /// para tIPO FORMATO  08/05
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", "ANA MARIA CHUCHON");
        parametros.put("logo", getLogo());
        parametros.put("oficina", "OFICINA GENERAL DE PLANIFICACION");
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            //no tiene hojas	
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void mostrarCargos() throws SQLException {
        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        ReporteController repor;
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.clear();
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("context" + context);
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        System.out.println("sc = " + sc.getRealPath("/reportes/"));
        repor = ReporteController.getInstance("CargosDocAreas");
        categoriaServicio categoriaServicio = new categoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("logo", getLogo());
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);

        if (!rpt && message == null) {
            //no tiene hojas	
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void mostrarReimpresion() throws SQLException {
        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        ReporteController repor;
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.clear();
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("context" + context);
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        System.out.println("sc = " + sc.getRealPath("/reportes/"));
        repor = ReporteController.getInstance("Reimpresion");
        categoriaServicio categoriaServicio = new categoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);
        FacesMessage message = null;
        boolean rpt = false;

        parametros.put("logo", getLogo());
        parametros.put("lote", loteinput);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);

        if (!rpt && message == null) {
            //no tiene hojas	
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void mostrarReporSeleccionadosUser() throws SQLException {
        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        ReporteController repor;
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.clear();

        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("context" + context);
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        System.out.println("sc = " + sc.getRealPath("/reportes/"));
        //repor = ReporteController.getInstance("reporteDocumentosSeleccionadosUser");
        repor = ReporteController.getInstance("seleccionados2");
        categoriaServicio categoriaServicio = new categoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);
        FacesMessage message = null;
        boolean rpt = false;

        parametros.put("logo", getLogo());

        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);

        if (!rpt && message == null) {
            //no tiene hojas	
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void mostrarReporSeleccionados() throws SQLException {
        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        ReporteController repor;
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.clear();

        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("context" + context);
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        System.out.println("sc = " + sc.getRealPath("/reportes/"));
        repor = ReporteController.getInstance("reporteDocumentosSeleccionados");
        categoriaServicio categoriaServicio = new categoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);
        FacesMessage message = null;
        boolean rpt = false;

        parametros.put("logo", getLogo());

        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);

        if (!rpt && message == null) {
            //no tiene hojas	
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void mostrarReporRegModPres() throws SQLException {
        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        ReporteController repor;
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.clear();

        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("context" + context);
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        System.out.println("sc = " + sc.getRealPath("/reportes/"));
        repor = ReporteController.getInstance("prueba3");
        categoriaServicio categoriaServicio = new categoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("logo", getLogo());
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);

        if (!rpt && message == null) {
            //no tiene hojas	
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public String partir(String nombre) {
        String[] cadena = new String[2];
        int i = 0;
        StringTokenizer tokens = new StringTokenizer(nombre);
        while (tokens.hasMoreTokens()) {
            cadena[i] = tokens.nextToken();
            i++;
        }
        return cadena[0];
    }

    public void setAnioActual(int anioActual) {
        this.anioActual = anioActual;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public ServletContext getServeltcontext() {
        return serveltcontext;
    }

    public void setServeltcontext(ServletContext serveltcontext) {
        this.serveltcontext = serveltcontext;
    }

    public int getOpcionFormato() {
        return opcionFormato;
    }

    public void setOpcionFormato(int opcionFormato) {
        this.opcionFormato = opcionFormato;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(int mesInicio) {
        this.mesInicio = mesInicio;
    }

    public int getMesFin() {
        return mesFin;
    }

    public void setMesFin(int mesFin) {
        this.mesFin = mesFin;
    }

    public int getMesActual() {
        return mesActual;
    }

    public void setMesActual(int mesActual) {
        this.mesActual = mesActual;
    }

    public String getLogo() {
        String logo = "";
        logo = serveltcontext.getRealPath("/resources/img/" + "escudo_reporte" + ".jpg");
        return logo;
    }

    /* public String obtenerUsuario(){
	
     String nombre="";
	
     try{
     ExternalContext context = 
     FacesContext.getCurrentInstance().getExternalContext();
     HttpServletRequest request = 
     (HttpServletRequest) context.getRequest();
		
     Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");  
     nombre = usuario.getUsuNombre();
		
     }catch(Exception e){
		
     }
	
     return nombre;
	
	
     }*/
    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public Date getDate4() {
        return date4;
    }

    public void setDate4(Date date4) {
        this.date4 = date4;
    }

    public Date getDate5() {
        return date5;
    }

    public void setDate5(Date date5) {
        this.date5 = date5;
    }

    public Date getDate6() {
        return date6;
    }

    public void setDate6(Date date6) {
        this.date6 = date6;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public List getDocselec() {
        return docselec;
    }

    public void setDocselec(List docselec) {
        this.docselec = docselec;
    }

    public String getTipodocumento1() {
        return tipodocumento1;
    }

    public void setTipodocumento1(String tipodocumento1) {
        this.tipodocumento1 = tipodocumento1;
    }

    public String getTipodocumento2() {
        return tipodocumento2;
    }

    public void setTipodocumento2(String tipodocumento2) {
        this.tipodocumento2 = tipodocumento2;
    }

    public List getDocselec1() {
        return docselec1;
    }

    public void setDocselec1(List docselec1) {
        this.docselec1 = docselec1;
    }

    public List getDocselec2() {
        return docselec2;
    }

    public void setDocselec2(List docselec2) {
        this.docselec2 = docselec2;
    }

    public List getDocselec3() {
        return docselec3;
    }

    public void setDocselec3(List docselec3) {
        this.docselec3 = docselec3;
    }

    public String getLoteinput() {
        return loteinput;
    }

    public void setLoteinput(String loteinput) {
        this.loteinput = loteinput;
    }

    public List getDocselec4() {
        return docselec4;
    }

    public void setDocselec4(List docselec4) {
        this.docselec4 = docselec4;
    }

    public String getDocueliminar() {
        return docueliminar;
    }

    public void setDocueliminar(String docueliminar) {
        this.docueliminar = docueliminar;
    }

    public String getIdeliminar() {
        return ideliminar;
    }

    public void setIdeliminar(String ideliminar) {
        this.ideliminar = ideliminar;
    }

}
