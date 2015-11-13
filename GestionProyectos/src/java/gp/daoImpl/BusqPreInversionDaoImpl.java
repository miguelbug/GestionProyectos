/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.BusqPreInversionDAO;
import gp.model.AspectosGenerales;
import gp.model.BusqPreInversion;
import gp.model.Componentes;
import gp.model.ComponentesMostrar;
import gp.model.GuardarNuevComp;
import gp.model.MontosViables;
import gp.model.MostrarDesdeDependencias;
import gp.model.MostrarFechaInicFin;
import gp.model.busquedaPreInversionMontos;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class BusqPreInversionDaoImpl implements BusqPreInversionDAO {

    private SqlSessionFactory sqlSessionFactory;

    public BusqPreInversionDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public BusqPreInversionDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Integer> etapas(String proy) {
        List<Integer> etapas = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            etapas = session.selectList("BusqPreInversion.listaEtapas", proy);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return etapas;
    }

    @Override
    public String validarProyecto(String codigo) {
        String nombreexp = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            nombreexp = session.selectOne("BusqPreInversion.validarProy", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return nombreexp;
    }

    @Override
    public void actualizarMontoViab(Integer idProy, Double nuevMontoViab) {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idproyecto", idProy);
        map.put("montoViab", nuevMontoViab);
        try {
            session.update("BusqPreInversion.update_montoViab", map);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void actualizarComponentes(Componentes c) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("BusqPreInversion.update_Co", c);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<MontosViables> getHistMontosViab(Integer idproy) {
        List<MontosViables> list = null;
        Map m = new HashMap();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("BusqPreInversion.getMontosViables", idproy);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<busquedaPreInversionMontos> getComponentesDeMonto(String montoFecha, String codigoproy) {
        List<busquedaPreInversionMontos> list = null;
        System.out.println(montoFecha + " " + codigoproy);
        Map m = new HashMap();
        m.put("montoFecha", montoFecha);
        m.put("codigo", codigoproy);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("BusqPreInversion.getComponenteDeMonto", m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<busquedaPreInversionMontos> getMontoHistorial(String codigo) {
        List<busquedaPreInversionMontos> list = null;
        System.out.println(codigo);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("BusqPreInversion.getMontosHistorial", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void actualizarAspectosGenerales(AspectosGenerales ag) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("BusqPreInversion.update_Ag", ag);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void guardarNuevosComponentes(GuardarNuevComp gnc) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("BusqPreInversion.insert_gnc", gnc);
            session.commit();
        } catch (Exception e) {
            System.out.println("BusqPreInversion.insert_gnc");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<BusqPreInversion> listaBusqPI(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<BusqPreInversion> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI", codigo);
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
    public List<BusqPreInversion> listaBusqPI_2(String codigo, String etapa) {
        SqlSession session = sqlSessionFactory.openSession();
        List<BusqPreInversion> list = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("etapa", etapa);
        try {
            list = session.selectList("BusqPreInversion.getBPI_2", map);
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
    public List<BusqPreInversion> listaBusqPI_3(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<BusqPreInversion> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI_3", codigo);
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
    public List<ComponentesMostrar> listaBusqPI_4(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<ComponentesMostrar> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI_4", codigo);
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
    public List<MostrarFechaInicFin> fechaInic_fechaFin(String fecha1, String fecha2) {
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println(fecha1 + " " + fecha2);
        Map<String, String> map = new HashMap<String, String>();
        map.put("fecha1", fecha1);
        map.put("fecha2", fecha2);
        List<MostrarFechaInicFin> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI_5", map);
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
    public List<MostrarDesdeDependencias> mostrarDesdeDependencias(String nombre) {
        SqlSession session = sqlSessionFactory.openSession();
        List<MostrarDesdeDependencias> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI_6", nombre);
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
    public List<MostrarDesdeDependencias> mostrarDesdeFacult() {
        SqlSession session = sqlSessionFactory.openSession();
        List<MostrarDesdeDependencias> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI_7");
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
    public List<MostrarDesdeDependencias> mostrarDesdeDepes() {
        SqlSession session = sqlSessionFactory.openSession();
        List<MostrarDesdeDependencias> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI_8");
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
    public List<MostrarDesdeDependencias> mostrarDesdeDepesyFacus() {
        SqlSession session = sqlSessionFactory.openSession();
        List<MostrarDesdeDependencias> list = null;
        try {
            list = session.selectList("BusqPreInversion.getBPI_9");
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
