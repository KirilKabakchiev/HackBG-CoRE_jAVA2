package week1.cars;

public class Car {
    private double mileage;

    public Car(double m) {
        mileage = m;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String toString() {
        return String.format("tostring for Car");
    }

}
