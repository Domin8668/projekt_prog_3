import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveToFile {
    public static void saveData(ArrayList<String> arr) throws FileNotFoundException {

        PrintWriter outputFile = new PrintWriter("wyjscie.txt");

        if(arr != null) {
            for (String combination : arr) {
                outputFile.println(combination);
            }
        }
        else
            System.out.println("Nie znaleziono pobranych danych z pliku wejściowego.");
    }
}
