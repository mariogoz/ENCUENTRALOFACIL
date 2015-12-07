/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.AlertaTO;
import cl.encuentraloFacil.aplicacion.util.Constantes;
import java.io.Serializable;
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
public class IntranetDAO implements Serializable {

    final static Logger logger = Logger.getLogger(IntranetDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    ResultSet rs = null;

    public List<AlertaTO> getAlert() {
        logger.info("Method getAlert");
        AlertaTO alerta;
        List<AlertaTO> value = new ArrayList();
        try {
            cst = conn.getConnection().prepareCall("SELECT c.rut RUT, c.dv DV, c.nomb NOMBRES, c.apells APELLIDOS,c.Correo CORREO, u.idus IDUSUARIO from usuario u, cliente c  \n"
                    + "WHERE u.id_cliente = c.idclie \n"
                    + "AND u.estados_idest   = ? ; ");
            cst.setInt(1, Constantes.Estados.INACTIVO);
           rs = cst.executeQuery();
            
            while (rs.next()) {
                alerta = new AlertaTO();
                alerta.setRut(rs.getInt("RUT"));
                alerta.setDv(rs.getString("DV"));
                alerta.setNombres(rs.getString("NOMBRES"));
                alerta.setApellidos(rs.getString("APELLIDOS"));
                alerta.setCorreo(rs.getString("CORREO"));
                alerta.setIdUsuario(rs.getInt("IDUSUARIO"));
                
                value.add(alerta);
                logger.info("alerta agregada : " + alerta.toString());
            }
            logger.info("Cantidad Alertas : " + value.size());
        } catch (SQLException e) {
            logger.error("[ERROR] getAlert :" + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] getAlert :" + e.getMessage());
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
                logger.error("[ERROR] getAlert : " + ex.getMessage());
            }
        }
        return value;
    }
}
