package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.TO.FamiliaProdTO;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 
@ManagedBean(name="arbolCatEmpr")
@ViewScoped
public class BeanCategoriasProdEmpr implements Serializable {
     
    private TreeNode root;
    private List<FamiliaProdTO> familia;
    private mapbean mapbean;
    private FacesContext context;
    
    @PostConstruct
    public void init() {
       /* context = FacesContext.getCurrentInstance();
        this.setFamilia(mapbean.getEjecutarBusquedaFamilia());
        int x;*/
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Node 0", root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);
         
        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
         
        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
         
        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
        root.getChildren().add(new DefaultTreeNode("Node 2"));
        
    }
 
    
    public void familias(List<FamiliaProdTO> familiaresultado){
        familia = familiaresultado;
    }
   
    public TreeNode getRoot() {
        return root;
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
}
