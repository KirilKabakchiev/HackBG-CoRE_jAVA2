package exceptions1.uncheckedExTry;

public class Main {

    public static User testMyEx() throws DatabaseCorruptedException {
        User me = null;
        try {
            String name = firstThrow("");
            me = new User(name);
        } catch (DatabaseCorruptedException ex) {
            throw new DatabaseCorruptedException("Username is empty ", ex);
        }

        return me;
    }

    public static String firstThrow(String name) throws DatabaseCorruptedException {
        if (name.equals(null) || name.equals("")) {
            throw new DatabaseCorruptedException();
        }
        return name;
    }

    public static void main(String[] args) {
        try {
            User user = Main.testMyEx();
            System.out.println(user);
        } catch (DatabaseCorruptedException ex) {
            System.out.println("Caught database ex. Printing stacktrace");
            ex.printStackTrace();
        }
    }
}
