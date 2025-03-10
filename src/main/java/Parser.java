public class Parser {
    /**
     * Parses the user input and extracts the command type and arguments.
     *
     * @param userInput The user's input string
     * @return A String array where the first element is the command type and the second element is the arguments
     * @throws KeesmaException If the input cannot be parsed
     */
    public String[] parseInput(String userInput) throws KeesmaException {
        String trimmedInput = userInput.trim();
        if (trimmedInput.isEmpty()) {
            throw new KeesmaException("Empty commands are not allowed lah.");
        }

        String[] parts = trimmedInput.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        return new String[] { commandType, arguments };
    }

    /**
     * Validates a todo task description.
     *
     * @param description The description to validate
     * @throws KeesmaException If the description is blank
     */
    public void validateTodoDescription(String description) throws KeesmaException {
        if (description.isBlank()) {
            throw new KeesmaException("Can you type something lol thanks bro");
        }
    }

    /**
     * Validates and parses a command argument with a specific delimiter.
     *
     * @param args The arguments for the command
     * @param delimiter The delimiter to split on (e.g., " /by ")
     * @param errorMessage The error message to show if validation fails
     * @return A String array containing the parts split by the delimiter
     * @throws KeesmaException If the command format is invalid
     */
    public String[] parseWithDelimiter(String args, String delimiter, String errorMessage) throws KeesmaException {
        if (!args.contains(delimiter)) {
            throw new KeesmaException(errorMessage);
        }

        String[] parts = args.split(delimiter, 2);
        String firstPart = parts[0];
        String secondPart = parts.length > 1 ? parts[1] : "";

        if (firstPart.isBlank() || secondPart.isBlank()) {
            throw new KeesmaException(errorMessage);
        }

        return new String[] { firstPart, secondPart };
    }

    /**
     * Validates and parses deadline command arguments.
     *
     * @param args The arguments for the deadline command
     * @return A String array containing the description and due date
     * @throws KeesmaException If the deadline command format is invalid
     */
    public String[] parseDeadline(String args) throws KeesmaException {
        String errorMsg = "Can you type the format properly lol thanks bro make sure you add description and date.";
        return parseWithDelimiter(args, " /by ", errorMsg);
    }

    /**
     * Validates and parses event command arguments.
     *
     * @param args The arguments for the event command
     * @return A String array containing the description, start time, and end time
     * @throws KeesmaException If the event command format is invalid
     */
    public String[] parseEvent(String args) throws KeesmaException {
        String formatError = "Can you type the format properly lol thanks bro";

        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new KeesmaException(formatError);
        }

        // Parse the description and the remaining part with from/to
        String[] firstParts = parseWithDelimiter(args, " /from ", formatError);
        String description = firstParts[0];

        // Parse the from and to parts
        String[] timeParts = parseWithDelimiter(firstParts[1], " /to ", formatError);
        String from = timeParts[0];
        String to = timeParts[1];

        return new String[] { description, from, to };
    }

    /**
     * Parses a number from the input string.
     *
     * @param args The string containing the number
     * @return The parsed integer
     * @throws KeesmaException If the input cannot be parsed as a number
     */
    public int parseNumber(String args) throws KeesmaException {
        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new KeesmaException("Bruh can you type a valid number? Thanks bro.");
        }
    }

    /**
     * Validates a task index.
     *
     * @param taskId The task index to validate
     * @param size The size of the task list
     * @throws KeesmaException If the task index is invalid
     */
    public void validateTaskIndex(int taskId, int size) throws KeesmaException {
        if (taskId <= 0 || taskId > size) {
            throw new KeesmaException("Can type your task number properly :') ");
        }
    }
}