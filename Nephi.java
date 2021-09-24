import javax.swing.*;

public class Nephi extends Sprite {
    private boolean hasSword;
    private ImageIcon normalIcon;
    private ImageIcon armedIcon;

    public Nephi() {
        //call superclass constructor
        super();
        //set the new image
        normalIcon = new ImageIcon("nephi.png");
        armedIcon = new ImageIcon("armednephi.png");
        //set everything to default
        reset();
    }

    public void pickUpSword() {
        //give Nephi the sword
        hasSword = true;
        image = armedIcon;
    }

    public boolean isArmed() {
        //does nephi have the sword?
        return hasSword;
    }

    public void reset() {
        //reset to normal
        image = normalIcon;
        hasSword = false;
    }
}
