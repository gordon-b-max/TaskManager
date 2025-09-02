package test.java.com.taskmanager.persistence;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.model.TaskStatus;
import main.java.com.taskmanager.persistence.UpdateFile;

import java.time.LocalDate;

public class UpdateFileTests {

    public static void main(String[] args) {
        // Setup
        String testSaveTasksFileName = "src/test/resources/data/test-save-tasks.csv";
        TaskCollection testTasks = new TaskCollection(testSaveTasksFileName);

        TaskStatus pendingStatus = TaskStatus.PENDING;
        LocalDate dueDate = LocalDate.parse("2025-09-01");

        Task newTask1 = new Task(1, "New Task 1", "Testing saveNewTask",
                pendingStatus, dueDate);
        Task newTask2 = new Task(2, "New Task 2", "Testing saveTasks",
                pendingStatus, dueDate);
        System.out.printf("INFO: setup complete. Task1:\n%s\n and Task2:\n%s\n", newTask1, newTask2);
        System.out.println("\n=== Running Persistence UpdateFileTests ===");


        // Test: saveNewTask should add new task to `test-save-tasks.csv`
        boolean isNewTaskSaved = UpdateFile.saveNewTask(newTask1, testSaveTasksFileName);
        if (isNewTaskSaved) {
            System.out.printf("PASS: saveNewTask successfully added new task:\n%s to: %s\n",
                    newTask1, testSaveTasksFileName);
        } else {
            System.out.printf("FAIL: saveNewTask failed to add new task:\n%s to: %s\n",
                    newTask1, testSaveTasksFileName);
        }


        // Test: saveTasks should overwrite and add new tasks to `test-save-tasks.csv`
        testTasks.addTask(newTask1);
        testTasks.addTask(newTask2);
        boolean isTasksSaved = UpdateFile.saveTasks(testTasks, testSaveTasksFileName);
        if (isTasksSaved) {
            System.out.printf("PASS: saveTasks successfully added tasks:\n%s\n%s\nto: %s\n",
                    newTask1, newTask2, testSaveTasksFileName);
        } else {
            System.out.printf("FAIL: saveTasks failed adding tasks:\n%s\n%s\nto: %s\n",
                    newTask1, newTask2, testSaveTasksFileName);
        }


        // Cleanup: Delete new tasks from CSV file
        if (!testTasks.listAllTasks().isEmpty()) {

            testTasks.deleteTask(newTask1.getId());
            testTasks.deleteTask(newTask2.getId());

            boolean isTasksDeleted = UpdateFile.saveTasks(testTasks, testSaveTasksFileName);
            if (isTasksDeleted) {
                System.out.println("INFO: cleanup complete. New tasks deleted from: " + testSaveTasksFileName);
            } else {
                System.out.println("WARNING: cleanup failed. New tasks not deleted from: " + testSaveTasksFileName);
            }
        }
    }
}
