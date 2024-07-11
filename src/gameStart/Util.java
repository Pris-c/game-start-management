package gameStart;

import java.util.Scanner;

public class Util {

    public static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println((i + 1) + ".\t" + array[i]);
        }
    }

    public static void printMatrix(String[][] matrix) {
        int columnLength = 35;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                String element = matrix[i][j];
                System.out.print(element);
                for (int space = 0; space < (columnLength - element.length()); space++)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static int validateOption(int min, int max) {
        Scanner input = new Scanner(System.in);
        int option;
        boolean validInput;

        do {
            validInput = true;
            System.out.print(">> ");
            option = input.nextInt();

            if (option < min || option > max) {
                validInput = false;
                System.out.println("Opção Inválida! Por favor, tente novamente.");
            }

        } while (!validInput);

        cleanScreen();
        return option;
    }

    public static void cleanScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }


}
