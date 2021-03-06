import java.util.Scanner;

public class Main {
    private static Board board;
    private static int boardSize;
    private static Computer computer;

    private static boolean checkValidDifficulty(String s) {
        return s.equals("easy") || s.equals("med") || s.equals("hard");
    }

    private static boolean restartGame(String s) {
        if (s.equals("y") || s.equals("Y") || s.equals("yes")) {
            board.reset();
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
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Tic Tac Toe!");
            System.out.println("Please choose a difficulty (easy, med, hard):");
            String diff = scanner.next();
            //loop to check validity of difficulty answer
            while (!checkValidDifficulty(diff)) {
                System.out.println("Invalid answer. Please choose from the following: easy, med, hard");
                diff = scanner.next();
            }
            System.out.println("Desired board size (enter a single integer): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid board size. Please enter a single integer.");
            }
            boardSize = scanner.nextInt();
            board = new Board(boardSize);
            computer = new Computer(diff);
            System.out.println("Please choose a number indicating your move.");
            board.display();
            Point playerMove, computerMove;

            //main game loop that runs until the board is full or someone wins.
            while (!board.gameOver()) {
                while(!scanner.hasNextLine()) {
                    System.out.println("Please enter a valid number point separated by a space.");
                    scanner.next();
                }

                playerMove = new Point(scanner.nextInt(), scanner.nextInt());
                while (!board.isValidMove(playerMove)) {
                    System.out.println("That cell is taken or invalid. Please choose another.");
                    while(!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number from 0 to 8.");
                        scanner.next();
                    }
                    playerMove = new Point(scanner.nextInt(), scanner.nextInt());
                }
                board.update(playerMove, 'X');
                board.display();
                //check if player move is a winning move
                if (board.gameOver()) {
                    continue;
                }
                computerMove = computer.makeMove(board);
                System.out.println("Computer chooses: (" + computerMove.getX() + ", " + computerMove.getY() + ")");
                board.update(computerMove, 'O');
                board.display();
            }
            checkWin(board);

            System.out.println("Would you like to play again? (y/n)");
            playGame = restartGame(scanner.next());
        }
    }
}
