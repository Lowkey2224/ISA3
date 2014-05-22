package reversi.impl;

import java.util.Set;

import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

/**
 * Created by Loki on 22.05.2014.
 */
public class BoardImpl implements Board{

    private Color[][] tiles;
    private int boardSize;

    public BoardImpl(int boardSize)
    {
        this.tiles = new Color[boardSize][boardSize];
        this.boardSize = boardSize;
        for (int i = 0; i< boardSize; i++)
        {
            for(int j = 0; j < boardSize;  j++)
            {
                tiles[i][j]= Color.EMPTY;
            }
        }
    }

    @Override
    public boolean setStone(Color color, int x, int y) {
        return false;
    }

    @Override
    public Set<Board> getNextPosibleStates() {
        return null;
    }

    @Override
    public int getNumberOfStones(Color color) {
        return 0;
    }

    @Override
    public Turn getLastTurn() {
	// TODO Auto-generated method stub
	return null;
    }
}
