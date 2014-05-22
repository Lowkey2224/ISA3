package reversi.ai.impl;

import reversi.ai.AIPlayer;
import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

public class AIPlayerImpl implements AIPlayer {
    private final Color myColor;
    private final int searchDeep;

    public AIPlayerImpl(Color color, int searchDeep) {
	myColor = color;
	this.searchDeep = searchDeep;
    }

    @Override
    public Turn nextTurn(Board board) {
	MinMaxNode minMaxNode = new MinMaxNode(board, myColor);
	return minMaxNode.getBestTurn(searchDeep);
    }
}
