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

    //method to train a naive bayes classifier using training data
    public NaiveBayes train() throws Exception {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.buildClassifier(trainingData);

        return naiveBayes;
    }

    //method to evaluate the trained classifier against training data
    public Evaluation test() throws Exception {
        Evaluation eval = new Evaluation(trainingData);
        eval.evaluateModel(train(), testingData);

        //prints out information on the comparison
        System.out.println(eval.toSummaryString());

        //creates a confusion matrix which displays which classification
        //was guessed right and wrong for each classification.
        //stores the matrix in a 2d array
        double[][] confusionMatrix = eval.confusionMatrix();

        //print out the confusion matrix using foreach loops
        System.out.println("Confusion Matrix:");
        for (double[] matrix : confusionMatrix) {
            for (double v : matrix) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

        return eval;
    }
}
