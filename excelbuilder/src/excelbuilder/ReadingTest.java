/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Diego
 */
public class ReadingTest {

    public static void main(String[] args) {
        String fileName = "files/PLANILLAS_COM_201701.xlsx";
        String fileNameTemplate = "files/PLANILLAS_COM.xlsx";
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(fileNameTemplate));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Map<Integer, List<String>> data = new HashMap<>();
            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());
                for (Cell cell : row) {
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            String cellValue=cell.getRichStringCellValue().getString();
                            System.out.println(cellValue);
                            data.get(i).add(cellValue);                            
                            break;
                        case NUMERIC:
                            break;
                        case BOOLEAN:
                            break;
                        case FORMULA:
                            break;
                        case _NONE:
                            break;
                        case BLANK:
                            break;
                        case ERROR:
                            break;
                        default:
                            data.get(new Integer(i)).add(" ");
                    }
                }
                i++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadingTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadingTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadingTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
