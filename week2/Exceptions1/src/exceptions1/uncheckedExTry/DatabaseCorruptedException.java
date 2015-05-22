package exceptions1.uncheckedExTry;

public class DatabaseCorruptedException extends RuntimeException {
    public DatabaseCorruptedException() {
        super();
    }

    public DatabaseCorruptedException(String message) {
        super(message);
    }

    public DatabaseCorruptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseCorruptedException(Throwable cause) {
        super(cause);
    }
}
