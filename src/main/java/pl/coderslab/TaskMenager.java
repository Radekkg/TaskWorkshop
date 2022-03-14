package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Scanner;

public class TaskMenager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("tasks.csv");

        try (Scanner fileScanner = new Scanner(file)) {

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
            e.getMessage();
        }


        boolean quitProgram = true;


        while (quitProgram) {
            options();
            String input = scanner.nextLine();
            switch (input) {
                case "add":
                    System.out.println("Jestem w ADD");
                    addTask();
                    break;
                case "remove":
                    System.out.println("Jestem w REMOVE");
                    removeTask();
                    break;
                case "list":
                    System.out.println("Jestem w LIST");
                    listTask();
                    break;
                case "quit":
                    System.out.println("Jestem w QUIT");
                    quitTask();
                default:
                    System.out.println("Please select a correct option.");
            }
        }
    }

    private static void quitTask() {

    }

    private static void listTask() {

    }

    private static void removeTask() {

    }

    private static void addTask() {
    }

    static void options() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }
}
