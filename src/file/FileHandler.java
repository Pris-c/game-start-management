package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static data.DataUtil.*;
import static gameStart.Util.openCloseOutput;

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

    public static File graphCopyright() {
        return new File("files/GameStart_Copyright.txt");
    }

    public static File graphGameStart() {
        return new File("files/CatalogoGrafico/GameStart.txt");
    }

    public static File graphCallOfDuty() {
        return new File("files/CatalogoGrafico/callOfDuty.txt");
    }

    public static File graphFifa() {
        return new File("files/CatalogoGrafico/fifa.txt");
    }

    public static File graphHollowKnight() {
        return new File("files/CatalogoGrafico/hollowKnight.txt");
    }

    public static File graphMinecraft() {
        return new File("files/CatalogoGrafico/minecraft.txt");
    }

    public static File graphMortalKombat() {
        return new File("files/CatalogoGrafico/mortalKombat.txt");
    }

    public static File graphOvercooked() {
        return new File("files/CatalogoGrafico/overcooked.txt");
    }

    public static File graphWitcher3() {
        return new File("files/CatalogoGrafico/witcher3.txt");
    }


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
        openCloseOutput();
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println("\n\n");
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }
        openCloseOutput();
    }

    public static void printCopyrights() {
        try {
            Scanner fileScanner = new Scanner(graphCopyright());
            while (fileScanner.hasNext()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println("\n\n");
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }
    }


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
        }

        return new String[0];
    }

    public static void printAdvetisingFileNotFound() {
        openCloseOutput();
        System.out.println("Arquivo não encontrado. Por favor, contacte o suporte.");
        openCloseOutput();

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
        String[][] categoriesProfit = extractCSVFileToMatrix(fileCategorias(), true);
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
                        profit += (value * (margin / 100));
                        sair = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }

        return profit;
    }
    public static String[][] calculateProfitByCategory() {
        String[][] categoriesProfit = extractCSVFileToMatrix(fileCategorias(), true);
        double profit = 0;

        double[] profitsArray = new double[categoriesProfit.length];
        for (int c = 0; c < profitsArray.length; c++){
            profitsArray[c] = 0;
        }

        try {
            Scanner fileScanner = new Scanner(fileVendas());
            int posCateg = 3;
            int posValue = 5;

            String line[];
            double margin;
            double value;
            boolean sair;
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");

                sair = false;
                for (int i = 0; i < categoriesProfit.length && !sair; i++) {
                    // Comparar categoria da linha com categoria do categoriesProfit
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
        }

        String[][] categoriesProfitMatrix = new String[categoriesProfit.length][2];
        for (int count = 0; count < categoriesProfit.length; count++){
            categoriesProfitMatrix[count][0] = categoriesProfit[count][0];
            categoriesProfitMatrix[count][1] = String.valueOf(profitsArray[count]);
        }
        return categoriesProfitMatrix;
    }

    public static String[][] calculateProfitByGame() {
        String[][] categoriesProfit = extractCSVFileToMatrix(fileCategorias(), true);
        int gamesColumn = 4;
        int categoryColumn = 3;
        int valueColumn = 5;
        String[] gamesSet = fileColumnToSet(fileVendas(), gamesColumn, true);

        double[] profitsArray = new double[gamesSet.length];

        for (int c = 0; c < profitsArray.length; c++){
            profitsArray[c] = 0;
        }

        try {
            Scanner fileScanner = new Scanner(fileVendas());
            fileScanner.nextLine();

            String line[];
            double margin;
            double value;
            boolean sair;
            double profit = 0;
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
                            for (int w = 0; w < gamesSet.length && !sair; w++){
                                if (gamesSet[w].equals(line[gamesColumn])){
                                    profitsArray[w] += profit;
                                    sair = true;
                                }
                            }
                        }
                    }
            }
        } catch (FileNotFoundException e) {
            printAdvetisingFileNotFound();
        }

        String[][] gameProfitMatrix = new String[gamesSet.length][2];
        for (int count = 0; count < gamesSet.length; count++){
            gameProfitMatrix[count][0] = gamesSet[count];
            gameProfitMatrix[count][1] = String.valueOf(profitsArray[count]);
        }
        return gameProfitMatrix;
    }


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
        }

        return new String[0];
    }

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
        }
        return stringBiggestValue;
    }



}
