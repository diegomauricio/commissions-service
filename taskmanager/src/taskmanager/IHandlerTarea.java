/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

/**
 *
 * @author floresdi
 * Manipula las tareas
 */
public interface IHandlerTarea {
    public void onStartTarea();
    public void onStopTarea();    
    public void onRestartTarea();
    public void onUpdateEstadoTarea();    
    public void onEndTarea();
}
