/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class tipo_usuario implements Serializable{

    private String nombre_tipo;
    private Integer idtipoUsuario;
    
    public tipo_usuario() {
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public Integer getIdtipoUsuario() {
        return idtipoUsuario;
    }

    public void setIdtipoUsuario(Integer idtipoUsuario) {
        this.idtipoUsuario = idtipoUsuario;
    }
    
}
