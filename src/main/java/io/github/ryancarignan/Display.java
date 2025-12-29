package io.github.ryancarignan;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;
import java.util.List;

public class Display {
    private static final TextColor BACKGROUND_COLOR = TextColor.ANSI.BLACK;
    private static final TextColor FOREGROUND_COLOR = TextColor.ANSI.WHITE;
    private static final TextColor      SNAKE_COLOR = TextColor.ANSI.GREEN;
    private static final TextColor      APPLE_COLOR = TextColor.ANSI.RED;

    private Terminal terminal;          // the terminal that is displayed
    private KeyStroke keyStroke;        // holds the last key-stroke input by the user

    public Display() throws IOException {
        // instantiate terminal
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        terminal = null;
        try {
            terminal = defaultTerminalFactory.createTerminal();
        } catch (IOException e) {
            terminal = defaultTerminalFactory.createTerminalEmulator();
        }

        // set colors
        TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(BACKGROUND_COLOR);
        textGraphics.setForegroundColor(FOREGROUND_COLOR);

        // enter fullscreen
        terminal.enterPrivateMode();
        terminal.clearScreen();
        terminal.setCursorVisible(false);

        // draw start screen
        String header = "SNAKE - Press Esc or Q to exit";
        textGraphics.putString(2, 1, header, SGR.BOLD);
        int width = terminal.getTerminalSize().getColumns();
        String score = "Score: 0 ";
        textGraphics.putString(width - score.length() - 2, 1, score, SGR.BOLD);
        textGraphics.drawLine(new TerminalPosition(0, 2), new TerminalPosition(terminal.getTerminalSize().getColumns(), 2), '_');

        terminal.flush();
    }

    public void close() {
        if (terminal != null) {
            try {
                terminal.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Draws the snake on the terminal display
     * @param snake the positions of each of the snake's body blocks
     */
    public void drawSnake(List<Position> snake) throws IOException {
        TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(SNAKE_COLOR);

        for (Position pos : snake) {
            textGraphics.drawRectangle(new TerminalPosition(pos.x * 2, pos.y + 3), new TerminalSize(2, 1), ' ');
        }

        terminal.flush();
    }

    /**
     * Draws an apple to the terminal display
     * @param apple the position of the apple to draw
     */
    public void drawApple(Position apple) throws IOException {
        TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(APPLE_COLOR);

        textGraphics.drawRectangle(new TerminalPosition(apple.x * 2, apple.y + 3), new TerminalSize(2, 1), ' ');

        terminal.flush();
    }

    /**
     * Draw background (i.e. erase) a block at the given position
     * @param pos the position to draw at on the game board
     */
    public void drawBackground(Position pos) throws IOException {
        TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(BACKGROUND_COLOR);

        textGraphics.drawRectangle(new TerminalPosition(pos.x * 2, pos.y + 3), new TerminalSize(2, 1), ' ');

        terminal.flush();
    }

    /**
     * Write the score to the screen
     * @param score the score, from 0 to 99
     */
    public void writeScore(int score) throws IOException {
        TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(BACKGROUND_COLOR);
        textGraphics.setForegroundColor(FOREGROUND_COLOR);

        // clear and rewrite score
        textGraphics.putString(terminal.getTerminalSize().getColumns() - 4, 1, "  ");
        textGraphics.putString(terminal.getTerminalSize().getColumns() - 4, 1, "" + score, SGR.BOLD);

        terminal.flush();
    }

    /**
     * Write a "Game Over" message to the center of the screen
     */
    public void writeGameOver() throws IOException {
        TextGraphics textGraphics = terminal.newTextGraphics();
        textGraphics.setBackgroundColor(BACKGROUND_COLOR);
        textGraphics.setForegroundColor(FOREGROUND_COLOR);

        String gameOver = "Game Over";
        int centerX = terminal.getTerminalSize().getColumns() / 2 - gameOver.length() / 2;
        int centerY = terminal.getTerminalSize().getRows() / 2;
        textGraphics.putString(centerX, centerY, gameOver, SGR.BOLD);

        terminal.flush();
    }
}
