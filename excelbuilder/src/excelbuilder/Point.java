/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excelbuilder;

/**
 *
 * @author Diego
 */
public class Point {
    private String name;
    private int sheet;
    private int rowNum;
    private int colNum;

    public Point(){
        
    }
    
    public Point(String name, int sheet, int rowNum, int colNum) {
        this.name = name;
        this.sheet = sheet;
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSheet() {
        return sheet;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }
    
    
}
