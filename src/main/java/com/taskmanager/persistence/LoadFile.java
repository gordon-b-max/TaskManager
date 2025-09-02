package main.java.com.taskmanager.persistence;

import java.io.BufferedReader;
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
import main.java.com.taskmanager.util.Constants;
import main.java.com.taskmanager.util.LoadStatus;


public class LoadFile {

    private static final Logger LOGGER = Logger.getLogger(LoadFile.class.getName());


    public static LoadStatus loadTasks(TaskCollection tasks, String fileName) {

        boolean isHeaders = true;

        File file = new File(fileName);

        if (!file.exists()) {
            LOGGER.log(Level.WARNING, Constants.FILE_HANDLER_FILE_NOT_FOUND + fileName);
            return LoadStatus.FILE_NOT_FOUND;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {

                if (isHeaders) {
                    isHeaders = false;
                    continue;
                }

                Task processedTask = processTask(line, fileName);

                if (processedTask == null) {
                    continue;
                }

                tasks.addTask(processedTask);
            }

            return LoadStatus.SUCCESS;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, String.format(Constants.FILE_HANDLER_ERROR_LOAD_FILE, fileName), e);
            return LoadStatus.LOAD_ERROR;
        }
    }


    private static Task processTask(String taskLine, String fileName) {

        String[] taskSplit = taskLine.split(",");
        List<String> taskFields = new ArrayList<>(Arrays.asList(taskSplit));
        int requiredTaskFields = 5;

        if (taskFields.size() != requiredTaskFields) {
            LOGGER.log(Level.WARNING, String.format(Constants.FILE_HANDLER_WARNING_INCORRECT_FIELDS,
                    requiredTaskFields, taskFields.size(), taskLine, fileName));
            return null;
        }

        try {

            int taskId;
            try {
                taskId = Integer.parseInt(taskFields.getFirst());
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, String.format(Constants.FILE_HANDLER_WARNING_PROCESS_TASK_INTEGER,
                        taskFields.getFirst(), fileName));
                return null;
            }

            String taskTitle = taskFields.get(1);
            String taskDescription = taskFields.get(2);

            TaskStatus taskStatus;
            try {
                taskStatus = TaskStatus.valueOf(taskFields.get(3).toUpperCase());
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.WARNING, String.format(Constants.FILE_HANDLER_WARNING_PROCESS_TASK_STATUS,
                        taskLine, fileName));
                return null;
            }

            LocalDate taskDueDate;
            try {
                taskDueDate = LocalDate.parse(taskFields.get(4));
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, String.format(Constants.FILE_HANDLER_WARNING_PROCESS_TASK_DATE,
                        taskLine, fileName));
                return null;
            }

            return new Task(taskId, taskTitle, taskDescription, taskStatus, taskDueDate);

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, Constants.FILE_HANDLER_WARNING_PROCESS_TASK_UNEXPECTED + taskLine, e);
            return null;
        }
    }


    public static void createNewFile(String fileName) {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write(Constants.FILE_HEADERS);

            LOGGER.log(Level.INFO, String.format(Constants.CREATE_NEW_FILE_SUCCESS, fileName));

        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, String.format(Constants.FILE_HANDLER_ERROR_CREATE_NEW_FILE,
                    fileName), e);
            System.exit(0);
        }
    }
}