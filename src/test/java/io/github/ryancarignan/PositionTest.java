package io.github.ryancarignan;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit test for Position.
 */
public class PositionTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }    

    /**
     * Testing positions constructor, fields, and methods
     */
    @Test
    public void positionTests() {
        // zeros
        Position pos1 = new Position(0, 0);
        assertTrue(pos1 instanceof Position);
        assertTrue(pos1.x == 0);
        assertTrue(pos1.y == 0);

        // positives
        Position pos2 = new Position(1, 2);
        assertTrue(pos2 instanceof Position);
        assertTrue(pos2.x == 1);
        assertTrue(pos2.y == 2);

        // negatives
        Position pos3 = new Position(-1, -2);
        assertTrue(pos3 instanceof Position);
        assertTrue(pos3.x == -1);
        assertTrue(pos3.y == -2);

        // equals
        Position pos4 = new Position(4, -2);
        Position pos5 = new Position(4, -2);
        Position pos6 = new Position(3, -2);
        Position pos7 = new Position(4, 2);
        // same
        assertTrue(pos4.equals(pos5));
        // different
        assertFalse(pos5.equals(pos6));
        assertFalse(pos5.equals(pos7));

        // sum
        Position pos8 = new Position(1, 2);
        Position pos9 = new Position(9, -15);
        assertTrue(Position.sum(pos8, pos9).x == 10);
        assertTrue(Position.sum(pos8, pos9).y == -13);
    }
}
