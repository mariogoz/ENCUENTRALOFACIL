/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.SubFamiliaProdTO;
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
public class SubFamiliaDAO {
    
    final static Logger logger = Logger.getLogger(SubFamiliaDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;
    
    /*Dap SubFamilia*/
    public List<SubFamiliaProdTO> getSubFamilia(int id_familia) {
        logger.info("getSubFamilia");
        List<SubFamiliaProdTO> listSubFamilia = new ArrayList();
        try {
            
            String sql = 
                "SELECT "
                + "     idSubFamilia "
                + "     ,nomsubfam "
                + "     ,familia_idFamilia "
                + "     ,nomfam "
                + "FROM "
                + "     subfamilia sf "
                + "INNER JOIN "
                + "     familia f "
                + "ON "
                + "     sf.Familia_idFamilia = f.idFamilia ";
                
                if(id_familia!=0){
                    sql 
                    += "WHERE "
                    + "     f.idFamilia = '"+id_familia+"' ";
                }
            
            cst = conn.getConnection().prepareStatement(sql);
            rs = cst.executeQuery();
            while (rs.next()) {
                SubFamiliaProdTO sf = new SubFamiliaProdTO(); 
                sf.setIdSubFam(rs.getInt("idSubFamilia"));
                sf.setNomSubFam(rs.getString("nomsubfam"));
                sf.getFamilia().setIdFam(rs.getInt("familia_idFamilia"));
                sf.getFamilia().setNomFam(rs.getString("nomfam"));
                
                listSubFamilia.add(sf);
            }
        } catch (SQLException e) {
            logger.error("[ERROR] getSubFamilia SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getSubFamilia: " + e.getMessage());
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
                logger.error("[ERROR] getSubFamilia: " + ex.getMessage());
            } 
        }
        return listSubFamilia;
    }
    
    
    public int setSubFamilia(SubFamiliaProdTO subfamilia) throws SQLException{
        int res = 0;
        logger.info("setSubFamilia");
        try {
            cst = conn.getConnection().prepareStatement(
                "INSERT INTO subfamilia " +
                "( " +
                "	nomsubfam, " +
                "	Familia_idFamilia " +
                ") " +
                "VALUES " +
                "( " +
                "	'"+subfamilia.getNomSubFam()+"', " +
                "	'"+subfamilia.getFamilia().getIdFam()+"' " +
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
    
    public int updSubFamilia(SubFamiliaProdTO subfamilia) throws SQLException{
        int res = 0;
        logger.info("updSubFamilia");
        try {
            cst = conn.getConnection().prepareStatement(
                "UPDATE  " +
                "	subfamilia " +
                "SET " +
                "	nomsubfam = '"+subfamilia.getNomSubFam()+"', " +
                "	Familia_idFamilia = '"+subfamilia.getFamilia().getIdFam()+"' " +
                "WHERE  " +
                "	idSubFamilia = '"+subfamilia.getIdSubFam()+"';");
            res = cst.executeUpdate();
        } catch (SQLException e) {
            logger.error("[ERROR] updSubFamilia SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] updSubFamilia: " + e.getMessage());
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
                logger.error("[ERROR] updSubFamilia: " + ex.getMessage());
            } 
        }
        return res;
    }
    
    
}
