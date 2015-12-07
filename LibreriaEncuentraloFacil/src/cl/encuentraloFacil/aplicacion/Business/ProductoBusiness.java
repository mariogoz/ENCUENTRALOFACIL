/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.ProductoDAO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MacBook Pro
 */
public class ProductoBusiness {
    
    ProductoDAO iDao = new ProductoDAO();
    
    
    public int setProductoBusiness(ProductoTO producto){
        int res = 0;
        try {
            res = iDao.setProducto(producto);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        
        return res;
    }
    
    public int updProductoBusiness(ProductoTO producto){
        int res = 0;
        try {
            res = iDao.updProducto(producto);
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        
        return res;
    }
    
    public List<ProductoTO> getProductos(){
        List<ProductoTO> listProductos = new ArrayList<ProductoTO>();
        try {
            listProductos = iDao.getProductos();
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return listProductos;
    }
    
}
