import java.util.ArrayList;
import java.util.Arrays;

class Calculate {
    private final ArrayList<String> result = new ArrayList<>();
    private ArrayList<String> file = new ArrayList<>();
    private ArrayList<Integer> numbersCopy = new ArrayList<>();
    public ArrayList<String> getResult() {
        return result;
    }

    void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
        int s = 0;
        for (int x : partial) s += x;
        if (s == target) {
            ArrayList<String> indices = new ArrayList<>();
            for(int i : partial) {
                indices.add(String.valueOf(numbersCopy.indexOf(i)));
            }
            file.add(String.join(", ", indices));
        }
        if (s >= target)
            return;
        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<>();
            int n = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) remaining.add(numbers.get(j));
            ArrayList<Integer> partial_rec = new ArrayList<>(partial);
            partial_rec.add(n);
            sum_up_recursive(remaining, target, partial_rec);
        }
    }

    void sum_up(ArrayList<Integer> numbers, int target) {
        numbersCopy = numbers;
        sum_up_recursive(numbers, target, new ArrayList<>());
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
            for (int i = 1; i < m; i++) {
                int value = (int) (Double.parseDouble(lines[i].split("\\s")[0]) * 100);
                values.add(value);
            }
            sum_up(values, budget);
            if(file.size() > 0) {
                result.add(String.join(";", file));
                file = new ArrayList<>();
            }
            else
                result.add("Brak kombinacji.");
        }
    }
}