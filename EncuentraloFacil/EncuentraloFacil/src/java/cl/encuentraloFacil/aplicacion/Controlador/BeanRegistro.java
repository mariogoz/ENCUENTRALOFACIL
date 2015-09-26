/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.TO.ClienteTO;
import java.io.Serializable;


/**
 *
 * @author Mario
 */
public class BeanRegistro implements Serializable{

    private ClienteTO cliente;
    /**
     * Creates a new instance of BeanRegistro
     */
    public BeanRegistro() {
    }
    
    public void registrarCliente() {
        
        setCliente(new ClienteTO());
        
        if(cliente.getRut() == 0){
            System.out.println("Exito");
        }

    }

    /**
     * @return the cliente
     */
    public ClienteTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }
    
}
