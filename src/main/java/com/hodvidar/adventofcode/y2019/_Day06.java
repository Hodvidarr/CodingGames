package com.hodvidar.adventofcode.y2019;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.hodvidar.utils.file.Constance.RESOURCES;

/**
 * '142915'
 *
 * @author Hodvidar
 */
public final class _Day06 {
    /**
     * If 'false' only response and Failure are written
     **/
    private static final boolean VERBOSE = false;

    private static final int NUMBER_OF_TEST = 6;
    private static final String INPUT_DIRECTORY = "adventofcode_2019"; // input1
    private static final String COM = "COM";

    public static void printIfVerbose(final String s) {
        if (VERBOSE)
            System.err.println(s);
    }

    public static void main(final String[] args) throws Exception {
        final int result = test(RESOURCES + File.separator + INPUT_DIRECTORY + File.separator + "input"
                + NUMBER_OF_TEST + ".txt");
        System.err.println("result='" + result + "'");
    }

    private static int test(final String inputFile) throws Exception {
        String line;
        final File file = new File(inputFile);
        // Scanner sc = new Scanner(System.in);
        final Scanner sc = new Scanner(file);
        printIfVerbose("DEBUGGING");

        final Map<String, Node> planetes = new HashMap<>();
        while (sc.hasNextLine()) {
            // line = input[i];
            line = sc.nextLine();
            final String[] line2 = line.split("\\)");
            final String a = line2[0];
            final String b = line2[1];

            Node A = planetes.get(a);
            if (A == null)
                A = new Node(a);
            Node B = planetes.get(b);
            if (B == null)
                B = new Node(b);
            final boolean success = B.connectToParent(A);
            if (!success) {
                sc.close();
                throw new IllegalStateException("Failed to connect child:"
                        + B.getName() + " to parent:" + A.getName());
            }
            planetes.put(A.getName(), A);
            planetes.put(B.getName(), B);
        }
        sc.close();
        planetes.get(COM).propagadeLevels(0);

        int total = 0;
        for (final Node n : planetes.values())
            total += n.getLevel();

        return total;
    }

}
