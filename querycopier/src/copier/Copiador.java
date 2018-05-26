/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copier;

//import connections.OracleConnection;
//import connections.SqlServerConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author floresdi
 */
public class Copiador {
    Copiar copiar;
    Connection conOrigen;
    Connection conDestino;
    String[][] listFieldTypes;

    public Copiador(Copiar copiar,Connection conOrigen,Connection conDestino) {
        this.copiar = copiar;
        this.conOrigen=conOrigen;
        this.conDestino=conDestino;        
    }
    
    
    public void iniciar() throws SQLException{
        System.out.println("Iniciando copia");
        if(copiar.getOrigen().getServerOrigen()==Servidores.SQLSERVER){
            String sql=copiar.getOrigen().getQueryOrigen();
            Statement stmt = conOrigen.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<String> queries=new ArrayList<>();
            String beginQuery="insert into "+copiar.getDestino().getNombreTabla()+
                    " (";
            for(ParCampos par : copiar.getListParCampos()){
                beginQuery=beginQuery+par.getNombreCampoDestino()+",";                
            }
            beginQuery=beginQuery.substring(0, beginQuery.lastIndexOf(","));
            beginQuery=beginQuery+") values(";
            ResultSetMetaData rsmd=rs.getMetaData();       
            
            obtenerTiposDato(rsmd);
            
            while (rs.next()) {
                String query=beginQuery;
                for (int i = 0; i < copiar.getListParCampos().size(); i++) {
//                    System.out.println(copiar.getListParCampos().get(i).getNombreCampoOrigen());
//                    System.out.println(getType(copiar.getListParCampos().get(i).getNombreCampoOrigen()));
//                    System.out.println("");
                    if(getType(copiar.getListParCampos().get(i).getNombreCampoOrigen())==Types.DATE){
                        Date date = rs.getDate(copiar.getListParCampos().get(i).getNombreCampoOrigen());
                        if(date!=null){
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String dateString = df.format(date);
                            query=query+"TO_DATE('"+dateString+"','DD/MM/YYYY hh24:mi:ss'),";
                        }else
                        {
                            query=query+"NULL"+",";
                        }                        
                    }else
                    {
                        String queryTmp=rs.getString(copiar.getListParCampos().get(i).getNombreCampoOrigen());    
                        if (queryTmp!=null){
                            query=query+"'"+
                                queryTmp+"',"; 
                        }
                        else
                        {
                            query=query+"NULL"+",";
                        }
                    }                    
                }
                query=query.substring(0, query.lastIndexOf(","));
                query=query+")";
                queries.add(query);                
            }
            
//            System.out.println(queries.toString());
            conOrigen.close();
            conOrigen=null;            
            insertarDestino(queries, conDestino);
        }
    }
    
    public void obtenerTiposDato(ResultSetMetaData rsmd) throws SQLException{      
        
        int numberOfColumns = rsmd.getColumnCount();        
        listFieldTypes=new String[numberOfColumns][3];
        for (int i = 1; i <= numberOfColumns; i++) {
            listFieldTypes[i-1][0]=String.valueOf(rsmd.getColumnName(i));
            listFieldTypes[i-1][1]=String.valueOf(rsmd.getColumnType(i));
            listFieldTypes[i-1][2]=String.valueOf(rsmd.getColumnTypeName(i));
        }
        
    }
    
    private int getType(String nameColumn){
        int type=0;
        for (String[] listFieldType : listFieldTypes) {
            if(listFieldType[0].equals(nameColumn)){
                type=Integer.valueOf(listFieldType[1]);
                break;
            }
        }        
        return type;
    }
    
    public void insertarDestino(List<String> listQueries, Connection conDestino) throws SQLException{
        if(copiar.getDestino().getServerDestino()==Servidores.ORACLESERVER){
            Statement stmt=conDestino.createStatement();            
            for(String query:listQueries){
                System.out.println(query);
                stmt.addBatch(query);
            }
            stmt.executeBatch();
            stmt.close();
            conDestino.close();
            conDestino=null;
            


//            for(String query:listQueries){
//                System.out.println(query);
//                Statement stmt=conDestino.createStatement();                            
//                stmt.execute(query);                
//                stmt.close();
//            }
//            conDestino.close();
//            conDestino=null;
            
        }
    }


    
}
