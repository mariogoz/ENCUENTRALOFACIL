/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.AutoAdminBusiness;
import cl.encuentraloFacil.aplicacion.Business.UsuarioBusiness;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import cl.encuentraloFacil.aplicacion.util.Properties;
import cl.encuentraloFacil.aplicacion.util.ValidateRut;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
public class BeanRegistro implements Serializable {

    final static Logger logger = Logger.getLogger(BeanRegistro.class);
    private ClienteTO cliente = new ClienteTO();
    private String dvView;

    private FacesMessage msg;
    private FacesContext context;

    AutoAdminBusiness adminBusiness = new AutoAdminBusiness();

    /**
     * Creates a new instance of BeanRegistro
     */
    public BeanRegistro() {

    }

    public void validarRut() {
        context = FacesContext.getCurrentInstance();
        if (getCliente() != null && getCliente().getDv() != ' ' && getCliente().getRut() != null) {
            if (!ValidateRut.ValidarRut(getCliente().getRut(), getCliente().getDv())) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rut no valido", null);
                context.addMessage(null, msg);
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rut no valido", null);
            context.addMessage(null, msg);
        }
    }

    public Boolean findExisteUsuario() {
        UsuarioBusiness usuerBusiness = new UsuarioBusiness();
        Boolean existe = usuerBusiness.getExisteUsuario(getCliente());
        return existe;
    }

    public Integer findExisteRut() {
        UsuarioBusiness usuerBusiness = new UsuarioBusiness();
        Integer existe = usuerBusiness.getExisteRut(getCliente());
        return existe;
    }

    public void registrarCliente() {
        context = FacesContext.getCurrentInstance();

        String replace = dvView.replaceAll("\\.", "");
        String[] valores = replace.split("-");
        Integer valorRut = Integer.parseInt(valores[0]);
        char dv = valores[1].charAt(0);
        getCliente().setRut(valorRut);
        getCliente().setDv(dv);
        Integer isRut = findExisteRut();

        if (isRut.equals(1)) {
            Boolean isExiste = findExisteUsuario();
            if (isExiste) {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Properties.getProperty("usuario.registro.usuario"), null);
                context.addMessage(null, msg);
            } else {

                Boolean isRegistro = adminBusiness.insertClienteUsuario(getCliente());
                if (isRegistro) {
                    logger.info("Registro Exitoso");
                    setCliente(null);
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("usuario.registro.exito"), null);
                    context.addMessage(null, msg);
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Properties.getProperty("beanlogin.autentificacion.error"), null);
                    context.addMessage(null, msg);
                }

            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Properties.getProperty("usuario.registro.rut"), null);
            context.addMessage(null, msg);
        }

    }

    public void limpiarRegistro() {
        setCliente(null);
    }

    public String redireccionarRegistro() {
        return "";
    }

    /**
     * @return the cliente
     */
    public ClienteTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the dvView
     */
    public String getDvView() {
        return dvView;
    }

    /**
     * @param dvView the dvView to set
     */
    public void setDvView(String dvView) {
        this.dvView = dvView;
    }

}
