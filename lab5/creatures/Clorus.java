package creatures;
import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature{
    /**
     * rgb for color
     */
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        energy = e;
        r = 34;
        g = 0;
        b = 231;
    }

    public Clorus() {
        this(1);
    }

    @Override
    public Color color() {
        return new Color(r, g, b);
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors){
        Deque<Direction> emptyDeque = new ArrayDeque<>();
        Deque<Direction> plipDeque = new ArrayDeque<>();
        for (Direction d: neighbors.keySet()) {
            String name = neighbors.get(d).name();
            if (name.equals("empty")) {
                emptyDeque.add(d);
            }
            else if (name.equals("plip")) {
                plipDeque.add(d);
            }
        }

        // Rule 1
        if (emptyDeque.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (plipDeque.size() > 0) {
            return new Action(Action.ActionType.ATTACK,
                    HugLifeUtils.randomEntry(plipDeque));
        }

        // Rule 3
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE,
                    HugLifeUtils.randomEntry(emptyDeque));
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE,
                HugLifeUtils.randomEntry(emptyDeque));
    }
}
