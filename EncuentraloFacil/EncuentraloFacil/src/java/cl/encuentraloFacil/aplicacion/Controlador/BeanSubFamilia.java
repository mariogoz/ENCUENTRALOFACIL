/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.FamiliaBusiness;
import cl.encuentraloFacil.aplicacion.Business.SubFamiliaBusiness;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import cl.encuentraloFacil.aplicacion.TO.SubFamiliaProdTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author MacBook Pro
 */

@ManagedBean
@Named(value="beanSubFamilia")
@ViewScoped
public class BeanSubFamilia implements Serializable{
    
    private List<SubFamiliaProdTO> list_subfamilia = new ArrayList<SubFamiliaProdTO>();
    private SubFamiliaProdTO subfamilia = new SubFamiliaProdTO();
    private SubFamiliaBusiness sfb =  new SubFamiliaBusiness();
    private FamiliaBusiness fb =  new FamiliaBusiness();
    
    private ArrayList<SelectItem> cmbFamilia   = null;
    private ArrayList<SelectItem> cmbFamilia_g = null;
    
    private FacesMessage msg;
    private FacesContext context;
    
    @PostConstruct
    public void init() {
        list_subfamilia = sfb.getSubFamiliaProductos(0);
        cmbFamilia = llenarFamilia();
        cmbFamilia_g = llenarFamilia_g();
    }

    public ArrayList<SelectItem> llenarFamilia(){
        cmbFamilia = new ArrayList<SelectItem>();
        for(FamiliaProdTO f : fb.getFamiliaProductos()){
            SelectItem s = new SelectItem(f.getIdFam(), f.getNomFam());
            cmbFamilia.add(s);
        }
        return cmbFamilia;
    }

    public ArrayList<SelectItem> llenarFamilia_g(){
        cmbFamilia_g = new ArrayList<SelectItem>();
        for(FamiliaProdTO f : fb.getFamiliaProductos()){
            SelectItem s = new SelectItem(f.getIdFam(), f.getNomFam());
            cmbFamilia_g.add(s);
        }
        return cmbFamilia_g;
    }
    
    public void ingresarSubFamilia(){
        int res = 0;
        context = FacesContext.getCurrentInstance();
        
        if(subfamilia.getNomSubFam().equalsIgnoreCase("")){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se debe ingresar el nombre del la subfamilia");
            context.addMessage(null, msg);
            return;
        }
        
        if(subfamilia.getFamilia().getIdFam()==0){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se debe ingresar el la familia a la cual pertenece subfamilia");
            context.addMessage(null, msg);
            return;
        }
        
        res = sfb.setSubFamiliaBusiness(subfamilia);
        
        if(res>0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ingreso correctamente la subfamilia"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hubo un problema al ingresar la subfamilia"));
        }
        
    }
    
    public void actualizarSubFamilia(RowEditEvent event){
        
        int res = 0;
        context = FacesContext.getCurrentInstance();
        
        if(subfamilia.getNomSubFam().equalsIgnoreCase("")){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se debe ingresar el nombre del la subfamilia");
            context.addMessage(null, msg);
            return;
        }
        
        if(subfamilia.getFamilia().getIdFam()==0){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se debe ingresar el la familia a la cual pertenece subfamilia");
            context.addMessage(null, msg);
            return;
        }
        
        subfamilia.setIdSubFam(((SubFamiliaProdTO) event.getObject()).getIdSubFam());        
        res = sfb.updSubFamiliaBusiness(subfamilia);
        
        if(res>0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ingreso correctamente la subfamilia"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hubo un problema al ingresar la subfamilia"));
        }
    }
    
    public List<SubFamiliaProdTO> getList_subfamilia() {
        return list_subfamilia;
    }

    public void setList_subfamilia(List<SubFamiliaProdTO> list_subfamilia) {
        this.list_subfamilia = list_subfamilia;
    }

    public SubFamiliaProdTO getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(SubFamiliaProdTO subfamilia) {
        this.subfamilia = subfamilia;
    }

    public SubFamiliaBusiness getSfb() {
        return sfb;
    }

    public void setSfb(SubFamiliaBusiness sfb) {
        this.sfb = sfb;
    }

    public FamiliaBusiness getFb() {
        return fb;
    }

    public void setFb(FamiliaBusiness fb) {
        this.fb = fb;
    }

    public ArrayList<SelectItem> getCmbFamilia() {
        return cmbFamilia;
    }

    public void setCmbFamilia(ArrayList<SelectItem> cmbFamilia) {
        this.cmbFamilia = cmbFamilia;
    }

    public ArrayList<SelectItem> getCmbFamilia_g() {
        return cmbFamilia_g;
    }

    public void setCmbFamilia_g(ArrayList<SelectItem> cmbFamilia_g) {
        this.cmbFamilia_g = cmbFamilia_g;
    }

    
}
