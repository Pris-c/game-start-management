package data;

import java.io.File;

import static data.DataUtil.arrayToSet;
import static data.DataUtil.extractColumnToArray;
import static file.FileHandler.extractToMatrix;
import static gameStart.Util.*;

public class Sales {

    // idVenda / idCliente / editora / categoria / jogo / valor

    public static File gameStart_Vendas(){
        return new File("files/GameStart_Vendas.csv");
    }

    public static void printGamesCatalog() {
        String[][] matrixSales = extractToMatrix(gameStart_Vendas(), true);
        String[] rawGamesTitle = extractColumnToArray(matrixSales, 4);
        System.out.println(" -- Catálogo de Jogos -- ");
        printArray(arrayToSet(rawGamesTitle));
        System.out.println(" -- Fim do Catálogo de Jogos -- ");
    }

}
