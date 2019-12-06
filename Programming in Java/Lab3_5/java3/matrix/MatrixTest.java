package java3.matrix;

import java3.MyException;

import java.util.Random;

public class MatrixTest {
    public static void test(){
        Random random = new Random();
        Matrix<Integer> matrix1= new Matrix<>(5,5);
        Matrix<Integer> matrix2= new Matrix<>(5,5);
        Matrix<String> matrix3= new Matrix<>(4,4);
        Matrix<String> matrix4= new Matrix<>(4,4);

        MatrixIterator matrix1Iterator=(MatrixIterator) matrix1.iterator();
        MatrixIterator matrix2Iterator=(MatrixIterator) matrix2.iterator();
        MatrixIterator matrix3Iterator=(MatrixIterator) matrix3.iterator();
        MatrixIterator matrix4Iterator=(MatrixIterator) matrix4.iterator();

        while (matrix1Iterator.hasNext()){
            matrix1Iterator.setNext(random.nextInt()%100);
        }

        while (matrix2Iterator.hasNext()){
            matrix2Iterator.setNext(random.nextInt()%100);
        }

        while (matrix3Iterator.hasNext()){
            matrix3Iterator.setNext(Integer.toString(random.nextInt()%100));
        }

        while (matrix4Iterator.hasNext()){
            matrix4Iterator.setNext(Integer.toString(random.nextInt()%100));
        }
        System.out.println("Macierz 1:");
        matrix1.print();
        System.out.println("Macierz 2:");
        matrix2.print();
        System.out.println("Macierz 1 + macierz 2:");
        try {
            Matrix<Integer> matrix5=Matrix.add(matrix1,matrix2);
            matrix5.print();
        } catch (MyException e) {
           // e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("Macierz 3 string:");
        matrix3.print();
        System.out.println("Macierz 4 string:");
        matrix4.print();
        System.out.println("Macierz 3 + macierz 4:");
        try {
            Matrix<String> matrix6=Matrix.addString(matrix3,matrix4);
            matrix6.print();
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

}
