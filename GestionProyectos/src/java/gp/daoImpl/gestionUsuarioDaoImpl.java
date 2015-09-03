/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.gestionUsuarioDAO;
import gp.model.Usuario;
import gp.model.actualizarUsuario;
import gp.model.listarUsuarios;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class gestionUsuarioDaoImpl implements gestionUsuarioDAO{
    
    private final SqlSessionFactory sqlSessionFactory;

    public gestionUsuarioDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public gestionUsuarioDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    @Override
    public List<listarUsuarios> listarUsuario() {
        List<listarUsuarios> etapas = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            etapas = session.selectList("UsuarioData.lista_usuarios");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return etapas;
    }

    @Override
    public void guardarUsuario(Usuario u) {
         SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("UsuarioData.insert_usu", u);
            session.commit();
        } catch (Exception e) {
            System.out.println("UsuarioData.insert_gnc");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void actualizarUsario(actualizarUsuario u) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int a = session.update("UsuarioData.actualizar_usu", u);
            session.commit();
            System.out.println("se han actualizado: " + a);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Integer getTipoUsuario(String nombre) {
        Integer inte = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inte = session.selectOne("UsuarioData.getIdTipoUsuario", nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return inte;
    }
    
}
