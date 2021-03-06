package com.hodvidar.adventofcode.y2019;

import com.hodvidar.utils.geometry.GeometryServices;
import com.hodvidar.utils.geometry.Point;
import com.hodvidar.utils.geometry.Wire;
import com.hodvidar.utils.geometry.WireBuilder;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import static com.hodvidar.utils.file.Constance.RESOURCES;

/**
 * Passed 1285
 *
 * @author Hodvidar
 */
public final class _Day03 {
    /**
     * If 'false' only response and Failure are written
     **/
    private static final boolean VERBOSE = false;

    private static final int NUMBER_OF_TEST = 4;
    private static final String INPUT_DIRECTORY = "adventofcode_2019"; // input1

    public static void printIfVerbose(final String s) {
        if (VERBOSE)
            System.err.println(s);
    }

    public static void main(final String[] args) throws Exception {
        final int result = test(RESOURCES + File.separator + INPUT_DIRECTORY + File.separator + "input" + NUMBER_OF_TEST + ".txt");
        System.err.println("result='" + result + "'");
    }

    private static int test(final String inputFile) throws Exception {
        final File file = new File(inputFile);
        // Scanner sc = new Scanner(System.in);
        final Scanner sc = new Scanner(file);
        printIfVerbose("DEBUGGING");

        // 2 Lines
        final String line1 = sc.nextLine();
        final String line2 = sc.nextLine();
        sc.close();

        final String[] directions1 = line1.split(",");
        final String[] directions2 = line2.split(",");

        final Point origin = new Point(0, 0);

        final WireBuilder wb1 = new WireBuilder(origin);
        final WireBuilder wb2 = new WireBuilder(origin);

        for (final String d : directions1)
            wb1.addInstruction(d);
        for (final String d : directions2)
            wb2.addInstruction(d);

        final Wire w1 = wb1.getWire();
        final Wire w2 = wb2.getWire();

        final List<Point> intersections = w1.getIntersections(w2);

        double minDistance = Double.MAX_VALUE;
        for (final Point p : intersections) {
            final double dist = GeometryServices.getManhattanDistance(origin, p);
            if (dist == 0)
                continue;
            if (dist < minDistance)
                minDistance = dist;
        }

        return (int) minDistance;
    }

}
