package model.vehicle.transporter;

import model.vehicle.Direction;
import model.vehicle.car.Car;

import java.awt.*;

public class MercedesBlueWonder extends Transporter<Car> {


    /**
     * A truck that transports cars
     * @param color - The color of the transporter.
     * @param point - The coordinates of the transporter, represented as a point (x, y).
     * @param dir - The direction at which the transporter is pointing at.
     * @param capacity - The maximum amount of cars the transporter can transport.
     */
    public MercedesBlueWonder(Color color, Point point, Direction dir, int capacity) {
        super(color, 500, 2, "Mercedes Blue Wonder", point, dir, capacity);
        stopEngine();
    }

    @Override
    public void addLoad(Car car) {
        if (isRampDown()) {
            if (getYCord() - car.getXCord() > 1 | getLoadSize() - 1 >= getCapacity())
                System.out.println("Must move car closer");
            else {
                getLoad().add(car);
                car.setXCord(getXCord());
                car.setYCord(getYCord());
            }
        } else
            System.out.println("Ramp must be down!");
    }

    @Override
    public Car unload() {
        if (getLoadSize() > 0 && isRampDown()) {
            Car car = getLoad().getLast();
            car.setXCord(car.getXCord() + 1); // why?
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
}

