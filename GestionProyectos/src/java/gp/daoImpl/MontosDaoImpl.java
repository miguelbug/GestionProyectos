/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.MontosDAO;
import gp.model.Montos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class MontosDaoImpl implements MontosDAO {
    
    private SqlSessionFactory sqlSessionFactory;

    public MontosDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public MontosDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Integer getNumMonto0(Integer proy, String etapa) {
        Integer nummonto = null;
        SqlSession session = sqlSessionFactory.openSession();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("idproyecto", proy);
        map.put("etapa", etapa);
        try {
            nummonto = session.selectOne("MontosData.numMonto_cero", map);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return nummonto;
    }

    @Override
    public Integer getNumMontoViab(Integer codigo) {
        Integer nummonto = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            nummonto = session.selectOne("MontosData.numMonto_viable", codigo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return nummonto;
    }

    @Override
    public Integer getNumMonto(String codigo) {
        Integer nummonto = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            nummonto = session.selectOne("MontosData.numMonto_select", codigo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return nummonto;
    }
    
    @Override
    public List<Montos> getMontosModificados(String codigo) {
        List<Montos> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("MontosData.get_MontModif", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Montos> getMontoExpTecn(String codigo) {
        List<Montos> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("MontosData.get_Monto1", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Montos> getMontoInfraestructura(String codigo) {
        List<Montos> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("MontosData.get_Monto2", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Montos> getMontoEquipMobili(String codigo) {
        List<Montos> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("MontosData.get_Monto3", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Montos> getMontoSupervision(String codigo) {
       List<Montos> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("MontosData.get_Monto4", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Montos> getMontoCapacitacion(String codigo) {
        List<Montos> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("MontosData.get_Monto5", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Montos> getMontoOtros(String codigo) {
        List<Montos> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("MontosData.get_Monto6", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
}
