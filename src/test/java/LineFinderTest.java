
import org.junit.Test;
import xyz.morecraft.dev.tool.pointprocessor.LineFinder;
import xyz.morecraft.dev.tool.pointprocessor.model.Line;
import xyz.morecraft.dev.tool.pointprocessor.model.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class LineFinderTest {

    @Test
    public void basicTest() {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(1, 2));
        points.add(new Point(1, 1));
        points.add(new Point(3, 4));
        points.add(new Point(4, 5));
        points.add(new Point(5, 6));
        points.add(new Point(6, 7));
        points.add(new Point(7, 8));
        points.add(new Point(1, 8));
        points.add(new Point(2, 7));
        points.add(new Point(3, 6));
        points.add(new Point(5, 4));
        points.add(new Point(6, 3));
        points.add(new Point(3, 8));
        points.add(new Point(1, 6));
        points.add(new Point(0, 5));
        points.add(new Point(1, 4));
        points.add(new Point(3, 2));
        points.add(new Point(4, 1));
        points.add(new Point(5, 0));
        points.add(new Point(8, 5));
        points.add(new Point(8, 3));
        points.add(new Point(8, 4));

        LineFinder lf = new LineFinder(points);
        List<Line> lineList = new ArrayList<>();
        Line line;

        while ((line = lf.getNext()) != null) {
            lineList.add(line);
        }

        lineList.remove(new Line(1, 2, 1, 1));
        lineList.remove(new Line(3, 4, 7, 8));
        lineList.remove(new Line(1, 8, 6, 3));
        lineList.remove(new Line(0, 5, 1, 4));
        lineList.remove(new Line(0, 5, 3, 8));
        lineList.remove(new Line(3, 2, 5, 0));
        lineList.remove(new Line(8, 5, 8, 3));

        assertTrue(lineList.isEmpty());
    }

}
