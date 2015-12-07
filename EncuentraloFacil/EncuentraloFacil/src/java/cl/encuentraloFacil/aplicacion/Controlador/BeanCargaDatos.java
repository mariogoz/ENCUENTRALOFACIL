/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.Controlador;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/**
 *
 * @author Mario
 */
@ManagedBean
public class BeanCargaDatos implements Serializable{
    
    public void cargarDatos(){
        try {

            FileInputStream file = new FileInputStream(new File("C:/Users/Mario/Desktop/Carga_masiva_productos.xls"));

            //Get the workbook instance for XLS file 
            HSSFWorkbook workbook = new HSSFWorkbook(file);

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
                            Integer prod_temp = (int)cell.getNumericCellValue();
                            Integer value = null;
                            Boolean es = true;
                            for(int x = 0; x < prod.size(); x++){
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
                System.out.println("");
            }

        } catch(Exception e)
        {
           e.printStackTrace();
        }
                

}
}
