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
public class FamiliaProdTO implements Serializable{
    
    private int idFam;
    private String nomFam;

    /**
     * @return the idFam
     */
    public int getIdFam() {
        return idFam;
    }

    /**
     * @param idFam the idFam to set
     */
    public void setIdFam(int idFam) {
        this.idFam = idFam;
    }

    /**
     * @return the nomFam
     */
    public String getNomFam() {
        return nomFam;
    }

    /**
     * @param nomFam the nomFam to set
     */
    public void setNomFam(String nomFam) {
        this.nomFam = nomFam;
    }
}
