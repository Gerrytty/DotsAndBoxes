package tests;

import game.Point;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class PointTest {

    private int x;
    private int y;

    public PointTest(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Parameterized.Parameters()
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {2, 3},
                {3, 3},
                {2, 1},
                {0, 0},
                {1, 4}
        });
    }

    @Test
    public void equals() {

        Point point1 = new Point(x, y);
        Point point2 = new Point(x, y);

        Assert.assertEquals(point1, point2);
    }

}
