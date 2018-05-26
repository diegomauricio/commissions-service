/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

/**
 *
 * @author floresdi
 */
public interface IGestorTareas {
    public void inicioTarea(Tarea tarea);
    public void finTarea(Tarea tarea);
    public void errorTarea(Tarea tarea,String message);
    
}
