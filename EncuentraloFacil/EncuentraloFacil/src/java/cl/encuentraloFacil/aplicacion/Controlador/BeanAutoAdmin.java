/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.AutoAdminBusiness;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mario
 */

public class BeanAutoAdmin implements Serializable{
    
    private FacesContext facesContext;
    ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
    BeanLogin referenciaBeanSession = (BeanLogin) contexto.getSessionMap().get("BeanLogin");
    UsuarioTO usuario = new UsuarioTO();
    AutoAdminBusiness autoAdminBuss;
    /**
     * Creates a new instance of BeanAutoAdmin
     */
    public BeanAutoAdmin() {
        
    }
    
  
    
    public void getProductosPorCliente(){
        List<ProductoTO> listProductos = new ArrayList();
        usuario = referenciaBeanSession.getUsuario();
        listProductos = autoAdminBuss.getProductosClientes(usuario);
    }
    
}
