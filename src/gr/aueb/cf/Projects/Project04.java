package gr.aueb.cf.Projects;

import java.util.Scanner;

public class Project04 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X'; // Ξεκινάει ο παίκτης 'X'

        System.out.println("Καλώς ήρθατε στο παιχνίδι Τρίλιζα!");
        printBoard();

        while (true) {
            // Εμφάνιση του παίκτη που παίζει
            System.out.printf("Παίκτης %c, διάλεξε θέση (σειρά και στήλη):%n", currentPlayer);

            // Ανάγνωση της θέσης
            int row, col;
            while (true) {
                System.out.print("Σειρά (0-2): ");
                row = scanner.nextInt();
                System.out.print("Στήλη (0-2): ");
                col = scanner.nextInt();

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Μη έγκυρη θέση. Προσπαθήστε ξανά.");
                } else if (board[row][col] != ' ') {
                    System.out.println("Η θέση είναι ήδη κατειλημμένη. Δοκιμάστε άλλη.");
                } else {
                    break; // Έγκυρη θέση
                }
            }

            // Ενημέρωση του πίνακα
            board[row][col] = currentPlayer;
            printBoard();

            // Έλεγχος για νικητή
            if (isWinner(currentPlayer)) {
                System.out.printf("Ο Παίκτης %c κέρδισε!%n", currentPlayer);
                break;
            }

            // Έλεγχος για ισοπαλία
            if (isDraw()) {
                System.out.println("Ισοπαλία! Το παιχνίδι τελείωσε.");
                break;
            }

            // Εναλλαγή παίκτη
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }
    // Αρχικοποιηση του πινακα της τριλιζας
    public static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    /**
     * Εμφάνιση του πίνακα παιχνιδιού.
     */
    public static void printBoard() {
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%d %c | %c | %c %n", i, board[i][0], board[i][1], board[i][2]);
            if (i < 2) {
                System.out.println(" ---|---|---");
            }
        }
    }

    /**
     * Έλεγχος αν ο παίκτης κέρδισε.
     * @param player Ο χαρακτήρας του παίκτη ('X' ή 'O').
     * @return true αν ο παίκτης κέρδισε, αλλιώς false.
     */
    public static boolean isWinner(char player) {
        // Έλεγχος οριζόντιων γραμμών
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Έλεγχος κάθετων γραμμών
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Έλεγχος διαγωνίων
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    /**
     * Έλεγχος για ισοπαλία.
     * @return true αν υπάρχει ισοπαλία, αλλιώς false.
     */
    public static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Βρέθηκε κενή θέση
                }
            }
        }
        return true; // Δεν υπάρχουν κενές θέσεις
    }
}
