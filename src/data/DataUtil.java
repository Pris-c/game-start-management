package data;

import static file.FileHandler.*;

public class DataUtil {

    public static String[] extractColumnToArray(String[][] matrix, int column){
        String[] columnArray = new String[matrix.length];

        for(int i = 0; i < columnArray.length; i++){
            columnArray[i] = matrix[i][column];
        }

        return columnArray;
    }

    public static boolean arrayContain(String[] array, String text, int size){
        for (int i = 0; i < size; i++){
            if (text.equalsIgnoreCase(array[i])){
                return true;
            }
        }
        return false;
    }

    public static String[] arrayToSet(String[] rawArray){
        String[] set;
        String[] filteredArray = new String[rawArray.length];
        filteredArray[0] = rawArray[0];

        int countSetElements = 1;
        String rawElement;

        for (int i = 0; i < rawArray.length; i++){
            rawElement = rawArray[i];
            if (!arrayContain(filteredArray, rawElement, countSetElements)) {
                filteredArray[countSetElements] = rawElement;
                countSetElements++;
            }
        }

        set = new String[countSetElements];
        for (int i = 0; i < countSetElements; i++){
            set[i] = filteredArray[i];
        }
        return set;
    }

    public static String[][] filterMatrixByColumn(String[][] matrix, String value, int column){
        String[][] newMatrix = new String[matrix.length][matrix[0].length];
        int countLines = 0;

        // Filtra linhas da matrix de acordo com valor e coluna informados
        for (int i = 0; i < matrix.length; i++){
            if (matrix[i][column].equalsIgnoreCase(value)){
                newMatrix[countLines] = matrix[i];
                countLines++;
            }
        }

        String[][] filteredMatrix = cleanEmptyMatrixLines(newMatrix, countLines);
        return filteredMatrix;
    }

    public static String[] cleanEmptyArrayPlaces(String[] array, int realSize){
        String[] newArray = new String[realSize];
        for (int i = 0; i < realSize; i++){
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


    public static void printGamesByKey(int key1Column, String key1, int key2Column){

        String[][] gamesByKey1 = filterFileToMatrix(fileVendas(), true, key1, key1Column);
        String[] key2Array = extractColumnToArray(gamesByKey1, key2Column);
        String[] key2Set = arrayToSet(key2Array);

        System.out.println("\n********** Catálogo " + key1 + " **********");

        // Percorrer cada categoria relacionada à editora
        for (int i = 0; i < key2Set.length; i++){
            System.out.println();
            System.out.println("-- " + key2Set[i] + " --");

            // Criar um array para guardar os jogos relacionados à categoria
            String[] gamesByKey2 = new String[gamesByKey1.length];
            int countGamesArray = 0;

            // Percorrer matriz buscando pelos jogos na categoria
            for (int k = 0; k < gamesByKey1.length; k++){

                // Compara categoria do setCategory, com a categoria da linha da matriz
                if (key2Set[i].equalsIgnoreCase(gamesByKey1[k][key2Column])){
                    String game = gamesByKey1[k][4];

                    for (int j = 0; (j < countGamesArray) || (j == 0); j++){
                        if(!arrayContain(gamesByKey2, game, countGamesArray)){
                            gamesByKey2[countGamesArray] = game;
                            countGamesArray++;
                            System.out.println(countGamesArray + ". " + game);
                        }
                    }
                }
            }
        }
        System.out.println("\n********** Fim Catálogo " + key1 + " **********");
    }

}
