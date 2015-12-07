/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Business;

import cl.encuentraloFacil.aplicacion.DAO.LoginDAO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.io.Serializable;

/**
 *
 * @author Mario
 */
public class LoginBusiness implements Serializable {

    private final LoginDAO loginDao = new LoginDAO();

    public LoginBusiness() {
    }

    public UsuarioTO getBuscarCliente(UsuarioTO user) {
        UsuarioTO res = null;
        try {
            res = loginDao.procedureAuntentificar(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    /*
     public String getInsertarCliente(int id,String rut, String dv, String nombre, String apellido,Date s, String correo){
     String res = null;
     try{
     res = loginDao.procedureRegistrarCliente(id,rut,dv,nombre,apellido,s,correo);
     }catch(Exception e){
     e.printStackTrace();
     throw new RuntimeException(e);
            
     }
     return res;
     }

     /**
     * @return the loginDao
     */

}
