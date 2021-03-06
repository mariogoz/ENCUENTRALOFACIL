/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.BusquedaBusiness;
import cl.encuentraloFacil.aplicacion.TO.*;
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
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 * @version 1.1
 * @author indispost
 */
@ManagedBean
@SessionScoped
public class mapbean implements Serializable {

    
    
    private final double distance = 2800;
    //private final double distance_meters = distance * 1852;
    private String centerCoords = "";
    //FIX: se agrego final 
    private final MapModel mapModel = new DefaultMapModel();
    private Marker marker;
    private int idemp;
    private List<String> idempresa;
    private List<EmpresaGeoTO> listaIdEmpresa;
    private String idEmpresaBuscar;
    private BusquedaBusiness busquedaBusiness;
    private String nomEmpresa;
    private FacesMessage msg;
    private FacesContext context;
    private Object value;
    private EmpresaGeoTO emprsaGeoTO = new EmpresaGeoTO();
    private TreeNode root;
    private int idprod;
    private String nombreEmpresa;
    private BusquedaTO busquedaObjeto;
    
    public mapbean() {
        ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
        BeanBusqueda referenciaBeanSession = (BeanBusqueda) contexto.getSessionMap().get("beanBusqueda");
        List<EmpresaGeoTO> resultadoFinal = referenciaBeanSession.getResFinal();
        String centrarMapa = referenciaBeanSession.getEmprego().getLat() + "," + referenciaBeanSession.getEmprego().getLng();
        LatLng latLngYO = new LatLng(referenciaBeanSession.getEmprego().getLat() , referenciaBeanSession.getEmprego().getLng());
        setIdprod(referenciaBeanSession.getIdprod());
        listaIdEmpresa = new ArrayList<EmpresaGeoTO>();
        centerCoords = centrarMapa;

        for (EmpresaGeoTO empresa : resultadoFinal) {
            LatLng ll = new LatLng(empresa.getLat(), empresa.getLng());
            Marker markers = new Marker(ll, String.valueOf(empresa.getIdem())+", "+empresa.getNombre(), "imagen/" + empresa.getIdem() + ".png");
            markers.setIcon("imagen/marcador_carro_rojo.png");
            mapModel.addOverlay(markers);
        }
                    Marker markerYO = new Marker(latLngYO,"YO :)");
            markerYO.setIcon("imagen/persona_roja.png");
        
            mapModel.addOverlay(markerYO);

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        String datos[] = getMarker().getTitle().split(",");
        setIdEmpresaBuscar(datos[0]);
        setValue(getMarker().getData());    
        setNombreEmpresa(datos[1]);
    }

    public void redireccionarResultado() {
        try {
            int valor = 0;
            valor = Integer.parseInt(getIdEmpresaBuscar());
            //empresa = getBuscarEmpresaDatos()
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewResultado.xhtml");
        } catch (IOException e) {
            System.out.println(e.getCause());
            e.getMessage();
        }
    }

    public List<BusquedaTO> getEjecutarBusqueda() {
            busquedaBusiness = new BusquedaBusiness();
        context = FacesContext.getCurrentInstance();
        int x = Integer.parseInt(getIdEmpresaBuscar());
        List<BusquedaTO> resultaBusqueda = new ArrayList<BusquedaTO>();
        try {
            
            resultaBusqueda = getBusquedaBusiness().getBusquedaProductoBusiness(x, getIdprod());
            if (resultaBusqueda != null && !resultaBusqueda.isEmpty()) {
                //setNomEmpresa(resultaBusqueda.get(0).getEmpresa().getNombreEmpresa());
                setBusquedaObjeto(resultaBusqueda.get(0));
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Properties.getProperty("beanBusqueda.buscarProducto.noregistros"), null);
                context.addMessage(null, msg);
            }

        } catch (Exception e) {
            System.out.println(e.getCause());
            e.getMessage();
        }
         return resultaBusqueda;
    }

   
    /*
     public String bundle(){
     FacesContext facesContext = FacesContext.getCurrentInstance();
     String messageBundleName = facesContext.getApplication().getMessageBundle();
     Locale locale = facesContext.getViewRoot().getLocale();
     ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
     return "";
     }
     */
    /**
     * @return the idemp
     */
    public int getIdemp() {
        return idemp;
    }

    /**
     * @param idemp the idemp to set
     */
    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    /**
     * @return the idempresa
     */
    public List<String> getIdempresa() {
        return idempresa;
    }

    /**
     * @param idempresa the idempresa to set
     */
    public void setIdempresa(List<String> idempresa) {
        this.idempresa = idempresa;
    }

    /**
     * @return the listaIdEmpresa
     */
    public List<EmpresaGeoTO> getListaIdEmpresa() {
        return listaIdEmpresa;
    }

    /**
     * @param listaIdEmpresa the listaIdEmpresa to set
     */
    public void setListaIdEmpresa(List<EmpresaGeoTO> listaIdEmpresa) {
        this.listaIdEmpresa = listaIdEmpresa;
    }

    /**
     * @return the prueba
     */
    public String getIdEmpresaBuscar() {
        return idEmpresaBuscar;
    }

    /**
     * @param idEmpresaBuscar the prueba to set
     */
    public void setIdEmpresaBuscar(String idEmpresaBuscar) {
        this.idEmpresaBuscar = idEmpresaBuscar;
    }

    /**
     * @return the busquedaBusiness
     */
    public BusquedaBusiness getBusquedaBusiness() {
        return busquedaBusiness;
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

    public MapModel getMapModel() {
        return mapModel;
    }

    public String getCenterCoords() {
        return centerCoords;
    }

    /**
     * @return the marcador
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the emprsaGeoTO
     */
    public EmpresaGeoTO getEmprsaGeoTO() {
        return emprsaGeoTO;
    }

    /**
     * @param emprsaGeoTO the emprsaGeoTO to set
     */
    public void setEmprsaGeoTO(EmpresaGeoTO emprsaGeoTO) {
        this.emprsaGeoTO = emprsaGeoTO;
    }

    /**
     * @return the idprod
     */
    public int getIdprod() {
        return idprod;
    }

    /**
     * @param idprod the idprod to set
     */
    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the busquedaObjeto
     */
    public BusquedaTO getBusquedaObjeto() {
        return busquedaObjeto;
    }

    /**
     * @param busquedaObjeto the busquedaObjeto to set
     */
    public void setBusquedaObjeto(BusquedaTO busquedaObjeto) {
        this.busquedaObjeto = busquedaObjeto;
    }
}
