/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.MontosDAO;
import gp.dao.RegistroInversionDAO;
import gp.model.Componentes;
import gp.model.Ejecucion;
import gp.model.EjecucionMostrado;
import gp.model.Historial;
import gp.model.NuevosDocumentos;
import gp.model.Registro_Inversion;
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
    private MontosDAO montd;

    public RegistroInversionDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        montd = new MontosDaoImpl();
    }

    public RegistroInversionDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
        montd = new MontosDaoImpl();
    }

    @Override
    public void agregarMontoET(Historial h) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.agregar_monto_et", h);
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
    public List<EjecucionMostrado> getMontosEjecutados(String codigo, String id) {
        List<EjecucionMostrado> list = null;
        System.out.println("Codigo - ID " + codigo + " " + id);
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
    public void actualizarEstadoExpTecn(String etapa, String proyecto) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("etapa", etapa);
        map.put("proyecto", proyecto);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("RegistroInversion.update_ExpTecn", map);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void ActualizarMontosEjecutados(List<Ejecucion> ejecu, String idproyexpt) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            for (int i = 0; i < ejecu.size(); i++) {
                System.out.println(ejecu.get(i).getMonto() + "  " + ejecu.get(i).getMonto2());
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
    public void guardarNuevoExpTecn(NuevosDocumentos dato, Historial h, String numero, String id, String etapa) {
        /*this.guardarExpTecn(dato);
         h.setIdproy(getIdProyExpt2(Integer.parseInt(numero), Integer.parseInt(id), Integer.parseInt(etapa)));
         this.guardarHistorial(h);
         Componentes c = new Componentes();
         c.setCodigoProy(Integer.parseInt(id));
         c.setMontoExpTec(0.0);
         c.setMontoInfra(h.getMonto());
         c.setMontoEquipMov(0.0);
         c.setMontoSuperv(0.0);
         c.setMontoCapac(0.0);
         c.setMontoOtros(0.0);
         c.setFecharegistro(h.getFecha());
         c.setMontoModif(0.0);
         c.setTipoRegistro("1");
         c.setNumMonto(getNumeroMonto(id));
         //c.setIdExpTecn(h.getIdproy());
         nuevaInfraestructura(c);*/
    }

    @Override
    public Integer getNumeroMonto(String codigo) {
        Integer numero = montd.getNumMonto(codigo);
        if (numero == null) {
            numero = 0;
        }
        int numero_parse = (numero) + 1;
        return numero_parse;
    }

    @Override
    public void nuevaInfraestructura(Componentes c) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.ingresar_infraestructura", c);
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
    public void guardarExpTecn(Componentes dato) {
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

    public void guardarHistorial(Historial h) {
        System.out.println(h.getIdproy() + " " + h.getResolucion() + " " + h.getFecha() + " " + h.getMonto());
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.guardar_historial", h);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroInversion.guardar_historial");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void guardarHistorialContrato(Historial h) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.guardar_historialContrato", h);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroInversion.guardar_historialContrato");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Integer getIdProyExpt2(Integer numero, Integer idproy, Integer etapa) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("numero", String.valueOf(numero));
        map.put("idproy", String.valueOf(idproy));
        map.put("etapa", String.valueOf(etapa));
        Integer idproyexpt = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idproyexpt = session.selectOne("RegistroInversion.getIdProyectoExpt", map);
        } catch (Exception e) {
            System.out.println("ERROR EN EL IMPL getIdProyectoExpt");
            System.out.println(e.getMessage());

            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(idproyexpt);
        return idproyexpt;
    }

    @Override
    public void guardarNuevoDocumento(NuevosDocumentos dato, Historial h, Integer numerodocu, String exptecn, Integer tipodocu) {
        //guardarDocumentoAdicional(dato);
        h.setIdnuevodoc(getIdNuevoDocu(numerodocu, exptecn, tipodocu));
        guardarHistorialAdicional(h);

    }

    public void guardarHistorialAdicional(Historial h) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroInversion.guardar_historialAdicDeduc", h);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroInversion.guardar_historialAdicDeduc");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Integer getIdNuevoDocu(Integer numerodocu, String exptecn, Integer tipodocu) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("numerodocu", String.valueOf(numerodocu));
        map.put("exptecn", exptecn);
        map.put("tipodocu", String.valueOf(tipodocu));
        SqlSession session = sqlSessionFactory.openSession();
        Integer idproyextp = null;
        try {
            idproyextp = session.selectOne("RegistroInversion.getIdNuevosDocus2", map);
        } catch (Exception e) {
            System.out.println("ERROR EN EL IMPL getIdNuevosDocus2");
            System.out.println(e.getMessage());

            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(idproyextp);
        return idproyextp;
    }

    @Override
    public void guardarDocumento(NuevosDocumentos dato) {
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
    public void guardarContrato(NuevosDocumentos dato) {
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

    @Override
    public void guardarNuevoDocumentoContrato(NuevosDocumentos dato, Historial h, String nombre, String idproy, String c) {
        guardarContrato(dato);
        h.setIdproy(getIdNuevosDocus(nombre, idproy, c));
        guardarHistorialContrato(h);
    }

    @Override
    public Componentes getMontosPorEtapa(String codigo, String etapa) {
        Componentes busq = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("codigo", codigo);
        map.put("etapa", etapa);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            busq = session.selectOne("RegistroInversion.getMontosPorEtapa", map);
        } catch (Exception e) {
            System.out.println("ERROR EN EL IMPL getMontosPorEtapa");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return busq;
    }

    public Integer getIdNuevosDocus(String nombre, String idproy, String c) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("idproy", String.valueOf(idproy));
        map.put("numerocontrato", String.valueOf(nombre));
        map.put("idexpt", c);

        Integer idproyexpt = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idproyexpt = session.selectOne("RegistroInversion.getIdNuevosDocus", map);
        } catch (Exception e) {
            System.out.println("ERROR EN EL IMPL getIdNuevosDocus");
            System.out.println(e.getMessage());

            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(idproyexpt);
        return idproyexpt;
    }

    @Override
    public Componentes getExpediente0(String proyecto) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("proyecto", proyecto);
        Componentes cantidad = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            cantidad = session.selectOne("RegistroInversion.getMontosExp0", map);
        } catch (Exception e) {
            System.out.println("ERROR EN EL IMPL getIdNuevosDocus");
            System.out.println(e.getMessage());

            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(cantidad);
        return cantidad;
    }

    @Override
    public Integer getCantidadExpTecn(String proyecto) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("proyecto", proyecto);
        System.out.println("Proyecto: "+proyecto);
        Integer cantidad = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            cantidad = session.selectOne("RegistroInversion.cantiExpTecn", map);
        } catch (Exception e) {
            System.out.println("ERROR EN EL IMPL getIdNuevosDocus");
            System.out.println(e.getMessage());

            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(cantidad);
        return cantidad;
    }

    @Override
    public Integer getMaxNumMontoHistorial(String codproy, String expTecn) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("codProy", codproy);
        map.put("expTecn", expTecn);
        Integer idhistorial = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idhistorial = session.selectOne("RegistroInversion.getIdHistorial", map);
        } catch (Exception e) {
            System.out.println("ERROR EN EL IMPL getIdNuevosDocus");
            System.out.println(e.getMessage());

            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(idhistorial);
        return idhistorial;
    }
}
