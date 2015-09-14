/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.ReporteInversionDAO;
import gp.model.ExpedienteTecnico;
import gp.model.MostrarDesdeDependencias;
import gp.model.MostrarTablaEjecucion;
import gp.model.NuevosDocumentos;
import gp.model.RIdatosEjecucion;
import gp.model.RIdatosProyecto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class ReporteInversionDaoImpl implements ReporteInversionDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public ReporteInversionDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public ReporteInversionDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<String> aniosDedAdic(String codigo) {
        List<String> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getaniosdedadic", codigo);
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
    public List<RIdatosProyecto> getDatosProyEjecu(String codigo) {
        List<RIdatosProyecto> list = null;
        System.out.println("El codigo es: "+codigo);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getDatosProyEjecu", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        System.out.println("TAMAÑO DE LA LISTA: "+list.size());
        return list;
    }

    @Override
    public List<MostrarTablaEjecucion> getEjecu1(String codigo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        List<MostrarTablaEjecucion> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getEjecu1", map);
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
    public List<MostrarTablaEjecucion> getEjecu2(String codigo) {
        List<MostrarTablaEjecucion> list = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getEjecu2", map);
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
    public List<NuevosDocumentos> getDeductivos(String codigo, String etapa) {
        List<NuevosDocumentos> list = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("etapa", etapa);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getAdicionales", map);
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
    public List<NuevosDocumentos> getAdicionales(String codigo, String etapa) {
        List<NuevosDocumentos> list = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("etapa", etapa);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getDeductivos", map);
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
    public List<ExpedienteTecnico> getExpediente(String codigo) {
        List<ExpedienteTecnico> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getExpedientes", codigo);
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
    public List<MostrarDesdeDependencias> getListaDesdeDepes(Integer idOrigen) {
        List<MostrarDesdeDependencias> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("ReporteInversion.getDatosDesdeDepes", idOrigen);
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
