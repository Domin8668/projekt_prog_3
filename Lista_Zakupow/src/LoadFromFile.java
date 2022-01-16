import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LoadFromFile {
    public static ArrayList<String> getInput(File[] files) throws IOException{
        ArrayList<String> input = new ArrayList<>();
        if(files != null) {
            for(File file : files) {
                String str = Files.readString(Paths.get(file.getAbsolutePath())).strip();
                input.add(str);
            }
        }
        else
            input.add("");
        return input;
    }
}
