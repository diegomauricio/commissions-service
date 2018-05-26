/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copier;

/**
 *
 * @author floresdi
 */
public class ParCampos {    
   private String nombreCampoOrigen;
   private String nombreCampoDestino;

    public ParCampos(String nombreCampoOrigen, String nombreCampoDestino) {
        this.nombreCampoOrigen = nombreCampoOrigen;
        this.nombreCampoDestino = nombreCampoDestino;
    }

    public String getNombreCampoOrigen() {
        return nombreCampoOrigen;
    }

    public void setNombreCampoOrigen(String nombreCampoOrigen) {
        this.nombreCampoOrigen = nombreCampoOrigen;
    }

    public String getNombreCampoDestino() {
        return nombreCampoDestino;
    }

    public void setNombreCampoDestino(String nombreCampoDestino) {
        this.nombreCampoDestino = nombreCampoDestino;
    }
   
}
