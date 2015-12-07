/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.TO;

import java.io.Serializable;

/**
 *
 * @author Mario
 */
public class AlertaTO implements Serializable{
    private Integer rut;
    private String dv;
    private String nombres;
    private String apellidos;
    private String correo;
    private Integer idUsuario;
    private String numeroFijo;
    private String numeroMovil;

    /**
     * @return the rut
     */
    public Integer getRut() {
        return rut;
    }

    @Override
    public String toString() {
        return "AlertaTO{" + "rut=" + rut + ", dv=" + dv + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo + ", idUsuario=" + idUsuario + ", numeroFijo=" + numeroFijo + ", numeroMovil=" + numeroMovil + '}';
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
    public String getDv() {
        return dv;
    }

    /**
     * @param dv the dv to set
     */
    public void setDv(String dv) {
        this.dv = dv;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the numeroFijo
     */
    public String getNumeroFijo() {
        return numeroFijo;
    }

    /**
     * @param numeroFijo the numeroFijo to set
     */
    public void setNumeroFijo(String numeroFijo) {
        this.numeroFijo = numeroFijo;
    }

    /**
     * @return the numeroMovil
     */
    public String getNumeroMovil() {
        return numeroMovil;
    }

    /**
     * @param numeroMovil the numeroMovil to set
     */
    public void setNumeroMovil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
    }
}
