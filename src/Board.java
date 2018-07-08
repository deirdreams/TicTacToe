import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    char[] board;
    char winner;
    int computerMove;

    Board() {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i).charAt(0);
        }
    }

    boolean isFull() {
        for (char c : board) {
            if (c != 'X' && c!= 'O') return false;
        }
        return true;
    }

    List<Integer> getAvailableSpots() {
        List<Integer> availableSpots = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                availableSpots.add(i);
            }
        }
        return availableSpots;
    }

    void update(int i, char move) {
        board[i] = move;
    }

    boolean gameOver() {
        if (isFull()) return true;
        if ((board[0] == board[4] && board[4] == board[8]) ||
                (board[6] == board[4] && board[4] == board[2])) {
            winner = board[4];
            return true;
        }
        for (int i = 0; i < 3; i++) { //Checking rows and columns
            if (board[i*3] == board[i*3+1] && board[i*3+1] == board[i*3+2]) {
                winner = board[i*3+1];
                return true;
            }
            if (board[i] == board[i+3] && board[i+3] == board[i+6]) {
                winner = board[i+3];
                return true;
            }
        }
        return false;
    }

    /* The minimax recursive algorithm to maximise computer's score */
    int minimax(int depth, int player) {
        Board copy = this;
        List<Integer> availableSpots = getAvailableSpots();
        Map<Integer, Integer> moveScores = new HashMap<>();
        int score;
        if (gameOver()) { //Want 'O' (the computer) to have the best score
            if (winner == 'O') {
                return 10-depth;
            } else if (winner == 'X') {
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
                score = minimax(depth + 1, 1);
                max = Math.max(score, max);
                if (depth == 0) {
                    moveScores.put(spot, score);
                }

            } else if (player == 1) { //player 1 represents human
                copy.update(spot, 'X');
                score = minimax(depth + 1, 0);
                min = Math.min(score, min);
                if (min == -10) {
                    copy.board[spot] = String.valueOf(spot).charAt(0);
                }
            }
            copy.board[spot] = String.valueOf(spot).charAt(0);
        }
        computerMove = getMaxIndex(moveScores);
        return player == 0 ? max : min;
    }

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

    void display() {
        System.out.println(" +---+---+---+");
        for (int i = 0; i < 3; i++) {
            System.out.print( " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[3*i+j] + " | ");
            }
            System.out.println();
            System.out.println(" +---+---+---+");
        }
    }

    void reset() {
        winner = '-';
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i).charAt(0);
        }
    }

    boolean isValidMove(int spot) {
        return board[spot] != 'X' && board[spot] != 'O';
    }

}
