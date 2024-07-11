package gameStart;

import java.util.Scanner;

import static data.DataUtil.*;
import static file.FileHandler.*;
import static gameStart.Util.*;

public class Client {

    public static void register(){
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


    public static void findParking(){
        System.out.println("Vagas disponíveis: ");
        for (int i = 0; i <= 121; i+=5){
            if (triangularNumber(i)){
                System.out.print(i + "\t");
            }
        }
        System.out.println();
    }

    public static void printGamesCatalog() {

        System.out.println(" -- Catálogo de Jogos -- ");
        printArray(columnToSet(fileVendas(), 4, true));
        System.out.println(" -- Fim do Catálogo de Jogos -- ");
    }

    public static void printGraph(){
        int option = 0;

        do{
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
            option = validateOption(0,7);

            switch (option){
                case 1:
                    printFile(fileCallOfDuty());
                    break;
                case 2:
                    printFile(fileFifa());
                    break;
                case 3:
                    printFile(fileHollowKnight());
                    break;
                case 4:
                    printFile(fileMinecraft());
                    break;
                case 5:
                    printFile(fileMortalKombat());
                    break;
                case 6:
                    printFile(fileOvercooked());
                    break;
                case 7:
                    printFile(fileWitcher3());
                    break;
            }

        } while (option != 0);

    }


/*    public static void printGamesByPublisher(){
        String publisher = readKey("editora");
        printGamesByKey(2, publisher, 3);
    }*/

    public static void printGamesByPublisher(){
        String publisher = readKey("editora");
        int publisherColumn = 2;
        int categoryColumn = 3;
        int gameColumn = 4;

        // Busca linhas do ficheiro em que a editora corresponta à pesquisada
        String[][] matrixByPublisher = filterFileToMatrix(fileVendas(), true, publisher, publisherColumn);

        if (matrixByPublisher.length < 1){
            System.out.println("\n-------- ");
            System.out.println("Editora não encontrada.");
            System.out.println("--------\n");
        } else {
            System.out.println("\n********** Catálogo " + matrixByPublisher[0][publisherColumn] + " **********\n");

            // Encontra categorias existentes na matriz correspondente à editora pesquisada
            String[] categorySet = arrayToSet(extractColumnToArray(matrixByPublisher, categoryColumn));

            // Para cada categoria
            for (int i = 0; i < categorySet.length; i++){
                System.out.println(categorySet[i]);
                // Encontrar jogos correspondentes à categoria
                String[][] gamesByCategory = filterMatrixByColumn(matrixByPublisher, categorySet[i], categoryColumn);
                String[] gamesByCategorySet = arrayToSet(extractColumnToArray(gamesByCategory, gameColumn));
                // Imprimir jogos
                printArray(gamesByCategorySet);
                System.out.println();
            }
            System.out.println("\n****** Fim do Catálogo " + matrixByPublisher[0][publisherColumn] + " ******");
        }
    }


    public static void printGamesByCategory(){
        String category = readKey("categoria");
        printGamesByKey(3, category, 2);
    }

    public static void printNewestGame(){
        cleanScreen();
        String[] set = arrayToSet(columnToSet(fileVendas(), 4, true));

        System.out.println(" ------------- ");
        System.out.println("Jogo mais recente: ");
        System.out.println(set[set.length-1]);
        System.out.println(" ------------- ");
        System.out.println();
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

    public static String readKey(String key){
        Scanner input = new Scanner(System.in);
        System.out.print("Pesquisar por " + key + ": ");
        return input.nextLine();
    }

}
