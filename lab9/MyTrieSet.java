import java.util.LinkedList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private class Node {
        boolean isKey;
        Node[] next = new Node[R];
    }

    private static final int R = 128;
    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        Node t = get(key);
        return t != null && t.isKey;
    }

    private Node get(String key) {
        return get(root, key, 0);
    }

    private Node get(Node x, String key, int index) {
        if (x == null) {
            return null;
        }
        if (index == key.length()) {
            return x;
        }
        char c = key.charAt(index);
        return get(x.next[c], key, index + 1);
    }

    @Override
    public void add(String key) {
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int index) {
        if (x == null) {
            x = new Node();
        }
        if (index == key.length()) {
            if (!x.isKey) {
                size++;
                x.isKey = true;
            }
            return x;
        }
        char c = key.charAt(index);
        x.next[c] = add(x.next[c], key, index + 1);
        return x;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> lst = new LinkedList<>();
        Node x = get(prefix);
        if (x == null) {
            return lst;
        }
        colHelp(prefix, x, lst);
        return lst;
    }

    private void colHelp(String s, Node x, List<String> lst) {
        if (x == null) {
            return;
        }
        if (x.isKey) {
            lst.add(s);
        }
        for (char c = 0; c < R; c++) {
            colHelp(s + c, x.next[c], lst);
        }
    }

    @Override
    public List<String> collect() {
        return keysWithPrefix("");
    }

    @Override
    public String longestPrefixOf(String key) {
        return null;
    }
}
