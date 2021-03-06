package com.hodvidar.adventofcode.y2019;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CodeGenerator {
    private final int min;
    private final int max;

    public CodeGenerator(final int min, final int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Example: <br/>
     * If length = 3 and this.min = 1 and this.max = 4 --> <br/>
     * [123, 124, 	132, 134, 	142, 143, <br/>
     * 213, 214, 	231, 234, 	241, 243, <br/>
     * 312, 314, 	321, 324, 	341, 342, <br/>
     * 412, 413, 	421, 423, 	431, 432] <br/>
     *
     * @param length - of each code.
     * @return List of codes where each digits is only used once.
     */
    public Collection<String> generateUniqueDigitCode(final int length) {
        if (length - 1 > max - min)
            throw new IllegalStateException("Length of unique digit code "
                    + "cannot be more than the given interval." + " length="
                    + length + " and interval: min=" + min + " max=" + max);

        final List<String> codes = new ArrayList<>();
        generate(length, new ArrayList<>(), codes);
        return codes;
    }

    private void generate(final int length, final List<Integer> values, final List<String> codes) {
        for (int i = min; i <= max; i++) {
            if (alredyExists(i, values))
                continue;
            if (length == 1) {
                codes.add(intToString(i, values));
            } else {
                final List<Integer> valuesBis = new ArrayList<>(values);
                valuesBis.add(i);
                generate(length - 1, valuesBis, codes);
            }
        }
    }

    private boolean alredyExists(final int i, final List<Integer> values) {
        for (final int v : values) {
            if (i == v)
                return true;
        }
        return false;
    }

    private String intToString(final int i, final List<Integer> values) {
        String s = i + "";
        for (final int v : values) {
            s += v;
        }
        return s;
    }
}
