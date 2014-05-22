package reversi.api;

import java.util.Set;

/**
 * oben links 0,0 unten rechts 4,4
 * 
 * @author jannik
 *
 */
public interface Board {
    /**
     * 
     * @param color
     * @param x
     * @param y
     * @return if turn is valid
     */
    public boolean setStone(Color color, int x, int y);

    public Set<Board> getNextPosibleStates();

    public int getNumberOfStones(Color color);

}
