package sample;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public  class DoubleBullet extends PowerUp {

    private Parent root;
    private Circle doubleBullet;

    public DoubleBullet(Location loc, Dimension dimensions, Movement movement, Image img, Parent root){
        super(loc,dimensions,movement,img);
        this.root = root;
    }
    public void powers(){}

    @Override
    public void powerUp(Location loc, Dimension dimensions, Movement movement, Image img) {

    }

    @Override
    public void renderobject() {
        doubleBullet = new Circle();
        doubleBullet.setFill(new ImagePattern(this.img));
        doubleBullet.setCenterX(loc.getX());
        doubleBullet.setCenterY(loc.getY());
        doubleBullet.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(doubleBullet);
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
        doubleBullet.setVisible(false);
    }
}
