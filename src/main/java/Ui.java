import java.util.List;

/**
 * UI class to handle user interface interactions.
 * Manages displaying information to the user and provides
 * a consistent text-based interface for the Keesma application.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Displays a welcome message to the user.
     * This is shown when the application starts.
     */
    public void sayHello() {
        System.out.println(LINE);
        System.out.println("Helloooo I'm Keesma, what can I do for you today babyboo");
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message to the user.
     * This is shown when the application exits.
     */
    public void sayBye() {
        System.out.println(LINE);
        System.out.println("Bye, Hope to see you again soon, you smell great by the way, although you did smell better last night hehe ^ W ^ ");
        System.out.println(LINE);
    }

    /**
     * Displays an error message for invalid commands.
     * This is shown when the user enters a command that isn't recognized.
     */
    public void handleBadCommand() {
        System.out.println(LINE);
        System.out.println("Eh bro can put a proper command anot smh");
        System.out.println(LINE);
    }

    /**
     * Displays the list of all tasks.
     * This formats the tasks with numbering and appropriate formatting.
     *
     * @param taskList The list of tasks to display
     */
    public void printTaskList(List<Task> taskList) {
        System.out.println(LINE);
        System.out.println("You have " + taskList.size() + " tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.getTaskType() + " " + task.toString());
        }
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after adding a task.
     * Shows the added task and the updated task count.
     *
     * @param task The task that was added
     * @param taskCount The new total number of tasks
     */
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Yes bro I have added:");
        System.out.println("\t" + task.toString());
        System.out.println("Now there are " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after deleting a task.
     * Shows the deleted task and the updated task count.
     *
     * @param task The task that was deleted
     * @param taskCount The new total number of tasks
     */
    public void printTaskDeleted(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Yes boss I have removed this task for you.");
        System.out.println("\t" + task.toString());
        System.out.println("Now there are " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after marking a task as done.
     * Shows the marked task with its updated status.
     *
     * @param task The task that was marked as done
     */
    public void printTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println("Your task has been marked as done babyboo");
        System.out.println("\t" + task.toString());
        System.out.println(LINE);
    }

    /**
     * Displays a confirmation message after unmarking a task.
     * Shows the unmarked task with its updated status.
     *
     * @param task The task that was unmarked
     */
    public void printTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println("Your task has been unmarked gugu");
        System.out.println("\t" + task.toString());
        System.out.println(LINE);
    }

    /**
     * Prompts the user to enter a command.
     * Displayed before waiting for user input.
     */
    public void promptCommand() {
        System.out.println("Please enter a command");
    }

    /**
     * Displays an error message to the user.
     * Used for showing exception messages in a consistent format.
     *
     * @param message The error message to display
     */
    public void printError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Displays an error message when there's a problem loading the tasks file.
     * Informs the user that a new task list is being created.
     */
    public void showLoadingError() {
        System.out.println(LINE);
        System.out.println("Problem loading tasks file! Creating new task list.");
        System.out.println(LINE);
    }

    /**
     * Displays the results of a search for tasks by keyword.
     * Shows all tasks that match the search criteria or a message if none are found.
     *
     * @param foundTasks The list of tasks matching the search criteria
     * @param keyword The keyword that was searched for
     */
    public void printFoundTasks(List<Task> foundTasks, String keyword) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list for \"" + keyword + "\":");
        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found :(");
        } else {
            for (int i = 0; i < foundTasks.size(); i++) {
                Task task = foundTasks.get(i);
                System.out.println("\t" + (i + 1) + ". " + task.getTaskType() + " " + task.toString());
            }
        }
        System.out.println(LINE);
    }
}