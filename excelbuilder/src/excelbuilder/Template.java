/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excelbuilder;

import java.util.List;

/**
 *
 * @author Diego
 */
public class Template {
    private String nameFile;
    private String nameTemplate;
    private List<TemplatePart> listPart;

//    public Template(String nameFile, List<TemplatePart> listPart) {
//        this.nameFile = nameFile;
//        this.listPart = listPart;
//    }

    public Template(String nameFile, String nameTemplate, List<TemplatePart> listPart) {
        this.nameFile = nameFile;
        this.nameTemplate = nameTemplate;
        this.listPart = listPart;
    }

    
    
    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public List<TemplatePart> getListPart() {
        return listPart;
    }

    public void setListPart(List<TemplatePart> listPart) {
        this.listPart = listPart;
    }

    public String getNameTemplate() {
        return nameTemplate;
    }

    public void setNameTemplate(String nameTemplate) {
        this.nameTemplate = nameTemplate;
    }
    
    
    
}
