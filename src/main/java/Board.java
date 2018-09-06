import java.util.ArrayList;
import java.util.List;

public class Board {
    static char[][] board;
    private char winner;
    private int n;

    Board(int n) {
        this.n = n;
        board = new char[n][n];
        winner = '-';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ' ';
            }
        }
    }

    char getWinner() {
        return winner;
    }

    int getSize() { return n; }

    boolean isFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c != 'X' && c!= 'O') return false;
            }
        }
        return true;
    }

    /* Get the spots not occupied by player or computer */
    List<Point> getAvailableSpots() {
        List<Point> availableSpots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    availableSpots.add(new Point(i, j));
                }
            }
        }
        return availableSpots;
    }

    void update(Point p, char move) {
        if (move != 'X' && move != 'O') {
            throw new IllegalArgumentException("Argument must be an 'X' or 'O'.");
        }
        board[p.getX()][p.getY()] = move;
    }

    boolean gameOver() {
        if (hasWon('X')) {
            winner = 'X';
            return true;
        }
        if (hasWon('O')) {
            winner = 'O';
            return true;
        }
        return isFull();
    }

    /* Check diagonals, rows, and cols if specific player has a match */
    boolean hasWon(char playerSymbol) {
        int result = 0;
        for (int i = 0; i < n; i++) { //diagonal-left
            if (board[i][i] == playerSymbol) result++;
            else break;
        }
        if (result == n) return true;

        result = 0;
        for (int i = 0; i < n; i++) { //diagonal-right
            if (board[i][n-1-i] == playerSymbol) result++;
            else break;
        }
        if (result == n) return true;


        for (int i = 0; i < n; i++) { //rows
            result = 0;
            int j = 0;
            while(j < n) {
                if (board[i][j++] == playerSymbol) result++;
                else break;
            }
            if (result == n) return true;
        }

        for (int j = 0; j < n; j++) { //columns
            result = 0;
            int i = 0;
            while (i < n) {
                if (board[i++][j] == playerSymbol) result++;
                else break;
            }
            if (result == n) return true;
        }
        return false;
    }

    void display() {
        System.out.print("    ");
        for (int i = 0; i < n; i++) {
            System.out.print(String.valueOf(i) + "   ");
        }
        System.out.println();
        System.out.print("  +---+");
        for (int i = 0; i < n-1; i++) {
            System.out.print("---+");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print( String.valueOf(i) + " | ");
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.print("  +---+");
            for (int k = 0; k < n-1; k++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }

    void reset() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ' ';
            }
        }
        winner = '-';
    }

    boolean isValidMove(Point spot) {
        if (spot.getX() < 0 || spot.getX() >= n || spot.getY() < 0 || spot.getY() >= n) return false;
        return board[spot.getX()][spot.getY()] != 'X' && board[spot.getX()][spot.getY()] != 'O';
    }


}
