package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static data.DataUtil.*;
import static gameStart.Util.*;

public class FileHandler {

    /**
     * Returns a {@link File} created from the location of the sales file.
     *
     * @return The {@link File} corresponding to the sales file.
     */
    public static File fileVendas() {
        return new File("files/GameStart_Vendas.csv");
    }

    /**
     * Returns a {@link File} created from the location of the admins file.
     *
     * @return The {@link File} corresponding to the admins file.
     */
    public static File fileAdmins() {
        return new File("files/GameStart_Admins.csv");
    }

    /**
     * Returns a {@link File} created from the location of the categories file.
     *
     * @return The {@link File} corresponding to the categories file.
     */
    public static File fileCategorias() {
        return new File("files/GameStart_Categorias.csv");
    }

    /**
     * Returns a {@link File} created from the location of the clients file.
     *
     * @return The {@link File} corresponding to the clients file.
     */
    public static File fileClientes() {
        return new File("files/GameStart_Clientes.csv");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for copyrights.
     *
     * @return The {@link File} corresponding to the ASCII graph of copyrights.
     */
    public static File graphCopyright() {
        return new File("files/GameStart_Copyright.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for Game Start.
     *
     * @return The {@link File} corresponding to the ASCII graph of Game Start.
     */
    public static File graphGameStart() {
        return new File("files/CatalogoGrafico/GameStart.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for Call of Duty.
     *
     * @return The {@link File} corresponding to the ASCII graph of Call of Duty.
     */
    public static File graphCallOfDuty() {
        return new File("files/CatalogoGrafico/callOfDuty.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for FIFA.
     *
     * @return The {@link File} corresponding to the ASCII graph of FIFA.
     */
    public static File graphFifa() {
        return new File("files/CatalogoGrafico/fifa.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for Hollow Knight.
     *
     * @return The {@link File} corresponding to the ASCII graph of Hollow Knight.
     */
    public static File graphHollowKnight() {
        return new File("files/CatalogoGrafico/hollowKnight.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for Minecraft.
     *
     * @return The {@link File} corresponding to the ASCII graph of Minecraft.
     */
    public static File graphMinecraft() {
        return new File("files/CatalogoGrafico/minecraft.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for Mortal Kombat.
     *
     * @return The {@link File} corresponding to the ASCII graph of Mortal Kombat.
     */
    public static File graphMortalKombat() {
        return new File("files/CatalogoGrafico/mortalKombat.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for Overcooked.
     *
     * @return The {@link File} corresponding to the ASCII graph of Overcooked.
     */
    public static File graphOvercooked() {
        return new File("files/CatalogoGrafico/overcooked.txt");
    }

    /**
     * Returns a {@link File} created from the location of the ASCII graph file for Witcher 3.
     *
     * @return The {@link File} corresponding to the ASCII graph of Witcher 3.
     */
    public static File graphWitcher3() {
        return new File("files/CatalogoGrafico/witcher3.txt");
    }


    /**
     * Extracts the content of a CSV file into a matrix.
     *
     * @param file         The CSV file to be extracted into a matrix.
     * @param ignoreHeader Whether to ignore the header of the file.
     * @return A matrix with the contents of the file.
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
     * Extracts a CSV file into a matrix, filtering only the rows corresponding to the specified key.
     *
     * @param file         The CSV file to be extracted into a matrix.
     * @param ignoreHeader Whether to ignore the header of the file.
     * @param key          The key used to filter the rows of interest.
     * @param column       The index of the column where the key should be searched.
     * @return A matrix with the filtered information from the file.
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
     * Counts the total number of lines in a file.
     *
     * @param file The file to count lines in.
     * @return The number of lines in the file.
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
     * Counts the number of columns in a CSV file based on its first line.
     *
     * @param file The file to count columns in.
     * @return The number of columns in the file.
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
     * Prints the content of a file.
     *
     * @param file The file to be printed.
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
     * Prints the content of the copyrights file to the screen.
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
     * Extracts the content of a column from the file into an array of unique elements.
     *
     * @param file         The file from which to filter and extract the column to an array.
     * @param column       The index of the column to be extracted.
     * @param ignoreHeader Boolean indicating whether the file's header should be ignored.
     * @return An array containing unique elements from the column.
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
     * Compares user and password values with the admin file information.
     *
     * @param username The username identifying the user.
     * @param password The user's access password.
     * @return true if the information matches a user's credentials, false otherwise.
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
     * Sums the values of a column from a file.
     *
     * @param file         The file from which to sum column values.
     * @param ignoreHeader Boolean indicating whether the file's header should be ignored.
     * @param column       The column to be summed.
     * @return The total sum as a double.
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
     * Calculates the total profit based on sales and category files.
     *
     * @return The total profit value.
     */
    public static double calculateTotalProfit() {

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

            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");

                sair = false;

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
     * Calculates the profit obtained separated by game category.
     *
     * @return A matrix with category information and total profit obtained by games in that category.
     */
    public static String[][] calculateProfitByCategory() {

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

            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine().split(";");
                sair = false;

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

        String[][] categoriesProfitMatrix = new String[categoriesProfit.length][2];
        for (int count = 0; count < categoriesProfit.length; count++) {
            categoriesProfitMatrix[count][0] = categoriesProfit[count][0];
            categoriesProfitMatrix[count][1] = String.valueOf(profitsArray[count]);
        }
        return categoriesProfitMatrix;
    }

    /**
     * Calculates the profit obtained for each game.
     *
     * @return A matrix with the game name and corresponding profit.
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
     * Searches for a client's information based on their id.
     *
     * @param clientId The id of the client to be searched.
     * @return An array of Strings with the client's information, or an empty array if the client is not found.
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
     * Finds the highest value in a column of the file.
     *
     * @param file         The file to be analyzed.
     * @param ignoreHeader Boolean determining whether the first line of the file should be ignored.
     * @param column       The column to search for the highest value.
     * @return String representing the highest value found in the column.
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
