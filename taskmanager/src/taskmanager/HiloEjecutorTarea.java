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
public class HiloEjecutorTarea extends Thread implements IHandlerTarea{
    Tarea tarea;
    IGestorTareas listenerPila;

    @Override
    public void run() {
        try {
            System.out.println("Inicio hilo ejecucion tarea");
            this.tarea.setHandler(this);
            listenerPila.inicioTarea(tarea);
            tarea.iniciarTarea();
            //tarea.setEstado(Estados.EJECUTANDO);
            
            /*
                switch(tarea.getTipoTarea()){
                    case (TipoTarea.EJECUCION_PROCESO):
                        ejecutarProceso();                
                        break;
                    case (TipoTarea.ACTUALIZACION_ARCHIVO):
                        actualizarArchivo();
                        break;
                }
            */
            listenerPila.finTarea(tarea);            
        } catch (Exception e) {            
            tarea.setEstado(Estados.ERROR);
            listenerPila.errorTarea(tarea,e.getMessage());
        }
    }
    
//    public void actualizarArchivo(){
//        
//    }
//    
//    public void ejecutarProceso(){
//        ejecutarProcedimiento();
//        generarArchivosCSV();
//        generarControlesXLSX();
//    }
//    
//    public void ejecutarProcedimiento(){
//        
//    }
//    
//    public void generarArchivosCSV(){
//        
//    }
//    
//    public void generarControlesXLSX(){
//        
//    }
    
    public HiloEjecutorTarea(Tarea tarea, IGestorTareas listenerPila){        
        this.tarea=tarea;
        this.listenerPila=listenerPila;        
    }

    @Override
    public void onStartTarea() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onStopTarea() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onRestartTarea() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onUpdateEstadoTarea() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onEndTarea() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
