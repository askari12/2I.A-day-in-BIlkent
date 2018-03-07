package sample;

public class Movement extends GameObject {
    private int dx;
    private int dy;
    private int currentSpeed;
    private int maxSpeed;
    private int acceleration;
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
    public int getCurrentSpeed(){
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeedd){
         currentSpeed=currentSpeedd;
    }

    public int getMaxSpeed(){
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeedd){
         maxSpeed=maxSpeedd;
    }

    public int getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(int accelerationn){
        acceleration=accelerationn;
    }
}
