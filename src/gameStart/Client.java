package gameStart;

import java.util.Scanner;

import static gameStart.Util.cleanScreen;
import static gameStart.Util.triangularNumber;

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



}
