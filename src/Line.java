public class Line {
    private Point point1;
    private Point point2;

    public Line() {
        point1 = new Point();
        point2 = new Point();
    }

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }
    public void setPoint1(int x, int y) {
        point1.setX(x);
        point1.setY(y);
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public void setPoint2(int x, int y) {
        point2.setX(x);
        point2.setY(y);
    }

    public void setPoints(int x1, int y1, int x2, int y2) {
        this.point1.setX(x1);
        this.point1.setY(y1);
        this.point2.setX(x2);
        this.point2.setY(y2);
    }
}
