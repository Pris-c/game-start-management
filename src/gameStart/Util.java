package gameStart;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {

    /**
     * Recebe um array de Strings e imprime cada elemento em uma linha.
     * As linhas são numeradas e formatadas com um padrão de espaçamento.
     *
     * @param array Array de Strings a ser impresso.
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
     * Recebe uma matriz de Strings e imprime suas informações.
     * Cada linha é formatadas com um padrão de espaçamento.
     *
     * @param matrix Matriz de Strings a ser impressa.
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
     * Lê e valida input do utilizador.
     * A opção inserida pelo utilizador deve ser do tipo int e estar entre o intervalo de valores definido.
     * Funciona em loop, que é encerrado quando um valor válido é inserido.
     *
     * @param min O valor mínimo do intervalo.
     * @param max O valor máximo do intervalo.
     * @return A opção válida inserida pelo utilizador.
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
     * Imprime uma série de linhas vazias para simular a limpeza do console.
     */
    public static void cleanScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }


    /**
     * Imprime uma linha de asteriscos para delimitar o inicio e o fim de
     * uma sessão de output.
     */
    public static void openCloseOutput() {
        System.out.println("\n*****************************************************************************************\n");
    }


    /**
     * Recebe um array de Strings contendo informações de um cliente.
     * Imprime o conteúdo em uma linha, formatada com um padrão de espaçamento.
     *
     * @param client O array de Strings com as informações do cliente.
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
     * Exibe mensagem de Ficheiro não encontrado.
     */
    public static void printAdvetisingFileNotFound() {
        openCloseOutput();
        System.out.println("Arquivo não encontrado. Por favor, contacte o suporte.");
        openCloseOutput();

    }

    /**
     * Exibe mensagem de Ficheiro vazio
     */
    public static void printAdvetisingNoSuchElementException() {
        openCloseOutput();
        System.out.println("Não há informações a serem lidas no ficheiro.");
        openCloseOutput();

    }

}
