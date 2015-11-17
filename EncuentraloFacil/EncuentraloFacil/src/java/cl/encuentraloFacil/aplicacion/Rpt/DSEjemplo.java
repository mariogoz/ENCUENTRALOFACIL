/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Rpt;

import cl.encuentraloFacil.aplicacion.Interface.EFDataSource;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.util.Constantes;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Mario
 */
public class DSEjemplo implements EFDataSource{
    List<ProductoTO> listProductoTO;
    int index = -1;
    
    public DSEjemplo(List<ProductoTO> producto){
        this.listProductoTO = producto;
    }
    
    @Override
    public String getNombreReporte() {
       return null;
    }

    @Override
    public boolean next() throws JRException {
        index++;
	return (index < listProductoTO.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object value = null;
	String nombre = jrf.getName();
        if(Constantes.ConstantesDSEjemplo.NOMBREPRODUCTO.equals(nombre)) {
            value = listProductoTO.get(index).getNombreProducto();
        } else if (Constantes.ConstantesDSEjemplo.PRECIO.equals(nombre)) {
            value = listProductoTO.get(index).getPrecio();
        } else if(Constantes.ConstantesDSEjemplo.RPTES_ULTIMA_PRO.equals(nombre)){
            value =  index == listProductoTO.size()-1;
	}
        return value;
    }
    
}
