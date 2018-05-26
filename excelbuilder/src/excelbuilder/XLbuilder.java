/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Diego
 */
public class XLbuilder {

    //private final List<TemplatePart> listPart;
    private final Template listPart;
    XSSFWorkbook workbook;
//    XSSFSheet sheet;
    //Esto debe estar en un archivo de configuracion

    public XLbuilder(Template template) throws IOException, InvalidFormatException {
        this.listPart = template;
//        workbook = new XSSFWorkbook();
//        workbook = new XSSFWorkbook(new File(listPart.getNameFile()));
//        sheet = workbook.createSheet("Template Part 1");
    }

    private void copyTemplate() throws Exception {
        File source = new File(listPart.getNameTemplate());
        File dest = new File(listPart.getNameFile());
        Files.copy(source.toPath(), dest.toPath());
    }

    public void build() throws Exception {
        copyTemplate();
        List<Point> listPoints = getPoints();
        for (Point point : listPoints) {
            for (TemplatePart templatePart : listPart.getListPart()) {
                if (templatePart.getName().equals(point.getName())) {
                    buildTemplatePart(templatePart, point);
                }
            }
        }

        writeFile();
    }

    public List<Point> getPoints() throws Exception {
        List<Point> listPoint = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File(listPart.getNameFile()));
        workbook = new XSSFWorkbook(fileInputStream);
        int nSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < nSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            String cellValue = cell.getRichStringCellValue().getString();
                            Pattern pattern = Pattern.compile("^\\{\\{\\w+\\}\\}$");
                            Matcher matcher = pattern.matcher(cellValue);
                            if (matcher.find()) {
                                cellValue = cellValue.replaceAll("\\{\\{|\\}\\}", "");
                                if (cellValue.equals(listPart.getListPart().get(0).getName())) {
                                    Point p = new Point();
                                    p.setName(cellValue);
                                    p.setSheet(i);
                                    p.setRowNum(cell.getRowIndex());
                                    p.setColNum(cell.getColumnIndex());
                                    listPoint.add(p);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        fileInputStream.close();
        return listPoint;
    }

//    public void build() throws Exception {
//        copyTemplate();
//        FileInputStream fileInputStream = new FileInputStream(new File(listPart.getNameFile()));
//        workbook = new XSSFWorkbook(fileInputStream);
////        FileInputStream file = null;
//        for (TemplatePart templatePart : listPart.getListPart()) {
//            Sheet sheet = workbook.getSheetAt(0);
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    switch (cell.getCellTypeEnum()) {
//                        case STRING:
//                            String cellValue = cell.getRichStringCellValue().getString();
//                            Pattern pattern = Pattern.compile("^\\{\\{\\w*\\}\\}$");
//                            Matcher matcher = pattern.matcher(cellValue);
//                            if (matcher.find()) {
//                                cellValue = cellValue.replaceAll("\\{\\{|\\}\\}", "");
//                                if (cellValue.equals(listPart.getListPart().get(0).getName())) {
////                                    System.out.println("cell.getRowIndex(): " + cell.getRowIndex());
////                                    System.out.println("cell.getColumnIndex(): " + cell.getColumnIndex());
//                                    buildTemplatePart(templatePart, cell.getRowIndex(), cell.getColumnIndex());
//                                }
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//        }
//        fileInputStream.close();
//        writeFile();
//    }
//    private void buildTemplatePart(TemplatePart part, int rowNum, int colNum) {
//        for (Object[] datatype : part.getData()) {
//            Sheet sheet = workbook.getSheetAt(0);
//            Row row = sheet.getRow(rowNum);
//            if (row == null) {
//                row = sheet.createRow(rowNum);
//            }
//            for (Object field : datatype) {
//                Cell cell = row.getCell(colNum);
//                if (cell == null) {
//                    cell = row.createCell(colNum);
//                }
//                if (field instanceof String) {
//                    cell.setCellValue((String) field);
//                } else if (field instanceof Integer) {
//                    cell.setCellValue((Integer) field);
//                }
//                colNum++;
//            }
//            rowNum++;
//        }
//    }
    private void buildTemplatePart(TemplatePart part, Point point) {
        int colNum;
        int rowNum = point.getRowNum();
        Sheet sheet = workbook.getSheetAt(point.getSheet());
        for (Object[] datatype : part.getData()) {
            colNum = point.getColNum();
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            for (Object field : datatype) {
                Cell cell = row.getCell(colNum);
                if (cell == null) {
                    cell = row.createCell(colNum);
                }
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
                colNum++;
            }
            rowNum++;
        }
    }

    private void writeFile() throws Exception {
        FileOutputStream outputStream = new FileOutputStream(listPart.getNameFile());
        workbook.write(outputStream);
        workbook.close();
    }

}
