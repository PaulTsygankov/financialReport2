import handler.Handler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Handler handler = new Handler();
        int command = -1;

        System.out.println("Привет! Выберите действие:");

        while (true) {
            printMainMenu();

            try {
                command = scanner.nextInt();
                if (command == 0) {
                    System.out.println("Выход.");
                    break;
                }
                handler.handleCommand(command);
            } catch (NumberFormatException exception) {
                System.out.println("Вы ввели не целое положительное число!");
            }
        }
        

    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("1. Считать все месячные отчеты.");
        System.out.println("2. Считать годовой отчет.");
        System.out.println("3. Сверить отчеты.");
        System.out.println("4. Вывести информацию о всех месячных отчетах.");
        System.out.println("5. Вывести информацию о годовом отчете.");
        System.out.println("0. Выход из приложения.");
        System.out.println();
    }

}