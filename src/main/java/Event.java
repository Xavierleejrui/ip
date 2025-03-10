import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with start and end times.
 * An Event is a task that occurs during a specific time period.
 * It extends the basic Task class with time period functionality.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private String fromString; // Original string input
    private String toString; // Original string input
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");

    /**
     * Constructs a new Event task with the given description, completion status, start time, and end time.
     * Attempts to parse the time strings into LocalDateTime objects.
     *
     * @param description The description of the event
     * @param isDone Whether the task is initially marked as done
     * @param from The start time of the event (in format d/M/yyyy HHmm)
     * @param to The end time of the event (in format d/M/yyyy HHmm)
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.fromString = from;
        this.toString = to;

        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        } catch (Exception e) {
            this.from = null;
        }

        try {
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (Exception e) {
            this.to = null;
        }
    }

    /**
     * Gets the original string representation of the start time.
     *
     * @return The start time as a string, exactly as it was input
     */
    public String getStartTime() {
        return fromString;
    }

    /**
     * Gets the original string representation of the end time.
     *
     * @return The end time as a string, exactly as it was input
     */
    public String getEndTime() {
        return toString;
    }

    /**
     * Gets the type identifier for this task.
     * An event task is represented by [E].
     *
     * @return The string "[E]" representing an event task
     */
    @Override
    public String getTaskType() {
        return "[E]";
    }

    /**
     * Returns a string representation of this event task.
     * Includes the task status, description, and formatted start and end times.
     * Falls back to original string representations if parsing failed.
     *
     * @return The string representation of this event task
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (from: ");

        if (from != null) {
            result.append(from.format(OUTPUT_FORMAT));
        } else {
            result.append(fromString);
        }

        result.append(" to: ");

        if (to != null) {
            result.append(to.format(OUTPUT_FORMAT));
        } else {
            result.append(toString);
        }

        result.append(")");
        return result.toString();
    }

    /**
     * Returns a string representation of this event task for storage in a file.
     * The format is: "E | isDone | description | fromString | toString"
     *
     * @return A string representation for file storage
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromString + " | " + toString;
    }
}