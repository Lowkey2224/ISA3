package reversi.impl;

import reversi.api.Board;
import reversi.api.Color;

import java.util.Set;

/**
 * Created by Loki on 22.05.2014.
 */
public class BoardImpl implements Board{
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
}
