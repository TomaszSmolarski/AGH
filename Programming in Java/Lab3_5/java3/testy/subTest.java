package java3.testy;

import static org.junit.jupiter.api.Assertions.*;

import java3.MyException;
import org.junit.jupiter.api.Test;
import java3.substring.*;
class subTest {


    @Test
    void solutionTrue() {
        String a = "abcd";
        String b = "cda";

        assertDoesNotThrow(()->Substring.substring(a,b));
       // try {
         //   assertEquals(2,Substring.substring(a,b));
       // } catch (MyException ex){};
    }



    @Test
    void solutionFalse() {
        String a = "abcd";
        String b = "de";
        assertThrows(MyException.class,()->Substring.substring(a,b));
    }


}