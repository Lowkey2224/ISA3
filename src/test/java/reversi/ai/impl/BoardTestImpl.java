package reversi.ai.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

public class BoardTestImpl implements Board {
    private Set<Board> nextPossibleBoards;
    private int blackStones;
    private int whiteStones;
    private Turn lastTurn;
    private String name;

    public BoardTestImpl(String name, int numberBlackStones, int numberWhiteStones, Turn lastTurn,
	    Board... nextPossibleBoards) {
	this.name = name;
	this.blackStones = numberBlackStones;
	this.whiteStones = numberWhiteStones;
	this.lastTurn = lastTurn;
	this.nextPossibleBoards = new HashSet<Board>(Arrays.asList(nextPossibleBoards));
    }

    @Override
    public boolean setStone(Color color, int x, int y) {
	throw new RuntimeException("not supported");
    }

    @Override
    public Set<Board> getNextPosibleStates() {
	return nextPossibleBoards;
    }

    @Override
    public Turn getLastTurn() {
	return lastTurn;
    }

    @Override
    public int getNumberOfStones(Color color) {
	switch (color) {
	case BLACK:
	    return blackStones;
	case WHITE:
	    return whiteStones;
	default:
	    throw new RuntimeException("not supported");
	}
    }

    @Override
    public String toString() {
	return "BoardTestImpl [name=" + name + "]";
    }

}
