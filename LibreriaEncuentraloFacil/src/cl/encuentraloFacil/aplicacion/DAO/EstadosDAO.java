/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.EstadosTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author MacBook Pro
 */
public class EstadosDAO {
    
    final static Logger logger = Logger.getLogger(EstadosDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;
    
    
    /*DAO Estados*/
    public List<EstadosTO> getBusquedaEstados() {
        logger.info("getBusquedaEstados");
        List<EstadosTO> listEstados = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement(
                  "SELECT "
                + "     idest"
                + "     ,nomest"
                + "     ,descest "
                + "FROM "
                + "     estados");
            rs = cst.executeQuery();
            while (rs.next()) {
                EstadosTO e = new EstadosTO(); 
                e.setIdest(rs.getInt("idest"));
                e.setNomest(rs.getString("nomest"));
                e.setDescest(rs.getString("descest"));
                
                listEstados.add(e);
            }
        } catch (SQLException e) {
            logger.error("[ERROR] getBusquedaEstados SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getBusquedaEstados: " + e.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.disconnect();
                } if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] getBusquedaEstados: " + ex.getMessage());
            } 
        }
        return listEstados;
    }
}
