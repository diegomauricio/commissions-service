/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvgenerator;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author floresdi
 */
public class CSVRequest {

    String query;
    String pathFile;
    String fileName;
    int estado;
    int connection;

    public CSVRequest(String query, String pathFile, String fileName) {
        this.query = query;
        this.pathFile = pathFile;
        this.fileName = fileName;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void generarCSV() throws IOException, SQLException {
        if (query != null && !query.trim().isEmpty()) {

//            Connection conn = ConexionJDBC.getConexion(connection);
            Connection conn=null;
            conn.setAutoCommit(true);

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            CsvWriter csvOutput = new CsvWriter(new FileWriter(pathFile + fileName, false), ';');
            csvOutput.setUseTextQualifier(false);
            while (rs.next()) {
                csvOutput.write(rs.getString(1));
                csvOutput.endRecord();
            }
            csvOutput.close();
        }
    }
}


