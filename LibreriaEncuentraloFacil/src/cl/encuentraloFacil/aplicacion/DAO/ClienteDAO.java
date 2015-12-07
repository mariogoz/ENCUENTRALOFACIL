/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class ClienteDAO {
    final static Logger logger = Logger.getLogger(ClienteDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;
    
   public Integer insertCliente (ClienteTO cliente){
        Integer value = null;
        try {
            cst = conn.getConnection().prepareStatement(" INSERT INTO cliente\n" +
                    "(  \n"+
                    "`rut`,\n" +
                    "`dv`,\n" +
                    "`nomb`,\n" +
                    "`apells` ,\n" +
                    "`fenac` ,\n" +
                    "`Correo`) \n" +
                    "VALUES \n" +
                    "( ? ,\n" +
                    " ? ,\n" +
                    " ? ,\n" +
                    " ? ,\n" +
                    " ? ,\n" +
                    " ? );");
            
            cst.setInt(1, cliente.getRut());
            cst.setString(2, String.valueOf(cliente.getDv()));
            cst.setString(3, cliente.getNombre());
            cst.setString(4, cliente.getPrimerApellido());
            java.sql.Date sqlDate = new java.sql.Date(cliente.getFecNac().getTime());
            cst.setDate(5, sqlDate);
            cst.setString(6, cliente.getCorreo());
 
             value = cst.executeUpdate();
        } catch (SQLException e) {
            logger.error("[insertCliente]  " + e.getCause().toString());
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
                logger.error("[insertCliente]  " + ex.getMessage());
            }
        }
        
        return value;   
    }
       public Integer getIdCliente(ClienteTO cliente) {
       Integer  respuesta = null;
        try {
            cst = conn.getConnection().prepareStatement("SELECT idclie IDCLIENTE FROM cliente where rut = ? and dv  = ?;");
            cst.setInt(1, cliente.getRut());
            cst.setString(2, String.valueOf(cliente.getDv()));
            rs = cst.executeQuery();
            while (rs.next()) {
                respuesta = rs.getInt("IDCLIENTE");
            }    
        } catch (SQLException e) {
            logger.error("[ERROR] getIdCliente SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getIdCliente: " + e.getMessage());
        } finally {
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
                logger.error("[ERROR] getIdCliente: " + ex.getMessage());
            } 
        }
        return respuesta;
    }
}
