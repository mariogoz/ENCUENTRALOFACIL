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
    private Date fecCrea;
    private Date fecVal;
    private String correo;
    private Integer estadoId;
    private String GlosaConexion;
    private Integer admin;

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

    /**
     * @return the admin
     */
    public Integer getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
