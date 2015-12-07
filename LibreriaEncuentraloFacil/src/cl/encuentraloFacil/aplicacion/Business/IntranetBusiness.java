/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.IntranetDAO;
import cl.encuentraloFacil.aplicacion.TO.AlertaTO;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class IntranetBusiness implements Serializable{
    private final IntranetDAO intranetDao = new IntranetDAO();
    final static Logger logger = Logger.getLogger(IntranetBusiness.class);
    
    public List<AlertaTO> getListAlert(){
        List<AlertaTO> alertas = null;
        try{
            alertas = intranetDao.getAlert();
        } catch (Exception e) {
            logger.error("Error Business getListAlertas :" + e.getMessage());
        } 
        return alertas;
    }
}
