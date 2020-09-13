public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return 1 == Math.abs(x - y);
    }
}
