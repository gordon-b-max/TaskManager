package main.java.com.taskmanager.util;


public class Messages {

    // Menu View
    public static final String MENU_WELCOME = "\nHello SAS! Welcome to the Task Manager Menu";

    public static final String MENU_OPTIONS = """
       
            ========== Task Manager Main Menu ==========
            Please select one of the following actions numerically on the menu below (1-5):
            1. List Tasks
            2. Add Task
            3. Update Task Status
            4. Delete Task
            5. Exit Menu""";

    // Create new file
    public static final String CREATE_NEW_FILE_PROMPT = "Do you want to create a new file?";

    public static final String CREATE_NEW_FILE_SUCCESS = "Successfully created new file to store tasks at: %s" +
            "\nOpening Task Manager main menu...";

    public static final String CREATE_NEW_FILE_CANCEL = "Canceling file creation. Closing Task Manager " +
            "application...";


    // Invalid Inputs
    public static final String INVALID_INPUT_MENU = "Invalid action. Please enter an action numerically 1-5";

    public static final String INVALID_INPUT_LIST_OPTIONS = "Invalid action. Please enter an action numerically 1-4";

    public static final String INVALID_INPUT_CREATE_NEW_FILE = "Invalid action, please enter '1' to create a " +
            "new file or '2' to cancel";

    public static final String INVALID_INPUT_ADD_TASK_TITLE_OR_DESCRIPTION = "Invalid title or description, " +
            "please remove commas and try again";

    public static final String INVALID_INPUT_ADD_TASK_STATUS = "Invalid status, please enter status as: " +
            "PENDING or COMPLETED";

    public static final String INVALID_INPUT_ADD_TASK_DUE_DATE = "Invalid date, please use date format: YYYY-MM-DD";

    public static final String INVALID_INPUT_TASK_ID = "Unable to find task 'id'. Please double check task 'id' " +
            "and try again...";

    public static final String INVALID_INPUT_WARNING_TASK_ID = "Unable to process task 'id' with error message: ";


    // List Options View
    public static final String LIST_TASKS_VIEW = """
       
            Please select which tasks to display on the menu below: (1-4)
            1. List All Tasks
            2. List Completed Tasks
            3. List Pending Tasks
            4. Return to Main Menu""";

    public static final String LIST_TASKS_ALL = "\nAll Tasks:";

    public static final String LIST_TASKS_COMPLETED = "\nCompleted Tasks:";

    public static final String LIST_TASKS_PENDING = "\nPending Tasks:";


    // Add Task
    public static final String ADD_TASK_VIEW = "\nTo add a task, please enter the following information " +
            "with the specified format:";

    public static final String ADD_TASK_TITLE = "Enter Title: ";

    public static final String ADD_TASK_DESCRIPTION = "Enter Description: ";

    public static final String ADD_TASK_STATUS = "Enter status (PENDING/COMPLETED): ";

    public static final String ADD_TASK_DUE_DATE = "Enter due date (YYYY-MM-DD): ";

    public static final String ADD_TASK_SUCCESS = "Successfully saved new task with id: ";


    // Update Task Status
    public static final String UPDATE_TASK_STATUS_CURRENT = "\nThe current status for this task is: ";

    public static final String UPDATE_TASK_STATUS_CONFIRM = "Do you want to update the status of this task to: ";

    public static final String UPDATE_TASK_STATUS_SUCCESS = "Successfully updated task status: %s";

    public static final String UPDATE_TASK_STATUS_FAILURE = "Failed to update task status.";

    public static final String UPDATE_TASK_STATUS_CANCEL = "Canceling updating task status.";


    // Delete Task
    public static final String DELETE_TASK_CONFIRM = "\nDo you want to delete this task?";

    public static final String DELETE_TASK_SUCCESS = "Successfully deleted task";

    public static final String DELETE_TASK_FAILURE = "Failed to delete task";

    public static final String DELETE_TASK_CANCEL = "Canceling task deletion.";


    // FileHandler
    public static final String FILE_HANDLER_FILE_NOT_FOUND = "File does not exist in folder: ";

    public static final String FILE_HANDLER_ERROR_LOAD_FILE = """
            Error loading tasks file from: %s
            with error message:""";

    public static final String FILE_HANDLER_ERROR_SAVE_NEW_TASK = """
            Error saving new task to: %s
            with error message:""";

    public static final String FILE_HANDLER_ERROR_SAVE_TASKS = "Error saving tasks to: ";

    public static final String FILE_HANDLER_ERROR_CREATE_NEW_FILE = """
            Error creating new file at: %s
            with error message:""";

    public static final String FILE_HANDLER_WARNING_INCORRECT_FIELDS = """
            Skipping task due to incorrect number of fields (expected %d, got %d):
            {%s}
            Please update the task in %s file before proceeding. Once updated,
            close and reopen the application to avoid permanently losing task data.""";

    public static final String FILE_HANDLER_WARNING_PROCESS_TASK_UNEXPECTED = "Unexpected error processing task: \n";

    public static final String FILE_HANDLER_WARNING_PROCESS_TASK_INTEGER = """
            Skipping task due to invalid id:
            %s
            Please update the `id` in %s before proceeding. Once updated,
            close and reopen the application to avoid permanently losing task data.""";

    public static final String FILE_HANDLER_WARNING_PROCESS_TASK_STATUS = """
            Skipping task due to invalid status (must be PENDING or COMPLETED):
            %s
            Please update the `status` in %s before proceeding. Once updated,
            close and reopen the application to avoid permanently losing task data.""";

    public static final String FILE_HANDLER_WARNING_PROCESS_TASK_DATE = """
            Skipping task due to invalid date:
            %s
            Please update the `dueDate` in %s before proceeding. Once updated,
            close and reopen the application to avoid permanently losing task data.""";

    public static final String FILE_HANDLER_WARNING_DUPLICATED_TASK = """
            The following task has a duplicated 'id' field and was overwritten locally:
            %s
            Please check and update the duplicated task `id` in `tasks.csv` file before proceeding. Once updated,
            close and reopen the application to avoid permanently losing task data.""";


    // Reusable Constants
    public static final String TASK_ID_VIEW = """

            Please enter the associated task 'id', which can be found by selecting 'List Tasks', then by
            selecting 'All' from the Task Manager Main Menu. Additionally, the task `id` can be found
            in the `tasks.csv` file located in the directory listed in the README.md file.""";

    public static final String INPUT_1 = "1";

    public static final String INPUT_2 = "2";

    public static final String INPUT_3 = "3";

    public static final String INPUT_4 = "4";

    public static final String INPUT_5 = "5";

    public static final String TASK_FOUND = "\nSuccessfully found task: \n";

    public static final String TASK_ID_PROMPT = "Enter ID: ";

    public static final String TASK_CHANGE_CONFIRM = "1. Yes\n2. Cancel";

    public static final String RETURN_TO_MAIN_MENU = "\nReturning to the Task Manager Main Menu...";
}