/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.LoginBusiness;
import cl.encuentraloFacil.aplicacion.Interface.EFDataSource;
import cl.encuentraloFacil.aplicacion.Interface.EFReporte;
import cl.encuentraloFacil.aplicacion.Rpt.RptEjemplo;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import cl.encuentraloFacil.aplicacion.util.Constantes;
import cl.encuentraloFacil.aplicacion.util.GenerardorReporte;
import cl.encuentraloFacil.aplicacion.util.Properties;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

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
            if (getRespuesta() != null && getRespuesta().getGlosaConexion() != null && 
                    getRespuesta().getGlosaConexion().equalsIgnoreCase(Properties.getProperty("beanlogin.autentificacion.exitosa"))) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("beanLogin", this);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", Properties.getProperty("beanlogin.autentificacion.success") + " " + getRespuesta().getUserName());
                context.addMessage(null, msg);
                if (getRespuesta().getAdmin().equals(Constantes.ConstantesBeanLogin.ISADMIN)) {
                    destino = Properties.getProperty("beanlogin.redireccionar.intranet");         
                } else {
                    destino = Properties.getProperty("beanlogin.redireccionar.exitoso");
                }
                

            } else {
                destino = Properties.getProperty("beanlogin.redireccionar.fallida");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanlogin.autentificacion.fail"), null);
                context.addMessage(null, msg);
                setRespuesta(null);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Properties.getProperty("beanlogin.autentificacion.error"), null);
            context.addMessage(null, msg);
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
            FacesMessage var = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Error redireccionar [Carga Masiva] :" + ex.getMessage());
            context.addMessage(null, var);
        }
        return val;
    }

    public String cargaMultipleRef() {
        String val = null;
        try {
            //FacesContext.getCurrentInstance().getExternalContext().redirect("cargaMasiva.xhtml");
            val = "cargamultiple.auto";
        } catch (ExceptionInInitializerError ex) {
            FacesMessage var = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Error redireccionar [Carga Multiple] :" + ex.getMessage());
            context.addMessage(null, var);
        }
        return val;
    }

    public String doInicio() {
        return "inicio.auto";
    }
    
    public String doInformes() {
        return "informes.auto";
    }
    
    public String doProductos() {
        return "mantenedorproductos.auto";
    }
    
    public String doFamilia() {
        return "mantenedorfamilia.auto";
    }

    public String doSubFamilia() {
        return "mantenedorsubfamilia.auto";
    }

    public String doProdEmpresa() {
        return "mantenedorprodempresa.auto";
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

    public void generarReporte() {
        
            Map parametros = new HashMap();
            Map mapaParametros = new HashMap();
            EFReporte ejemplo = new RptEjemplo();
            parametros.put("RESPUESTA", getRespuesta()); 
            mapaParametros.putAll(ejemplo.crearDataSources(parametros));
            mapaParametros.putAll(ejemplo.crearImagenes());
            EFDataSource dataSource = ejemplo.obtenerDataSource();
            GenerardorReporte generador = new GenerardorReporte();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String ruta = facesContext.getExternalContext().getRealPath("/Reportes/rptEjemplo.jasper");

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            String nombre = "rptEjemplo.pdf";
            
            generador.isDataSource(nombre, mapaParametros, dataSource, response, ruta);

    }
}
