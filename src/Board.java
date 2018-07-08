public class Board {
    static char[][] board;
    static int numMoves;

    public Board() {
        numMoves = 0;
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static boolean isFull() {
        return numMoves == 9;
    }

    public static void update(int i, int j, char move) {
        board[i][j] = move;
        numMoves++;
    }

    public static boolean matchMade() {
        return checkDiagonals() || checkCols() || checkRows();
    }

    public static boolean checkDiagonals() {
        return (board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    public static boolean checkCols() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        return false;
    }

    public static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
        }
        return false;
    }

    public static void display() {
        System.out.println(" + 1 + 2 + 3 +");
        System.out.println(" +---+---+---+");
        for (int i = 0; i < 3; i++) {
            System.out.print(i+1 + "| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println(" +---+---+---+");
        }
    }

    public static void main(String[] args) {
        Board b = new Board();
        b.update(0, 2, 'X');
        b.update(1, 1, 'X');
        b.update(2, 0, 'O');
        if (b.matchMade()) {
            System.out.println("X is the winner!");
        }
        b.display();
    }
}
