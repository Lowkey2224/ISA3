package reversi.gui.TUI;

import reversi.ai.AIPlayer;
import reversi.api.Board;
import reversi.api.Color;
import reversi.impl.BoardImpl;

/**
 * Created by marcus on 26.05.14.
 */
public class BoardTUI {

    private Board board;
    private AIPlayer ai;
    private int width;
    private int height;
    private final static String FIELD_TOP = "___";
    private final static String FIELD_SIDE = "|";

    public BoardTUI(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.board = new BoardImpl(width);
        this.loop(this.board);
    }

    private void drawState(Board b)
    {
        b.setStone(Color.BLACK, 0,1);
        b.setStone(Color.WHITE, 0,2);
        b.setStone(Color.BLACK, 1,1);
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(FIELD_TOP);
            }
            System.out.print("\n");
            for (int x = 0; x < this.width; x++) {
                System.out.print(FIELD_SIDE);
                System.out.print(showColor(b.getColor(x,y)));
                System.out.print(FIELD_SIDE);
            }
            System.out.print("\n");
            for (int x = 0; x < this.width; x++) {
                System.out.print(FIELD_TOP);
            }
            System.out.print("\n");
        }

        System.out.println("GIEF STONE");

    }

    private void loop(Board b)
    {
        this.drawState(b);

//        System.in.read()
    }

    private String showColor(Color c)
    {

        if(c == Color.BLACK)
            return "X";
        if(c == Color.WHITE)
            return "O";
        return " ";
    }
}
