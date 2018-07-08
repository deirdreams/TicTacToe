import java.util.Scanner;

public class Main {
    static Board board = new Board();
    static Computer computer;


    public static void main(String[] args) {
        board.reset();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Please choose a difficulty (easy, med, hard):");
        String diff = scanner.next();
//        while (!diff.equals("easy") || !diff.equals("med") || !diff.equals("hard")) {
//            System.out.println("Invalid difficulty. Please choose from the following: easy, med, hard");
//            diff = scanner.next();
//        }
        computer = new Computer(diff);
        System.out.println("Please choose a number indicating your move.");
        int move;
        while (!board.gameOver()) {
            board.display();
            move = scanner.nextInt();
            while (!board.isValidMove(move)) {
                System.out.println("That cell is taken. Please choose another.");
                move = scanner.nextInt();
            }
            board.update(move, 'X');
            board.display();
            board.minimax(0, 0);
            System.out.println("Computer chooses: " + board.computerMove);
            board.update(board.computerMove, 'O');
        }
        if (board.gameOver()) {
            if (board.winner == 'X') {
                System.out.println("Congratulations! You've won!");
            } else if (board.winner == 'O') {
                System.out.println("Oh no! The computer won this time around.");
            } else {
                System.out.println("Tie game!");
            }
        }
    }
}
