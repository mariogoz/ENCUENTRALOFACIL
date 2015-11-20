/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.DAO.FamiliaDao;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author MacBook Pro
 */
@ManagedBean
@Named(value="beanFamilia")
@SessionScoped
public class BeanFamilia implements Serializable{

    private ArrayList<SelectItem> cmbFamilia = null;
    
    /**
     * Creates a new instance of BeanFamilia
     */
    public BeanFamilia() {}

    public ArrayList<SelectItem> getCmbFamilia() {
        
        if(cmbFamilia==null){
            cmbFamilia = new ArrayList<SelectItem>();
            FamiliaDao fDao = new FamiliaDao();
            
            SelectItem a = new SelectItem(0,"Seleccione");
            cmbFamilia.add(a);
            
            for(FamiliaProdTO f : fDao.getBusquedaProd()){
                SelectItem s = new SelectItem(f.getIdFam(), f.getNomFam());
                cmbFamilia.add(s);
            } 
        }
        
        return cmbFamilia;
    }

    public void setCmbFamilia(ArrayList<SelectItem> cmbFamilia) {
        this.cmbFamilia = cmbFamilia;
    }
    
}
