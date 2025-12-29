package io.github.ryancarignan;

import java.io.IOException;

public class Game {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        try {
            Display display = new Display();
            Thread.sleep(5000); // allow time to look at it
        } catch (IOException ioe) {
            System.out.println("Test failed in case 1");
        } catch (InterruptedException ie) {
            System.out.println("Test failure while sleeping");
            ie.printStackTrace();
            System.out.println("Test failed in case 2");
        }
    }

}
