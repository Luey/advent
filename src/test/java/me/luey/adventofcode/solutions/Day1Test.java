package me.luey.adventofcode.solutions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    Solution underTest = new Day1();

    @Test
    void part1() throws IOException {
        assertThat(underTest.part1(Solution.createBufferedReader("day1.txt"))).isEqualTo("75622");
    }

    @Test
    void part2() throws IOException {
        assertThat(underTest.part2(Solution.createBufferedReader("day1.txt"))).isEqualTo("213159");
    }
}
