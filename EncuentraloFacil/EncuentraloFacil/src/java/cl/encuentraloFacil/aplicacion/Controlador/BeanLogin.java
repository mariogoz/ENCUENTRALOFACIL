/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.LoginBusiness;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import cl.encuentraloFacil.aplicacion.util.Properties;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private ProductoTO pro;
    private final LoginBusiness loginBusiness = new LoginBusiness();
    private FacesMessage msg;
    private FacesContext context;
    private Boolean render = false;
    private String auto;
    
    

    /**
     * Creates a new instance of BeanLogin
     */
    public BeanLogin() {
        
    }
    
    public void cambiar()
    {
        System.out.println("default :" + render);
        setRender(true);
        System.out.println("RenderCambiado :" + render);
    }
    /**
     * Metodo encargado de la autentificacion al sistema EncuentraloFacil
     *
     * @return Si el usuario a autentificarse es exitiso o fallido
     */
    public String doLogin() {
        String destino = null;
        String respuesta = null;
        context = FacesContext.getCurrentInstance();

        try {

            respuesta = loginBusiness.getBuscarCliente(getUsuario());
            if (respuesta != null && respuesta.equals("EXITO")) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("beanLogin", this);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanlogin.autentificacion.success") + " " + usuario.getPrimerNombre()
                        + " " + usuario.getPrimerApellido(), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                destino = "exito.login";
                
                
            } else {
                destino = "fallo.login";
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanlogin.autentificacion.fail"), null);
                context.addMessage(null, msg);
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewLogin.xhtml");
            usuario = null;
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

    /**
     *
     * @return Metodo que redirecciona ViewRegistrar
     */
    public String doNuevoUsuario() {
        return "usuario.nuevo";
    }
   
     
    public List<String> autoComplete(String query){
        List<ProductoTO> resPro = new ArrayList<ProductoTO>();
        List<String> res = new ArrayList<String>();
       
        
        resPro = loginBusiness.getBuscarAutoComplete(query);
        for(int x = 0; x < resPro.size();x++){
            res.add(resPro.get(x).getNombreProducto());
            
            
        }
       
        
        
        
        
       return res;
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
     * @return the render
     */
    public Boolean getRender() {
        return render;
    }

    /**
     * @param render the render to set
     */
    public void setRender(Boolean render) {
        this.render = render;
    }

    

    /**
     * @return the pro
     */
    public ProductoTO getPro() {
        return pro;
    }

    /**
     * @param pro the pro to set
     */
    public void setPro(ProductoTO pro) {
        this.pro = pro;
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

}
