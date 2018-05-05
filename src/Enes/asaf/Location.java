package sample;

public class Location{      //Location information for every object and distance calculator for 2 objects
    private double x;
    private double y;
    public Location(double xx,double yy){
        x=xx;
        y=yy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    double getDistance(Location a, Location b)
    {
        double x_square = (a.x - b.x) * (a.x - b.x);
        double y_square = (a.y - b.y) * (a.y - b.y);
        return Math.sqrt(x_square + y_square);
    }
}
