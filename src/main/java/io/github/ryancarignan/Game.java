// Game
//     // The main class that handles all the game logic
// ----
// *display: Display            // handles drawing to the screen
// *width: int                  // width of the game screen 
// *height: int                 // height of the game screen
// *snake: Position[1..*]       // each position of the snake body (probably as a LinkedList)
// *apples: Position[1..*]      // position of each apple on screen (probably as array or ArrayList)
// *snakeDirection: Position    // Direction (as single-direction single-magnitude Position) of the snake
// *score: int                  // the number of apples (length of the snake) that the snake has eaten
// ----
// startGame()                 // starts the game loop
// *moveSnake()                 // moves the snake in the direction of `direction`
// *checkCollisions()           // checks if the snake head is colliding with its body, an apple, or the screen boundaries
// *eatApple(apple: Position)   // remove apple, place new one, grow snake, increment score
// *placeApple()                // places a new apple in a valid position

package io.github.ryancarignan;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ListIterator;

import java.io.IOException;

public class Game {

    private Display display;            // handles drawing to the screen
    public int width;                   // width of the game board (which the snake can traverse)
    public int height;                  // height of the game board (which the snake can traverse)
    private LinkedList<Position> snake;       // positions of each of the blocks comprising the body of the snake
    private Position snakeDirection;    // the direction of movement of the snake as a vector relative to the head of the snake
    private int snakeLength;            // the length of the snake, useful for growth
    private ArrayList<Position> apples;      // positions of each of the apples on the game board
    private int score;                  // the score (num apples eaten) of the game
    private boolean gameOver;           // if the game has terminated

    public Game(int numApples) {
        try {
            // init display and game board dimensions
            display = new Display();
            width = display.getWidth();
            height = display.getHeight();

            // init snake
            snake = new LinkedList<>();
            snake.add(new Position(width / 2, height / 2));
            snakeLength = 1;
            snakeDirection = null;
            try {
                display.drawSnake(snake);
            } catch (IOException e) {
                System.out.println("An IO error has occurred");
                gameOver = true;
            }

            // init apples
            apples = new ArrayList<>();
            for (int i = 0; i < numApples; i++) {
                Position newApple = placeApple();
                apples.add(newApple);
            }

            // init game data
            score = 0;
            gameOver = false;
            
        } catch (IOException e) {
            System.out.println("An IO error has occurred");
            gameOver = true;
        }
    }

    public void startGame() {
        while (!gameOver) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                System.out.println("An interrupt error has occurred");
                gameOver = true;
            }
            readInput();
            moveSnake();
            checkCollisions();
        }
        gameOver();
    }

    private void readInput() {
        // read input
        try {
            display.pollInput();
        } catch (IOException e) {
            System.out.println("An IO error has occurred");
            gameOver = true;
        }
        // exit key?
        gameOver = display.exitKeyPressed();
        // directional key?
        Position newDirection = display.directionKeyPressed();
        if (newDirection != null) {
            snakeDirection = newDirection;
        }
    }

    private Position placeApple() { // FIXME - apples are going to the center of the screen I think?
        // concatinate snake and apples
        Position[] excludedPositions = new Position[snake.size() + apples.size()];
        int i = 0; 
        for (Position snakeBody : snake) {
            excludedPositions[i] = snakeBody;
            i++;
        }
        for (Position apple : apples) {
            excludedPositions[i] = apple;
            i++;
        }
        Position newApple = Position.findRandomUnique(width, height, excludedPositions);
        try {
            display.drawApple(newApple);
        } catch (IOException e) {
            System.out.println("An IO error has occurred");
            gameOver = true;
            return null;
        }
        return newApple;
    }

    private void moveSnake() {
        if (snakeDirection != null) {
            try {
                // add to head
                Position newHead = Position.sum(snake.getFirst(), snakeDirection);
                snake.addFirst(newHead);

                // remove from tail if not growing
                if (snake.size() >= snakeLength) {
                    Position tail = snake.removeLast();
                    display.drawBackground(tail);
                }

                display.drawSnake(snake); // TODO change to only drawing new head
            } catch (IOException e) {
                System.out.println("An IO error has occurred");
                gameOver = true;
            }
        }
    }

    private void checkCollisions() {
        Position snakeHead = snake.getFirst();
        if (snakeHead.x < 0 ||
            snakeHead.x >= width ||
            snakeHead.y < 0 ||
            snakeHead.y >= height)
        {
            // game board boundary collision
            gameOver();
        }

        ListIterator<Position> iterator = apples.listIterator();
        while (iterator.hasNext()) {
            Position apple = iterator.next();
            if (snakeHead.equals(apple)) {
                // apple collision
                eatApple();
                iterator.remove();
                Position newApple = placeApple();
                iterator.add(newApple);
            }
        }

        for (Position snakeBody : snake) {
            if (snakeHead != snakeBody && snakeHead.equals(snakeBody)) {
                // snake body collision
                gameOver();
            }
        }
    }

    private void gameOver() {
        gameOver = true;
        try {
            display.writeGameOver();
        } catch (IOException e) {
            System.out.println("An IO error occurred");
        }
    }

    private void eatApple() {
        score++;
        snakeLength++;
        try {
            display.writeScore(score);
        } catch (IOException e) {
            gameOver = true;
            System.out.println("An IO error occurred");
        }
    }




    // --------------- MAIN METHOD ---------------

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        try {
            Game game = new Game(1);
            game.startGame();
            Thread.sleep(2500);
        } catch (InterruptedException ie) {
            System.out.println("An interrupt error has occurred");
        }
    }

}
