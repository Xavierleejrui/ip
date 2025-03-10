import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class to handle file operations.
 * Responsible for saving and loading tasks from persistent storage.
 * This class manages the serialization and deserialization of Task objects
 * to and from a text file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * Creates any necessary parent directories if they don't exist.
     *
     * @param filePath The path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        // Create directories if they don't exist
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Loads tasks from the storage file.
     * Reads each line from the file and parses it into a Task object.
     *
     * @return A list of tasks loaded from the file
     * @throws KeesmaException If there's an error reading the file or parsing its contents
     */
    public List<Task> loadTasks() throws KeesmaException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks; // Return empty list if file doesn't exist yet
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromStorage(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new KeesmaException("Cannot find file at: " + filePath);
        } catch (Exception e) {
            throw new KeesmaException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the storage file.
     * Each task is converted to a string representation and written to the file.
     *
     * @param tasks The list of tasks to save
     * @throws KeesmaException If there's an error writing to the file
     */
    public void saveTasks(List<Task> tasks) throws KeesmaException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new KeesmaException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     * The format of each line is determined by the task type:
     * - Todo: "T | isDone | description"
     * - Deadline: "D | isDone | description | by"
     * - Event: "E | isDone | description | from | to"
     *
     * @param line The line to parse from the storage file
     * @return The parsed Task object, or null if the line couldn't be parsed
     */
    private Task parseTaskFromStorage(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];

        switch (type) {
        case "T":
            return new TodoTask(description, isDone);
        case "D":
            String by = parts.length > 3 ? parts[3] : "";
            return new Deadline(description, isDone, by);
        case "E":
            String from = parts.length > 3 ? parts[3] : "";
            String to = parts.length > 4 ? parts[4] : "";
            return new Event(description, isDone, from, to);
        default:
            return null;
        }
    }
}