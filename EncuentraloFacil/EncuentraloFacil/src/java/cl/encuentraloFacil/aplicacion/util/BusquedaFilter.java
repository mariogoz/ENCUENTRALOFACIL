/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.util;

import cl.encuentraloFacil.aplicacion.Controlador.BeanBusqueda;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mario
 */
public class BusquedaFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
          
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        BeanBusqueda beanLogin =  (BeanBusqueda) ((session != null) ? session.getAttribute("beanBusqueda") : null);
        String loginURI = request.getContextPath() + "/faces/ViewBusqueda.xhtml";

        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

        if (beanLogin != null ||loginRequest || resourceRequest) {
             FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewBusqueda.xhtml");
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
       
    }
    
}
