import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A Deadline is a task that needs to be completed by a specific date and time.
 * It extends the basic Task class with deadline functionality.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected String byString; // Original string input for file storage
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");

    /**
     * Constructs a new Deadline task with the given description, completion status, and due date.
     * Attempts to parse the due date string into a LocalDateTime object.
     *
     * @param description The description of the deadline task
     * @param isDone Whether the task is initially marked as done
     * @param by The due date of the deadline task (in format d/M/yyyy HHmm)
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.byString = by;
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (Exception e) {
            // If parsing fails, keep the original string only
            this.by = null;
        }
    }

    /**
     * Constructs a new Deadline task with a pre-parsed LocalDateTime object.
     * Used when the date has already been parsed.
     *
     * @param description The description of the deadline task
     * @param isDone Whether the task is initially marked as done
     * @param by The parsed LocalDateTime object representing the due date
     * @param byString The original string representation of the due date
     */
    public Deadline(String description, boolean isDone, LocalDateTime by, String byString) {
        super(description, isDone);
        this.by = by;
        this.byString = byString;
    }

    /**
     * Gets the original string representation of the due date.
     *
     * @return The due date as a string, exactly as it was input
     */
    public String getDueDate() {
        return byString;
    }

    /**
     * Gets the parsed LocalDateTime object representing the due date.
     * May be null if parsing failed.
     *
     * @return The parsed LocalDateTime object or null
     */
    public LocalDateTime getDueDateTime() {
        return by;
    }

    /**
     * Gets the type identifier for this task.
     * A deadline task is represented by [D].
     *
     * @return The string "[D]" representing a deadline task
     */
    @Override
    public String getTaskType() {
        return "[D]";
    }

    /**
     * Returns a string representation of this deadline task.
     * Includes the task status, description, and formatted due date.
     *
     * @return The string representation of this deadline task
     */
    @Override
    public String toString() {
        if (by != null) {
            return super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
        } else {
            return super.toString() + " (by: " + byString + ")";
        }
    }

    /**
     * Returns a string representation of this deadline task for storage in a file.
     * The format is: "D | isDone | description | byString"
     *
     * @return A string representation for file storage
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byString;
    }
}