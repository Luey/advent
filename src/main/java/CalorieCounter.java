import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CalorieCounter {

    public static void main(String[] args) {
        String inputFilename = "calories.txt";

        TreeSet<Integer> sortedCaloriesPerElf = new CalorieCounter().getCaloriesPerElfDesc(
                Objects.requireNonNull(CalorieCounter.class.getClassLoader().getResource(inputFilename)).getPath());

        Iterator<Integer> iterator = sortedCaloriesPerElf.iterator();
        int caloriesSumForTopThreeElves = iterator.next() + iterator.next() + iterator.next();

        System.out.printf("""
                The top three elves are carrying a total of %s calories
                    
                Calories carried by each elf (in desc order): %s
                """, caloriesSumForTopThreeElves, sortedCaloriesPerElf);
    }

    public TreeSet<Integer> getCaloriesPerElfDesc(String filepath) {
        TreeSet<Integer> caloriesPerElf = new TreeSet<>(Comparator.reverseOrder());
        int totalCaloriesForCurrentElf = 0;

        try (FileReader fileReader = new FileReader(filepath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                // Elves are separated by an empty line
                if (line.isEmpty()) {
                    caloriesPerElf.add(totalCaloriesForCurrentElf);
                    totalCaloriesForCurrentElf = 0;
                    continue;
                }

                totalCaloriesForCurrentElf += Integer.parseInt(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return caloriesPerElf;
    }
}
