/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.BusquedaTO;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
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
public class FamiliaDao {
    final static Logger logger = Logger.getLogger(BusquedaDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;

    public List<FamiliaProdTO> getBusquedaProd() {
        logger.info("getBusquedaProd");
        List<FamiliaProdTO> listFamilia = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement("SELECT idFamilia,nomfam FROM familia");
            rs = cst.executeQuery();
            while (rs.next()) {
                FamiliaProdTO f = new FamiliaProdTO(); 
                f.setIdFam(rs.getInt("idFamilia"));
                f.setNomFam(rs.getString("nomFam"));
                
                listFamilia.add(f);
            }
        } catch (SQLException e) {
            logger.error("[ERROR] getBusquedaProd SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getBusquedaProd: " + e.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                } if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] getBusquedaProd: " + ex.getMessage());
            } 
        }
        return listFamilia;
    }
}
