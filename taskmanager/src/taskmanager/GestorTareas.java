/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author floresdi
 */

public class GestorTareas extends Thread implements IGestorTareas{
    ListaEjecucion listaEjecucion;
    int maxThreads=3; //obtener este valor de las propiedades en archivo de configuracion
    Map<Integer, HiloEjecutorTarea> pilaEjecucion = new HashMap<>();
    boolean mRun=true;

    public GestorTareas() {
        this.listaEjecucion= new ListaEjecucion();
    }
    
    public void agregarTarea(Tarea tarea){
        listaEjecucion.agregar(tarea);
    }
    
    public boolean disponible(){
        boolean disponible=false;
        if(pilaEjecucion.size()<maxThreads){
            disponible=true;
        }
        return disponible;
    }
    
    public void cancelarEjecucion(int idTarea){
        
    }
    
    public void ejecutar(Tarea tarea){
        HiloEjecutorTarea hiloEjecutor=new HiloEjecutorTarea(tarea,this);
        int idTarea=tarea.getIdTarea();
        pilaEjecucion.put(idTarea, hiloEjecutor);
        hiloEjecutor.start();
    }
    
    @Override
    public void inicioTarea(Tarea tarea){
        registrarInicioTarea(tarea);
    }
    
    private void registrarInicioTarea(Tarea tarea){
        
    }
    
    @Override
    public void finTarea(Tarea tarea){
        pilaEjecucion.remove(tarea.getIdTarea());
        registrarFinTarea(tarea);
    }
    
    private void registrarFinTarea(Tarea tarea){
        
    }
    
    public int verificarEstado(int idTarea){
        return Estados.INICIO;
    }

    @Override
    public void run() {
        while(mRun){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(disponible()){
                Tarea tarea=listaEjecucion.sacar();
                if(tarea!=null){
                    ejecutar(tarea);
                    mRun=false;
                }                
            }
            
        }
    }

    @Override
    public void errorTarea(Tarea tarea,String message) {
        
    }
    
    public void detener(){
        mRun=false;
    }
}
