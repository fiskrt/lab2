package lab2.level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;


public class LevelGUI implements Observer {

    private Level lv;
    private Display d;

    public LevelGUI(Level level, String name, int x, int y) {
        this.lv = level;

        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lv.addObserver(this); // Add this instance of the class as an observer of Level.


        d = new Display(lv, x, y);
        frame.getContentPane().add(d);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center screen-location on startup.
        frame.setVisible(true);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        d.repaint();
    }

    private class Display extends JPanel {
        private Level fp;

        public Display(Level fp, int x, int y) {
            this.fp = fp;
            addKeyListener(new Listener());
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(x + 20, y + 20));
            setFocusable(true);
        }

        private void drawLines(Graphics g, Room r) {
            if (r.getRoomNorth() != null) { // if there is a room in the north direction, draw a line to it.
                g.drawLine(
                        (int) r.rect.getCenterX(),
                        (int) (r.rect.getY()),
                        (int) r.getRoomNorth().rect.getCenterX(),
                        (int) (r.getRoomNorth().rect.getY() + r.getRoomNorth().rect.getHeight()));
            }
            if (r.getRoomSouth() != null) {
                g.drawLine(
                        (int) r.rect.getCenterX(),
                        (int) (r.rect.getY() + r.rect.getHeight()),
                        (int) r.getRoomSouth().rect.getCenterX(),
                        (int) r.getRoomSouth().rect.getY());
            }
            if (r.getRoomEast() != null) {
                g.drawLine(
                        (int) (r.rect.getX() + r.rect.getWidth()),
                        (int) r.rect.getCenterY(),
                        (int) r.getRoomEast().rect.getX(),
                        (int) r.getRoomEast().rect.getCenterY());
            }
            if (r.getRoomWest() != null) {
                g.drawLine(
                        (int) r.rect.getX(),
                        (int) r.rect.getCenterY(),
                        (int) (r.getRoomWest().rect.getX() + r.getRoomWest().rect.getWidth()),
                        (int) r.getRoomWest().rect.getCenterY());
            }
        }

        private void drawRoom(Graphics g, Room r) {
            g.setColor(r.color); // set color to the floors color.
            ((Graphics2D) g).fill(r.rect);
            g.setColor(Color.BLACK); // set color to default again.
        }

        private void drawAllowedLines(Graphics g, Room r) {
            Stroke oldStroke = ((Graphics2D) g).getStroke();
            ((Graphics2D) g).setStroke(new BasicStroke(4)); // Increase stroke size.
            g.setColor(Color.GREEN);

            drawLines(g, r);

            ((Graphics2D) g).setStroke(oldStroke); // Reset pen size.
            g.setColor(Color.BLACK); // Reset color.
        }

        private void drawBorder(Graphics g, Room r) {
            Stroke oldStroke = ((Graphics2D) g).getStroke();
            ((Graphics2D) g).setStroke(new BasicStroke(4)); // Increase stroke size.
            g.setColor(Color.CYAN); // Set border color to cyan.

            ((Graphics2D) g).draw(r.rect);
            ((Graphics2D) g).setStroke(oldStroke); // Reset pen size.
            g.setColor(Color.BLACK); // Reset color.
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Room r : fp.getRooms()) {
                if (r != fp.getCurrentRoom()) { // We will draw the current room lines after.
                    drawLines(g, r);
                }
            }

            drawAllowedLines(g, fp.getCurrentRoom()); // Draw the green lines where you are allowed to move.

            for (Room r : fp.getRooms()) {
                drawRoom(g, r);
            }

            drawBorder(g, lv.getCurrentRoom()); // Finally draw a border around the current room.
        }


        private class Listener implements KeyListener {

            @Override
            public void keyPressed(KeyEvent arg0) {
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                switch (Character.toLowerCase(e.getKeyChar())) {
                    case 'w': // If you press 'w' key aka UP.
                        lv.setCurrentRoom(lv.getCurrentRoom().getRoomNorth()); // Set current room to the northern one.
                        break;
                    case 's':
                        lv.setCurrentRoom(lv.getCurrentRoom().getRoomSouth());
                        break;
                    case 'a':
                        lv.setCurrentRoom(lv.getCurrentRoom().getRoomWest());
                        break;
                    case 'd':
                        lv.setCurrentRoom(lv.getCurrentRoom().getRoomEast());
                        break;
                    case 'r':
                        lv.resetCurrentRoom();
                }
            }
        }
    }
}