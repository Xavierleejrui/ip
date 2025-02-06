public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "[D]"; // "D" for Deadline
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
