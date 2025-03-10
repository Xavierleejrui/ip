import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class to manage the collection of tasks.
 * Handles adding, modifying, and retrieving tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks
     */
    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new todo task.
     *
     * @param description The description of the todo task
     * @return The newly created task
     */
    public Task addTodo(String description) {
        Task newTask = new TodoTask(description, false);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a new deadline task.
     *
     * @param description The description of the deadline
     * @param by The due date of the deadline
     * @return The newly created deadline task
     */
    public Task addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, false, by);
        tasks.add(newDeadline);
        return newDeadline;
    }

    /**
     * Adds a new event task.
     *
     * @param description The description of the event
     * @param from The start time of the event
     * @param to The end time of the event
     * @return The newly created event task
     */
    public Task addEvent(String description, String from, String to) {
        Event newEvent = new Event(description, false, from, to);
        tasks.add(newEvent);
        return newEvent;
    }

    /**
     * Deletes a task by its index.
     *
     * @param taskId The index of the task to delete (1-based)
     * @return The deleted task
     */
    public Task deleteTask(int taskId) {
        return tasks.remove(taskId - 1);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param taskId The index of the task to mark as done (1-based)
     * @return The marked task
     */
    public Task markTaskAsDone(int taskId) {
        Task task = tasks.get(taskId - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done by its index.
     *
     * @param taskId The index of the task to mark as not done (1-based)
     * @return The unmarked task
     */
    public Task markTaskAsNotDone(int taskId) {
        Task task = tasks.get(taskId - 1);
        task.markAsNotDone();
        return task;
    }

    /**
     * Finds tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for
     * @return A list of matching tasks
     */
    public List<Task> findTasks(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }
}