/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.LoginDao;
import gp.model.Usuario;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class LoginDaoImpl implements LoginDao{
    
    private SqlSessionFactory sqlSessionFactory;

    public LoginDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    public LoginDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    @Override
    public Usuario getUsuario(String usuario, String clave) {
        SqlSession session = sqlSessionFactory.openSession();
        Usuario usu=null;
        Map parameters = new HashMap();
        parameters.put("usuario", usuario);
        parameters.put("clave", clave);
        try {
            System.out.println(parameters);
            usu = session.selectOne("UsuarioData.getUsuario",parameters);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL GET USU");
        } 
        finally {
            session.close();
        }
        return usu;
    }
    
}
