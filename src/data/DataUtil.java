package data;

import static file.FileHandler.*;
import static gameStart.Util.*;

public class DataUtil {

    /**
     * Copies the content of a column from a matrix of Strings to an array of Strings.
     *
     * @param matrix Matrix of Strings to extract the column from.
     * @param column Index of the column to be copied to the array.
     * @return An array of Strings with the elements from the specified column.
     */
    public static String[] extractColumnToArray(String[][] matrix, int column) {
        String[] columnArray = new String[matrix.length];
        for (int i = 0; i < columnArray.length; i++) {
            columnArray[i] = matrix[i][column];
        }

        return columnArray;
    }

    /**
     * Checks if an array of Strings contains the specified element
     * from position 0 up to the specified position.
     *
     * @param array Array of Strings to be checked.
     * @param text  Content to be searched in the array.
     * @param size  Size of the array to be considered in the search.
     * @return true if the content exists in the array, false if the content does not exist in the array.
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
     * Receives an array of Strings and removes duplicate elements.
     *
     * @param rawArray Array from which duplicates should be removed.
     * @return A new array with unique elements.
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
     * Receives a matrix of Strings and extracts the specified column into
     * an array of unique elements.
     *
     * @param matrix Matrix of Strings from which the column should be extracted.
     *               column Index of the column to be extracted.
     * @return An array of unique elements.
     */
    public static String[] matrixColumnToSet(String[][] matrix, int column) {
        return arrayToSet(extractColumnToArray(matrix, column));
    }

    /**
     * Receives an array of Strings and removes unused spaces.
     *
     * @param array    Original array with unused spaces.
     * @param realSize The actual size of the array, up to where there is useful information.
     * @return A resized array with the exact size of the data it contains.
     */
    public static String[] cleanEmptyArrayPlaces(String[] array, int realSize) {
        String[] newArray = new String[realSize];
        for (int i = 0; i < realSize; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Receives a matrix of Strings and removes unused rows.
     *
     * @param matrix    Original matrix with empty rows.
     * @param realLines The actual size of the matrix, up to where there is useful information.
     * @return A resized matrix with the exact size of the data it contains.
     */
    public static String[][] cleanEmptyMatrixLines(String[][] matrix, int realLines) {
        String[][] newMatrix = new String[realLines][matrix[0].length];
        for (int i = 0; i < realLines; i++) {
            newMatrix[i] = matrix[i];
        }
        return newMatrix;
    }


    /**
     * Filters games from the sales file and prints them according to the defined priorities.
     *
     * @param key             The key value for the primary filter, determines condition for displaying the game.
     * @param columnKey       Index of the column where the primary key should be searched.
     * @param columnSecFilter Index of the secondary column, determines the organization of game display.
     */
    public static void printGamesByKey(String key, int columnKey, int columnSecFilter) {
        String[][] matrixByKey = filterFileToMatrix(fileVendas(), true, key, columnKey);

        if (matrixByKey.length < 1) {
            cleanScreen();
            openCloseOutput();
            System.out.println("Nenhum jogo encontrado em \"" + key + "\".");
            openCloseOutput();

        } else {
            cleanScreen();
            System.out.println("\n******************************* Catálogo " + matrixByKey[0][columnKey] + " *******************************\n");


            String[] setOfSecColumnElements = matrixColumnToSet(matrixByKey, columnSecFilter);

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
     * Filters and prints games from the sales file purchased by a specific client.
     *
     * @param cliendId The id of the client of interest.
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
     * Calculates the total amount spent by each client.
     *
     * @return Matrix containing client ids and total amounts spent by each.
     */
    public static String[][] calculateClientsSpent() {

        String[][] matrixVendas = extractCSVFileToMatrix(fileVendas(), true);
        int clientIdColumn = 1;
        int valueColumn = 5;

        String[] clientArray = new String[matrixVendas.length];
        double[] spentArray = new double[matrixVendas.length];

        for (int c = 0; c < clientArray.length; c++) {
            spentArray[c] = 0;
        }

        String clientId;
        String stringValue;
        boolean found;

        stringValue = matrixVendas[0][valueColumn];
        spentArray[0] += Double.parseDouble(stringValue);
        clientArray[0] = matrixVendas[0][clientIdColumn];
        int countSpentMatrixLines = 1;

        for (int i = 1; i < matrixVendas.length; i++) {
            clientId = matrixVendas[i][clientIdColumn];
            stringValue = matrixVendas[i][valueColumn];
            found = false;

            for (int k = 0; k < countSpentMatrixLines && !found; k++) {

                if (clientId.equals(clientArray[k])) {
                    spentArray[k] += Double.parseDouble(stringValue);
                    found = true;
                }
            }

            if (!found) {
                clientArray[countSpentMatrixLines] = clientId;
                spentArray[countSpentMatrixLines] = Double.parseDouble(stringValue);
                countSpentMatrixLines++;
            }
        }

        String[][] clientSpent = new String[countSpentMatrixLines][2];
        for (int count = 0; count < countSpentMatrixLines; count++) {
            clientSpent[count][0] = clientArray[count];
            clientSpent[count][1] = String.valueOf(spentArray[count]);
        }

        return clientSpent;
    }


    /**
     * Finds the clients who spent the most.
     *
     * @return An array of Strings with the IDs of the clients who spent the most amount.
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
     * Finds the highest value in the specified column of the given matrix.
     * Returns the information from the specified return column in the row with the highest value.
     *
     * @param matrix       The matrix to be analyzed.
     * @param column       The index of the column to be analyzed.
     * @param returnColumn The index of the column to return the value from.
     * @return An array of Strings with the values from the return column.
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
     * Finds the top 5 most profitable games.
     *
     * @return A matrix of Strings with the names of the 5 most profitable games and their respective profits.
     */
    public static String[][] getTop5Games() {

        String[][] profitByGame = calculateProfitByGame();
        double profitI;
        double profitJ;
        String[] gameI;
        String[] gameJ;
        String[] temp;
        double end;

        if (profitByGame.length > 5) {
            end = 5;
        } else {
            end = profitByGame.length;
        }


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

        String[][] top5Games = new String[5][2];
        for (int i = 0; i < top5Games.length; i++) {
            top5Games[i] = profitByGame[i];
        }

        return top5Games;
    }

    /**
     * Finds the 5 least profitable games.
     *
     * @return A matrix of Strings with the names of the 5 least profitable games and their respective profits.
     */
    public static String[][] getBottom5Games() {
        String[][] profitByGame = calculateProfitByGame();
        double profitI;
        double profitJ;
        String[] gameI;
        String[] gameJ;
        String[] temp;
        double end;

        if (profitByGame.length > 5) {
            end = 5;
        } else {
            end = profitByGame.length;
        }

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

        String[][] bottom5Games = new String[5][2];
        for (int i = 0; i < bottom5Games.length; i++) {
            bottom5Games[i] = profitByGame[i];
        }
        return bottom5Games;

    }


    /**
     * Filters the games from a given matrix according to the specified key and column.
     *
     * @param matrix The matrix to be filtered.
     * @param key    The key value to be searched for.
     * @param column The index of the column where the key should be searched.
     * @return An array of Strings with the names of the games matching the filter, without duplicates.
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
