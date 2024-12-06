package fr.istic.prg3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapArrayTest {

    int number1 = 100;
    int number2 = 113;
    int[] array1 = {109, 107, 111, 112, 103, 104, 110, 101, 106, 102, 108, 105};
    int[] array2 = {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113};


    @Test
    @DisplayName("Add Values test")
    void addValue() {
        HeapArray heapArray1 = new HeapArray(array1);
        int[] arr = {number1};
        HeapArray heapArray2 = new HeapArray(arr);

        heapArray1.addValue(number2);
        heapArray2.addValue(number2);

        assertEquals(113, heapArray1.getMax(), "Le max doit être égale à 113");
        assertEquals(113,heapArray2.getMax(), "Le max devrait être égale à 113");

    }

    @Test
    @DisplayName("Test fonction getMax()")
    void getMax() {
        HeapArray heapArray1 = new HeapArray(array1);
        HeapArray heapArray2 = new HeapArray(array2);
        HeapArray heapArray3 = new HeapArray(array1);
        heapArray3.addValue(number2);



        assertAll(
                () -> assertEquals(112, heapArray1.getMax(),"Le max devrait être égale à 112"),
                () -> assertEquals(113, heapArray2.getMax(),"Le max devrait être égale à 113"),
                () -> assertEquals(113, heapArray3.getMax(),"Le max devrait être égale à 113")
        );
    }

    @Test
    void extractMax() {

        HeapArray heapArray1 = new HeapArray(array1);
        //HeapArray heapArray2 = new HeapArray(number2);

        int max1 = heapArray1.extractMax();
        //int max2 = heapArray2.extractMax();
        //int max3 = heapArray2.extractMax();

        assertEquals(112, max1, "La valeur extraite doit être égale à 112");
        //assertEquals(113, max2, "La valeur extraite doit être égale à 113");
        //assertEquals(113, max3, "La valeur extraite doit être égale à 113");



    }

    @Test
    void heapsort() {
        int[] arr = {1,2,3,7,6,5,4,8};
        int[] ordArr = {1,2,3,4,5,6,7,8};

        int[] arr2 = HeapArray.heapsort(arr);
        assertArrayEquals(ordArr, arr2, "Ordered array should be {1,2,3,4,5,6,7,8}");
    }


    @Test
    void testToString() {
    }
}