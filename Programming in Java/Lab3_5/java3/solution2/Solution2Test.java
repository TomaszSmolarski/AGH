package java3.solution2;
import java3.MyException;
public class Solution2Test {
    public static void test(){
        int tab[]=new int[]{4,2,3,5,7,1,9,10,8,12,2};
        float tab1[]=new float[]{4,2,3,5,7,1,9,10,8,12,2};
        int target=15;
        try {
            System.out.println("test sumy w tablicach");
            int tab2[];
            tab2=Solution2.solution(tab1,target);
            System.out.println(tab2[0]);
            System.out.println(tab2[1]);

        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }



    }
}
