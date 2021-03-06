package com.hodvidar.adventofcode.y2020;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class _Day03_2 extends _Day03 {

    public static void main(final String[] args) throws Exception {
        final _Day03 me = new _Day03();
        final int result = me.getResult(me.getScanner());
        System.err.println("Expected '1744787392' - result='" + result + "'");
    }

    @Override
    protected int getResult(final Scanner sc) throws FileNotFoundException {
        final int result1 = countTrees(sc, 1, 1);
        final int result2 = countTrees(sc, 1, 3);
        final int result3 = countTrees(sc, 1, 5);
        final int result4 = countTrees(sc, 1, 7);
        final int result5 = countTrees(sc, 2, 1);
        return result1 * result2 * result3 * result4 * result5;
    }

}
