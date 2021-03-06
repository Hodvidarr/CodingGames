package com.hodvidar.adventofcode.y2020;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _Day08_2 extends _Day08 {

    public static void main(final String[] args) throws Exception {
        final _Day08_2 me = new _Day08_2();
        final int result = me.getResult(me.getScanner());
        System.err.println("Expected '640' - result='" + result + "'");
    }

    @Override
    protected int getResult(final Scanner sc) throws FileNotFoundException {
        final int result = getAccumulatorValueAfterFix(sc);
        return result;
    }

    public int getAccumulatorValueAfterFix(final Scanner sc) {
        List<String> instructions = getInstructions(sc);
        instructions = fixInstructions(instructions);
        return runInstructions(instructions);
    }

    /**
     * Not most efficient way of doing it
     **/
    public List<String> fixInstructions(final List<String> instructions) {
        String[] instruction;
        for (int i = 0; i < instructions.size(); i++) {
            instruction = instructions.get(i).split(" ");
            final int value = Integer.parseInt(instruction[1]);
            if (NO_OPERATION.equals(instruction[0])) {
                instructions.set(i, JUMP + " " + value);
                if (!isLoop(instructions)) {
                    break;
                }
                instructions.set(i, NO_OPERATION + " " + value);
            } else if (JUMP.equals(instruction[0])) {
                instructions.set(i, NO_OPERATION + " " + value);
                if (!isLoop(instructions)) {
                    break;
                }
                instructions.set(i, JUMP + " " + value);
            }
        }
        return instructions;
    }

    public boolean isLoop(final List<String> instructions) {
        final List<Integer> visitedValues = new ArrayList<>();
        String[] instruction;
        for (int i = 0; i < instructions.size(); /* empty */) {
            if (visitedValues.contains(i)) {
                return true;
            }
            visitedValues.add(i);
            instruction = instructions.get(i).split(" ");
            final int value = Integer.parseInt(instruction[1]);

            if (JUMP.equals(instruction[0])) {
                i += value;
                if (i < 0 || i > instructions.size()) {
                    return true;
                }
                continue;
            }

            // NO_OPERATION and ignore ACCUMULATOR
            i++;
        }
        return false;
    }

}
