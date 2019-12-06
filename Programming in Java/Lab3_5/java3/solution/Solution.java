package java3.solution;
import java3.MyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Solution {

    public static int solution1(List<Integer> a)throws MyException {
            if(a.isEmpty()){throw new MyException("lista pusta");}
            List <Integer> b=new ArrayList<>(a);
            Collections.sort(b);
            if(b.size()>100000){throw new MyException("za duzo elementow");}
            if(b.get(0)<-100000){throw new MyException("za mała liczba");}
            if(b.get(b.size()-1)>100000){throw new MyException("za duza liczba");}
            if(b.get(b.size()-1)<=0){return 1;}
            for(int i=1;i<b.get(b.size()-1);i++)
            {
                if(!b.contains(i)){ return i;}
            }
            return b.get(b.size()-1)+1;



        }



}
