/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
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
public class ProductoDAO {
    final static Logger logger = Logger.getLogger(ProductoDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;
    
    
    public int setProducto(ProductoTO producto) throws SQLException{
        int res = 0;
        logger.info("setProductos");
        try {
            cst = conn.getConnection().prepareStatement(
                  "INSERT INTO producto ( "
                + " nomprod "
                + ",precio "
                + ",SubFamilia_idSubFamilia "
                + ",estados_idest "
                + ")VALUES( "
                + "'"+producto.getNombreProducto()+"', "
                + "'"+producto.getPrecio()+"', "
                + "'"+producto.getSubfamilia().getIdSubFam()+"', "
                + "'"+producto.getEstados().getIdest()+"' "
                + ")");
            res = cst.executeUpdate();
        } catch (SQLException e) {
            logger.error("[ERROR] setProductos SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] setProductos: " + e.getMessage());
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
                logger.error("[ERROR] getBusquedaProd: " + ex.getMessage());
            } 
        }
        return res;
    }
    
    public List<ProductoTO> getProductos(){
        logger.info("getBusquedaEstados");
        List<ProductoTO> listProductos = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement(
                "SELECT  " +
                "	*  " +
                "FROM  " +
                "	producto p " +
                " " +
                "INNER JOIN  " +
                "	subfamilia sf " +
                "ON " +
                "	sf.idSubFamilia = p.SubFamilia_idSubFamilia " +
                " " +
                "INNER JOIN " +
                "	familia f " +
                "ON  " +
                "	f.idFamilia = sf.Familia_idFamilia " +
                " " +
                "INNER JOIN  " +
                "	estados e " +
                "ON " +
                "	e.idest = p.estados_idest");
            rs = cst.executeQuery();
            while (rs.next()) {
                ProductoTO p = new ProductoTO(); 
                
                p.getSubfamilia().getFamilia().setIdFam(rs.getInt(9));
                p.getSubfamilia().getFamilia().setNomFam(rs.getString(10));
                
                p.getSubfamilia().setIdSubFam(rs.getInt(6));
                p.getSubfamilia().setNomSubFam(rs.getString(7));
                
                p.getEstados().setIdest(rs.getInt(11));
                p.getEstados().setNomest(rs.getString(12));
                
                p.setIdProducto(rs.getInt(1));
                p.setNombreProducto(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                
                listProductos.add(p);
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
        return listProductos;
    }
    
    public int updProducto(ProductoTO producto) throws SQLException{
        int res = 0;
        logger.info("setProductos");
        try {
            cst = conn.getConnection().prepareStatement(
                "UPDATE  " +
                "	producto " +
                "SET " +
                "	nomprod = '"+producto.getNombreProducto()+"', " +
                "	precio = '"+producto.getPrecio()+"', " +
                "	SubFamilia_idSubFamilia = '"+producto.getSubfamilia().getIdSubFam()+"', " +
                "	estados_idest = '"+producto.getEstados().getIdest()+"' " +
                "WHERE  " +
                "	idproduc = '"+producto.getIdProducto()+"';"
            );
            res = cst.executeUpdate();
        } catch (SQLException e) {
            logger.error("[ERROR] setProductos SQLException: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] setProductos: " + e.getMessage());
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
        return res;
    }
    
}
