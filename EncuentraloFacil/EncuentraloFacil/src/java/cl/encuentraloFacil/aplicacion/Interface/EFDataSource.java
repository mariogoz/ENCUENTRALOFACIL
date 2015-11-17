/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Interface;

import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Mario
 */
public interface EFDataSource  extends JRDataSource{
    public String getNombreReporte();
}
