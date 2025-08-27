package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;

import java.util.Map;
import java.util.Scanner;


public class DeleteTask {


    public static void DeleteTaskForm(Map<Integer, Task> tasks, Scanner scanner) {
        boolean openDeleteTaskForm = true;

        System.out.println("""
                
                To delete a task, please enter the associated task 'id' located in the 'data/tasks.csv'
                    directory. Additionally, the task 'id' can be located by selecting 'List Tasks', then by
                    selecting either 'All', 'Completed', or 'Pending' from the Task Manager Main Menu.
                """);


        while (openDeleteTaskForm) {

            Integer taskIdToDelete = promptTaskId(tasks, scanner);
            Task taskToDelete = tasks.get(taskIdToDelete);
            System.out.println("\nSuccessfully found task: \n" + taskToDelete);

            System.out.println("\nTo delete the task, please press '1' to confirm or '2' to cancel and " +
                    "return to the Task Manager Main Menu");

            String inputDeleteTask = promptDeleteTask(scanner);

            if ("1".equals(inputDeleteTask)) {

                tasks.remove(taskIdToDelete);
                FileHandler.saveTasks(tasks);

                System.out.println("\nSuccessfully deleted task! Returning to Task Manager main menu...");
                openDeleteTaskForm = false;

            }
            if ("2".equals(inputDeleteTask)) {
                System.out.println("Canceling task deletion. Returning to Task Manager main menu...");
                openDeleteTaskForm = false;
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

            } catch (Exception e) {
                System.out.println("Unable to find task 'id' with error message: " + e.getMessage());
                System.out.println("Please double check task 'id' and try again...");
            }
        }

        return taskIdToUpdate;
    }


    private static String promptDeleteTask(Scanner scanner) {
        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!"1".equals(input) && !"2".equals(input)) {
                System.out.println("Invalid input, please enter '1' to delete task or '2' to cancel deleting task");
            }
        }

        return input;
    }
}
