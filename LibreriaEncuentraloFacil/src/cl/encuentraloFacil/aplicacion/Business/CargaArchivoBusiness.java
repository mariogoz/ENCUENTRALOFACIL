/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.CargaArchivosDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author FelipeSilva
 */
public class CargaArchivoBusiness implements Serializable{
    
    CargaArchivosDAO cargaArchDAO =  new CargaArchivosDAO();
    
    public List<String> retornaNomProd(List<Integer> prod) {
        
        List<String> resp = new ArrayList<>();
        try {
            resp = cargaArchDAO.cargarDatos(prod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
