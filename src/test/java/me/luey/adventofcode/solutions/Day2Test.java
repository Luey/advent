package me.luey.adventofcode.solutions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    Solution underTest = new Day2();

    @Test
    void part1() throws IOException {
        assertThat(underTest.part1(Solution.createBufferedReader("day2.txt"))).isEqualTo("12679");
    }

    @Test
    void part2() throws IOException {
        assertThat(underTest.part2(Solution.createBufferedReader("day2.txt"))).isEqualTo("14470");
    }
}
