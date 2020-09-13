package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import java.util.Map;

import huglife.Action;
import huglife.Direction;
import huglife.Empty;
import huglife.Impassible;
import huglife.Occupant;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals("clorus", p.name());
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.stay();
        assertEquals(1.96, p.energy(), 0.01);
        p.replicate();
        assertEquals(0.98, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus p = new Clorus(1.4);
        Clorus q = p.replicate();
        assertEquals(p.color(), q.color());
        assertEquals(p.name(), q.name());
        assertEquals(p.energy(), q.energy(), 0.01);
    }

    @Test
    public void testChooseAction() {
        // Rule 1: No empty adjacent square
        Map<Direction, Occupant> neighbors = new HashMap<>();
        neighbors.put(Direction.TOP, new Impassible());
        neighbors.put(Direction.BOTTOM, new Impassible());
        neighbors.put(Direction.LEFT, new Impassible());
        neighbors.put(Direction.RIGHT, new Impassible());
        Clorus p = new Clorus(1);
        assertEquals(new Action(Action.ActionType.STAY),
                p.chooseAction(neighbors));

        // Rule 2: attack plip
        Map<Direction, Occupant> topPlip = new HashMap<>();
        topPlip.put(Direction.TOP, new Plip(1));
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.RIGHT, new Impassible());
        topPlip.put(Direction.LEFT, new Impassible());
        p = new Clorus(1);
        assertEquals(new Action(Action.ActionType.ATTACK, Direction.TOP),
                p.chooseAction(topPlip));

        // Rule 3: replicate
        Map<Direction, Occupant> leftReplicate = new HashMap<>();
        leftReplicate.put(Direction.TOP, new Impassible());
        leftReplicate.put(Direction.BOTTOM, new Impassible());
        leftReplicate.put(Direction.RIGHT, new Impassible());
        leftReplicate.put(Direction.LEFT, new Empty());
        p = new Clorus(1);
        assertEquals(new Action(Action.ActionType.REPLICATE, Direction.LEFT),
                p.chooseAction(leftReplicate));

        // Rule 4: move
        Map<Direction, Occupant> topMove = new HashMap<>();
        topMove.put(Direction.TOP, new Empty());
        topMove.put(Direction.BOTTOM, new Impassible());
        topMove.put(Direction.RIGHT, new Impassible());
        topMove.put(Direction.LEFT, new Impassible());
        p = new Clorus(0.5);
        assertEquals(new Action(Action.ActionType.MOVE, Direction.TOP),
                p.chooseAction(topMove));
    }

}
