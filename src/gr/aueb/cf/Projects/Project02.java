package gr.aueb.cf.Projects;

public class Project02 {
    public static void main(String[] args) {
        // Παράδειγμα πίνακα
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // Εκτελούμε τον αλγόριθμο και καταγράφουμε το αποτέλεσμα
        int result = findMaxSubarraySum(arr);

        // Εκτύπωση αποτελέσματος
        System.out.println("Το μέγιστο άθροισμα υποπίνακα είναι: " + result);
    }

    /**
     * Επιστρέφει το μέγιστο άθροισμα συνεχόμενου υποπίνακα.
     *
     * @param arr Ο πίνακας ακεραίων
     * @return Το μέγιστο άθροισμα
     */
    public static int findMaxSubarraySum(int[] arr) {
        int localMaximum = arr[0]; // Το μέγιστο άθροισμα που τελειώνει στην τρέχουσα θέση
        int globalMaximum = arr[0]; // Το μέγιστο άθροισμα που έχουμε βρει συνολικά

        // Ο βρόχος επεξεργάζεται κάθε στοιχείο του πίνακα μία φορά.
        // Η πολυπλοκότητα είναι O(n), καθώς το πλήθος των επαναλήψεων είναι ίσο με το μήκος του πίνακα (n).
        for (int i = 1; i < arr.length; i++) {
            // Υπολογισμός localMaximum
            localMaximum = Math.max(localMaximum + arr[i], arr[i]);
            // Ενημέρωση globalMaximum
            globalMaximum = Math.max(globalMaximum, localMaximum);
        }

        return globalMaximum;
    }
}


