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
    private ArrayList<Integer> mockList;

    @Mock
    private Board mockBoard;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMaxIndexReturnsCorrectValue() {
        Map<Integer, Integer> testMap = new HashMap<>();
        testMap.put(2, 7);
        testMap.put(3, 6);
        testMap.put(8, 10);
        testMap.put(10, 3);
        Assert.assertEquals(c.getMaxIndex(testMap), 8);
    }

    @Test
    public void chooseRandomReturnsElementFromAvailableSpots() {
        when(mockBoard.getAvailableSpots()).thenReturn(mockList);
        when(mockList.size()).thenReturn(2);
        when(mockList.get(0)).thenReturn(3);
        when(mockList.get(1)).thenReturn(4);
        int actual = c.chooseRandom(mockBoard);

        Assert.assertTrue(actual == 3 || actual == 4);
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
        when(mockBoard.hasWon('O')).thenReturn(true);

        Assert.assertEquals(c.minimax(mockBoard, 3, 0), 7);

        when(mockBoard.hasWon('O')).thenReturn(false);
        when(mockBoard.hasWon('X')).thenReturn(true);

        Assert.assertEquals(c.minimax(mockBoard, 3, 0), -7);
    }
}
