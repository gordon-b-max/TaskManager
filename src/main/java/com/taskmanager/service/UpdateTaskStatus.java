package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.model.TaskStatus;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.util.Messages;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UpdateTaskStatus {

    private static final Logger LOGGER = Logger.getLogger(UpdateTaskStatus.class.getName());


    public static void UpdateTaskStatusForm(TaskCollection tasks, Scanner scanner) {

        boolean openUpdateTaskStatusForm = true;

        System.out.println(Messages.TASK_ID_VIEW);


        while (openUpdateTaskStatusForm) {

            Task taskToUpdate = promptTaskId(tasks, scanner);

            System.out.println(Messages.TASK_FOUND + taskToUpdate);

            System.out.println(Messages.UPDATE_TASK_STATUS_CURRENT + taskToUpdate.getStatus());

            handleChangeStatus(taskToUpdate, tasks, scanner);

            System.out.println(Messages.RETURN_TO_MAIN_MENU);
            openUpdateTaskStatusForm = false;
        }
    }


    private static Task promptTaskId(TaskCollection tasks, Scanner scanner) {

        Task taskToUpdate = null;

        while (taskToUpdate == null) {
            System.out.print(Messages.TASK_ID_PROMPT);
            String input = scanner.nextLine().trim();

            try {
                int parsedId = Integer.parseInt(input);

                taskToUpdate = tasks.getTask(parsedId);

                if (taskToUpdate == null) {
                    System.out.println(Messages.INVALID_INPUT_TASK_ID);
                }

            } catch (Exception e) {
                LOGGER.log(Level.WARNING,  Messages.INVALID_INPUT_WARNING_TASK_ID, e);
            }
        }

        return taskToUpdate;
    }


    private static void handleChangeStatus(Task taskToUpdate, TaskCollection tasks, Scanner scanner) {

        TaskStatus currentTaskStatus = taskToUpdate.getStatus();
        TaskStatus updatedTaskStatus = TaskStatus.PENDING.equals(currentTaskStatus) ?
                TaskStatus.COMPLETED : TaskStatus.PENDING;

        String inputStatusUpdate = promptChangeStatus(updatedTaskStatus, scanner);


        if (Messages.INPUT_1.equals(inputStatusUpdate)) {

            taskToUpdate.setStatus(updatedTaskStatus);

            boolean isTasksSaved = FileHandler.saveTasks(tasks);

            if (!isTasksSaved) {
                taskToUpdate.setStatus(currentTaskStatus);
                LOGGER.log(Level.SEVERE, Messages.UPDATE_TASK_STATUS_FAILURE);
                return;
            }

            LOGGER.log(Level.INFO, String.format(Messages.UPDATE_TASK_STATUS_SUCCESS, updatedTaskStatus));
            return;
        }

        LOGGER.log(Level.INFO, Messages.UPDATE_TASK_STATUS_CANCEL);
    }


    private static String promptChangeStatus(TaskStatus updatedTaskStatus, Scanner scanner) {

        System.out.println(Messages.UPDATE_TASK_STATUS_CONFIRM + updatedTaskStatus);
        System.out.println(Messages.TASK_CHANGE_CONFIRM);

        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!Messages.INPUT_1.equals(input) && !Messages.INPUT_2.equals(input)) {
                System.out.println(Messages.INVALID_INPUT_TASK_ID);
            }
        }

        return input;
    }
}