package io.github.ryancarignan;

import java.util.Random;

public class Position {
    public int x; // x (i) component of the position vector
    public int y; // y (j) component of the position vector

    public static final Position  LEFT = new Position(-1, 0);
    public static final Position RIGHT = new Position(1, 0);
    public static final Position    UP = new Position(0, 1);
    public static final Position  DOWN = new Position(0, -1);

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

    /**
     * Returns a random position that is not equal to the given positions and no greater than the given bounds
     * @param width the max width that the returned position shoud be less than
     * @param height the max height that the returned position should be less than
     * @param positions the positions that the new random position should be unique to
     * @return a new Position, being random and not equal to those given
     */
    public static Position findRandomUnique(int width, int height, Position... positions) {
        Random random = new Random(System.currentTimeMillis());
        Position newPosition;
        
        do {
            int newX = random.nextInt(0, width - 1);
            int newY = random.nextInt(0, height - 1);
            newPosition = new Position(newX, newY);
        } while (newPosition.isIn(positions));

        return newPosition;
    }

    /**
     * Returns if the position is equal to any of the given positions
     * @param positions the positions to check against
     * @return if the position is equal to any of the given positions
     */
    private boolean isIn(Position... positions) {
        for (Position other : positions) {
            if (this.equals(other)) return true;
        }
        return false;
    }
}
