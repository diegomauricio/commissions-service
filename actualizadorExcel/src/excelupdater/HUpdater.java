/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelupdater;

//import com.tigo.properties.ConfiguracionProperties;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author floresdi
 */
public class HUpdater extends Thread {

    private GestorActualizadorArchivos gestor;
    

//    String pathFolder;
    private List<UpdateFileRequest> listUpdateRequest;
    private List<Integer> listIdTareas;
    private String fileNameLog;

    public HUpdater(List<UpdateFileRequest> fileNames, String fileNameLog) {
        this.listUpdateRequest = fileNames;
        this.fileNameLog = fileNameLog;
    }

    @Override
    public void run() {
        UpdateFileRequest updateFileRequest;
        for (int i = 0; i < listUpdateRequest.size(); i++) {
            listUpdateRequest.get(i).setStatus(Estados.EJECUTANDO);
            listUpdateRequest.get(i).setMessageStatus("ACTUALIZANDO");
            updateFileRequest = listUpdateRequest.get(i);
            String newFileName = renameFile(updateFileRequest.getPath(), updateFileRequest.getFileName());
            if (!"".equals(newFileName)&&newFileName!=null) {
                String message = updateDetailed(i, newFileName, updateFileRequest.getPath() + "\\" + newFileName);
                if ("".equals(message)) {
                    String messageError = verificarError(i);
                    if ("".equals(messageError)) {
                        listUpdateRequest.get(i).setStatus(Estados.TERMINADO);
                        listUpdateRequest.get(i).setMessageStatus("OK");
                    } else {
                        listUpdateRequest.get(i).setStatus(Estados.ERROR);
                        listUpdateRequest.get(i).setMessageStatus(messageError);
                    }
                } else {
                    listUpdateRequest.get(i).setStatus(Estados.ERROR);
                    listUpdateRequest.get(i).setMessageStatus(message);
                }
            } else {
                listUpdateRequest.get(i).setStatus(Estados.ERROR);
                listUpdateRequest.get(i).setMessageStatus("Error al renombrar archivo");
                System.out.println("Error al renombrar el archivo");
            }
            System.out.println(i+". Actualizado el archivo: "+newFileName);
        }
    }

    private String renameFile(String path, String fileName) {
        try {

//        System.out.println("Esta es la ruta: " + path);
//        System.out.println("Este el nombre del archivo: " + fileName);
//        String oldFileName=fileName.replace(".xlsx","");
            String newFileName = fileName.replace(".xlsx", "");
            newFileName = newFileName.replace(" ", "_");

            Pattern p = Pattern.compile("\\d{6}");
            Matcher m = p.matcher(newFileName);
            String periodoAnt;
            if (m.find()) {
                periodoAnt = newFileName.substring(m.start(), m.end());
                newFileName = newFileName.replace(periodoAnt, "");
                newFileName = newFileName.trim().replaceAll("_$", "");
            } else {
                System.out.println("Not find");
            }

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            Date result = cal.getTime();
            String periodo = new SimpleDateFormat("yyyyMM").format(result);

            newFileName = newFileName + '_' + periodo + ".xlsx";

//        return newFileName;
            File file = new File(path + "\\" + fileName);

            File file2 = new File(path + "\\" + newFileName);

            if (file.renameTo(file2)) {
                System.out.println("New File Name: "+newFileName);
                return newFileName;
            } else {
                System.out.println("No renamed");
                return "";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private String updateDetailed(int index, String fileName, String pathFile) {
//        String cmd = ConfiguracionProperties.SCRIPT_VBS + " \"" + pathFile+ "\" " + ConfiguracionProperties.CADENA_CONEXION;
        String cmd = ConfiguracionProperties.SCRIPT_VBS + " \"" + pathFile + "\" " + " \"" + fileNameLog + "\" " + index;
        String errorMessage = "";
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(cmd);
            p.waitFor();
        } catch (IOException e) {
            errorMessage = e.getMessage();
        } catch (InterruptedException e) {
            errorMessage = e.getMessage();
        }
        return errorMessage;
    }

    private String verificarError(int idTarea) {
        String errorMessage = "";
        try {
            InputStream fis = new FileInputStream(fileNameLog);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            String lastLine = "";
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                lastLine = tempLine;
            }
            if (!"".equals(lastLine)) {
                String blocks[] = lastLine.split(";");
                if (blocks != null) {
                    String correctoBlock[] = blocks[0].split(":");
                    if (correctoBlock[0].equals(String.valueOf(idTarea))) {
                        if (correctoBlock[1].equals("1")) {
                            System.out.println("...OK");
                        } else {
                            String errorBlock[];
                            if (blocks.length > 1) {
                                errorBlock = blocks[1].split(":");
                                errorMessage = "Error: " + errorBlock[1];
                            } else {
                                errorMessage = "Error: 0";
                            }
                        }
                    } else {
                        errorMessage = "Error";
                    }
                } else {
                    errorMessage = "Error: 2";
                }
            } else {
                errorMessage = "Error: 3";
            }
        } catch (IOException ex) {
            Logger.getLogger(HUpdater.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = "Error: " + ex.getMessage();
        }
        return errorMessage;
    }

    public GestorActualizadorArchivos getGestor() {
        return gestor;
    }

    public void setGestor(GestorActualizadorArchivos gestor) {
        this.gestor = gestor;
    }

    public void setEstado(int estado) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<UpdateFileRequest> getListUpdateRequest() {
        return listUpdateRequest;
    }

    public void setListUpdateRequest(List<UpdateFileRequest> listUpdateRequest) {
        this.listUpdateRequest = listUpdateRequest;
    }

}
