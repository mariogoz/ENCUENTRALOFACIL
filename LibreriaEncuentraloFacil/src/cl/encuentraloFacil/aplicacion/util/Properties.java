/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.encuentraloFacil.aplicacion.util;

import java.util.ResourceBundle;

/**
 *
 * @author Mario
 */
public final class Properties {
    
    private static java.util.ResourceBundle properties;
    private static final String CONFIG_PATH = "message";
    
    public synchronized static String getProperty(String llave){
        if(properties == null){
            Properties.reload();
        }
        String value = properties.getString(llave);
        assert value != null : "Propiedad : \"" + llave + "\" no encontrada";
        return value;
    }
    
    public synchronized static void reload(){
        try{
            properties = ResourceBundle.getBundle(CONFIG_PATH);
        }catch (Exception e){
            properties = null;
            throw new RuntimeException(e);
        }
        
    }
    
}
