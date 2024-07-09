package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {



    public static File gameStart_Admins(){
        return new File("files/GameStart_Admins.csv");
    }

    public static File gameStart_Categorias(){
        return new File("files/GameStart_Categorias.csv");
    }

    public static File gameStart_Clientes(){
        return new File("files/GameStart_Clientes.csv");
    }



    public static String[][] extractToMatrix(File file, boolean ignoreHeader) {
        try{
            Scanner fileScanner = new Scanner(file);
            int countColumns = countColumns(file);
            int countLines = countLines(file) ;

            if (ignoreHeader) {
                fileScanner.nextLine();
                countLines--;
            }

            String[][] matrix = new String[countLines][countColumns];

            int lineInd = 0;
            while (fileScanner.hasNext()) {
                String[] currentLine = fileScanner.nextLine().split(";");
                for (int nColumn = 0; nColumn < countColumns; nColumn++) {
                    matrix[lineInd][nColumn] = currentLine[nColumn];
                }
                lineInd++;
            }
            return matrix;
        } catch (FileNotFoundException ex){
            System.out.println("Arquivo não encontrato. Por favor, contacte o suporte.");
        }
        return new String[0][0];
    }


    public static int countLines(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        int countLines = 0;
        while (fileScanner.hasNext()) {
            countLines++;
            fileScanner.nextLine();
        }
        return countLines;
    }

    public static int countColumns(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        int countColumns = 0;
        if (fileScanner.hasNext()) {
            countColumns = fileScanner.nextLine().split(";").length;
        }
        return countColumns;
    }


}
