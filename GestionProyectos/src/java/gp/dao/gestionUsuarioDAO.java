/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.Usuario;
import gp.model.actualizarUsuario;
import gp.model.listarUsuarios;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface gestionUsuarioDAO {
    public List<listarUsuarios> listarUsuario();
    public void guardarUsuario(Usuario u);
    public void actualizarUsario(actualizarUsuario u);
    public Integer getTipoUsuario(String nombre);
    
}
