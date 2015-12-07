/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.TO;

/**
 *
 * @author Administrador
 */
public class SubFamiliaProdTO {
    
    
    private int idSubFam;
    private String nomSubFam;
    private String descest;
    private FamiliaProdTO familia = new FamiliaProdTO();
    
    /**
     * @return the idSubFam
    */
    public int getIdSubFam() {
        return idSubFam;
    }

    /**
     * @param idSubFam the idSubFam to set
     */
    public void setIdSubFam(int idSubFam) {
        this.idSubFam = idSubFam;
    }

    /**
     * @return the nomSubFam
     */
    public String getNomSubFam() {
        return nomSubFam;
    }

    /**
     * @param nomSubFam the nomSubFam to set
     */
    public void setNomSubFam(String nomSubFam) {
        this.nomSubFam = nomSubFam;
    }
    
    public String getDescest() {
        return descest;
    }

    public void setDescest(String descest) {
        this.descest = descest;
    }

    public FamiliaProdTO getFamilia() {
        return familia;
    }

    public void setFamilia(FamiliaProdTO familia) {
        this.familia = familia;
    }
    }
