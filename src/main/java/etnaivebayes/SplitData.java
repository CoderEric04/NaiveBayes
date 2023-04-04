package etnaivebayes;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;

public class SplitData {
    private Instances dataset;
    private int trainSize;
    private int testSize;

    public SplitData(Instances dataset){
        this.dataset = dataset;
    }

    public Instances randData() throws Exception {
        Randomize rand = new Randomize();
        rand.setInputFormat(dataset);
        Instances randData = Filter.useFilter(dataset, rand);

        return randData;
    }

    public Instances trainingData() throws Exception {
        trainSize = (int) Math.round(randData().numInstances() * 0.7);
        Instances trainingData = new Instances(randData(), 0, trainSize);

        return trainingData;
    }

    public Instances testingData() throws Exception {
        testSize = randData().numInstances() - trainSize;
        Instances testingData = new Instances(randData(), trainSize, testSize);

        return testingData;
    }
}
