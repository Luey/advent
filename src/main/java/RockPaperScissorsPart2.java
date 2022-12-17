import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RockPaperScissorsPart2 {

    private enum Move {
        ROCK ('A'),
        PAPER ('B'),
        SCISSORS ('C');

        private final char abc;

        private static final Map<Character,Move> charMapping;
        private static final Map<Move, Move> toWin = Map.of(ROCK, PAPER, PAPER, SCISSORS, SCISSORS, ROCK);
        private static final Map<Move, Move> toLose = Map.of(PAPER, ROCK, SCISSORS, PAPER, ROCK, SCISSORS);

        static {
            charMapping = new HashMap<>(3);
            for (Move v : Move.values()) {
                charMapping.put(v.abc, v);
            }
        }

        Move (char abc) {
            this.abc = abc;
        }

        public static Move fromABC(char abc) {
            return charMapping.get(abc);
        }

        public static Move toWin(Move move) {
            return toWin.get(move);
        }

        public static Move toLose(Move move) {
            return toLose.get(move);
        }
    }

    public static void main(String[] args) {
        String inputFilename = "strategy-guide.txt";

        int score = new RockPaperScissorsPart2().calculateScore(
                Objects.requireNonNull(RockPaperScissorsPart2.class.getClassLoader().getResource(inputFilename)).getPath());

        System.out.println("Total score: " + score);
    }

    public int calculateScore(String filepath) {
        int score = 0;

        try (FileReader fileReader = new FileReader(filepath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Move opponentsMove = Move.fromABC(line.charAt(0));
                char winLoseDraw = line.charAt(2);

                System.out.println("Me: " + winLoseDraw + " vs opponent: " + opponentsMove);

                score += scoreWinLoseDraw(winLoseDraw);
                score += scoreMyMove(getMyMove(opponentsMove, winLoseDraw));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return score;
    }

    private Move getMyMove(Move opponentsMove, char winLoseDraw) {
        return switch (winLoseDraw) {
            case 'X' -> Move.toLose(opponentsMove);
            case 'Y' -> opponentsMove;
            case 'Z' -> Move.toWin(opponentsMove);
            default -> throw new IllegalStateException("Unexpected value: " + winLoseDraw);
        };
    }

    private int scoreMyMove(Move move) {
        return switch (move) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSORS -> 3;
        };
    }

    private int scoreWinLoseDraw(char xyz) {
        return switch (xyz) {
            case 'X' -> 0;
            case 'Y' -> 3;
            case 'Z' -> 6;
            default -> throw new IllegalStateException("Expected the character X, Y or Z not " + xyz);
        };
    }
}
