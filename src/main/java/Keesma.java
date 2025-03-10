import java.util.Scanner;
import java.util.List;

/**
 * Main class for the Keesma task management application.
 * Coordinates the interactions between various components like
 * Parser, TaskList, UI, and Storage.
 */
public class Keesma {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Keesma application with the given file path for storage.
     * Initializes UI, Storage, Parser, and TaskList components.
     *
     * @param filePath The file path for storage of tasks
     */
    public Keesma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KeesmaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Keesma application.
     * Displays welcome message, processes user commands in a loop
     * until exit command is given, and handles any exceptions.
     */
    public void run() {
        ui.sayHello();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                ui.promptCommand();
                String userInput = scanner.nextLine().trim();

                // Parse the user input into command and arguments
                String[] parsedInput = parser.parseInput(userInput);
                String command = parsedInput[0];
                String arguments = parsedInput[1];

                switch (command) {
                case "list":
                    ui.printTaskList(tasks.getTaskList());
                    break;

                case "todo":
                    // Validate the todo description
                    parser.validateTodoDescription(arguments);
                    Task newTask = tasks.addTodo(arguments);
                    ui.printTaskAdded(newTask, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;

                case "deadline":
                    // Parse deadline arguments
                    String[] deadlineParts = parser.parseDeadline(arguments);
                    String description = deadlineParts[0];
                    String by = deadlineParts[1];

                    // Create the deadline
                    Task newDeadline = tasks.addDeadline(description, by);
                    ui.printTaskAdded(newDeadline, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;

                case "event":
                    // Parse event arguments
                    String[] eventParts = parser.parseEvent(arguments);
                    String eventDescription = eventParts[0];
                    String from = eventParts[1];
                    String to = eventParts[2];

                    // Create the event
                    Task newEvent = tasks.addEvent(eventDescription, from, to);
                    ui.printTaskAdded(newEvent, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;

                case "mark":
                    // Parse and validate the task number
                    int markTaskId = parser.parseNumber(arguments);
                    parser.validateTaskIndex(markTaskId, tasks.size());

                    // Mark the task
                    Task markedTask = tasks.markTaskAsDone(markTaskId);
                    ui.printTaskMarked(markedTask);
                    storage.saveTasks(tasks.getTaskList());
                    break;

                case "unmark":
                    // Parse and validate the task number
                    int unmarkTaskId = parser.parseNumber(arguments);
                    parser.validateTaskIndex(unmarkTaskId, tasks.size());

                    // Unmark the task
                    Task unmarkedTask = tasks.markTaskAsNotDone(unmarkTaskId);
                    ui.printTaskUnmarked(unmarkedTask);
                    storage.saveTasks(tasks.getTaskList());
                    break;

                case "delete":
                    // Parse and validate the task number
                    int deleteTaskId = parser.parseNumber(arguments);
                    parser.validateTaskIndex(deleteTaskId, tasks.size());

                    // Delete the task
                    Task deletedTask = tasks.deleteTask(deleteTaskId);
                    ui.printTaskDeleted(deletedTask, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;

                case "find":
                    if (arguments.isBlank()) {
                        throw new KeesmaException("Bruh what you want me to find? Give keyword lah.");
                    }
                    List<Task> foundTasks = tasks.findTasks(arguments);
                    ui.printFoundTasks(foundTasks, arguments);
                    break;

                case "bye":
                    isRunning = false;
                    ui.sayBye();
                    break;

                default:
                    ui.handleBadCommand();
                }
            } catch (KeesmaException e) {
                ui.printError(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Main entry point for the application.
     * Creates and runs a new Keesma instance with the default storage path.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        new Keesma("data/tasks.txt").run();
    }
}