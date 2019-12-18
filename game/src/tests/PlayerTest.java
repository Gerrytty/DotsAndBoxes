package tests;

import game.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void letterTest() {
        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            char letter = player.getLetter().charAt(0);
            Assert.assertTrue(letter >= 65 && letter <= 90);
        }
    }

    @Test
    public void pointsTest() {
        Player player = new Player();
        player.addPoint();
        Assert.assertEquals(player.getPoints(), 1);
    }
}
