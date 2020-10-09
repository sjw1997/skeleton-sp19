/**
 *  Wrting my own code to test Body.
 */
public class TestBody {
    public static void main(String[] args) {
        Body d1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body d2 = new Body(2.0, 2.0, 6.0, 7.0, 9.0, "jupiter.gif");

        System.out.println(d1.calcForceExertedBy(d2));
    }
}