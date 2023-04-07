package etnaivebayes;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;

public class SplitData {
    private final Instances dataset;
    private int trainSize;

    public SplitData(Instances dataset){
        this.dataset = dataset;
    }

    //method to randomize data
    public Instances randData() throws Exception {
        //randomize the dataset
        Randomize rand = new Randomize();
        rand.setInputFormat(dataset);

        //return randomized data
        return Filter.useFilter(dataset, rand);
    }

    //method to get data to train the classifier
    public Instances trainingData() throws Exception {
        //get 70% of size of data
        trainSize = (int) Math.round(randData().numInstances() * 0.7);

        //return new instances containing 70% of data
        return new Instances(randData(), 0, trainSize);
    }

    //method to get data to test the trained classifier on
    public Instances testingData() throws Exception {
        //get remaining size of data
        int testSize = randData().numInstances() - trainSize;

        //return new instances containing 30% of data
        return new Instances(randData(), trainSize, testSize);
    }
}
