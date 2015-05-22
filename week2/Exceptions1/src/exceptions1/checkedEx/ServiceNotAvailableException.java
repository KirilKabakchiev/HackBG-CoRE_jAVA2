package exceptions1.checkedEx;

@SuppressWarnings("serial")
public class ServiceNotAvailableException extends Exception {

    private static final String COOL_MESSAGE = "It is the cool exception message and shiet.. service unavailable bro, apologies";

    public ServiceNotAvailableException() {
        super(COOL_MESSAGE);
    }

    public ServiceNotAvailableException(String message) {
        super(message);
    }

    public ServiceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceNotAvailableException(Throwable cause) {
        super(cause);
    }
    
   
}
