package data;

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

}
