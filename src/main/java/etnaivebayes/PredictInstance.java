package etnaivebayes;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;

public class PredictInstance {
    private final Instances trainingData;
    private final NaiveBayes naiveBayes;
    private final ArrayList<String> values;

    public PredictInstance(Instances trainingData, NaiveBayes naiveBayes, ArrayList<String> values){
        this.trainingData = trainingData;
        this.naiveBayes = naiveBayes;
        this.values = values;
    }

    //getter for list containing instance values
    public ArrayList<String> getValues(){
        return values;
    }

    //method that creates new instance
    public Instance enterInstance(ArrayList<String> values){
        //create new instance with number of attributes contained in training data
        Instance addedValues = new Instance(trainingData.numAttributes());
        //set reference to training dataset
        addedValues.setDataset(trainingData);

        //add values in array list into instance
        int i = 0;
        for (String value : values) {
            addedValues.setValue(i, value);
            i++;
        }

        return addedValues;
    }

    //method that predicts class of instance
    public String predict() throws Exception {
        //classifies the given instance, 0 for no, 1 for yes
        double predict = naiveBayes.classifyInstance(enterInstance(values));

        //returns yes or no for classification
        return trainingData.classAttribute().value((int)predict);
    }
}
