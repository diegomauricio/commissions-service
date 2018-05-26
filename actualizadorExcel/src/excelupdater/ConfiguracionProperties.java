package excelupdater;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;
//import org.apache.log4j.Logger;

/**
 *
 * @author SoftWill
 */
public class ConfiguracionProperties implements Serializable {

//    private static final Logger log = Logger.getLogger(ConfiguracionProperties.class);
    private static final Properties prop = new Properties();

    static {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("etc/configuracion.properties");
            prop.load(fis);
        } catch (Exception e) {
//            log.fatal("[Fallo al leer el archivo configuracion.properties]", e);
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
//    SCRIPT VBS
    public static final String SCRIPT_VBS = prop.getProperty("SCRIPT_VBS");
    public static final String CADENA_CONEXION = prop.getProperty("CADENA_CONEXION");
    public static final String PATH_LOG=prop.getProperty("PATH_LOG");
}
