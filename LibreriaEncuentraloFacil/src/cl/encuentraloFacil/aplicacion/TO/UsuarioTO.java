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
public class UsuarioTO implements Serializable {

    private Integer idUsuario;
    private String userName;
    private String password;
    private String primerNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fecCrea;
    private Date fecVal;
    private String correo;
    private Integer estadoId;
    private String GlosaConexion;

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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the primerNombre
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * @param primerNombre the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
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
     * @return the segundoApellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the fecCrea
     */
    public Date getFecCrea() {
        return fecCrea;
    }

    /**
     * @param fecCrea the fecCrea to set
     */
    public void setFecCrea(Date fecCrea) {
        this.fecCrea = fecCrea;
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
     * @return the estadoId
     */
    public Integer getEstadoId() {
        return estadoId;
    }

    /**
     * @param estadoId the estadoId to set
     */
    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    /**
     * @return the GlosaConexion
     */
    public String getGlosaConexion() {
        return GlosaConexion;
    }

    /**
     * @param GlosaConexion the GlosaConexion to set
     */
    public void setGlosaConexion(String GlosaConexion) {
        this.GlosaConexion = GlosaConexion;
    }
}
