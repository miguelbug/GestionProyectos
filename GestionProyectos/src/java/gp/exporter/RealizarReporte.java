/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//ESTE ES PARA EL REPORTE
package gp.exporter;

import gp.model.Usuario;
import java.io.Serializable;
;
import javax.faces.application.FacesMessage;
import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



@ManagedBean
@ViewScoped
public class RealizarReporte implements Serializable {

    private static final long serialVersionUID = 8797816477254175229L;
    FacesContext context;
    ServletContext serveltcontext;
    private int opcionFormato;
    ReporteController repor;
    CategoriaServicio categoriaServicio;
    HashMap<String, Object> parametros;

    public RealizarReporte() {

    }

    public void Inicializar(String reporte) throws SQLException {
        context = FacesContext.getCurrentInstance();
        serveltcontext = (ServletContext) context.getExternalContext().getContext();
        repor = new ReporteController();
        parametros = new HashMap<String, Object>();
        parametros.clear();
        repor = ReporteController.getInstance(reporte);
        categoriaServicio = new CategoriaServicio();
        repor.setConexion(categoriaServicio.getConexion());
        repor.setTipoFormato(opcionFormato);
    }

    public void ReporteProyectosXCodigo(Usuario usu, String codigo, String reporte) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("codigo", codigo);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteProyectosXFecha(Usuario usu, Date date1, Date date2, String reporte, String suma) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("fechaIn", date1);
        parametros.put("fechaFin", date2);
        parametros.put("suma", suma);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteProyectosxNombre(Usuario usu, String nombre, String reporte, String suma) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("nombre", nombre);
        parametros.put("suma", suma);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reportePoryectosxNombre2(Usuario usu, String nombre, String reporte) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("codigo", nombre);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteProyectosSoloFacus(Usuario usu, String reporte, String suma) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("suma", suma);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteProyectosSoloDepes(Usuario usu, String reporte, String suma) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("suma", suma);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteProyectosFacusDepes(Usuario usu, String reporte, String suma) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("suma", suma);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteEjecucionPorAnio(String reporte, Usuario usu, String codigo) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("codigo", codigo);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteEjecucionPorMes(String reporte, Usuario usu, String codigo, String anio, String nombreProy) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("codigo", codigo);
        parametros.put("anio", anio);
        parametros.put("nombreProyecto", nombreProy);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteAdicionalesDeductivos(String reporte, Usuario usu, String codigo, String nombreProy) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("codigo", codigo);
        parametros.put("nombreProy", nombreProy);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
    }

    public void reporteExpedientesTecnicos(String reporte, Usuario usu, String codigo, String nombreProy) throws SQLException {
        Inicializar(reporte);
        FacesMessage message = null;
        boolean rpt = false;
        parametros.put("usuario", usu.getNombreUsuario());
        parametros.put("logo", getLogo());
        parametros.put("codigo", codigo);
        parametros.put("nombreProy", nombreProy);
        repor.addMapParam(parametros);
        rpt = repor.ejecutaReporte(context, serveltcontext);
        if (!rpt && message == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "No hay datos para generar reporte");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        categoriaServicio.CerrandoConexion();
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

    public String getLogo() {
        String logo = "";
        logo = serveltcontext.getRealPath("image/" + "escudo_reporte" + ".jpg");
        return logo;
    }
}
