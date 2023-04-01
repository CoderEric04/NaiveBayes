package etnaivebayes;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SplitData {
    String file;

    public SplitData(String file){
        this.file = file;
    }

    public List<List<String>> readCSV(String file) throws IOException {
        Reader filereader = new FileReader(file);
        CSVParser parser = new CSVParser(filereader, CSVFormat.DEFAULT);
        List<List<String>> data = new ArrayList<>();

        for (CSVRecord record : parser) {
            List<String> row = new ArrayList<>();
            for (String value : record) {
                row.add(value);
            }
            data.add(row);
        }

        return data;
    }

    public int numTraining() throws IOException {
        List<List<String>> data = readCSV("MLdata.csv");

        return (int) (0.7 * data.size());
    }

    public int numTesting() throws IOException {
        List<List<String>> data = readCSV("MLdata.csv");

        return data.size() - numTraining();
    }

    public List<List<String>> trainingData() throws IOException {
        List<List<String>> trainingData = new ArrayList<>();

        for (int i = 0; i < numTraining(); i++) {
            trainingData.add(new ArrayList<>(readCSV("MLdata.csv").get(i)));
        }

        return trainingData;
    }

    public List<List<String>> testingData() throws IOException {
        List<List<String>> data = readCSV("MLdata.csv");
        List<List<String>> testingData = new ArrayList<>();

        for (int i = numTraining(); i < data.size(); i++) {
            testingData.add(new ArrayList<>(readCSV("MLdata.csv").get(i)));
        }

        return testingData;
    }

}
