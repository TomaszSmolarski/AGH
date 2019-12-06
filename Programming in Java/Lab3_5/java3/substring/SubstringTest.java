package java3.substring;

import java3.MyException;

public class SubstringTest {
    public static void test() {
try {
    System.out.println("test stringow");
    String a = "abcd";
    String b = "cda";
    System.out.println(Substring.substring(a, b));
}catch (MyException ex) {
    System.out.println(ex.getMessage());
}

    }
}
