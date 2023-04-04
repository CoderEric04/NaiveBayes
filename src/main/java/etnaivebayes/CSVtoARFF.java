package etnaivebayes;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVtoARFF {
    public String convert() throws IOException {
        //Load csv
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("MLdata.csv"));
        Instances data = loader.getDataSet();
        //Convert to arff
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File("MLdata.arff"));
        saver.setDestination(new File("MLdata.arff"));
        saver.writeBatch();
        return "MLdata.arff";
    }

    public Instances withClasses() throws IOException {
        Instances dataset = new Instances(new BufferedReader(new FileReader(convert())));

        //Set last attribute as classes
        if (dataset.classIndex() == -1) {
            dataset.setClassIndex(dataset.numAttributes() - 1);
        }

        return dataset;
    }
}
