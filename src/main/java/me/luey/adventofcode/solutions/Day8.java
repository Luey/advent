package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;

public class Day8 implements Solution {

    private int height;
    private int width;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = Solution.createBufferedReader("day8.txt");
        System.out.println(new Day8().part1(bufferedReader));
    }

    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        final int[][] treeHeights = getInput(bufferedReader);

        height = treeHeights.length;
        width = treeHeights[0].length;

        boolean[][] isVisible = new boolean[height][width]; // cannot make numbers negative cos of 0
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || x == 0 || y == height - 1 || x == width - 1) {
                    isVisible[y][x] = true;
                }
            }
        }

        for (int y = 1; y < height - 1; y++) {

            // left to right
            var tallestFromLeft = treeHeights[y][0];
            for (int x = 1; x < width - 1; x++) {
                if (isVisible[y][x]) continue;
                if (treeHeights[y][x] > tallestFromLeft ) {
                    tallestFromLeft = treeHeights[y][x];
                    isVisible[y][x] = true;
                }
            }

            // right to left
            var tallestFromRight = treeHeights[y][width - 1];
            for (int x = width - 2; x > 0; x--) {
                if (treeHeights[y][x] > tallestFromRight ) {
                    tallestFromRight = treeHeights[y][x];
                    isVisible[y][x] = true;
                }
            }
        }

        for (int x = 1; x < width - 1; x++) {

            // top down
            var tallestFromTop = treeHeights[0][x];
            for (int y = 1; y < height - 1; y++) {
                if (treeHeights[y][x] > tallestFromTop) {
                    tallestFromTop = treeHeights[y][x];
                    isVisible[y][x] = true;
                }
            }

            // bottom up
            var tallestFromBottom = treeHeights[height - 1][x];
            for (int y = height - 2; y > 0; y--) {
                if (treeHeights[y][x] > tallestFromBottom) {
                    tallestFromBottom = treeHeights[y][x];
                    isVisible[y][x] = true;
                }
            }
        }

        return String.valueOf(countVisibleTrees(isVisible));
    }

    private int countVisibleTrees(boolean[][] isVisible) {
        int isVisibleCount = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isVisible[i][j]) {
                    isVisibleCount++;
                }
            }
        }
        return isVisibleCount;
    }

    private static int[][] getInput(BufferedReader bufferedReader) {
        return bufferedReader.lines()
            .map(line -> line.split(""))
            .map(line -> Stream.of(line).mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        return null;
    }
}
