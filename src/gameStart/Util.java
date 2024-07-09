package gameStart;

public class Util {


    public static void printArray(String[] array){
        for (int i = 0; i < array.length; i++){
            System.out.println((i+1) + ".\t" + array[i]);
        }

    }

    public static boolean triangularNumber(int num){
        int i = 1;
        int soma = 0;

        while (soma < num){
            soma += i;
            i++;
        }

        return soma == num;
    }


    public static void cleanScreen(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }



}
