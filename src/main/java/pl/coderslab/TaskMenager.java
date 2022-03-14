package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.Scanner;

public class TaskMenager {
    static String[][] taskArray;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("tasks.csv");
        taskArray = new String[0][3];

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String textLine = fileScanner.nextLine();

                String[] splitLine = textLine.split(",");

                taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
                taskArray[taskArray.length - 1] = new String[3];

                taskArray[taskArray.length - 1][0] = splitLine[0];
                taskArray[taskArray.length - 1][1] = splitLine[1];
                taskArray[taskArray.length - 1][2] = splitLine[2];
            }

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
                    listTask(taskArray);
                    break;
                case "exit":
                    System.out.println("Jestem w QUIT");
                    quitTask();
                default:
                    System.out.println("Please select a correct option.");
            }
        }
    }

    private static void quitTask() {
        System.out.println(ConsoleColors.RED + "Bye ,Bye " + ConsoleColors.RESET);
    }

    private static void listTask(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void removeTask() {
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        boolean flag = true;
        System.out.println("Please select number to remove :");
        String selectedToRemove = scanner.nextLine();
        //TODO ZROBIC WALIDACJE ZEBY MOZNA TYLKO WPISYWAC LICZBY
        number = Integer.parseInt(selectedToRemove);
        taskArray = ArrayUtils.remove(taskArray, number);

        //System.out.println("Incorrect argument passed. Please give a number greater or equal 0");
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please ass task description");
        String description = scanner.nextLine();

        System.out.println("Please add task due date");
        String date = scanner.nextLine();

        System.out.println("Is your task is important true/false");
        String importatnt = scanner.nextLine();

        taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
        taskArray[taskArray.length - 1] = new String[3];
        taskArray[taskArray.length - 1][0] = description;
        taskArray[taskArray.length - 1][1] = date;
        taskArray[taskArray.length - 1][2] = importatnt;

    }

    static void options() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }
}
