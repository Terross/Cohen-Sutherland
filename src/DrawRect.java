import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;



public class DrawRect extends JComponent {
    private int x1 = 0;
    private int x2 = 0;
    private int y1 = 0;
    private int y2 = 0;
    private ArrayList<Line> lines;
    private boolean mod = false;
    private MouseListenerForObjects mouseListenerForObjects;
    private Algorithm algorithm;

    public DrawRect() {

        lines = new ArrayList<>();
        algorithm = new Algorithm(this);
        mouseListenerForObjects = new MouseListenerForRect(this);
        addMouseListener(mouseListenerForObjects);
        addMouseMotionListener(mouseListenerForObjects);
    }

    private void drawTrueRect(Graphics g) {
        int q1 = x2 - x1;
        int q2 = y2 - y1;
        if (q1 > 0 && q2 > 0) {
            g.drawRect(x1, y1, x2 - x1, y2 - y1);
        } else if (q1 < 0 && q2 < 0) {
            g.drawRect(x2, y2, x1 - x2, y1 - y2);
        } else if (q1 > 0 && q2 < 0) {
            g.drawRect(x1, y2, x2 - x1, y1 - y2);
        } else {
            g.drawRect(x2, y1, x1 - x2, y2 - y1);
        }
    }

    public void setStartCoordinates(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setEndCoordinates(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void setStartLineCoordinates(int x, int y, int index) {
        lines.get(index).setPoint1(x, y);
    }

    public void setEndLineCoordinates(int x, int y, int index) {
        lines.get(index).setPoint2(x, y);
    }

    public void setMod(boolean mod) {
        this.mod = mod;
        System.out.println("1");
        removeMouseListener(mouseListenerForObjects);
        removeMouseMotionListener(mouseListenerForObjects);
        mouseListenerForObjects = new MouseListenerForLines(this);
        addMouseListener(mouseListenerForObjects);
        addMouseMotionListener(mouseListenerForObjects);
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    private boolean checkIntersect(Line line) {
        if (line.getPoint1().getX() == line.getPoint2().getX() &&
                (line.getPoint1().getX() == x1 || line.getPoint1().getX() == x2)) {
            return true;
        } else if (line.getPoint1().getY() == line.getPoint2().getY() &&
                (line.getPoint1().getY() == y1 || line.getPoint1().getY() == y2)) {
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTrueRect(g);

        if(mod) {
            Line line;
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i);
                int lineClass = algorithm.getClassLine(line);
                switch (lineClass) {
                    case 0:
                        g.setColor(Color.RED);
                        g.drawLine(line.getPoint1().getX(),line.getPoint1().getY(),
                                line.getPoint2().getX(),line.getPoint2().getY());
                        break;
                    case 1:
                        g.setColor(Color.BLUE);

                        Line subLine = algorithm.findSubLine(line);

                        g.drawLine(line.getPoint1().getX(),line.getPoint1().getY(),
                                subLine.getPoint1().getX(),subLine.getPoint1().getY());
                        g.setColor(Color.BLUE);
                        g.drawLine(subLine.getPoint2().getX(),subLine.getPoint2().getY() ,
                                line.getPoint2().getX(),line.getPoint2().getY());
                        if (checkIntersect(subLine)) {
                            g.setColor(Color.GREEN);
                        } else {
                            g.setColor(Color.RED);
                        }
                        g.drawLine(subLine.getPoint1().getX(),subLine.getPoint1().getY(),
                                subLine.getPoint2().getX(),subLine.getPoint2().getY());



                        g.setColor(Color.GREEN);
                        if (subLine.getPoint1().getX() == x1 || subLine.getPoint1().getX() == x2 ||
                            subLine.getPoint1().getY() == y1 || subLine.getPoint1().getY() == y2) {
                            if (subLine.getPoint1().getX() <= x2 && subLine.getPoint1().getX() >= x1) {
                                g.fillOval(subLine.getPoint1().getX() - 2,subLine.getPoint1().getY() - 2,6,6);
                            }

                        }
                        if (subLine.getPoint2().getX() == x1 || subLine.getPoint2().getX() == x2 ||
                        subLine.getPoint2().getY() == y1 || subLine.getPoint2().getY() == y2) {

                            if (subLine.getPoint2().getX() <= x2 && subLine.getPoint2().getX() >= x1) {
                                g.fillOval(subLine.getPoint2().getX() - 2,subLine.getPoint2().getY() - 2,6,6);
                            }
                        }



                        break;
                    case 2:
                        g.setColor(Color.BLUE);
                        g.drawLine(line.getPoint1().getX(),line.getPoint1().getY(),
                                line.getPoint2().getX(),line.getPoint2().getY());
                        break;
                }

            }

        }
    }
}
