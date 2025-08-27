package main.java.com.taskmanager.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.taskmanager.model.Task;


public class FileHandler {

    private static final String FILE_NAME = "src/main/resources/data/tasks.csv";
    private static final String HEADERS = "id,title,description,status,dueDate\n";


    public static Map<Integer, Task> loadTasks() {

        Map<Integer, Task> tasks = new HashMap<>();
        boolean isHeaders = true;

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("\nFile does not exist in folder: " + FILE_NAME);
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {

                if (isHeaders) {
                    isHeaders = false;
                    continue;
                }

                Task processedTask = processTask(line);

                if (processedTask != null) {
                    Task overwrittenTask = tasks.put(processedTask.getId(), processedTask);

                    if (overwrittenTask != null) {
                        System.out.println("\nWarning, the following task has a duplicated 'id' field and was" +
                                " overwritten locally:");
                        System.out.println(overwrittenTask);
                        System.out.println("Please check and update the CSV file located in the '" + FILE_NAME +
                                "' directory before proceeding to avoid losing task data");
                    }
                }
            }
            return tasks;

        } catch (IOException e) {
            System.out.println("Error loading tasks file: " + e.getMessage());
            return null;
        }
    }


    private static Task processTask(String taskLine) {

        String[] taskSplit = taskLine.split(",");
        List<String> taskFields = new ArrayList<>(Arrays.asList(taskSplit));

        if (taskFields.size() != 5) {
            System.out.println("\nWarning, the following task has missing or malformed data and was not processed:");
            System.out.println("{ " + taskLine + " }");
            System.out.println("Please check and update the CSV file located in the '" + FILE_NAME +
                    "' directory before proceeding to avoid permanently losing task data");

            return null;
        }

        try {
            int taskId = Integer.parseInt(taskFields.get(0));
            String taskTitle = taskFields.get(1);
            String taskDescription = taskFields.get(2);
            String taskStatus = taskFields.get(3);
            LocalDate taskDueDate = LocalDate.parse(taskFields.get(4));

            return new Task(taskId, taskTitle, taskDescription, taskStatus, taskDueDate);

        } catch (Exception e) {
            System.out.println("\nWarning, the following task has missing or malformed data and was not processed:");
            System.out.println("{ " + taskLine + " } \n with error message: " + e.getMessage());
            System.out.println("Please check and update the CSV file located in the '" + FILE_NAME +
                    "' directory before proceeding to avoid permanently losing task data");

            return null;
        }
    }


    public static void saveNewTask(Map<Integer, Task> tasks, Task newTask) {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {

            tasks.put(newTask.getId(), newTask);

            fileWriter.write(
                    newTask.getId() + "," +
                    newTask.getTitle() + "," +
                    newTask.getDescription() + "," +
                    newTask.getStatus() + "," +
                    newTask.getDueDate() + "\n");

            System.out.println("\nSuccessfully added a new task with id: " + newTask.getId());
            System.out.println("Returning to the Task Manager Main Menu...");

        } catch(IOException e) {
            System.out.println("Error saving new task to '" + FILE_NAME + "' directory with error: " + e.getMessage());
        }
    }


    public static void saveTasks(Map<Integer, Task> tasks) {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {

            fileWriter.write(HEADERS);

            for (Task task : tasks.values()) {
                fileWriter.write(
                    task.getId() + "," +
                        task.getTitle() + "," +
                        task.getDescription() + "," +
                        task.getStatus() + "," +
                        task.getDueDate() + "\n");
            }

            FileHandler.loadTasks();

        } catch(IOException e) {
            System.out.println("Error saving tasks to '" + FILE_NAME + "' directory with error: " + e.getMessage());
        }
    }


    public static void createNewFile() {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {

            fileWriter.write(HEADERS);

        } catch(IOException e) {
            System.out.println("Error creating new file in '" + FILE_NAME + "' directory " +
                    "with error message: " + e.getMessage());
        }
    }
}