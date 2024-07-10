package gameStart;

import java.util.Scanner;

public class Util {

    public static void printArray(String[] array){
        for (int i = 0; i < array.length; i++){
            System.out.println((i+1) + ".\t" + array[i]);
        }

    }

    public static boolean triangularNumber(int num){
        int i = 1;
        int soma = 0;

        while (soma < num){
            soma += i;
            i++;
        }

        return soma == num;
    }

    public static int validateOption(int min, int max){
        Scanner input = new Scanner(System.in);
        int option;
        boolean validInput;

        do{
            validInput = true;
            System.out.print(">> ");
            option = input.nextInt();

            if (option < min || option > max){
                validInput = false;
                System.out.println("Opção Inválida! Por favor, tente novamente.");
            }

        } while(!validInput);

        cleanScreen();
        return option;
    }


    public static void cleanScreen(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void printAdvetisingFileNotFound(){
        System.out.println("Arquivo não encontrato. Por favor, contacte o suporte.");
    }



}
