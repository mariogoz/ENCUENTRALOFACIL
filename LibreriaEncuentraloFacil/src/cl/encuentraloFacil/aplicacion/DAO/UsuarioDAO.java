/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import cl.encuentraloFacil.aplicacion.util.Constantes;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class UsuarioDAO implements Serializable{
    
     final static Logger logger = Logger.getLogger(UsuarioDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;
    
    public Boolean getExisteUsuario(ClienteTO cliente) {
        Boolean existe = false;
         try {
             cst = conn.getConnection().prepareStatement(" select usernom from encuentralofacil.usuario \n" +
                     "where usernom = ? ");
             UsuarioTO usuario = cliente.getUsuario();
             cst.setString(1, usuario.getUserName());
             
             rs = cst.executeQuery();
            
            while (rs.next()) {
               existe = true;
               logger.info("Existe empresa : " + existe);
            }
         } catch (SQLException ex) {
             logger.error("Error sql getExisteUsuario " +ex);
         } catch (Exception e) {
            logger.error("getExisteUsuario : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (conn != null) {
                    conn.disconnect();
                } 
                
            } catch (SQLException ex) {
                logger.error(" getExisteUsuario FINALLY: " + ex.getMessage());
            }
        }
         return existe;
    }
    
       public Integer insertUsuario (UsuarioTO usuario, Integer idCliente){
        Integer value = null;
        try {
            cst = conn.getConnection().prepareStatement(" INSERT INTO usuario \n" +
                    "(`usernom`, \n" +
                    "`cod`, \n" +
                    "`fecrea`, \n" +
                    "`Estados_idest`, \n" +
                    "`is_admin`, \n" +
                    "`id_cliente`, \n" +
                    "`correo`) \n" +
                    "VALUES \n" +
                    "( ? , \n" +
                    "? , \n" +
                    "? , \n" +
                    "? , \n" +
                    "? , \n" +
                    "? , \n" +
                    "? ); ");
            
            cst.setString(1, usuario.getUserName());
            cst.setString(2, usuario.getPassword());
            java.sql.Date sqlDate = new java.sql.Date(usuario.getFecCrea().getTime());
            cst.setDate(3, sqlDate);
            cst.setInt(4, usuario.getEstadoId());
            cst.setInt(5, Constantes.TipoConexion.PUBLICO);
            cst.setInt(6, idCliente);
            cst.setString(7, usuario.getCorreo());
 
             value = cst.executeUpdate();
        } catch (SQLException e) {
            logger.error("[InsertUsuario]  " + e.getMessage());
        } catch (Exception e){
            logger.error("[InsertUsuario]  " + e.getMessage());
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (conn != null) {
                    conn.disconnect();
                } 
                
            } catch (SQLException ex) {
                logger.error("[InsertUsuario]  " + ex.getMessage());
            }
        }
        
        return value;
        
    }
       
        public Integer isNewRut(ClienteTO cliente) {
        Integer respuesta = 0;
        try {
            cst = conn.getConnection().prepareStatement(" SELECT count(*) existe from cliente where rut = ? AND dv = '?; ");
            cst.setInt(1, cliente.getRut());
            cst.setString(2,String.valueOf(cliente.getDv()));
            
            while (rs.next()) {
                respuesta = rs.getInt("existe");
            }
            
        } catch (SQLException ex) {
            logger.info("[ERROR] isNewRut : " + ex.getMessage());
        } catch (Exception e) {
            logger.info("[ERROR NO IDENTIFICADO] isNewRut : " + e.getMessage());
        } 
        finally {
            try {
                 if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.disconnect();
                }
            } catch (SQLException ex) {
                logger.info("[ERROR] isNewRut : " + ex.getMessage());
            } 
        }
        return respuesta;
    }
    
}
