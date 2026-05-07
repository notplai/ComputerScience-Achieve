// This is written by @notplai

public class Q1 {
    static void main(String [] args) {
        Car myCar = new Car(50, 20);
        myCar.addGas(20);
        myCar.drive(100);
        System.out.println("Gas left = " + myCar.getGas() + " gallons");

        Truck t = new Truck(50, 20, 15, 20);
        t.drive(100);
        System.out.println("Gas left = " + t.getGas() + " gallons");

        Truck t2 = new Truck(0, 20, 20, 15);
        t2.addGas(5);
        t2.drive(100);
        System.out.println("Gas left = " + t2.getGas() + " gallons");
    }
}


class Car {
    private double gas;
    private final double efficiency;

    Car(double gas, double efficiency) {
        this.gas = gas;
        this.efficiency = efficiency;
    }

    void drive(double distance) {
        var pcal = distance / efficiency;
        if (gas < pcal) {
            System.out.println("You cannot drive too far, please add gas");
            return;
        }
        gas -= pcal;
    }

    void setGas(double amount) { this.gas = amount; }
    void addGas(double amount) { this.gas += amount; }

    double getGas() { return this.gas; }
    double getEfficiency() { return this.efficiency; }
}

class Truck extends Car {
    private final double weight;

    Truck(double gas, double efficiency, double maxWeight, double weight) {
        super(gas, efficiency);
        this.weight = Math.min(weight, maxWeight);
    }

    @Override
    void drive(double distance) {
        double distanceMultiplier = weight > 20 ? 1.3d :
                                  weight > 10 ? 1.2d :
                                  weight <= 1 ? 1.1d : 1d;
        double consumption = (distance / getEfficiency()) * distanceMultiplier;

        if (getGas() < consumption) {
            System.out.println("You cannot drive too far, please add gas");
            return;
        }

        setGas(getGas() - consumption);
    }
}