package org.example.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReaderUtil {

    // Método para leer el archivo CSV y devolver una lista de credenciales
    public List<String[]> readCSV(String filePath) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> records = reader.readAll();
        reader.close();
        return records;  // Devuelve todos los registros en el CSV
    }

    // Método para obtener las credenciales de login (email, password)
    public String[] getLoginData(String filePath, int row) throws IOException, CsvException {
        List<String[]> data = readCSV(filePath);
        return data.get(row);  // Retorna las credenciales en la fila especificada
    }
}
