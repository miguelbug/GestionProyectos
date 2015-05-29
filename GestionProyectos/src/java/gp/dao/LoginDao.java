/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.Usuario;

/**
 *
 * @author OGPL
 */
public interface LoginDao {
    public Usuario getUsuario(String usuario, String clave);
}
