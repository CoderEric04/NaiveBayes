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

    public Instance enterInstance(ArrayList<String> values){
        Instance addValues = new Instance(trainingData.numAttributes());
        addValues.setDataset(trainingData);

        int i = 0;
        for (String value : values) {
            addValues.setValue(i, value);
            i++;
        }

        return addValues;
    }

    public String predict() throws Exception {
        double predict = naiveBayes.classifyInstance(enterInstance(values));
        String predClass = trainingData.classAttribute().value((int)predict);

        return predClass;
    }
}
