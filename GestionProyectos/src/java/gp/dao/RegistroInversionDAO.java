/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.Componentes;
import gp.model.Ejecucion;
import gp.model.EjecucionMostrado;
import gp.model.Historial;
import gp.model.NuevosDocumentos;
import gp.model.Registro_Inversion;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface RegistroInversionDAO {
    public List<Registro_Inversion> getExpedientes(String codigo, String tipo);
    public String getNombreExpediente(String codigo, String tipo);
    public String getNombreDocumentos(String codigo, String tipo, String exp);
    public void guardarNuevoExpTecn(NuevosDocumentos dato, Historial h, String numero, String id, String etapa);
    public void guardarNuevoDocumento(NuevosDocumentos dato,Historial h,Integer numerodocu ,String exptecn, Integer tipodocu);
    public void guardarNuevoDocumentoContrato(NuevosDocumentos dato, Historial h,String codigocont,String codigproy, String c);
    public String getIdExpedienteTecn(String proy, String tipo,String numero);
    public void guardarEjecucion(List<Ejecucion> e);
    public String validarProy(String codigo);
    public List<String> getCoincidencias(String codigo);
    public Integer getIDRoRdR(String nombre);
    public List<String> getListaEtapas(String idproy);
    public Integer getIdProyExpt(String codigo, String etapa);
    public Integer validarProyecto(String codigo, String etapa);
    public List<EjecucionMostrado> getMontosEjecutados(String codigo, String id);
    public void ActualizarMontosEjecutados(List<Ejecucion> ejecu , String idproy);
    public Integer getIdProyExpt2(Integer numero, Integer idproy, Integer etapa);
    public void nuevaInfraestructura(Componentes c);
    public void agregarMontoET(Historial h);
    public Integer getNumeroMonto(String codigo);
    public Componentes getMontosPorEtapa(String codigo, String etapa);
    public Integer getMaxNumMontoHistorial(String codproy, String codigoExpTecn);
    public void guardarExpTecn(Componentes dato);
    public void guardarContrato(NuevosDocumentos dato);
    public void guardarDocumento(NuevosDocumentos dato);
    public void actualizarEstadoExpTecn(String etapa, String proyecto);
    public Integer getCantidadExpTecn(String proyecto);
    public Componentes getExpediente0(String proyecto);
}
