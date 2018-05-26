/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copier;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author floresdi
 */
public class COPIER {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Origen ori=new Origen("Tigo Media Corporate", "select * from TIGO_MEDIA", Servidores.SQLSERVER);
        Destino des=new Destino("Tigo Media Comisiones", "t_tigo_media_corp_src", Servidores.ORACLESERVER);
        ParCampos par1=new ParCampos("DURACION_CONTRATO", "DURACION_CONTRATO");
        ParCampos par2=new ParCampos("EHUMANO", "EHUMANO");
        ParCampos par3=new ParCampos("FECHA_HABILITACION", "FECHA_HABILITACION");
        ParCampos par4=new ParCampos("NOMBRE_CLIENTE", "NOMBRE_CLIENTE");
        ParCampos par5=new ParCampos("NOMBRE_VENDEDOR", "NOMBRE_VENDEDOR");
        ParCampos par6=new ParCampos("PERIODO", "PERIODO");
        ParCampos par7=new ParCampos("TBM_PAGAR_BS", "TBM_PAGAR_BS");
        
        List<ParCampos> listPar;
        listPar = new ArrayList<>();
        
        listPar.add(par1);
        listPar.add(par2);
        listPar.add(par3);
        listPar.add(par4);
        listPar.add(par5);
        listPar.add(par6);
        listPar.add(par7);
        
        Copiar copy=new Copiar(ori, des, listPar);        
        
        Copiador copier=new Copiador(copy,null,null);
        
        try {
            copier.iniciar();
        } catch (Exception e) {
                e.printStackTrace();
        }
        
    }

}
