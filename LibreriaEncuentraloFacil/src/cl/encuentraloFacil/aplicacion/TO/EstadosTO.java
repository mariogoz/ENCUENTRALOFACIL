/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.TO;

/**
 *
 * @author MacBook Pro
 */
public class EstadosTO {
    private int idest;
    private String nomest;
    private String descest;

    public EstadosTO() {
    }

    public EstadosTO(int idest, String nomest, String descest) {
        this.idest = idest;
        this.nomest = nomest;
        this.descest = descest;
    }

    public int getIdest() {
        return idest;
    }

    public void setIdest(int idest) {
        this.idest = idest;
    }

    public String getNomest() {
        return nomest;
    }

    public void setNomest(String nomest) {
        this.nomest = nomest;
    }

    public String getDescest() {
        return descest;
    }

    public void setDescest(String descest) {
        this.descest = descest;
    }
   
}
