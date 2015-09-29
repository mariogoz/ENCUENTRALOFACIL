package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.BusquedaBusiness;
import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
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
@ViewScoped
public class BeanCategoriasProdEmpr implements Serializable {
     
    private MenuModel modelomenu;
    private List<FamiliaProdTO> familia;
    private FacesContext context;
    
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
            if (resultaBusqueda != null && !resultaBusqueda.isEmpty()) {
                System.out.println("asdas" + resultaBusqueda);

            } else {
                 }

        } catch (Exception e) {
            System.out.println(e.getCause());
            e.getMessage();
        }
        return resultaBusqueda;
    }
        
    public void crearMenu(){
         
        for(FamiliaProdTO fam: familia)
        {
            DefaultSubMenu primSubMenu =  new DefaultSubMenu(fam.getNomFam());
            //DefaultMenuItem item = new DefaultMenuItem(fam.getNomFam().toString());
            //primSubMenu.addElement(item);
            getModelomenu().addElement(primSubMenu);
        }
        
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
}
