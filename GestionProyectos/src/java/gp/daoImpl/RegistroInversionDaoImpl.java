/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.RegistroInversionDAO;
import gp.model.Ejecucion;
import gp.model.EjecucionMostrado;
import gp.model.NuevosDocumentos;
import gp.model.Registro_Inversion;
import gp.model.busquedaPreInversionMontos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class RegistroInversionDaoImpl implements RegistroInversionDAO {

    private SqlSessionFactory sqlSessionFactory;

    public RegistroInversionDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public RegistroInversionDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    @Override
    public List<EjecucionMostrado> getMontosEjecutados(String codigo, String id) {
        List<EjecucionMostrado> list = null;
        System.out.println(codigo+" "+id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("id", id);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("RegistroInversion.getMontosEjecutados", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL getMontosEjecutados");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Integer validarProyecto(String codigo, String etapa) {
        Integer proyexpt = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("etapa", etapa);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            proyexpt = session.selectOne("RegistroInversion.validarProy2", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL validarProy");
        } finally {
            session.close();
        }
        return proyexpt;
    }

    @Override
    public Integer getIdProyExpt(String codigo, String etapa) {
        Integer proyexpt = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("etapa", etapa);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            proyexpt = session.selectOne("RegistroInversion.getIdProyExpt", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return proyexpt;
    }

    @Override
    public List<String> getListaEtapas(String idproy) {
        List<String> list = null;
        System.out.println(idproy);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("RegistroInversion.getListaEtapas", idproy);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Integer getIDRoRdR(String nombre) {
        Integer idrordr = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idrordr = session.selectOne("RegistroInversion.getIdrordr", nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return idrordr;
    }

    @Override
    public List<String> getCoincidencias(String codigo) {
        List<String> list = null;
        System.out.println(codigo);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("RegistroInversion.getListaCoincidencia", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public String validarProy(String codigo) {
        String nombreexp = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            nombreexp = session.selectOne("RegistroInversion.validarProy", codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return nombreexp;
    }

    @Override
    public void ActualizarMontosEjecutados(List<Ejecucion> ejecu, String idproyexpt) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            for (int i = 0; i < ejecu.size(); i++) {
                System.out.println(ejecu.get(i).getMonto()+"  "+ejecu.get(i).getMonto2());
                session.insert("RegistroInversion.updateEjec", ejecu.get(i));
                session.commit();
            }
        } catch (Exception e) {
            System.out.println("RegistroInversion.insert_nuevaEjec");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void guardarEjecucion(List<Ejecucion> ej) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            for (int i = 0; i < ej.size(); i++) {
                session.insert("RegistroInversion.insert_nuevaEjec", ej.get(i));
                session.commit();
            }
        } catch (Exception e) {
            System.out.println("RegistroInversion.insert_nuevaEjec");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public String getIdExpedienteTecn(String proy, String tipo, String numero) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", proy);
        map.put("tipo", tipo);
        map.put("numero", numero);

        String nombreexp = "";
        SqlSession session = sqlSessionFactory.openSession();
        try {
            nombreexp = session.selectOne("RegistroInversion.getIdExpTecn", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return nombreexp;
    }

    @Override
    public List<Registro_Inversion> getExpedientes(String codigo, String tipo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("tipo", tipo);
        List<Registro_Inversion> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("RegistroInversion.getListaExp", map);
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
    public String getNombreExpediente(String codigo, String tipo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("tipo", tipo);

        String nombreexp = "";
        SqlSession session = sqlSessionFactory.openSession();
        try {
            nombreexp = session.selectOne("RegistroInversion.getNumeroExp", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        return nombreexp;
    }

    @Override
    public String getNombreDocumentos(String codigo, String tipo, String exp) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("tipo", tipo);
        map.put("numeroExp", exp);

        String nombreexp = "";
        SqlSession session = sqlSessionFactory.openSession();
        try {
            nombreexp = session.selectOne("RegistroInversion.getNumeroDocumento", map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL getnumonto");
        } finally {
            session.close();
        }
        System.out.println(nombreexp);
        return nombreexp;
    }

    @Override
    public void guardarNuevoExpTecn(NuevosDocumentos dato) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.insert_nuevoExp", dato);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroInversion.insert_nuevoExp");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void guardarNuevoDocumento(NuevosDocumentos dato) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.insert_nuevoDoc", dato);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroInversion.insert_nuevoDoc");
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void guardarNuevoDocumentoContrato(NuevosDocumentos dato) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.insert_nuevoDocContrato", dato);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroInversion.insert_nuevoDocContrato");
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

}
