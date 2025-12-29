package io.github.ryancarignan;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.io.IOException;

import java.util.LinkedList;

/**
 * Unit test for Display.
 */
public class DisplayTest 
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
     * Visual test for Display creation and start screen
     */
    @Test
    public void startScreen() {
        Display display = null;
        try {
            display = new Display();
            Thread.sleep(2500); // allow time to look at it
        } catch (IOException ioe) {
            fail();
        } catch (InterruptedException ie) {
            System.out.println("Test failure while sleeping");
            ie.printStackTrace();
            fail();
        } finally {
            if (display != null) {
                display.close();
            }
        }

    }

    /**
     * Visual test of drawing a snake
     */
    @Test
    public void drawSnake() {
        Display display = null;
        try {
            display = new Display();

            LinkedList<Position> snake = new LinkedList<>();
            snake.add(new Position(5,5));
            snake.add(new Position(5,6));
            snake.add(new Position(5,7));
            snake.add(new Position(6,5));
            snake.add(new Position(7,5));

            display.drawSnake(snake);

            Thread.sleep(2500);
        } catch (IOException ioe) {
            fail();
        } catch (InterruptedException ie) {
            System.out.println("Test failure while sleeping");
            ie.printStackTrace();
            fail();
        } finally {
            if (display != null) {
                display.close();
            }
        }
    }

    /**
     * Visual test of drawing apples
     */
    @Test
    public void drawApples() {
        Display display = null;
        try {
            display = new Display();

            Position apple1 = new Position(0, 0);
            display.drawApple(apple1);
            Thread.sleep(1000);
            Position apple2 = new Position(2, 2);
            display.drawApple(apple2);
            Thread.sleep(1000);
            Position apple3 = new Position(3, 1);
            display.drawApple(apple3);
            Thread.sleep(1000);
            Position apple4 = new Position(5, 5);
            display.drawApple(apple4);
            Thread.sleep(1000);
            Position apple5 = new Position(10, 15);
            display.drawApple(apple5);
            Thread.sleep(1000);
        } catch (IOException ioe) {
            fail();
        } catch (InterruptedException ie) {
            System.out.println("Test failure while sleeping");
            ie.printStackTrace();
            fail();
        } finally {
            if (display != null) {
                display.close();
            }
        }
    }

    /**
     * Visual test of erasing (drawing background)
     */
    @Test
    public void drawBackground() {
        Display display = null; 
        try {
            display = new Display();

            Position apple1 = new Position(0, 0);
            Position apple2 = new Position(3, 4);
            LinkedList<Position> snake = new LinkedList<>();
            snake.add(new Position(5, 5));
            snake.add(new Position(5,6));

            display.drawApple(apple1);
            display.drawApple(apple2);
            display.drawSnake(snake);

            Thread.sleep(1000);

            display.drawBackground(apple2);
            Thread.sleep(1000);

            display.drawBackground(snake.get(1));
            Thread.sleep(1000);
        } catch (IOException ioe) {
            fail();
        } catch (InterruptedException ie) {
            System.out.println("Test failure while sleeping");
            ie.printStackTrace();
            fail();
        } finally {
            if (display != null) {
                display.close();
            }
        }
    }

    /**
     * Visual test of writing the score to the screen
     */
    @Test
    public void writeScore() {
        Display display = null;
        try {
            display = new Display();

            Thread.sleep(1000);

            display.writeScore(0);
            Thread.sleep(1000);

            display.writeScore(1);
            Thread.sleep(1000);

            display.writeScore(25);
            Thread.sleep(1000);

            display.writeScore(3);
            Thread.sleep(1000);
        } catch (IOException ioe) {
            fail();
        } catch (InterruptedException ie) {
            System.out.println("Test failure while sleeping");
            ie.printStackTrace();
            fail();
        } finally {
            if (display != null) {
                display.close();
            }
        }
    }

    /**
     * Visual test of writing the "Game Over" message to the screen
     */
    @Test
    public void writeGameOver() {
        Display display = null;
        try {
            display = new Display();

            // put other things on the screen first
            Position apple1 = new Position(15, 20);
            Position apple2 = new Position(3, 5);
            LinkedList<Position> snake = new LinkedList<>();
            snake.add(new Position(18, 7));
            snake.add(new Position(18, 8));
            snake.add(new Position(18, 9));
            snake.add(new Position(18, 10));
            snake.add(new Position(19, 10));
            snake.add(new Position(20, 10));

            display.drawApple(apple1);
            display.drawApple(apple2);
            display.drawSnake(snake);

            Thread.sleep(1000);

            display.writeGameOver();
            Thread.sleep(1000);
        } catch (IOException ioe) {
            fail();
        } catch (InterruptedException ie) {
            System.out.println("Test failure while sleeping");
            ie.printStackTrace();
            fail();
        } finally {
            if (display != null) {
                display.close();
            }
        }
    }
}
