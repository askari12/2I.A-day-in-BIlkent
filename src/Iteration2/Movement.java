package sample;

public class Movement{// movement attribute for every object

    private double dx;
    private double dy;
    private double speed;

    public Movement(double dx , double dy, double speed) {
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    double getDx(){
        return dx;
    }
    double getDy(){
        return dy;
    }

    public void setDx(double dxx){
        dx=dxx;
    }
    public void setDy(double dyy){
         dy=dyy;
    }

    double getSpeed(){
        return speed;
    }
    void setSpeed(int currentSpeedd){
         speed=currentSpeedd;
    }

}
