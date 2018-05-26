package config;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;
//import org.apache.log4j.Logger;

/**
 *
 * @author SoftWill
 */
public class ConfigurationProperties implements Serializable {

    private static final Properties prop = new Properties();

    static {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("etc/configuracion.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-3);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                
            }
        }
    }
    
    public static final String URL_ORACLE_RRHH = prop.getProperty("URL_ORACLE_RRHH");
    public static final String USER_ORACLE_RRHH = prop.getProperty("USER_ORACLE_RRHH");
    public static final String PASS_ORACLE_RRHH = prop.getProperty("PASS_ORACLE_RRHH");
    
    public static final String URL_SQL_SERVER = prop.getProperty("URL_SQL_SERVER");
    public static final String USER_SQL_SERVER = prop.getProperty("USER_SQL_SERVER");
    public static final String PASS_SQL_SERVER = prop.getProperty("PASS_SQL_SERVER");
    
    public static void main(String[] args) {
        System.out.println(ConfigurationProperties.URL_ORACLE_RRHH);
        System.out.println(ConfigurationProperties.USER_ORACLE_RRHH);
        System.out.println(ConfigurationProperties.PASS_ORACLE_RRHH);
        
        System.out.println(ConfigurationProperties.URL_SQL_SERVER);
        System.out.println(ConfigurationProperties.USER_SQL_SERVER);
        System.out.println(ConfigurationProperties.PASS_SQL_SERVER);
        
    }
}
