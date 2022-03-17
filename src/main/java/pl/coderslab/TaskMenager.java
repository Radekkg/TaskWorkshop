package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskMenager {
    static String[][] taskArray;

    public static void main(String[] args) {
        Scanner scanner = initProgram();
        boolean quitProgram = true;


        while (quitProgram) {
            options();
            String input = scanner.nextLine();
            switch (input) {
                case "add":
                    System.out.println("add");
                    addTask();
                    break;
                case "remove":
                    System.out.println("remove");
                    removeTask();
                    break;
                case "list":
                    System.out.println("list");
                    listTask(taskArray);
                    break;
                case "exit":
                    System.out.println("quit");
                    quitTask();
                    System.exit(0);
                default:
                    System.out.println("Please select a correct option.");
            }
        }
    }

    private static Scanner initProgram() {
        Scanner scanner = new Scanner(System.in);
        File file = new File("tasks.csv");
        taskArray = new String[0][3];

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String textLine = fileScanner.nextLine();

                String[] splitLine = textLine.split(",");

                taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);
                taskArray[taskArray.length - 1] = new String[3];

                taskArray[taskArray.length - 1][0] = splitLine[0].trim();
                taskArray[taskArray.length - 1][1] = splitLine[1].trim();
                taskArray[taskArray.length - 1][2] = splitLine[2].trim();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
            e.getMessage();
        }
        return scanner;
    }

    private static void quitTask() {
        Path path = Paths.get("tasks.csv");
        String line = "";
        List<String> outList = new ArrayList<>();
        for (int i = 0; i < taskArray.length; i++) {
            if (i == taskArray.length - 1) {
                line += taskArray[i][0] + ", "
                        + taskArray[i][1] + ", "
                        + taskArray[i][2];
            }else {
                line += taskArray[i][0] + ", "
                        + taskArray[i][1] + ", "
                        + taskArray[i][2] +"\n";
            }

        }
        outList.add(line);

        try {
            Files.write(path, outList);
            System.out.println(ConsoleColors.RED + "Bye ,Bye " + ConsoleColors.RESET);
        } catch (
                IOException ex) {
            ex.printStackTrace();
            System.out.println("I can't write file");
        }

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
        while (flag) {
            String selectedToRemove = scanner.nextLine();
            System.out.println(selectedToRemove);
            try {
                number = Integer.parseInt(selectedToRemove);
                taskArray = ArrayUtils.remove(taskArray, number);
                flag = false;
                System.out.println("Value was successfully deleted");
            } catch (NumberFormatException ex) {
                ex.getStackTrace();
                System.out.println("Incorrect argument passed. Please give a number greater or equal 0");
            } catch (IndexOutOfBoundsException ex) {
                ex.getStackTrace();
                System.out.println("Incorrect argument passed. Please give a number greater or equal 0");
            }
        }

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
