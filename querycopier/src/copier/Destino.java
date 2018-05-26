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
public class Destino {
    String nombreDestino;
    String nombreTabla;
    int serverDestino;

    public Destino(String nombreDestino, String nombreTabla, int serverDestino) {
        this.nombreDestino = nombreDestino;
        this.nombreTabla = nombreTabla;
        this.serverDestino = serverDestino;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public int getServerDestino() {
        return serverDestino;
    }

    public void setServerDestino(int serverDestino) {
        this.serverDestino = serverDestino;
    }
    
    
}
