/**
 * Represents a simple to-do task without a deadline or timeframe.
 * A TodoTask is the most basic type of task in the system,
 * requiring only a description without any time constraints.
 * It extends the abstract Task class.
 */
public class TodoTask extends Task {

    /**
     * Constructs a new TodoTask with the given description and completion status.
     *
     * @param description The description of the to-do task
     * @param isDone Whether the task is initially marked as done
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the type identifier for this task.
     * A todo task is represented by [T].
     *
     * @return The string "[T]" representing a todo task
     */
    @Override
    public String getTaskType() {
        return "[T]";
    }

    /**
     * Returns a string representation of this todo task for storage in a file.
     * The format is: "T | isDone | description"
     *
     * @return A string representation for file storage
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}