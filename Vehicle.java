import java.awt.*;

public abstract class Vehicle implements Movable {
    private static final Direction[] dirs = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    protected Position position;
    public Direction direction;
    private final int nrDoors;
    public final double enginePower;
    public double currentSpeed;
    private Color color;
    public String modelName;
    public Vehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        stopEngine();
    }
    protected Position getPosition() {
        return this.position;
    }
    protected Direction getDirection() { return this.direction; }
    protected void currentSpeed(double currentSpeed) {
        if (this.currentSpeed < 0) {
            this.currentSpeed = 0;
        }
        else if (this.currentSpeed > enginePower) {
            this.currentSpeed = enginePower;
        }
        else {
            this.currentSpeed = currentSpeed;
        }
    }
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void setPosition (int x, int y) {this.position = new Position(x, y);}

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }
    public abstract void incrementSpeed(double amount);

    public abstract void decrementSpeed(double amount);


    protected void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Illegal amount " + amount);
        }
        else {
            int old = (int) getCurrentSpeed();
            incrementSpeed(amount);
            if (old > (int) getCurrentSpeed()) {
                currentSpeed = old;
            }
        }
    }
    protected void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Illegal amount: " + amount);
        }
        else {
            int old = (int) getCurrentSpeed();
            decrementSpeed(amount);
            if ((int) getCurrentSpeed() > old) {
                currentSpeed = old;
            }
        }
    }
    @Override
    public void move() {
        int x = position.getX();
        int y = position.getY();
        int x1 = x;
        int y1 = y;
        startEngine();
        if (direction == Direction.EAST) {
            x1 += (int) getCurrentSpeed();
        }
        if (direction == Direction.WEST) {
            x1 -= (int) getCurrentSpeed();
        }
        if (direction == Direction.NORTH) {
            y1 += (int) getCurrentSpeed();
        }
        if (direction == Direction.SOUTH) {
            y1 -= (int) getCurrentSpeed();
        }
        int amount = (int) Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2));
        gas(amount);
        stopEngine();
        position = new Position(x1, y1);
    }
    @Override
    public void turnLeft() {
        direction = getNext(direction, -1);
    }
    @Override
    public void turnRight() {
        direction = getNext(direction, 1);
    }


    private static Direction getNext (Direction dir, int j) {
        int index = 0;
        for (int i = 0; i < dirs.length; i++) {
            if (dir == dirs[i]) {
                index = i;
            }
        }
        index = index + j;
        if (index < 0) {
            index = dirs.length - 1;
        }
        if (index > dirs.length - 1) {
            index = 0;
        }
        int find = Math.max(0, Math.min(index, dirs.length - 1));
        return dirs[find];
    }

    @Override
    public String toString() {
        return "The car is a " + this.modelName + " of " + this.getClass();
    }



}
