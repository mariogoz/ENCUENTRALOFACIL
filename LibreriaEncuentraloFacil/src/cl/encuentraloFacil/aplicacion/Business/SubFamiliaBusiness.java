/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.SubFamiliaDAO;
import cl.encuentraloFacil.aplicacion.TO.SubFamiliaProdTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MacBook Pro
 */
public class SubFamiliaBusiness {
    
    SubFamiliaDAO sfDao = new SubFamiliaDAO();
    
    public List<SubFamiliaProdTO> getSubFamiliaProductos(int id_familia){
        List<SubFamiliaProdTO> listSubFamilia = new ArrayList<SubFamiliaProdTO>();
        try {
            listSubFamilia = sfDao.getSubFamilia(id_familia);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return listSubFamilia;
    }
    
    
    public int setSubFamiliaBusiness(SubFamiliaProdTO subfamilia){
        int res = 0;
        try {
            res = sfDao.setSubFamilia(subfamilia);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        
        return res;
    }
    
    public int updSubFamiliaBusiness(SubFamiliaProdTO subfamilia){
        int res = 0;
        try {
            res = sfDao.updSubFamilia(subfamilia);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        
        return res;
    }
    
    
}
