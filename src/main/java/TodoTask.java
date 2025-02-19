public class TodoTask extends Task {
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String getTaskType() {
        return "[T]";
    }
}
