package ImmutableListImpl;


@SuppressWarnings("serial")
public class ImmutableListModificationException extends RuntimeException {
    
    public ImmutableListModificationException() {
        super();
    }

    public ImmutableListModificationException(String message) {
        super(message);
    }

    public ImmutableListModificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImmutableListModificationException(Throwable cause) {
        super(cause);
    }

}
