/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.AspectosGenerales;
import gp.model.BusqPreInversion;
import gp.model.Componentes;
import gp.model.ComponentesMostrar;
import gp.model.GuardarNuevComp;
import gp.model.MostrarDesdeDependencias;
import gp.model.MostrarFechaInicFin;
import gp.model.busquedaPreInversionMontos;
import java.util.Date;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface BusqPreInversionDAO {
    public List<BusqPreInversion> listaBusqPI(String codigo);
    public List<BusqPreInversion> listaBusqPI_2(String codigo, String etapa);
    public List<BusqPreInversion> listaBusqPI_3(String codigo);
    public List<ComponentesMostrar> listaBusqPI_4(String codigo);
    public List<MostrarFechaInicFin> fechaInic_fechaFin(String fecha1, String fecha2);
    public List<MostrarDesdeDependencias> mostrarDesdeDependencias(String nombre);
    public List<MostrarDesdeDependencias> mostrarDesdeFacult();
    public List<MostrarDesdeDependencias> mostrarDesdeDepes();
    public List<MostrarDesdeDependencias> mostrarDesdeDepesyFacus();
    public void guardarNuevosComponentes(GuardarNuevComp gnc);
    public void actualizarAspectosGenerales(AspectosGenerales ag);
    public void actualizarComponentes(Componentes c);
    public List<busquedaPreInversionMontos> getMontoHistorial(String codigo);
    public List<busquedaPreInversionMontos> getComponentesDeMonto(String monoFecha, String codigoproy);
    public String validarProyecto(String codigo);
    public List<Integer> etapas(String proy);
}
