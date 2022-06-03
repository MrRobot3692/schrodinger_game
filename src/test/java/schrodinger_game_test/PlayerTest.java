package schrodinger_game_test;

import com.example.schrodinger_game.Player;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerTest {

    Player player;

    @BeforeMethod
    public void setUp() {
        player = new Player("name", 10);
    }

    @AfterMethod
    public void tearDown() {
        player = null;
    }

    @Test
    public void testPay() {
        player.pay(1);
        Assert.assertEquals(player.getNumber(), Integer.valueOf(9));
    }

    @Test
    public void testGetCount() {
        player.addCount(1);
        Assert.assertEquals(player.getCount(), Integer.valueOf(1));
    }
}