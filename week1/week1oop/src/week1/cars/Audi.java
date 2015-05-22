package week1.cars;

public class Audi extends Car {

    public Audi(double m) {
        super(m);
    }

    @Override
    public String toString() {
        return String.format("Audi with mileage " + getMileage());
      
    }

    public static void main(String[] args) {
        Audi audi = new Audi(124.5);
        System.out.println(audi);
    }

}
