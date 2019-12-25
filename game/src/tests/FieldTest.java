package tests;

import display.windows.Field;
import org.junit.Assert;
import org.junit.Test;

public class FieldTest {

    @Test
    public void letterTest() {
        Field field = Field.getField();
        String letter = field.getLetter();
        Assert.assertEquals("A", letter);
    }

    @Test
    public void computerScoreTest() {
        int score = Field.getComputerScore();
        Assert.assertEquals(0, score);
    }

}
