package etnaivebaynes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCSVFile {
    public static String[][] CSV2DArray(String filepath, int amountOfFields){
        List<String> recordList = new ArrayList<String>();

        String delimiter = ",";
        String currentLine;

        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                recordList.add(currentLine);
            }

            int recordCount = recordList.size();

            String finalArray[][] = new String[recordCount][amountOfFields];
            String[] data;

            for (int i = 0; i < recordCount; i++) {
                data = recordList.get(i).split(delimiter);

                for (int j = 0; j < data.length; j++){
                    finalArray[i][j] = data[j];
                }
            }

            return finalArray;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public static void main(String[] args) {
        ReadCSVFile csv = new ReadCSVFile();

        String[][] data = csv.CSV2DArray("MLdata.csv", 6);

        for (int i = 0; i < data.length; i++) {
            System.out.println(String.join(",", data[i]));
        }
    }
}
