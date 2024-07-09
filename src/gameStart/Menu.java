package gameStart;

import java.util.Scanner;

import static data.Sales.printGamesCatalog;
import static gameStart.Client.*;
import static gameStart.Util.cleanScreen;

public class Menu {

    public static void startApp(){
        int option;

        do{

            System.out.println(" --- Seja bem vindo à Game Star! ---");
            System.out.println("Informe sua categoria de utilizador: ");
            System.out.println("1 - CLIENTE");
            System.out.println("2 - ADMINISTRADOR");
            System.out.println("\033[3mPara encerrar, digite \"0\".");
            option = validateOption(0,2);

            switch (option){
                case 0:
                    // TODO: Show copyrights
                    System.out.println("APRESENTAR COPYRIGHTS");
                    break;
                case 1:
                    clientMenu();
                    break;
                case 2:
                    // TODO: Chamar menu administrador
                    System.out.println("OPÇÃO ADMINISTRADOR");
                    break;
            }

        } while (option != 0);
    }

    public static void clientMenu(){
        int option;

        do {

            System.out.println();
            System.out.println(" -- ÁREA DO CLIENTE -- ");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Novo registro");
            System.out.println("2 - Procurar estacionamento");
            System.out.println("3 - Imprimir Catálogo");
            System.out.println("4 - Imprimir Catálogos Gráficos");
            System.out.println("5 - Imprimir Catálogo Editora");
            System.out.println("6 - Imprimir Catálogo Categoria");
            System.out.println("7 - Imprimir jogo mais recente");
            System.out.println("0 - SAIR");
            option = validateOption(0,7);

            switch (option){
                case 0:
                    cleanScreen();
                    break;
                case 1:
                    register();
                    break;
                case 2:
                    findParking();
                    break;
                case 3:
                    printGamesCatalog();
                    break;
                case 4:
                    // TODO: Imprimir Catálogos Gráficos
                    break;
                case 5:
                    // TODO: Imprimir Catálogo Editora
                    break;
                case 6:
                    // TODO: Imprimir Catálogo Categoria
                    break;
                case 7:
                    // TODO: Imprimir jogo mais recente
                    break;
            }

        } while (option != 0);
    }

    public static void adminMenu(){}

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

}
