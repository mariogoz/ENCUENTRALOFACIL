/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.EmpresaDAO;
import cl.encuentraloFacil.aplicacion.TO.EmpresaTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.util.List;
import org.apache.log4j.Logger;
/**
 *
 * @author Mario
 */
public class AutoAdminBusiness {

    final static Logger logger = Logger.getLogger(AutoAdminBusiness.class);
    private final EmpresaDAO empresaDAO = new EmpresaDAO();

    public EmpresaTO getInfoEmpresa(UsuarioTO usuario) {
        logger.info("METHOD getInfoEmpresa");
        EmpresaTO empresa = null;
        try {
            empresa = empresaDAO.datosEmpresa(usuario);
        } catch (Exception e) {
            logger.error("[AutoAdminBusiness] getInfoEmpresa : " + e.getMessage());
        }
        return empresa;
    }    
    
    public List<ProductoTO> getProductoEmpresa(EmpresaTO empresa){
        logger.info("METHOD getProductoEmpresa");
        List<ProductoTO> respuesta = null;  
        try {
           respuesta = empresaDAO.getProductoEmpresa(empresa);
        } catch (Exception e) {
            logger.error("[AutoAdminBusiness] getProductoEmpresa : " + e.getMessage());
        }
        return respuesta;
    }
}
