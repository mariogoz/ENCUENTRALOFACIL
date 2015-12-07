/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.AutoAdminBusiness;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
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
    
    public void crearMenu(){
         String[] arreglo = new String[]{"Carga de datos","Quien SOY", "Info EncuenraloFacil"};
        for (String titulo : arreglo) {
           // DefaultMenu primSubMenu =  new DefaultSubMenu(titulo);
          //  primSubMenu.
        }
         
    }    
}
