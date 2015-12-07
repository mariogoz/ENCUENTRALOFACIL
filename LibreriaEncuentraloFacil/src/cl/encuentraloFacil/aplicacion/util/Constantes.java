/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.util;

/**
 *
 * @author Mario
 */
public class Constantes {
    
  public static class ConstantesDSEjemplo {
      public static final String NOMBREPRODUCTO = "NOMBREPRODUCTO";
      public static final String PRECIO = "PRECIO";
      public static final String RPTES_ULTIMA_PRO = "ES_ULTIMA_PRO";
  }
  
  public static class ConstantesBeanLogin {
      public static final Integer ISADMIN = 1;
  }
  
  public static class SQL {
      public static final Integer RESPUESTAVALIDA = 1;
  }
   
  public static class RegistrarUsuario{
      public static final Boolean INSERTEXITOSO = true;
      public static final Boolean INSERTFALLIDO = false;
  }
  
  public static class Estados{
      public static final Integer ACTIVO = 1;
      public static final Integer INACTIVO = 2;
      public static final Integer SUSPENDIDO = 3;
  }
  
  public static class TipoConexion {
      public static final Integer PUBLICO = 0;
      public static final Integer ADMIN = 1;
  }
            
}
