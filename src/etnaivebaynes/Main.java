package etnaivebaynes;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
//        String[][] data = ReadCSVFile.CSV2DArray("MLdata.csv", 6);
//
//        for (int i = 0; i < Objects.requireNonNull(data).length; i++) {
//            System.out.println(String.join(",", data[i]));
//        }
//
//        String[][] features = DataFreq.feature();
//
//        for (int i = 0; i < Objects.requireNonNull(features).length; i++) {
//            System.out.println(String.join(",", features[i]));
//        }

        String[] labels = DataFreq.labels();

        for (int i = 0; i < Objects.requireNonNull(labels).length; i++) {
            System.out.println(String.join(",", labels[i]));
        }
    }
}
