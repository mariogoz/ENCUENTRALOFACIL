/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;

import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mario
 */
public class LoginDAO implements Serializable {

    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    public LoginDAO() {

    }

    public UsuarioTO procedureAuntentificar(UsuarioTO user) {

        
        String resultado = null;
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
            sql.append("Apells APELLIDOS, ");
            sql.append("nomb NOMBRES ");
            sql.append("FROM USUARIO ");
            sql.append("WHERE ");
            sql.append("usernom = ? ");
            sql.append("AND ");
            sql.append("cod = ? ");
            
            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
             cst = conn.getConnection().prepareStatement(sql.toString());
            //se cargan los parametros de entrada
            cst.setString(1, user.getUserName());//Tipo String
            cst.setString(2, user.getPassword());//Tipo String

            ResultSet rs = cst.executeQuery();

            while (rs.next()) {
                
                usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
                usuario.setFecCrea(rs.getDate("FECHACREACION"));
                usuario.setFecVal(rs.getDate("FECHAVALIDA"));
                usuario.setPrimerNombre(rs.getString("NOMBRES"));
                usuario.setPrimerApellido(rs.getString("APELLIDOS"));
                usuario.setEstadoId(rs.getInt("IDESTADO"));
                
                String n = rs.getString("NOMBREUSUARIO");
                String p = rs.getString("CONTRASENIA");


                if (user.getUserName().equals(n) && user.getPassword().equals(p)) {
                    usuario.setGlosaConexion("EXITO");
                } 
            }
            if (usuario.getGlosaConexion() == null) {
                usuario.setGlosaConexion("DATOS RRONEOS");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                } if (conn != null){
                     conn.getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } 
        }

        return usuario;
    }
}
