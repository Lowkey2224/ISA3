package reversi.ai.impl;

import reversi.ai.AIPlayer;
import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

public class AIPlayerImpl implements AIPlayer {
    private final Color myColor;
    private final int searchDeep;

    public AIPlayerImpl(Color color, int searchDeep, boolean abEnabled) {
	myColor = color;
	this.searchDeep = searchDeep;
    }

    @Override
    public Turn nextTurn(Board board) {
	MinMaxNode minMaxNode = new MinMaxNode(board, myColor);
	long start=System.currentTimeMillis(); 
	Turn best=minMaxNode.getBestTurn(searchDeep);
	System.out.println(String.format("Der Computer hat %s Milisekungen lang überlegt.", System.currentTimeMillis()-start));
	return best;
	
    }
}
