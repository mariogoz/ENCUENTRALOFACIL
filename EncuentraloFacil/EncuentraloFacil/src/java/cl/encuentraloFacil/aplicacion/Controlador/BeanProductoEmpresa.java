/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.ProductoBusiness;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@ManagedBean
@Named(value="beanProductoEmpresa")
@ViewScoped
public class BeanProductoEmpresa implements Serializable{

    private List<ProductoTO> listadoProd;
    
    private ProductoTO producto = new ProductoTO();
    private ProductoTO producto_g = new ProductoTO();

    @PostConstruct
    public void init(){
        listadoProd = getProductos();
    }
    
    public List<ProductoTO> getProductos(){
        ProductoBusiness ib = new ProductoBusiness();
        listadoProd = ib.getProductos();
        return listadoProd;
    }

    public void asociar_producto(){
        
    }
    
    public List<ProductoTO> getListadoProd() {
        return listadoProd;
    }

    public void setListadoProd(List<ProductoTO> listadoProd) {
        this.listadoProd = listadoProd;
    }

    public ProductoTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoTO producto) {
        this.producto = producto;
    }

    public ProductoTO getProducto_g() {
        return producto_g;
    }

    public void setProducto_g(ProductoTO producto_g) {
        this.producto_g = producto_g;
    }
    
}
