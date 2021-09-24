import java.awt.*;

public class Room implements Drawable {

    public static final int SIZE = 50;
    private Point pos;
    private Room exitEast;
    private Room exitWest;
    private Room exitNorth;
    private Room exitSouth;

    public Room(int x, int y){
        this.pos = new Point(x,y);
        exitWest=null;
        exitEast=null;
        exitNorth=null;
        exitSouth=null;
    }

    //set the exits of the rooms
    public void setEastExit(Room r){
        exitEast = r;
        r.exitWest = this;
    }

    public void setNorthExit(Room r){
        exitNorth = r;
        r.exitSouth = this;
    }

    public void setWestExit(Room r){
        exitWest = r;
        r.exitEast = this;
    }

    public void setSouthExit(Room r){
        exitSouth = r;
        r.exitNorth = this;
    }

    //check to see if rooms have exit in certain directions
    public boolean hasNorthExit(){
        if(exitNorth==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean hasSouthExit(){
        if(exitSouth==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean hasWestExit(){
        if(exitWest==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean hasEastExit(){
        if(exitEast==null){
            return false;
        }else{
            return true;
        }
    }

    //get the exits of the current room
    public Room getNorthExit(){
        return exitNorth;
    }

    public Room getSouthExit(){
        return exitSouth;
    }

    public Room getWestExit(){
        return exitWest;
    }

    public Room getEastExit(){
        return exitEast;
    }

    //returns the position of the room
    public Point getPosition(){
        return pos;
    }

    //draws the room
    public void draw(Graphics g){

        int xStart = (int)pos.getX();
        int yStart = (int)pos.getY();

        //draws the room
        g.setColor(Color.black);
        g.drawRect(xStart, yStart,SIZE,SIZE);

        //draw the lines to open the entrances for the maze
        g.setColor(Color.lightGray);
        if(exitEast!=null)
        {
            //draw new lines for hallway
            g.setColor(Color.black);
            g.drawRect(xStart+SIZE,yStart+15,10,20);

            //cancel out line blocking path
            g.setColor(Color.lightGray);
            g.drawLine((xStart+SIZE),(yStart+15),(xStart+SIZE),(yStart+35));
        }
        if(exitWest!=null)
        {
            //cancel out line blocking path
            g.setColor(Color.lightGray);
            g.drawLine((xStart),(yStart+35),(xStart),(yStart+15));
        }
        if(exitNorth!=null)
        {
            //cancel out line blocking path
            g.setColor(Color.lightGray);
            g.drawLine((xStart+15),(yStart),(xStart+35),(yStart));
        }
        if(exitSouth!=null)
        {
            //draw new lines for hallway
            g.setColor(Color.black);
            g.drawRect(xStart+15,yStart+SIZE,20,10);

            //cancel out line blocking path
            g.setColor(Color.lightGray);
            g.drawLine((xStart+15),(yStart+SIZE),(xStart+35),(yStart+SIZE));

        }

    }

}
