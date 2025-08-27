package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.util.Messages;

import java.util.Map;
import java.util.Scanner;


public class UpdateTaskStatus {


    public static void UpdateTaskStatusForm(Map<Integer, Task> tasks, Scanner scanner) {
        boolean openUpdateTaskStatusForm = true;

        System.out.println(Messages.TASK_ID_VIEW);

        while (openUpdateTaskStatusForm) {

            Integer taskIdToUpdate = promptTaskId(tasks, scanner);
            Task taskToUpdate = tasks.get(taskIdToUpdate);
            System.out.println(Messages.TASK_FOUND + taskToUpdate);

            String currentTaskStatus = taskToUpdate.getStatus();
            System.out.println(Messages.UPDATE_TASK_STATUS_CURRENT + currentTaskStatus);

            String updatedTaskStatus = "PENDING".equalsIgnoreCase(currentTaskStatus) ? "COMPLETED" : "PENDING";

            System.out.println(Messages.UPDATE_TASK_STATUS_CONFIRM + updatedTaskStatus);
            System.out.println(Messages.TASK_CHANGE_CONFIRM);

            String inputStatusUpdate = promptChangeStatus(scanner);

            if ("1".equals(inputStatusUpdate)) {

                taskToUpdate.setStatus(updatedTaskStatus);
                FileHandler.saveTasks(tasks);

                System.out.println(Messages.UPDATE_TASK_STATUS_SUCCESS);
                openUpdateTaskStatusForm = false;
 
            }
            if ("2".equals(inputStatusUpdate)) {
                System.out.println(Messages.UPDATE_TASK_STATUS_CANCEL);
                openUpdateTaskStatusForm = false;
            }
        }
    }


    private static Integer promptTaskId(Map<Integer, Task> tasks, Scanner scanner) {
        Integer taskIdToUpdate = null;

        while (taskIdToUpdate == null) {
            System.out.print(Messages.TASK_ID_PROMPT);
            String input = scanner.nextLine().trim();

            try {
                int parsedId = Integer.parseInt(input);

                taskIdToUpdate = tasks.keySet().stream()
                        .filter(taskId -> taskId.equals(parsedId))
                        .findFirst()
                        .orElse(null);

                if (taskIdToUpdate == null) {
                    System.out.println(Messages.INVALID_INPUT_TASK_ID);
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
                System.out.println(Messages.INVALID_INPUT_UPDATE_TASK_STATUS);
            }
        }

        return input;
    }
}