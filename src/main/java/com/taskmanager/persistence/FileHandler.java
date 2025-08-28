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
import java.util.logging.Logger;
import java.util.logging.Level;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.util.Messages;


public class FileHandler {

    public static final String FILE_NAME = "src/main/resources/data/tasks.csv";
    private static final String FILE_HEADERS = "id,title,description,status,dueDate\n";

    private static final Logger LOGGER = Logger.getLogger(FileHandler.class.getName());


    public static Map<Integer, Task> loadTasks() {

        Map<Integer, Task> tasks = new HashMap<>();
        boolean isHeaders = true;

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            LOGGER.log(Level.WARNING, Messages.FILE_HANDLER_FILE_NOT_FOUND + FILE_NAME);
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
                        LOGGER.log(Level.WARNING, Messages.FILE_HANDLER_WARNING_DUPLICATED_TASK + overwrittenTask);
                    }
                }
            }
            return tasks;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, Messages.FILE_HANDLER_ERROR_LOAD_FILE + FILE_NAME +
                    Messages.FILE_HANDLER_ERROR_MESSAGE, e);
            return null;
        }
    }


    private static Task processTask(String taskLine) {

        String[] taskSplit = taskLine.split(",");
        List<String> taskFields = new ArrayList<>(Arrays.asList(taskSplit));

        if (taskFields.size() != 5) {
            LOGGER.log(Level.WARNING, Messages.FILE_HANDLER_WARNING_PROCESS_TASK + taskLine);
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
            LOGGER.log(Level.WARNING, Messages.FILE_HANDLER_WARNING_PROCESS_TASK + taskLine +
                    Messages.FILE_HANDLER_ERROR_MESSAGE, e);
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

            LOGGER.log(Level.INFO, Messages.ADD_TASK_SUCCESS + newTask.getId());
            System.out.println(Messages.RETURN_TO_MAIN_MENU);

        } catch(IOException e) {
            LOGGER.log(Level.SEVERE,Messages.FILE_HANDLER_ERROR_SAVE_NEW_TASK + FILE_NAME +
                    Messages.FILE_HANDLER_ERROR_MESSAGE, e);
        }
    }


    public static void saveTasks(Map<Integer, Task> tasks) {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {

            fileWriter.write(FILE_HEADERS);

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
            LOGGER.log(Level.SEVERE, Messages.FILE_HANDLER_ERROR_SAVE_TASKS + FILE_NAME, e);
        }
    }


    public static void createNewFile() {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {

            fileWriter.write(FILE_HEADERS);

        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, Messages.FILE_HANDLER_ERROR_CREATE_NEW_FILE + FILE_NAME, e);
        }
    }
}