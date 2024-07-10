package gameStart;

import java.io.File;
import java.util.Scanner;

import static data.DataUtil.arrayToSet;
import static data.DataUtil.extractColumnToArray;
import static data.Sales.gameStart_Vendas;
import static file.FileHandler.*;
import static gameStart.Util.*;

public class Client {

    public static void register(){
        Scanner input = new Scanner(System.in);
        String clientName, clientPhone, clientMail;

        System.out.println(" -- Registrar Cliente -- ");
        System.out.print("Nome do cliente: ");
        clientName = input.nextLine();
        System.out.print("Telefone para contacto: ");
        clientPhone = input.nextLine();
        System.out.print("Endereço de e-mail: ");
        clientMail = input.nextLine();

        cleanScreen();
        System.out.println("Cliente registrado com sucesso: " + clientName + " | " + clientPhone + " | " + clientMail);
    }


    public static void findParking(){
        System.out.println("Vagas disponíveis: ");
        for (int i = 0; i <= 121; i+=5){
            if (triangularNumber(i)){
                System.out.print(i + "\t");
            }
        }
        System.out.println();
    }

    public static void printGraph(){
        int option = 0;

        do{
            System.out.println(" -- Catálogo Gráfico -- ");
            System.out.println("Escolha o gráfico a ser impresso: ");
            System.out.println("1 - Call Of Duty");
            System.out.println("2 - Fifa");
            System.out.println("3 - Hollow Knight");
            System.out.println("4 - Minecraft");
            System.out.println("5 - Mortal Kombat");
            System.out.println("6 - Overcooked");
            System.out.println("7 - Witcher 3");
            System.out.println("\033[3mPara voltar, digite \"0\".");
            option = validateOption(0,7);

            switch (option){
                case 1:
                    printFile(callOfDutyFile());
                    break;
                case 2:
                    printFile(fifaFile());
                    break;
                case 3:
                    printFile(hollowKnightFile());
                    break;
                case 4:
                    printFile(minecraftFile());
                    break;
                case 5:
                    printFile(mortalKombatFile());
                    break;
                case 6:
                    printFile(overcookedFile());
                    break;
                case 7:
                    printFile(witcher3File());
                    break;
            }

        } while (option != 0);

    }

    public static void printNewestGame(){
        cleanScreen();
        String[][] matrixSales = extractToMatrix(gameStart_Vendas(), true);
        String[] rawGamesTitle = extractColumnToArray(matrixSales, 4);
        String[] set = arrayToSet(rawGamesTitle);

        System.out.println("Jogo mais recente: ");
        System.out.println(set[set.length-1]);
        System.out.println(" ------------- ");
        System.out.println();
    }




}
