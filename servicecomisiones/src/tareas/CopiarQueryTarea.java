/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareas;

import copier.Copiador;
import taskmanager.Tarea;

/**
 *
 * @author floresdi
 */
public class CopiarQueryTarea extends Tarea {

    Copiador copier;
    
    public CopiarQueryTarea(String nombre, int idTarea, int tipoTarea, Copiador copier) {
        super(nombre, idTarea, tipoTarea);
        this.copier=copier;
    }

    @Override
    public void start() {
        try {
            copier.iniciar();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
