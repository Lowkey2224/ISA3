package reversi.ai.impl;

import org.junit.Before;
import org.junit.Test;

import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

public class MinMaxNodeTest {
    private MinMaxNode rootNode;

    @Before
    public void before() {
	// BoardTestImpl(nrblackstone,nrwhitestone, lastTurn,nextBoards)
	Board board_3_1 = new BoardTestImpl("3_1", 1, 2, null);
	Board board_3_2 = new BoardTestImpl("3_2", 1, 1, null);
	Board board_3_3 = new BoardTestImpl("3_3", 2, 1, null);
	Board board_3_4 = new BoardTestImpl("3_4", 2, 2, null);
	Board board_3_5 = new BoardTestImpl("3_5", 3, 1, null);
	Board board_3_6 = new BoardTestImpl("3_6", 3, 2, null);
	Board board_2_1 = new BoardTestImpl("2_1", 1, 0, TurnTestImpl.create("1_1 to 2_1"), board_3_1, board_3_2);
	Board board_2_2 = new BoardTestImpl("2_2", 2, 0, TurnTestImpl.create("1_1 to 2_2"), board_3_3, board_3_4);
	Board board_2_3 = new BoardTestImpl("2_3", 2, 1, TurnTestImpl.create("1_1 to 2_3"), board_3_5, board_3_6);
	Board board_1_1 = new BoardTestImpl("1_1", 0, 0, null, board_2_3, board_2_2, board_2_1);
	rootNode = new MinMaxNode(board_1_1, Color.BLACK);
    }

    @Test
    public void test() {
	Turn turn = rootNode.getBestTurn(1);
	System.out.println(turn);
    }
}
