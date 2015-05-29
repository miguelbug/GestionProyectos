/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import gp.dao.LoginDao;
import gp.daoImpl.LoginDaoImpl;
import gp.model.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author OGPL
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String usuario;
    private String pass;
    private LoginDao logindao;
    private HttpServletRequest httpServletRequest;
    private FacesContext contex;
    private Usuario usu;
    private String direccion;

    public LoginBean() {
        usuario = "";
        pass = "";
        logindao = new LoginDaoImpl();
        contex = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) contex.getExternalContext().getRequest();
        if (usu == null) {
            usu = new Usuario();
        }
    }

    public String ActionLogin() {
        String url = "index";
        if (!usuario.equals("") && !pass.equals("")) {
            try {
                usu = logindao.getUsuario(usuario, pass);
                System.out.println(usu.getEstado()+"-"+usu.getNombreUsuario()+"-"+usu.getTipoNombre()+"-"+usu.getUsuario());
                contex = FacesContext.getCurrentInstance();
                httpServletRequest = (HttpServletRequest) contex.getExternalContext().getRequest();
                httpServletRequest.getSession().setAttribute("sesionUsuario", usu);
                getPathRol();
                url = "template";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido","Usuario: "+usu.getNombreUsuario());
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception e) {
                System.out.println("entra a catch");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('val2').show();");
                url = "index";
                limpiarlabels();
            }
        } else {
            if (usuario.equals("") && pass.equals("")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('val3').show();");
                limpiarlabels();
            }

        }
        return url;
    }

    public String logout() {
        limpiarlabels();
        httpServletRequest.removeAttribute("sesionUsuario");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public void limpiarlabels() {
        this.setUsuario("");
        this.setPass("");
    }

    public void getPathRol() {
        if (usu != null) {
            if (usu.getTipoNombre().equals("REGISTRADOR")) {
                direccion = "menu_registrador.xhtml";
            }
            if (usu.getTipoNombre().equals("CONSULTOR")) {
                direccion = "menu_consultor.xhtml";
            }
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
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

    public LoginDao getLogindao() {
        return logindao;
    }

    public void setLogindao(LoginDao logindao) {
        this.logindao = logindao;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
