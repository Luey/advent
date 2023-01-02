package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;

public class Day7 implements Solution {
    int result = 0;

    public static void main(String[] args) {
        Results results = Solution.runSolution("day7.txt", new Day7());
        System.out.println("RESULT: " + results.part1());
    }

    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        getSize(bufferedReader);
        return String.valueOf(result);
    }

    private int getSize(BufferedReader bufferedReader) throws IOException {
        int folderSize = 0;

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);

            if (line.startsWith("$")) {
                if (line.equals("$ cd ..")) {
                    if (folderSize < 100000) {
                        System.out.println("Adding folder of " + folderSize + " to result");
                        result += folderSize;
                    }
                    break;
                } else if (line.startsWith("$ cd")) {
                    int nestedFolderSize = getSize(bufferedReader);
                    System.out.println(folderSize + " + " + nestedFolderSize);
                    folderSize += nestedFolderSize;
                }
            } else {
                String firstWord = line.split(" ")[0];
                if (!firstWord.equals("dir")) {
                    Integer fileSize = Integer.valueOf(firstWord);
                    System.out.println(folderSize + " + " + fileSize);
                    folderSize += fileSize;
                }
            }
        }

        System.out.println("Folder size is " + folderSize);
        return folderSize;
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        return null;
    }
}
