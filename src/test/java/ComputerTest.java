import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class ComputerTest {

    private Computer c = new Computer("easy");

    @Mock
    private ArrayList<Point> mockList;

    @Mock
    private Board mockBoard;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMaxIndexReturnsCorrectValue() {
        Map<Point, Integer> testMap = new HashMap<>();
        Point maxIndex = new Point(0, 4);
        testMap.put(new Point(3, 1), 7);
        testMap.put(new Point(1, 1), 6);
        testMap.put(maxIndex, 10);
        testMap.put(new Point(2, 2), 3);
        Assert.assertEquals(c.getMaxIndex(testMap), maxIndex);
    }

    @Test
    public void chooseRandomReturnsElementFromAvailableSpots() {
        when(mockBoard.getAvailableSpots()).thenReturn(mockList);
        when(mockList.size()).thenReturn(2);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 3);
        when(mockList.get(0)).thenReturn(p1);
        when(mockList.get(1)).thenReturn(p2);
        Point actual = c.chooseRandom(mockBoard);

        Assert.assertTrue(actual == p1 || actual == p2);
    }

    @Test
    public void minimaxReturnsZeroWhenNoAvailableSpots() {
        when(mockBoard.getAvailableSpots()).thenReturn(mockList);
        when(mockList.size()).thenReturn(0);

        Assert.assertEquals(c.minimax(mockBoard, 0, 0), 0);
    }

    @Test
    public void minimaxReturnsCorrectScoreBasedOnWinningPlayer() {
        when(mockBoard.gameOver()).thenReturn(true);
        when(mockBoard.getWinner()).thenReturn('O');

        Assert.assertEquals(c.minimax(mockBoard, 3, 0), 7);

        when(mockBoard.getWinner()).thenReturn('X');

        Assert.assertEquals(c.minimax(mockBoard, 3, 0), -7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minimaxThrowsExceptionWhenPlayerIsNot1Or0() {
        c.minimax(mockBoard, 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minimaxThrowsExceptionWhenDepthIsLessThan0() {
        c.minimax(mockBoard, -10, 1);
    }
}
