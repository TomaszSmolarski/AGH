package java3.testy;

import static org.junit.jupiter.api.Assertions.*;

import java3.matrix.Matrix;
import java3.matrix.MatrixIterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class matrixTest {
    static Matrix<Integer> matrix;
    static Matrix<Integer> matrixToAdd;

    @BeforeAll
    static void createMatrix(){
        matrix = new Matrix<>(4,4);

        MatrixIterator i1 = (MatrixIterator) matrix.iterator();
        while (i1.hasNext()) {i1.setNext(10);}

        matrixToAdd=new Matrix<>(4,4);
        MatrixIterator i2 = (MatrixIterator) matrixToAdd.iterator();
        while (i2.hasNext()) {i2.setNext(20);}
    }


    @Test
    void createMatrixTest(){
        assertNotNull(matrix);
    }

    @Test
    void createMatrixWithNegativeSizeTest(){
        assertThrows(NegativeArraySizeException.class, ()-> new Matrix<Integer>(-10,-22));
    }


    @Test
    void getColumnsTest() {
        assertEquals(4, matrix.getColumns());
    }

    @Test
    void getRowsTest() {
        assertEquals(4, matrix.getRows());
    }

    @Test
    void setAndGetTest() {
        matrix.set(2,3,100);
        assertEquals(100,matrix.get(2,3));
    }


    @Test
    void iteratorIsNotNull(){
        assertNotNull(matrix.iterator());
    }


    @Test
    void addIntegersTest() {
        try {
            assertEquals(30,Matrix.add(matrix,matrixToAdd).get(1,2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
