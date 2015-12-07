/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Mario
 */
@FacesValidator("rutValidor")
public class ValidarRut implements Validator{
    @Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
            
	  String rut =  value.toString();
          if (rut == null || rut.isEmpty() ) {
			return;
	  }
          
          String replace = rut.replaceAll("\\.", "");
          String[] valores = replace.split("-");
          Integer valorRut = Integer.parseInt(valores[0]);
          char dv = valores[1].charAt(0);
          


          if (!ValidarRut(valorRut,dv)) { 
              
//              FacesContext.getCurrentInstance().validationFailed();
//              ((UIInput) component).setValid(false);
//              FacesContext.getCurrentInstance().addMessage(component.getClientId(context), msg);
                  throw new ValidatorException(new FacesMessage( "Rut Invalido"));
          }
	}
        
    public static boolean ValidarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }
}
