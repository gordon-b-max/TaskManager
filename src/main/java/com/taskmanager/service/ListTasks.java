package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.util.Messages;

import java.util.Map;
import java.util.Scanner;


public class ListTasks {


    public static void ListOptions(Map<Integer, Task> tasks, Scanner scanner) {

        boolean openListMenu = true;

        while (openListMenu) {
            System.out.println(Messages.LIST_TASKS_VIEW);

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> showAllTasks(tasks);
                case "2" -> showCompletedTasks(tasks);
                case "3" -> showPendingTasks(tasks);
                case "4" -> openListMenu = false;
                default -> System.out.println(Messages.INVALID_INPUT_LIST_OPTIONS);
            }
        }
    }


    private static void showAllTasks(Map<Integer, Task> tasks) {
        System.out.println(Messages.LIST_TASKS_ALL);
        tasks.values().forEach(System.out::println);
    }


    private static void showCompletedTasks(Map<Integer, Task> tasks) {
        System.out.println(Messages.LIST_TASKS_COMPLETED);
        tasks.values().stream()
                .filter(task -> "COMPLETED".equalsIgnoreCase(task.getStatus()))
                .forEach(System.out::println);
    }


    private static void showPendingTasks(Map<Integer, Task> tasks) {
        System.out.println(Messages.LIST_TASKS_PENDING);
        tasks.values().stream()
                .filter(task -> "PENDING".equalsIgnoreCase(task.getStatus()))
                .forEach(System.out::println);
    }
}