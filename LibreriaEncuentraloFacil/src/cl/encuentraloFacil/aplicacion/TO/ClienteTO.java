/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.TO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mario
 */
public class ClienteTO implements Serializable {

    private Integer idCliente;
    private Integer rut;
    private char dv;
    private String nombre;
    private String primerApellido;
    private Date fecNac;
    private Date fecVal;
    private String correo;
    private UsuarioTO usuario = new UsuarioTO();

    /**
     * @return the rut
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    /**
     * @return the dv
     */
    public char getDv() {
        return dv;
    }

    /**
     * @param dv the dv to set
     */
    public void setDv(char dv) {
        this.dv = dv;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the primerApellido
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the fecNac
     */
    public Date getFecNac() {
        return fecNac;
    }

    /**
     * @param fecNac the fecNac to set
     */
    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    /**
     * @return the fecVal
     */
    public Date getFecVal() {
        return fecVal;
    }

    /**
     * @param fecVal the fecVal to set
     */
    public void setFecVal(Date fecVal) {
        this.fecVal = fecVal;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the usuario
     */
    public UsuarioTO getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setCliente(UsuarioTO usuario) {
        this.usuario = usuario;
    }

}
