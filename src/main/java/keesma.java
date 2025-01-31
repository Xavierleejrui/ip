import java.util.ArrayList;
import java.util.Scanner;

class Task { // Move Task class outside to avoid inner-class issues
    protected String description;
    protected boolean isDone;

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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

public class keesma {
    public static boolean filterSentence(String sentence) {
        return sentence.equalsIgnoreCase("Bye"); // Exit when "Bye" or "bye" is entered
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String name = "Keesma";
        String question = "What can I do for you?";
        String greetings = "Helloooooo I'm " + name + "\n" + question;

        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(); // Store Task objects, not Strings

        while (true) {
            String response = in.nextLine().trim();

            if (filterSentence(response)) {
                break; // Exit if "Bye" is entered
            }

            if (response.equalsIgnoreCase("list")) {
                if (list.isEmpty()) {
                    System.out.println("No tasks yet.");
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    System.out.println(line);
                }
                continue;
            }

            if (response.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(response.substring(5)) - 1;
                    if (index >= 0 && index < list.size()) {
                        list.get(index).markAsDone();
                        System.out.println(line);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + list.get(index));
                        System.out.println(line);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number.");
                }
                continue;
            }

            if (response.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(response.substring(7)) - 1;
                    if (index >= 0 && index < list.size()) {
                        list.get(index).markAsNotDone();
                        System.out.println(line);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + list.get(index));
                        System.out.println(line);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number.");
                }
                continue;
            }

            // Add new task
            list.add(new Task(response));
            System.out.println("Added: " + response);
            System.out.println(line);
        }

        // Goodbye message
        String goodbye = "Bye, Hope to see you again soon, you smell great by the way, although you did smell better last night hehe ^ W ^ ";
        System.out.println(goodbye);
        System.out.println(line);
    }
}
