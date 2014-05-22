package reversi.ai;

import reversi.api.Board;
import reversi.api.Turn;

public interface AIPlayer {
    public Turn nextTurn(Board board);
}
