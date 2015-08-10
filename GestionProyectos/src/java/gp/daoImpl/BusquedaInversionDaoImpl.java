/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.BusquedaInversionDAO;
import gp.model.ActualizarEjecucion;
import gp.model.DetalleExpTecnico;
import gp.model.DocumentosNuevos;
import gp.model.ExpedienteTecnico;
import gp.model.MostrarEjecucion;
import gp.model.MostrarExpedientesTecnicos;
import java.text.SimpleDateFormat;
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
public class BusquedaInversionDaoImpl implements BusquedaInversionDAO {

    private SqlSessionFactory sqlSessionFactory;

    public BusquedaInversionDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public BusquedaInversionDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<MostrarExpedientesTecnicos> getListaExpedientes(String codigo, String nombreDocu) {
        SqlSession session = sqlSessionFactory.openSession();
        Map m = new HashMap();
        m.put("codigo", codigo);
        m.put("nombre", nombreDocu);
        List<MostrarExpedientesTecnicos> list = null;
        try {
            list = session.selectList("BusquedaInversion.getListaExpTecn", m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
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
    public void actualizarMontoModif(ExpedienteTecnico e) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int a = session.update("BusquedaInversion.actualizarMontoModif", e);
            session.commit();
            System.out.println("se han actualizado: " + a);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void ActualizarExpedienteTecnico(ExpedienteTecnico e) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int a = session.update("BusquedaInversion.actualizarExpTecn", e);
            session.commit();
            System.out.println("se han actualizado: " + a);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            session.close();
        }

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
        Map m = new HashMap();
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

    public Date getDate(String date) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d = sdf.parse(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return d;
    }

    @Override
    public Integer getIdProyExpt(String docu) {
        Integer inte = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inte = session.selectOne("BusquedaInversion.getIdExpTecn", docu);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return inte;
    }

    @Override
    public void ActualizarExpTecnico(String docu, String fecha, Double monto, String resoul) {
        ExpedienteTecnico ex = new ExpedienteTecnico();
        ex.setDocumento(docu);
        ex.setFecha(getDate(fecha));
        ex.setMonto(monto);
        ex.setResolucion(resoul);
        ex.setIdpExptecn(getIdProyExpt(docu));
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("BusquedaInversion.insert_gnc", ex);
            session.commit();
        } catch (Exception e) {
            System.out.println("BusquedaInversion.insert_gnc");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
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

    @Override
    public void validarEjecucion(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizaSoloContrato(String codigo, Integer d) {
        SqlSession session = sqlSessionFactory.openSession();
        Map m = new HashMap();
        m.put("codigo", codigo);
        m.put("idnuevodocu", d);
        try {
            session.insert("BusquedaInversion.actualizarContrato1", m);
            session.commit();
        } catch (Exception e) {
            System.out.println("BusquedaInversion.actualizarContrato1");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void ActualizarContrato(DocumentosNuevos d) {
        actualizaSoloContrato(d.getDocumento(), d.getIdnuevodocu());
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("BusquedaInversion.actualizarContrato2", d);
            session.commit();
        } catch (Exception e) {
            System.out.println("BusquedaInversion.actualizarContrato2");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void ActualizarDocumentos(DocumentosNuevos d) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("BusquedaInversion.actualizarDocumentos", d);
            session.commit();
        } catch (Exception e) {
            System.out.println("BusquedaInversion.actualizarDocumentos");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<String> getEjecucionMeses(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<String> list = null;
        try {
            list = session.selectList("BusquedaInversion.getMesEjecu", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<String> getEjecucionAnios(String codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<String> list = null;
        try {
            list = session.selectList("BusquedaInversion.getAnioEjecu", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<MostrarEjecucion> getEjecucionMontos(String codigo, String mes, String anio) {
        SqlSession session = sqlSessionFactory.openSession();
        Map map= new HashMap();
        map.put("codigo", codigo);
        map.put("mes",mes);
        map.put("anio",anio);
        
        List<MostrarEjecucion> list = null;
        try {
            list = session.selectList("BusquedaInversion.getEjecucion", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void actualizarMontoEjecucion(ActualizarEjecucion codigo) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("BusquedaInversion.actualizarEjecucion", codigo);
            session.commit();
        } catch (Exception e) {
            System.out.println("BusquedaInversion.actualizarEjecucion");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
