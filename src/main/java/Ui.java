import java.util.List;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    public void sayHello() {
        System.out.println(LINE);
        System.out.println("Helloooo I'm Keesma, what can I do for you today babyboo");
        System.out.println(LINE);
    }

    public void sayBye() {
        System.out.println(LINE);
        System.out.println("Bye, Hope to see you again soon, you smell great by the way, although you did smell better last night hehe ^ W ^ ");
        System.out.println(LINE);
    }

    public void handleBadCommand() {
        System.out.println(LINE);
        System.out.println("Eh bro can put a proper command anot smh");
        System.out.println(LINE);
    }

    public void printTaskList(List<Task> taskList) {
        System.out.println(LINE);
        System.out.println("You have " + taskList.size() + " tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.getTaskType() + " " + task.toString());
        }
        System.out.println(LINE);
    }

    public void printTaskAdded(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Yes bro I have added:");
        System.out.println("\t" + task.toString());
        System.out.println("Now there are " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    public void printTaskDeleted(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Yes boss I have removed this task for you.");
        System.out.println("\t" + task.toString());
        System.out.println("Now there are " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    public void printTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println("Your task has been marked as done babyboo");
        System.out.println("\t" + task.toString());
        System.out.println(LINE);
    }

    public void printTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println("Your task has been unmarked gugu");
        System.out.println("\t" +task.toString());
        System.out.println(LINE);
    }

    public void promptCommand() {
        System.out.println("Please enter a command");
    }

    public void printError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println(LINE);
        System.out.println("Problem loading tasks file! Creating new task list.");
        System.out.println(LINE);
    }
}