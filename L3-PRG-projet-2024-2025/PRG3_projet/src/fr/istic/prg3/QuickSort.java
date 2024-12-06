package fr.istic.prg3;

// Inspiré du triRapide trouvé sur http://cermics.enpc.fr/polys/oap/node89.html
public class QuickSort {

    static int[] sort(int[] arr) {
        int[] copy = arr.clone();
        quickSort(copy, 0, copy.length - 1);
        return copy;
    }

    static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
    static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }
}