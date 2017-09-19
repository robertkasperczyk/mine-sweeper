import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MineSweeperImplTest {
    private MineSweeperImpl mineSweeper;


    @Before
    public void setUp() throws Exception {
        mineSweeper = new MineSweeperImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMineFieldWhenNull() throws Exception {
        //when
        mineSweeper.setMineField(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMineFieldWhenEmptyString() throws Exception {
        //when
        mineSweeper.setMineField("");
    }

    @Test()
    public void testSetMineFieldWhenProperString() throws Exception {
        char[][] expected = {
                {'*', '.', '.'},
                {'.', '.', '*'}
        };

        //given
        String input = "*..\n..*";

        //when
        mineSweeper.setMineField(input);

        //then
        assertArrayEquals(expected, mineSweeper.getMineField());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMineFieldWhenImproperString() throws Exception {

        //given
        String input = "*..\n...*";

        //when
        mineSweeper.setMineField(input);

    }

    @Test
    public void testGetHintFieldWithProperMineField() throws Exception {
        //given
        mineSweeper.setMineField("*...\n..*.\n....");

        //when
        String result = mineSweeper.getHintField();

        //then
        assertEquals("*211\n12*1\n0111", result);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetHintFieldWithNotInitialisedMineField() throws Exception {

        //when
        mineSweeper.getHintField();

    }

}