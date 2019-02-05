package lab2.level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;


public class Level extends Observable {
    private Room currentRoom;
    private ArrayList<Room> rooms = new ArrayList<>();
    private boolean allowNewRooms = true;


    public boolean place(Room r, int x, int y) {
        if (!allowNewRooms) {
            return false;
        }
        if (!isOverlapping(r, x, y)) {
            r.createRect(x, y);
            r.x = x;
            r.y = y;
            rooms.add(r);
            return true;
        }
        return false;
    }

    public void firstLocation(Room r) {
        allowNewRooms = false;
        currentRoom = r;
    }

    public int getMaxHeight() {
        int maxSoFar = 0;
        for (Room r : rooms) { // Loop through the rectangles and see whos Y-coord is max.
            if (r.rect.getMaxY() > maxSoFar) {
                maxSoFar = (int) r.rect.getMaxY();
            }
        }
        return maxSoFar;
    }

    public int getMaxWidth() {
        int maxSoFar = 0;
        for (Room r : rooms) {
            if (r.rect.getMaxX() > maxSoFar) {
                maxSoFar = (int) r.rect.getMaxX();
            }
        }
        return maxSoFar;
    }

    Room getCurrentRoom() {
        return currentRoom;
    }


    public void setCurrentRoom(Room newRoom) {
        if (newRoom == null) // The new room is not connected.
            return;
        if (getCurrentRoom().getRoomNorth() == newRoom || // If the new room is connected you are allowed to move.
                getCurrentRoom().getRoomSouth() == newRoom ||
                getCurrentRoom().getRoomEast() == newRoom ||
                getCurrentRoom().getRoomWest() == newRoom) {

            setChanged();
            notifyObservers(); // Notify others (in this case LevelGUI)
            currentRoom = newRoom;
            System.out.println("New room: " + newRoom.toString());
        } else {
            System.out.println("Did not move rome!!!");
        }
    }

    public void resetCurrentRoom() {
        setChanged();
        notifyObservers();
        currentRoom = rooms.get(0);
    }

    public ArrayList<Room> getRooms() { // remove public?
        return this.rooms;
    }


//    private boolean isOverlapping(Room r, int x, int y) {
//        for (Room room : rooms) {
//            if ((room.x < x + r.dx && x < room.x + room.dx) &&     // check x coordinates intersect.
//                    (y - r.dy < room.y && room.y - room.dy < y)) { // check y cord intersect.
//                return true; // The room overlapped with some other room!
//            }
//        }
//        return false;
//    }


    private boolean isOverlapping(Room r, int x, int y) {
        Rectangle r1 = new Rectangle(x, y, r.dx, r.dy);
        for (Room room : rooms) { // Loop through all rooms and see if any overlapp.
            Rectangle r2 = new Rectangle(room.x, room.y, room.dx, room.dy);
            if (r1.intersects(r2)) {
                return true;
            }
        }
        return false;
    }
}