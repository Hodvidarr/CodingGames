package com.hodvidar.adventofcode.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class _Day01_Test extends AbstractTestForAdventOfCode {

    @Override
    protected int getDay() {
        return 1;
    }

    @ParameterizedTest
    @CsvSource(delimiter = '=', value = {
            "1 = 514579",
            "2 = 1020100"
    })
    void shouldFindResultInSmallNumberPool(int numberOfTheTest, int expectedResult) throws FileNotFoundException {
        Scanner sc = getScanner(numberOfTheTest);
        int result = _Day01.getResult(sc);
        assertThat(result).isEqualTo(expectedResult);
    }

}
