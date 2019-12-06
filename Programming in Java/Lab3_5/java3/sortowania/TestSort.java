package java3.sortowania;

import java.util.Random;

public class TestSort {
   public static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public static void test(){


        // BubbleSort.bubbleSort(a);
            //  InsertSort.insertSort(a);
          //MergeSort.mergeSort(a,0,a.length-1);
           // QuickSort.quickSort(a,0,a.length-1);
            //  ShellSort.shellSort(a);
        Random gen=new Random();
        int n=20000;
        int[]tab1=new int[n];
        int[]tab2=new int[n];
        int[]tab3=new int[n];
        int[]tab4=new int[n];
        int[]tab5=new int[n];
        int[]optymistyczna=new int[n];
        int[]pesymistyczna=new int[n];
        for(int i=0;i<n;i++){
            tab1[i]= gen.nextInt(20000);
            tab2[i]=tab3[i]=tab4[i]=tab5[i]=tab1[i];
        }
        for(int i=0;i<n;i++){
            optymistyczna[i]=i;
        }
        for(int i=0;i<n;i++){
            pesymistyczna[i]=n-i;
        }

        System.out.println("BubbleSort random:");
        long tStart = System.currentTimeMillis();
        BubbleSort.bubbleSort(tab1);
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("BubbleSort optymistyczny:");
        tStart = System.currentTimeMillis();
        BubbleSort.bubbleSort(optymistyczna);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("BubbleSort pesymistyczny:");
        tStart = System.currentTimeMillis();
        BubbleSort.bubbleSort(pesymistyczna);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        for(int i=0;i<n;i++){
            pesymistyczna[i]=n-i;
        }


        System.out.println("InsertSort random:");
        tStart = System.currentTimeMillis();
        InsertSort.insertSort(tab2);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("InsertSort optymistyczny:");
        tStart = System.currentTimeMillis();
        InsertSort.insertSort(optymistyczna);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("InsertSort pesymistyczny:");
        tStart = System.currentTimeMillis();
        InsertSort.insertSort(pesymistyczna);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        for(int i=0;i<n;i++){
            pesymistyczna[i]=n-i;
        }


        System.out.println("MergeSort random:");
        tStart = System.currentTimeMillis();
        MergeSort.mergeSort(tab3,0,tab3.length-1);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("MergeSort optymistyczny:");
        tStart = System.currentTimeMillis();
        MergeSort.mergeSort(optymistyczna,0,optymistyczna.length-1);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("MergeSort pesymistyczny:");
        tStart = System.currentTimeMillis();
        MergeSort.mergeSort(pesymistyczna,0,pesymistyczna.length-1);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        for(int i=0;i<n;i++){
            pesymistyczna[i]=n-i;
        }


        System.out.println("QuickSort random:");
        tStart = System.currentTimeMillis();
        QuickSort.quickSort(tab4,0,tab3.length-1);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("QuickSort optymistyczny:");
        tStart = System.currentTimeMillis();
        QuickSort.quickSort(optymistyczna,0,optymistyczna.length-1);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("QuickSort pesymistyczny:");
        tStart = System.currentTimeMillis();
        QuickSort.quickSort(pesymistyczna,0,pesymistyczna.length-1);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        for(int i=0;i<n;i++){
            pesymistyczna[i]=n-i;
        }


        System.out.println("ShellSort random:");
        tStart = System.currentTimeMillis();
        ShellSort.shellSort(tab5);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("ShellSort optymistyczny:");
        tStart = System.currentTimeMillis();
        ShellSort.shellSort(optymistyczna);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

        System.out.println("ShellSort pesymistyczny:");
        tStart = System.currentTimeMillis();
        ShellSort.shellSort(pesymistyczna);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);

    }
}
