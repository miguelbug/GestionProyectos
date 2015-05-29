/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import gp.dao.TbVehiculosDAO;
import gp.daoImpl.TbVehiculosDaoImpl;
import gp.model.Expedientes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class Pruebas implements Serializable{

    TbVehiculosDAO tdao;
    List<Expedientes> lista;

    public Pruebas() {
        tdao = new TbVehiculosDaoImpl();
        lista = new ArrayList<Expedientes>();
        try{
        lista = tdao.selectAll();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public TbVehiculosDAO getTdao() {
        return tdao;
    }

    public void setTdao(TbVehiculosDAO tdao) {
        this.tdao = tdao;
    }

    public List<Expedientes> getLista() {
        return lista;
    }

    public void setLista(List<Expedientes> lista) {
        this.lista = lista;
    }

}
