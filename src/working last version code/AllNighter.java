package sample;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class AllNighter extends PowerUp {
    private Parent root;
    private Circle allNighter;
    public AllNighter(Location loc, Dimension dimensions, Movement movement, Image img, Parent root){
        super(loc,dimensions,movement,img);
        this.root=root;
    }

    @Override
    public void powerUp(Location loc, Dimension dimensions, Movement movement, Image img) {

    }

    public void powers(){}

    @Override
    public void renderobject() {
        allNighter = new Circle();
        allNighter.setFill(new ImagePattern(this.img));
        allNighter.setCenterX(loc.getX());
        allNighter.setCenterY(loc.getY());
        allNighter.setRadius(dimensions.getRadius());

        ((Pane) root).getChildren().add(allNighter);
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

        allNighter.setVisible(false);


    }
}
