
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.BusquedaBusiness;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.SubFamProductosTO;
import cl.encuentraloFacil.aplicacion.TO.SubFamiliaProdTO;
import java.awt.Menu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
 
@ManagedBean(name="MenuProdEmpre")
@SessionScoped
public class BeanCategoriasProdEmpr implements Serializable {
     
    private MenuModel modelomenu;
    private List<FamiliaProdTO> familia;
    private FacesContext context;
    private SubFamiliaProdTO SubFamilia;
    private List<SubFamProductosTO> productosBean ;
    @PostConstruct
    public void init() {
        familia = ejecutarBusquedaFamilia();
        setModelomenu(new DefaultMenuModel());
        crearMenu();
        
    }
    
    
    public List<FamiliaProdTO> ejecutarBusquedaFamilia() {
        mapbean map =  (mapbean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mapbean");
        BusquedaBusiness busquedaBusiness = new BusquedaBusiness();
        int x = Integer.parseInt(map.getIdEmpresaBuscar());
        List<FamiliaProdTO> resultaBusqueda = new ArrayList<FamiliaProdTO>();
        try {
            resultaBusqueda = busquedaBusiness.getBusquedaFamiliaEmpre(x);

        } catch (Exception e) {
            System.out.println(e.getCause());
            e.getMessage();
        }
        return resultaBusqueda;
    }
        
    public void crearMenu(){
         
        for(FamiliaProdTO fam: familia)
        {
            BusquedaBusiness busquedaBusiness = new BusquedaBusiness();
            DefaultSubMenu primSubMenu =  new DefaultSubMenu(fam.getNomFam());
            mapbean map =  (mapbean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mapbean");
            int idemp = Integer.parseInt(map.getIdEmpresaBuscar());
            List<SubFamiliaProdTO> resultaBusqueda = new ArrayList<SubFamiliaProdTO>();
            resultaBusqueda = busquedaBusiness.getBusquedaSubFamiliaEmpre(fam.getIdFam(),idemp);
            if(resultaBusqueda.size()>0)
            {
                for(SubFamiliaProdTO subFa: resultaBusqueda){
                DefaultMenuItem subitem = new DefaultMenuItem(subFa.getNomSubFam());
                subitem.setCommand("#{MenuProdEmpre.ejecutarBusquedaProductosPorSubFam("+subFa.getIdSubFam()+")}");
                primSubMenu.addElement(subitem);}
            }
                       
            getModelomenu().addElement(primSubMenu);
            
        }
        
    }
    
    
    //Método que ejecuta la busqueda de los productos según subFamilia seleccionada en el MenuItem de la página viewResultado.xhtml
     public List<SubFamProductosTO> ejecutarBusquedaProductosPorSubFam(int idsubfa) {
        mapbean map =  (mapbean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mapbean");
        BusquedaBusiness busquedaBusiness = new BusquedaBusiness();
        
        int idempresa = Integer.parseInt(map.getIdEmpresaBuscar());
        List<SubFamProductosTO> productos = new ArrayList<SubFamProductosTO>();
        try {
            //necesito capturar variable seleccionada de subfamilia para listar
            productos = busquedaBusiness.getBusquedaProductosSubfa(idempresa,idsubfa);
            productosBean = productos;
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewResultadoSubFamilia.xhtml");
        } catch (Exception e) {
            System.out.println(e.getCause());
            e.getMessage();
        }
        return productos;
    }
     
     public List<SubFamProductosTO> retornaListaProductos(){
         return this.productosBean;
     }
    
    public void familias(List<FamiliaProdTO> familiaresultado){
        familia = familiaresultado;
    }
   

    /**
     * @return the familia
     */
    public List<FamiliaProdTO> getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(List<FamiliaProdTO> familia) {
        this.familia = familia;
    }

    /**
     * @return the modelomenu
     */
    public MenuModel getModelomenu() {
        return modelomenu;
    }

    /**
     * @param modelomenu the modelomenu to set
     */
    public void setModelomenu(MenuModel modelomenu) {
        this.modelomenu = modelomenu;
    }

    /**
     * @return the SubFamilia
     */
    public SubFamiliaProdTO getSubFamilia() {
        return SubFamilia;
    }

    /**
     * @param SubFamilia the SubFamilia to set
     */
    public void setSubFamilia(SubFamiliaProdTO SubFamilia) {
        this.SubFamilia = SubFamilia;
    }

    /**
     * @return the productos
     */
    public List<SubFamProductosTO> getProductosSubFam() {
        return productosBean;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductosSubFam(List<SubFamProductosTO> productosBean) {
        this.productosBean = productosBean;
    }
}
