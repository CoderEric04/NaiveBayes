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

    public Instances randData() throws Exception {
        Randomize rand = new Randomize();
        rand.setInputFormat(dataset);

        return Filter.useFilter(dataset, rand);
    }

    public Instances trainingData() throws Exception {
        trainSize = (int) Math.round(randData().numInstances() * 0.7);

        return new Instances(randData(), 0, trainSize);
    }

    public Instances testingData() throws Exception {
        int testSize = randData().numInstances() - trainSize;

        return new Instances(randData(), trainSize, testSize);
    }
}
