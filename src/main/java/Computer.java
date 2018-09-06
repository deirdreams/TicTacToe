import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Computer {
    private int probability;
    private Random random;
    private Point computerMove;

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
    Point chooseRandom(Board b) {
        List<Point> availableSpots = b.getAvailableSpots();
        int chosen = random.nextInt(availableSpots.size());
        return availableSpots.get(chosen);
    }

    /* The minimax recursive algorithm to maximise computer's score */
    int minimax(Board b, int depth, int player) {
        if (player < 0 || player > 1 || depth < 0) {
            throw new IllegalArgumentException("Player should be 0 or 1. Depth should be greater than 0.");
        }

        List<Point> availableSpots = b.getAvailableSpots();
        Map<Point, Integer> moveScores = new HashMap<>();
        int score;

        if (depth > b.getSize()) { return 0; }

        //scores based on depth and winner
        if (b.gameOver()) { //Want 'O' (the computer) to have the best score
            if (b.getWinner() == 'O') {
                return 10-depth;
            } else if (b.getWinner() == 'X') {
                return depth-10;
            } else {
                return 0;
            }
        }
        if (availableSpots.size() == 0) return 0;

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int i = 0; i < availableSpots.size(); i++) {
            Point spot = availableSpots.get(i);
            if (player == 0) { //player 0 represents AI
                b.update(spot, 'O');
                score = minimax(b, depth + 1, 1);
                max = Math.max(score, max);
                if (depth == 0) {
                    moveScores.put(spot, score);
                }

            } else { //player 1 represents human
                b.update(spot, 'X');
                score = minimax(b, depth + 1, 0);
                min = Math.min(score, min);
            }
            b.board[spot.getX()][spot.getY()] = ' ';
        }

        //set the computer's chosen move to the spot with the best score
        computerMove = getMaxIndex(moveScores);
        return player == 0 ? max : min;
    }

    /* Get the index with the maximum value, i.e. the best move */
    Point getMaxIndex(Map<Point, Integer> map) {
        int max = Integer.MIN_VALUE;
        Point maxIndex = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Map.Entry<Point, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxIndex = entry.getKey();
            }
        }
        return maxIndex;
    }

    /* Deciding whether to use minimax or random for the next move based on the probability */
    Point makeMove(Board b) {
        int chosen = random.nextInt(11);
        if (chosen > probability) {
            minimax(b,0, 0);
            return computerMove;
        }
        return chooseRandom(b);
    }
}
