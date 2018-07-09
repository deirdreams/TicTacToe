import java.util.ArrayList;
import java.util.List;

public class Board {
    char[] board;

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

    /* Get the spots not occupied by player or computer */
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
        return isFull() || hasWon('X') || hasWon('O');
    }

    /* Check diagonals, rows, and cols if specific player has a match */
    boolean hasWon(char playerSymbol) {
        if ((board[0] == board[4] && board[4] == board[8] && board[8] == playerSymbol) ||
        (board[6] == board[4] && board[4] == board[2] && board[2] == playerSymbol)) {
            return true;
        }
        for (int i = 0; i < 3; i++) { //Checking rows and columns for a match
            if (board[i*3] == board[i*3+1] && board[i*3+1] == board[i*3+2] && board[i*3+2] == playerSymbol) {
                return true;
            }
            if (board[i] == board[i+3] && board[i+3] == board[i+6] && board[i+6] == playerSymbol) {
                return true;
            }
        }
        return false;
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
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i).charAt(0);
        }
    }

    boolean isValidMove(int spot) {
        return spot > -1 && spot < 9 && board[spot] != 'X' && board[spot] != 'O';
    }

}
