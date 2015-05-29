/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.daoImpl;

import gp.connectionFactory.MyBatisConnectionFactory;
import gp.dao.TbVehiculosDAO;
import gp.model.Expedientes;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author Rolando
 */
public class TbVehiculosDaoImpl implements TbVehiculosDAO {

    private SqlSessionFactory sqlSessionFactory;

    public TbVehiculosDaoImpl() {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    @Override
    public List<Expedientes> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();
        List<Expedientes> list=null;
        try {
            list = session.selectList("ExpedienteData.getAll");
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR EN EL IMPL");
        } 
        finally {
            session.close();
        }
        return list;
    }
/*
 /**
 * Returns the list of all Contact instances from the database.
 * @return the list of all Contact instances from the database.
     
 @SuppressWarnings("unchecked")
 public List<TbVehiculos> selectAll() {
 SqlSession session = sqlSessionFactory.openSession();
 try {
 List<TbVehiculos> list = session.selectList("TbVehiculos.getAll");
 return list;
 } finally {
 session.close();
 }
 }

 /**
 * Returns a Contact instance from the database.
 * @param id primary key value used for lookup.
 * @return A Contact instance with a primary key value equals to pk. null if there is no matching row.
     
 public TbVehiculos selectById(int id) {
 SqlSession session = sqlSessionFactory.openSession();
 try {
 TbVehiculos contact = (TbVehiculos) session.selectOne("TbVehiculos.getById", id);
 return contact;
 } finally {
 session.close();
 }
 }

 /**
 * Updates an instance of Contact in the database.
 * @param contact the instance to be updated.
     
 public void update(TbVehiculos contact) {
 SqlSession session = sqlSessionFactory.openSession();
 try {
 session.update("TbVehiculos.update", contact);
 session.commit();
 } finally {
 session.close();
 }
 }

 /**
 * Insert an instance of Contact into the database.
 * @param contact the instance to be persisted.
     
 public void insert(TbVehiculos contact) {
 SqlSession session = sqlSessionFactory.openSession();
 try {
 session.insert("TbVehiculos.insert", contact);
 session.commit();
 } finally {
 session.close();
 }
 }

 /**
 * Delete an instance of Contact from the database.
 * @param id primary key value of the instance to be deleted.
     
 public void delete(int id) {
 SqlSession session = sqlSessionFactory.openSession();
 try {
 session.delete("TbVehiculos.deleteById", id);
 session.commit();
 } finally {
 session.close();
 }
 }*/

}
