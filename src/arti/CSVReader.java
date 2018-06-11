package arti;

import java.io.*;
import java.util.*;

public class CSVReader {
	
	private String csvFile = "";
	
	public CSVReader (String file) {
		csvFile = file;
	}

    public ArrayList<String[]> readCSV() {
    	ArrayList<String[]> output = new ArrayList<String[]>();

        BufferedReader br = null;
        String line = "";
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                output.add(line.split(","));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        output.remove(0);
        return output;
    }

}