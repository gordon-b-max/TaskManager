package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.util.Messages;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UpdateTaskStatus {

    private static final Logger LOGGER = Logger.getLogger(UpdateTaskStatus.class.getName());


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

                LOGGER.log(Level.INFO, Messages.UPDATE_TASK_STATUS_SUCCESS);
                System.out.println(Messages.RETURN_TO_MAIN_MENU);
                openUpdateTaskStatusForm = false;
 
            }
            if ("2".equals(inputStatusUpdate)) {
                LOGGER.log(Level.INFO, Messages.UPDATE_TASK_STATUS_CANCEL);
                System.out.println(Messages.RETURN_TO_MAIN_MENU);
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
                LOGGER.log(Level.WARNING,  Messages.INVALID_INPUT_WARNING_TASK_ID, e);
            }
        }

        return taskIdToUpdate;
    }


    private static String promptChangeStatus(Scanner scanner) {
        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!"1".equals(input) && !"2".equals(input)) {
                System.out.println(Messages.INVALID_INPUT_TASK_ID);
            }
        }

        return input;
    }
}