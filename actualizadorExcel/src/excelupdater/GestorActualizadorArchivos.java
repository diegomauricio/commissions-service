/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelupdater;

import java.util.HashMap;


/**
 *
 * @author floresdi
 */
public class GestorActualizadorArchivos {
    HashMap<Integer,HUpdater> listTask;

    public GestorActualizadorArchivos() {
        listTask=new HashMap<>();
    }    
    
    public GestorActualizadorArchivos(HashMap<Integer, HUpdater> listTask) {
        this.listTask = listTask;
    }
    public int addTareaActualizacion(HUpdater hupdater){
        hupdater.setGestor(this);
//        int idTarea=System.identityHashCode(hupdater);
        int idTarea=555;
        listTask.put(idTarea, hupdater);
        return idTarea;
    }
    
    public void starTareaActualizacion(int idTarea){
        HUpdater hupdater=listTask.get(idTarea);
        hupdater.start();                
    }
    
    public void tareaTerminada(HUpdater hupdater){
        hupdater.setEstado(Estados.TERMINADO);
    }
    
    public HUpdater getHupdater(int idTarea){
        return this.listTask.get(idTarea);
    }
}
