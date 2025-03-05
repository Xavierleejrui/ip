import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        // Create directories if they don't exist
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

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