/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Interface;

import java.util.Map;

/**
 *
 * @author Mario
 */
public interface EFReporte {
    public Map crearDataSources(Map parametros);
    public Map crearImagenes();
    public EFDataSource obtenerDataSource();
}
