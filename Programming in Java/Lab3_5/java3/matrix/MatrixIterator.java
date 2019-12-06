package java3.matrix;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {

    private Matrix<T> ref;
    private int rows;
    private int columns;
    private int currentRow;
    private int currentColumn;

    MatrixIterator(Matrix<T> matrix){
        this.rows=matrix.getRows();
        this.columns=matrix.getColumns();
        this.currentRow=0;
        this.currentColumn=0;
        this.ref=matrix;
    }

    @Override
    public boolean hasNext() {
        if(currentRow>=rows) return false;
        return true;

    }

    @Override
    public T next() {
        T data=ref.get(currentRow,currentColumn);
        if(currentColumn>=columns-1){
            currentColumn=0;
            currentRow++;
        }else{
            currentColumn++;
        }
        return data;
    }

    public void setNext(T value) {
        ref.set(currentRow,currentColumn, value);
        if(currentColumn>=columns-1){
            currentColumn=0;
            currentRow++;
        }else{
            currentColumn++;
        }

    }
}
