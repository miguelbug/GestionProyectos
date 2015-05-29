/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.Expedientes;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface TbVehiculosDAO {
    /*public List<TbVehiculos> selectAll();
    public TbVehiculos selectById(int id);
    public void update(TbVehiculos contact);
    public void insert(TbVehiculos contact);
    public void delete(int id);*/
    public List<Expedientes> selectAll();
}
