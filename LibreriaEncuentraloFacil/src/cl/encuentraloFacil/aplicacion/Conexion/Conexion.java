/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Conexion;

/**
 *
 * @author Mario
 */
import cl.encuentraloFacil.aplicacion.util.Properties;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class Conexion implements Serializable {
    final static Logger logger = Logger.getLogger(Conexion.class);
    private DataSource datasource;
     BasicDataSource bds;
      Connection cnn = null;
    public Conexion() {
       bds = new BasicDataSource();
    }
    
    public Connection getConnection() {
        try {
            bds.setDriverClassName(Properties.getProperty("conexion.driver"));
            bds.setUrl(Properties.getProperty("conexion.url"));
            bds.setUsername(Properties.getProperty("conexion.user"));
            bds.setPassword(Properties.getProperty("conexion.password"));
            bds.setMaxTotal(100);
            bds.setMaxConnLifetimeMillis(30000);
            bds.setDefaultQueryTimeout(30);
            bds.setMaxWaitMillis(30000);
            bds.setMaxIdle(1);
            datasource = bds;
            
            cnn = datasource.getConnection();
            
        } catch (SQLException e) {
            logger.error("[ERROR] conexion: " + e.getMessage());
        } catch (Exception e) {
            logger.error("[ERROR] conexion: " + e.getMessage());
        }
        
        return cnn;   
    }
    
    // disconnect database
    public void disconnect() {
        if (cnn != null) {
            try {
                cnn.close();           
                cnn = null;
            } catch (SQLException e) {
                logger.error("[ERROR disconnect] :" + e.getMessage());
            }
        }
    }
}
