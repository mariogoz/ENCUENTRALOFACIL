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
        productos = prod;
        List<Integer> repetidos =  new ArrayList<>();
                
        int contador = 0;
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement("insert into producto_empresa values(?,?);");
            for (Integer prod1 : prod) {
                ps.setInt(1, Integer.parseInt(prod1.toString()));
                //Es necesario tomar la variable id empresa para pasarla como parametro
                ps.setInt(2, 15);
                ps.addBatch();
                contador++;
                if (contador >= 100 || contador == prod.size()) {
                    int[] numUpdates = ps.executeBatch();
                    for (int i = 0; i < numUpdates.length; i++) {
                        if (numUpdates[i] == SUCCESS_NO_INFO) {
                            System.out.println("Execution " + i
                                    + ": unknown number of rows updated");
                        } else {
                            System.out.println("Carga " + i + 1 + "exitosa: " + numUpdates[i] + " rows updated");
                        }
                        contador = 0;
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                cst = conn.getConnection().prepareStatement("select * from producto_empresa where empresa_idemp = 15");
                ResultSet rs =  cst.executeQuery();
                while(rs.next())
                {
                    int prodbd = rs.getInt(1);
                    for(int x = 0; x < prod.size(); x++)
                    {
                        if(prodbd == prod.get(x)){
                            repetidos.add(x);
                            System.out.println("Número ID_PRODUCTO "+prodbd+" repetido.");
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CargaArchivosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return repetidos;
    }
    
}
