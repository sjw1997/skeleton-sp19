public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
//        Deque<Character> p = new LinkedListDeque<Character>();
        Deque<Character> p = new ArrayDeque<>();
        if (null == word) {
            return p;
        }
        for (int i = 0; i < word.length(); i++) {
            p.addLast(word.charAt(i));
        }
        return p;
    }

    public boolean isPalindrome(String word) {
        return helper(wordToDeque(word));
    }
    private boolean helper(Deque<Character> d) {
        if (d.isEmpty()) {
            return true;
        }
        if (d.getFirst() != d.getLast()) {
            return false;
        }
        d.removeFirst();
        d.removeLast();
        return helper(d);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (!cc.equalChars(d.getFirst(), d.getLast())) {
                return false;
            }
            d.removeFirst();
            d.removeLast();
        }
        return true;
    }
}
