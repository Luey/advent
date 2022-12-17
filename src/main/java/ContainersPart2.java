import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContainersPart2 {
    public static void main(String[] args) {
        List<Deque<Character>> containers = List.of(
                new LinkedList<>(List.of()), // to fix index 0
                new LinkedList<>(List.of('F', 'G', 'V', 'R', 'J', 'L', 'D')),
                new LinkedList<>(List.of('S', 'J', 'H', 'V', 'B', 'M', 'P', 'T')),
                new LinkedList<>(List.of('C', 'P', 'G', 'D', 'F', 'M', 'H', 'V')),
                new LinkedList<>(List.of('Q', 'G', 'N', 'P', 'D', 'M')),
                new LinkedList<>(List.of('F', 'N', 'H', 'L', 'J')),
                new LinkedList<>(List.of('Z', 'T', 'G', 'D', 'Q', 'V', 'F', 'N')),
                new LinkedList<>(List.of('L', 'B', 'D', 'F')),
                new LinkedList<>(List.of('N', 'D', 'V', 'S', 'B', 'J', 'M')),
                new LinkedList<>(List.of('D', 'L', 'G'))
        );

        String inputFilename = "containers.txt";
        Path inputPath = Path.of(OverlappingSectionsPart1.class.getClassLoader().getResource(inputFilename).getPath());

        ContainersPart2.moveContainers(containers, inputPath);
        ContainersPart2.printTopContainers(containers);
    }

    private static void moveContainers(List<Deque<Character>> containers, Path inputPath) {
        try (FileReader fileReader = new FileReader(inputPath.toFile());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher m = Pattern.compile("move (?<times>\\p{Digit}+) from (?<from>\\p{Digit}+) to (?<to>\\p{Digit}+)").matcher(line);

                if (! m.matches())
                    throw new IllegalArgumentException("Invalid input: \"" + line + "\"");


                int times = Integer.valueOf(m.group("times"));
                int from = Integer.valueOf(m.group("from"));
                int to = Integer.valueOf(m.group("to"));

                Deque<Character> containersToMove = new LinkedList<>();
                for (int i = 0; i < times; i++) {
                    containersToMove.push(containers.get(from).remove());
                }

                containersToMove.stream()
                    .forEach(container -> containers.get(to).push(container));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printTopContainers(List<Deque<Character>> containers) {
        containers.stream().filter(characters -> !characters.isEmpty())
                .forEachOrdered(stack -> System.out.print(stack.peek()));
    }
}
