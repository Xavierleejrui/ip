import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Keesma {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Keesma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KeesmaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.sayHello();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                ui.promptCommand();
                String input = scanner.nextLine().trim();
                String[] stringParts = input.split(" ", 2);
                String command = stringParts[0];
                String entryRemainder = stringParts.length > 1 ? stringParts[1] : "";

                switch (command.toLowerCase()) {
                case "list":
                    ui.printTaskList(tasks.getTaskList());
                    break;
                case "todo":
                    Task newTask = tasks.addTodo(entryRemainder);
                    ui.printTaskAdded(newTask, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;
                case "deadline":
                    Task newDeadline = tasks.addDeadline(entryRemainder);
                    ui.printTaskAdded(newDeadline, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;
                case "event":
                    Task newEvent = tasks.addEvent(entryRemainder);
                    ui.printTaskAdded(newEvent, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;
                case "mark":
                    int markTaskId = stringParts.length > 1 ? Integer.parseInt(stringParts[1]) : 0;
                    Task markedTask = tasks.markTaskAsDone(markTaskId);
                    ui.printTaskMarked(markedTask);
                    storage.saveTasks(tasks.getTaskList());
                    break;
                case "unmark":
                    int unmarkTaskId = stringParts.length > 1 ? Integer.parseInt(stringParts[1]) : 0;
                    Task unmarkedTask = tasks.markTaskAsNotDone(unmarkTaskId);
                    ui.printTaskUnmarked(unmarkedTask);
                    storage.saveTasks(tasks.getTaskList());
                    break;
                case "delete":
                    int deleteTaskId = Integer.parseInt(entryRemainder);
                    Task deletedTask = tasks.deleteTask(deleteTaskId);
                    ui.printTaskDeleted(deletedTask, tasks.size());
                    storage.saveTasks(tasks.getTaskList());
                    break;
                case "bye":
                    isRunning = false;
                    break;
                default:
                    ui.handleBadCommand();
                }
            } catch (KeesmaException e) {
                ui.printError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printError("Bruh can you type a valid number? Thanks bro.");
            }
        }

        scanner.close();
        ui.sayBye();
    }

    public static void main(String[] args) {
        new Keesma("data/tasks.txt").run();
    }
}