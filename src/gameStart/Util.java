package gameStart;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {

    public static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i < 9){
                System.out.println("0" + (i + 1) + ".\t" + array[i]);
            } else {
                System.out.println((i + 1) + ".\t" + array[i]);
            }
        }
    }

    public static void printMatrix(String[][] matrix) {
        int columnLength = 35;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                String element = matrix[i][j];
                System.out.print(element);
                for (int space = 0; space < (columnLength - element.length()); space++){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static int validateOption(int min, int max) {
        Scanner input = new Scanner(System.in);
        int option = -1;
        boolean validInput;

            do {
                validInput = true;
                System.out.print(">> ");

                try{
                    option = input.nextInt();
                } catch (InputMismatchException e){
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

    public static void cleanScreen() {
        for (int i = 0; i < 50; i++){
            System.out.println("\n");
        }
    }


    public static void openCloseOutput(){
        System.out.println("\n*****************************************************************************************\n");
    }

    public static void printClient(String[] client){
        System.out.println("ID Cliente:\t\t" + client[0]);
        System.out.println("Nome: \t\t\t" + client[1]);
        System.out.println("Telemóvel:\t\t" + client[2]);
        System.out.println("Email: \t\t\t" + client[3]);
        System.out.println("\n----------------");
    }

/*    public static void printClientArray(String[] client){

        System.out.println("ID Cliente:\t\t" + client[0]);
        System.out.println("Nome: \t\t\t" + client[1]);
        System.out.println("Telemóvel:\t\t" + client[2]);
        System.out.println("Email: \t\t\t" + client[3]);
        System.out.println("\n----------------");
    }*/




}
