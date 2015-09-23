/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.Ejecucion;
import gp.model.ExpedienteTecnico;
import gp.model.GraficasMontosAnios;
import gp.model.MostrarDesdeDependencias;
import gp.model.MostrarTablaEjecucion;
import gp.model.NuevosDocumentos;
import gp.model.RIdatosProyecto;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface ReporteInversionDAO {
    public List<RIdatosProyecto> getDatosProyEjecu(String codigo);
    public List<MostrarTablaEjecucion> getEjecu1(String codigo);
    public List<MostrarTablaEjecucion> getEjecu2(String codigo);
    public List<String> aniosDedAdic(String codigo);
    public List<NuevosDocumentos> getDeductivos(String codigo, String etapa);
    public List<NuevosDocumentos> getAdicionales(String codigo, String etapa);
    public List<ExpedienteTecnico> getExpediente(String codigo);
    public List<MostrarDesdeDependencias> getListaDesdeDepes(Integer nombreOrigen);
    public List<GraficasMontosAnios> getAniosMontos(String codigo);
    public List<Ejecucion> getEjecucionXanios(String codigo, String anio);
}
