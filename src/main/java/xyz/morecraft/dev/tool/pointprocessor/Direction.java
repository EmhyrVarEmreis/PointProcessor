package xyz.morecraft.dev.tool.pointprocessor;

public enum Direction {

    N(0), E(1), S(2), W(3), NE(4), SE(5), SW(6), NW(7);

    private final int value;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}