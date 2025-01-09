package gr.aueb.cf.Projects;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Project01 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("C:/tmp/tmp3/project1.txt"));
             PrintStream ps = new PrintStream("C:/tmp/tmp3/project1Out.txt", StandardCharsets.UTF_8)){

            final int SIX_NUM_LINE = 6;
            int[] arrNumbers = new int[29];
            int pivot = 0;
            int num;

            // Ανάγνωση αριθμών από το αρχείο
            while ((num = in.nextInt()) != -1 && pivot <= 28) {
                arrNumbers[pivot++] = num;
            }

            // Ταξινόμηση του πίνακα
            int[] numbers = Arrays.copyOfRange(arrNumbers, 0, pivot);
            Arrays.sort(numbers);

            // Παραγωγή συνδυασμών και εφαρμογή κριτηρίων φιλτραρίσματος
            generateCombinations(numbers, SIX_NUM_LINE, 0, new int[SIX_NUM_LINE], 0, ps);
            System.out.println(arrNumbers);

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Αναδρομική μέθοδος για την παραγωγή συνδυασμών με φιλτράρισμα.
     *
     * @param numbers         Ο πίνακας με τους διαθέσιμους αριθμούς.
     * @param combinationSize Το μέγεθος του συνδυασμού (6).
     * @param index           Ο τρέχων δείκτης του πίνακα numbers.
     * @param result          Ο τρέχων συνδυασμός.
     * @param level           Το τρέχον επίπεδο του συνδυασμού.
     * @param ps              Το PrintStream για την εκτύπωση των αποτελεσμάτων.
     */

    public static void generateCombinations(int[] numbers, int combinationSize, int index, int[] result, int level, PrintStream ps) {
        // Αν έχει δημιουργηθεί πλήρης συνδυασμός
        if (level == combinationSize) {
            if (isValidCombination(result)) { // Ελέγχει αν ο συνδυασμός πληροί τα κριτήρια
                ps.printf("%d %d %d %d %d %d\n",
                        result[0], result[1], result[2], result[3], result[4], result[5]);

                System.out.printf("%d %d %d %d %d %d\n",
                        result[0], result[1], result[2], result[3], result[4], result[5]);
            }
            return;
        }

        // Αν φτάσουμε στο τέλος του πίνακα
        if (index == numbers.length) {
            return;
        }

        // Επιλέγουμε τον αριθμό στην τρέχουσα θέση
        result[level] = numbers[index];

        // Προχωράμε στο επόμενο επίπεδο με τον τρέχοντα αριθμό
        generateCombinations(numbers, combinationSize, index + 1, result, level + 1, ps);

        // Αγνοούμε τον τρέχοντα αριθμό και προχωράμε
        generateCombinations(numbers, combinationSize, index + 1, result, level, ps);
    }


    /**
     * Ελέγχει αν ο συνδυασμός πληροί τα κριτήρια.
     *
     * @param combination Ο συνδυασμός προς έλεγχο.
     * @return true αν ο συνδυασμός είναι έγκυρος, false διαφορετικά.
     */

    public static boolean isValidCombination(int[] combination) {
        return isEven(combination, 4) &&
                isOdd(combination, 4) &&
                !consecutive(combination) &&
                sameEnding(combination, 3) &&
                sameTen(combination, 3);
    }

    /**
     * Επιστρέφει true αν υπάρχουν το πολύ maximumFour ζυγοί αριθμοί.
     */


    public static boolean isEven(int[] arr, int maximumFour) {
        long evenCount = 0;

        evenCount = Arrays.stream(arr)
                .filter( num -> num % 2 == 0)
                .count();
        return evenCount <= maximumFour;
    }

    /**
     * Επιστρέφει true αν υπάρχουν το πολύ maximumFour μονοί αριθμοί.
     */

    public static boolean isOdd(int[] arr, int maximumFour) {
        long oddCount = 0;

        oddCount = Arrays.stream(arr)
                .filter(num -> num % 2 != 0)
                .count();

        return oddCount <= maximumFour;
    }


    /**
     * Επιστρέφει true αν υπάρχουν περισσότεροι από 2 συνεχόμενοι αριθμοί.
     */

    public static boolean consecutive(int[] arr) {
        int consecutiveCount = 0;

        for (int i = 0; i < 3; i++) {
            if (arr[i] == arr[i + 1] - 1 && arr[i] == arr[i + 2] -2) {
                consecutiveCount++;
                break;
            }
        }
        return consecutiveCount >=1;
    }

    /**
     * Επιστρέφει true αν υπάρχουν το πολύ maximumThree ίδιοι λήγοντες.
     */

    public static boolean sameEnding(int[] arr, int maximumThree) {
        int[] endings = new int[10];

        for (int num : arr) {
            endings[num % 10]++;
        }
        return Arrays.stream(endings).anyMatch( e -> e <= maximumThree);
    }

    /**
     * Επιστρέφει true αν υπάρχουν το πολύ maximumThree αριθμοί στην ίδια δεκάδα.
     */

    public static boolean sameTen(int[] arr, int maximumThree) {
        int[] ten = new int[3];

        for (int num : arr) {
            ten[num/10]++;
        }
        return Arrays.stream(ten).anyMatch(t -> t <= maximumThree);
    }



}
