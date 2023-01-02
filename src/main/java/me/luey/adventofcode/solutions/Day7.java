package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Day7 implements Solution {
    int FILE_SYSTEM_CAPACITY = 70000000;
    int FREE_SPACE_REQUIRED = 30000000;

    int result = 0;
    NavigableSet<Integer> folderSizes = new TreeSet();

    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        System.out.println(getSize(bufferedReader));
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
        folderSizes.add(folderSize);
        return folderSize;
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        int additionalSpaceRequired = FREE_SPACE_REQUIRED + getSize(bufferedReader) - FILE_SYSTEM_CAPACITY;
        return String.valueOf(folderSizes.higher(additionalSpaceRequired));
    }
}
