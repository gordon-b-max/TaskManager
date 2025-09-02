package test.java.com.taskmanager.model;

import main.java.com.taskmanager.model.Task;
import main.java.com.taskmanager.model.TaskCollection;
import main.java.com.taskmanager.model.TaskStatus;

import java.time.LocalDate;
import java.util.List;

public class TaskCollectionTests {

    public static void main(String[] args) {
        // Setup
        String testSaveTasksFileName = "src/test/resources/data/test-save-tasks.csv";
        TaskCollection testTasks = new TaskCollection(testSaveTasksFileName);

        TaskStatus pendingStatus = TaskStatus.PENDING;
        TaskStatus completedStatus = TaskStatus.COMPLETED;
        LocalDate dueDate = LocalDate.parse("2025-09-01");

        Task newTask1 = new Task(1, "New Task 1", "Testing addTask",
                pendingStatus, dueDate);
        Task newTask2 = new Task(2, "New Task 2", "Testing deleteTask",
                completedStatus, dueDate);
        System.out.println("INFO: setup complete. Created two new tasks");
        System.out.println("\n=== Running Service Tests ===");


        // Test: addTask adds new tasks to TaskCollection
        testTasks.addTask(newTask1);
        testTasks.addTask(newTask2);
        List<Task> listAllTasks = testTasks.listAllTasks();
        if (!listAllTasks.isEmpty()) {
            System.out.println("PASS: addTask successfully added new tasks to TaskCollection");
        } else {
            System.out.println("FAIL: addTask failed adding new tasks to TaskCollection");
        }


        // Test: list pending and completed tasks return associated values
        List<Task> pendingTasks = testTasks.listPendingTasks();
        List<Task> completedTasks = testTasks.listCompletedTasks();
        if (pendingTasks.size() == 1 && completedTasks.size() == 1) {
            System.out.println("PASS: list pending and completed tasks successfully filtered by TaskStatus");
        } else {
            System.out.println("FAIL: list pending and completed tasks failed to filter by TaskStatus");
        }


        // Test: setStatus changes newTask1 status to COMPLETED
        newTask1.setStatus(TaskStatus.COMPLETED);
        if (TaskStatus.COMPLETED.equals(newTask1.getStatus())) {
            System.out.println("PASS: setStatus successfully updated newTask1 status to COMPLETED");
        } else {
            System.out.println("FAIL: setStatus failed to update newTask1 status to COMPLETED");
        }


        // Test: delete tasks from TaskCollection removes tasks from local memory
        testTasks.deleteTask(newTask1.getId());
        testTasks.deleteTask(newTask2.getId());
        if (testTasks.listAllTasks().isEmpty()) {
            System.out.println("PASS: deleteTask successfully deleted newTask1 and newTask2 from testTasks");
        } else {
            System.out.println("FAIL: deleteTask failed to delete newTask1 and newTask2 from testTasks");
        }
    }
}