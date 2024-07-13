package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static data.DataUtil.*;
import static gameStart.Util.*;

public class FileHandler {

    /**
     * Retorna um File criado a partir da localização do ficheiro de vendas.
     *
     * @return File correspondente ao ficheiro de vendas.
     */
    public static File fileVendas() {
        return new File("files/GameStart_Vendas.csv");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro de Admins.
     *
     * @return File correspondente ao ficheiro de Admins.
     */
    public static File fileAdmins() {
        return new File("files/GameStart_Admins.csv");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro de Categorias.
     *
     * @return File correspondente ao ficheiro de Categorias.
     */
    public static File fileCategorias() {
        return new File("files/GameStart_Categorias.csv");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro de Clientes.
     *
     * @return File correspondente ao ficheiro de Clientes.
     */
    public static File fileClientes() {
        return new File("files/GameStart_Clientes.csv");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII de Copyrights.
     *
     * @return File correspondente ao ficheiro do gráfico de Copyrights.
     */
    public static File graphCopyright() {
        return new File("files/GameStart_Copyright.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII da Game Start.
     *
     * @return File correspondente ao ficheiro do gráfico da Game Start.
     */
    public static File graphGameStart() {
        return new File("files/CatalogoGrafico/GameStart.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII do jogo Call Of Duty.
     *
     * @return File correspondente ao ficheiro do gráfico do jogo Call Of Duty.
     */
    public static File graphCallOfDuty() {
        return new File("files/CatalogoGrafico/callOfDuty.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII do jogo Fifa.
     *
     * @return File correspondente ao ficheiro do gráfico do jogo Fifa.
     */
    public static File graphFifa() {
        return new File("files/CatalogoGrafico/fifa.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII do jogo Hollow Knight.
     *
     * @return File correspondente ao ficheiro do gráfico do jogo Hollow Knight.
     */
    public static File graphHollowKnight() {
        return new File("files/CatalogoGrafico/hollowKnight.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII do jogo Minecraft.
     *
     * @return File correspondente ao ficheiro do gráfico do jogo Minecraft.
     */
    public static File graphMinecraft() {
        return new File("files/CatalogoGrafico/minecraft.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII do jogo Mortal Kombat.
     *
     * @return File correspondente ao ficheiro do gráfico do jogo Motal Kombat.
     */
    public static File graphMortalKombat() {
        return new File("files/CatalogoGrafico/mortalKombat.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII do jogo Overcooked.
     *
     * @return File correspondente ao ficheiro do gráfico do jogo Overcooked.
     */
    public static File graphOvercooked() {
        return new File("files/CatalogoGrafico/overcooked.txt");
    }

    /**
     * Retorna um File criado a partir da localização do ficheiro que contém o gráfico em ASCII do jogo Witcher 3.
     *
     * @return File correspondente ao ficheiro do gráfico do jogo Witcher 3.
     */
    public static File graphWitcher3() {
        return new File("files/CatalogoGrafico/witcher3.txt");
    }


    /**
     * Extrai conteudo de um ficheiro para uma matriz.
     *
     * @param file         ficheiro CSV a ser extraído para matriz.
     * @param ignoreHeader boolean a indicar se o cabeçalho do ficheiro deve ser ignorado.
     * @return Matriz com o conteúdo do ficheiro.
     */
    public static String[][] extractCSVFileToMatrix(File file, boolean ignoreHeader) {
        try {
            Scanner fileScanner = new Scanner(file);
            int countColumns = countColumns(file);
            int countLines = countLines(file);

            if (ignoreHeader) {
                fileScanner.nextLine();
                countLines--;
            }

            String[][] matrix = new String[countLines][countColumns];

            int lineIndex = 0;
            while (fileScanner.hasNext()) {
                matrix[lineIndex] = fileScanner.nextLine().split(";");
                lineIndex++;
            }
            return matrix;

        } catch (FileNotFoundException ex) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }
        return new String[0][0];
    }

    /**
     * Extrai ficheiro CSV para matriz, filtrando apenas as linhas correspondentes à chave indicada.
     *
     * @param file         ficheiro CSV a ser extraído para matriz.
     * @param ignoreHeader boolean a indicar se o cabeçalho do ficheiro deve ser ignorado.
     * @param key          chave utilizado para filtrar as linhas de interesse
     * @param column       Índex da coluna onde a chave deve ser procurada
     * @return Matriz com as informações filtradas do ficheiro.
     */
    public static String[][] filterFileToMatrix(File file, boolean ignoreHeader, String key, int column) {
        try {
            Scanner fileScanner = new Scanner(file);
            int countColumns = countColumns(file);
            int countLines = countLines(file);

            if (ignoreHeader) {
                fileScanner.nextLine();
                countLines--;
            }

            String[][] matrix = new String[countLines][countColumns];
            int lineIndex = 0;
            String[] line;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");
                if (key.equalsIgnoreCase(line[column])) {
                    matrix[lineIndex] = line;
                    lineIndex++;
                }
            }

            return cleanEmptyMatrixLines(matrix, lineIndex);

        } catch (FileNotFoundException ex) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }
        return new String[0][0];
    }

    /**
     * Conta o total de linhas do ficheiro
     *
     * @param file ficheiro a ter linhas contadas
     * @return numero de linhas do ficheiro
     */
    public static int countLines(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            int countLines = 0;
            while (fileScanner.hasNext()) {
                countLines++;
                fileScanner.nextLine();
            }
            return countLines;
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }
        return 0;
    }

    /**
     * Conta o numero de colunas de um ficheiro CSV, com base em sua primeira linha.
     *
     * @param file ficheiro a ter colunas contadas
     * @return numero de colunas do ficheiro
     */
    public static int countColumns(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            if (fileScanner.hasNext()) {
                return fileScanner.nextLine().split(";").length;
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        return 0;
    }


    /**
     * Imprime o conteúdo de um ficheiro.
     *
     * @param file ficheiro a ser impresso.
     */
    public static void printFile(File file) {
        openCloseOutput();
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println("\n\n");
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }
        openCloseOutput();
    }

    /**
     * Exibe na tela o conteúdo do ficheiro de Copyrights
     */
    public static void printCopyrights() {
        try {
            Scanner fileScanner = new Scanner(graphCopyright());
            while (fileScanner.hasNext()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println("\n\n");
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }
    }


    /**
     * Extrai conteudo de uma coluna do ficheiro para um array de elementos unicos.
     *
     * @param file         ficheiro a ter a coluna filtrada e extraida pra um array
     * @param column       Índex da coluna a ser extraída
     * @param ignoreHeader boolean a indicar se o cabeçalho do ficheiro deve ser ignorado.
     * @return Array com elementos da coluna sem repetição
     */
    public static String[] fileColumnToSet(File file, int column, boolean ignoreHeader) {
        try {
            Scanner fileScanner = new Scanner(file);
            int countLines = countLines(file);

            if (ignoreHeader) {
                fileScanner.nextLine();
                countLines--;
            }

            String[] columnArray = new String[countLines];
            columnArray[0] = fileScanner.nextLine().split(";")[column];

            String element;
            int countSetElements = 1;

            // Este ciclo percorrer a coluna especificada em ao longo do arquivo e salvar valores de títulos distintos no columnArray
            while (fileScanner.hasNext()) {
                element = fileScanner.nextLine().split(";")[column];
                if (!arrayContains(columnArray, element, countSetElements)) {
                    columnArray[countSetElements] = element;
                    countSetElements++;
                }
            }

            return cleanEmptyArrayPlaces(columnArray, countSetElements);

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        return new String[0];
    }


    /**
     * Compara valores de utilizador e senha com as informações do ficheiro de Admins.
     *
     * @param username username que identifica o utilizador
     * @param password senha de acesso do utilizador
     * @return true, se as informações corresponderem às credenciais de um utilizador, false, do contrário.
     */
    public static boolean checkLogin(String username, String password) {
        boolean success = false;

        try {
            Scanner fileScanner = new Scanner(fileAdmins());
            String[] line;

            while (fileScanner.hasNext() && !success) {
                line = fileScanner.nextLine().split(";");
                if (username.equals(line[0])) {
                    if (password.equals(line[1])) {
                        success = true;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        return success;
    }

    /**
     * Soma os valores de uma coluna de um ficheiro
     *
     * @param file         ficheiro a ter valores de uma coluna somados.
     * @param ignoreHeader boolean a indicar se o cabeçalho do ficheiro deve ser ignorado.     * @param column
     * @return double com o valor total da soma.
     */
    public static double sumColumn(File file, boolean ignoreHeader, int column) {
        double sum = 0;
        String stringValue;

        try {
            Scanner fileScanner = new Scanner(file);

            if (ignoreHeader) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNext()) {
                stringValue = fileScanner.nextLine().split(";")[column];
                sum += Double.parseDouble(stringValue);
            }

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        return sum;
    }


    /**
     * Calcula o lucro total com base no ficheiros de vendas e de categorias.
     *
     * @return valor total do lucro.
     */
    public static double calculateTotalProfit() {

        // Extrai informações das margens de lucro a serem aplicadas para cada categoria de jogos
        String[][] categoriesProfit = extractCSVFileToMatrix(fileCategorias(), true);
        double profit = 0;

        try {
            Scanner fileScanner = new Scanner(fileVendas());
            int posCateg = 3;
            int posValue = 5;

            String[] line;
            double margin;
            double value;
            boolean sair;

            // Lê o ficheiro de vendas, linha a linha
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");

                sair = false;

                // Identifica a categoria e o valor do jogo daquela linha, calcula o valor do lucro e acumula no lucro total
                for (int i = 0; i < categoriesProfit.length && !sair; i++) {
                    if (line[posCateg].equals(categoriesProfit[i][0])) {
                        value = Double.parseDouble(line[posValue]);
                        margin = Double.parseDouble(categoriesProfit[i][1]);
                        profit += (value * (margin / 100));
                        sair = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        return profit;
    }

    /**
     * Calcula o lucro obtido separado por categoria de jogos.
     *
     * @return Matriz com as informações da categoria e lucro total obtido pelos jogos que a pertencem.
     */
    public static String[][] calculateProfitByCategory() {

        // Extrai fichiero de categorias para matriz
        String[][] categoriesProfit = extractCSVFileToMatrix(fileCategorias(), true);
        double profit;

        double[] profitsArray = new double[categoriesProfit.length];

        try {
            Scanner fileScanner = new Scanner(fileVendas());
            int posCateg = 3;
            int posValue = 5;

            String[] line;
            double margin;
            double value;
            boolean sair;
            fileScanner.nextLine();

            // Percorre ficheiro de vendas
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");
                sair = false;

                // Comparar categoria da linha com categoria do categoriesProfit, calcula lucro e acumula no vetor de lucro.
                for (int i = 0; i < categoriesProfit.length && !sair; i++) {
                    if (line[posCateg].equals(categoriesProfit[i][0])) {
                        value = Double.parseDouble(line[posValue]);
                        margin = Double.parseDouble(categoriesProfit[i][1]);
                        profit = (value * (margin / 100));
                        profitsArray[i] += profit;
                        sair = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        // Salva os arrays de categorias e lucros em uma matriz de Strings.
        String[][] categoriesProfitMatrix = new String[categoriesProfit.length][2];
        for (int count = 0; count < categoriesProfit.length; count++) {
            categoriesProfitMatrix[count][0] = categoriesProfit[count][0];
            categoriesProfitMatrix[count][1] = String.valueOf(profitsArray[count]);
        }
        return categoriesProfitMatrix;
    }

    /**
     * Calcula o lucro obtido por cada jogo.
     *
     * @return Matriz com as informações de nome do jogo e lucro correspondente.
     */
    public static String[][] calculateProfitByGame() {
        String[][] categoriesProfit = extractCSVFileToMatrix(fileCategorias(), true);
        int gamesColumn = 4;
        int categoryColumn = 3;
        int valueColumn = 5;
        String[] gamesSet = fileColumnToSet(fileVendas(), gamesColumn, true);

        double[] profitsArray = new double[gamesSet.length];

        try {
            Scanner fileScanner = new Scanner(fileVendas());
            fileScanner.nextLine();

            String[] line;
            double margin;
            double value;
            boolean sair;
            double profit;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");
                sair = false;

                for (int i = 0; i < categoriesProfit.length && !sair; i++) {
                    // Comparar categoria da linha com categoria do categoriesProfit
                    if (line[categoryColumn].equals(categoriesProfit[i][0])) {
                        value = Double.parseDouble(line[valueColumn]);
                        margin = Double.parseDouble(categoriesProfit[i][1]) / 100;
                        profit = value * margin;

                        // Comparar game da linha com game do gameSet
                        for (int w = 0; w < gamesSet.length && !sair; w++) {
                            if (gamesSet[w].equals(line[gamesColumn])) {
                                profitsArray[w] += profit;
                                sair = true;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        String[][] gameProfitMatrix = new String[gamesSet.length][2];
        for (int count = 0; count < gamesSet.length; count++) {
            gameProfitMatrix[count][0] = gamesSet[count];
            gameProfitMatrix[count][1] = String.valueOf(profitsArray[count]);
        }
        return gameProfitMatrix;
    }


    /**
     * Busca informações de um cliente a partir de seu id.
     *
     * @param clientId id do cliente a ser pesquisado
     * @return Array de String com as informações do cliente, ou array vazio se cliente nao encontrado.
     */
    public static String[] findClient(String clientId) {
        String[] line;

        try {
            Scanner fileScanner = new Scanner(fileClientes());
            fileScanner.nextLine();

            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");

                if (clientId.equals(line[0])) {
                    return line;
                }
            }

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }

        return new String[0];
    }

    /**
     * Encontra o maior valor de uma coluna do ficheiro.
     *
     * @param file         ficheiro a ser analisado.
     * @param ignoreHeader boolean que determina se a primeira linha do ficheiro deve ser ignorada.
     * @param column       coluna a ter o maior valor buscado.
     * @return String represnetando o maior valor encontrado na coluna.
     */
    public static String findBiggestValue(File file, boolean ignoreHeader, int column) {
        double biggestValue = 0;
        String stringBiggestValue = "";
        String[] line;

        try {
            Scanner fileScanner = new Scanner(file);

            if (ignoreHeader) {
                fileScanner.nextLine();
            }

            double value;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");

                value = Double.parseDouble(line[column]);
                if (value > biggestValue) {
                    biggestValue = value;
                    stringBiggestValue = line[column];
                }
            }

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        } catch (NoSuchElementException e) {
            printAdvetisingNoSuchElementException();
        }
        return stringBiggestValue;
    }


}
