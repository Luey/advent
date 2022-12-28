package me.luey.adventofcode.solutions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    Solution underTest = new Day3();

    @Test
    void part1() throws IOException {
        assertThat(underTest.part1(Solution.createBufferedReader("day3.txt"))).isEqualTo("7990");
    }

    @Test
    void part2() throws IOException {
        assertThat(underTest.part2(Solution.createBufferedReader("day3.txt"))).isEqualTo("2602");
    }
}
