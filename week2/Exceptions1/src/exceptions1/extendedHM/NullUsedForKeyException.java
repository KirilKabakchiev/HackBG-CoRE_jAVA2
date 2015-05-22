package exceptions1.extendedHM;

@SuppressWarnings("serial")
public class NullUsedForKeyException extends IllegalArgumentException {

    private static final String COOL_MESSAGE = "Null keys are not allowed. Enable setNullIsAllowed";

    public NullUsedForKeyException() {
        super(COOL_MESSAGE);
    }

    public NullUsedForKeyException(String message) {
        super(message);
    }

    public NullUsedForKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullUsedForKeyException(Throwable cause) {
        super(cause);
    }

}
