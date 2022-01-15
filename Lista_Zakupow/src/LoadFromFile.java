import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LoadFromFile {
    // TODO
    // Dokończyć wczytywanie
    // Tak, musi dostawać tablicę
    // Więc biorąc to pod uwagę, będzie zwracać Listę stringów
    // Każdy element listy to zawartość jednego pliku
    // Obsługę błędów chyba zrobię z poziomu GUI, żeby wyświetlało ostrzeżenie i zamykało program
    public static ArrayList<String> getInput(File[] files) throws IOException, NullPointerException {
        ArrayList<String> arr = new ArrayList<>();
        if(files != null) {
            for (File file : files) {
                String str = Files.readString(Paths.get(file.getAbsolutePath()));
                arr.add(str);
            }
        }
        else
            arr.add("Tablica była nullem :((((");
        return arr;
    }
}
