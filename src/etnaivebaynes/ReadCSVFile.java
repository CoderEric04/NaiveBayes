package etnaivebaynes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//Feature,Feature,Feature,Feature,Feature,Label
//Student gender, Parent/guardian had own business, Has a part time job, Urban or rural address, Studies business subjects, Become an Entrepreneur
public class ReadCSVFile {
    public static String[][] CSV2DArray(String filepath, int amountOfFields) {
        List<String> recordList = new ArrayList<>();

        String delimiter = ",";
        String currentLine;

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                recordList.add(currentLine);
            }

            int recordCount = recordList.size();

            String[][] finalArray = new String[recordCount][amountOfFields];
            String[] data;

            for (int i = 0; i < recordCount; i++) {
                data = recordList.get(i).split(delimiter);

                System.arraycopy(data, 0, finalArray[i], 0, data.length);
            }

            return finalArray;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
