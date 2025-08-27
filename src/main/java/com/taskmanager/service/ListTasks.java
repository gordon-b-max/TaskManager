package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;

import java.util.Map;
import java.util.Scanner;


public class ListTasks {


    public static void ListOptions(Map<Integer, Task> tasks, Scanner scanner) {

        boolean openListMenu = true;

        while (openListMenu) {
            showListMenu();

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> showAllTasks(tasks);
                case "2" -> showCompletedTasks(tasks);
                case "3" -> showPendingTasks(tasks);
                case "4" -> openListMenu = false;
                default -> System.out.println("Invalid action. Please enter an action numerically 1-4");
            }
        }
    }


    private static void showAllTasks(Map<Integer, Task> tasks) {
        System.out.println("\nAll Tasks:");
        tasks.values().forEach(System.out::println);
    }


    private static void showCompletedTasks(Map<Integer, Task> tasks) {
        System.out.println("\nCompleted Tasks:");
        tasks.values().stream()
                .filter(task -> "COMPLETED".equalsIgnoreCase(task.getStatus()))
                .forEach(System.out::println);
    }


    private static void showPendingTasks(Map<Integer, Task> tasks) {
        System.out.println("\nPending Tasks:");
        tasks.values().stream()
                .filter(task -> "PENDING".equalsIgnoreCase(task.getStatus()))
                .forEach(System.out::println);
    }


    private static void showListMenu() {
        System.out.print("""
                
                Please select which tasks to display on the menu below: (1-4)
                1. List All Tasks
                2. List Completed Tasks
                3. List Pending Tasks
                4. Return to Main Menu
                """
        );
    }
}
