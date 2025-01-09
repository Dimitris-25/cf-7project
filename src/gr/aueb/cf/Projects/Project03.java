package gr.aueb.cf.Projects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project03 {
    public static void main(String[] args) {
        // Δηλωση του πινακα 128 Χ 2
        int[][] fileCharacters = new int[128][2];

        // Αρχικοποιηση του πινακα
        for (int i = 0; i < 128; i++) {
            fileCharacters[i][0] = i; //  Αποθηκευση ASCII
            fileCharacters[i][1] = 0; //  Συχνοτητα χαρακτηρων
        }

        // Διαβασμα χαρκτηρων απο αρχειο

        try (Scanner in = new Scanner(new File("C:/tmp/tmp3/project3.txt"))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                for (char c : line.toCharArray()) {
                    if (!Character.isWhitespace(c)) {  // αγνοουμε τα κενα
                        fileCharacters[c][1]++; // αυξηση συχνοτητας χαρακτηρα
                    }
                }
            }


        }catch (FileNotFoundException e) {
            System.out.println("To αρχειο δεν βρεθηκε");
            return;
        }
        // Εμφάνιση των χαρακτήρων και συχνοτήτων ταξινομημένων ανά χαρακτήρα (αλφαβητικά)
        System.out.println("Στατιστικά ανά χαρακτήρα (αλφαβητική σειρά):");
        for (int i = 0; i < fileCharacters.length; i++) {
            if (fileCharacters[i][1] > 0) {
                System.out.printf("%c: %d\n", (char) fileCharacters[i][0],fileCharacters[i][1]);
            }
        }
        // Ταξινόμηση κατά συχνότητα (φθίνουσα σειρά)
        for (int i = 0; i < fileCharacters.length - 1; i++) {
            for (int j = i + 1; j < fileCharacters.length; j++) {
                if (fileCharacters[j][1] > fileCharacters[i][1]) {
                    // Ανταλλαγή (swap) για φθίνουσα ταξινόμηση
                    int[] temp = fileCharacters[i];
                    fileCharacters[i] = fileCharacters[j];
                    fileCharacters[j] = temp;
                }
            }
        }
        // Εμφάνιση των χαρακτήρων και συχνοτήτων ταξινομημένων ανά συχνότητα (φθίνουσα σειρά)
        System.out.println("\nΣτατιστικά ανά συχνότητα εμφάνισης:");
        for (int i = 0; i < fileCharacters.length; i++) {
            if (fileCharacters[i][1] > 0) { // Εμφάνιση μόνο χαρακτήρων με συχνότητα > 0
                System.out.printf("%c: %d\n", (char) fileCharacters[i][0], fileCharacters[i][1]);
            }
        }
    }
}
