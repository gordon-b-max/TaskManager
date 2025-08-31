package main.java.com.taskmanager;

import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.service.DeleteTask;
import main.java.com.taskmanager.service.ListTasks;
import main.java.com.taskmanager.service.AddTask;
import main.java.com.taskmanager.service.UpdateTaskStatus;
import main.java.com.taskmanager.util.Messages;
import main.java.com.taskmanager.util.LoadStatus;

import java.util.Scanner;


public class Menu {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        boolean openMainMenu = true;

        TaskCollection tasks = new TaskCollection();

        if (tasks.getLoadStatus() != LoadStatus.SUCCESS) {
            handleCreateNewFile();
        }


        System.out.println(Messages.MENU_WELCOME);

        while (openMainMenu) {

            System.out.println(Messages.MENU_OPTIONS);

            String input = scanner.nextLine().trim();

            switch (input) {

                case Messages.INPUT_1 -> ListTasks.ListOptions(tasks, scanner);

                case Messages.INPUT_2 -> AddTask.AddTaskForm(tasks, scanner);

                case Messages.INPUT_3 -> UpdateTaskStatus.UpdateTaskStatusForm(tasks, scanner);

                case Messages.INPUT_4 -> DeleteTask.DeleteTaskForm(tasks, scanner);

                case Messages.INPUT_5 -> openMainMenu = false;

                default -> System.out.println(Messages.INVALID_INPUT_MENU);
            }
        }
    }


    private static void handleCreateNewFile() {

        System.out.println(Messages.CREATE_NEW_FILE_PROMPT);
        System.out.println(Messages.TASK_CHANGE_CONFIRM);

        String inputCreateNewFile = promptCreateNewFile();

        if (Messages.INPUT_1.equals(inputCreateNewFile)) {
            FileHandler.createNewFile();
        }

        if (Messages.INPUT_2.equals(inputCreateNewFile)) {
            System.out.println(Messages.CREATE_NEW_FILE_CANCEL);
            System.exit(0);
        }
    }


    private static String promptCreateNewFile() {

        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!Messages.INPUT_1.equals(input) && !Messages.INPUT_2.equals(input)) {
                System.out.println(Messages.INVALID_INPUT_CREATE_NEW_FILE);
            }
        }

        return input;
    }
}