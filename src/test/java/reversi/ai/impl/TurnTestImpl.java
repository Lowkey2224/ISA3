package reversi.ai.impl;

import reversi.api.Color;
import reversi.api.Turn;

public class TurnTestImpl implements Turn {
    String name;

    public static Turn create(String name) {
	return new TurnTestImpl(name);
    }

    public TurnTestImpl(String name) {
	this.name = name;
    }

    @Override
    public int getX() {
	throw new RuntimeException("Not supported");
    }

    @Override
    public int getY() {
	throw new RuntimeException("Not supported");
    }

    @Override
    public Color getColor() {
	throw new RuntimeException("Not supported");
    }

    @Override
    public String toString() {
	return "TurnTestImpl [name=" + name + "]";
    }

}
