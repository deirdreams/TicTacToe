import java.util.*;

public class Computer {
    private int probability;
    private Random random;

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
               probability = 2;
               break;
           default:
               probability = 4;
               break;
       }
    }


    int chooseRandom(Board b) {
        List<Integer> availableSpots = b.getAvailableSpots();
        int chosen = random.nextInt(availableSpots.size());
        return availableSpots.get(chosen);
    }

    int makeMove(Board b) {
        int chosen = random.nextInt(11);
        if (chosen > probability) {
            b.minimax(0, 0);
            return b.computerMove;
        }
        return chooseRandom(b);
    }
}
