/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicecomisiones;

import config.ConfigurationProperties;
import connections.OracleConnection;
import connections.SqlServerConnection;
import copier.Copiador;
import copier.Copiar;
import copier.Destino;
import copier.Origen;
import copier.ParCampos;
import copier.Servidores;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import tareas.CopiarQueryTarea;
import taskmanager.GestorTareas;

/**
 *
 * @author floresdi
 */
public class TestTaskManager {

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
        
        Connection oriCon=SqlServerConnection.getConnection(ConfigurationProperties.URL_SQL_SERVER, ConfigurationProperties.USER_SQL_SERVER, ConfigurationProperties.PASS_SQL_SERVER);
        Connection destCon=OracleConnection.getConnection(ConfigurationProperties.URL_ORACLE_RRHH, ConfigurationProperties.USER_ORACLE_RRHH, ConfigurationProperties.PASS_ORACLE_RRHH);
        
        Copiador copier=new Copiador(copy,oriCon,destCon);
        
        CopiarQueryTarea tareaCopia=new CopiarQueryTarea("Llenar Tigo Media", 1, 1, copier);
        
        GestorTareas gestor=new GestorTareas();
        gestor.start();
        
        gestor.agregarTarea(tareaCopia);
    }
    
}
