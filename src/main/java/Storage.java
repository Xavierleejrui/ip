import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data/keesma.txt"; // Relative path
    private static final String DIRECTORY_PATH = "data/";

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("No saved tasks found. Creating a new task list.");
            return tasks; // Return empty list
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task task = parseTask(taskString);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    public static void saveTasks(List<Task> tasks) {
        try {
            // Ensure the directory exists
            Files.createDirectories(Paths.get(DIRECTORY_PATH));

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static Task parseTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
            case "T":
                return new TodoTask(description, isDone);
            case "D":
                if (parts.length < 4) return null; // Prevent errors
                return new Deadline(description, isDone, parts[3]);
            case "E":
                if (parts.length < 5) return null; // Prevent errors
                return new Event(description, isDone, parts[3], parts[4]);
            default:
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error parsing task: " + taskString);
            return null;
        }
    }
}
