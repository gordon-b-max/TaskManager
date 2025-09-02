package main.java.com.taskmanager;

import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.persistence.LoadFile;
import main.java.com.taskmanager.service.DeleteTask;
import main.java.com.taskmanager.service.ListTasks;
import main.java.com.taskmanager.service.AddTask;
import main.java.com.taskmanager.service.UpdateTaskStatus;
import main.java.com.taskmanager.util.Constants;
import main.java.com.taskmanager.util.LoadStatus;

import java.util.Scanner;


public class Menu {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        boolean openMainMenu = true;

        TaskCollection tasks = new TaskCollection(Constants.FILE_NAME);

        if (tasks.getLoadStatus() != LoadStatus.SUCCESS) {
            handleCreateNewFile();
        }


        System.out.println(Constants.MENU_WELCOME);

        while (openMainMenu) {

            System.out.println(Constants.MENU_OPTIONS);

            String input = scanner.nextLine().trim();

            switch (input) {

                case Constants.INPUT_1 -> ListTasks.ListOptions(tasks, scanner);

                case Constants.INPUT_2 -> AddTask.AddTaskForm(tasks, scanner);

                case Constants.INPUT_3 -> UpdateTaskStatus.UpdateTaskStatusForm(tasks, scanner);

                case Constants.INPUT_4 -> DeleteTask.DeleteTaskForm(tasks, scanner);

                case Constants.INPUT_5 -> openMainMenu = false;

                default -> System.out.println(Constants.INVALID_INPUT_MENU);
            }
        }
    }


    private static void handleCreateNewFile() {

        System.out.println(Constants.CREATE_NEW_FILE_PROMPT);
        System.out.println(Constants.TASK_CHANGE_CONFIRM);

        String inputCreateNewFile = promptCreateNewFile();

        if (Constants.INPUT_1.equals(inputCreateNewFile)) {
            LoadFile.createNewFile(Constants.FILE_NAME);
        }

        if (Constants.INPUT_2.equals(inputCreateNewFile)) {
            System.out.println(Constants.CREATE_NEW_FILE_CANCEL);
            System.exit(0);
        }
    }


    private static String promptCreateNewFile() {

        String input = "";

        while (input.isEmpty()) {

            input = scanner.nextLine().trim();

            if (!Constants.INPUT_1.equals(input) && !Constants.INPUT_2.equals(input)) {
                System.out.println(Constants.INVALID_INPUT_CREATE_NEW_FILE);
            }
        }

        return input;
    }
}