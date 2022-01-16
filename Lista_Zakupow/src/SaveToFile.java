import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveToFile {
    public static void saveData(ArrayList<String> result, File[] files) throws FileNotFoundException {
        PrintWriter outputFile = new PrintWriter("wyjscie.txt");

        if(result != null) {
            int counter = 1;
            StringBuilder outputToFile = new StringBuilder();
            for(int i = 0; i < files.length; i++) {
                String[] combinations = result.get(i).split(";");
                outputToFile.append("Plik ").append(counter).append(" ").append(files[i].getName()).append(":\n");
                for (String combination : combinations) {
                    outputToFile.append(combination).append("\n");
                }
                counter++;
                outputToFile.append("\n");
            }
            outputFile.write(String.valueOf(outputToFile).substring(0, outputToFile.length() - 2));
        }
        outputFile.close();
    }
}
