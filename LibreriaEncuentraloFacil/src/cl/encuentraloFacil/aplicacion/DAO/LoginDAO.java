/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class LoginDAO implements Serializable {

    Conexion conn = new Conexion();

    public LoginDAO() {

    }

    public String procedureAuntentificar(UsuarioTO user) {

        
        String resultado = null;
        try {
            // se crea instancia a procedimiento, los parametros de entrada y salida se simbolizan con el signo ?
            CallableStatement proc = conn.getConnection().prepareCall("{call pro_auntenticar(?,?)}");
            //se cargan los parametros de entrada
            proc.setString("nombre", user.getUserName());//Tipo String
            proc.setString("pass", user.getPassword());//Tipo String

            ResultSet rs = proc.executeQuery();

            while (rs.next()) {

                user.setFecCrea(rs.getDate("fecrea"));
                user.setFecVal(rs.getDate("fevalha"));
                user.setPrimerNombre(rs.getString("nomb"));
                user.setPrimerApellido(rs.getString("Apells"));
                String n = rs.getString("usernom");
                String p = rs.getString("cod");


                if (user.getUserName().equals(n) && user.getPassword().equals(p)) {
                    resultado = "EXITO";
                } else {
                    resultado = "DATOSERRONEOS";
                }

            }

        } catch (SQLException e) {
            System.out.println("Error en la llamada al procedimiento");
            throw new RuntimeException(e);
        } finally {
            try {
                conn.getConnection().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return resultado;
    }
    
    public List<ProductoTO> getProcedureAutoComplete(String resultado){
        List<ProductoTO> producto = new ArrayList<ProductoTO>() {};
            try{
                
                CallableStatement cts = conn.getConnection().prepareCall("{call serchautocomplete(?)}");
                cts.setString("valor","%"+resultado+"%" );
                ResultSet rs = cts.executeQuery();
                while (rs.next()){
                    ProductoTO x = new ProductoTO();
                    x.setIdProducto(rs.getInt("idproduc"));
                    x.setNombreProducto(rs.getNString("nomprod"));
                    producto.add(x);
                }
                
            } catch(SQLException e){
                throw new RuntimeException(e);
            } finally {
            try {
                conn.getConnection().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return producto;
    }
    

}
