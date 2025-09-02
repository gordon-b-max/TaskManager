package main.java.com.taskmanager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.com.taskmanager.persistence.LoadFile;
import main.java.com.taskmanager.util.Constants;
import main.java.com.taskmanager.util.LoadStatus;


public class TaskCollection {

    private final LoadStatus loadStatus;
    private final Map<Integer, Task> tasks = new HashMap<>();

    private static final Logger LOGGER = Logger.getLogger(TaskCollection.class.getName());


    public TaskCollection() {
        this.loadStatus = LoadFile.loadTasks(this);
    }


    public LoadStatus getLoadStatus() {
        return loadStatus;
    }


    public List<Task> listAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Task> listCompletedTasks() {
        return tasks.values().stream()
                .filter(task -> TaskStatus.COMPLETED.equals(task.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Task> listPendingTasks() {
        return tasks.values().stream()
                .filter(task -> TaskStatus.PENDING.equals(task.getStatus()))
                .collect(Collectors.toList());
    }


    public int getNewId() {
        return tasks.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    public void addTask(Task task) {
        Task overwrittenTask = tasks.put(task.getId(), task);

        if (overwrittenTask != null) {
            LOGGER.log(Level.WARNING, String.format(Constants.FILE_HANDLER_WARNING_DUPLICATED_TASK,
                    overwrittenTask));
        }
    }


    public Task getTask(int parsedId) {
        Integer taskId = tasks.keySet().stream()
                .filter(id -> id.equals(parsedId))
                .findFirst()
                .orElse(null);

        return tasks.get(taskId);
    }


    public void deleteTask(int taskId) {
        tasks.remove(taskId);
    }
}