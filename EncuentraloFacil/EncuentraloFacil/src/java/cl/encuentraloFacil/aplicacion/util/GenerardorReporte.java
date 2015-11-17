/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.util;

import cl.encuentraloFacil.aplicacion.Interface.EFDataSource;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Mario
 */
public class GenerardorReporte {
    
    
    public void isDataSource(String nombre,Map mapaParametros,EFDataSource dataSource,HttpServletResponse response, String ruta) {
        if (dataSource == null) {
                generarReportePDF(nombre, mapaParametros, response,ruta);
            } else {
                generarReportePDF(nombre, mapaParametros, dataSource, response,ruta);
            }
    }
    /**
     *
     * @param name - identificador del reporte.
     * @param paramReporte - parametros del reporte.
     * @param response
     * @param ruta - ubicacion del reporte
     */
    public void generarReportePDF(String name, Map paramReporte, HttpServletResponse response, String ruta) {

        generarReportePDF(name, paramReporte, new JREmptyDataSource(1), response, ruta);
    }

    public void generarReportePDF(String name, Map paramReporte, JRDataSource jrDataSource, HttpServletResponse response, String ruta) {
        try {
            JasperPrint jasperPrint;

            jasperPrint = getJasperPrint(ruta, paramReporte, jrDataSource);
            byte[] archivo = JasperExportManager.exportReportToPdf(jasperPrint);
            if (archivo != null) {
                response.reset();
                response.setContentLength(archivo.length);
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "attachment; filename= " + name + ".pdf");
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

                bos.write(archivo);
                bos.flush();
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (JRException ex) {
            Logger.getLogger(GenerardorReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(GenerardorReporte.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private JasperPrint getJasperPrint(String ruta, Map paramReporte, JRDataSource jrDataSource) throws JRException {
        JasperPrint jasperPrint = null;

        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ruta);
        jasperPrint = JasperFillManager.fillReport(jasperReport, paramReporte, jrDataSource);

        return jasperPrint;
    }
}
