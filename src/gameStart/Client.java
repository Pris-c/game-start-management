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
        System.out.println("Cliente registrado com sucesso: " + clientName + " | " + clientPhone + " | " + clientMail);
    }


    public static void findParking() {
        System.out.println("Vagas disponíveis: ");
        for (int i = 0; i <= 121; i += 5) {
            if (triangularNumber(i)) {
                System.out.print(i + "\t");
            }
        }
        System.out.println();
    }

    public static void printGamesCatalog() {

        System.out.println(" -- Catálogo de Jogos -- ");
        printArray(fileColumnToSet(fileVendas(), 4, true));
        System.out.println(" -- Fim do Catálogo de Jogos -- ");
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
            System.out.println("\033[3mPara voltar, digite \"0\".");
            option = validateOption(0, 7);

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

        } while (option != 0);

    }


    public static void printGamesByPublisher() {
        Scanner input = new Scanner(System.in);
        System.out.print("Pesquisar por editora: ");
        String publisher = input.nextLine();
        printGamesByKey(publisher, 2, 3);
    }

    public static void printGamesByCategory() {
        Scanner input = new Scanner(System.in);
        System.out.print("Pesquisar por categoria: ");
        String category = input.nextLine();
        printGamesByKey(category, 3, 2);
    }

    public static void printNewestGame() {
        cleanScreen();
        String[] set = arrayToSet(fileColumnToSet(fileVendas(), 4, true));

        System.out.println(" ------------- ");
        System.out.println("Jogo mais recente: ");
        System.out.println(set[set.length - 1]);
        System.out.println(" ------------- ");
        System.out.println();
    }


    public static String[] filterGames(String[][] matrix, String key, int column) {
        final int gameColumn = 4;
        String[] arrayUniqueElements = new String[matrix.length];
        int countElements = 0;

        String game;
        for (int i = 0; i < matrix.length; i++) {
            game = matrix[i][gameColumn];

            // Imprimir jogos correspondentes ao filtro aplicado
            if (key.equalsIgnoreCase(matrix[i][column])) {
                if (!arrayContains(arrayUniqueElements, game, countElements)) {
                    arrayUniqueElements[countElements] = game;
                    countElements++;
                }
            }
        }
        return arrayToSet(cleanEmptyArrayPlaces(arrayUniqueElements, countElements));
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
