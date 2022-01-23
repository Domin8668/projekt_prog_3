import java.util.ArrayList;
import java.util.Arrays;

class Calculate {
    private final ArrayList<String> result = new ArrayList<>();
    private ArrayList<String> file = new ArrayList<>();
    private ArrayList<Integer> valuesCopy = new ArrayList<>();
    public ArrayList<String> getResult() {
        return result;
    }

    public void calculate(ArrayList<String> input) {
        int m;
        ArrayList<Integer> values;
        int budget;

        for (String s : input) {
            String[] lines = s.split("\\n");
            m = lines.length;
            values = new ArrayList<>();
            budget = (int) (Double.parseDouble(lines[0]) * 100);
            for(int i = 1; i < m; i++) {
                int value = (int) (Double.parseDouble(lines[i].split(" ")[0]) * 100);
                values.add(value);
            }
            sumUp(values, budget);
            if(file.size() > 0) {
                result.add(String.join(";", file));
                file = new ArrayList<>();
            }
            else
                result.add("Brak kombinacji.");
        }
    }

    void sumUp(ArrayList<Integer> values, int budget) {
        valuesCopy = values;
        sumUpRecursive(values, budget, new ArrayList<>());
    }

    void sumUpRecursive(ArrayList<Integer> values, int budget, ArrayList<Integer> partial) {
        // Sumujemy wartości w partial:
        int partialSum = 0;
        for(int i : partial)
            partialSum += i;

        // Jeśli budżet został wykorzystany:
        if(partialSum == budget) {
            ArrayList<String> indices = new ArrayList<>();
            for(int i : partial) {
                indices.add(String.valueOf(valuesCopy.indexOf(i)));
            }
            file.add(String.join(", ", indices));
        }
        // Jeśli przekroczyliśmy budżet, metoda kończy działanie:
        if(partialSum >= budget)
            return;
        // Przenosimy elementy do remaining oraz partial_rec
        for(int i = 0; i < values.size(); i++) {
            // Nowa lista partial_rec równa partial
            ArrayList<Integer> partial_rec = new ArrayList<>(partial);
            // Nowa pusta lista values_rec
            ArrayList<Integer> values_rec = new ArrayList<>();
            // Dodajemy i-ty element values do partial_rec
            partial_rec.add(values.get(i));
            // Pozostałe elementy z values dodajemy do values_rec
            for(int j = i + 1; j < values.size(); j++)
                values_rec.add(values.get(j));
            // Wykonujemy sumUpRecursive rekurencyjnie dla values_rec oraz partial_rec
            sumUpRecursive(values_rec, budget, partial_rec);
        }
    }
}

