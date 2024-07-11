package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static data.DataUtil.arrayContain;
import static gameStart.Util.*;

public class FileHandler {


    public static File fileVendas() {
        return new File("files/GameStart_Vendas.csv");
    }

    public static File fileAdmins() {
        return new File("files/GameStart_Admins.csv");
    }

    public static File fileCategorias() {
        return new File("files/GameStart_Categorias.csv");
    }

    public static File fileClientes() {
        return new File("files/GameStart_Clientes.csv");
    }

    public static File fileCallOfDuty() {
        return new File("files/CatalogoGrafico/callOfDuty.txt");
    }

    public static File fileFifa() {
        return new File("files/CatalogoGrafico/fifa.txt");
    }

    public static File fileHollowKnight() {
        return new File("files/CatalogoGrafico/hollowKnight.txt");
    }

    public static File fileMinecraft() {
        return new File("files/CatalogoGrafico/minecraft.txt");
    }

    public static File fileMortalKombat() {
        return new File("files/CatalogoGrafico/mortalKombat.txt");
    }

    public static File fileOvercooked() {
        return new File("files/CatalogoGrafico/overcooked.txt");
    }

    public static File fileWitcher3() {
        return new File("files/CatalogoGrafico/witcher3.txt");
    }


    public static String[][] extractToMatrix(File file, boolean ignoreHeader) {
        try {
            Scanner fileScanner = new Scanner(file);
            int countColumns = countColumns(file);
            int countLines = countLines(file);

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
        } catch (FileNotFoundException ex) {
            printAdvetisingFileNotFound();
        }
        return new String[0][0];
    }


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

            int lineInd = 0;
            String[] line;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");
                if (key.equalsIgnoreCase(line[column])) {
                    for (int i = 0; i < countColumns; i++) {
                        matrix[lineInd][i] = line[i];
                    }
                    lineInd++;
                }
            }

            String[][] filteredMatrix = new String[lineInd][countColumns];
            for (int i = 0; i < lineInd; i++) {
                for (int k = 0; k < countColumns; k++) {
                    filteredMatrix[i][k] = matrix[i][k];
                }
            }
            return filteredMatrix;

        } catch (FileNotFoundException ex) {
            printAdvetisingFileNotFound();
        }
        return new String[0][0];
    }

    // Pode usar sobrescrita ?
    public static String[][] filterFileToMatrixByDoubleValue(File file, boolean ignoreHeader, double key, int column) {
        try {
            Scanner fileScanner = new Scanner(file);
            int countColumns = countColumns(file);
            int countLines = countLines(file);

            if (ignoreHeader) {
                fileScanner.nextLine();
                countLines--;
            }

            String[][] matrix = new String[countLines][countColumns];

            int lineInd = 0;
            String[] line;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");
                if (key == Double.parseDouble(line[column])) {
                    for (int i = 0; i < countColumns; i++) {
                        matrix[lineInd][i] = line[i];
                    }
                    lineInd++;
                }
            }

            String[][] filteredMatrix = new String[lineInd][countColumns];
            for (int i = 0; i < lineInd; i++) {
                for (int k = 0; k < countColumns; k++) {
                    filteredMatrix[i][k] = matrix[i][k];
                }
            }
            return filteredMatrix;

        } catch (FileNotFoundException ex) {
            printAdvetisingFileNotFound();
        }
        return new String[0][0];
    }


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
        }
        return 0;
    }

    public static int countColumns(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            if (fileScanner.hasNext()) {
                return fileScanner.nextLine().split(";").length;
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }

        return 0;
    }

    public static void printFile(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println("\n\n");
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }
    }

    public static String[] columnToSet(File file, int column, boolean ignoreHeader) {
        try {
            Scanner fileScanner = new Scanner(file);
            int countLines = countLines(file);
            if (ignoreHeader) {
                fileScanner.nextLine();
                countLines--;
            }
            String[] columnArray = new String[countLines];
            columnArray[0] = fileScanner.nextLine().split(";")[column];

            String rawElement;
            int countSetElements = 1;

            // Este ciclo percorrer a coluna especificada em ao longo do arquivo e salvar valores de títulos distintos no columnArray
            while (fileScanner.hasNext()) {
                rawElement = fileScanner.nextLine().split(";")[column];
                if (!arrayContain(columnArray, rawElement, countSetElements)) {
                    columnArray[countSetElements] = rawElement;
                    countSetElements++;
                }
            }

            String[] columnSet = new String[countSetElements];

            for (int i = 0; i < countSetElements; i++) {
                columnSet[i] = columnArray[i];
            }

            return columnSet;

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }

        return new String[0];
    }

    public static void printAdvetisingFileNotFound() {
        System.out.println("Arquivo não encontrato. Por favor, contacte o suporte.");
    }


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
        }

        return success;
    }

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
        }

        return sum;
    }


    public static double calculateTotalProfit() {
        String[][] categoriesProfit = extractToMatrix(fileCategorias(), true);
        double profit = 0;

        try {
            Scanner fileScanner = new Scanner(fileVendas());
            int posCateg = 3;
            int posValue = 5;

            String line[];
            double margin;
            double value;
            boolean sair;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");

                sair = false;
                for (int i = 0; i < categoriesProfit.length && !sair; i++) {
                    // Comparar categoria da linha com categoria do categoriesProfit
                    if (line[posCateg].equals(categoriesProfit[i][0])) {
                        value = Double.parseDouble(line[posValue]);
                        margin = Double.parseDouble(categoriesProfit[i][1]);
                        profit += (value * (margin/100));
                        sair = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }

        return profit;
    }

    public static String[] findClient(int clientId){
        String[] line;

        try{
            Scanner fileScanner = new Scanner(fileClientes());
            fileScanner.nextLine();

            while(fileScanner.hasNext()){
                line = fileScanner.nextLine().split(";");

                if (Integer.parseInt(line[0]) == clientId){
                    return line;
                }
            }

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }

        return new String[0];
    }

    public static double findBiggestValue(File file, boolean ignoreHeader, int column){
        double biggestValue = 0;

        try{
            Scanner fileScanner = new Scanner(file);

            if (ignoreHeader){
                fileScanner.nextLine();
            }

            double value;
            while (fileScanner.hasNext()){
                value = Double.parseDouble(fileScanner.nextLine().split(";")[column]);
                if (value > biggestValue){
                    biggestValue = value;
                }
            }

        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }
        return biggestValue;
    }

}
