/*2022/11/19 11:15
 * by IDEA
 */
public class offByN implements CharacterComparator {
    private int N;
    public offByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == this.N;
    }
}
