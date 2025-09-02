package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskStatus;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.persistence.UpdateFile;
import main.java.com.taskmanager.util.Constants;

import java.time.LocalDate;
import java.util.Scanner;


public class AddTask {


    public static void AddTaskForm(TaskCollection tasks, Scanner scanner) {

        boolean openAddTaskForm = true;

        System.out.println(Constants.ADD_TASK_VIEW);


        while (openAddTaskForm) {

            int taskId = tasks.getNewId();

            String taskTitle = promptTitleOrDescription(Constants.ADD_TASK_TITLE, scanner);

            String taskDescription = promptTitleOrDescription(Constants.ADD_TASK_DESCRIPTION, scanner);

            TaskStatus taskStatus = promptStatus(scanner);

            LocalDate taskDueDate = promptDueDate(scanner);

            Task newTask = new Task(taskId, taskTitle, taskDescription, taskStatus, taskDueDate);


            boolean isNewTaskSaved = UpdateFile.saveNewTask(newTask, Constants.FILE_NAME);
            if (isNewTaskSaved) {
                tasks.addTask(newTask);
            }

            System.out.println(Constants.RETURN_TO_MAIN_MENU);
            openAddTaskForm = false;
        }
    }


    private static String promptTitleOrDescription(String message, Scanner scanner) {
        String input = "";

        while (input.isEmpty()) {
            System.out.print(message);
            input = scanner.nextLine().trim();

            if (input.contains(",")) {
                System.out.println(Constants.INVALID_INPUT_ADD_TASK_TITLE_OR_DESCRIPTION);
                input = "";
            }
        }

        return input;
    }


    private static TaskStatus promptStatus(Scanner scanner) {
        TaskStatus status = null;

        while (status == null) {
            System.out.print(Constants.ADD_TASK_STATUS);
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                status = TaskStatus.valueOf(input);

            } catch (IllegalArgumentException e) {
                System.out.println(Constants.INVALID_INPUT_ADD_TASK_STATUS);
            }
        }

        return status;
    }


    private static LocalDate promptDueDate(Scanner scanner) {
        LocalDate dueDate = null;

        while (dueDate == null) {
            System.out.print(Constants.ADD_TASK_DUE_DATE);
            String input = scanner.nextLine().trim();

            try {
                dueDate = LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println(Constants.INVALID_INPUT_ADD_TASK_DUE_DATE);
            }
        }

        return dueDate;
    }
}