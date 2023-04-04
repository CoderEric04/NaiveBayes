package etnaivebayes;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

//Student gender,Parent/ guardian had own business,Has a part time job,Urban or rural address,Studies business subjects,Become an Entrepreneur

public class Main {
    public static void main(String[] args) throws Exception {

        CSVtoARFF getData = new CSVtoARFF();

        Instances dataset = getData.withClasses();

        SplitData splitData = new SplitData(dataset);

        Instances trainingData = splitData.trainingData();
        Instances testingData = splitData.testingData();

//        System.out.println("full:" + dataset.numInstances());
//        System.out.println("training:" + trainingData.numInstances());
//        System.out.println("testing:" + testingData.numInstances());

        TrainAndTest trainAndTest = new TrainAndTest(trainingData, testingData);
        NaiveBayes naiveBayes = trainAndTest.train();
        trainAndTest.test();

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

        PredictInstance predictInstance = new PredictInstance(trainingData, naiveBayes);
        predictInstance.predict();
    }
}

