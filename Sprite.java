import javax.swing.*;
import java.awt.*;

public abstract class Sprite implements Drawable{
    protected Room currentRoom;
    protected ImageIcon image;

    //set current room and image to null
    public Sprite() {
        currentRoom = null;
        image = null;
    }

    //set current room
    public void setCurrentRoom(Room r){
        currentRoom = r;
    }

    //return current room
    public Room getCurrentRoom(){
        return currentRoom;
    }

    //move down
    public void moveSouth(){
        if(currentRoom.hasSouthExit())
        {
            currentRoom = currentRoom.getSouthExit();
        }
    }

    //move up
    public void moveNorth(){
        if(currentRoom.hasNorthExit())
        {
            currentRoom = currentRoom.getNorthExit();
        }
    }

    //move right
    public void moveEast(){
        if(currentRoom.hasEastExit())
        {
            currentRoom = currentRoom.getEastExit();
        }
    }

    //move left
    public void moveWest(){
        if(currentRoom.hasWestExit())
        {
            currentRoom = currentRoom.getWestExit();
        }
    }

    //draw the sprite
    public void draw(Graphics g){
        if(currentRoom!=null)
        {
            //find the X and Y positions of the current room
            int x = (int)currentRoom.getPosition().getX();
            int y = (int)currentRoom.getPosition().getY();

            //paint the sprite
            //each sprite is 35/35px and the rooms are 50/50px
            image.paintIcon(null, g, x+8, y+8);

        }
    }

}
