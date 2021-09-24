import javax.swing.*;
import java.awt.*;

public class Test extends JPanel{

    ImageIcon pickle;

    public static void main(String[] args) {
        //sets new window
        JFrame window = new JFrame();

        //closes the program when user closes window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //sets the size of the window
        window.setSize(800,380);

        //sets the content to be the pyramid code
        window.setContentPane(new Test());

        //sets visibility
        window.setVisible(true);
    }

    public Test(){
        pickle = new ImageIcon("nephi.png");
    }

    @Override
    public void paintComponent(Graphics g) {
        //gets the current width and height
        var w = getWidth();
        var h = getHeight();

        //set the background
        g.setColor(Color.cyan);
        g.fillRect(0,0,w,h);

        pickle.paintIcon(null,g,10,70);


    }
}
