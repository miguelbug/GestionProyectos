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
import gp.model.MostrarAPG;
import java.util.ArrayList;
import java.util.List;
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
public class EliminarProyectoBean {

    private List<MostrarAPG> listaProyectos;
    private String codigo;
    private ListasGeneralesDAO lgd;
    private RegistroInversionDAO rid;
    private MostrarAPG selectedAPG;
    private List<MostrarAPG> listafiltro;
    
    public EliminarProyectoBean() {
        lgd = new ListasGeneralesDaoImpl();
        codigo = "";
        listaProyectos = new ArrayList<MostrarAPG>();
        rid= new RegistroInversionDaoImpl();

    }

    public void buscar() {
        listaProyectos.clear();
        if(codigo.equals("")){
            listaProyectos = lgd.getProyeco2();
        }else{
            listaProyectos = lgd.getProyeco(codigo);
        }
        codigo = "";
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
    
    public void eliminarProyecto(){
        FacesMessage message = null;
        try{
            System.out.println("Codigo: "+selectedAPG.getCodigo());
            lgd.EliminarProyecto(selectedAPG.getCodigo());
            listaProyectos.clear();
            listaProyectos = lgd.getProyeco2();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "SE HA ELIMINADO EL PROYECTO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }catch(Exception e){
            System.out.println(e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "NO SE HA ELIMINAR EL PROYECTO");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public List<MostrarAPG> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<MostrarAPG> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ListasGeneralesDAO getLgd() {
        return lgd;
    }

    public void setLgd(ListasGeneralesDAO lgd) {
        this.lgd = lgd;
    }

    public RegistroInversionDAO getRid() {
        return rid;
    }

    public void setRid(RegistroInversionDAO rid) {
        this.rid = rid;
    }

    public MostrarAPG getSelectedAPG() {
        return selectedAPG;
    }

    public void setSelectedAPG(MostrarAPG selectedAPG) {
        this.selectedAPG = selectedAPG;
    }

    public List<MostrarAPG> getListafiltro() {
        return listafiltro;
    }

    public void setListafiltro(List<MostrarAPG> listafiltro) {
        this.listafiltro = listafiltro;
    }

}
