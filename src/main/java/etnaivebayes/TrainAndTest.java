package etnaivebayes;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class TrainAndTest {
    private Instances trainingData;
    private Instances testingData;

    public TrainAndTest(Instances trainingData, Instances testingData){
        this.trainingData = trainingData;
        this.testingData = testingData;
    }

    public NaiveBayes train() throws Exception {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.buildClassifier(trainingData);

        return naiveBayes;
    }

    public void test() throws Exception {
        Evaluation eval = new Evaluation(trainingData);
        eval.evaluateModel(train(), testingData);

        System.out.println(eval.toSummaryString());
    }
}
