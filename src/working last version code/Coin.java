package sample;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Coin extends Collectable{
    private Parent root;
    private Circle coin;
    public Coin(Location loc, Dimension dimensions, Movement movement, Image img, Parent root){
        super(loc,dimensions,movement,img);
        this.root=root;
    }

    @Override
    public void renderobject() {
        coin = new Circle();
        coin.setFill(new ImagePattern(this.img));
        coin.setCenterX(loc.getX());
        coin.setCenterY(loc.getY());
        coin.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(coin);
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

    }
}
