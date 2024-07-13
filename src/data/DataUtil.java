package data;

import static file.FileHandler.*;
import static gameStart.Util.*;

public class DataUtil {

    /**
     * Copia o conteúdo de uma coluna de uma matriz de Strings para um array de Strings.
     *
     * @param matrix Matriz de Strings a ter coluna extraída.
     * @param column Índice da coluna a ser copiada para o array.
     * @return Um array de Strings com os elementos da coluna especificada.
     */
    public static String[] extractColumnToArray(String[][] matrix, int column) {
        String[] columnArray = new String[matrix.length];
        for (int i = 0; i < columnArray.length; i++) {
            columnArray[i] = matrix[i][column];
        }

        return columnArray;
    }

    /**
     * Verifica se um array de Strings contém o elemento especificado
     * , considerando da posição 0 até a posição especificada.
     *
     * @param array Array de Strings a ser verificado.
     * @param text  Conteúdo a ser procurado no array.
     * @param size  Tamanho preenchido do array, a ser considerado na busca.
     * @return true se o conteúdo existir no array, false se o conteúdo não existir no array.
     */
    public static boolean arrayContains(String[] array, String text, int size) {
        for (int i = 0; i < size; i++) {
            if (text.equalsIgnoreCase(array[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recebe um array de Strings e remove elementos duplicados.
     *
     * @param rawArray Array a ter duplicações eliminadas.
     * @return Um novo array de elementos únicos.
     */
    public static String[] arrayToSet(String[] rawArray) {
        String[] set;
        String[] filteredArray = new String[rawArray.length];
        filteredArray[0] = rawArray[0];

        int countSetElements = 1;
        String rawElement;

        for (int i = 0; i < rawArray.length; i++) {
            rawElement = rawArray[i];
            if (!arrayContains(filteredArray, rawElement, countSetElements)) {
                filteredArray[countSetElements] = rawElement;
                countSetElements++;
            }
        }

        set = new String[countSetElements];
        for (int i = 0; i < countSetElements; i++) {
            set[i] = filteredArray[i];
        }
        return set;
    }

    /**
     * Recebe uma matriz de Strings e extrai a coluna especificada para
     * um array de elementos únicos.
     *
     * @param matrix Matriz de Strings eu deve ter a coluna extraída.
     *               column Índice da coluna a ser extraída.
     * @return Um array de elementos únicos.
     */
    public static String[] matrixColumnToSet(String[][] matrix, int column) {
        return arrayToSet(extractColumnToArray(matrix, column));
    }

    /**
     * Recebe um array de Strings e remove os espaços inutilizados.
     *
     * @param array    Array original com espaços inutilizados.
     * @param realSize O tamanho real do array, até onde há informação útil.
     * @return Um array redimensionado com o tamanho exato dos dados que contém.
     */
    public static String[] cleanEmptyArrayPlaces(String[] array, int realSize) {
        String[] newArray = new String[realSize];
        for (int i = 0; i < realSize; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Recebe uma matiz de Strings e remove as linhas inutilizadas.
     *
     * @param matrix    Matriz original com linhas vazias.
     * @param realLines O tamanho real da matriz, até onde há informação útil.
     * @return Uma matriz redimensionada com o tamanho exato dos dados que contém.
     */
    public static String[][] cleanEmptyMatrixLines(String[][] matrix, int realLines) {
        String[][] newMatrix = new String[realLines][matrix[0].length];
        for (int i = 0; i < realLines; i++) {
            newMatrix[i] = matrix[i];
        }
        return newMatrix;
    }


    /**
     * Filtra jogos do ficheiro de vendas e os imprime de acordo com as prioridades definidas.
     *
     * @param key             O valor da chave para o primeiro filtro, determina condição para a exibição do jogo.
     * @param columnKey       Índice da coluna onde a chave primária deve ser buscada.
     * @param columnSecFilter Índice da coluna secundária, determina a oganização da exibição dos jogos.
     */
    public static void printGamesByKey(String key, int columnKey, int columnSecFilter) {
        // Busca linhas do ficheiro em que a chave corresponda ao valor pesquisado.
        String[][] matrixByKey = filterFileToMatrix(fileVendas(), true, key, columnKey);

        // Se não for encontrada nenhuma correspondência, exibe mensagem de aviso ao utilizados.
        if (matrixByKey.length < 1) {
            cleanScreen();
            openCloseOutput();
            System.out.println("Nenhum jogo encontrado em \"" + key + "\".");
            openCloseOutput();

            // Caso forem encontradas linhas correspondentes à chava, procede à organização e impressão das informações.
        } else {
            cleanScreen();
            System.out.println("\n******************************* Catálogo " + matrixByKey[0][columnKey] + " *******************************\n");


            // Identifica os elementos da coluna secundaria para organizar impressão
            String[] setOfSecColumnElements = matrixColumnToSet(matrixByKey, columnSecFilter);

            // Para cada elemento distinto da coluna secundária, imprime os jogos correspondentes sem repetição
            for (int i = 0; i < setOfSecColumnElements.length; i++) {
                System.out.println(setOfSecColumnElements[i]);
                String[] gamesByCategorySet = filterGames(matrixByKey, setOfSecColumnElements[i], columnSecFilter);
                printArray(gamesByCategorySet);
                System.out.println();
            }
            openCloseOutput();
        }
    }

    /**
     * Filtra e imprime jogos do ficheiro de vendas comprados por um determinado cliente.
     *
     * @param cliendId O id do cliente de interesse.
     */
    public static void printGamesByClient(String cliendId) {
        int clientIdColumn = 1;
        int gameColumn = 4;

        // Busca linhas do ficheiro que corresponde ao cliente procurado
        String[][] matrixByKey = filterFileToMatrix(fileVendas(), true, cliendId, clientIdColumn);

        if (matrixByKey.length < 1) {
            System.out.println("Nenhum Jogo Encontrado.");
        } else {
            // Extrai e imprime a coluna de jogos da matriz filtrada, sem repetição
            String[] games = arrayToSet(extractColumnToArray(matrixByKey, gameColumn));
            printArray(games);
        }
    }

    /**
     * Calcula o valor total gasto por cada cliente.
     *
     * @return Matriz contendo ids dos clientes e valores totais gastos por cada um.
     */
    public static String[][] calculateClientsSpent() {

        // Extração do ficheiro de vendas para uma matrix, ignorando o cabeçalho.
        String[][] matrixVendas = extractCSVFileToMatrix(fileVendas(), true);
        int clientIdColumn = 1;
        int valueColumn = 5;

        // Criação de um array de String para guardar os ids dos clientes e de
        // um array de double para guardar os valores gastos acumulados
        String[] clientArray = new String[matrixVendas.length];
        double[] spentArray = new double[matrixVendas.length];

        // Inicializa o array de valores gastos
        for (int c = 0; c < clientArray.length; c++) {
            spentArray[c] = 0;
        }

        String clientId;
        String stringValue;
        boolean found;

        // Preenche o array com os dados da primeira linha da matriz
        stringValue = matrixVendas[0][valueColumn];
        spentArray[0] += Double.parseDouble(stringValue);
        clientArray[0] = matrixVendas[0][clientIdColumn];
        int countSpentMatrixLines = 1;

        // Percorre matriz de vendas e extrai o id do cliente e o valor gasto
        for (int i = 1; i < matrixVendas.length; i++) {
            clientId = matrixVendas[i][clientIdColumn];
            stringValue = matrixVendas[i][valueColumn];
            found = false;

            // Percorre o array de ids de clientes e verifica se contém o id do cliente da linha atual
            for (int k = 0; k < countSpentMatrixLines && !found; k++) {

                // se o id for encontrado, acumula o valor da compra no array de gastos
                if (clientId.equals(clientArray[k])) {
                    spentArray[k] += Double.parseDouble(stringValue);
                    found = true;
                }
            }

            // Caso o array de ids ainda não tenha o id do cliente da linha atual,
            // adiciona o id no array de ids, e o valor da compra no array de gastos,
            // na mesma posição
            if (!found) {
                clientArray[countSpentMatrixLines] = clientId;
                spentArray[countSpentMatrixLines] = Double.parseDouble(stringValue);
                countSpentMatrixLines++;
            }
        }

        // Transfere as informações do array de ids e do array de gastos para uma matriz de Strings
        String[][] clientSpent = new String[countSpentMatrixLines][2];
        for (int count = 0; count < countSpentMatrixLines; count++) {
            clientSpent[count][0] = clientArray[count];
            clientSpent[count][1] = String.valueOf(spentArray[count]);
        }

        return clientSpent;
    }


    /**
     * Encontra os clientes que mais gastaram.
     *
     * @return Array de Strings com os ids dos clientes que gastaram o maior valor.
     */
    public static String[] findBestsClients() {

        String[][] clientsSpentMatrix = calculateClientsSpent();
        String[] bestsClients = new String[clientsSpentMatrix.length];
        int countBestClients = 0;
        String stringSpent;
        double biggestSpent = 0;
        double clientSpent;

        // Encontra os clientes cujo valor de gasto seja igual ao maior valor gasto identificado
        for (int i = 0; i < clientsSpentMatrix.length; i++) {
            stringSpent = clientsSpentMatrix[i][1];
            clientSpent = Double.parseDouble(stringSpent);
            if (clientSpent > biggestSpent) {
                biggestSpent = clientSpent;
                bestsClients[0] = clientsSpentMatrix[i][0];
                countBestClients = 1;
            } else if (clientSpent == biggestSpent) {
                bestsClients[countBestClients] = clientsSpentMatrix[i][0];
                countBestClients++;
            }
        }

        return cleanEmptyArrayPlaces(bestsClients, countBestClients);
    }

    /**
     * Encontra o maior valor existente na coluna especificada da matriz recebida
     * Retorna a informação correspondente à coluna determinada, na linha do maior valor.
     *
     * @param matrix       Matriz a ser analisada.
     * @param column       índex da coluna a ser analizada.
     * @param returnColumn Índex da coluna a ter o valor retornado
     * @return Array de Strings com os valores da coluna a ser retornada.
     */
    public static String[] findBiggestColumnValue(String[][] matrix, int column, int returnColumn) {
        double biggestValue = 0;
        String[] stringToReturn = new String[2];
        String[] line;

        double value;
        for (int i = 0; i < matrix.length; i++) {
            line = matrix[i];
            value = Double.parseDouble(line[column]);
            if (value > biggestValue) {
                biggestValue = value;
                stringToReturn[0] = line[returnColumn];
                stringToReturn[1] = line[column];
            }
        }

        return stringToReturn;
    }

    /**
     * Encontra os 5 jogos que mais deram lucro.
     *
     * @return Matrix de Strings com os nomes dos 5 jogos mais lucrativos e seus respetivos lucros.
     */
    public static String[][] getTop5Games() {

        String[][] profitByGame = calculateProfitByGame();
        double profitI;
        double profitJ;
        String[] gameI;
        String[] gameJ;
        String[] temp;
        double end;

        // Assegura que o loop não exceda o tamanho do array
        if (profitByGame.length > 5) {
            end = 5;
        } else {
            end = profitByGame.length;
        }


        // Percorre o array 5 vezes, encontrando os maiores valores e transferindo para as primeiras posições do array
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < profitByGame.length; j++) {
                gameI = profitByGame[i];
                gameJ = profitByGame[j];
                profitI = Double.parseDouble(gameI[1]);
                profitJ = Double.parseDouble(gameJ[1]);
                if (profitJ > profitI) {
                    temp = gameI;
                    profitByGame[i] = gameJ;
                    profitByGame[j] = temp;
                }
            }
        }

        // Cria nova matriz apenas com os 5 jogos mais lucrativos
        String[][] top5Games = new String[5][2];
        for (int i = 0; i < top5Games.length; i++) {
            top5Games[i] = profitByGame[i];
        }

        return top5Games;
    }

    /**
     * Encontra os 5 jogos que menos deram lucro.
     *
     * @return Matrix de Strings com os nomes dos 5 jogos menos lucrativos e seus respetivos lucros.
     */
    public static String[][] getBottom5Games() {
        String[][] profitByGame = calculateProfitByGame();
        double profitI;
        double profitJ;
        String[] gameI;
        String[] gameJ;
        String[] temp;
        double end;

        // Assegura que o loop não exceda o tamanho do array
        if (profitByGame.length > 5) {
            end = 5;
        } else {
            end = profitByGame.length;
        }

        // Percorre o array 5 vezes, encontrando os menores valores e transferindo para as primeiras posições do array
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < profitByGame.length; j++) {
                gameI = profitByGame[i];
                gameJ = profitByGame[j];
                profitI = Double.parseDouble(gameI[1]);
                profitJ = Double.parseDouble(gameJ[1]);
                if (profitJ < profitI) {
                    temp = gameI;
                    profitByGame[i] = gameJ;
                    profitByGame[j] = temp;
                }
            }
        }

        // Cria nova matriz apenas com os 5 jogos menos lucrativos
        String[][] bottom5Games = new String[5][2];
        for (int i = 0; i < bottom5Games.length; i++) {
            bottom5Games[i] = profitByGame[i];
        }
        return bottom5Games;

    }


    /**
     * Filtra os jogos de uma matriz recebida, de acordo com a chave e coluna informadas.
     *
     * @param matrix Matriz a ser filtrada
     * @param key    Valor chave a ser pesquisado
     * @param column Índice da coluna onde a chave deve ser pesquisada
     * @return Um array de Strings com os nomes dos jogos correspondentes ao filtro, sem repetição.
     */
    public static String[] filterGames(String[][] matrix, String key, int column) {
        final int gameColumn = 4;
        String[] arrayUniqueElements = new String[matrix.length];
        int countElements = 0;

        String game;
        for (int i = 0; i < matrix.length; i++) {
            game = matrix[i][gameColumn];

            if (key.equalsIgnoreCase(matrix[i][column])) {
                if (!arrayContains(arrayUniqueElements, game, countElements)) {
                    arrayUniqueElements[countElements] = game;
                    countElements++;
                }
            }
        }
        return arrayToSet(cleanEmptyArrayPlaces(arrayUniqueElements, countElements));
    }

}
