package etnaivebayes;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;

//Student gender,Parent/ guardian had own business,Has a part time job,Urban or rural address,Studies business subjects,Become an Entrepreneur

public class Main {
    public static void main(String[] args) throws IOException {

        //Convert to arff
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("MLdata.csv"));
        Instances data = loader.getDataSet();

        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File("MLdata.arff"));
        saver.setDestination(new File("MLdata.arff"));
        saver.writeBatch();
    }
}

