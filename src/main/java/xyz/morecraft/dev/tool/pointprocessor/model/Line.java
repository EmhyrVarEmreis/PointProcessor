package xyz.morecraft.dev.tool.pointprocessor.model;

import java.util.ArrayList;

public class Line {

    private Point startingPoint;
    private Point endingPoint;
    private double direction;
    private boolean isHorizontal = false;
    private boolean isGrowing;
    private double adder;
    private boolean changed;

    public Line() {
        this(new Point(0, 0), new Point(0, 0));
    }

    public Line(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Line(Point startingPoint, Point endingPoint) {
        this.startingPoint = (startingPoint.x <= endingPoint.x) ? new Point(startingPoint.x, startingPoint.y) : new Point(endingPoint.x, endingPoint.y);
        this.endingPoint = (startingPoint.x > endingPoint.x) ? new Point(startingPoint.x, startingPoint.y) : new Point(endingPoint.x, endingPoint.y);
        reCalc();
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        changed = true;
        this.startingPoint = startingPoint;
    }

    public Point getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Point endingPoint) {
        changed = true;
        this.endingPoint = endingPoint;
    }

    public double getDirection() {
        if (changed) {
            reCalc();
        }
        return direction;
    }

    public double getAdder() {
        if (changed) {
            reCalc();
        }
        return adder;
    }

    public ArrayList<Point> getPoints() {
        Point p1 = new Point(startingPoint.x, startingPoint.y);
        ArrayList<Point> pl = new ArrayList<>();
        pl.add(new Point(p1.x, p1.y));

        if (isHorizontal) {
            while (p1.y != endingPoint.y) {
                p1.y = (isGrowing) ? p1.y + 1 : p1.y - 1;
                pl.add(new Point(p1.x, p1.y));
            }
            return pl;
        } else if (direction == 0) {
            while (p1.x != endingPoint.x) {
                p1.x++;
                pl.add(new Point(p1.x, p1.y));
            }
            return pl;
        } else {
            while (p1.x < endingPoint.x) {
                p1.x++;
                p1.y = (int) Math.round(p1.x * getDirection() + getAdder());
                pl.add(new Point(p1.x, p1.y));
            }
            return pl;
        }
    }

    private void reCalc() {
        direction = 0;
        isHorizontal = false;
        if ((endingPoint.x - startingPoint.x) == 0) {
            isHorizontal = true;
        } else {
            direction = (endingPoint.y - startingPoint.y) / (endingPoint.x - startingPoint.x);
        }
        adder = (isHorizontal) ? 0 : startingPoint.y - direction * startingPoint.x;
        isGrowing = startingPoint.y < endingPoint.y;
        changed = false;
    }

    @Override
    public String toString() {
        if (startingPoint == null || endingPoint == null) {
            return "NULL";
        }
        return "[(" + startingPoint.x + "," + startingPoint.y + ")" + ",(" + endingPoint.x + "," + endingPoint.y + ")]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Line line = (Line) o;

        return (startingPoint != null ? startingPoint.equals(line.startingPoint) : line.startingPoint == null && (endingPoint != null ? endingPoint.equals(line.endingPoint) : line.endingPoint == null)) || (endingPoint != null ? endingPoint.equals(line.endingPoint) : line.endingPoint == null && (startingPoint != null ? startingPoint.equals(line.startingPoint) : line.startingPoint == null));
    }

    @Override
    public int hashCode() {
        int result = startingPoint != null ? startingPoint.hashCode() : 0;
        result = 31 * result + (endingPoint != null ? endingPoint.hashCode() : 0);
        return result;
    }

}
