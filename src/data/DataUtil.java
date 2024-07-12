package data;

import static file.FileHandler.*;
import static gameStart.Util.*;

public class DataUtil {

    public static String[] extractColumnToArray(String[][] matrix, int column) {
        String[] columnArray = new String[matrix.length];

        for (int i = 0; i < columnArray.length; i++) {
            columnArray[i] = matrix[i][column];
        }

        return columnArray;
    }

    public static boolean arrayContains(String[] array, String text, int size) {
        for (int i = 0; i < size; i++) {
            if (text.equalsIgnoreCase(array[i])) {
                return true;
            }
        }
        return false;
    }

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

    public static String[] matrixColumnToSet(String[][] matrix, int column) {
        return arrayToSet(extractColumnToArray(matrix, column));
    }

    public static String[] cleanEmptyArrayPlaces(String[] array, int realSize) {
        String[] newArray = new String[realSize];
        for (int i = 0; i < realSize; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    public static String[][] cleanEmptyMatrixLines(String[][] matrix, int realLines){
        String[][] newMatrix = new String[realLines][matrix[0].length];
        for (int i = 0; i < realLines; i++){
            newMatrix[i] = matrix[i];
        }
        return newMatrix;
    }

    public static void printGamesByKey(String key, int columnKey, int columnSecFilter) {
        // Busca linhas do ficheiro em que a chave corresponda à pesquisada
        String[][] matrixByKey = filterFileToMatrix(fileVendas(), true, key, columnKey);

        if (matrixByKey.length < 1) {
            cleanScreen();
            openCloseOutput();
            System.out.println("Nenhum jogo encontrado em \"" + key + "\".");
            openCloseOutput();
        } else {
            cleanScreen();
            System.out.println("\n******************************* Catálogo " + matrixByKey[0][columnKey] + " *******************************\n");

            // Encontra elementos da coluna secundaria na matriz filtrada
            String[] setOfSecColumnElements = matrixColumnToSet(matrixByKey, columnSecFilter);

            // Para cada categoria, imprimir jogos sem repetição
            for (int i = 0; i < setOfSecColumnElements.length; i++) {
                System.out.println(setOfSecColumnElements[i]);
                // Encontrar jogos correspondentes à categoria
                String[] gamesByCategorySet = filterGames(matrixByKey, setOfSecColumnElements[i], columnSecFilter);
                printArray(gamesByCategorySet);
                System.out.println();
            }
            openCloseOutput();
        }
    }

    public static void printGamesByClient(String cliendId) {
        int clientIdColumn = 1;
        int gameColumn = 4;

        // Busca linhas do ficheiro em que a chave corresponda à pesquisada
        String[][] matrixByKey = filterFileToMatrix(fileVendas(), true, cliendId, clientIdColumn);

        if (matrixByKey.length < 1) {
            System.out.println("Nenhum Jogo Encontrado.");
        } else {
            printArray(extractColumnToArray(matrixByKey, gameColumn));
        }
    }

    public static String[][] calculateClientsSpent() {
        String[][] matrixVendas = extractCSVFileToMatrix(fileVendas(), true);
        int clientIdColumn = 1;
        int valueColumn = 5;

        String[] clientArray = new String[matrixVendas.length];
        double[] spentArray = new double[matrixVendas.length];

        for (int c = 0; c < clientArray.length; c++){
            spentArray[c] = 0;
        }

        String clientId;
        String stringValue;
        boolean found;

        stringValue = matrixVendas[0][valueColumn];
        spentArray[0] += Double.parseDouble(stringValue);
        clientArray[0] = matrixVendas[0][clientIdColumn];

        int countSpentMatrixLines = 1;

        // Percorrendo matrix de vendas
        for (int i = 1; i < matrixVendas.length; i++){
            clientId = matrixVendas[i][clientIdColumn];
            stringValue = matrixVendas[i][valueColumn];

            found = false;
            // Percorrendo array de clientId
            for (int k = 0; k < countSpentMatrixLines && !found; k++){
                if (clientId.equals(clientArray[k])){
                    spentArray[k] += Double.parseDouble(stringValue);
                    found = true;
                }
            }
            if (!found){
                clientArray[countSpentMatrixLines] = clientId;
                spentArray[countSpentMatrixLines] = Double.parseDouble(stringValue);
                countSpentMatrixLines++;
            }
        }

        String[][] clientSpent = new String[countSpentMatrixLines][2];

        for (int count = 0; count < countSpentMatrixLines; count++){
            clientSpent[count][0] = clientArray[count];
            clientSpent[count][1] = String.valueOf(spentArray[count]);
        }

        return clientSpent;
    }

    public static String[] findBestsClients(){
        // ClientID || Spent

        String[][] clientsSpentMatrix = calculateClientsSpent();
        String[] bestsClients = new String[clientsSpentMatrix.length];
        int countBestClients = 0;
        String stringSpent;
        double biggestSpent = 0;
        double clientSpent;


        for (int i = 0; i < clientsSpentMatrix.length; i++){
            stringSpent = clientsSpentMatrix[i][1];
            clientSpent = Double.parseDouble(stringSpent);
            if (clientSpent > biggestSpent){
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

    public static String[] findBiggestColumnValue(String[][] matrix, int column, int returnColumn) {
        double biggestValue = 0;
        String[] stringToReturn = new String[2];
        String[] line;

        double value;
        for (int i = 0; i < matrix.length; i++){
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

    public static String[][] getTop5Games(){
        String[][] profitByGame = calculateProfitByGame();

        double profitI;
        double profitJ;
        String[] gameI;
        String[] gameJ;
        String[] temp;
        double end;

        if (profitByGame.length > 5){
            end = 5;
        } else {
            end = profitByGame.length;
        }

        for (int i = 0; i < end; i++) {
            for (int j = i+1; j < profitByGame.length; j++) {
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

        for (int i = 0; i < top5Games.length; i++){
            top5Games[i] = profitByGame[i];
        }

        return top5Games;

    }

    public static String[][] getBottom5Games(){
        String[][] profitByGame = calculateProfitByGame();
        double profitI;
        double profitJ;
        String[] gameI;
        String[] gameJ;
        String[] temp;
        double end;

        if (profitByGame.length > 5){
            end = 5;
        } else {
            end = profitByGame.length;
        }
        for (int i = 0; i < end; i++) {
            for (int j = i+1; j < profitByGame.length; j++) {
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
        for (int i = 0; i < bottom5Games.length; i++){
            bottom5Games[i] = profitByGame[i];
        }
        return bottom5Games;

    }

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
