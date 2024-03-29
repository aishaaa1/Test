import java.awt.*;

public class Volvo240 extends Car {
    private final static double trimFactor = 1.25;
    public Volvo240 (){
        super(4, Color.black, 240, "Volvo");
    }

    @Override
    public double speedFactor() {
        return this.enginePower * 0.01 * trimFactor;
    }
    @Override
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    @Override
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

}
