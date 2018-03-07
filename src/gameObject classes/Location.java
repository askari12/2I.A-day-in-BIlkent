package sample;

public class Location extends GameObject {
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
    private int getDistance(Location a,Location b){
        return 5;
    }
}
