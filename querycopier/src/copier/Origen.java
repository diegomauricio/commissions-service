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
public class Origen {
    String nombreOrigen;
    String queryOrigen;
    int serverOrigen;

    public Origen(String nombreOrigen, String queryOrigen, int serverOrigen) {
        this.nombreOrigen = nombreOrigen;
        this.queryOrigen = queryOrigen;
        this.serverOrigen = serverOrigen;
    }

    public int getServerOrigen() {
        return serverOrigen;
    }

    public void setServerOrigen(int serverOrigen) {
        this.serverOrigen = serverOrigen;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getQueryOrigen() {
        return queryOrigen;
    }

    public void setQueryOrigen(String queryOrigen) {
        this.queryOrigen = queryOrigen;
    }

    
    
}
