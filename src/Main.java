import java.util.Scanner;

public class Main {
    static Board board = new Board();
    static Computer computer;

    private static boolean checkValidDifficulty(String s) {
        return s.equals("easy") || s.equals("med") || s.equals("hard");
    }

    public static void main(String[] args) {
        board.reset();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Please choose a difficulty (easy, med, hard):");
        String diff = scanner.next();
        while (!checkValidDifficulty(diff)) {
            System.out.println("Invalid difficulty. Please choose from the following: easy, med, hard");
            diff = scanner.next();
        }
        computer = new Computer(diff);
        System.out.println("Please choose a number indicating your move.");
        board.display();
        int playerMove, computerMove;
        while (!board.gameOver()) {
            playerMove = scanner.nextInt();
            while (!board.isValidMove(playerMove)) {
                System.out.println("That cell is taken. Please choose another.");
                playerMove = scanner.nextInt();
            }
            board.update(playerMove, 'X');
            board.display();
            computerMove = computer.makeMove(board);
            System.out.println("Computer chooses: Cell #" + computerMove);
            board.update(computerMove, 'O');
            board.display();
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
