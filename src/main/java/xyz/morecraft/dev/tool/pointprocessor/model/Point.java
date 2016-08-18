package xyz.morecraft.dev.tool.pointprocessor.model;

/**
 * This class represents point in Cartesian coordinate system.
 */
public class Point {

    /**
     * <em>Abscissa</em>
     */
    public int x;
    /**
     * <em>Ordinate</em>
     */
    public int y;

    /**
     * Create new {@link xyz.morecraft.dev.tool.pointprocessor.model.Point} with <em>abscissa</em>
     * (x) and <em>ordinate</em> (y) set to 0.
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Create new {@link xyz.morecraft.dev.tool.pointprocessor.model.Point} with provided
     * <em>abscissa</em> (x) and <em>ordinate</em> (y).
     *
     * @param x <em>abscissa</em>
     * @param y <em>ordinate</em>
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns <em>abscissa</em> of this {@link xyz.morecraft.dev.tool.pointprocessor.model.Point}.
     *
     * @return x <em>abscissa</em>
     */
    public int getX() {
        return x;
    }

    /**
     * Sets <em>abscissa</em> of this {@link xyz.morecraft.dev.tool.pointprocessor.model.Point}.
     *
     * @param x <em>abscissa</em>
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns <em>ordinate</em> of this {@link xyz.morecraft.dev.tool.pointprocessor.model.Point}.
     *
     * @return y <em>ordinate</em>
     */
    public int getY() {
        return y;
    }

    /**
     * Sets <em>ordinate</em> of this {@link xyz.morecraft.dev.tool.pointprocessor.model.Point}.
     *
     * @param y <em>ordinate</em>
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns {@link java.lang.String} representing this {@link xyz.morecraft.dev.tool.pointprocessor.model.Point}
     * in format <em>[x=XX,y=YY]</em>.
     *
     * @return {@link java.lang.String} in format <em>[x=XX,y=YY]</em>
     */
    public String toString() {
        return "[x=" + x + ",y=" + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}
