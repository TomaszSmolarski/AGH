package java3.testy;

import java3.MyException;
import org.junit.jupiter.api.Test;
import java3.solution.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolTest {

    @Test
    void solutionTrue() {
        List<Integer> a = new ArrayList<Integer>();
        a.add(-4);
        a.add(1);
        a.add(-6);
        a.add(5);
        a.add(4);


       try {
           assertEquals(2, Solution.solution1(a));
        }catch(Exception e){}
    }

    @Test
    void solutionBig() {
        List<Integer> a = new ArrayList<Integer>();
        a.add(6000000);
        assertThrows(MyException.class,()->Solution.solution1(a));
    }

    @Test
    void solutionSmall() {
        List<Integer> a = new ArrayList<Integer>();
        a.add(-40000000);
        assertThrows(MyException.class,()->Solution.solution1(a));
    }

    @Test
    void solutionEmpty() {
        List<Integer> a = new ArrayList<Integer>();
        assertThrows(MyException.class,()->Solution.solution1(a));
    }
    @Test
    void solutionBigArray() {
        List<Integer> a = new ArrayList<Integer>();
        for(int i=0;i<140000;i++){
            a.add(2);
        }
        assertThrows(MyException.class,()->Solution.solution1(a));
    }
}