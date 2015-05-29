/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class Registro_Inversion {

    private String nombre_exp;
    private String codigo;
    
    public Registro_Inversion() {
    }

    public String getNombre_exp() {
        return nombre_exp;
    }

    public void setNombre_exp(String nombre_exp) {
        this.nombre_exp = nombre_exp;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
