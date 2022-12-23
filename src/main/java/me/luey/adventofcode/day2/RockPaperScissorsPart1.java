package me.luey.adventofcode.day2;

import me.luey.adventofcode.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RockPaperScissorsPart1 {

    private enum Move {
        ROCK ('A', 'X'),
        PAPER ('B', 'Y'),
        SCISSORS ('C', 'Z');

        private final char abc;
        private final char xyz;


        private static final Map<Character,Move> charMapping;
        static {
            charMapping = new HashMap<>();
            for (Move v : Move.values()) {
                charMapping.put(v.abc, v);
                charMapping.put(v.xyz, v);
            }
        }

        Move (char abc, char xyz) {
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

                if ((opponent.equals(PAPER) && mine.equals(ROCK)) ||
                    (opponent.equals(ROCK) && mine.equals(SCISSORS)) ||
                    (opponent.equals(SCISSORS) && mine.equals(PAPER))) {
                    return -1;
                }

                return 1;
            }
        };
    }

    public static void main(String[] args) {
        String score = Utils.readFile("strategy-guide.txt", RockPaperScissorsPart1::calculateScore);

        System.out.println("Total score: " + score);
    }

    public static String calculateScore(BufferedReader bufferedReader) {
        int score = 0;

        String line;
        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null))
                    break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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