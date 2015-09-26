/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.TO;

import java.io.Serializable;

/**
 *
 * @author indispost
 */
public class EmpresaGeoTO implements Serializable{
    
    private int idem;
    private double lat;
    private double lng;
    private String direc;
    private String producto; 
    private String link;
    private String image;
    private String nombre;
    private int idprod;

    /**
     * @return the idem
     */
    public int getIdem() {
        return idem;
    }

    /**
     * @param idem the idem to set
     */
    public void setIdem(int idem) {
        this.idem = idem;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * @return the direc
     */
    public String getDirec() {
        return direc;
    }

    /**
     * @param direc the direc to set
     */
    public void setDirec(String direc) {
        this.direc = direc;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the idprod
     */
    public int getIdprod() {
        return idprod;
    }

    /**
     * @param idprod the idprod to set
     */
    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }
    
}
