/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gp.model;

/**
 *
 * @author Rolando
 */
public class TbVehiculos {

    private String id;
    private String numeroPlaca;
    private String fechaRegistro;
    private String idModelo;

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(String idModelo) {
        this.idModelo = idModelo;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }
}
