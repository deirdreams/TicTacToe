import java.util.*;

public class Computer {
    private static String difficulty;
    private static int probability;
    private static Random random;

    public Computer(String diff) {
       difficulty = diff;
       random = new Random();
       switch(difficulty) {
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


    private static int chooseRandom(Board b) {
        List<Integer> availableSpots = b.getAvailableSpots();
        int chosen = random.nextInt(availableSpots.size());
        return availableSpots.get(chosen);
    }

    public static void makeMove(Board b) {
        int chosen = random.nextInt(11);
        if (chosen > probability) {

        }
        else chooseRandom(b);
    }

    public static void main(String[] args) {

    }
}
