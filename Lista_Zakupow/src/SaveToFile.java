import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveToFile {

    public static void saveData(ArrayList<String> arr) throws FileNotFoundException {

        PrintWriter outputFile = new PrintWriter("wyjscie.txt");

        if(arr != null) {
            int counter = 1;
            for (String file_comb : arr) {
                outputFile.print("Plik " + counter + ": ");
                String[] combinations = file_comb.split(";");
                for (String combination : combinations) {
                    outputFile.println(combination);
                }
                counter += 1;
                outputFile.println("");
            }
        }
        else
            System.out.println("Nie znaleziono pobranych danych z pliku wejściowego lub plik jest pusty.");
        outputFile.close();
    }

}
