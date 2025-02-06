public class Task { // Move Task class outside to avoid inner-class issues
    protected String description;
    protected boolean isDone;
    private static final String line = "____________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" for done, " " for not done
    }

    public String getTaskType() {
        return "[T]";
    }

    public void printTaskAddition() {
        System.out.println(line);
        System.out.println("Yes bro! I've added this for you hehe: ");
        System.out.println("\t" + getTaskType() + " " + this);
        System.out.println(line);

    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}