package java3.substring;

import java3.MyException;

public class Substring {
    public static int substring(String a,String b)throws MyException {
        String c = a;
        if(a.contains(b)){return 1;}
        int x=1;
        do{
            a=a+c;
            x++;
            if(a.contains(b)){
                return x;
            }
        }while(a.length()<=b.length());
        throw new MyException("nie ma tego stringa w powtorzonym stringu") ;
    }
}
