package java3.testy;

import static org.junit.jupiter.api.Assertions.*;

import java3.MyException;
import org.junit.jupiter.api.Test;
import java3.solution2.*;
class sol2Test {


    @Test
    void solution2True() {

        float tab1[]=new float[]{4,2,3,5,7,1,9,10,8,12,2};
        int target=15;
        int tab2[]={7,3};
        try {
            assertArrayEquals(tab2, Solution2.solution(tab1, target));
        }catch(Exception e){}
    }

    @Test
    void solution2False()  {

        float tab1[]=new float[]{4,2,3,5,7,1,9,10,8,12,2};
        int target=30;


            assertThrows(MyException.class,()->{
                Solution2.solution(tab1, target);
            });
    }
    @Test
    void solution2Empty()  {

        float []tab1;
        tab1=new float[0];
        int target=30;


        assertThrows(MyException.class,()->{
            Solution2.solution(tab1, target);
        });
    }

}