package me.luey.adventofcode.solutions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    Solution underTest = new Day4();

    @Test
    void part1() throws IOException {
        assertThat(underTest.part1(Solution.createBufferedReader("day4.txt"))).isEqualTo("456");
    }

    @Test
    void part2() throws IOException {
        assertThat(underTest.part2(Solution.createBufferedReader("day4.txt"))).isEqualTo("808");
    }
}