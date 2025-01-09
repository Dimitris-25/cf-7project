package gr.aueb.cf.Projects;

import java.util.Scanner;

public class Project05{

    // Πίνακας 30x12 για το θέατρο
    private static boolean[][] theatre = new boolean[30][12];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Καλώς ήρθατε στη διαχείριση θεάτρου!");
        while (true) {
            System.out.println("\nΕπιλέξτε ενέργεια:");
            System.out.println("1. Κράτηση θέσης (Book)");
            System.out.println("2. Ακύρωση κράτησης (Cancel)");
            System.out.println("3. Εμφάνιση θέσεων (Display)");
            System.out.println("4. Έξοδος");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Κράτηση θέσης
                    System.out.print("Δώστε στήλη (A-L): ");
                    char column = scanner.next().toUpperCase().charAt(0);
                    System.out.print("Δώστε σειρά (1-30): ");
                    int row = scanner.nextInt();
                    book(column, row);
                    break;
                case 2:
                    // Ακύρωση κράτησης
                    System.out.print("Δώστε στήλη (A-L): ");
                    column = scanner.next().toUpperCase().charAt(0);
                    System.out.print("Δώστε σειρά (1-30): ");
                    row = scanner.nextInt();
                    cancel(column, row);
                    break;
                case 3:
                    // Εμφάνιση θέσεων
                    displaySeats();
                    break;
                case 4:
                    // Έξοδος
                    System.out.println("Ευχαριστούμε που χρησιμοποιήσατε τη διαχείριση θεάτρου!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Μη έγκυρη επιλογή. Προσπαθήστε ξανά.");
            }
        }
    }

    /**
     * Κράτηση θέσης (Book).
     *
     * @param column Η στήλη της θέσης (A-L).
     * @param row    Η σειρά της θέσης (1-30).
     */
    public static void book(char column, int row) {
        int colIndex = column - 'A'; // Μετατροπή της στήλης σε δείκτη (0-11)
        int rowIndex = row - 1;      // Μετατροπή της σειράς σε δείκτη (0-29)

        if (isValidPosition(colIndex, rowIndex)) {
            if (!theatre[rowIndex][colIndex]) {
                theatre[rowIndex][colIndex] = true;
                System.out.printf("Η θέση %c%d κρατήθηκε επιτυχώς.%n", column, row);
            } else {
                System.out.printf("Η θέση %c%d είναι ήδη κρατημένη.%n", column, row);
            }
        } else {
            System.out.println("Μη έγκυρη θέση. Προσπαθήστε ξανά.");
        }
    }

    /**
     * Ακύρωση κράτησης (Cancel).
     *
     * @param column Η στήλη της θέσης (A-L).
     * @param row    Η σειρά της θέσης (1-30).
     */
    public static void cancel(char column, int row) {
        int colIndex = column - 'A'; // Μετατροπή της στήλης σε δείκτη (0-11)
        int rowIndex = row - 1;      // Μετατροπή της σειράς σε δείκτη (0-29)

        if (isValidPosition(colIndex, rowIndex)) {
            if (theatre[rowIndex][colIndex]) {
                theatre[rowIndex][colIndex] = false;
                System.out.printf("Η κράτηση για τη θέση %c%d ακυρώθηκε επιτυχώς.%n", column, row);
            } else {
                System.out.printf("Η θέση %c%d δεν είναι κρατημένη.%n", column, row);
            }
        } else {
            System.out.println("Μη έγκυρη θέση. Προσπαθήστε ξανά.");
        }
    }

    /**
     * Εμφάνιση θέσεων (Display).
     */
    public static void displaySeats() {
        System.out.println("Κατάσταση θέσεων:");
        System.out.print("   ");
        for (char c = 'A'; c <= 'L'; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < 12; j++) {
                System.out.print((theatre[i][j] ? "X" : "O") + " ");
            }
            System.out.println();
        }
    }

    /**
     * Έλεγχος αν η θέση είναι έγκυρη.
     *
     * @param colIndex Ο δείκτης της στήλης (0-11).
     * @param rowIndex Ο δείκτης της σειράς (0-29).
     * @return true αν η θέση είναι έγκυρη, αλλιώς false.
     */
    private static boolean isValidPosition(int colIndex, int rowIndex) {
        return rowIndex >= 0 && rowIndex < 30 && colIndex >= 0 && colIndex < 12;
    }
}

