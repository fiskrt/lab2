package lab2.level;


import java.awt.*;


public class Room {
    Color color;
    int x, y, dx, dy;
    Rectangle rect;
    private Room roomNorth, roomSouth, roomEast, roomWest;


    public Room(int dx, int dy, Color color) {
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    @Override
    public String toString() {
        return "P(" + x + ", " + y + ")" + " D(" + dx + "x" + dy + ") Col: " + color.toString();
    }

    public void createRect(final int x, final int y) {
        rect = new Rectangle(x, y, dx, dy);
    }

    public void connectNorthTo(Room r) {
        if (r.y <= y) { // If the new room is a bit north of current.
            roomNorth = r;
        }
    }

    public void connectEastTo(Room r) {
        if (r.x + r.dx >= x + dx) {
            roomEast = r;
        }
    }

    public void connectSouthTo(Room r) {
        if (r.y > y && r.y + r.dy > y + dy) {
            roomSouth = r;
        }
    }

    public void connectWestTo(Room r) {
        if (r.x <= x) {
            roomWest = r;
        }
    }

    Room getRoomNorth() {
        return roomNorth;
    }

    Room getRoomSouth() {
        return roomSouth;
    }

    Room getRoomEast() {
        return roomEast;
    }

    Room getRoomWest() {
        return roomWest;
    }
}