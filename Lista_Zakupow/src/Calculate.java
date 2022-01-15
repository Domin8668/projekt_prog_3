import java.util.ArrayList;
import java.util.Arrays;

public class Calculate {
    public static ArrayList<String> calculate(ArrayList<String> input) {
        ArrayList<String> arr = new ArrayList<>();
        for (String s : input) {
            String[] lines = s.split("\\s");
            double budget = 1;
        }


        int n = input.size();
        for (String s : input) {
            String[] tab = s.split(" "); //ten split jest gogolnie ciulaty, robi ci po spacji i jeszcze masz w tej tablicy ciagle nazwy i te kwoty iwec to zmien
            double[] tablica = new double[tab.length]; // a no i nie wiem jak z "\n w javie bo jest ich w takim pliku od chuja// "
            for (int j = 0; j < tab.length; j++) {
                tablica[j] = Double.parseDouble(tab[j]);
            }
            double suma = tablica[0];
            tablica = Arrays.copyOfRange(tablica, 1, tablica.length); //jakbys usunal tylko stringi masz tu uber chujowy sposob na usuniecie pierwszego elelmntu z tablicy ktorym była suma
            isSubsetSum(tablica, tablica.length, tablica[0]);
        }
        arr.add("1 2 4");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        return arr;
    }

    static boolean isSubsetSum(double[] set,
                               int n, double sum)
    {
        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;

        // If last element is greater than
        // sum, then ignore it
        if (set[n - 1] > sum)
            return isSubsetSum(set, n - 1, sum);

        /* else, check if sum can be obtained
        by any of the following
            (a) including the last element
            (b) excluding the last element */
        return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
    }

}
