/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.LoginBusiness;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import cl.encuentraloFacil.aplicacion.util.Properties;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author Mario
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class BeanLogin implements Serializable {

    //Declaracion de atributos
    private UsuarioTO usuario = new UsuarioTO();
    private ClienteTO cliente = new ClienteTO();
    private final LoginBusiness loginBusiness = new LoginBusiness();
    private FacesMessage msg;
    private FacesContext context;
    private String auto;
    private UsuarioTO respuesta;
 
    public BeanLogin() {
        
    }
    /**
     * Metodo encargado de la autentificacion al sistema EncuentraloFacil
     *
     * @return Si el usuario a autentificarse es exitiso o fallido
     */
    public String doLogin() {
        String destino = null;
        setRespuesta(null);
        context = FacesContext.getCurrentInstance();

        try {

            setRespuesta(loginBusiness.getBuscarCliente(getUsuario()));
            if (getRespuesta() != null && getRespuesta().getGlosaConexion().equalsIgnoreCase(Properties.getProperty("beanlogin.autentificacion.exitosa"))) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("beanLogin", this);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", Properties.getProperty("beanlogin.autentificacion.success") + " " + getRespuesta().getPrimerNombre()
                        + " " + getRespuesta().getPrimerApellido());
                context.addMessage(null, msg);
                destino = Properties.getProperty("beanlogin.redireccionar.exitoso");
                
                
            } else {
                destino = Properties.getProperty("beanlogin.redireccionar.fallida");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanlogin.autentificacion.fail"), null);
                context.addMessage(null, msg);
                setRespuesta(null);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Properties.getProperty("beanlogin.autentificacion.error"), null);
            context.addMessage(null, msg);
            throw new RuntimeException(e);
        }
        return destino;
    }

    /**
     * Metodo quue se encarga del cierre de sesion y redirecciona la vista a
     * ViewLogin
     */
    public void cerrarSesion() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewLogin.xhtml");
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     *
     * @return Metodo que redirecciona a ViewBusqueda
     */
    public String cancelarLogin() {
        return "cancelar.login";
    }
    
    
     public String cargaMasivaRef() {
         String val = null;
        try {
            //FacesContext.getCurrentInstance().getExternalContext().redirect("cargaMasiva.xhtml");
            val = "cargamasiva.auto";
        } catch (ExceptionInInitializerError ex) {
         FacesMessage var =  new FacesMessage(FacesMessage.SEVERITY_ERROR, null,"Error redireccionar [Carga Masiva] :"+ ex.getMessage());
         context.addMessage(null, var);           
        } 
         return val;
    }
     public String doInicio() {
        return "inicio.auto";
    }
    /**
     *
     * @return Metodo que redirecciona ViewRegistrar
     */
    public String doNuevoUsuario() {
        return "usuario.nuevo";
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
    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the cliente
     * 
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
     * @return the auto
     */
    public String getAuto() {
        return auto;
    }

    /**
     * @param auto the auto to set
     */
    public void setAuto(String auto) {
        this.auto = auto;
    }

    /**
     * @return the respuesta
     */
    public UsuarioTO getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(UsuarioTO respuesta) {
        this.respuesta = respuesta;
    }

}
