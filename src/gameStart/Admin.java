package gameStart;

import java.util.Scanner;

import static data.DataUtil.*;
import static file.FileHandler.*;
import static gameStart.Util.*;

public class Admin {

    /**
     * Lê nome de usuario e senha e valida as informações.
     * Exibe mensagem de erro se a checagem do login for false.
     *
     * @return true, se bem sucedido, e false, se a combinação de credenciais for inválida.
     */
    public static boolean login() {

        Scanner input = new Scanner(System.in);
        System.out.print("Nome de utilizador: ");
        String username = input.nextLine();

        System.out.print("Senha: ");
        String password = input.nextLine();

        if (checkLogin(username, password)) {
            return true;
        } else {
            cleanScreen();
            openCloseOutput();
            System.out.println("Utilizador ou senha incorretos.");
            openCloseOutput();
            return false;
        }
    }

    /**
     * Permite que o usuario escolha um dos ficheiros disponíveis para se exibido na tela.
     * Funciona em loop, até que o utilizador informe o valor "0".
     */
    public static void consultFile() {
        int option;

        do {
            System.out.println(" -- Consulta de Ficheiros -- ");
            System.out.println("Escolha o ficheiro a ser consultado: ");
            System.out.println("1 - Vendas");
            System.out.println("2 - Clientes");
            System.out.println("3 - Categorias");
            System.out.println("Para voltar, digite \"0\".");
            option = validateOption(0, 3);

            switch (option) {
                case 1:
                    System.out.println("\n   ---  VENDAS   ---");
                    printMatrix(extractCSVFileToMatrix(fileVendas(), false));
                    break;
                case 2:
                    System.out.println("   ---  CLIENTES   ---");
                    printMatrix(extractCSVFileToMatrix(fileClientes(), false));
                    break;
                case 3:
                    System.out.println("   ---  CATEGORIAS   ---");
                    printMatrix(extractCSVFileToMatrix(fileCategorias(), false));
                    break;
            }
        } while (option != 0);

    }

    /**
     * Imprime o número e valor total de vendas efetuadas, de acordo com o ficheiro de vendas.
     */
    public static void consultSalesTotal() {
        openCloseOutput();
        System.out.println("   ---  ANÁLISE DE VENDAS   ---");
        System.out.println("Vendas efetuadas: " + (countLines(fileVendas()) - 1));
        System.out.println("Valor total: € " + sumColumn(fileVendas(), true, 5));
        openCloseOutput();
    }

    /**
     * Exibe o valor do lucro tatal.
     */
    public static void consultTotalProfit() {
        openCloseOutput();
        System.out.println("   ---  CONSULTA AO LUCRO   ---");
        System.out.println("Lucro total: € " + calculateTotalProfit());
        openCloseOutput();
    }

    /**
     * Lê o id de um cliente e exibe suas informações, se encontrado.
     * Se não houver cliente com o id informado, exibe mensagem de aviso.
     */
    public static void consultClient() {
        Scanner input = new Scanner(System.in);
        System.out.println("   ---  PESQUISA DE CLIENTES   ---");
        System.out.print("Id do cliente: ");
        String clientId = input.next();
        String[] client = findClient(clientId);

        cleanScreen();
        openCloseOutput();
        if (client.length == 0) {
            System.out.println("ID " + clientId + " não encontrado.");
        } else {
            System.out.println("------- CLIENTE ENCONTRADO -------");
            System.out.println("ID:\t\t\t\t" + clientId);
            System.out.println("Nome: \t\t\t" + client[1]);
            System.out.println("Telemóvel:\t\t" + client[2]);
            System.out.println("Email: \t\t\t" + client[3]);
        }
        openCloseOutput();
    }

    /**
     * Exibe as informações referente ao jogo mais caro do arquivo de vendas:
     * Valor e nome dos jogos, e lista de clientes que o compraram.
     */
    public static void showMostExpensiveGame() {
        int valueColumn = 5;
        int gameColumn = 4;

        String biggestValue = findBiggestValue(fileVendas(), true, valueColumn);

        String[][] expensiveGames = filterFileToMatrix(fileVendas(), true, biggestValue, valueColumn);

        String[] games = extractColumnToArray(expensiveGames, gameColumn);
        String[] gamesSet = arrayToSet(games);

        String[] clientIds = new String[expensiveGames.length];
        int countClientsId;

        String game;
        String id;

        openCloseOutput();
        System.out.println("-- Jogo mais caro --");
        System.out.println("Valor: " + " € " + biggestValue);
        for (int i = 0; i < gamesSet.length; i++) {
            System.out.println("\nJogo: " + gamesSet[i]);
            countClientsId = 0;

            for (int l = 0; l < expensiveGames.length; l++) {
                game = expensiveGames[l][gameColumn];
                if (game.equals(games[i])) {
                    id = expensiveGames[l][1];
                    if (!arrayContains(clientIds, id, countClientsId)) {
                        clientIds[countClientsId] = id;
                        countClientsId++;
                    }
                }
            }

            String[] clientsIdArray = cleanEmptyArrayPlaces(clientIds, countClientsId);
            String[] clientsIdSet = arrayToSet(clientsIdArray);

            String[] client;
            System.out.println("-- Comprado por: ");
            System.out.println("ID\t\tCliente");
            for (int k = 0; k < clientsIdSet.length; k++) {
                client = findClient(clientsIdSet[k]);
                System.out.println(client[0] + "\t\t" + client[1]);
            }
        }
        openCloseOutput();
    }


    /**
     * Exibe as informações dos clientes que mais gastaram.
     */
    public static void printBestClients() {
        String[] bestClients = findBestsClients();
        int clientName_fileClient = 1;
        String[] client;
        openCloseOutput();
        System.out.println(" --- MELHORES CLIENTES --- ");
        System.out.println();

        for (int i = 0; i < bestClients.length; i++) {
            client = findClient(bestClients[i]);
            System.out.println("Cliente " + bestClients[i] + "   |   " + client[clientName_fileClient]);
            System.out.println("-- Jogos comprados:");
            printGamesByClient(bestClients[i]);
        }
        openCloseOutput();
    }


    /**
     * Exibe a categoria que mais gerou lucro e o valor do lucro gerado.
     */
    public static void findBestCategory() {
        String[][] profitByCategory = calculateProfitByCategory();
        String[] cateforyProfit = findBiggestColumnValue(profitByCategory, 1, 0);

        openCloseOutput();
        System.out.println("--- Categoria mais lucrativa ---");
        System.out.println("Categoria: " + cateforyProfit[0]);
        System.out.println("Lucro: € " + cateforyProfit[1]);
        openCloseOutput();
    }

    /**
     * Lê o nome de um jogo e exibe os clientes que já o compraram.
     */
    public static void detailGameSale() {
        int gameColumn = 4;
        int clientIdColumn = 1;
        boolean finish = false;

        Scanner input = new Scanner(System.in);
        do {

            System.out.println("   ---  PESQUISA DE VENDAS   ---");
            System.out.println(" Para voltar, digite \"0\".");
            System.out.print("Jogo a pesquisar: ");
            String game = input.nextLine();

            cleanScreen();
            if (!game.equals("0")) {

                String[][] salesRegister = filterFileToMatrix(fileVendas(), true, game, gameColumn);
                if (salesRegister.length == 0) {
                    openCloseOutput();
                    System.out.println("Nenhuma venda encontrada");
                    openCloseOutput();

                } else {
                    System.out.println();
                    openCloseOutput();
                    System.out.println(" ------- VENDAS POR JOGO --------- ");
                    System.out.println("Jogo: " + salesRegister[0][gameColumn]);
                    System.out.println("-- Comprado por:");
                    String[] clientIds = arrayToSet(extractColumnToArray(salesRegister, clientIdColumn));

                    String[] client;
                    for (int i = 0; i < clientIds.length; i++) {
                        client = findClient(clientIds[i]);
                        printClient(client);
                    }
                    openCloseOutput();
                }
            } else {
                finish = true;
                cleanScreen();
            }

        } while (!finish);
    }

    /**
     * Exibe os 5 jogos que mais deram lucro e o respectivo valor do lucro
     */
    public static void printTop5Games() {
        openCloseOutput();
        System.out.println("---- TOP 5 GAMES ----");
        String[][] topFiveGames = getTop5Games();

        System.out.print("Jogo: ");
        for (int space = 0; space < 29; space++) {
            System.out.print(" ");
        }
        System.out.println("Lucro:");

        int columnLength = 35;
        for (int i = 0; i < topFiveGames.length; i++) {
            String element = topFiveGames[i][0];
            System.out.print((i + 1) + " " + element);
            for (int space = 0; space < (columnLength - element.length()); space++) {
                System.out.print(" ");
            }
            System.out.println(topFiveGames[i][1]);
        }
        openCloseOutput();
    }

    /**
     * Exibe os 5 jogos que menos deram lucro e o respectivo valor do lucro.
     */
    public static void printBottom5Games() {
        openCloseOutput();
        System.out.println("---- BOTTOM 5 GAMES ----");
        String[][] bottonFiveGames = getBottom5Games();

        System.out.print("Jogo: ");
        for (int space = 0; space < 31; space++) {
            System.out.print(" ");
        }
        System.out.println("Lucro:");

        int columnLength = 35;
        for (int i = 0; i < bottonFiveGames.length; i++) {
            String element = bottonFiveGames[i][0];
            System.out.print((i + 1) + " " + element);
            for (int space = 0; space < (columnLength - element.length()); space++) {
                System.out.print(" ");
            }
            System.out.println("€ " + bottonFiveGames[i][1]);
        }
        openCloseOutput();
    }


}
