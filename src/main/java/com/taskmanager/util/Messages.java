package main.java.com.taskmanager.util;


public class Messages {

    // Menu View
    public static final String MENU_WELCOME = "\nHello SAS! Welcome to the Task Manager Menu";

    public static final String MENU_OPTIONS = """
       
        ==== Task Manager Main Menu ====
        Please select one of the following actions numerically on the menu below (1-5):
        1. List Tasks
        2. Add Task
        3. Update Task Status
        4. Delete Task
        5. Exit Menu""";

    // Create new file
    public static final String CREATE_NEW_FILE_PROMPT = "Do you want to create a new file?";

    public static final String CREATE_NEW_FILE_SUCCESS = "Successfully created new file to store tasks! " +
            "Opening Task Manager main menu...";

    public static final String CREATE_NEW_FILE_CANCEL = "\nCanceling file creation. Closing Task Manager " +
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

    public static final String INVALID_INPUT_WARNING_TASK_ID = "Unable to find task 'id' with error message: ";


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

    public static final String ADD_TASK_SUCCESS = "Successfully added new task with id: ";


    // Update Task Status
    public static final String UPDATE_TASK_STATUS_CURRENT = "\nThe current status for this task is: ";

    public static final String UPDATE_TASK_STATUS_CONFIRM = "Do you want to update the status of this task to: ";

    public static final String UPDATE_TASK_STATUS_SUCCESS = "\nSuccessfully updated task status!";

    public static final String UPDATE_TASK_STATUS_CANCEL = "Canceling updating task status.";


    // Delete Task
    public static final String DELETE_TASK_CONFIRM = "\nDo you want to delete this task?";

    public static final String DELETE_TASK_SUCCESS = "\nSuccessfully deleted task";

    public static final String DELETE_TASK_CANCEL = "Canceling task deletion.";


    // FileHandler
    public static final String FILE_HANDLER_FILE_NOT_FOUND = "\nFile does not exist in folder: ";

    public static final String FILE_HANDLER_ERROR_MESSAGE = "\nwith error message: ";

    public static final String FILE_HANDLER_ERROR_LOAD_FILE = "Error loading tasks file from: ";

    public static final String FILE_HANDLER_ERROR_SAVE_NEW_TASK = "Error saving new task to: ";

    public static final String FILE_HANDLER_ERROR_SAVE_TASKS = "Error saving tasks to: ";

    public static final String FILE_HANDLER_ERROR_CREATE_NEW_FILE = "Error creating new file at: ";

    public static final String FILE_HANDLER_WARNING_PROCESS_TASK = """
        The following task has missing or malformed data and was not processed. Please check and update the
        `tasks.csv` file before proceeding to avoid permanently losing task data:
        """;

    public static final String FILE_HANDLER_WARNING_DUPLICATED_TASK = """
        The following task has a duplicated 'id' field and was overwritten locally. Please check and update the
        `tasks.csv` file before proceeding to avoid permanently losing task data:
        """;


    // Reusable Constants
    public static final String TASK_ID_VIEW = """
        
        Please enter the associated task 'id' located in the 'src/main/resources/data/tasks.csv'
            directory. Additionally, the task 'id' can be located by selecting 'List Tasks', then by
            selecting either 'All', 'Completed', or 'Pending' from the Task Manager Main Menu.""";

    public static final String TASK_FOUND = "\nSuccessfully found task: \n";

    public static final String TASK_ID_PROMPT = "Enter ID: ";

    public static final String TASK_CHANGE_CONFIRM = "1. Yes\n2. Cancel";

    public static final String RETURN_TO_MAIN_MENU = "Returning to the Task Manager Main Menu...";
}