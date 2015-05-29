/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.ListasGeneralesDAO;
import gp.model.BusqPreInversion;
import gp.model.Expedientes;
import gp.model.Resoluciones;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class ListasGeneralesDaoImpl implements ListasGeneralesDAO {

    private SqlSessionFactory sqlSessionFactory;

    public ListasGeneralesDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public ListasGeneralesDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<String> getExpedientes() {
        SqlSession session = sqlSessionFactory.openSession();
        List<String> list = null;
        try {
            list = session.selectList("ListasGenerales.getExpedientes");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<String> getResoluciones() {
        SqlSession session = sqlSessionFactory.openSession();
        List<String> list = null;
        try {
            list = session.selectList("ListasGenerales.getResoluciones");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<String> getInformes() {
        SqlSession session = sqlSessionFactory.openSession();
        List<String> list = null;
        try {
            list = session.selectList("ListasGenerales.getInformesOPYP");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

}
