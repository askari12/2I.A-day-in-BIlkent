package sample;

public class Movement{// movement attribute for every object

    private int dx;
    private int dy;
    private int speed;

    public Movement(int dx , int dy, int speed) {
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    public int getDx(){
        return dx;
    }
    public int getDy(){
        return dy;
    }

    public void setDx(int dxx){
        dx=dxx;
    }
    public void setDy(int dyy){
         dy=dyy;
    }

    public int getspeed(){
        return speed;
    }
    public void setSpeed(int currentSpeedd){
         speed=currentSpeedd;
    }

}
