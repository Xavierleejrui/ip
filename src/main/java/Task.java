public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final String LINE = "____________________________________________________________";

    // Constructor allows loading completed status from file
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String toFileString(); // Each subclass will implement this

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


