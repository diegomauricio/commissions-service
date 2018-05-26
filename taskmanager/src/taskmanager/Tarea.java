/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author floresdi
 */
public abstract class Tarea {
    private int estado;
    private String nombre;
    private int idTarea;
    private int tipoTarea;
    private Map<String, String> listParams = new HashMap<>();
    private IHandlerTarea handler;    

    public IHandlerTarea getHandler() {
        return handler;
    }

    public void setHandler(IHandlerTarea handler) {
        this.handler = handler;
    }

    public Tarea(String nombre, int idTarea, int tipoTarea) {        
        this.nombre = nombre;
        this.idTarea = idTarea;
        this.tipoTarea = tipoTarea;
    }

    public Tarea(String nombre, int idTarea, int tipoTarea, IHandlerTarea handler) {        
        this.nombre = nombre;
        this.idTarea = idTarea;
        this.tipoTarea = tipoTarea;
        this.handler = handler;
    }
    
    public int getEstado() {        
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(int tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public Map<String, String> getListParams() {
        return listParams;
    }

    public void setListParams(Map<String, String> listParams) {
        this.listParams = listParams;
    }
    
    public void iniciarTarea(){
        handler.onStartTarea();
        this.start();
        handler.onEndTarea();
    }
    public void detenerTarea(){        
        stop();
        handler.onStopTarea();
    }    
    
    public void reiniciarTarea(){
        handler.onRestartTarea();
        
    }
        
    public abstract void start();
    
    public abstract void stop();
    
    public abstract void updateState();
    
    public abstract void getState();
    
}
