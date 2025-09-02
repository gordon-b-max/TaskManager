package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.util.Constants;

import java.util.List;
import java.util.Scanner;


public class ListTasks {


    public static void ListOptions(TaskCollection tasks, Scanner scanner) {

        boolean openListMenu = true;

        while (openListMenu) {
            System.out.println(Constants.LIST_TASKS_VIEW);

            String input = scanner.nextLine().trim();

            switch (input) {

                case Constants.INPUT_1 -> showAllTasks(tasks);

                case Constants.INPUT_2 -> showCompletedTasks(tasks);

                case Constants.INPUT_3 -> showPendingTasks(tasks);

                case Constants.INPUT_4 -> openListMenu = false;

                default -> System.out.println(Constants.INVALID_INPUT_LIST_OPTIONS);
            }
        }
    }


    private static void showAllTasks(TaskCollection tasks) {
        List<Task> listAllTasks = tasks.listAllTasks();

        System.out.println(Constants.LIST_TASKS_ALL);
        listAllTasks.forEach(System.out::println);
    }


    private static void showCompletedTasks(TaskCollection tasks) {
        List<Task> listCompletedTasks = tasks.listCompletedTasks();

        System.out.println(Constants.LIST_TASKS_COMPLETED);
        listCompletedTasks.forEach(System.out::println);
    }


    private static void showPendingTasks(TaskCollection tasks) {
        List<Task> listPendingTasks = tasks.listPendingTasks();

        System.out.println(Constants.LIST_TASKS_PENDING);
        listPendingTasks.forEach(System.out::println);
    }
}