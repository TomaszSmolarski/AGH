package java3.solution2;

import java3.MyException;

import java.util.Hashtable;

public class Solution2 {
    public static int[] solution(float[] arr, float target)throws MyException{

        int[] tab=new int[2];

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(i==j){break;}
                if(arr[i]+arr[j]==target){tab[0]=i;tab[1]=j;return tab;}
            }
        }
        throw new MyException("nie ma takich dwóch liczb") ;
    }










}
