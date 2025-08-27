package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;


public class AddTask {


    public static void AddTaskForm(Map<Integer, Task> tasks, Scanner scanner) {
        boolean openAddTaskForm = true;

        System.out.println("\nTo add a task, please enter the following information with the specified format:");

        while (openAddTaskForm) {

            int taskId = tasks.keySet().stream()
                    .max(Integer::compareTo)
                    .orElse(0) + 1;

            String taskTitle = promptTitleOrDescription("Enter Title: ", scanner);
            String taskDescription = promptTitleOrDescription("Enter Description: ", scanner);

            String taskStatus = promptStatus(scanner);

            LocalDate taskDueDate = promptDueDate(scanner);

            Task newTask = new Task(taskId, taskTitle, taskDescription, taskStatus, taskDueDate);

            FileHandler.saveNewTask(tasks, newTask);

            openAddTaskForm = false;
        }
    }


    private static String promptTitleOrDescription(String message, Scanner scanner) {
        String input = "";

        while (input.isEmpty()) {
            System.out.print(message);
            input = scanner.nextLine().trim();

            if (input.contains(",")) {
                System.out.println("Invalid input, please remove commas from text and try again");
                input = "";
            }
        }

        return input;
    }


    private static String promptStatus(Scanner scanner) {
        String status = "";

        while (!"COMPLETED".equals(status) && !"PENDING".equals(status)) {
            System.out.print("Enter status (PENDING/COMPLETED): ");
            status = scanner.nextLine().trim().toUpperCase();

            if (!"COMPLETED".equals(status) && !"PENDING".equals(status)) {
                System.out.println("Invalid status, please enter status as: PENDING or COMPLETED");
            }
        }

        return status;
    }


    private static LocalDate promptDueDate(Scanner scanner) {
        LocalDate dueDate = null;

        while (dueDate == null) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();

            try {
                dueDate = LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println("Invalid date, please use date format: YYYY-MM-DD");
            }
        }

        return dueDate;
    }
}