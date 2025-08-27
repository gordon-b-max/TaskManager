package main.java.com.taskmanager.service;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.util.Messages;

import java.util.Map;
import java.util.Scanner;


public class DeleteTask {


    public static void DeleteTaskForm(Map<Integer, Task> tasks, Scanner scanner) {
        boolean openDeleteTaskForm = true;

        System.out.println(Messages.TASK_ID_VIEW);

        while (openDeleteTaskForm) {

            Task taskToDelete = promptTaskId(tasks, scanner);
            System.out.println(Messages.TASK_FOUND + taskToDelete);

            System.out.println(Messages.DELETE_TASK_CONFIRM);
            System.out.println(Messages.TASK_CHANGE_CONFIRM);

            String inputDeleteTask = promptDeleteTask(scanner);

            if ("1".equals(inputDeleteTask)) {

                tasks.remove(taskToDelete.getId());
                FileHandler.saveTasks(tasks);

                System.out.println(Messages.DELETE_TASK_SUCCESS);
                openDeleteTaskForm = false;

            }
            if ("2".equals(inputDeleteTask)) {
                System.out.println(Messages.DELETE_TASK_CANCEL);
                openDeleteTaskForm = false;
            }
        }
    }


    private static Task promptTaskId(Map<Integer, Task> tasks, Scanner scanner) {
        Integer taskIdToDelete = null;

        while (taskIdToDelete == null) {
            System.out.print(Messages.TASK_ID_PROMPT);
            String input = scanner.nextLine().trim();

            int parsedId = Integer.parseInt(input);

            taskIdToDelete = tasks.keySet().stream()
                    .filter(taskId -> taskId.equals(parsedId))
                    .findFirst()
                    .orElse(null);

            if (taskIdToDelete == null) {
                System.out.println(Messages.INVALID_INPUT_TASK_ID);
            }
        }

        return tasks.get(taskIdToDelete);
    }


    private static String promptDeleteTask(Scanner scanner) {
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
