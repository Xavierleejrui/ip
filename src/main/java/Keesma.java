import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;



public class Keesma {

    private static final String line = "____________________________________________________________";
    private static boolean isRunning = true;
    private static List<Task> taskList = new ArrayList<>();

    public static void sayHello() {
        System.out.println(line);
        System.out.println("Helloooo I'm Keesma, what can I do for you today babyboo");
        System.out.println(line);
    }

    public static void sayBye() {
        System.out.println(line);
        System.out.println("Bye, Hope to see you again soon, you smell great by the way, although you did smell better last night hehe ^ W ^ ");
        System.out.println(line);
    }

    public static void handleBadCommand() {
        System.out.println(line);
        System.out.println("Eh bro can put a proper command anot smh");
        System.out.println(line);
    }

    public static void addTask(String task) throws KeesmaException {
        if (task.isBlank()) {
            throw new KeesmaException("Can you type something lol thanks bro");
        }
        Task newTask = new TodoTask(task, false); // ✅ Use TodoTask instead
        newTask.printTaskAddition();
        taskList.add(newTask);
        Storage.saveTasks(taskList); // Save tasks after adding
    }

    public static void addDeadline(String task) throws KeesmaException {
        if (!task.contains(" /by ")) {
            throw new KeesmaException("Can you type the format properly lol thanks bro");
        }
        String[] stringParts = task.split(" /by ", 2);
        String taskDescription = stringParts[0];
        String dueDate = stringParts.length > 1 ? stringParts[1] : "";

        if (taskDescription.isBlank() || dueDate.isBlank()) {
            throw new KeesmaException("Can you type the format properly lol thanks bro make sure you add description and date.");
        }

        Deadline newDeadline = new Deadline(taskDescription, false, dueDate); // ✅ Pass false for isDone
        newDeadline.printTaskAddition();
        taskList.add(newDeadline);
        Storage.saveTasks(taskList); // Save tasks after adding
    }

    public static void addEvent(String task) throws KeesmaException {
        if (!task.contains(" /from ") || !task.contains(" /to ")) {
            throw new KeesmaException("Can you type the format properly lol thanks bro");
        }

        String[] stringParts = task.split(" /from ", 2);
        if (stringParts.length < 2) {
            System.out.println(line);
            System.out.println("Invalid event format! Use: event <description> /from <start time> /to <end time>");
            System.out.println(line);
            return;
        }

        String taskDescription = stringParts[0];
        String[] timeParts = stringParts[1].split(" /to ", 2);
        String from = timeParts[0];
        String to = (timeParts.length > 1) ? timeParts[1] : "";

        Event newEvent = new Event(taskDescription, false, from, to); // ✅ Pass false for isDone
        newEvent.printTaskAddition();
        taskList.add(newEvent);
        Storage.saveTasks(taskList); // Save tasks after adding
    }

    public static void deleteTask(int taskId) throws KeesmaException {
        if (taskId <= 0 || taskId > taskList.size()) {
            throw new KeesmaException("Can you type your task number properly lol thanks bro");
        }

        Task removeTask = taskList.remove(taskId - 1);
        System.out.println(line);
        System.out.println("Yes boss I have removed this task for you.");
        System.out.println("\t" + removeTask.toString());
        System.out.println("Now there are " + taskList.size() + " tasks in the list");
        System.out.println(line);
    }

    public static void printTaskList() {
        System.out.println(line);
        System.out.println("You have " + taskList.size() + " tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.getTaskType() + " " + task.toString());
        }
        System.out.println(line);
    }

    public static void markTask(int taskId) throws KeesmaException {
        if (taskId <= 0 || taskId > taskList.size()) {
            throw new KeesmaException("Can type your task number properly :') ");
        }

        Task task = taskList.get(taskId - 1);
        task.markAsDone();
        System.out.println(line);
        System.out.println("Your task has been marked as done babyboo");
        System.out.println(task.toString());
        System.out.println(line);
    }

    public static void unmarkTask(int taskId) {
        Task task = taskList.get(taskId - 1);
        task.markAsNotDone();
        System.out.println(line);
        System.out.println("Your task has been unmarked gugu");
        System.out.println(task.toString());
        System.out.println(line);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        taskList = Storage.loadTasks(); // Load tasks from file at startup
        sayHello();

        while (isRunning) {
            try {
                System.out.println("Please enter a command");
                String input = in.nextLine().trim();
                String[] stringParts = input.split(" ", 2);
                String command = stringParts[0];
                String entryRemainder = stringParts.length > 1 ? stringParts[1] : "";

                switch (command.toLowerCase()) {
                case "list":
                    printTaskList();
                    break;
                case "todo":
                    addTask(entryRemainder);
                    break;
                case "deadline":
                    addDeadline(entryRemainder);
                    break;
                case "event":
                    addEvent(entryRemainder);
                    break;
                case "mark":
                    int markTaskId = stringParts.length > 1 ? Integer.parseInt(stringParts[1]) : 0;
                    markTask(markTaskId);
                    break;
                case "unmark":
                    int unmarkTaskId = stringParts.length > 1 ? Integer.parseInt(stringParts[1]) : 0;
                    unmarkTask(unmarkTaskId);
                    break;
                case "delete":
                    int deleteTaskId = Integer.parseInt(entryRemainder);
                    deleteTask(deleteTaskId);
                    break;
                case "bye":
                    isRunning = false;
                    break;
                default:
                    handleBadCommand();
                }
            } catch (KeesmaException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            } catch (NumberFormatException e) {
                System.out.println(line);
                System.out.println("Bruh can you type a valid number? Thanks bro.");
                System.out.println(line);
            }
        }

        in.close();
        sayBye();
    }
}