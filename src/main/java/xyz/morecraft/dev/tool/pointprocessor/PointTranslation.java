package xyz.morecraft.dev.tool.pointprocessor;

import xyz.morecraft.dev.tool.pointprocessor.model.Point;

public class PointTranslation {

    public static void moveN(Point p) {
        p.y++;
        // return p;
    }

    public static void moveS(Point p) {
        p.y--;
        // return p;
    }

    public static void moveW(Point p) {
        p.x--;
        // return p;
    }

    public static void moveE(Point p) {
        p.x++;
        // return p;
    }

    public static void moveNE(Point p) {
        moveN(p);
        moveE(p);
        // return p;
    }

    public static void moveNW(Point p) {
        moveN(p);
        moveW(p);
        // return p;
    }

    public static void moveSE(Point p) {
        moveS(p);
        moveE(p);
        // return p;
    }

    public static void moveSW(Point p) {
        moveS(p);
        moveW(p);
        // return p;
    }

    public static void move(Point p, Direction d) {
        switch (d) {
            case E:
                moveE(p);
                break;
            case N:
                moveN(p);
                break;
            case NE:
                moveNE(p);
                break;
            case NW:
                moveNW(p);
                break;
            case S:
                moveS(p);
                break;
            case SE:
                moveSE(p);
                break;
            case SW:
                moveSW(p);
                break;
            case W:
                moveW(p);
                break;
            default:
                break;
        }

    }

    public static Point transformedN(Point p) {
        return new Point(p.x, p.y + 1);
    }

    public static Point transformedS(Point p) {
        return new Point(p.x, p.y - 1);
    }

    public static Point transformedW(Point p) {
        return new Point(p.x - 1, p.y);
    }

    public static Point transformedE(Point p) {
        return new Point(p.x + 1, p.y);
    }

    public static Point transformedNE(Point p) {
        return transformedE(transformedN(p));
    }

    public static Point transformedNW(Point p) {
        return transformedW(transformedN(p));
    }

    public static Point transformedSE(Point p) {
        return transformedE(transformedS(p));
    }

    public static Point transformedSW(Point p) {
        return transformedW(transformedS(p));
    }

    public static Point transformed(Point p, Direction d) {
        switch (d) {
            case E:
                return transformedE(p);
            case N:
                return transformedN(p);
            case NE:
                return transformedNE(p);
            case NW:
                return transformedNW(p);
            case S:
                return transformedS(p);
            case SE:
                return transformedSE(p);
            case SW:
                return transformedSW(p);
            case W:
                return transformedW(p);
            default:
                return null;
        }

    }

    public static Point translatePoint(Point p, int translateX, int translateY) {
        return new Point(p.x + translateX, p.y + translateY);
    }

    public static void movePoint(Point p, int translateX, int translateY) {
        p.x += translateX;
        p.y += translateY;
    }

}
