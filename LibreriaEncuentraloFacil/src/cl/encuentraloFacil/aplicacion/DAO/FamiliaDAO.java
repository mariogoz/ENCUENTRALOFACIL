/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
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
public class FamiliaDAO {
    final static Logger logger = Logger.getLogger(FamiliaDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;

    /*DAO Familia*/
    public List<FamiliaProdTO> getFamilia() {
        logger.info("getFamilia");
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
            logger.error("[ERROR] getFamilia SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getFamilia: " + e.getMessage());
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
                logger.error("[ERROR] getFamilia: " + ex.getMessage());
            } 
        }
        return listFamilia;
    }
    
    public int setFamilia(FamiliaProdTO familia) throws SQLException{
        int res = 0;
        logger.info("setFamilia");
        try {
            cst = conn.getConnection().prepareStatement(
                "INSERT INTO familia " +
                "( " +
                "   nomfam " +
                ") " +
                "VALUES " +
                "( " +
                "   '"+familia.getNomFam()+"' " +
                ");");
            res = cst.executeUpdate();
        } catch (SQLException e) {
            logger.error("[ERROR] setFamilia SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] setFamilia: " + e.getMessage());
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
                logger.error("[ERROR] setFamilia: " + ex.getMessage());
            } 
        }
        return res;
    }
    
    public int updFamilia(FamiliaProdTO familia) throws SQLException{
        int res = 0;
        logger.info("updFamilia");
        try {
            cst = conn.getConnection().prepareStatement(
                "UPDATE  " +
                "	familia " +
                "SET " +
                "	nomfam = '"+familia.getNomFam()+"' " +
                "WHERE  " +
                "	idFamilia = '"+familia.getIdFam()+"';");
            res = cst.executeUpdate();
        } catch (SQLException e) {
            logger.error("[ERROR] updFamilia SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] updFamilia: " + e.getMessage());
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
                logger.error("[ERROR] updFamilia: " + ex.getMessage());
            } 
        }
        return res;
    }
    
}
