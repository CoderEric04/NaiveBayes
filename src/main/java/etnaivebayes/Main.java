package etnaivebayes;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader filereader = new FileReader("MLdata.csv");
        Iterable<CSVRecord> records=CSVFormat.DEFAULT.parse(filereader);

        for(CSVRecord record:records){
            String StudentGender = record.get(0);
            System.out.println(StudentGender);
        }
    }
}
