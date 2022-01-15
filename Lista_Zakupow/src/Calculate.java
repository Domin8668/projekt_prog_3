import java.util.ArrayList;
import java.util.Arrays;

// TODO
// Dodać zwracanie według indeksów a nie wartości
class Calculate {
    private final ArrayList<String> result = new ArrayList<>();
    public ArrayList<String> getResult() {
        return result;
    }

    void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
        int s = 0;
        for (int x : partial) s += x;
        if (s == target)
            result.add("sum(" + Arrays.toString(partial.toArray()) + ")=" + target);
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
            System.out.println("Budżet: " + budget);
            for (int i = 1; i < m; i++) {
                int value = (int) (Double.parseDouble(lines[i].split("\\s")[0]) * 100);
                values.add(value);
                System.out.println("Wartość: " + value);
            }
            int size = result.size();
            sum_up(values, budget);
            if(size == result.size())
                result.add("Brak kombinacji.");
        }
    }
}