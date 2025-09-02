package test.java.com.taskmanager.persistence;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.persistence.LoadFile;

import java.io.File;
import java.util.List;

public class LoadFileTests {

    public static void main(String[] args) {
        // Setup
        String testCreateNewFileName = "src/test/resources/data/test-create-file.csv";
        String testValidTasksFileName = "src/test/resources/data/test-valid-tasks.csv";
        String testInvalidTasksFileName = "src/test/resources/data/test-invalid-tasks.csv";
        System.out.println("=== Running Persistence LoadFileTests ===");


        // Test: createNewFile(testCreateNewFileName) creates new CSV file with headers
        File newFile = new File(testCreateNewFileName);
        LoadFile.createNewFile(testCreateNewFileName);
        if (newFile.exists()) {
            System.out.printf("PASS: createNewFile(%s) created new file\n", testCreateNewFileName);
        } else {
            System.out.printf("FAIL: createNewFile(%s) did not create new file\n", testCreateNewFileName);
        }


        // Test: load valid tasks from CSV should return Task in TaskCollection
        TaskCollection validTasks = new TaskCollection(testValidTasksFileName);
        List<Task> validTaskList = validTasks.listAllTasks();
        if (!validTaskList.isEmpty()) {
            System.out.println("PASS: processTask(valid) correctly returned Task in TaskCollection");
        } else {
            System.out.println("FAIL: processTask(valid) incorrectly returned empty TaskCollection");
        }


        // Test: load valid tasks from CSV should return Task in TaskCollection
        TaskCollection invalidTasks = new TaskCollection(testInvalidTasksFileName);
        List<Task> invalidTaskList = invalidTasks.listAllTasks();
        if (invalidTaskList.isEmpty()) {
            System.out.println("PASS: processTask(invalid) correctly returned empty TaskCollection");
        } else {
            System.out.println("FAIL: processTask(invalid) incorrectly returned Task in TaskCollection");
        }


        // Cleanup: delete created CSV file from createNewFile
        if (newFile.exists()) {
            boolean isNewFileDeleted = newFile.delete();
            if (!isNewFileDeleted) {
                System.out.println("WARNING: Could not delete test file: " + testCreateNewFileName);
            }
        }
    }
}