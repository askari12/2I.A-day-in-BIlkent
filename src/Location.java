package sample;

public class Location{      //Location information for every object and distance calculator for 2 objects
    private int x;
    private int y;
    public Location(int xx,int yy){
        x=xx;
        y=yy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance(Location a, Location b)
    {
        int x_square = (a.x - b.x) * (a.x - b.x);
        int y_square = (a.y - b.y) * (a.y - b.y);
        return (int)Math.sqrt(x_square + y_square);
    }
}
