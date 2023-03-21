package etnaivebaynes;

public class DataFreq{
    public static String[][] feature() {
        String[][] data = ReadCSVFile.CSV2DArray("MLdata.csv", 6);
        assert data != null;
        String[][] features = new String[data.length][5];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 5; j++) {
                features[i][j] = data[i][j];
            }
        }

        return features;
    }

    public static String[] labels() {
        String[][] data = ReadCSVFile.CSV2DArray("MLdata.csv", 6);
        assert data != null;
        String[] labels = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            labels[i] = data[i][data[0].length - 1];
        }

        return labels;
    }
}
