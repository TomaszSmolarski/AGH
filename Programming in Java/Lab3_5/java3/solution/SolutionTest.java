package java3.solution;

import java3.MyException;

import java.util.ArrayList;
import java.util.List;

public class SolutionTest {
    public static void test() {
        List<Integer> a = new ArrayList<Integer>();
        a.add(-4);
        a.add(1);
        a.add(-6);
        a.add(5);
        a.add(4);

        //Solution x = new Solution();
        try {
            System.out.println("test najmniejszej liczby dodatniej");
            System.out.println(Solution.solution1(a));
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }
}