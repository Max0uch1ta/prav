package fr.istic.prg3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTreeTest {

    int number1 = 100;
    int number2 = 113;
    int[] array1 = {109, 107, 111, 112, 103, 104, 110, 101, 106, 102, 108, 105};
    int[] array2 = {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113};


    @Test
    @DisplayName("Add Values test")
    void addValue() {
        HeapTree heapTree1 = new HeapTree(array1);
        HeapTree heapTree2 = new HeapTree(number1);

        heapTree1.addValue(number2);
        heapTree2.addValue(number2);

        assertEquals(113, heapTree1.getMax(), "Le max doit être égale à 113");
        assertEquals(113,heapTree2.getMax(), "Le max devrait être égale à 113");

    }

    @Test
    @DisplayName("Test fonction getMax()")
    void getMax() {
        HeapTree heapTree1 = new HeapTree(array1);
        HeapTree heapTree2 = new HeapTree(array2);
        HeapTree heapTree3 = new HeapTree(array1);
        heapTree3.addValue(number2);



        assertAll(
                () -> assertEquals(112, heapTree1.getMax(),"Le max devrait être égale à 112"),
                () -> assertEquals(113, heapTree2.getMax(),"Le max devrait être égale à 113"),
                () -> assertEquals(113, heapTree3.getMax(),"Le max devrait être égale à 113")
        );
    }

    @Test
    void extractMax() {

        HeapTree heapTree1 = new HeapTree(array1);
        HeapTree heapTree2 = new HeapTree(number2);

        int max1 = heapTree1.extractMax();
        int max2 = heapTree2.extractMax();
        int max3 = heapTree2.extractMax();

        assertEquals(112, max1, "La valeur extraite doit être égale à 112");
        assertEquals(113, max2, "La valeur extraite doit être égale à 113");
        assertEquals(113, max3, "La valeur extraite doit être égale à 113");



    }


    @Test
    void heapsort() {
    }
}