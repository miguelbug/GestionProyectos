/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.bean;

import java.text.DecimalFormat;

import gp.dao.BusqPreInversionDAO;
import gp.dao.ListasGeneralesDAO;
import gp.dao.RegistroInversionDAO;
import gp.daoImpl.BusqPreInversionDaoImpl;
import gp.daoImpl.ListasGeneralesDaoImpl;
import gp.daoImpl.RegistroInversionDaoImpl;
import gp.exporter.RealizarReporte;
import gp.model.BusqPreInversion;
import gp.model.ComponentesMostrar;
import gp.model.MostrarDesdeDependencias;
import gp.model.MostrarFechaInicFin;
import gp.model.Usuario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author OGPL
 */
@ManagedBean
@ViewScoped
public class ReportePreInversion {

    private String codigo;
    private Date fechaIni, fechaFin;
    private String nombreDepe;
    private List<ComponentesMostrar> busqPreInv2;
    private List<BusqPreInversion> busqPreInv1;
    private List<MostrarFechaInicFin> busqPreInv3;
    private List<String> listaDepe;
    private List<MostrarDesdeDependencias> listaDesdeDepe;
    private final RegistroInversionDAO rid;
    private final ListasGeneralesDAO listgenDao;
    private final BusqPreInversionDAO buspreInvDao;
    private String codigo2;
    private String nombre;
    private String nivEstud;
    private Double montoViab;
    private boolean mostrar;
    private boolean mostrar2;
    private boolean mostrar3;
    private boolean mostrar4;
    private Date fechainic;
    private Date fechafin;
    private String fechaViab;
    private BigDecimal suma;
    private BigDecimal suma2;
    private final FacesContext faceContext;
    private final Usuario usu;
    private RealizarReporte realizarRep;
    private String tipo;

    public ReportePreInversion() {
        faceContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(true);
        usu = (Usuario) session.getAttribute("sesionUsuario");
        listaDesdeDepe = new ArrayList<MostrarDesdeDependencias>();
        busqPreInv2 = new ArrayList<ComponentesMostrar>();
        listaDepe = new ArrayList<String>();
        busqPreInv3 = new ArrayList<MostrarFechaInicFin>();
        busqPreInv1 = new ArrayList<BusqPreInversion>();
        listgenDao = new ListasGeneralesDaoImpl();
        buspreInvDao = new BusqPreInversionDaoImpl();
        rid = new RegistroInversionDaoImpl();
        mostrar = false;
        mostrar2 = false;
        mostrar3 = false;
        mostrar4 = true;

    }

    public void llenarDepes() {
        if (!tipo.equals(" ") && !tipo.equals("3") && !tipo.equals("4") && !tipo.equals("5")) {
            mostrar4 = true;
            listaDepe = listgenDao.getDependencias(tipo);
        } else {
            if (tipo.equals("3") || tipo.equals("4") || tipo.equals("5")) {
                mostrar4 = false;
            }
        }
    }

    public List<String> coincidencias(String query) {

        List<String> cadena = new ArrayList<String>();
        try {
            cadena = rid.getCoincidencias(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cadena;
    }

    public void Buscar() {
        busqPreInv1.clear();
        busqPreInv2.clear();
        try {
            busqPreInv1 = buspreInvDao.listaBusqPI(codigo);
            busqPreInv2 = buspreInvDao.listaBusqPI_4(codigo);
            llenarTextos();
            mostrar = true;
        } catch (Exception e) {
            mostrar = false;
            System.out.println(e.getMessage());
        }
    }

    public void Buscar2() {
        busqPreInv3.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            busqPreInv3 = buspreInvDao.fechaInic_fechaFin(sdf.format(fechaIni), sdf.format(this.fechaFin));
            mostrar2 = true;
        } catch (Exception e) {
            mostrar2 = false;
            System.out.println(e.getMessage());
        }
    }

    public void Buscar3() {
        listaDesdeDepe.clear();
        try {
            if (mostrar4 == true) {
                listaDesdeDepe = buspreInvDao.mostrarDesdeDependencias(this.nombreDepe);
                mostrar3 = true;
            } else {
                if (tipo.equals("3")) {
                    listaDesdeDepe = buspreInvDao.mostrarDesdeFacult();
                    mostrar3 = true;
                } else {
                    if (tipo.equals("4")) {
                        listaDesdeDepe = buspreInvDao.mostrarDesdeDepes();
                        mostrar3 = true;
                    } else {
                        if (tipo.equals("5")) {
                            listaDesdeDepe = buspreInvDao.mostrarDesdeDepesyFacus();
                            mostrar3 = true;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrar3 = false;
        }
    }

    public void llenarTextos() {
        int i = 0;
        while (i < busqPreInv1.size()) {
            codigo2 = busqPreInv1.get(i).getC1();
            nombre = busqPreInv1.get(i).getC2();
            nivEstud = busqPreInv1.get(i).getC10();
            this.fechaViab = busqPreInv1.get(i).getC5();
            montoViab = Double.parseDouble(busqPreInv1.get(i).getC4());
            i++;
        }
    }

    public String SumaPorFecha() {
        suma = new BigDecimal(0.0);
        int i = 0;
        while (i < busqPreInv3.size()) {
            suma = suma.add(BigDecimal.valueOf(busqPreInv3.get(i).getMontoViab()));
            i++;
        }
        return new DecimalFormat("###,###.###").format(suma);
    }

    public String SumarPorDependencia() {
        suma2 = new BigDecimal(0.0);
        int i = 0;
        while (i < listaDesdeDepe.size()) {
            suma2 = suma2.add(BigDecimal.valueOf(listaDesdeDepe.get(i).getMontoViab()));
            i++;
        }
        return new DecimalFormat("###,###.###").format(suma2);
    }

    public String getCodigo() {
        return codigo;
    }

    public void Imprimir() throws SQLException {
        try {
            this.realizarRep = new RealizarReporte();
            realizarRep.ReporteProyectosXCodigo(usu, codigo, "Reporte1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Imprimir2() throws SQLException {
        try {
            this.realizarRep = new RealizarReporte();
            realizarRep.reporteProyectosXFecha(usu, fechaIni, this.fechaFin, "Reporte2", String.valueOf(suma));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Imprimir3() throws SQLException {
        try {
            this.realizarRep = new RealizarReporte();
            if (mostrar4 == true) {
                realizarRep.reporteProyectosxNombre(usu, nombreDepe, "Reporte3", String.valueOf(suma2));
            } else {
                if (tipo.equals("3")) {
                    realizarRep.reporteProyectosSoloFacus(usu, "Reporte4", String.valueOf(suma2));
                } else {
                    if (tipo.equals("4")) {
                        realizarRep.reporteProyectosSoloDepes(usu, "Reporte5", String.valueOf(suma2));
                    } else {
                        if (tipo.equals("5")) {
                            realizarRep.reporteProyectosFacusDepes(usu, "Reporte6", String.valueOf(suma2));
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreDepe() {
        return nombreDepe;
    }

    public void setNombreDepe(String nombreDepe) {
        this.nombreDepe = nombreDepe;
    }

    public RegistroInversionDAO getRid() {
        return rid;
    }

    public ListasGeneralesDAO getListgenDao() {
        return listgenDao;
    }

    public String getCodigo2() {
        return codigo2;
    }

    public void setCodigo2(String codigo2) {
        this.codigo2 = codigo2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivEstud() {
        return nivEstud;
    }

    public void setNivEstud(String nivEstud) {
        this.nivEstud = nivEstud;
    }

    public Double getMontoViab() {
        return montoViab;
    }

    public void setMontoViab(Double montoViab) {
        this.montoViab = montoViab;
    }

    public List<ComponentesMostrar> getBusqPreInv2() {
        return busqPreInv2;
    }

    public void setBusqPreInv2(List<ComponentesMostrar> busqPreInv2) {
        this.busqPreInv2 = busqPreInv2;
    }

    public List<BusqPreInversion> getBusqPreInv1() {
        return busqPreInv1;
    }

    public void setBusqPreInv1(List<BusqPreInversion> busqPreInv1) {
        this.busqPreInv1 = busqPreInv1;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public Date getFechainic() {
        return fechainic;
    }

    public void setFechainic(Date fechainic) {
        this.fechainic = fechainic;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public List<MostrarFechaInicFin> getBusqPreInv3() {
        return busqPreInv3;
    }

    public void setBusqPreInv3(List<MostrarFechaInicFin> busqPreInv3) {
        this.busqPreInv3 = busqPreInv3;
    }

    public boolean isMostrar2() {
        return mostrar2;
    }

    public void setMostrar2(boolean mostrar2) {
        this.mostrar2 = mostrar2;
    }

    public List<String> getListaDepe() {
        return listaDepe;
    }

    public void setListaDepe(List<String> listaDepe) {
        this.listaDepe = listaDepe;
    }

    public List<MostrarDesdeDependencias> getListaDesdeDepe() {
        return listaDesdeDepe;
    }

    public void setListaDesdeDepe(List<MostrarDesdeDependencias> listaDesdeDepe) {
        this.listaDesdeDepe = listaDesdeDepe;
    }

    public boolean isMostrar3() {
        return mostrar3;
    }

    public void setMostrar3(boolean mostrar3) {
        this.mostrar3 = mostrar3;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

    public BigDecimal getSuma2() {
        return suma2;
    }

    public void setSuma2(BigDecimal suma2) {
        this.suma2 = suma2;
    }

    public String getFechaViab() {
        return fechaViab;
    }

    public void setFechaViab(String fechaViab) {
        this.fechaViab = fechaViab;
    }

    public BusqPreInversionDAO getBuspreInvDao() {
        return buspreInvDao;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public Usuario getUsu() {
        return usu;
    }

    public RealizarReporte getRealizarRep() {
        return realizarRep;
    }

    public void setRealizarRep(RealizarReporte realizarRep) {
        this.realizarRep = realizarRep;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isMostrar4() {
        return mostrar4;
    }

    public void setMostrar4(boolean mostrar4) {
        this.mostrar4 = mostrar4;
    }

}
