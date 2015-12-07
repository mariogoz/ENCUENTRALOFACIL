/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.IntranetBusiness;
import cl.encuentraloFacil.aplicacion.TO.AlertaTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class BeanIntranet implements Serializable {
    final static Logger logger = Logger.getLogger(BeanIntranet.class);
    IntranetBusiness intranet = new IntranetBusiness();
    private List<AlertaTO> tableAlertas = new ArrayList<AlertaTO>();

   @PostConstruct
    public void init(){
        try {
            tableAlertas =  getListAlerta();
        } catch (Exception e){
            logger.error("ERROR INIT :" + e.getMessage());
        }
      
    }
    public List<AlertaTO> getListAlerta() {
        List<AlertaTO> alert = new ArrayList();
        alert = intranet.getListAlert();
        return alert;
    }

    public void setTableAlertas(List<AlertaTO> tableAlertas) {
        this.tableAlertas = tableAlertas;
    }

    public List<AlertaTO> getTableAlertas() {
        return tableAlertas;
    }
}
