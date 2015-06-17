/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.BusquedaInversionDAO;
import gp.model.DetalleExpTecnico;
import gp.model.MostrarEjecucion;
import gp.model.MostrarExpedientesTecnicos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class BusquedaInversionDaoImpl implements BusquedaInversionDAO {

    private SqlSessionFactory sqlSessionFactory;

    public BusquedaInversionDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public BusquedaInversionDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<MostrarExpedientesTecnicos> getListaExpedientesTecnicos(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<MostrarExpedientesTecnicos> list = null;
        try {
            list = session.selectList("BusquedaInversion.getExpTecn", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<MostrarEjecucion> getEjecucion(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<MostrarEjecucion> list = null;
        try {
            list = session.selectList("BusquedaInversion.getEjecu", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<DetalleExpTecnico> getDetalleExpedTecnico(String codigo, String exptecnico) {
        SqlSession session = sqlSessionFactory.openSession();
        Map m= new HashMap();
        m.put("codigo", codigo);
        m.put("expediente", exptecnico);
        List<DetalleExpTecnico> list = null;
        try {
            list = session.selectList("BusquedaInversion.getDetalleExpTecn", m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public String getNombreProy(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        String list = null;
        try {
            list = session.selectOne("BusquedaInversion.getProy", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

}
