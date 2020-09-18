package com.hodvidar.formation.java11;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class RPNCalculatorTest {

	private RPNCalculator rpnCalculator;

	@BeforeEach
	void setup() {
		rpnCalculator = new RPNCalculator();
	}

	// Can replace 3 previous tests
	@ParameterizedTest
	@CsvSource(value = {
			"1", "2", "3", "42"
	})
	void should_evaluate_a_constant(String constant){
		assertThat(rpnCalculator.calculate(constant)).isEqualTo(Integer.valueOf(constant));
	}

	// Can replace 3 previous tests
	@ParameterizedTest
	@CsvSource(delimiter = '=', value = {
			"2 = 1 1 +",
			"3 = 1 2 +",
			"84 = 42 42 +"
	})
	void should_add_two_constants(int result, String expression){
		assertThat(rpnCalculator.calculate(expression)).isEqualTo(result);
	}

	@ParameterizedTest
	@CsvSource(delimiter = '=', value = {
			"2 = 3 1 -",
			"18 = 21 3 -",
			"0 = 42 42 -",
			"-10 = 100 110 -"
	})
	void should_subtract_two_constants(int result, String expression){
		assertThat(rpnCalculator.calculate(expression)).isEqualTo(result);
	}

	@ParameterizedTest
	@CsvSource(delimiter = '=', value = {
			"3 = 3 1 *",
			"100 = 10 10 *",
			"69 = 23 3 *",
			"1500 = 100 15 *"
	})
	void should_multiple_two_constants(int result, String expression){
		assertThat(rpnCalculator.calculate(expression)).isEqualTo(result);
	}

	@ParameterizedTest
	@CsvSource(delimiter = '=', value = {
			"1 = 1500 1500 /",
			"5 = 50 10 /",
			"21 = 42 2 /",
			"7 = 49 7 /",
			"5 = 52 10 /"
	})
	void should_divide_two_constants(int result, String expression){
		assertThat(rpnCalculator.calculate(expression)).isEqualTo(result);
	}

	@Test
	void should_throws_exception_for_unknown_operator() {
		assertThatThrownBy(() -> rpnCalculator.calculate("42 42 .")).isInstanceOf(UnsupportedOperationException.class);

	}
}