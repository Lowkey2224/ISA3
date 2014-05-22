package reversi.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

/**
 * Created by Loki on 22.05.2014.
 */
public class BoardImpl implements Board {

    private Color[][] tiles;
    private int boardSize;
    private Turn lastTurn;

    public BoardImpl(int boardSize) {
        this.tiles = new Color[boardSize][boardSize];
        this.boardSize = boardSize;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.tiles[i][j] = Color.EMPTY;
            }
        }
    }

    private BoardImpl(int boardSize, Color[][] state)
    {
        this.tiles = new Color[boardSize][boardSize];
        this.boardSize = boardSize;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.tiles[i][j] = state[i][j];
            }
        }
    }

    @Override
    public boolean setStone(Color color, int x, int y) {
        if (x < 0 || x > this.boardSize || y < 0 || y > this.boardSize) {
            return false;
        }
        if (this.tiles[x][y] != Color.EMPTY) {
            return false;
        }

        this.tiles[x][y] = color;
        //Change Color of adjacent tiles.
        for(int i = x-1; i <= x+1; i++)
        {
            for (int j = y-1; j <= y+1; j++)
            {//If they are legit fields
                if (x > 0 && x < this.boardSize && y > 0 && y < this.boardSize) {
                    if(this.tiles[i][j] != Color.EMPTY)
                    {
                        this.tiles[i][j] = color;
                    }
                }
            }
        }
        this.lastTurn = new TurnImpl(x, y, color);
        return true;


    }

    @Override
    public Set<Board> getNextPosibleStates() {
        Set<Board> set = new HashSet<Board>()
    }

    @Override
    public int getNumberOfStones(Color color) {
        int amount = 0;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (this.tiles[i][j] == color) {
                    amount++;
                }
            }
        }
        return amount;
    }

    @Override
    public Turn getLastTurn() {
        return lastTurn;
    }

    public boolean equals(Object o)
    {
        if (o == null){
            return false;
        }
        if(o instanceof Board){
            Turn t = (Turn) o;
            //TODO Werteueberpruefung!
//            if(t.getColor() == this.color && t.getX() == this.x && t.getY() == this.y)
//            {
//                return true;
//            }
        }
        return false;
    }
}
