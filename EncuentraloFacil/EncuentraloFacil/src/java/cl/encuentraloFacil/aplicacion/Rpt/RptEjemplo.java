/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Rpt;

import cl.encuentraloFacil.aplicacion.Business.AutoAdminBusiness;
import cl.encuentraloFacil.aplicacion.Interface.EFDataSource;
import cl.encuentraloFacil.aplicacion.Interface.EFReporte;
import cl.encuentraloFacil.aplicacion.TO.EmpresaTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.UsuarioTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mario
 */
public class RptEjemplo implements EFReporte{
    private final AutoAdminBusiness adminBusiness = new AutoAdminBusiness();
    private EmpresaTO empresa;
    private List<ProductoTO> listProducEmp;
    DSEjemplo dataSource = null;
    public RptEjemplo(){}
    @Override
    public Map crearDataSources(Map parametros) {
        UsuarioTO respuesta =  (UsuarioTO) parametros.get("RESPUESTA");
        empresa = getValoresEmpresa(respuesta);
        parametros.put("TITULO", "TITULO EJEMPLO REPORTE");
        parametros.put("NOMBRE", "Mario Aaron Gonzalez Sanchez");
        parametros.put("URL", empresa.getUrl());
        parametros.put("IMG", empresa.getImagen());
        parametros.put("NOMBREEMPRESA",empresa.getNombreEmpresa());
        parametros.put("RUT",empresa.getRutEmp());
        parametros.put("DV",empresa.getDv());
        listProducEmp = adminBusiness.getProductoEmpresa(empresa);
        if (listProducEmp != null && !listProducEmp.isEmpty()) {
            parametros.put("DS_PRODUCTOS",dataSource = new DSEjemplo(listProducEmp));
           // dataSource = new DSEjemplo(listProducEmp);
        }
        return parametros;
    }

    @Override
    public Map crearImagenes() {
        return new HashMap();
    }

    @Override
    public EFDataSource obtenerDataSource() {
        DSEjemplo ds = dataSource;
        return ds;
    }
    
    private EmpresaTO getValoresEmpresa(UsuarioTO usuario){
        EmpresaTO empresaInfo = new EmpresaTO();
        empresaInfo = adminBusiness.getInfoEmpresa(usuario);
        return empresaInfo;
    }
    
}
