package sample;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class RageMode extends PowerUp {
    private Parent root;
    private Circle rageMode;

    public RageMode(Location loc, Dimension dimensions, Movement movement, Image img, Parent root){
        super(loc,dimensions,movement,img);
        this.root = root;
    }

    @Override
    public void powerUp(Location loc, Dimension dimensions, Movement movement, Image img) {

    }

    public void powers(){}

    @Override
    public void renderobject() {
        rageMode = new Circle();
        rageMode.setFill(new ImagePattern(this.img));
        rageMode.setCenterX(loc.getX());
        rageMode.setCenterY(loc.getY());
        rageMode.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(rageMode);
    }
    public int getX(){
        return loc.getX();
    }

    public int getY(){
        return loc.getY();
    }

    public void setX(int x) {
        loc.setX(x);
    }

    public void setY(int y) {
        loc.setY(y);
    }

    @Override
    public void destroy() {
rageMode.setVisible(false);
    }
}
