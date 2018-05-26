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
public class FacturacionManual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // TIGO MEDIA
        Origen ori=new Origen("Facturacion manual Corporate", "select * from VENTAS_FACTURA_MANUAL where periodo=convert(varchar(6),dateadd(month,-1,getdate()),112)", Servidores.SQLSERVER);
        Destino des=new Destino("Facturacion manual Comisiones", "T_VENT_FACT_MAN_CORP_SRC", Servidores.ORACLESERVER);
        
        ParCampos par1=new ParCampos("PERIODO","PERIODO");
        ParCampos par2=new ParCampos("EHUMANO","EHUMANO");
        ParCampos par3=new ParCampos("NOMBRE","NOMBRE");
        ParCampos par4=new ParCampos("FECHA_HABILITACION","FECHA_HABILITACION");
        ParCampos par5=new ParCampos("COD_CLIENTE","COD_CLIENTE");
        ParCampos par6=new ParCampos("NOMBRE_CLIENTE","NOMBRE_CLIENTE");
//        ParCampos par7=new ParCampos("NRO_CONTRATO","NRO_CONTRATO");
        ParCampos par8=new ParCampos("NRO_CUENTA","NRO_CUENTA");
        ParCampos par9=new ParCampos("PLAN_CONSUMO","PLAN_CONSUMO");
        ParCampos par10=new ParCampos("TBM_TOTAL","TBM_TOTAL");
        
        List<ParCampos> listPar;
        listPar = new ArrayList<>();
        
        listPar.add(par1);
        listPar.add(par2);
        listPar.add(par3);
        listPar.add(par4);
        listPar.add(par5);
        listPar.add(par6);
//        listPar.add(par7);
        listPar.add(par8);
        listPar.add(par9);
        listPar.add(par10);
        
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
