import java.io.File;
import java.util.ArrayList;

public class LoadFromFile {
    // TODO
    // Dokończyć wczytywanie
    // Tak, musi dostawać tablicę
    // Więc biorąc to pod uwagę, będzie zwracać Listę stringów
    // Każdy element listy to zawartość jednego pliku
    // Obsługę błędów chyba zrobię z poziomu GUI, żeby wyświetlało ostrzeżenie i zamykało program
    public static ArrayList<String> getInput(File[] files) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        return arr;
    }
}
