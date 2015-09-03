/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import gp.dao.ListasGeneralesDAO;
import gp.dao.gestionUsuarioDAO;
import gp.daoImpl.ListasGeneralesDaoImpl;
import gp.daoImpl.gestionUsuarioDaoImpl;
import gp.model.Usuario;
import gp.model.actualizarUsuario;
import gp.model.listarUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class gestionUsuarios {

    private List<listarUsuarios> listaUsu;
    private gestionUsuarioDAO gu;
    private ListasGeneralesDAO lgd;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String tipoUsu;
    private String claveUsuario;
    private String nombreUsuario;
    private List<String> tipoUsuario;
    private List<String> estado;

    public gestionUsuarios() {
        lgd= new ListasGeneralesDaoImpl();
        gu = new gestionUsuarioDaoImpl();
        listaUsu = gu.listarUsuario();
        tipoUsuario=new ArrayList<String>();
        estado=new ArrayList<String>();
        tipoUsuario=lgd.getTipoUsuario();      
        estado.add("ACTIVO");
        estado.add("INACTIVO");
    }

    public void formatearValores() {
        nombre = "";
        apellidoPat = "";
        apellidoMat = "";
        tipoUsu = " ";
        claveUsuario = "";
        nombreUsuario = "";
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            actualizarUsuario actu = new actualizarUsuario();
            String estadonew="";
            String nombretipo="";
            actu.setIdusuario(((listarUsuarios) event.getObject()).getIdUsuario());
            actu.setApellidos(((listarUsuarios) event.getObject()).getApellidos());
            estadonew=((listarUsuarios) event.getObject()).getEstado();
            actu.setEstado(estadonew.equals("ACTIVO") ? 'A': 'I');
            actu.setNombre(((listarUsuarios) event.getObject()).getNombre());
            nombretipo=((listarUsuarios) event.getObject()).getTipoUsuario();
            actu.setIdtipo(gu.getTipoUsuario(nombretipo));
            actu.setUsu_nombre(((listarUsuarios) event.getObject()).getUsuario());
            gu.actualizarUsario(actu);
            listaUsu.clear();
            listaUsu = gu.listarUsuario();
            FacesMessage message = null;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "SE HA ACTUALIZADO AL USUARIO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
            FacesMessage message = null;
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA PODIDO ACTUALIZAR AL USUARIO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.println(e.getMessage());
        }
    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("NO SE HIZO NADA");
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "NO SE HA ACTUALIZADO NINGUN DOCUMENTO");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void guardarUsuario() {
        Usuario u = new Usuario();
        u.setNombreUsuario(this.nombre.toUpperCase());
        u.setUsuario(nombreUsuario);
        u.setClave(claveUsuario);
        u.setTipoUsuario(Integer.parseInt(tipoUsu));
        u.setApellidos((apellidoPat + " " + apellidoMat).toUpperCase());
        u.setEstado("A");
        gu.guardarUsuario(u);
        listaUsu.clear();
        listaUsu = gu.listarUsuario();
    }

    public void unirNombre() {
        nombreUsuario = "";
        try {
            if (!nombre.isEmpty()) {
                nombreUsuario = nombreUsuario + String.valueOf(nombre.charAt(0));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void unirApellido() {
        try {
            if (!apellidoPat.isEmpty()) {
                nombreUsuario = nombreUsuario + apellidoPat;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<listarUsuarios> getListaUsu() {
        return listaUsu;
    }

    public void setListaUsu(List<listarUsuarios> listaUsu) {
        this.listaUsu = listaUsu;
    }

    public gestionUsuarioDAO getGu() {
        return gu;
    }

    public void setGu(gestionUsuarioDAO gu) {
        this.gu = gu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public ListasGeneralesDAO getLgd() {
        return lgd;
    }

    public void setLgd(ListasGeneralesDAO lgd) {
        this.lgd = lgd;
    }

    public List<String> getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(List<String> tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<String> getEstado() {
        return estado;
    }

    public void setEstado(List<String> estado) {
        this.estado = estado;
    }

}
