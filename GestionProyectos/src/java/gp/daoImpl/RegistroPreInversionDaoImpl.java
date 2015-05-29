/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.RegistroPreInversionDAO;
import gp.model.AspectosGenerales;
import gp.model.Componentes;
import gp.model.Expedientes;
import gp.model.Nivel_Estudio;
import gp.model.Opi_responsable;
import gp.model.Origen;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author OGPL
 */
public class RegistroPreInversionDaoImpl implements RegistroPreInversionDAO {

    private SqlSessionFactory sqlSessionFactory;

    public RegistroPreInversionDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public RegistroPreInversionDaoImpl() {
        this.sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    @Override
    public boolean validarProyecto(String codigo) {
        boolean esta = false;
        String var = "";
        SqlSession session = sqlSessionFactory.openSession();
        try {
            var = session.selectOne("RegistroPreInversion.validarProy", codigo);
            System.out.println("VALIDACION: " + var);
            if (var == null) {
                esta = false;
            } else {
                esta = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return esta;
    }

    @Override
    public String getMontoViab(String codigo) {
        String montoViab = "";
        SqlSession session = sqlSessionFactory.openSession();
        try {
            montoViab = session.selectOne("RegistroPreInversion.getMontoViab", codigo);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        System.out.println(montoViab);
        return montoViab;
    }

    @Override
    public void RegistrarAspecGeneral(AspectosGenerales contact) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroPreInversion.insert_ag", contact);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroPreInversion.insert_ag");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void RegistrarComponentes(Componentes contact) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("RegistroPreInversion.insert_comp", contact);
            session.commit();
        } catch (Exception e) {
            System.out.println("RegistroPreInversion.insert_comp");
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Origen> ObtenerLista_Origen(String tipo) {
        List<Origen> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("RegistroPreInversion.getOrigenes", tipo);
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
    public List<Opi_responsable> obtenerLista_opi() {
        List<Opi_responsable> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("RegistroPreInversion.getOpi");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Nivel_Estudio> obtenerList_nivEst() {
        List<Nivel_Estudio> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("RegistroPreInversion.getNivEst");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public Integer getId_Origen(String nombre) {
        Integer idorigen = 0;
        System.out.println(nombre);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idorigen = (session.selectOne("RegistroPreInversion.getIdOrigen", nombre));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        System.out.println(idorigen);
        return idorigen;
    }

    @Override
    public Integer getId_Opi(String nombre) {
        Integer idopi = 0;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idopi = session.selectOne("RegistroPreInversion.getIdOpi", nombre);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        System.out.println(idopi);
        return idopi;
    }

    @Override
    public Integer getId_Nivel(String nombre) {
        Integer idnivel = 0;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idnivel = session.selectOne("RegistroPreInversion.getIdNivel", nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        System.out.println(idnivel);
        return idnivel;
    }

    @Override
    public Integer getId_Usuario(String nombre) {
        Integer idusuario = 0;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            idusuario = session.selectOne("RegistroPreInversion.getIdUsuario", nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN EL IMPL");
        } finally {
            session.close();
        }
        System.out.println(idusuario);
        return idusuario;
    }

}
