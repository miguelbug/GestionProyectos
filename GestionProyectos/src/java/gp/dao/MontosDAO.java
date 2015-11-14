/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.Montos;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface MontosDAO {
    public List<Montos> getMontosModificados(String codigo);
    public List<Montos> getMontoExpTecn(String codigo);
    public List<Montos> getMontoInfraestructura(String codigo);
    public List<Montos> getMontoEquipMobili(String codigo);
    public List<Montos> getMontoSupervision(String codigo);
    public List<Montos> getMontoCapacitacion(String codigo);
    public List<Montos> getMontoOtros(String codigo);
    public Integer getNumMonto(String codigo);
    public Integer getNumMonto0(Integer proy, String etapa);
    public Integer getNumMontoViab(Integer codigo);
}
