/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.TO;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class ResultadoCargaTO implements Serializable {
    private int id_prod;
    private String nomprod;
    private String resultado;

    /**
     * @return the id_prod
     */
    public int getId_prod() {
        return id_prod;
    }

    /**
     * @param id_prod the id_prod to set
     */
    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    /**
     * @return the nomprod
     */
    public String getNomprod() {
        return nomprod;
    }

    /**
     * @param nomprod the nomprod to set
     */
    public void setNomprod(String nomprod) {
        this.nomprod = nomprod;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}
