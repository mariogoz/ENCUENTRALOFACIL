/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.FamiliaBusiness;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@Named(value="beanFamilia")
@ViewScoped
public class BeanFamilia implements Serializable{
    
    private List<FamiliaProdTO> list_familia = new ArrayList<FamiliaProdTO>();
    private FamiliaProdTO familia = new FamiliaProdTO();
    private FamiliaBusiness fb =  new FamiliaBusiness();
    
    private FacesMessage msg;
    private FacesContext context;
    
    @PostConstruct
    public void init(){
        list_familia = fb.getFamiliaProductos();
    }
    
    public void IngresarFamilia(){
        fb.setFamiliaBusiness(familia);
    }
    
    public void ActualizarFamilia(RowEditEvent event){
        
        int res = 0;
        context = FacesContext.getCurrentInstance();
        
        if(familia.getNomFam().equalsIgnoreCase("")){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se debe ingresar el nombre del la familia");
            context.addMessage(null, msg);
            return;
        }
        
        if(res>0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ingreso correctamente la familia"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hubo un problema al ingresar la familia"));
        }
        
        familia.setIdFam(((FamiliaProdTO) event.getObject()).getIdFam());
        fb.updFamiliaBusiness(familia);
        
        list_familia = fb.getFamiliaProductos();
        
    }
    
    
    public FamiliaBusiness getFb() {
        return fb;
    }

    public void setFb(FamiliaBusiness fb) {
        this.fb = fb;
    }

    public List<FamiliaProdTO> getList_familia() {
        return list_familia;
    }

    public void setList_familia(List<FamiliaProdTO> list_familia) {
        this.list_familia = list_familia;
    }

    public FamiliaProdTO getFamilia() {
        return familia;
    }

    public void setFamilia(FamiliaProdTO familia) {
        this.familia = familia;
    }
    
}
