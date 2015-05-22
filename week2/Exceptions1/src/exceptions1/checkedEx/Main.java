package exceptions1.checkedEx;

public class Main {

    public static void serviceThrow() throws ServiceNotAvailableException {
        throw new ServiceNotAvailableException("Its doomsday...no wifi so you get this exception message instead");
    }

    public static void main(String[] args) {
        try {
            serviceThrow();
        } catch (ServiceNotAvailableException e) {
            System.out.println(e.getMessage());
        }
    }
}
