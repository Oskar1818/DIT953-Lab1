import java.awt.*;

public class MercedesActros extends Transporter<Car> {

    /**
     * A truck that transports cars
     * @param color
     * @param point
     * @param dir
     * @param capacity
     */
    public MercedesActros(Color color, Point point, Direction dir, int capacity){
        super(color, 725, 2, "Mercedes Actros", point, dir, capacity);
        stopEngine();
    }

    // could move this to VTransporter, but would that make it confusing or prevent extensibility..?
    @Override
    public void addLoad(Car car){
        if (!isRampDown()){
            if (getLoadSize() - 1 >= getCapacity() | (getYCord() - car.getYCord() > 1 && getXCord() - car.getXCord() > 1))
                System.out.println("Must move car closer");
            else {
                getLoad().add(car);
                car.setXCord(getXCord());
                car.setYCord(getYCord());
            }
        }
        else
            System.out.println("Ramp must be down!");
    }

    @Override
    public Car unload(){
        if (getLoadSize() > 0 && !isRampDown()) {
            Car car = getLoad().getLast();
            car.setXCord(car.getXCord() + 1);
            car.setYCord(car.getYCord() + 1);
            return car;
        }
        System.out.println("The load is empty");
        return null;
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    // could probably be moved to super class

}
