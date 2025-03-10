/**
 * Custom exception class for Keesma-specific errors.
 * Used for conveying user-friendly error messages.
 */
public class KeesmaException extends Exception {

    /**
     * Constructs a new KeesmaException with the specified message.
     *
     * @param message The error message
     */
    public KeesmaException(String message) {
        super(message);
    }
}