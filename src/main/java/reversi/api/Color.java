package reversi.api;

public enum Color {
    EMPTY, BLACK, WHITE;

    public Color getOtherColor() {
	switch (this) {
	case BLACK:
	    return Color.WHITE;
	case EMPTY:
	    throw new IllegalArgumentException("EMPTY is no color");
	case WHITE:
	    return Color.BLACK;
	default:
	    throw new RuntimeException("should never happen");

	}
    }
}
