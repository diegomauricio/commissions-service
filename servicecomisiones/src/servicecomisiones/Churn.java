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
public class Churn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Origen ori1=new Origen("Churn Corporate", "select * from CHURN WHERE periodo =convert(varchar(6),dateadd(month,-1,getdate()),112)", Servidores.SQLSERVER);
        Destino des1=new Destino("Churn Corporate Comisiones", "T_CHURN_CORP_SRC", Servidores.ORACLESERVER);

        ParCampos par8=new ParCampos("PERIODO","PERIODO");
        ParCampos par9=new ParCampos("CIUDAD","CIUDAD");
        ParCampos par10=new ParCampos("EJECUTIVO_CORPORATE","EJECUTIVO_CORPORATE");
        ParCampos par11=new ParCampos("EHUMANO"," EHUMANO");
        ParCampos par12=new ParCampos("TIPO_EJECUTIVO","TIPO_EJECUTIVO");
        ParCampos par13=new ParCampos("SISTEMA","SISTEMA");
        ParCampos par14=new ParCampos("NOMBRE_CLIENTE","NOMBRE_CLIENTE");
        ParCampos par15=new ParCampos("NRO_CUENTA","NRO_CUENTA");
        ParCampos par16=new ParCampos("TBM_USD","TBM_USD");
        ParCampos par17=new ParCampos("TBM_BS","TBM_BS");

        List<ParCampos> listPar;
        listPar = new ArrayList<>();
        
        listPar.add(par8);
        listPar.add(par9);
        listPar.add(par10);
        listPar.add(par11);
        listPar.add(par12);
        listPar.add(par13);
        listPar.add(par14);
        listPar.add(par15);
        listPar.add(par16);
        listPar.add(par17);
        
        Copiar copy=new Copiar(ori1, des1, listPar);
        
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
