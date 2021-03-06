package com.hodvidar.codingame.puzzles.easy;

import java.util.Scanner;

/**
 * https://www.codingame.com/ide/puzzle/rugby-score by Hodvidar
 **/
class RugbyScore {

    public static void main(final String[] args) {
        System.err.println("START");

        final Scanner in = new Scanner(System.in);
        final int score = in.nextInt();

        int tries = 0; // +5
        int transformations = 0; // +2 (can't be more than tries)
        int penalties = 0; // +3

        while (computeScore(tries, 0, 0) <= score) {
            transformations = 0;
            while ((computeScore(tries, transformations, 0) <= score)
                    && (transformations <= tries)) {
                penalties = 0;
                while (computeScore(tries, transformations, penalties) <= score) {
                    if (computeScore(tries, transformations, penalties) == score) {
                        System.out.println(tries + " " + transformations + " " + penalties);
                    }
                    penalties++;
                }
                transformations++;
            }
            tries++;
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.err.println("END");
        in.close();
    }

    private static int computeScore(final int tries, final int transformations, final int penalties) {
        return (tries * 5) + (transformations * 2) + (penalties * 3);
    }
}
