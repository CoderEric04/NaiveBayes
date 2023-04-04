package etnaivebayes;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;

public class PredictInstance {
    private Instances trainingData;
    private NaiveBayes naiveBayes;
    public PredictInstance(Instances trainingData, NaiveBayes naiveBayes){
        this.trainingData = trainingData;
        this.naiveBayes = naiveBayes;
    }

    public Instance enterInstance(){
        Instance addValues = new Instance(trainingData.numAttributes());
        addValues.setDataset(trainingData);

        addValues.setValue(0,"Female");
        addValues.setValue(1,"Yes");
        addValues.setValue(2,"No");
        addValues.setValue(3,"Rural");
        addValues.setValue(4,"No");

        return addValues;
    }

    public void predict() throws Exception {
        double predict = naiveBayes.classifyInstance(enterInstance());
        String predClass = trainingData.classAttribute().value((int)predict);
        System.out.println("Class predicted: " + predClass);
    }
}
