/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.EstadosDAO;
import cl.encuentraloFacil.aplicacion.TO.EstadosTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MacBook Pro
 */
public class EstadosBusiness {
    
    EstadosDAO eDao = new EstadosDAO();
    
    public List<EstadosTO> getEstadosDAO(){
        List<EstadosTO> listEstados = new ArrayList<EstadosTO>();
        try {
            listEstados = eDao.getBusquedaEstados();
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return listEstados;
    }
}
