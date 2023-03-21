package etnaivebaynes;
public class Main {
    public static void main(String[] args) {
        ReadCSVFile csv = new ReadCSVFile();

        String[][] data = csv.CSV2DArray("MLdata.csv", 6);

        for (int i = 0; i < data.length; i++) {
            System.out.println(String.join(",", data[i]));
        }
    }
}
