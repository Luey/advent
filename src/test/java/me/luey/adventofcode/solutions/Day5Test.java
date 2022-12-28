package me.luey.adventofcode.solutions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {

    Solution underTest = new Day5();

    @Test
    void part1() throws IOException {
        assertThat(underTest.part1(Solution.createBufferedReader("day5.txt"))).isEqualTo("QMBMJDFTD");
    }

    @Test
    void part2() throws IOException {
        assertThat(underTest.part2(Solution.createBufferedReader("day5.txt"))).isEqualTo("NBTVTJNFJ");
    }
}