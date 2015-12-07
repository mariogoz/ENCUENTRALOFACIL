/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.EstadosBusiness;
import cl.encuentraloFacil.aplicacion.Business.FamiliaBusiness;
import cl.encuentraloFacil.aplicacion.Business.ProductoBusiness;
import cl.encuentraloFacil.aplicacion.Business.SubFamiliaBusiness;
import cl.encuentraloFacil.aplicacion.TO.EstadosTO;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.SubFamiliaProdTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author MacBook Pro
 */
@ManagedBean
@Named(value="beanProducto")
@SessionScoped
public class BeanProducto implements Serializable{

    private ArrayList<SelectItem> cmbFamilia = null;
    private ArrayList<SelectItem> cmbSubFamilia = null;
    private ArrayList<SelectItem> cmbEstados = null;
    
    private ArrayList<SelectItem> cmbFamilia_g = null;
    private ArrayList<SelectItem> cmbSubFamilia_g = null;
    private ArrayList<SelectItem> cmbEstados_g = null;
    
    private FamiliaProdTO familia =  new FamiliaProdTO();
    private SubFamiliaProdTO subfamilia =  new SubFamiliaProdTO();
    private EstadosTO estados = new EstadosTO();
    
    private ProductoTO producto = new ProductoTO();
    private ProductoTO producto_g = new ProductoTO();

    private List<ProductoTO> listadoProd;
    
    private final ProductoBusiness ib = new ProductoBusiness();
    
    private FacesMessage msg;
    private FacesContext context;
    
    public BeanProducto() {}
    
    /*
    Cargar Combobox de familia
    */
    public ArrayList<SelectItem> getCmbFamilia() {
        
        if(cmbFamilia==null){
            cmbFamilia = new ArrayList<SelectItem>();
            FamiliaBusiness fb = new FamiliaBusiness();
            
            SelectItem a = new SelectItem(0,"Seleccione");
            cmbFamilia.add(a);
            
            for(FamiliaProdTO f : fb.getFamiliaProductos()){
                SelectItem s = new SelectItem(f.getIdFam(), f.getNomFam());
                cmbFamilia.add(s);
            } 
        }
        
        return cmbFamilia;
    }
    
    /*
    Cargar Combobox de SubFamilia
    */
    public ArrayList<SelectItem> getCmbSubFamilia() {        
        cmbSubFamilia = new ArrayList<SelectItem>();
        SubFamiliaBusiness sfb = new SubFamiliaBusiness();

        SelectItem a = new SelectItem(0,"Seleccione");
        cmbSubFamilia.add(a);

        for(SubFamiliaProdTO sf : sfb.getSubFamiliaProductos(producto.getSubfamilia().getFamilia().getIdFam())){
            SelectItem s = new SelectItem(sf.getIdSubFam(), sf.getNomSubFam());
            cmbSubFamilia.add(s);
        } 

        return cmbSubFamilia;
    }
    
    /*
    Cargar Combobox de estados
    */
    public ArrayList<SelectItem> getCmbEstados() {
        
        if(cmbEstados==null){
            cmbEstados = new ArrayList<SelectItem>();
            EstadosBusiness eb = new EstadosBusiness();
            
            SelectItem a = new SelectItem(0,"Seleccione");
            cmbEstados.add(a);
            
            for(EstadosTO e : eb.getEstadosDAO()){
                SelectItem s = new SelectItem(e.getIdest(), e.getNomest());
                cmbEstados.add(s);
            }
        }
        
        return cmbEstados;
    }
    
    
    /*
    Cargar Combobox de familia
    */
    public ArrayList<SelectItem> getCmbFamilia_g() {
        
        if(cmbFamilia==null){
            cmbFamilia = new ArrayList<SelectItem>();
            FamiliaBusiness fb = new FamiliaBusiness();
            
            SelectItem a = new SelectItem(0,"Seleccione");
            cmbFamilia.add(a);
            
            for(FamiliaProdTO f : fb.getFamiliaProductos()){
                SelectItem s = new SelectItem(f.getIdFam(), f.getNomFam());
                cmbFamilia.add(s);
            } 
        }
        
        return cmbFamilia;
    }
    
    /*
    Cargar Combobox de SubFamilia
    */
    public ArrayList<SelectItem> getCmbSubFamilia_g() {        
        cmbSubFamilia = new ArrayList<SelectItem>();
        SubFamiliaBusiness sfb = new SubFamiliaBusiness();

        SelectItem a = new SelectItem(0,"Seleccione");
        cmbSubFamilia.add(a);

        for(SubFamiliaProdTO sf : sfb.getSubFamiliaProductos(producto_g.getSubfamilia().getFamilia().getIdFam())){
            SelectItem s = new SelectItem(sf.getIdSubFam(), sf.getNomSubFam());
            cmbSubFamilia.add(s);
        } 

        return cmbSubFamilia;
    }
    
    /*
    Cargar Combobox de estados
    */
    public ArrayList<SelectItem> getCmbEstados_g() {
        
        if(cmbEstados==null){
            cmbEstados = new ArrayList<SelectItem>();
            EstadosBusiness eb = new EstadosBusiness();
            
            SelectItem a = new SelectItem(0,"Seleccione");
            cmbEstados.add(a);
            
            for(EstadosTO e : eb.getEstadosDAO()){
                SelectItem s = new SelectItem(e.getIdest(), e.getNomest());
                cmbEstados.add(s);
            }
        }
        
        return cmbEstados;
    }
    
    /*
    Ingresar producto a BD
    */
    public void ingresarProducto(){
        
        context = FacesContext.getCurrentInstance();
        
        if(producto.getNombreProducto().equalsIgnoreCase("")){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se debe ingresar el nombre del producto");
            context.addMessage(null, msg);
            return;
        }
        
        if(producto.getPrecio() < 0){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se debe ingresar el nombre del producto");
            context.addMessage(null, msg);
            return;
        }
        
        if(producto.getSubfamilia().getFamilia().getIdFam() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe asociar el producto a una familia"));
            return;
        }
        
        if(producto.getSubfamilia().getIdSubFam()== 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe asociar el producto a una sub-familia"));
            return;
        }
        
        if(producto.getSubfamilia().getIdSubFam()== 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe asociar el producto a un estado"));
            return;
        }
        
        
        int res = 0;
        ProductoBusiness pb = new ProductoBusiness();
        res = pb.setProductoBusiness(producto);
        
        if(res>0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ingreso correctamente el producto"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hubo un problema al ingresar el producto"));
        }
    }
    
    
    public void actualizarProducto(RowEditEvent event){
        
        if(producto_g != null){
            int res = 0;
            ProductoBusiness pb = new ProductoBusiness();

            producto_g.setIdProducto(((ProductoTO) event.getObject()).getIdProducto());

            res = pb.updProductoBusiness(producto_g);

            if(res>0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ingreso correctamente el producto"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hubo un problema al ingresar el producto"));
            }
        }
    }
    
    public void setCmbSubFamilia(ArrayList<SelectItem> cmbSubFamilia) {
        this.cmbSubFamilia = cmbSubFamilia;
    }
    
    public void setCmbFamilia(ArrayList<SelectItem> cmbFamilia) {
        this.cmbFamilia = cmbFamilia;
    }
    
    public void setCmbEstados(ArrayList<SelectItem> cmbEstados) {
        this.cmbEstados = cmbEstados;
    }
    
    public FamiliaProdTO getFamilia() {
        return familia;
    }

    public void setFamilia(FamiliaProdTO familia) {
        this.familia = familia;
    }
    
    public ProductoTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoTO producto) {
        this.producto = producto;
    }
    
    public SubFamiliaProdTO getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(SubFamiliaProdTO subfamilia) {
        this.subfamilia = subfamilia;
    }
 
    public EstadosTO getEstados() {
        return estados;
    }

    public void setEstados(EstadosTO estados) {
        this.estados = estados;
    }
    
    public void getProductos(){
        ProductoBusiness ib = new ProductoBusiness();
        listadoProd = ib.getProductos();
    }
    
    public List<ProductoTO> getListadoProd() {
        return listadoProd;
    }

    public void setListadoProd(List<ProductoTO> listadoProd) {
        this.listadoProd = listadoProd;
    }
    
    public void actualizarProducto(){
        System.out.print("actualizar");
    }
    
    public void cancelar(){
        System.out.print("actualizar");
    }
    
    public ProductoTO getProducto_g() {
        return producto_g;
    }

    public void setProducto_g(ProductoTO producto_g) {
        this.producto_g = producto_g;
    }
    
}
