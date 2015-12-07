/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.FamiliaDAO;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MacBook Pro
 */
public class FamiliaBusiness {
    
    FamiliaDAO fDao = new FamiliaDAO();
    
    public List<FamiliaProdTO> getFamiliaProductos(){
        List<FamiliaProdTO> listFamilia = new ArrayList<FamiliaProdTO>();
        try {
            listFamilia = fDao.getFamilia();
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return listFamilia;
    }
    
    public int setFamiliaBusiness(FamiliaProdTO familia){
        int res = 0;
        try {
            res = fDao.setFamilia(familia);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        
        return res;
    }
    
    public int updFamiliaBusiness(FamiliaProdTO familia){
        int res = 0;
        try {
            res = fDao.updFamilia(familia);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        
        return res;
    }
    
}
