import calculator.model.Calculator;
import org.junit.jupiter.api.Test;

public class StackTest {

    @Test
    public void Test() {

        Calculator c = new Calculator();

        System.out.println(simpleTests(c));
        System.out.println(intermediateTests(c));
        System.out.println(difficultTests(c));
    }

    public boolean simpleTests(Calculator cal) {
        String simpleTest = "1+2";
        String simpleTest2 = "6/2";
        String simpleTest3 = "2^3";
        String simpleTest4 = "8-3";
        String simpleTest5 = "(1+2)";

        double a = cal.evaluate(simpleTest);
        if(a != 3D) return false;

        double b = cal.evaluate(simpleTest2);
        if(b != 3D) return false;

        double c = cal.evaluate(simpleTest3);
        if(c != 8D) return false;

        double d = cal.evaluate(simpleTest4);
        if(d != 5D) return false;

        double e = cal.evaluate(simpleTest5);
        if(e != 3D) return false;

        return true;
    }

    public boolean intermediateTests(Calculator cal) {
        String test1 = "1+6/3";
        String test2 = "2^4-3";
        String test3 = "(1+3)/2";
        String test4 = "1.525*.523";
        double a = cal.evaluate(test1);
        if(a != 3D) return false;

        double b = cal.evaluate(test2);
        if(b != 13D) return false;

        double c = cal.evaluate(test3);
        if(c != 2D) return false;

        double d = cal.evaluate(test4);
        if((int)(d * 1000) != 797) return false;

        return true;
    }

    public boolean difficultTests(Calculator cal) {
        String test1 = "(1+3*(4-2))-2";
        String test2 = "2^(1+3)";
        String test3 = "1-(2+3/(2.5+2))-5";
        String test4 = "4^(1+3/2)*6/2-13.523";
        String test5 = "(4+2)(-4*5)";
        double a = cal.evaluate(test1);
        if(a != 5D) return false;

        double b = cal.evaluate(test2);
        if(b != 16D) return false;

        double c = cal.evaluate(test3);
        if((int)(c * 1000) != -6666) return false;

        double d = cal.evaluate(test4);
        if(d != 82.477) return false;

        double e = cal.evaluate(test5);
        if(e != -120) return false;
        x
        return true;
    }
}
