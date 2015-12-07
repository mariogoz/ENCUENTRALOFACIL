/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.ClienteTO;

import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class LoginDAO implements Serializable {

    final static Logger logger = Logger.getLogger(LoginDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;

    public LoginDAO() {

    }

    public UsuarioTO procedureAuntentificar(UsuarioTO user) {
        logger.info("METHOD procedureAuntentificar");
        StringBuilder sql = new StringBuilder();
        UsuarioTO usuario = new UsuarioTO();
        try {
            sql.append("SELECT ");
            sql.append("idus IDUSUARIO, ");
            sql.append("usernom NOMBREUSUARIO, ");
            sql.append("cod CONTRASENIA, ");
            sql.append("fecrea FECHACREACION, ");
            sql.append("fevalha FECHAVALIDA, ");
            sql.append("Estados_idest IDESTADO, ");
            sql.append("is_admin ADMIN  ");
            sql.append("FROM USUARIO ");
            sql.append("WHERE ");
            sql.append("usernom = ? ");
            sql.append("AND ");
            sql.append("cod = ? ");
            logger.info("sql :" + sql.toString());
            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
            cst = conn.getConnection().prepareStatement(sql.toString());
            //se cargan los parametros de entrada
            cst.setString(1, user.getUserName());//Tipo String
            cst.setString(2, user.getPassword());//Tipo String
            logger.info("username :" + user.getUserName());

            rs = cst.executeQuery();

            while (rs.next()) {

                usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
                usuario.setFecCrea(rs.getDate("FECHACREACION"));
                usuario.setFecVal(rs.getDate("FECHAVALIDA"));
                usuario.setEstadoId(rs.getInt("IDESTADO"));
                usuario.setAdmin(rs.getInt("ADMIN"));

                String n = rs.getString("NOMBREUSUARIO");
                String p = rs.getString("CONTRASENIA");

                if (user.getUserName().equals(n) && user.getPassword().equals(p)) {
                    usuario.setGlosaConexion("EXITO");
                }
            }
            if (usuario.getGlosaConexion() == null) {
                usuario.setGlosaConexion("DATOS RRONEOS");
            }
            logger.info("Conexion :" + usuario.getGlosaConexion());
        } catch (SQLException e) {
            logger.error("[ERROR] procedureAuntentificar SQLException: " + e.getMessage());
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
                logger.error("[ERROR] procedureAuntentificar close connection: " + ex.getMessage());
            }
        }

        return usuario;
    }

    public Integer isNewRut(ClienteTO cliente) {
        Integer respuesta = 0;
        try {
            cst = conn.getConnection().prepareStatement(" SELECT count(*) existe from cliente where rut = ? AND dv = '?; ");
            cst.setInt(1, cliente.getRut());
            cst.setString(2, String.valueOf(cliente.getDv()));

            while (rs.next()) {
                respuesta = rs.getInt("existe");
            }

        } catch (SQLException ex) {
            logger.info("[ERROR] isNewRut : " + ex.getMessage());
        } catch (Exception e) {
            logger.info("[ERROR NO IDENTIFICADO] isNewRut : " + e.getMessage());
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
                logger.info("[ERROR] isNewRut : " + ex.getMessage());
            }
        }
        return respuesta;
    }
}
