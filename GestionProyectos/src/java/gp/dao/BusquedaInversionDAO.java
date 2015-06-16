/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.DetalleExpTecnico;
import gp.model.MostrarEjecucion;
import gp.model.MostrarExpedientesTecnicos;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface BusquedaInversionDAO {
    public List<MostrarExpedientesTecnicos> getListaExpedientesTecnicos(String codigo);
    public List<MostrarEjecucion> getEjecucion(String codigo);
    public List<DetalleExpTecnico> getDetalleExpedTecnico(String codigo, String exptecnico);
}
