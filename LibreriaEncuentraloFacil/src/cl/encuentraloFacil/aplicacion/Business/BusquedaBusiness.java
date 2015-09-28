/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.BusquedaDAO;
import cl.encuentraloFacil.aplicacion.TO.BusquedaTO;
import cl.encuentraloFacil.aplicacion.TO.EmpresaGeoTO;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class BusquedaBusiness implements Serializable{
    BusquedaDAO busquedaDAO = new BusquedaDAO();
    
    public List<BusquedaTO> getBusquedaProductoBusiness(int x){
        List<BusquedaTO> busquedaPro = new ArrayList<>();
        try {
            busquedaPro = busquedaDAO.getBusquedaProd(x);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return busquedaPro;
    }
    
    public List<EmpresaGeoTO> buscarProducto(String nomprod, double lat, double lng)
    {
        List<EmpresaGeoTO> resultadoFinal = new ArrayList<EmpresaGeoTO>();
        try{
        resultadoFinal = busquedaDAO.getEmpresas(nomprod, lat, lng);
        }catch(Exception e){
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return resultadoFinal;
    }
    
    public List<FamiliaProdTO> getBusquedaFamiliaEmpre(int x){
        List<FamiliaProdTO> listFamiliaEmpresa = new ArrayList<>();
        try {
            listFamiliaEmpresa = busquedaDAO.getBusquedaFamiliaEmpre(x);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return listFamiliaEmpresa;
    }
    
    public List<ProductoTO> retornaNomProd(String nombreprod) {
        List<ProductoTO> resultadoFinal = new ArrayList<ProductoTO>();
        try {
            resultadoFinal = busquedaDAO.autoComProd(nombreprod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoFinal;
    }
}
