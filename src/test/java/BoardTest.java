import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class BoardTest {

    private int BOARD_SIZE = 5;
    private Board b = new Board(BOARD_SIZE);

    @Mock
    private ArrayList<Integer> mockList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void boardInitialisesWithCorrectNumbers() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Assert.assertEquals(b.board[i][j], ' ');
            }
        }
    }

    @Test
    public void ifFullReturnsTrueIfFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                b.update(new Point(i, j), 'X');
            }
        }
        Assert.assertTrue(b.isFull());
    }

    @Test
    public void hasWonReturnsCorrectBooleanBasedOnWinner() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            b.update(new Point(i, i), 'X');
        }
        Assert.assertTrue(b.hasWon('X'));
        Assert.assertFalse(b.hasWon('O'));
    }

    @Test
    public void isValidMoveReturnsTrueIfSpotIsNotTaken() {
        Assert.assertTrue(b.isValidMove(new Point(0,0)));
    }

    @Test
    public void isValidMoveReturnsFalseIfNotInRangeOrSpotTaken() {
        Assert.assertFalse(b.isValidMove(new Point(-20, 3)));
        b.update(new Point(0, 3), 'O');
        Assert.assertFalse(b.isValidMove(new Point(0, 3)));
    }

    @Test
    public void gameOverUpdatesWinner() {
        for (int j = 0; j < BOARD_SIZE; j++) {
            b.update(new Point(0, j), 'O');
        }
        b.gameOver();
        Assert.assertEquals(b.getWinner(), 'O');
    }

    @Test
    public void getAvailableSpotsReturnsCorrectList() {
        Point p1 = new Point(0, 4);
        Point p2 = new Point(3, 3);
        b.update(p1, 'X');
        b.update(p2, 'O');
        Assert.assertFalse(b.getAvailableSpots().contains(p1));
        Assert.assertFalse(b.getAvailableSpots().contains(p2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateThrowsExceptionIfInvalidArgument() {
        b.update(new Point(3,2), 'C');
    }

}
