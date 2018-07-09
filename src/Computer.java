import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Computer {
    private int probability;
    private Random random;
    private int computerMove;

    Computer(String diff) {
       random = new Random();
       switch(diff) {
           case "easy":
                probability = 8;
                break;
           case "med":
               probability = 4;
               break;
           case "hard":
               probability = 1;
               break;
           default:
               probability = 4;
               break;
       }
    }

    /* Choosing a random empty spot on the board */
    int chooseRandom(Board b) {
        List<Integer> availableSpots = b.getAvailableSpots();
        int chosen = random.nextInt(availableSpots.size());
        return availableSpots.get(chosen);
    }

    /* The minimax recursive algorithm to maximise computer's score */
    int minimax(Board b, int depth, int player) {
        Board copy = b;
        List<Integer> availableSpots = copy.getAvailableSpots();
        Map<Integer, Integer> moveScores = new HashMap<>();
        int score;

        //scores based on depth and winner
        if (copy.gameOver()) { //Want 'O' (the computer) to have the best score
            if (copy.hasWon('O')) {
                return 10-depth;
            } else if (copy.hasWon('X')) {
                return depth-10;
            } else {
                return 0;
            }
        }
        if (availableSpots.size() == 0) return 0;

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int i = 0; i < availableSpots.size(); i++) {
            int spot = availableSpots.get(i);
            if (player == 0) { //player 0 represents AI
                copy.update(spot, 'O');
                score = minimax(copy, depth + 1, 1);
                max = Math.max(score, max);
                if (depth == 0) {
                    moveScores.put(spot, score);
                }

            } else if (player == 1) { //player 1 represents human
                copy.update(spot, 'X');
                score = minimax(copy, depth + 1, 0);
                min = Math.min(score, min);
                if (min == -10) {
                    copy.board[spot] = String.valueOf(spot).charAt(0);
                }
            }
            copy.board[spot] = String.valueOf(spot).charAt(0);
        }

        //set the computer's chosen move to the spot with the best score
        computerMove = getMaxIndex(moveScores);
        return player == 0 ? max : min;
    }

    /* Get the index with the maximum value, i.e. the best move */
    int getMaxIndex(Map<Integer, Integer> map) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxIndex = entry.getKey();
            }
        }
        return maxIndex;
    }

    /* Deciding whether to use minimax or random for the next move based on the probability */
    int makeMove(Board b) {
        int chosen = random.nextInt(11);
        if (chosen > probability) {
            minimax(b,0, 0);
            return computerMove;
        }
        return chooseRandom(b);
    }
}
