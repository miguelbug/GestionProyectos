/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.ActualizarEjecucion;
import gp.model.DetalleExpTecnico;
import gp.model.DocumentosNuevos;
import gp.model.ExpedienteTecnico;
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
    public String getNombreProy(String codigo);
    public void ActualizarExpTecnico(String docu,String fecha,Double monto,String resoul);
    public Integer getIdProyExpt(String docu);
    public void ActualizarExpedienteTecnico(ExpedienteTecnico e);
    public void ActualizarContrato(DocumentosNuevos d);
    public void ActualizarDocumentos(DocumentosNuevos d);
    public void actualizaSoloContrato(String codigo, Integer d);
    public List<String> getEjecucionMeses(String codigo);
    public List<String> getEjecucionAnios(String codigo);
    public List<MostrarEjecucion> getEjecucionMontos(String codigo, String mes, String anio);
    public void actualizarMontoEjecucion(ActualizarEjecucion codigo);
    public void validarEjecucion(String codigo);
    public List<MostrarExpedientesTecnicos> getListaExpedientes(String codigo);
    public void actualizarMontoModif(ExpedienteTecnico e);
}
