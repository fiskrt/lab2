package lab2;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

import java.awt.*;
import java.util.Random;

public class Driver {
    private Random rand = new Random();

    private int getRandInt(final int lower, final int upper, final int exclude) {
        int randNum = 0;
        while ((randNum = rand.nextInt(upper - lower) + lower) == exclude) {
        }
        return randNum;
    }

    public void run() {

        Level level = new Level();

        Room room1 = new Room(50, 50, Color.GREEN); // north
        Room room2 = new Room(50, 50, Color.GREEN); // north
        Room room3 = new Room(80, 50, Color.BLUE);  // south
        Room room4 = new Room(50, 50, Color.RED);
        Room room5 = new Room(50, 50, Color.MAGENTA);

        Room room6 = new Room(50, 50, Color.PINK);


        Room room7 = new Room(90, 50, Color.GREEN); // north
        Room room8 = new Room(50, 50, Color.GREEN); // north
        Room room9 = new Room(50, 50, Color.BLUE);  // south
        Room room10 = new Room(50, 50, Color.RED);
        Room room11 = new Room(50, 50, Color.MAGENTA);


        level.place(room1, 100, 100);
        level.place(room2, 100, 10);
        level.place(room3, 100, 200);
        level.place(room4, 200, 100);
        level.place(room5, 10, 100);

        level.place(room7, 300, 100);
        level.place(room8, 300, 300);
        level.place(room9, 100, 350);
        level.place(room10, 500, 700);
        level.place(room11, 400, 100);

        level.place(room6, 300, 300);
        room6.connectNorthTo(room3);

        room3.connectSouthTo(room6);
        room9.connectEastTo(room8);
        room3.connectSouthTo(room9);
        room9.connectNorthTo(room4);
        room8.connectNorthTo(room4);
        room4.connectWestTo(room1);

        room7.connectEastTo(room10);

        room3.connectNorthTo(room1);

        room1.connectNorthTo(room2);
        room1.connectSouthTo(room3);
        room1.connectEastTo(room4);
        room1.connectWestTo(room5);

        room3.connectSouthTo(room6);

        room6.connectEastTo(room11);

        level.firstLocation(room3);

        new LevelGUI(level, "co.ol", level.getMaxWidth(), level.getMaxHeight());
    }

}