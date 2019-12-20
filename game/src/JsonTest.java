import game.Square;

import java.util.ArrayList;

public class JsonTest {
    ArrayList<Square> squares;

    public JsonTest() {
        squares = new ArrayList<>();
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
}
