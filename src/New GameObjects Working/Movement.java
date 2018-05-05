package GameObject;

public class Movement {

    // Instance Variables

    private double dx; // movement in x direction
    private double dy; // movement in y direction
    private double speed; // speed of the object


    // Constructor

    public Movement(double dx , double dy , double speed){
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    // getters

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getSpeed() {
        return speed;
    }

    // Setters

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
