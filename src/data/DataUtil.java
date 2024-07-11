package data;

import static file.FileHandler.fileVendas;
import static file.FileHandler.filterFileToMatrix;
import static gameStart.Client.filterGames;
import static gameStart.Util.printArray;

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
            System.out.println("\n-------- ");
            System.out.println("Nenhum Jogo Encontrado.");
            System.out.println("--------\n");
        } else {
            System.out.println("\n********** Catálogo " + matrixByKey[0][columnKey] + " **********\n");

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
            System.out.println("****** Fim do Catálogo " + matrixByKey[0][columnKey] + " ******\n");
        }
    }

}
