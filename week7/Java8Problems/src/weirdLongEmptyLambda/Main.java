package weirdLongEmptyLambda;

public class Main {

	private static  X x = () -> () -> () -> () -> () -> () -> () -> () -> () -> null;
	public static void main(String[] args) {
		System.out.println(x.compile());
	}
} 
