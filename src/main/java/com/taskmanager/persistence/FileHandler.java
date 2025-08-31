package main.java.com.taskmanager.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskStatus;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.util.Messages;
import main.java.com.taskmanager.util.LoadStatus;


public class FileHandler {

    private static final String FILE_NAME = "src/main/resources/data/tasks.csv";
    private static final String FILE_HEADERS = "id,title,description,status,dueDate\n";

    private static final Logger LOGGER = Logger.getLogger(FileHandler.class.getName());


    public static LoadStatus loadTasks(TaskCollection tasks) {

        boolean isHeaders = true;

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            LOGGER.log(Level.WARNING, Messages.FILE_HANDLER_FILE_NOT_FOUND + FILE_NAME);
            return LoadStatus.FILE_NOT_FOUND;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {

                if (isHeaders) {
                    isHeaders = false;
                    continue;
                }

                Task processedTask = processTask(line);

                if (processedTask == null) {
                    continue;
                }

                tasks.addTask(processedTask);
            }

            return LoadStatus.SUCCESS;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, String.format(Messages.FILE_HANDLER_ERROR_LOAD_FILE, FILE_NAME), e);
            return LoadStatus.LOAD_ERROR;
        }
    }


    private static Task processTask(String taskLine) {

        String[] taskSplit = taskLine.split(",");
        List<String> taskFields = new ArrayList<>(Arrays.asList(taskSplit));
        int requiredTaskFields = 5;

        if (taskFields.size() != requiredTaskFields) {
            LOGGER.log(Level.WARNING, String.format(Messages.FILE_HANDLER_WARNING_INCORRECT_FIELDS,
                    requiredTaskFields, taskFields.size(), taskLine, FILE_NAME));
            return null;
        }

        try {

            int taskId;
            try {
                taskId = Integer.parseInt(taskFields.getFirst());
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, String.format(Messages.FILE_HANDLER_WARNING_PROCESS_TASK_INTEGER,
                        taskFields.getFirst(), FILE_NAME));
                return null;
            }

            String taskTitle = taskFields.get(1);
            String taskDescription = taskFields.get(2);

            TaskStatus taskStatus;
            try {
                taskStatus = TaskStatus.valueOf(taskFields.get(3).toUpperCase());
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.WARNING, String.format(Messages.FILE_HANDLER_WARNING_PROCESS_TASK_STATUS,
                        taskLine, FILE_NAME));
                return null;
            }

            LocalDate taskDueDate;
            try {
                taskDueDate = LocalDate.parse(taskFields.get(4));
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, String.format(Messages.FILE_HANDLER_WARNING_PROCESS_TASK_DATE,
                        taskLine, FILE_NAME));
                return null;
            }

            return new Task(taskId, taskTitle, taskDescription, taskStatus, taskDueDate);

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, Messages.FILE_HANDLER_WARNING_PROCESS_TASK_UNEXPECTED + taskLine, e);
            return null;
        }
    }


    public static boolean saveNewTask(Task newTask) {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {

            fileWriter.write(
                    newTask.getId() + "," +
                    newTask.getTitle() + "," +
                    newTask.getDescription() + "," +
                    newTask.getStatus() + "," +
                    newTask.getDueDate() + "\n");

            LOGGER.log(Level.INFO, Messages.ADD_TASK_SUCCESS + newTask.getId());
            return true;

        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, String.format(Messages.FILE_HANDLER_ERROR_SAVE_NEW_TASK, FILE_NAME), e);
            return false;
        }
    }


    public static boolean saveTasks(TaskCollection tasks) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME))) {

            bufferedWriter.write(FILE_HEADERS);

            for (Task task : tasks.listAllTasks()) {

                bufferedWriter.write(
                    task.getId() + "," +
                        task.getTitle() + "," +
                        task.getDescription() + "," +
                        task.getStatus() + "," +
                        task.getDueDate()
                );
                bufferedWriter.newLine();
            }
            return true;

        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, Messages.FILE_HANDLER_ERROR_SAVE_TASKS + FILE_NAME, e);
            return false;
        }
    }


    public static void createNewFile() {

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {

            fileWriter.write(FILE_HEADERS);

            LOGGER.log(Level.INFO, String.format(Messages.CREATE_NEW_FILE_SUCCESS, FILE_NAME));

        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, String.format(Messages.FILE_HANDLER_ERROR_CREATE_NEW_FILE, FILE_NAME), e);
            System.exit(0);
        }
    }
}