package reversi.gui.TUI;

import reversi.ai.AIPlayer;
import reversi.ai.impl.AIPlayerImpl;
import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;
import reversi.impl.BoardImpl;
import reversi.impl.TurnImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public BoardTUI(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new BoardImpl(width);
        ai = new AIPlayerImpl(Color.BLACK, 4);
        this.loop(this.board);
    }

    private void drawState(Board b) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(FIELD_TOP);
            }
            System.out.print("\n");
            for (int x = 0; x < this.width; x++) {
                System.out.print(FIELD_SIDE);
                System.out.print(showColor(b.getColor(x, y)));
                System.out.print(FIELD_SIDE);
            }
            System.out.print("\n");
            for (int x = 0; x < this.width; x++) {
                System.out.print(FIELD_TOP);
            }
            System.out.print("\n");
        }

    }

    private void loop(Board b) {
        try {
            this.drawState(b);
            System.out.println("Geben sie ihre auswahl in der Reihenfolge X,Y an:");
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            Turn t = splitInput(s);
            boolean legitTurn = b.setStone(t.getColor(), t.getX(), t.getY());
            if (legitTurn) {
                t = ai.nextTurn(b);
                b.setStone(t.getColor(), t.getX(), t.getY());
            }

            this.loop(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String showColor(Color c) {

        if (c == Color.BLACK)
            return "X";
        if (c == Color.WHITE)
            return "O";
        return " ";
    }

    private Turn splitInput(String in) {
        String s = in.trim();
        if (s.length() != 3) {
            return null;
        }
        String[] coords = s.split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        return new TurnImpl(x, y, Color.WHITE);
    }
}
