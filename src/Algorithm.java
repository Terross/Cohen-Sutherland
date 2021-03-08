public class Algorithm {
    DrawRect drawRect;
    public Algorithm(DrawRect drawRect) {
        this.drawRect = drawRect;

    }

    private int getPointCode(Point point) {
        int pointCode = 0;

        if (point.getX() < drawRect.getX1()) {
            pointCode += 1;
        }
        if (point.getX() > drawRect.getX2()) {
            pointCode += 2;
        }
        if (point.getY() < drawRect.getY1()) {
            pointCode += 8;
        }
        if (point.getY() > drawRect.getY2()) {
            pointCode += 4;
        }
        return pointCode;
    }
    public Line findSubLine(Line line) {
        Line result = new Line(line.getPoint1(), line.getPoint2());
        Point point;
        Point point1 = line.getPoint1();
        Point point2 = line.getPoint2();
        int code1 = getPointCode(point1);
        int code2 = getPointCode(point2);
        int code;
        while ((code1 | code2) != 0) {
            if (code1 == 0 && code2 == 0) {
                return result; //Внутри
            }

            if (code1 != 0) {
                point = new Point(point1.getX(), point1.getY());
                code = code1;
            } else {
                point = new Point(point2.getX(), point2.getY());
                code = code2;
            }

            if (point.getX() < drawRect.getX1()) {
                point.setY(point.getY() + (point1.getY() - point2.getY()) * (drawRect.getX1() - point.getX())
                        / (point1.getX() - point2.getX()));
                point.setX(drawRect.getX1());
            }
            if (point.getX() > drawRect.getX2()) {
                point.setY( point.getY() +(point1.getY() - point2.getY()) * (drawRect.getX2() - point.getX())
                        / (point1.getX() - point2.getX()));
                point.setX(drawRect.getX2());
            }
            if (point.getY() < drawRect.getY1()) {
                point.setX( point.getX() + (point1.getX() - point2.getX()) * (drawRect.getY1() - point.getY())
                        / (point1.getY() - point2.getY()));
                point.setY(drawRect.getY1());

            }
            if (point.getY() > drawRect.getY2()) {
                point.setX(point.getX() +(point1.getX() - point2.getX()) * (drawRect.getY2() - point.getY())
                        / (point1.getY() - point2.getY()));
                point.setY(drawRect.getY2());
            }

            if (code == code1) {
                result.setPoint1(point);
                code1 = 0;getPointCode(result.getPoint1());
            } else {
                result.setPoint2(point);
                code2 = 0;getPointCode(result.getPoint2());
            }
        }

        return result;
    }
    public int getClassLine(Line line) {
        int lineClass = 0;
        int firstPointCode = getPointCode(line.getPoint1());
        int secondPointCode = getPointCode(line.getPoint2());

        if (firstPointCode == 0 && secondPointCode == 0) {
            lineClass = 0; //Внутри
        } else if ((firstPointCode & secondPointCode) == 0) {
            lineClass = 1; //Пересекает
        } else {
            lineClass = 2; //Не пересекает
        }
        return lineClass;
    }
}
