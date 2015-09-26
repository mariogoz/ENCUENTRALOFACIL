/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.encuentraloFacil.aplicacion.TO;

import java.io.Serializable;



/**
 *
 * @author Mario
 */
    public class BusquedaTO implements Serializable{
   private EmpresaTO empresa = new EmpresaTO();
   private ProductoTO producto = new ProductoTO();

    /**
     * @return the listProducto
     */
    public ProductoTO getProducto() {
        return producto;
    }

    
    public void setListProducto(ProductoTO producto) {
        this.producto = producto;
    }

    /**
     * @return the listEmpresa
     */
    public EmpresaTO getEmpresa() {
        return empresa;
    }

    
    public void setListEmpresa(EmpresaTO empresa) {
        this.empresa = empresa;
    }

   
}
