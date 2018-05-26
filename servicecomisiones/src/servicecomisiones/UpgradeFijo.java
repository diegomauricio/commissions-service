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

/**
 *
 * @author floresdi
 */
public class UpgradeFijo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Origen ori=new Origen("Upgrade fijo", "select * from UPGRADE_FIJO where periodo=convert(varchar(6),dateadd(month,-1,getdate()),112)", Servidores.SQLSERVER);
        Destino des=new Destino("Facturacion manual Comisiones", "T_UPGRADE_FIJO_CORP_SRC", Servidores.ORACLESERVER);
        
        ParCampos par1=new ParCampos("PERIODO","PERIODO");
        ParCampos par2=new ParCampos("EHUMANO","EHUMANO");
        ParCampos par3=new ParCampos("NOMBRE_EJECUTIVO","NOMBRE_EJECUTIVO");
        ParCampos par4=new ParCampos("NOMBRE_CLIENTE","NOMBRE_CLIENTE");
        ParCampos par5=new ParCampos("FECHA_HABILITACION","FECHA_HABILITACION");
        ParCampos par6=new ParCampos("PLAN_ANTIGUO","PLAN_ANTIGUO");
        ParCampos par7=new ParCampos("PLAN_NUEVO","PLAN_NUEVO");
        ParCampos par8=new ParCampos("NRO_CUENTA","NRO_CUENTA");
        ParCampos par9=new ParCampos("FACT_ANT","FACT_ANT");
        ParCampos par10=new ParCampos("FACT_ACTUAL","FACT_ACTUAL");
        ParCampos par11=new ParCampos("UPGRADE","UPGRADE");
        
        List<ParCampos> listPar;
        listPar = new ArrayList<>();
        
        listPar.add(par1);
        listPar.add(par2);
        listPar.add(par3);
        listPar.add(par4);
        listPar.add(par5);
        listPar.add(par6);
        listPar.add(par7);
        listPar.add(par8);
        listPar.add(par9);
        listPar.add(par10);
        listPar.add(par11);
        
        Copiar copy=new Copiar(ori, des, listPar);
        
        Connection oriCon=SqlServerConnection.getConnection(ConfigurationProperties.URL_SQL_SERVER, ConfigurationProperties.USER_SQL_SERVER, ConfigurationProperties.PASS_SQL_SERVER);
        Connection destCon=OracleConnection.getConnection(ConfigurationProperties.URL_ORACLE_RRHH, ConfigurationProperties.USER_ORACLE_RRHH, ConfigurationProperties.PASS_ORACLE_RRHH);
        
        Copiador copier=new Copiador(copy,oriCon,destCon);

        try {            
            copier.iniciar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
                e.printStackTrace();
        }       
    }
    
}
