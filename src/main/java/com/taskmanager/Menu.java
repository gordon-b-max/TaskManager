package main.java.com.taskmanager;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.service.DeleteTask;
import main.java.com.taskmanager.service.ListTasks;
import main.java.com.taskmanager.service.AddTask;
import main.java.com.taskmanager.service.UpdateTaskStatus;
import main.java.com.taskmanager.util.Messages;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Menu {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        boolean openMainMenu = true;

        Map<Integer, Task> tasks = FileHandler.loadTasks();
        if (tasks == null) {

            System.out.println(Messages.CREATE_NEW_FILE_PROMPT);
            System.out.println(Messages.TASK_CHANGE_CONFIRM);
            String inputCreateNewFile = promptCreateNewFile();

            if ("1".equals(inputCreateNewFile)) {
                tasks = new HashMap<>();

                FileHandler.createNewFile();
                System.out.println(Messages.CREATE_NEW_FILE_SUCCESS);

            }
            if ("2".equals(inputCreateNewFile)) {
                System.out.println(Messages.CREATE_NEW_FILE_CANCEL);
                System.exit(0);
            }
        }

        System.out.println(Messages.MENU_WELCOME);

        while (openMainMenu) {
            System.out.println(Messages.MENU_OPTIONS);

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> ListTasks.ListOptions(tasks, scanner);
                case "2" -> AddTask.AddTaskForm(tasks, scanner);
                case "3" -> UpdateTaskStatus.UpdateTaskStatusForm(tasks, scanner);
                case "4" -> DeleteTask.DeleteTaskForm(tasks, scanner);
                case "5" -> openMainMenu = false;
                default -> System.out.println(Messages.INVALID_INPUT_MENU);
            }
        }
    }


    private static String promptCreateNewFile() {
        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!"1".equals(input) && !"2".equals(input)) {
                System.out.println(Messages.INVALID_INPUT_CREATE_NEW_FILE);
            }
        }

        return input;
    }
}