import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Maze extends JPanel implements KeyListener {

    ArrayList<Room> maze;
    ArrayList<Drawable> objects;
    Nephi nephi;
    Plates plates;
    Sword sword;
    Laban laban;

    public Maze() {
        //set the objects arraylist
        this.objects = new ArrayList<>();
        //set the maze arraylist
        this.maze = new ArrayList<>();
        //set the sprites
        nephi = new Nephi();
        plates = new Plates();
        sword = new Sword();
        laban = new Laban();

        //add all sprites to the objects arraylist
        objects.add(nephi);
        objects.add(plates);
        objects.add(sword);
        objects.add(laban);

        //initialize keylistener?
        addKeyListener(this);

        //initialize the x and y values so there is space on the side
        int x = 10;
        int y = 10;

        //set the rooms up
        //columns
        for(int c=0; c<4;c++)
        {
            //rows
            for(int r=0; r<4; r++)
            {
                Room rm = new Room(x,y);
                //add the room to the maze arraylist
                maze.add(rm);
                //add each room to the objects arraylist
                objects.add(rm);
                //increase x by 60 (50px for box and 10 for space)
                x+=60;
            }
            //increase y by 60
            y+=60;
            //set x back to 10 to start the new column
            x=10;
        }

        //assign sprites to their rooms
        setSpriteRooms();

        //set up the actual maze (room exits)
        //room 0
        maze.get(0).setEastExit(maze.get(1));
        maze.get(0).setSouthExit(maze.get(4));
        //room 1
        maze.get(1).setSouthExit(maze.get(5));
        maze.get(1).setEastExit(maze.get(2));
        maze.get(1).setWestExit(maze.get(0));
        //room 2
        maze.get(2).setEastExit(maze.get(3));
        maze.get(2).setWestExit(maze.get(1));
        //room 3
        maze.get(3).setSouthExit(maze.get(7));
        maze.get(3).setWestExit(maze.get(2));
        //room 4
        maze.get(4).setNorthExit(maze.get(0));
        //room 5
        maze.get(5).setSouthExit(maze.get(9));
        maze.get(5).setNorthExit(maze.get(1));
        //room 6
        maze.get(6).setSouthExit(maze.get(10));
        //room 7
        maze.get(7).setSouthExit(maze.get(11));
        maze.get(7).setNorthExit(maze.get(3));
        //room 8
        maze.get(8).setSouthExit(maze.get(12));
        //room 9
        maze.get(9).setNorthExit(maze.get(5));
        //room 10
        maze.get(10).setEastExit(maze.get(11));
        maze.get(10).setNorthExit(maze.get(6));
        maze.get(10).setSouthExit(maze.get(14));
        //room 11
        maze.get(11).setSouthExit(maze.get(15));
        maze.get(11).setNorthExit(maze.get(7));
        maze.get(11).setWestExit(maze.get(10));
        //room 12
        maze.get(12).setEastExit(maze.get(13));
        maze.get(12).setNorthExit(maze.get(8));
        //room 13
        maze.get(13).setEastExit(maze.get(14));
        maze.get(13).setWestExit(maze.get(12));
        //room 14
        maze.get(14).setWestExit(maze.get(13));
        maze.get(14).setNorthExit(maze.get(10));
        //room 15
        maze.get(15).setNorthExit(maze.get(11));

    }

    //set the sprites to be in their starting rooms
    public void setSpriteRooms() {
        nephi.setCurrentRoom(maze.get(15));
        laban.setCurrentRoom(maze.get(5));
        plates.setCurrentRoom(maze.get(9));
        sword.setCurrentRoom(maze.get(8));
    }

    public static void main(String[] args) {
        //set the width and height
        int width = (4*50) + (5*10); //4 boxes of 50px, 5 spaces of 10px
        int height = (4*50) + (5*10) + 20; //the 20 here accounts for space at top taken by the closing bar

        //sets new window
        JFrame window = new JFrame();

        //closes the program when user closes window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //sets the size of the window
        window.setSize(width,height);

        //sets the content to be the pyramid code
        window.setContentPane(new Maze());

        //sets visibility
        window.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        //connect keylistener to keyboard
        requestFocusInWindow();

        //gets the current width and height
        var w = getWidth();
        var h = getHeight();

        //set the background
        g.setColor(Color.lightGray);
        g.fillRect(0,0,w,h);

        //for each object in objects arraylist, draw it
        for(Drawable r: objects)
        {
            r.draw(g);
        }
    }

    //
    @Override
    public void keyPressed(KeyEvent e) {
        //find out which key was pressed
        int key = e.getKeyCode();

        //NEPHI CONTROLS
        //move up
        if(key==KeyEvent.VK_UP)
        {
            nephi.moveNorth();
        }
        //move down
        if(key==KeyEvent.VK_DOWN)
        {
            nephi.moveSouth();
        }
        //move right
        if(key==KeyEvent.VK_RIGHT)
        {
            nephi.moveEast();
        }
        //move left
        if(key==KeyEvent.VK_LEFT)
        {
            nephi.moveWest();
        }

        //LABAN CONTROLS
        //move up
        if(key==KeyEvent.VK_W)
        {
            laban.moveNorth();
        }
        //move down
        if(key==KeyEvent.VK_S)
        {
            laban.moveSouth();
        }
        //move right
        if(key==KeyEvent.VK_D)
        {
            laban.moveEast();
        }
        //move left
        if(key==KeyEvent.VK_A)
        {
            laban.moveWest();
        }

        //check to see if Nephi can pick up the sword
        if(nephi.getCurrentRoom()==sword.getCurrentRoom())
        {
            nephi.pickUpSword();
            sword.currentRoom = null;
        }

        //check to see if Nephi found the plates
        if(nephi.getCurrentRoom()==plates.getCurrentRoom())
        {
            //tell nephi he won
            JOptionPane.showMessageDialog(null,"Congrats Nephi! You found the brass plates!");
            //reset the game
            reset();
        }

        //check to see if laban and nephi are together
        if(nephi.getCurrentRoom()==laban.getCurrentRoom()) {
            //check to see if nephi has the sword
            if(nephi.isArmed()) {
                //if he has the sword, make laban disappear
                laban.setCurrentRoom(null);
            } else {
                //nephi got killed by laban
                JOptionPane.showMessageDialog(null,"Nephi, you died! Try again");
                reset();
            }
        }

        //redraw the screen with the changes
        repaint();
    }

    private void reset() {
        //reset nephi back to not being armed
        nephi.reset();
        //reset sprites to be back in their original rooms
        setSpriteRooms();
    }

    //stubbed mandatory override methods from KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
