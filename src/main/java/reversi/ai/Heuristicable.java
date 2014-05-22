package reversi.ai;

/**
 * Created by Loki on 22.05.2014.
 */
public interface Heuristicable {

    public void setHeuristic(Heuristic h);

    public int getValue();
}
