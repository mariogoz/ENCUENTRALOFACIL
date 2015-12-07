/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.AutoAdminBusiness;
import cl.encuentraloFacil.aplicacion.TO.EmpresaTO;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class BeanRegistroEmpresa implements Serializable {

    final static Logger logger = Logger.getLogger(BeanRegistroEmpresa.class);
    AutoAdminBusiness adminBusiness = new AutoAdminBusiness();
    private FacesMessage msg;
    private FacesContext context;
    private EmpresaTO empresa = new EmpresaTO();
    private String rutEmpresaFrond;
    private Boolean isIngresar;
    private Boolean isEditar;
    private Boolean isEliminar;

    public BeanRegistroEmpresa() {
        this.isIngresar = null;
        this.isEditar = null;
        this.isEliminar = null;
    }

    public void registrarEmpresa() {
        logger.info("registrarEmpresa");
        try {
        String replace = rutEmpresaFrond.replaceAll("\\.", "");
        String[] valores = replace.split("-");
        Integer valorRut = Integer.parseInt(valores[0]);
        char dv = valores[1].charAt(0);
        getEmpresa().setRutEmp(valorRut);
        getEmpresa().setDv(String.valueOf(dv));
        adminBusiness.insertEmpresa();
        rutEmpresaFrond = null;
            setEmpresa(null);
        } catch (Exception e) {
            logger.error("Error registrarEmpresa : " + e.getMessage());
        }
        
        //Integer isRut = findExisteRut();
    }

    public Boolean renderIngresar() {
        setIsEditar(null);
        setIsIngresar((Boolean) true);
        setIsEliminar(null);
        return getIsIngresar();
    }

    public Boolean renderEditar() {
        setIsEditar((Boolean) true);
        setIsIngresar(null);
        setIsEliminar(null);
        return getIsEditar();
    }

    public Boolean renderEliminar() {
        setIsEliminar((Boolean) true);
        setIsEditar(null);
        setIsIngresar(null);
        return getIsEliminar();
    }

    /**
     * @return the empresa
     */
    public EmpresaTO getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(EmpresaTO empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the rutEmpresaFrond
     */
    public String getRutEmpresaFrond() {
        return rutEmpresaFrond;
    }

    /**
     * @param rutEmpresaFrond the rutEmpresaFrond to set
     */
    public void setRutEmpresaFrond(String rutEmpresaFrond) {
        this.rutEmpresaFrond = rutEmpresaFrond;
    }

    /**
     * @return the isIngresar
     */
    public Boolean getIsIngresar() {
        return isIngresar;
    }

    /**
     * @param isIngresar the isIngresar to set
     */
    public void setIsIngresar(Boolean isIngresar) {
        this.isIngresar = isIngresar;
    }

    /**
     * @return the isEditar
     */
    public Boolean getIsEditar() {
        return isEditar;
    }

    /**
     * @param isEditar the isEditar to set
     */
    public void setIsEditar(Boolean isEditar) {
        this.isEditar = isEditar;
    }

    /**
     * @return the isEliminar
     */
    public Boolean getIsEliminar() {
        return isEliminar;
    }

    /**
     * @param isEliminar the isEliminar to set
     */
    public void setIsEliminar(Boolean isEliminar) {
        this.isEliminar = isEliminar;
    }

}
