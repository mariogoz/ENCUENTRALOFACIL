/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.BusquedaBusiness;
import cl.encuentraloFacil.aplicacion.TO.BusquedaTO;
import cl.encuentraloFacil.aplicacion.TO.EmpresaGeoTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.util.Properties;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author indispost
 */
@ManagedBean
@SessionScoped
public class BeanBusqueda implements Serializable {

    private EmpresaGeoTO emprego = new EmpresaGeoTO();
    private BusquedaBusiness busquedaBusiness;
    private BusquedaTO selectedBusqueda;
    private String nomEmpresa;
    private String url;
    private List<EmpresaGeoTO> resFinal;
    private ProductoTO prod = new ProductoTO();
    List<ProductoTO> producto = new ArrayList<ProductoTO>();
    private FacesMessage msg;
    private FacesContext context;
    /**
     * Creates a new instance of BeanBusqueda
     */
    public BeanBusqueda() {
        busquedaBusiness = new BusquedaBusiness();
        ExternalContext contexto =  (ExternalContext) FacesContext.getCurrentInstance().getExternalContext();
        if (contexto.getSessionMap().get("mapbean") != null) {
            contexto.invalidateSession();
        } 
    }

    public void ejecutarBusqueda() {
             mapbean s = null ; 
        BusquedaBusiness bbuss = new BusquedaBusiness();
        setResFinal(null);
        List<EmpresaGeoTO> resultadoFinal = new ArrayList<EmpresaGeoTO>();
        context = FacesContext.getCurrentInstance();
        try {
            if (getProd().getNombreProducto() != null) {
                if (emprego.getLat() != 0.0 && emprego.getLng() != 0.0) {
                    resultadoFinal = bbuss.buscarProducto(getProd().getNombreProducto(), emprego.getLat(), emprego.getLng());
                    if (resultadoFinal != null && !resultadoFinal.isEmpty()) {                                
                                setResFinal(resultadoFinal);    
                                FacesContext.getCurrentInstance().getExternalContext().redirect("resultado.xhtml");
                    } else {
                        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanBusqueda.buscarProducto.noregistros"), null);
                        context.addMessage(null, msg);
                    }
                } else {
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanBusqueda.buscarProducto.geocalizacion"), null);
                    context.addMessage(null, msg);
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanBusqueda.buscarProducto.llenarCampos"), null);
                context.addMessage(null, msg);
            }

        } catch (IOException e) {
            System.out.println(e.getCause());
            e.getMessage();
        } 
 //         if (s != null) {
 //                                   FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
 //                               } 
    }
    
    public List<String> complete(String query) {
        prod.setNombreProducto("");
        BusquedaBusiness bbuss = new BusquedaBusiness();

        List<String> nomprod = new ArrayList<String>();
        try {
            producto = bbuss.retornaNomProd(query);
            /*if (producto != null && !producto.isEmpty()) {
             FacesContext.getCurrentInstance().getExternalContext().redirect("resultado.xhtml");
             System.out.println(producto.size());
             }else{
             */
            if (producto.size() > 0) {
                for (ProductoTO producto1 : producto) {
                    nomprod.add(producto1.getNombreProducto());
                }
            } else {
                nomprod.add("No se han encontrado coincidencias");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            e.getMessage();
        }

        return nomprod;
    }

    /**
     * @return the emprego
     */
    public EmpresaGeoTO getEmprego() {
        return emprego;
    }

    /**
     * @param emprego the emprego to set
     */
    public void setEmprego(EmpresaGeoTO emprego) {
        this.emprego = emprego;
    }

    /**
     * @return the selectedBusqueda
     */
    public BusquedaTO getSelectedBusqueda() {
        return selectedBusqueda;
    }

    /**
     * @param selectedBusqueda the selectedBusqueda to set
     */
    public void setSelectedBusqueda(BusquedaTO selectedBusqueda) {
        this.selectedBusqueda = selectedBusqueda;
    }

    /**
     * @return the nomEmpresa
     */
    public String getNomEmpresa() {
        return nomEmpresa;
    }

    /**
     * @param nomEmpresa the nomEmpresa to set
     */
    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the resFinal
     */
    public List<EmpresaGeoTO> getResFinal() {
        return resFinal;
    }

    /**
     * @param resFinal the resFinal to set
     */
    public void setResFinal(List<EmpresaGeoTO> resFinal) {
        this.resFinal = resFinal;
    }

    /**
     * @return the prod
     */
    public ProductoTO getProd() {
        return prod;
    }

    /**
     * @param prod the prod to set
     */
    public void setProd(ProductoTO prod) {
        this.prod = prod;
    }

}
