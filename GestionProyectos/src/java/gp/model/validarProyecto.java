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
public class validarProyecto {

    private Integer validar;
    
    public validarProyecto() {
    }

    public Integer getValidar() {
        return validar;
    }

    public void setValidar(Integer validar) {
        this.validar = validar;
    }
    
}
