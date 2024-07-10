package data;

import java.io.File;

import static data.DataUtil.arrayToSet;
import static data.DataUtil.extractColumnToArray;
import static file.FileHandler.*;
import static gameStart.Util.*;

public class Sales {

    // idVenda / idCliente / editora / categoria / jogo / valor

    public static void printGamesCatalog() {

        System.out.println(" -- Catálogo de Jogos -- ");
        printArray(columnToSet(fileVendas(), 4, true));
        System.out.println(" -- Fim do Catálogo de Jogos -- ");
    }

}
