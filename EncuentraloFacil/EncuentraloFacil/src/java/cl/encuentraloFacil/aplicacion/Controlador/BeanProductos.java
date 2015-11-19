/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import java.io.Serializable;

/**
 *
 * @author MacBook Pro
 */
public class BeanProductos implements Serializable {
    
    private ProductoTO producto;

    public BeanProductos() {}
    
    public void registrarProducto(){
        setProducto(new ProductoTO());
        if(producto.getIdProducto() == 0){
            System.out.println("Exito");
        }
    }
    
    public void setProducto(ProductoTO producto){
        this.producto = producto;
    }
    
    
    
}
