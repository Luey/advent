package me.luey.adventofcode.solutions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("params")
    void testSolutions(Solution underTest, String inputFileName, String expectedPart1, String expectedPart2) throws IOException {
        SolutionRunner runner = new SolutionRunner(underTest, inputFileName);

        String resultPart1 = runner.part1();
        assertThat(resultPart1)
                .withFailMessage("Incorrect answer for part 1 - expected %s but got %s", expectedPart1, resultPart1)
                .isEqualTo(expectedPart1);

        String resultPart2 = runner.part2();
        assertThat(resultPart2)
                .withFailMessage("Incorrect answer for part 2 - expected %s but got %s", expectedPart2, resultPart2)
                .isEqualTo(expectedPart2);
    }

    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(new Day1(), "day1.txt", "75622", "213159"),
                Arguments.of(new Day2(), "day2.txt", "12679", "14470"),
                Arguments.of(new Day3(), "day3.txt", "7990", "2602"),
                Arguments.of(new Day4(), "day4.txt", "456", "808"),
                Arguments.of(new Day5(), "day5.txt", "QMBMJDFTD", "NBTVTJNFJ"),
                Arguments.of(new Day6(), "day6.txt", "1140", "3495"),
                Arguments.of(new Day7(), "day7-example.txt", "95437", "0"),
                Arguments.of(new Day7(), "day7.txt", "1844187", "0")
        );
    }
}
