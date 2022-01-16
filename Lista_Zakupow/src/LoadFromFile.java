import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LoadFromFile {
    public static ArrayList<String> getInput(File[] files) throws IOException{
        ArrayList<String> arr = new ArrayList<>();
        if(files != null) {
            for(File file : files) {
                String str = Files.readString(Paths.get(file.getAbsolutePath())).strip();
                arr.add(str);
            }
        }
        else
            arr.add("");
        return arr;
    }
}
