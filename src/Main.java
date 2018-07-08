import java.util.Scanner;

public class Main {
    static Board board = new Board();
    static Computer computer;

    private static boolean checkValidDifficulty(String s) {
        return s.equals("easy") || s.equals("med") || s.equals("hard");
    }

    private static boolean restartGame(String s) {
        if (s.equals("y") || s.equals("Y")) {
            return true;
        } else {
            System.out.println("Thank you for playing. Good bye!");
            return false;
        }
    }

    private static void checkWin(Board b) {
        if (b.hasWon('X')) {
            System.out.println("Congratulations! You've won!");
        } else if (b.hasWon('O')) {
            System.out.println("Oh no! The computer won this time around.");
        } else {
            System.out.println("It's a tie game!");
        }
    }

    public static void main(String[] args) {
        boolean playGame = true;
        while(playGame) {
            board.reset();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Tic Tac Toe!");
            System.out.println("Please choose a difficulty (easy, med, hard):");
            String diff = scanner.next();
            while (!checkValidDifficulty(diff)) {
                System.out.println("Invalid answer. Please choose from the following: easy, med, hard");
                diff = scanner.next();
            }
            computer = new Computer(diff);
            System.out.println("Please choose a number indicating your move.");
            board.display();
            int playerMove, computerMove;
            while (!board.gameOver()) {
                while(!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number from 0 to 8.");
                    scanner.next();
                }
                playerMove = scanner.nextInt();
                while (!board.isValidMove(playerMove)) {
                    System.out.println("That cell is taken or invalid. Please choose another.");
                    playerMove = scanner.nextInt();
                }
                board.update(playerMove, 'X');
                board.display();
                if (board.gameOver()) {
                    continue;
                }
                computerMove = computer.makeMove(board);
                System.out.println("Computer chooses: Cell #" + computerMove);
                board.update(computerMove, 'O');
                board.display();
            }
            checkWin(board);

            System.out.println("Would you like to play again? (y/n)");
            playGame = restartGame(scanner.next());
        }
    }
}
