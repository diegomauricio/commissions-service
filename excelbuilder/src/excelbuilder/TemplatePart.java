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
public class TemplatePart {

    private Object[][] data;
    private String name;
    private String type;

    public TemplatePart() {
        this.name = "EJECUTIVOS GTM";
        this.type = "Table";

        Object[][] datatypes = {
            {"Ehumano", "Type", "Size(in bytes)"},
            {1341, "Primitive", 2},
            {1234, "Primitive", 4},
            {1455, "Primitive", 8},
            {7644, "Primitive", 1},
            {4563, "Non-Primitive", "No fixed size"}
        };
//        Object[][] datatypes = {
//            {"Ehumano", "Type", "Size(in bytes)"},
//            {1341, "Primitive", 2}
//        };
        this.data = datatypes;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
