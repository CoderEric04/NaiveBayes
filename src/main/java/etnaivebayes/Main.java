package etnaivebayes;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SplitData sd = new SplitData("MLdata.csv");
        System.out.println(sd.testingData());
    }
}
