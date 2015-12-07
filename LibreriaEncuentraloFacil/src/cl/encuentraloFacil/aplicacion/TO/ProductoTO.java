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
public class ProductoTO implements Serializable{
    private Integer idProducto;
    private String nombreProducto;
    private Double precio;
    private String img;
    private FamiliaProdTO familia =  new FamiliaProdTO();
    private SubFamiliaProdTO subfamilia =  new SubFamiliaProdTO();
    private EstadosTO estados =  new EstadosTO();

    
    public ProductoTO(){}

    public ProductoTO(Integer idProducto, String nombreProducto, Double precio, String img, FamiliaProdTO familia, SubFamiliaProdTO subfamilia, EstadosTO estados) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.img = img;
        //this.familia = familia;
        this.subfamilia = subfamilia;
        this.estados = estados;
    }

    public ProductoTO(Integer idProducto, String nombreProducto, Double precio, String img, SubFamiliaProdTO subfamilia) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.img = img;
        this.subfamilia = subfamilia;
    }
    
    
    public ProductoTO(Integer idProducto, String nombreProducto,Double precio,String img) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.img = img;
    }
    /**
     * @return the idProducto
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }
    
    public SubFamiliaProdTO getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(SubFamiliaProdTO subfamilia) {
        this.subfamilia = subfamilia;
    }

    public EstadosTO getEstados() {
        return estados;
    }

    public void setEstados(EstadosTO estados) {
        this.estados = estados;
    }
    
    
   
}
