package main.java.com.taskmanager;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.persistence.FileHandler;
import main.java.com.taskmanager.service.DeleteTask;
import main.java.com.taskmanager.service.ListTasks;
import main.java.com.taskmanager.service.AddTask;
import main.java.com.taskmanager.service.UpdateTaskStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Menu {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        boolean openMainMenu = true;

        Map<Integer, Task> tasks = FileHandler.loadTasks();
        if (tasks == null) {
            System.out.println("Do you want to create a new file? Press '1' to confirm or '2' to close application");
            String inputCreateNewFile = promptCreateNewFile();

            if ("1".equals(inputCreateNewFile)) {
                tasks = new HashMap<>();

                FileHandler.createNewFile();
                System.out.println("\nSuccessfully created new file to store tasks!" +
                        " Opening Task Manager main menu...\n");

            }
            if ("2".equals(inputCreateNewFile)) {
                System.out.println("\nCanceling file creation. Closing Task Manager application...");
                System.exit(0);
            }
        }

        System.out.println("\nHello SAS! Welcome to the Task Manager Menu");

        while (openMainMenu) {
            showMenu();

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> ListTasks.ListOptions(tasks, scanner);
                case "2" -> AddTask.AddTaskForm(tasks, scanner);
                case "3" -> UpdateTaskStatus.UpdateTaskStatusForm(tasks, scanner);
                case "4" -> DeleteTask.DeleteTaskForm(tasks, scanner);
                case "5" -> openMainMenu = false;
                default -> System.out.println("Invalid action. Please enter an action numerically 1-5");
            }
        }
    }


    private static void showMenu() {
        System.out.print("""
                
                ==== Task Manager Main Menu ====
                Please select one of the following actions numerically on the menu below (1-5):
                1. List Tasks
                2. Add Task
                3. Update Task Status
                4. Delete Task
                5. Exit Menu
                """
        );
    }


    private static String promptCreateNewFile() {
        String input = "";

        while (input.isEmpty()) {

            input = Menu.scanner.nextLine().trim();

            if (!"1".equals(input) && !"2".equals(input)) {
                System.out.println("Invalid input, please enter '1' to create a new file or '2' to cancel");
            }
        }

        return input;
    }
}