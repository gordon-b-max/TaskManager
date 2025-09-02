package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.persistence.UpdateFile;
import main.java.com.taskmanager.util.Constants;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DeleteTask {

    private static final Logger LOGGER = Logger.getLogger(DeleteTask.class.getName());


    public static void DeleteTaskForm(TaskCollection tasks, Scanner scanner) {

        boolean openDeleteTaskForm = true;

        System.out.println(Constants.TASK_ID_VIEW);


        while (openDeleteTaskForm) {

            Task taskToDelete = promptTaskId(tasks, scanner);
            System.out.println(Constants.TASK_FOUND + taskToDelete);

            handleDeleteTask(tasks, taskToDelete, scanner);

            System.out.println(Constants.RETURN_TO_MAIN_MENU);
            openDeleteTaskForm = false;
        }
    }


    private static Task promptTaskId(TaskCollection tasks, Scanner scanner) {

        Task taskToDelete = null;

        while (taskToDelete == null) {
            System.out.print(Constants.TASK_ID_PROMPT);
            String input = scanner.nextLine().trim();

            try {
                int parsedId = Integer.parseInt(input);

                taskToDelete = tasks.getTask(parsedId);

                if (taskToDelete == null) {
                    System.out.println(Constants.INVALID_INPUT_TASK_ID);
                }

            } catch (Exception e) {
                LOGGER.log(Level.WARNING,  Constants.INVALID_INPUT_WARNING_TASK_ID, e);
            }
        }

        return taskToDelete;
    }


    private static void handleDeleteTask(TaskCollection tasks, Task taskToDelete, Scanner scanner) {

        String inputDeleteTask = promptDeleteTask(scanner);

        if (Constants.INPUT_1.equals(inputDeleteTask)) {

            tasks.deleteTask(taskToDelete.getId());
            System.out.println(tasks);

            boolean isTasksSaved = UpdateFile.saveTasks(tasks);

            if (!isTasksSaved) {
                tasks.addTask(taskToDelete);
                LOGGER.log(Level.INFO, Constants.DELETE_TASK_FAILURE);
                return;
            }

            LOGGER.log(Level.INFO, Constants.DELETE_TASK_SUCCESS);
            return;
        }

        LOGGER.log(Level.INFO, Constants.DELETE_TASK_CANCEL);
    }


    private static String promptDeleteTask(Scanner scanner) {

        System.out.println(Constants.DELETE_TASK_CONFIRM);
        System.out.println(Constants.TASK_CHANGE_CONFIRM);

        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!Constants.INPUT_1.equals(input) && !Constants.INPUT_2.equals(input)) {
                System.out.println(Constants.INVALID_INPUT_TASK_ID);
            }
        }

        return input;
    }
}