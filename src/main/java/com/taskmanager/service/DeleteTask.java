package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.util.Messages;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DeleteTask {

    private static final Logger LOGGER = Logger.getLogger(DeleteTask.class.getName());


    public static void DeleteTaskForm(TaskCollection tasks, Scanner scanner) {

        boolean openDeleteTaskForm = true;

        System.out.println(Messages.TASK_ID_VIEW);


        while (openDeleteTaskForm) {

            Task taskToDelete = promptTaskId(tasks, scanner);
            System.out.println(Messages.TASK_FOUND + taskToDelete);

            handleDeleteTask(tasks, taskToDelete, scanner);

            System.out.println(Messages.RETURN_TO_MAIN_MENU);
            openDeleteTaskForm = false;
        }
    }


    private static Task promptTaskId(TaskCollection tasks, Scanner scanner) {

        Task taskToDelete = null;

        while (taskToDelete == null) {
            System.out.print(Messages.TASK_ID_PROMPT);
            String input = scanner.nextLine().trim();

            try {
                int parsedId = Integer.parseInt(input);

                taskToDelete = tasks.getTask(parsedId);

                if (taskToDelete == null) {
                    System.out.println(Messages.INVALID_INPUT_TASK_ID);
                }

            } catch (Exception e) {
                LOGGER.log(Level.WARNING,  Messages.INVALID_INPUT_WARNING_TASK_ID, e);
            }
        }

        return taskToDelete;
    }


    private static void handleDeleteTask(TaskCollection tasks, Task taskToDelete, Scanner scanner) {

        String inputDeleteTask = promptDeleteTask(scanner);

        if (Messages.INPUT_1.equals(inputDeleteTask)) {

            tasks.deleteTask(taskToDelete.getId());
            System.out.println(tasks);

            boolean isTasksSaved = FileHandler.saveTasks(tasks);

            if (!isTasksSaved) {
                tasks.addTask(taskToDelete);
                LOGGER.log(Level.INFO, Messages.DELETE_TASK_FAILURE);
                return;
            }

            LOGGER.log(Level.INFO, Messages.DELETE_TASK_SUCCESS);
            return;
        }

        LOGGER.log(Level.INFO, Messages.DELETE_TASK_CANCEL);
    }


    private static String promptDeleteTask(Scanner scanner) {

        System.out.println(Messages.DELETE_TASK_CONFIRM);
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
