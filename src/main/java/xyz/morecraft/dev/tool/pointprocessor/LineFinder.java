package xyz.morecraft.dev.tool.pointprocessor;

import xyz.morecraft.dev.tool.pointprocessor.model.Line;
import xyz.morecraft.dev.tool.pointprocessor.model.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LineFinder {

    private HashMap<Point, Boolean> pointsToProcess;
    private ArrayList<Point> standalonePoints;

    public LineFinder(HashMap<Point, Boolean> pointsToProcess) {
        this.pointsToProcess = pointsToProcess;
        this.standalonePoints = new ArrayList<>();
    }

    public LineFinder(ArrayList<Point> pointsToProcess) {
        this.pointsToProcess = new HashMap<>();
        this.standalonePoints = new ArrayList<>();
        for (Point p : pointsToProcess) {
            this.pointsToProcess.put(p, true);
        }
    }

    public Line getNext() {
        Line l = null;
        Point p = null;
        Point p2 = null;

        int[] q = new int[]{0, 0, 0, 0, 0, 0, 0, 0}; // 0N, 1E, 2S, 3W, 4NE, 5SE, 6SW, 7NW;
        int[] s = new int[]{0, 0, 0, 0}; // 0| 1- 2/ 3\

        Point[] m = new Point[]{null, null, null, null, null, null, null, null,};

        for (Map.Entry<Point, Boolean> entry : this.pointsToProcess.entrySet()) {
            if (entry.getValue()) {
                p = entry.getKey();
                break;
            }
        }

        if (p == null) {
            return null;
        }

        this.pointsToProcess.put(p, false);

        p2 = new Point();

        for (Direction d : Direction.values()) {
            if (p2 == null) {
                return null;
            }
            p2.x = p.x;
            p2.y = p.y;
            m[d.getValue()] = new Point(p2.x, p2.y);
            while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, d))) != null) {
                if (p2 == null) {
                    return null;
                }
                q[d.getValue()]++;
                m[d.getValue()] = new Point(p2.x, p2.y);
            }
        }

        s[0] = q[0] + q[2];
        s[1] = q[1] + q[3];
        s[2] = q[4] + q[6];
        s[3] = q[5] + q[7];

        int max = findKeyOfMax(s);

        if (s[max] == 0) {
            this.standalonePoints.add(p);
            return getNext();
        }

        switch (max) {
            case 0:
                l = new Line(m[0], m[2]);
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.N))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.S))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                break;
            case 1:
                l = new Line(m[1], m[3]);
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.W))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.E))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                break;
            case 2:
                l = new Line(m[4], m[6]);
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.NE))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.SW))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                break;
            case 3:
                l = new Line(m[5], m[7]);
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.NW))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                p2 = new Point(p.x, p.y);
                while (this.pointsToProcess.get((p2 = PointTranslation.transformed(p2, Direction.SE))) != null) {
                    this.pointsToProcess.put(p2, false);
                }
                break;
            default:
                break;
        }

        return l;
    }

    public ArrayList<Point> getStandalonePoints() {
        return this.standalonePoints;
    }

    public HashMap<Point, Boolean> getPointsToProcess() {
        return this.pointsToProcess;
    }

    private int findKeyOfMax(int[] a) {
        int k = 0;
        int s = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > k) {
                k = a[i];
                s = i;
            }
        }
        return s;
    }

}
