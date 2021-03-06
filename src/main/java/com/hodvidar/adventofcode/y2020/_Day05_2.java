package com.hodvidar.adventofcode.y2020;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class _Day05_2 extends _Day05 {

    public static void main(final String[] args) throws Exception {
        final _Day05_2 me = new _Day05_2();
        final int result = me.getResult(me.getScanner());
        System.err.println("Expected '747' - result='" + result + "'");
    }

    public static int getMissingCode(final Scanner sc) {
        final List<Integer> codes = new ArrayList<>();
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            final int code = getFinalPositionCode(line);
            codes.add(code);
        }
        Collections.sort(codes);
        for (int i = 0; i < codes.size() - 1; i++) {
            final int code1 = codes.get(i);
            final int code2 = codes.get(i + 1);
            if (code2 - code1 > 1) {
                return code1 + 1;
            }
        }
        return -1;
    }

    @Override
    protected int getResult(final Scanner sc) throws FileNotFoundException {
        return getMissingCode(sc);
    }
}
