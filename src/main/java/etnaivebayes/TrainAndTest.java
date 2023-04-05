package etnaivebayes;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class TrainAndTest {
    private final Instances trainingData;
    private final Instances testingData;

    public TrainAndTest(Instances trainingData, Instances testingData){
        this.trainingData = trainingData;
        this.testingData = testingData;
    }

    public NaiveBayes train() throws Exception {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.buildClassifier(trainingData);

        return naiveBayes;
    }

    public Evaluation test() throws Exception {
        Evaluation eval = new Evaluation(trainingData);
        eval.evaluateModel(train(), testingData);

        System.out.println(eval.toSummaryString());

        return eval;
    }
}
