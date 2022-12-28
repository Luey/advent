package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Day2 implements Solution {
    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        return Part1.calculateScore(bufferedReader);
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        return Part2.calculateScore(bufferedReader);
    }
}

class Part1 {
    private enum Move {
        ROCK('A', 'X'), PAPER('B', 'Y'), SCISSORS('C', 'Z');

        private final char abc;
        private final char xyz;


        private static final Map<Character, Move> charMapping;

        static {
            charMapping = new HashMap<>();
            for (Move v : Move.values()) {
                charMapping.put(v.abc, v);
                charMapping.put(v.xyz, v);
            }
        }

        Move(char abc, char xyz) {
            this.abc = abc;
            this.xyz = xyz;
        }

        public static Move fromABC(char abc) {
            return charMapping.get(abc);
        }

        public static Move fromXYZ(char xyz) {
            return charMapping.get(xyz);
        }

        public static final Comparator<Move> winComparator = new Comparator<>() {
            public int compare(Move opponent, Move mine) {
                if (opponent.equals(mine)) {
                    return 0;
                }

                if ((opponent.equals(PAPER) && mine.equals(ROCK)) || (opponent.equals(ROCK) && mine.equals(SCISSORS)) || (opponent.equals(SCISSORS) && mine.equals(PAPER))) {
                    return -1;
                }

                return 1;
            }
        };
    }

    public static String calculateScore(BufferedReader bufferedReader) throws IOException {
        int score = 0;

        String line;
        while (true) {
            if (!((line = bufferedReader.readLine()) != null))
                break;
            Move opponentsMove = Move.fromABC(line.charAt(0));
            Move myMove = Move.fromXYZ(line.charAt(2));

            System.out.println("Me: " + myMove + " vs opponent: " + opponentsMove);

            score += scoreMyMove(myMove);
            score += scoreMatch(opponentsMove, myMove);
        }

        return String.valueOf(score);
    }

    private static int scoreMyMove(Move move) {
        switch (move) {
            case ROCK:
                System.out.println("Adding 1 point for playing rock");
                return 1;
            case PAPER:
                System.out.println("Adding 2 points for playing paper");
                return 2;
            case SCISSORS:
                System.out.println("Adding 3 points for playing scissors");
                return 3;
            default:
                throw new RuntimeException("You played an unknown move: " + move);
        }
    }

    private static int scoreMatch(Move opponent, Move mine) {
        switch (Move.winComparator.compare(opponent, mine)) {
            case -1:
                System.out.println("I lose by playing " + mine + " against " + opponent);
                return 0;
            case 0:
                System.out.println("Draw as we both played " + mine);
                return 3;
            case 1:
                System.out.println("I win by playing " + mine + " against " + opponent);
                return 6;
            default:
                throw new RuntimeException("Comparator cannot assess move");
        }
    }
}

class Part2 {
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

    public static String calculateScore(BufferedReader bufferedReader) throws IOException {
        int score = 0;

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Move opponentsMove = Move.fromABC(line.charAt(0));
            char winLoseDraw = line.charAt(2);

            System.out.println("Me: " + winLoseDraw + " vs opponent: " + opponentsMove);

            score += scoreWinLoseDraw(winLoseDraw);
            score += scoreMyMove(getMyMove(opponentsMove, winLoseDraw));
        }

        return String.valueOf(score);
    }

    private static Move getMyMove(Move opponentsMove, char winLoseDraw) {
        return switch (winLoseDraw) {
            case 'X' -> Move.toLose(opponentsMove);
            case 'Y' -> opponentsMove;
            case 'Z' -> Move.toWin(opponentsMove);
            default -> throw new IllegalStateException("Unexpected value: " + winLoseDraw);
        };
    }

    private static int scoreMyMove(Move move) {
        return switch (move) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSORS -> 3;
        };
    }

    private static int scoreWinLoseDraw(char xyz) {
        return switch (xyz) {
            case 'X' -> 0;
            case 'Y' -> 3;
            case 'Z' -> 6;
            default -> throw new IllegalStateException("Expected the character X, Y or Z not " + xyz);
        };
    }
}
