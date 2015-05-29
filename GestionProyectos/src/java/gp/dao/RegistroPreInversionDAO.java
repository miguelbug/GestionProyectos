/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gp.dao;

import gp.model.AspectosGenerales;
import gp.model.Componentes;
import gp.model.Nivel_Estudio;
import gp.model.Opi_responsable;
import gp.model.Origen;
import java.util.List;

/**
 *
 * @author OGPL
 */
public interface RegistroPreInversionDAO {
    public void RegistrarAspecGeneral(AspectosGenerales ag);
    public void RegistrarComponentes(Componentes c);
    public List<Origen> ObtenerLista_Origen(String tipo);
    public List<Opi_responsable> obtenerLista_opi();
    public List<Nivel_Estudio> obtenerList_nivEst();
    public Integer getId_Origen(String nombre);
    public Integer getId_Opi(String nombre);
    public Integer getId_Nivel(String nombre);
    public Integer getId_Usuario(String nombre);
    public String getMontoViab(String codigo);
    public boolean validarProyecto(String codigo);
}
