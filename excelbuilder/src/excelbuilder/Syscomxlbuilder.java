/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelbuilder;

//import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Diego
 */
public class Syscomxlbuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String fileName = "files/PLANILLAS_COM_201701.xlsx";
            String fileNameTemplate = "files/PLANILLAS_COM.xlsx";
            TemplatePart p1 = new TemplatePart();
            p1.setName("ejec_gtm");
            List<TemplatePart> lp = new ArrayList<>();
            lp.add(p1);
            Template tp = new Template(fileName, fileNameTemplate, lp);
//            XLbuilder xb = new XLbuilder(tp);
//            xb.build();
//            Gson gson=new Gson();
//            String message=gson.toJson(tp);
//            System.out.println(message);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Syscomxlbuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
