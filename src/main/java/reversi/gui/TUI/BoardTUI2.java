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
public class BoardTUI2 {

    private Board board;
    private AIPlayer ai;
    private int width;
    private int height;

    public BoardTUI2(int boardSize, int searchDepth ,boolean playerBegins) {
        this.width = boardSize;
        this.height = boardSize;
        this.board = new BoardImpl(boardSize);
        ai = new AIPlayerImpl(Color.BLACK, searchDepth);
        if(playerBegins){
            this.playWithPlayerFirst(board);
        }else{
            this.playWithComputerFirst(board);
        }
    }
    private void drawState(Board b) {
        System.out.print("  ");
        for (int x = 0; x < this.width; x++) {
            System.out.print(x +" ");
        }
        for (int y = 0; y < this.height; y++) {
            System.out.print(String.format("\n%s ",y));
            for (int x = 0; x < this.width; x++) {
                System.out.print(showColor(b.getColor(x, y)));
                System.out.print(" ");
            }
        }
        System.out.println("");

    }
    private void finished(Board b){
        this.drawState(b);
        System.out.println("Das Spiel ist zuende. Der Spielstand lautet:");
        int white = b.getNumberOfStones(Color.WHITE);
        int black = b.getNumberOfStones(Color.BLACK);
        System.out.println("Sie: " + white+ " Gegner: " + black);
        if(black>white){
            System.out.println("Sie haben verloren");
        }else if(white==black)
        {
            System.out.println("Unentschieden!");
        }else{
            System.out.println("Sie haben gewonnen!!");
        }
    }
    private boolean playersTurn(Board b) throws IOException {
        System.out.println("Geben sie Ihre Auswahl in der Reihenfolge X,Y an:");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        boolean legitTurn=false;
        do{
            String s = bufferRead.readLine();
            legitTurn = b.setStone(splitInput(s));
            if(!legitTurn) {
                System.out.println("Zug war nicht konform. Versuchen sie es erneut");
            }
        }while(!legitTurn);
        System.out.println("Ihr Zug:");
        this.drawState(b);
        return b.isFinished();
    }
    private boolean computersTurn(Board b){
        System.out.println("Der Computer ist an der Reihe:");
        b.setStone(ai.nextTurn(b));
        this.drawState(b);
        return b.isFinished();
    }
    private void playWithPlayerFirst(Board b) {
        try {
           boolean isFinished=false;
            drawState(b);
            do{
               isFinished=playersTurn(b);
               if(!isFinished){
                   isFinished=computersTurn(b);
               }
            }while(!isFinished);
            finished(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void playWithComputerFirst(Board b) {
        try {
            boolean isFinished=false;
            drawState(b);
            do{
                isFinished=computersTurn(b);
                if(!isFinished){
                    isFinished=playersTurn(b);
                }
            }while(!isFinished);
            finished(b);
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
