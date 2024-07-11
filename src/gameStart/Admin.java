package gameStart;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

import static data.DataUtil.*;
import static file.FileHandler.*;
import static file.FileHandler.fileWitcher3;
import static gameStart.Util.*;

public class Admin {

    public static boolean login(){

        Scanner input = new Scanner(System.in);
        System.out.print("Nome de utilizador: ");
        String username = input.nextLine();

        System.out.print("Senha: ");
        String password = input.nextLine();

        if (checkLogin(username, password)){
            return true;
        } else {
            System.out.println(" ------ ");
            System.out.println("Utilizador ou senha incorretos.");
            System.out.println(" ------ ");
            return false;
        }
    }

    public static void consultFile(){
        int option = 0;

        do{
            System.out.println(" -- Consulta de Ficheiros -- ");
            System.out.println("Escolha o ficheiro a ser consultado: ");
            System.out.println("1 - Vendas");
            System.out.println("2 - Clientes");
            System.out.println("3 - Categorias");
            System.out.println("\033[3mPara voltar, digite \"0\".");
            option = validateOption(0,7);

            switch (option){
                case 1:
                    System.out.println("\n   ---  VENDAS   ---");
                    printMatrix(extractToMatrix(fileVendas(), false));
                    System.out.println(" -------------------\n");
                    break;
                case 2:
                    System.out.println("   ---  CLIENTES   ---");
                    printMatrix(extractToMatrix(fileClientes(), false));
                    System.out.println(" -------------------");
                    break;
                case 3:
                    System.out.println("   ---  CATEGORIAS   ---");
                    printMatrix(extractToMatrix(fileCategorias(), false));
                    System.out.println(" -------------------");
                    break;
            }
        } while (option != 0);

    }

    public static void consultSalesTotal(){
        System.out.println("   ---  ANÁLISE DE VENDAS   ---");
        System.out.println("Vendas efetuadas: " + (countLines(fileVendas())-1));
        System.out.println("Valor total: € " + sumColumn(fileVendas(), true, 5));
        System.out.println(" -------------------");
    }

    public static void consultTotalProfit(){
        System.out.println("   ---  CONSULTA AO LUCRO   ---");
        System.out.println("Lucro total: " + calculateTotalProfit());
        System.out.println(" -------------------");
    }

    public static void consultClient(){
        Scanner input = new Scanner(System.in);
        System.out.println("   ---  PESQUISA DE CLIENTES   ---");
        System.out.print("Id do cliente: ");

        try {
            int clientId = input.nextInt();
            input.nextLine();
            String[] client = findClient(clientId);

            if (client.length == 0){
                System.out.println("\nID " + clientId + " não encontrado.");
            } else {
                System.out.println("\n-- Cliente " + clientId + " --");
                System.out.println("Nome: " + client[1]);
                System.out.println("Telemóvel: " + client[2]);
                System.out.println("Email: " + client[3]);
            }
            System.out.println(" -------------------\n");

        } catch (InputMismatchException e){
            System.out.println("\nID inválido!\n");
        }

    }


    // Considerar que não há jogos com o mesmo valor
    // Jogo mais caro: Imprima qual o jogo mais caro e os clientes que o compraram.
        public static void showMostExpensiveGame(){
            // idVenda;idCliente;editora;categoria;jogo;valor
            // idCliente;nome;telemovel;email

            double biggestValue = findBiggestValue(fileVendas(), true, 5);

            int valueColumn = 5;
            int gameColumn = 4;

            String[][] expensiveGames = filterFileToMatrixByDoubleValue(fileVendas(), true, biggestValue, valueColumn);

            String[] games = extractColumnToArray(expensiveGames, gameColumn);
            String[] gamesSet = arrayToSet(games);

            String[] clientIds = new String[expensiveGames.length];
            int countClientsId = 0;

            String game;
            String id;
            for (int i = 0; i < gamesSet.length; i++){
                System.out.println(" -- Jogo " + gamesSet[i] + " --");

                // Refatorar
                for (int l = 0; l < expensiveGames.length; l++){
                    game = expensiveGames[l][gameColumn];
                    if (game.equals(games[i])){
                        id = expensiveGames[l][1];
                        if(!arrayContain(clientIds, id, countClientsId)){
                            clientIds[countClientsId] = id;
                            countClientsId++;
                        }
                    }
                }

                String[] clientsIdArray = new String[countClientsId];
                for (int k = 0; k < countClientsId; k++ ){
                    clientsIdArray[k] = clientIds[k];
                }

                String[] clientsIdSet = arrayToSet(clientsIdArray);

                String[] client;
                System.out.println("Comprado por: ");
                for (int k = 0; k < clientsIdSet.length; k++){
                    client = findClient(Integer.parseInt(clientsIdSet[k]));
                    System.out.println(client[1]);
                }
            }

        }

}
