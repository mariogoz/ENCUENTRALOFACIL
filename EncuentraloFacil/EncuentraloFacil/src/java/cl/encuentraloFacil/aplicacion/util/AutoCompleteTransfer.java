/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.util;

import cl.encuentraloFacil.aplicacion.Controlador.BeanBusqueda;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mario
 */
@FacesConverter("autoCompleteTransfer")
public class AutoCompleteTransfer implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        BeanBusqueda service;
        Integer val  = null;
        if (value != null && value.trim().length() > 0) {
            try {
                service = (BeanBusqueda) context.getExternalContext().getSessionMap().get("beanBusqueda");
                for (int x = 0; x < service.getProds().size(); x++) {
                    ProductoTO producto = service.getProds().get(x);
                    if (producto.getIdProducto().equals(Integer.parseInt(value))) {
                        val = x;
                        //return service.getProds().get(x);
                    }
                }
                return service.getProds().get(val);

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es valido Producto"));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((ProductoTO) value).getIdProducto());
        } else {
            return null;
        }
    }

}
