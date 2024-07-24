package gameStart;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {


    /**
     * Receives an array of Strings and prints each element on a separate line.
     * The lines are numbered and formatted with a spacing pattern.
     *
     * @param array Array of Strings to be printed.
     */
    public static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i < 9) {
                System.out.println("0" + (i + 1) + ".\t" + array[i]);
            } else {
                System.out.println((i + 1) + ".\t" + array[i]);
            }
        }
    }

    /**
     * Receives a matrix of Strings and prints its information.
     * Each line is formatted with a spacing pattern.
     *
     * @param matrix Matrix of Strings to be printed.
     */
    public static void printMatrix(String[][] matrix) {
        int columnLength = 35;
        openCloseOutput();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                String element = matrix[i][j];
                System.out.print(element);
                for (int space = 0; space < (columnLength - element.length()); space++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        openCloseOutput();
    }

    /**
     * Reads and validates user input.
     * The option entered by the user must be of type int and within the defined range of values.
     * It runs in a loop, which is terminated when a valid value is entered.
     *
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return The valid option entered by the user.
     */
    public static int validateOption(int min, int max) {
        Scanner input = new Scanner(System.in);
        int option;
        boolean validInput;

        do {
            validInput = true;
            System.out.print(">> ");

            try {
                option = input.nextInt();
            } catch (InputMismatchException e) {
                validInput = false;
                option = -1;
            }

            if (option < min || option > max) {
                validInput = false;
                System.out.println("\n-- Opção Inválida! Por favor, tente novamente. --");
                input.nextLine();
            }

        } while (!validInput);

        cleanScreen();
        return option;
    }

    /**
     * Prints a series of empty lines to simulate clearing the console.
     */
    public static void cleanScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }


    /**
     * Prints a line of asterisks to delimit the beginning and end of an output session.
     */
    public static void openCloseOutput() {
        System.out.println("\n*****************************************************************************************\n");
    }


    /**
     * Receives an array of Strings containing information about a client.
     * Prints the content in a line, formatted with a spacing pattern.
     *
     * @param client The array of Strings with the client's information.
     */
    public static void printClient(String[] client) {
        int columnLength = 35;
        for (int j = 0; j < client.length; j++) {
            String element = client[j];
            System.out.print(element);
            for (int space = 0; space < (columnLength - element.length()); space++) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }


    /**
     * Displays a "File Not Found" message.
     */
    public static void printAdvetisingFileNotFound() {
        openCloseOutput();
        System.out.println("Arquivo não encontrado. Por favor, contacte o suporte.");
        openCloseOutput();

    }

    /**
     * Displays an "Empty File" message.
     */
    public static void printAdvetisingNoSuchElementException() {
        openCloseOutput();
        System.out.println("Não há informações a serem lidas no ficheiro.");
        openCloseOutput();

    }

}
