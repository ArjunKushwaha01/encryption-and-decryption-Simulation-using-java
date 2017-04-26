
package classes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class print_class {
    private JTable table;
    private String table_title;
    int table_position = 0;
    private String file;
    int kol_total = 0;
    
    public void setSource(JTable table_name){
        this.table = table_name;
    }
    
    public void setReport_Title(String report_title){
        this.table_title = report_title;
    }
    
    public void setPosition(int position){
        this.table_position = position;
    }
    
    public void setFile(String file){
        this.file = file;
    }
    
    public void print_excel(){
        try{
            FileOutputStream fileOut = null;
            
            //Create workbook
            Workbook wb = new HSSFWorkbook();
            
            //Create Sheet
            Sheet sheet = wb.createSheet("new sheet");
            
            CellStyle style;
            style = wb.createCellStyle();
            style.setAlignment(CellStyle.VERTICAL_CENTER);
            
            Row titleRow = sheet.createRow(1);
            Cell titleCell = titleRow.createCell(table_position);
            titleCell.setCellValue(table_title);
            titleCell.setCellStyle(style);
            
            
            //Set title columns
            Row row = sheet.createRow(1);
            for (int i = 0; i < table.getColumnCount();i++){
               row.createCell(i).setCellValue(table.getColumnName(i));
            }
            
            int j = 2;
            int k = 2;
            
            int row_count = table.getRowCount()-1;
            for (j = 0; j <= row_count; j++){
                row = sheet.createRow(k);
                for (int i = 0; i < table.getColumnCount(); i++){
                    row.createCell(i).setCellValue(table.getValueAt(j, i).toString());
                }
                k = k +1;
            }

            
            //Set column width
            for (int i = 0;i < table.getColumnCount(); i++){
                sheet.autoSizeColumn(i);
            }
            sheet.setColumnWidth(0, 2000);
            String tempFile = file;
            File fileTemp = new File(tempFile);
            
            if (fileTemp.exists()){
                fileTemp.delete();
            }
            
            fileOut = new FileOutputStream(tempFile);
            wb.write(fileOut);
            fileOut.close();
            try{
                    Desktop.getDesktop().open(new File(file));
            }
            catch (Exception e){
                e.printStackTrace();
            
            }    
        }
        catch (Exception e){
                Logger.getLogger(print_class.class.getName()).log(Level.SEVERE,null, e);
        }
        
        
    
    }
    
    
}
