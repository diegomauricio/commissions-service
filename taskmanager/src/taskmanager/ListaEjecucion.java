/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author floresdi
 */
public class ListaEjecucion {
    List<Tarea> listTarea;

    public ListaEjecucion() {
        this.listTarea=new ArrayList<>();
    }
    
    public void agregar(Tarea tarea){
        listTarea.add(tarea);
    }
    public Tarea sacar(){
        Tarea tarea;
        if(listTarea.size()>0){
            tarea=listTarea.get(0);
            listTarea.remove(0);
        }else{
            tarea=null;
        }
        
        return tarea;
    }
    public boolean isEmpty(){
        return true;
    }
    
    public void ordenar(){
        
    }
}
