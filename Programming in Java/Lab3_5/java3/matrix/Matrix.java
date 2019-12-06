package java3.matrix;

import java3.MyException;

import java.util.Iterator;


public class Matrix<T> implements Iterable<T>{


    private T [][] matrix;
    private int columns;
    private int rows;


    public Matrix(int rows, int columns){
        this.columns=columns;
        this.rows=rows;
        matrix=(T[][])new Object[rows][columns];
    }


    @Override
    public Iterator iterator() {
        return new MatrixIterator<T>(this);
    }

    public int getColumns(){return columns;}
    public int getRows(){return rows;}
    public T get(int row, int column){
     if(row<rows&&column<columns) return matrix[row][column];
     return null;
    }

    public Matrix set(int row, int column, T value){
        if(row<rows&&column<columns) matrix[row][column]=value;
        return this;
    }

    public void print(){
        Iterator iterator = this.iterator();
        int counter=1;
        while (iterator.hasNext()){
            System.out.print(iterator.next()+" ");
            if(counter%columns==0) System.out.println();
            counter++;
        }

    }

    public static Matrix<Integer> add(Matrix<Integer> m1, Matrix<Integer> m2) throws MyException
    {
        if(m1.getRows()!=m2.getRows()||m1.getColumns()!=m2.getColumns()) throw new MyException("Nie da sie ");
        Matrix<Integer> resultMatrix = new Matrix<>(m1.getRows(),m1.getColumns());
        MatrixIterator<Integer> m1Iterator = (MatrixIterator<Integer>) m1.iterator();
        MatrixIterator<Integer> m2Iterator = (MatrixIterator<Integer>) m2.iterator();
        MatrixIterator<Integer> resultIterator = (MatrixIterator<Integer>) resultMatrix.iterator();

        while (resultIterator.hasNext()) {
            resultIterator.setNext(m1Iterator.next() + m2Iterator.next());
        }

        return resultMatrix;
    }

    public static Matrix<String> addString(Matrix<String> m1, Matrix<String> m2) throws MyException
    {

        if(m1.getRows()!=m2.getRows()||m1.getColumns()!=m2.getColumns()) throw new MyException("Nie da sie ");
        Matrix<String> resultMatrix = new Matrix<>(m1.getRows(),m1.getColumns());
        MatrixIterator<String> m1Iterator = (MatrixIterator<String>) m1.iterator();
        MatrixIterator<String> m2Iterator = (MatrixIterator<String>) m2.iterator();
        MatrixIterator<String> resultIterator = (MatrixIterator<String>) resultMatrix.iterator();

        while (resultIterator.hasNext()) {
            resultIterator.setNext(m1Iterator.next() + m2Iterator.next());
        }

        return resultMatrix;
    }
}
