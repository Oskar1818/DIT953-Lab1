import java.awt.*;

/**
 * The abstract base class for all vehicles.
 */
public abstract class Vehicle implements IMove, ITransportable{

    private double xCord;
    private double yCord;
    private Direction dir;
    private Color color;
    private String name;
    private double speed;
    private double enginePower;
    private int nrDoors;

    /**
     *
     * @param color - The color of a vehicle.
     * @param enginePower - The engine power of a vehicle.
     * @param speed The current speed of a vehicle.
     * @param nrDoors The number of doors of a vehicle.
     * @param name - The name of a specific instance of a vehicle.
     * @param point - The coordinates of a vehicle, represented as a point (x, y).
     * @param dir - The initial direction of the vehicle.
     */
    public Vehicle(Color color, double enginePower, double speed, int nrDoors, String name, Point point, Direction dir){
        this.color = color;
        this.enginePower = enginePower;
        this.nrDoors = nrDoors;
        this.name = name;
        this.xCord = point.getX();
        this.yCord = point.getY();
        this.dir = dir;
        this.speed = speed;
    }

    /**
     * @return Returns the number of doors the car has.
     */
    public int getNrDoors(){ return this.nrDoors; }

    /**
     * @return Returns the engine power of the car.
     */
    public double getEnginePower(){ return this.enginePower; }

    /**
     * Starts the engine and sets the initial speed to 1.
     */
    public void startEngine(){ this.speed = 1; }

    /**
     * Stops the engine, which makes the car stop.
     */
    public void stopEngine(){ this.speed = 0; }

    /**
     * Makes the car go faster by increasing the speed via the incrementSpeed method.
     * @param amount amount the amount at which the speed should increase by.
     */
    public void gas(double amount) {
        double gasFactor = Math.max(Math.min(amount, 1), 0);
        incrementSpeed(gasFactor);
    }

    /**
     * Makes the car go slower by decreasing the speed via the decrementSpeed method.
     * @param amount amount amount the amount at which the speed should decrease by.
     */
    public void brake(double amount) {
        double brakeFactor = Math.max(Math.min(amount, 1), 0); // 0 <= breakFactor <= 1
        decrementSpeed(brakeFactor);
    }

    /**
     * @return Returns vehicle name.
     */
    public String getName(){ return this.name; }

    /**
     * @return Returns vehicle colour.
     */
    public String getColor(){ return color.toString(); }

    /* interface functions */

    /**
     * @param d - Sets direction.
     */
    @Override
    public void setDirection(Direction d) { this.dir = d; }

    /**
     * @return Returns direction of vehicle.
     */
    @Override
    public Direction getDirection() { return this.dir; }

    /**
     * Increases the speed of the Saab by the input amount times the speed factor, and makes sure the
     * speed doesn't go above the engine power.
     * @param amount amount the amount at which the speed should decrease by.
     */
    @Override
    public void incrementSpeed(double amount){
        setSpeed(Math.min(getSpeed() + speedFactor() * amount, getEnginePower()));
    }

    /**
     * Decreases the speed of the Saab by the input amount times the speed factor, and makes sure the
     * speed doesn't go below 0.
     * @param amount amount the amount at which the speed should decrease by.
     */
    @Override
    public void decrementSpeed(double amount){
        setSpeed(Math.max(getSpeed() - speedFactor() * amount,0));
    }

    /**
     * @param x - Sets the x-coordinate.
     */
    @Override
    public void setXCord(double x) { this.xCord = x; }

    /**
     * @return Returns the x-coordinate.
     */
    @Override
    public double getXCord() { return this.xCord; }

    /**
     * @param y - Sets the y-coordinate.
     */
    @Override
    public void setYCord(double y) { this.yCord = y;}

    /**
     * @return Returns y coordinate of vehicle.
     */
    @Override
    public double getYCord() { return this.yCord; }

    /**
     * @param speed - the desired speed.
     */
    @Override
    public void setSpeed(double speed){ this.speed = Math.min(speed, this.enginePower); }

    /**
     * @return Gets the speed.
     */
    @Override
    public double getSpeed(){ return this.speed; }

    @Override
    public void move() {
        switch (getDirection()) {
            case EAST -> setXCord(getXCord() + getSpeed());
            case WEST -> setXCord(getXCord() - getSpeed());
            case NORTH -> setYCord(getYCord() + getSpeed());
            case SOUTH -> setYCord(getYCord() - getSpeed());
        }
    }

    @Override
    public void turnRight() {
        switch (getDirection()) {
            case EAST -> setDirection(Direction.SOUTH);
            case WEST -> setDirection(Direction.NORTH);
            case NORTH -> setDirection(Direction.EAST);
            case SOUTH -> setDirection(Direction.WEST);
        }
    }

    @Override
    public void turnLeft() {
        switch (getDirection()) {
            case EAST -> setDirection(Direction.NORTH);
            case WEST -> setDirection(Direction.SOUTH);
            case NORTH -> setDirection(Direction.WEST);
            case SOUTH -> setDirection(Direction.EAST);
        }
    }

}