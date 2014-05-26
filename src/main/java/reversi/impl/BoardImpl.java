package reversi.impl;

import java.util.HashSet;
import java.util.Set;

import reversi.ai.ReversiHeuristic;
import reversi.ai.Heuristicable;
import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

/**
 * Created by Loki on 22.05.2014.
 */
public class BoardImpl implements Board, Heuristicable {

    private Color[][] tiles;
    private int boardSize;
    private Turn lastTurn;
    private ReversiHeuristic heuristic;

    public BoardImpl(int boardSize) {
        this.tiles = new Color[boardSize][boardSize];
        this.boardSize = boardSize;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.tiles[i][j] = Color.EMPTY;
            }
        }
    }

    private BoardImpl(int boardSize, Color[][] state) {
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
        Turn t = new TurnImpl(x, y, color);
        if (!this.isTurnValid(t)) {
            return false;
        }
        this.tiles[x][y] = color;
        //Change Color of adjacent tiles.
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {//If they are legit fields
                if (i >= 0 && i < this.boardSize && j >= 0 && j < this.boardSize) {
                    if (this.tiles[i][j] != Color.EMPTY) {
                        this.tiles[i][j] = color;
                    }
                }
            }
        }
        this.lastTurn = t;
        return true;
    }

    public boolean setStone(Turn turn) {
        if(turn == null || turn.getColor() == Color.EMPTY)
            return false;
        return this.setStone(turn.getColor(), turn.getX(), turn.getY());
    }

    @Override
    public Set<Board> getNextPosibleStates() {
        Set<Board> set = new HashSet<Board>();
        Color c = (lastTurn.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (this.tiles[i][j] == Color.EMPTY) {
                    Board b = new BoardImpl(this.boardSize, this.tiles);
                    b.setStone(c, i, j);
                    set.add(b);
                }
            }
        }
        return set;
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
    public int getBoardSize() {
        return this.boardSize;
    }

    @Override
    public Color getColor(int x, int y) {
        if (x < 0 || x > this.boardSize || y < 0 || y > this.boardSize) {
            return null;
        }
        return this.tiles[x][y];
    }

    private boolean isTurnValid(Turn t) {
        if (t == null || (lastTurn != null && t.getColor() == lastTurn.getColor())) {
            return false;
        }
        if (t.getX() < 0 || t.getX() > this.boardSize || t.getY() < 0 || t.getY() > this.boardSize) {
            return false;
        }
        if (this.tiles[t.getX()][t.getY()] != Color.EMPTY) {
            return false;
        }
        return true;
    }

    @Override
    public Turn getLastTurn() {
        return lastTurn;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Board) {
            Board b = (Board) o;
            if (this.boardSize != b.getBoardSize()) {
                return false;
            }
            for (int x = 0; x < this.boardSize; x++) {
                for (int y = 0; y < this.boardSize; y++) {
                    if (this.tiles[x][y] != b.getColor(x, y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void setHeuristic(ReversiHeuristic h) {
        this.heuristic = h;
    }

    @Override
    public int getValue() {
        return this.heuristic.getValue(this);
    }
}
