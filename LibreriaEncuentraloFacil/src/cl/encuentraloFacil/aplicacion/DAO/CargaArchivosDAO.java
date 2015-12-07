/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.DAO;

import cl.encuentraloFacil.aplicacion.Conexion.Conexion;
import cl.encuentraloFacil.aplicacion.TO.ResultadoCargaTO;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class CargaArchivosDAO implements Serializable{
    final static Logger logger = Logger.getLogger(CargaArchivosDAO.class);
    Conexion conn = new Conexion();
    PreparedStatement cst = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ResultadoCargaTO> cargarDatos(List<Integer> prod) {
        logger.info("METHOD cargarDatos");
        List<Integer> productos = new ArrayList<>();
        List<String> querys = new ArrayList<>();
        List<Integer> repetidos = new ArrayList<>();
        List<ResultadoCargaTO> resultadoCarga = new ArrayList<>();
        int sizeRs = 0;
        int contador = 0;
        try {
            //Es necesario tomar la variable id empresa para pasarla como parametro
            cst = conn.getConnection().prepareStatement("select * from producto_empresa where empresa_idemp = 15");
            rs = cst.executeQuery();
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
                        ResultadoCargaTO resultcarNoOk = new ResultadoCargaTO();
                        resultcarNoOk.setId_prod(prodbd);
                        resultcarNoOk.setResultado("Repetido");
                        resultadoCarga.add(resultcarNoOk);
                        cont = 0;
                    }
                    if (sizeRs == cont) {
                        productos.add(prod.get(x));
                        System.out.println("Nuevo " + prod.get(x));
                        ResultadoCargaTO resultcarOk = new ResultadoCargaTO();
                        resultcarOk.setId_prod(prod.get(x));
                        resultcarOk.setResultado("Agregado");
                        resultadoCarga.add(resultcarOk);
                    }
                }
            }

            ps = conn.getConnection().prepareStatement("insert into producto_empresa values(?,?);");
            for (Integer prod1 : productos) {
                ps.setInt(1, Integer.parseInt(prod1.toString()));
                //Es necesario tomar la variable id empresa para pasarla como parametro
                ps.setInt(2, 15);
                ps.addBatch();
                contador++;
                ps.executeBatch();
            }
        } catch (SQLException e) {
            logger.error("[ERROR] cargarDatos SQLException: " + e.getMessage());
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
                logger.error("[EMPRESADAO] cargarDatos FINALLY: " + ex.getMessage());
            }
        }
        return resultadoCarga;
    }
}
