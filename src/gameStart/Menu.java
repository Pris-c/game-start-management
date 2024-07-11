package gameStart;

import static gameStart.Admin.*;
import static gameStart.Client.*;
import static gameStart.Util.cleanScreen;
import static gameStart.Util.validateOption;

public class Menu {

    public static void startApp() {
        int option;

        do {

            System.out.println(" --- Seja bem vindo à Game Star! ---");
            System.out.println("Informe sua categoria de utilizador: ");
            System.out.println("1 - CLIENTE");
            System.out.println("2 - ADMINISTRADOR");
            System.out.println("\033[3mPara encerrar, digite \"0\".");
            option = validateOption(0, 2);

            switch (option) {
                case 0:
                    // TODO: Show copyrights
                    System.out.println("APRESENTAR COPYRIGHTS");
                    break;
                case 1:
                    clientMenu();
                    break;
                case 2:
                    if (login()) {
                        adminMenu();
                    }
                    break;
            }

        } while (option != 0);
    }

    public static void clientMenu() {
        int option;

        do {

            System.out.println();
            System.out.println(" -- ÁREA DO CLIENTE -- ");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Novo registro");
            System.out.println("2 - Procurar estacionamento");
            System.out.println("3 - Imprimir Catálogo");
            System.out.println("4 - Imprimir Gráficos");
            System.out.println("5 - Imprimir Catálogo Editora");
            System.out.println("6 - Imprimir Catálogo Categoria");
            System.out.println("7 - Imprimir jogo mais recente");
            System.out.println("0 - SAIR");
            option = validateOption(0, 7);

            switch (option) {
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
                    printGraph();
                    break;
                case 5:
                    printGamesByPublisher();
                    break;
                case 6:
                    printGamesByCategory();
                    break;
                case 7:
                    printNewestGame();
                    break;
            }

        } while (option != 0);
    }

    public static void adminMenu() {
        int option;

        do {

            System.out.println();
            System.out.println(" -- ÁREA DO ADMINISTRADOR -- ");
            System.out.println("Escolha uma opção:");
            System.out.println("1  - Consultar Ficheiros ");
            System.out.println("2  - Consultar Total de Vendas");
            System.out.println("3  - Consultar Lucro Total");
            System.out.println("4  - Pesquisar Cliente");
            System.out.println("5  - Consultar Jogo mais caro");
            System.out.println("6  - Buscar Melhores Clientes");
            System.out.println("7  - Buscar Categoria Mais Lucrativa");
            System.out.println("8  - Pesquisar Vendas");
            System.out.println("9  - Top 5 Jogos");
            System.out.println("10 - Bottom 5 jogos");
            System.out.println("0  - SAIR");
            option = validateOption(0, 7);

            switch (option) {
                case 0:
                    cleanScreen();
                    break;
                case 1:
                    consultFile();
                    break;
                case 2:
                    consultSalesTotal();
                    break;
                case 3:
                    consultTotalProfit();
                    break;
                case 4:
                    consultClient();
                    break;
                case 5:
                    showMostExpensiveGame();
                    // 5 - Consultar Jogo mais caro
                    break;
                case 6:
                    // 6 - Buscar Melhores Clientes
                    break;
                case 7:
                    // 7 - Buscar Categoria Mais Lucrativa
                    break;
                case 8:
                    // 8 - Pesquisar Vendas
                    break;
                case 9:
                    // 9 - Top 5 Jogos
                    break;
                case 10:
                    // 10 - Bottom 5 jogos
                    break;
            }

        } while (option != 0);
    }


}
