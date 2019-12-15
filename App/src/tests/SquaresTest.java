package tests;

import display.windows.Field;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class SquaresTest {

    private int height;
    private int width;
    private int expected;

    public SquaresTest(int height, int width, int expected) {
        this.height = height;
        this.width = width;
        this.expected = expected;
    }

    @Parameterized.Parameters()
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {2, 3, 6},
                {3, 3, 9},
                {2, 2, 4},
                {0, 0, 0},
                {1, 1, 1}
        });
    }

    @Test
    public void setSquares() {
        int h = height * 2 + 1;
        int w = width * 2 + 1;

        Assert.assertEquals(expected, new Field().setSquares(h, w).size());
    }

}
