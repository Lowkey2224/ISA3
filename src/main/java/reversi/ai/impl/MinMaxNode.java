package reversi.ai.impl;

import java.util.HashSet;
import java.util.Set;

import Util.LogLevel;
import reversi.api.Board;
import reversi.api.Color;
import reversi.api.Turn;

public class MinMaxNode {
    private static final LogLevel LOG_LEVEL = LogLevel.Error;
    private Board board;
    private Color color;
    private Set<MinMaxNode> children;
    private MinMaxNode maxChild;
    private MinMaxNode minChild;

    public MinMaxNode(Board board, Color color) {
        this.board = board;
        this.color = color;
    }
    private int maxAB(int alpha, int beta, int depth){
    	 if (depth < 0) {
             throw new IllegalArgumentException(String.format("positiv value or 0 expected, but was %s", depth));
         }
         // Maximale Tiefe erreicht
         if (depth == 0) {
             if (LOG_LEVEL == LogLevel.Debug) {
                 System.out.println(String.format("%s heuristic max=%s", board, heuristic()));
             }
             return heuristic();
         }
         // Ist Blattknoten
         else if (getChildren().size() == 0) {
             if (LOG_LEVEL == LogLevel.Debug) {
                 System.out.println(String.format("%s eval max=%s", board, eval()));
             }
             return eval();
         }
         int best = Integer.MIN_VALUE;
         for (MinMaxNode child : getChildren()) {
        	 if(best>alpha){
        		 alpha=best;
        	 }
             int val = child.minAB(alpha,beta,depth - 1);
             if (val >= best) {
                 best = val;
                 maxChild = child;
             }
             if(best>=beta){
            	 return best;
             }
         }
         if (LOG_LEVEL == LogLevel.Debug) {
             System.out.println(String.format("%s  max=%s child=%s", board, best, maxChild));
         }
         return best;
    }
    private int max(int depth) {
        if (depth < 0) {
            throw new IllegalArgumentException(String.format("positiv value or 0 expected, but was %s", depth));
        }
        // Maximale Tiefe erreicht
        if (depth == 0) {
            if (LOG_LEVEL == LogLevel.Debug) {
                System.out.println(String.format("%s heuristic max=%s", board, heuristic()));
            }
            return heuristic();
        }
        // Ist Blattknoten
        else if (getChildren().size() == 0) {
            if (LOG_LEVEL == LogLevel.Debug) {
                System.out.println(String.format("%s eval max=%s", board, eval()));
            }
            return eval();
        }
        int best = Integer.MIN_VALUE;
        for (MinMaxNode child : getChildren()) {
            int val = child.min(depth - 1);
            if (val >= best) {
                best = val;
                maxChild = child;
            }
        }
        if (LOG_LEVEL == LogLevel.Debug) {
            System.out.println(String.format("%s  max=%s child=%s", board, best, maxChild));
        }
        return best;
    }
    private int minAB(int alpha, int beta, int deep) {
        if (deep < 0) {
            throw new IllegalArgumentException(String.format("positiv value or 0 expected, but was %s", deep));
        }
        // Maximale Tiefe erreicht
        if (deep == 0) {
            if (LOG_LEVEL == LogLevel.Debug) {
                System.out.println(String.format("%s heuristic min=%s", board, heuristic()));
            }
            return heuristic();
        }
        // Ist Blattknoten
        else if (getChildren().size() == 0) {
            if (LOG_LEVEL == LogLevel.Debug) {
                System.out.println(String.format("%s eval min=%s", board, eval()));
            }
            return eval();
        }
        int best = Integer.MAX_VALUE;
        for (MinMaxNode child : getChildren()) {
        	if(best<beta){
        		beta=best;
        	}
            int val = child.maxAB(alpha, beta,deep - 1);
            if (val < best) {
                best = val;
                minChild = child;
            }
            if(alpha>=best){
            	return best;
            }
        }
        if (LOG_LEVEL == LogLevel.Debug) {
            System.out.println(String.format("%s  min=%s child=%s", board, best, minChild));
        }

        return best;
    }
    
    private int min(int deep) {
        if (deep < 0) {
            throw new IllegalArgumentException(String.format("positiv value or 0 expected, but was %s", deep));
        }
        // Maximale Tiefe erreicht
        if (deep == 0) {
            if (LOG_LEVEL == LogLevel.Debug) {
                System.out.println(String.format("%s heuristic min=%s", board, heuristic()));
            }
            return heuristic();
        }
        // Ist Blattknoten
        else if (getChildren().size() == 0) {
            if (LOG_LEVEL == LogLevel.Debug) {
                System.out.println(String.format("%s eval min=%s", board, eval()));
            }
            return eval();
        }
        int best = Integer.MAX_VALUE;
        for (MinMaxNode child : getChildren()) {
            int val = child.max(deep - 1);
            if (val < best) {
                best = val;
                minChild = child;
            }
        }
        if (LOG_LEVEL == LogLevel.Debug) {
            System.out.println(String.format("%s  min=%s child=%s", board, best, minChild));
        }

        return best;
    }

    private int eval() {
        // Wenn Blattknoten, gucken ob Farbe gewonnen
        if (getChildren().size() > 0) {
            throw new IllegalStateException("must be a leaf node");
        }
        int difference = board.getNumberOfStones(color) - board.getNumberOfStones(color.getOtherColor());
        // Gleichstand
        if (difference == 0) {
            return 0;
        }
        // Gewonnen
        else if (difference > 0) {
            return Integer.MAX_VALUE;
        }
        // Verloren
        else {
            return Integer.MIN_VALUE;
        }
    }

    private int heuristic() {
        int difference = board.getNumberOfStones(color) - board.getNumberOfStones(color.getOtherColor());
        return difference;
    }

    private Set<MinMaxNode> getChildren() {
        if (children == null) {
            children = new HashSet<MinMaxNode>();
            Set<Board> boards = board.getNextPossibleStates(color);
            Color otherColor = color.getOtherColor();
            for (Board board : boards) {
                children.add(new MinMaxNode(board, otherColor));
            }
        }
        return children;
    }
    public Turn getBestABTurn(int searchDepth){
    	maxAB(Integer.MIN_VALUE, Integer.MAX_VALUE,searchDepth);
        Board boardOfChild = maxChild.board;
        return boardOfChild.getLastTurn();
    }
    public Turn getBestTurn(int searchDepth) {
        max(searchDepth);
        Board boardOfChild = maxChild.board;
        return boardOfChild.getLastTurn();
    }

    @Override
    public String toString() {
        return String.format("MinMaxNode [board=%s, color=%s, maxChild=%s]", board, color, maxChild);
    }
}
