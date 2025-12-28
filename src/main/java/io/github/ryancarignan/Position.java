package io.github.ryancarignan;

public class Position {
    public int x; // x (i) component of the position vector
    public int y; // y (j) component of the position vector

    /**
     * Represents a position as a vector (either absolute from top-left of screen or relative to another Position)
     * @param x the x (i) component of the position vector
     * @param y the y (j) component of the position vector
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position otherPos) {
        boolean sameX = this.x == otherPos.x;
        boolean sameY = this.y == otherPos.y;
        return sameX && sameY;
    }

    /**
     * Returns a position that is the vector sum of the given positions
     * @param positions the positions to sum
     * @return a new Position, being the vector sum of the given
     */
    public static Position sum(Position... positions) {
        int newX = 0;
        int newY = 0;
        for (Position pos : positions) {
            newX += pos.x;
            newY += pos.y;
        }
        return new Position(newX, newY);
    }
}
