package com.hodvidar.utils.number;

public final class ArithmeticServices {
    public static final boolean isNumeric(final String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (final NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Greatest Common Divisor
     *
     * @param a - one number
     * @param b - several numbers
     * @return
     */
    public static final double greatestCommonDivisor(final double a, final double... b) {
        if (b == null || b.length == 0)
            throw new IllegalArgumentException("Need at least 2 values.");

        if (b.length == 1) {
            return gcd(a, b[0]);
        } else {
            final double[] b2 = new double[b.length - 1];
            for (int i = 1; i < b.length; i++)
                b2[i - 1] = b[i];
            return greatestCommonDivisor(gcd(a, b[0]), b2);
        }
    }

    /**
     * Greatest Common Divisor
     *
     * @param a - one number
     * @param b - one number
     * @return
     */
    private static final double gcd(final double a, final double b) {
        return b == 0 ? a : gcd(b, a % b); // Not bad for one line of code :)
    }

    /**
     * Lowest Common Multiplier
     *
     * @param a - one number
     * @param b - several numbers
     * @return
     */
    public static final double lowerCommonMultiplier(final double a, final double... b) {
        if (b == null || b.length == 0)
            throw new IllegalArgumentException("Need at least 2 values.");

        if (b.length == 1) {
            return lcm(a, b[0]);
        } else {
            final double[] b2 = new double[b.length - 1];
            for (int i = 1; i < b.length; i++)
                b2[i - 1] = b[i];
            return lowerCommonMultiplier(lcm(a, b[0]), b2);
        }
    }

    /**
     * Lowest Common Multiplier
     *
     * @param a - one number
     * @param b - one number
     * @return
     */
    private static final double lcm(final double a, final double b) {
        return (a * b) / gcd(a, b);
    }

    public static int max(final int... numbers) {
        int max = Integer.MIN_VALUE;
        for (final int n : numbers) {
            max = Math.max(max, n);
        }
        return max;
    }

    public static int getFactorial(final int aNumber) {
        double factorial = 1;
        for (int i = 1; i <= aNumber; i++) {
            factorial *= i;
        }

        if (factorial > Integer.MAX_VALUE) {
            throw new ArithmeticException("Factorial > Integer.MAX_VALUE");
        }
        return (int) factorial;
    }

    /**
     * 105! / (100! * 2!) --> calculate this knowing that 100! cannot be computed (for integers).
     * <p>
     * Should become : (105*104*103*102*101 / 2)
     *
     * @param numerator
     * @param denominatorsMultiplied
     * @return
     */
    // TODO Hodvidar
    public static int getFactorialDivision(final int numerator, final int... denominatorsMultiplied) {
        return 0;
    }
}
