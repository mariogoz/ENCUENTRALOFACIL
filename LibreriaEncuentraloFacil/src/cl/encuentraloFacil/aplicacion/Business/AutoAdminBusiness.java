/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.ClienteDAO;
import cl.encuentraloFacil.aplicacion.DAO.EmpresaDAO;
import cl.encuentraloFacil.aplicacion.DAO.UsuarioDAO;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import cl.encuentraloFacil.aplicacion.TO.EmpresaTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import cl.encuentraloFacil.aplicacion.util.Constantes;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class AutoAdminBusiness {

    final static Logger logger = Logger.getLogger(AutoAdminBusiness.class);
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    private final ClienteDAO clienteDao = new ClienteDAO();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();

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

    public List<ProductoTO> getProductoEmpresa(EmpresaTO empresa) {
        logger.info("METHOD getProductoEmpresa");
        List<ProductoTO> respuesta = null;
        try {
            respuesta = empresaDAO.getProductoEmpresa(empresa);
        } catch (Exception e) {
            logger.error("[AutoAdminBusiness] getProductoEmpresa : " + e.getMessage());
        }
        return respuesta;
    }

    public Boolean insertClienteUsuario(ClienteTO cliente) {

        Integer respuestaInsert = clienteDao.insertCliente(cliente);
        Integer respInsertUsu = null;
        Boolean isRegistro = Constantes.RegistrarUsuario.INSERTFALLIDO;
        UsuarioTO usuario = cliente.getUsuario();
        if (respuestaInsert.equals(Constantes.SQL.RESPUESTAVALIDA) && usuario != null) {
            logger.info("RUT CLIENTE:" + cliente.getRut());
            logger.info("DV CLIENTE:" + cliente.getDv());
            Integer idCliente = clienteDao.getIdCliente(cliente);
            if (idCliente != null) {
                logger.info("IDCLIENTE :" + idCliente);
                java.util.Date utilDate = new java.util.Date();
                usuario.setFecCrea(utilDate);
                usuario.setEstadoId(Constantes.Estados.INACTIVO);
                usuario.setCorreo(cliente.getCorreo());
                respInsertUsu = usuarioDao.insertUsuario(usuario, idCliente);
                if (respInsertUsu.equals(Constantes.SQL.RESPUESTAVALIDA)) {
                    isRegistro = Constantes.RegistrarUsuario.INSERTEXITOSO;
                }
            }
        }
        logger.info("ISREGISTRO ES :" + isRegistro);
        return isRegistro;
    }
    
    public Integer insertEmpresa() {
        Integer respuesta = null;
        try {
            respuesta = empresaDAO.createEmpresa();
        } catch (Exception e) {
            logger.error("[ERROR] insertEmpresa() : " + e.getMessage());
        }
        
        
        return respuesta;
    }
}
