package reversi.ai;

import reversi.api.Board;

/**
 * Created by Loki on 22.05.2014.
 */
public interface ReversiHeuristic {
    public int getValue(Board b);
}
