import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class BoardTest {

    private Board b = new Board();

    @Mock
    private ArrayList<Integer> mockList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void boardInitialisesWithCorrectNumbers() {
        for (int i = 0; i < 9; i++) {
            Assert.assertEquals(b.board[i], String.valueOf(i).charAt(0));
        }
    }

    @Test
    public void ifFullReturnsTrueIfFull() {
        for (int i = 0; i < 9; i++) {
            b.update(i,'X');
        }
        Assert.assertTrue(b.isFull());
    }

    @Test
    public void hasWonReturnsCorrectBooleanBasedOnWinner() {
        b.update(0, 'X');
        b.update(1, 'X');
        b.update(2, 'X');
        Assert.assertTrue(b.hasWon('X'));
        Assert.assertFalse(b.hasWon('O'));
    }

}
