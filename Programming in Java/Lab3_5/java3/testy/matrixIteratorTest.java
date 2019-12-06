package java3.testy;


import static org.junit.jupiter.api.Assertions.*;

import java3.matrix.Matrix;
import java3.matrix.MatrixIterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class matrixIteratorTest {

    @Test
    void hasNextReturnFalseOnEmptyMatrix(){
        Matrix<Integer> matrix = new Matrix<>(0,0);
        assertFalse(matrix.iterator().hasNext());
    }

    @Test
    void hasNextReturnTrueWhenNextElementExist(){
        Matrix<Integer> matrix = new Matrix<>(10,10);
        assertTrue(matrix.iterator().hasNext());
    }


    @Test
    void nextReturnCorrectValue(){
        Matrix<Integer> matrix = new Matrix<>(2,2);
        matrix.set(1,1,10);

        MatrixIterator<Integer> iterator = (MatrixIterator<Integer>) matrix.iterator();

        for(int i=0;i<3;i++)iterator.next();

        assertEquals(10,iterator.next());
    }


}