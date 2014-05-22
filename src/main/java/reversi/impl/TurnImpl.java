package reversi.impl;

import reversi.api.Color;
import reversi.api.Turn;

import java.util.Objects;

/**
 * Created by Loki on 22.05.2014.
 */
public class TurnImpl implements Turn{

    private int x, y;
    private Color color;

    public TurnImpl(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public Color getColor() {
        return this.color;
    }


    public boolean equals(Object o)
    {
        if (o == null){
            return false;
        }
        if(o instanceof Turn){
            Turn t = (Turn) o;
            if(t.getColor() == this.color && t.getX() == this.x && t.getY() == this.y)
            {
                return true;
            }
        }
        return false;
    }
}
