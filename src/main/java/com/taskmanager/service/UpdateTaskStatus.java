package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;

import java.util.Map;
import java.util.Scanner;


public class UpdateTaskStatus {


    public static void UpdateTaskStatusForm(Map<Integer, Task> tasks, Scanner scanner) {
        boolean openUpdateTaskStatusForm = true;

        System.out.println("""
                
                To update the status of a task, please enter the associated task 'id' located in the 'data/tasks.csv'
                    directory. Additionally, the task 'id' can be located by selecting 'List Tasks', then by
                    selecting either 'All', 'Completed', or 'Pending' from the Task Manager Main Menu.
                
                """);

        while (openUpdateTaskStatusForm) {

            Integer taskIdToUpdate = promptTaskId(tasks, scanner);
            Task taskToUpdate = tasks.get(taskIdToUpdate);
            System.out.println("\nSuccessfully found task: \n" + taskToUpdate);

            String currentTaskStatus = taskToUpdate.getStatus();
            System.out.println("\nThe current status for this task is: " + currentTaskStatus);

            String updatedTaskStatus = "PENDING".equalsIgnoreCase(currentTaskStatus) ? "COMPLETED" : "PENDING";

            System.out.println("Do you want to update the status of this task to: '" + updatedTaskStatus + "'?");
            System.out.println("1. Yes\n2. Cancel Update");

            String inputStatusUpdate = promptChangeStatus(scanner);

            if ("1".equals(inputStatusUpdate)) {

                taskToUpdate.setStatus(updatedTaskStatus);
                FileHandler.saveTasks(tasks);

                System.out.println("\nSuccessfully updated task status! Returning to Task Manager main menu...");
                openUpdateTaskStatusForm = false;

            }
            if ("2".equals(inputStatusUpdate)) {
                System.out.println("Canceling updating task status. Returning to Task Manager main menu...");
                openUpdateTaskStatusForm = false;
            }
        }
    }


    private static Integer promptTaskId(Map<Integer, Task> tasks, Scanner scanner) {
        Integer taskIdToUpdate = null;

        while (taskIdToUpdate == null) {
            System.out.print("Enter ID: ");
            String input = scanner.nextLine().trim();

            try {
                int parsedId = Integer.parseInt(input);

                taskIdToUpdate = tasks.keySet().stream()
                        .filter(taskId -> taskId.equals(parsedId))
                        .findFirst()
                        .orElse(null);

                if (taskIdToUpdate == null) {
                    System.out.println("Unable to find task 'id'. Please double check task 'id' and try again...");
                }

            } catch (Exception e) {
                System.out.println("Unable to find task 'id' with error message: " + e.getMessage());
            }
        }

        return taskIdToUpdate;
    }


    private static String promptChangeStatus(Scanner scanner) {
        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!"1".equals(input) && !"2".equals(input)) {
                System.out.println("Invalid input, please enter '1' to update status or '2; to cancel updating task");
            }
        }

        return input;
    }
}