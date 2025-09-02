package main.java.com.taskmanager.persistence;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.util.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UpdateFile {

    private static final Logger LOGGER = Logger.getLogger(UpdateFile.class.getName());


    public static boolean saveNewTask(Task newTask, String fileName) {

        try (FileWriter fileWriter = new FileWriter(fileName, true)) {

            fileWriter.write(
                    newTask.getId() + "," +
                            newTask.getTitle() + "," +
                            newTask.getDescription() + "," +
                            newTask.getStatus() + "," +
                            newTask.getDueDate() + "\n");

            LOGGER.log(Level.INFO, Constants.ADD_TASK_SUCCESS + newTask.getId());
            return true;

        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, String.format(Constants.FILE_HANDLER_ERROR_SAVE_NEW_TASK,
                    fileName), e);
            return false;
        }
    }


    public static boolean saveTasks(TaskCollection tasks, String fileName) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {

            bufferedWriter.write(Constants.FILE_HEADERS);

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
            LOGGER.log(Level.SEVERE, Constants.FILE_HANDLER_ERROR_SAVE_TASKS + fileName, e);
            return false;
        }
    }
}