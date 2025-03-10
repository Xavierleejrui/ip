/**
 * Abstract base class for all task types.
 * Defines common properties and behaviors for tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final String LINE = "____________________________________________________________";

    /**
     * Constructs a task with the given description and completion status.
     *
     * @param description The description of the task
     * @param isDone Whether the task is initially marked as done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of this task for storage in a file.
     *
     * @return A string representation for file storage
     */
    public abstract String toFileString(); // Each subclass will implement this

    /**
     * Returns the type identifier of this task.
     *
     * @return A string representing the task type
     */
    public abstract String getTaskType(); // Each subclass should return its type

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" for done, " " for not done
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void printTaskAddition() {
        System.out.println(LINE);
        System.out.println("Yes bro! I've added this for you hehe: ");
        System.out.println("\t" + getTaskType() + " " + this);
        System.out.println(LINE);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

