import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task addTodo(String description) throws KeesmaException {
        if (description.isBlank()) {
            throw new KeesmaException("Can you type something lol thanks bro");
        }
        Task newTask = new TodoTask(description, false);
        tasks.add(newTask);
        return newTask;
    }

    public Task addDeadline(String taskInput) throws KeesmaException {
        if (!taskInput.contains(" /by ")) {
            throw new KeesmaException("Can you type the format properly lol thanks bro");
        }
        String[] stringParts = taskInput.split(" /by ", 2);
        String taskDescription = stringParts[0];
        String dueDate = stringParts.length > 1 ? stringParts[1] : "";

        if (taskDescription.isBlank() || dueDate.isBlank()) {
            throw new KeesmaException("Can you type the format properly lol thanks bro make sure you add description and date.");
        }

        Deadline newDeadline = new Deadline(taskDescription, false, dueDate);
        tasks.add(newDeadline);
        return newDeadline;
    }

    public Task addEvent(String taskInput) throws KeesmaException {
        if (!taskInput.contains(" /from ") || !taskInput.contains(" /to ")) {
            throw new KeesmaException("Can you type the format properly lol thanks bro");
        }

        String[] stringParts = taskInput.split(" /from ", 2);
        if (stringParts.length < 2) {
            throw new KeesmaException("Invalid event format! Use: event <description> /from <start time> /to <end time>");
        }

        String taskDescription = stringParts[0];
        String[] timeParts = stringParts[1].split(" /to ", 2);
        String from = timeParts[0];
        String to = (timeParts.length > 1) ? timeParts[1] : "";

        Event newEvent = new Event(taskDescription, false, from, to);
        tasks.add(newEvent);
        return newEvent;
    }

    public Task deleteTask(int taskId) throws KeesmaException {
        if (taskId <= 0 || taskId > tasks.size()) {
            throw new KeesmaException("Can you type your task number properly lol thanks bro");
        }
        return tasks.remove(taskId - 1);
    }

    public Task markTaskAsDone(int taskId) throws KeesmaException {
        if (taskId <= 0 || taskId > tasks.size()) {
            throw new KeesmaException("Can type your task number properly :') ");
        }
        Task task = tasks.get(taskId - 1);
        task.markAsDone();
        return task;
    }

    public Task markTaskAsNotDone(int taskId) throws KeesmaException {
        if (taskId <= 0 || taskId > tasks.size()) {
            throw new KeesmaException("Can type your task number properly :') ");
        }
        Task task = tasks.get(taskId - 1);
        task.markAsNotDone();
        return task;
    }
}