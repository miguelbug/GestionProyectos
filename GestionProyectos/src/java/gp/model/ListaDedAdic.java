/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.model;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@RequestScoped
public class ListaDedAdic implements Serializable{

    private String numDedAdic;
    private String motivo;
    private Date monto;
    
    public ListaDedAdic() {
    }

    public String getNumDedAdic() {
        return numDedAdic;
    }

    public void setNumDedAdic(String numDedAdic) {
        this.numDedAdic = numDedAdic;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getMonto() {
        return monto;
    }

    public void setMonto(Date monto) {
        this.monto = monto;
    }
    
}
