package gameStart;

import java.util.Scanner;

import static data.DataUtil.*;
import static file.FileHandler.*;
import static gameStart.Util.*;

public class Client {

    public static void register() {
        Scanner input = new Scanner(System.in);
        String clientName, clientPhone, clientMail;

        System.out.println(" -- Registrar Cliente -- ");
        System.out.print("Nome do cliente: ");
        clientName = input.nextLine();
        System.out.print("Telefone para contacto: ");
        clientPhone = input.nextLine();
        System.out.print("Endereço de e-mail: ");
        clientMail = input.nextLine();

        cleanScreen();
        openCloseOutput();
        System.out.println("Cliente registrado com sucesso!\n " + clientName + " | " + clientPhone + " | " + clientMail);
        openCloseOutput();
    }


    public static void findParking() {
        openCloseOutput();
        System.out.println("Vagas disponíveis: ");
        for (int i = 0; i <= 121; i += 5) {
            if (triangularNumber(i)) {
                System.out.print(i + "\t");
            }
        }
        System.out.println();
        openCloseOutput();
    }

    public static void printGamesCatalog() {
        openCloseOutput();
        System.out.println(" -- Catálogo de Jogos -- \n");
        printArray(fileColumnToSet(fileVendas(), 4, true));
        openCloseOutput();
    }

    public static void printGraph() {
        int option;

        do {
            System.out.println(" -- Catálogo Gráfico -- ");
            System.out.println("Escolha o gráfico a ser impresso: ");
            System.out.println("1 - Call Of Duty");
            System.out.println("2 - Fifa");
            System.out.println("3 - Hollow Knight");
            System.out.println("4 - Minecraft");
            System.out.println("5 - Mortal Kombat");
            System.out.println("6 - Overcooked");
            System.out.println("7 - Witcher 3");
            System.out.println(" Para voltar, digite \"0\".");
            option = validateOption(0, 7);

            cleanScreen();

            switch (option) {
                case 1:
                    printFile(graphCallOfDuty());
                    break;
                case 2:
                    printFile(graphFifa());
                    break;
                case 3:
                    printFile(graphHollowKnight());
                    break;
                case 4:
                    printFile(graphMinecraft());
                    break;
                case 5:
                    printFile(graphMortalKombat());
                    break;
                case 6:
                    printFile(graphOvercooked());
                    break;
                case 7:
                    printFile(graphWitcher3());
                    break;
            }
            openCloseOutput();
        } while (option != 0);
    }


    public static void printGamesByPublisher() {
        boolean finish = false;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println(" Para retornar, digite \"0\".");
            System.out.print("Pesquisar por editora: ");
            String publisher = input.nextLine();
            if (publisher.equals("0")){
                finish = true;
                cleanScreen();
            } else {
                printGamesByKey(publisher, 2, 3);
            }
        } while (!finish);
    }

    public static void printGamesByCategory() {
        boolean finish = false;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println(" Para retornar, digite \"0\".");
            System.out.print("Pesquisar por categoria: ");
            String category = input.nextLine();
            if (category.equals("0")){
                finish = true;
                cleanScreen();
            } else {
                printGamesByKey(category, 3, 2);
            }
        } while (!finish);
    }

    public static void printNewestGame() {
        cleanScreen();
        String[] set = arrayToSet(fileColumnToSet(fileVendas(), 4, true));

        openCloseOutput();
        System.out.println("Jogo mais recente: " + set[set.length - 1]) ;
        openCloseOutput();
    }

    public static boolean triangularNumber(int num) {
        int i = 1;
        int soma = 0;

        while (soma < num) {
            soma += i;
            i++;
        }
        return soma == num;
    }

}
