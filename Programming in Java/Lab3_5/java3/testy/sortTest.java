package java3.testy;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java3.MyException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java3.sortowania.*;

import java.time.Duration;
import java.util.Random;

class sortTest{
    static int []tab1=new int[20000];
    @BeforeAll
        static void PrzypiszTab(){
        Random gen=new Random();
        for(int i=0;i<20000;i++){
            tab1[i]= gen.nextInt(20000);
        } }




    @Test
    void BubbleSortTime(){
        final int []tab2=tab1.clone();
        assertTimeout(Duration.ofMillis(3000),()->BubbleSort.bubbleSort(tab2));
    }

    @Test
    void InsertSortTime(){
        final int []tab2=tab1.clone();
        assertTimeout(Duration.ofMillis(3000),()->InsertSort.insertSort(tab2));
    }

    @Test
    void MergeSortTime(){
        final int []tab2=tab1.clone();
        assertTimeout(Duration.ofMillis(3000),()->MergeSort.mergeSort(tab2,0,19999));
    }

    @Test
    void QuickSortTime(){
        final int []tab2=tab1.clone();
        assertTimeout(Duration.ofMillis(3000),()->QuickSort.quickSort(tab2,0,19999));
    }
    @Test
    void ShellSortTime(){
        final int []tab2=tab1.clone();
        assertTimeout(Duration.ofMillis(3000),()->ShellSort.shellSort(tab2));
    }




    @Test
    void BubbleSortTrue(){
        final int []tab2=tab1.clone();
        final int[]tab3=tab1.clone();
        BubbleSort.bubbleSort(tab2);
        Arrays.sort(tab3);
        assertArrayEquals(tab3,tab2);
    }

    @Test
    void InsertSortTrue(){
        final int []tab2=tab1.clone();
        final int[]tab3=tab1.clone();
        InsertSort.insertSort(tab2);
        Arrays.sort(tab3);
        assertArrayEquals(tab3,tab2);
    }

    @Test
    void MergeSortTrue(){
        final int []tab2=tab1.clone();
        final int[]tab3=tab1.clone();
        MergeSort.mergeSort(tab2,0,19999);
        Arrays.sort(tab3);
        assertArrayEquals(tab3,tab2);
    }

    @Test
    void QuickSortTrue(){
        final int []tab2=tab1.clone();
        final int[]tab3=tab1.clone();
        QuickSort.quickSort(tab2,0,19999);
        Arrays.sort(tab3);
        assertArrayEquals(tab3,tab2);
    }
    @Test
    void ShellSortTrue(){
        final int []tab2=tab1.clone();
        final int[]tab3=tab1.clone();
        ShellSort.shellSort(tab2);
        Arrays.sort(tab3);
        assertArrayEquals(tab3,tab2);
    }
}