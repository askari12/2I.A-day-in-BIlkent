package  sample;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Shield extends PowerUp {
    private Parent root;
    private Circle shield;
    public  Shield(Location loc, Dimension dimensions, Movement movement, Image img, Parent root){
        super(loc,dimensions,movement,img);
        this.root=root;
    }

    @Override
    public void powerUp(Location loc, Dimension dimensions, Movement movement, Image img) {

    }

    public void powers(){}

    @Override
    public void renderobject() {
        shield = new Circle();
        shield.setFill(new ImagePattern(this.img));
        shield.setCenterX(loc.getX());
        shield.setCenterY(loc.getY());
        shield.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(shield);
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
shield.setVisible(false);
    }
}
