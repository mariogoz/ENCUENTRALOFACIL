/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;

import cl.encuentraloFacil.aplicacion.Business.CargaArchivoBusiness;
import cl.encuentraloFacil.aplicacion.TO.ProductoTO;
import cl.encuentraloFacil.aplicacion.TO.ResultadoCargaTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author FelipeSilva
 */
@ManagedBean
@SessionScoped
public class BeanCargarLeerDatos implements Serializable{

    private UploadedFile file;
    private CargaArchivoBusiness archivoBusiness;
    private List<Integer> listaCargaMulti;
    private List<ProductoTO> listaCargaMultiString;
    private List<ResultadoCargaTO> listaResultado;

    public void cargarLeerDatos(FileUploadEvent event) {

        try {

            if (event.getFile() != null) {
                file = event.getFile();
                //Get the workbook instance for XLS file 
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                HSSFWorkbook workbook = new HSSFWorkbook(file.getInputstream());

                //Get first sheet from the workbook
                HSSFSheet sheet = workbook.getSheetAt(0);

                //Iterate through each rows from first sheet
                Iterator<Row> rowIterator = sheet.iterator();

                List<Integer> prod = new ArrayList<Integer>();
                List<Integer> prodRep = new ArrayList<Integer>();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    //For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();

                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "\t\t");
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t\t");
                                Integer prod_temp = (int) cell.getNumericCellValue();
                                Integer value = null;
                                Boolean es = true;
                                for (int x = 0; x < prod.size(); x++) {
                                    value = prod.get(x);
                                    if (prod_temp.equals(value)) {
                                        es = false;
                                        prodRep.add(value);
                                        break;
                                    }
                                }
                                if (es) {
                                    prod.add(prod_temp);
                                    break;
                                }
                                break;
                            case Cell.CELL_TYPE_STRING:
                                System.out.print(cell.getStringCellValue() + "\t\t");
                                break;
                        }
                    }
                }
                archivoBusiness = new CargaArchivoBusiness();
                setListaResultado(archivoBusiness.retornaNomProd(prod)) ;
                FacesContext.getCurrentInstance().getExternalContext().redirect("cargaMasivaResultado.xhtml");
                /*if (prodRepBD.size()!= 0) {
                    generarExcel(prodRepBD);
                } else {

                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cargaDatosMulti(){
        this.getListaCargaMultiString();
    
    }
    public void generarExcel(List<Integer> rep) {
        byte[] xls = new byte[0];

        //Libro Excel
        HSSFWorkbook libro = new HSSFWorkbook();

        //Hoja Excel
        HSSFSheet hoja = libro.createSheet();

        // Se crea una fila dentro de la hoja
        HSSFRow fila = null;

        try {
            //cramos la celda
            HSSFCell celda = null;

            //Realizamos un ciclo para recorrer los arrelgos privados
            for (int i = 0; i < rep.size(); i++) {
                fila = hoja.createRow(i);

                //Nombre
                celda = fila.createCell(0);
                celda.setCellValue(rep.get(i));
            }

            libro.setSheetName(0, "Productos Repetidos");
            System.out.println(libro.getSheetName(0));
            //Obtenemos los bytes del Excel
            xls = libro.getBytes();

            //Generamos el Excel
            String contentType = "application/vnd.ms-excel";

            //Obtenemos el Contexto
            FacesContext fc = FacesContext.getCurrentInstance();

            //Obtenemos el Response
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

            response.setHeader("Content-disposition", "attachment; filename=" + "ProductosRepetidos");
            //Se indicamos el tipo de contenido
            response.setContentType(contentType);

            //Escribimos en el Response los bytes
            response.getOutputStream().write(xls); //Forzamos a la finalización y cierre de los objetos necesarios
            response.getOutputStream().flush();
            response.flushBuffer();
            fc.responseComplete();

        } catch (Exception e) {
            System.out.println("Error generando Excel.");
        }
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the listaResultado
     */
    public List<ResultadoCargaTO> getListaResultado() {
        return listaResultado;
    }

    /**
     * @param listaResultado the listaResultado to set
     */
    public void setListaResultado(List<ResultadoCargaTO> listaResultado) {
        this.listaResultado = listaResultado;
    }

    /**
     * @return the listaCargaMulti
     */
    public List<Integer> getListaCargaMulti() {
        return listaCargaMulti;
    }

    /**
     * @param listaCargaMulti the listaCargaMulti to set
     */
    public void setListaCargaMulti(List<Integer> listaCargaMulti) {
        this.listaCargaMulti = listaCargaMulti;
    }

    /**
     * @return the listaCargaMultiString
     */
    public List<ProductoTO> getListaCargaMultiString() {
        return listaCargaMultiString;
    }

    /**
     * @param listaCargaMultiString the listaCargaMultiString to set
     */
    public void setListaCargaMultiString(List<ProductoTO> listaCargaMultiString) {
        this.listaCargaMultiString = listaCargaMultiString;
    }
}
