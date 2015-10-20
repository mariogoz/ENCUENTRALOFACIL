/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.SUCCESS_NO_INFO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class CargaArchivosDAO {

    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    private Object FacesContext;

    public List<Integer> cargarDatos(List<Integer> prod) {
        List<Integer> productos = new ArrayList<>();
        List<String> querys = new ArrayList<>();
        List<Integer> repetidos = new ArrayList<>();
        int sizeRs = 0;
        int contador = 0;
        try {
            //Es necesario tomar la variable id empresa para pasarla como parametro
            cst = conn.getConnection().prepareStatement("select * from producto_empresa where empresa_idemp = 15");
            ResultSet rs = cst.executeQuery();
            rs.last();
            sizeRs = rs.getRow();
            for (int x = 0; x < prod.size(); x++) {
                rs.beforeFirst();
                int cont = 0;
                while (rs.next()) {
                    int prodbd = rs.getInt(1);
                    cont++;
                    if (prodbd == prod.get(x)) {
                        repetidos.add(x);
                        System.out.println("Repetido " + prodbd);
                        cont = 0;
                    }
                    if (sizeRs == cont) {
                        productos.add(prod.get(x));
                        System.out.println("Nuevo " + prod.get(x));
                    }
                }
            }

            PreparedStatement ps = conn.getConnection().prepareStatement("insert into producto_empresa values(?,?);");
            for (Integer prod1 : productos) {
                ps.setInt(1, Integer.parseInt(prod1.toString()));
                //Es necesario tomar la variable id empresa para pasarla como parametro
                ps.setInt(2, 15);
                ps.addBatch();
                contador++;
                ps.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repetidos;
    }
}
