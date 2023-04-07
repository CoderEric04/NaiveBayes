package etnaivebayes;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

//Student gender,Parent/ guardian had own business,Has a part-time job,Urban or rural address,Studies business subjects,Become an Entrepreneur

public class Main {
    public static void main(String[] args) throws Exception {

        //create new instance of the CSVtoARFF class
        CSVtoARFF getData = new CSVtoARFF();

        //create instances containing dataset
        Instances dataset = getData.getDataWithClasses();

        //create new instance of the SplitData class passing the dataset
        SplitData splitData = new SplitData(dataset);
        //create training data containing 70% of dataset
        Instances trainingData = splitData.trainingData();
        //create test data containing 30% of dataset
        Instances testingData = splitData.testingData();

//        System.out.println("full:" + dataset.numInstances());
//        System.out.println("training:" + trainingData.numInstances());
//        System.out.println("testing:" + testingData.numInstances());

        //create new instance of the TrainAndTest class passing training and test data
        TrainAndTest trainAndTest = new TrainAndTest(trainingData, testingData);
        //get trained classifier
        NaiveBayes naiveBayes = trainAndTest.train();
        //get evaluation of classifier against test data
        Evaluation eval = trainAndTest.test();

//Test if evaluation accurate
//        double amount = 0;
//        double correct = 0;
//        double accuracy;
//
//        System.out.println("==================");
//        System.out.println("Actual Class, NB predicted");
//        for (int i = 0; i < testingData.numInstances(); i++) {
//            double actualClass = testingData.instance(i).classValue();
//            String actual = testingData.classAttribute().value((int) actualClass);
//            Instance newInst = testingData.instance(i);
//            double predNB = naiveBayes.classifyInstance(newInst);
//            String predString = testingData.classAttribute().value((int) predNB);
//            amount++;
//            if(actual == predString){
//                correct++;
//            }
//        }
//
//        accuracy = correct/amount;
//
//        System.out.println("The prediction is " + accuracy + "% accurate");
        //create new instance of the GUI class passing the classifier, training data and evaluation
        GUI gui1 = new GUI(naiveBayes, trainingData, eval);
    }
}

