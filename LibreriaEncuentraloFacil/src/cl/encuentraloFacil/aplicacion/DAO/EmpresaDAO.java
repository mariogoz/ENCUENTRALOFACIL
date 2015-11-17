/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.EmpresaTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class EmpresaDAO {

    final static Logger logger = Logger.getLogger(EmpresaDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;

    /**
     * Obtiene datos de la empresa por el ID USUARIO LOGEADO EN EL SISTEMA
     *
     * @param usuario
     * @return
     */
    public EmpresaTO datosEmpresa(UsuarioTO usuario) {
        logger.info("METHOD datosEmpresa");
        StringBuilder sql = new StringBuilder();
        EmpresaTO empresa = new EmpresaTO();
        UsuarioTO usu = new UsuarioTO();
        try {
            sql.append("select usuario.fecrea fecha_creacion, ");
            sql.append("usuario.fevalha       fecha_validacion, ");
            sql.append("empresa.rutemp        rut_empresa,");
            sql.append("empresa.dvemp         dv_empresa, ");
            sql.append("empresa.nomb          nombre_empresa,");
            sql.append("empresa.urlemp        url_empresa, ");
            sql.append("empresa.img           img_empresa, ");
            sql.append("empresa.idemp         id_empresa ");
            sql.append("from usuario usuario, ");
            sql.append("empresa_has_usuario empresaUsuario, ");
            sql.append("empresa empresa ");
            sql.append("WHERE usuario.idus = empresaUsuario.Usuario_idus ");
            sql.append("AND empresaUsuario.Empresa_idemp = empresa.idemp ");
            sql.append("AND usuario.idus = ? ");
            //logger.info("sql : " + sql.toString());
            cst = conn.getConnection().prepareStatement(sql.toString());
            logger.info("value idUsuario: " + usuario.getIdUsuario()); 
            cst.setInt(1, usuario.getIdUsuario());

            rs = cst.executeQuery();

            while (rs.next()) {

                usu.setFecCrea(Date.valueOf(rs.getString("fecha_creacion")));
                if (rs.getString("fecha_validacion") != null) {
                    usu.setFecVal(Date.valueOf(rs.getString("fecha_validacion")));
                }
                empresa.setRutEmp(rs.getInt("rut_empresa"));
                empresa.setDv(rs.getString("dv_empresa"));
                empresa.setNombreEmpresa(rs.getString("nombre_empresa"));
                empresa.setUrl(rs.getString("url_empresa"));
                if (rs.getString("img_empresa") != null) {
                    empresa.setImagen(rs.getString("img_empresa"));
                }
                empresa.setIdEmpresa(rs.getInt("id_empresa"));
                empresa.setUsuario(usu);
                logger.info("{EmpresaTO} object : " + empresa);
            }
        } catch (SQLException s) {
            logger.error("[ERROR] datosEmpresa SQLException: " + s.getMessage());
        } catch (Exception s) {
            logger.error("[ERROR] datosEmpresa Exception: " + s.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                if (conn != null) {
                    conn.getConnection().close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[ERROR] datosEmpresa close connection: " + ex.getMessage());
            }
        }
        return empresa;
    }

    public List<ProductoTO> getProductoEmpresa(EmpresaTO empresa) {
        logger.info("METHOD getProductoEmpresa");
        ProductoTO producto;
        List<ProductoTO> listProductos = new ArrayList();
        try {
            cst = conn.getConnection().prepareStatement(" SELECT  "
                    + " producto.nomprod NOMBRE_PRODUCTO, "
                    + " producto.precio PRECIO "
                    + "from "
                    + "encuentralofacil.producto_empresa productoEmpresa, "
                    + "encuentralofacil.producto producto "
                    + "where "
                    + "productoEmpresa.producto_idproduc = producto.idproduc "
                    + "and "
                    + "productoEmpresa.empresa_idemp =  ? ");
            cst.setInt(1, empresa.getIdEmpresa());
            
            rs = cst.executeQuery();
            
            while (rs.next()) {
                producto = new ProductoTO();
                producto.setNombreProducto(rs.getString("NOMBRE_PRODUCTO"));
                producto.setPrecio(rs.getDouble("PRECIO"));
                logger.info("{EmpresaTO} object : " + producto);
                listProductos.add(producto);
            }
        } catch (SQLException ex) {
            logger.error("[EMPRESADAO] getProductoEmpresa SQL: " + ex.getMessage());
        } catch (Exception e) {
            logger.error("[EMPRESADAO] getProductoEmpresa : " + e.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                if (conn != null) {
                    conn.getConnection().close();
                } 
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                logger.error("[EMPRESADAO] getProductoEmpresa FINALLY: " + ex.getMessage());
            }
        }
        logger.info("{Producto} List returh: " + listProductos.size());
        return listProductos;
    }
}
