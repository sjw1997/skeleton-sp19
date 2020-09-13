import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFInd {

    @Test
    public void testUnionFind() {
        UnionFind u = new UnionFind(10);
        u.union(3, 4);
        u.union(4, 3);
        u.union(1, 4);
        u.union(7, 9);
        u.union(8, 8);
        u.union(8, 0);
        u.union(1, 7);
        u.union(9, 0);
        u.union(6, 9);
        int[] expected = new int[]{4, 4, 2, 4, 4, 5, 4, 9, 0, 4};
        assertArrayEquals(expected, u.getId());
    }
}
