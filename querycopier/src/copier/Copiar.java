/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copier;

import java.util.List;

/**
 *
 * @author floresdi
 */
public class Copiar {
    Origen origen;
    Destino destino;
    List<ParCampos> listParCampos;

    public Copiar(Origen origen, Destino destino, List<ParCampos> listParCampos) {
        this.origen = origen;
        this.destino = destino;
        this.listParCampos = listParCampos;
    }

    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public List<ParCampos> getListParCampos() {
        return listParCampos;
    }

    public void setListParCampos(List<ParCampos> listParCampos) {
        this.listParCampos = listParCampos;
    }
    
    
}
