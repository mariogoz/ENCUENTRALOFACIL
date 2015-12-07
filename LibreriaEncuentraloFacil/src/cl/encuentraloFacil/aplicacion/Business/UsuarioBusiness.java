/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.UsuarioDAO;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class UsuarioBusiness {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    final static Logger logger = Logger.getLogger(UsuarioBusiness.class);
    public Boolean getExisteUsuario(ClienteTO cliente) {
        Boolean isExiste = null;
        try {
            isExiste = usuarioDAO.getExisteUsuario(cliente);
        } catch (Exception e) {
            logger.error("Eroor :" + e.getMessage());
        }
        
        return isExiste;
    }
    
    public Integer getExisteRut(ClienteTO cliente) {
        Integer isExiste = null;
        try {
            isExiste = usuarioDAO.isNewRut(cliente);
        } catch (Exception e) {
            logger.error("Eroor :" + e.getMessage());
        }
        
        return isExiste;
    }
}
