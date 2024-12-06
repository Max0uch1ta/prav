package fr.istic.prg3;

import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class TestPerf {

    /*
     * Prend un tableau et echange  la valeur soit de tous les index soit de entre 1/4 et 1/2.
     *
     * @param arr tableau de int qui doit etre randomizé
     * @param complete vrai si tout random ou 1/4
     * @param arrSize taille du tableau
     */
    public static void swapRandom(int[] arr, boolean complete, int arrSize) {
        Random r = new Random();
        int end;

        if (complete) {
            end = arrSize;
        } else {
            end = arrSize / 4;
        }

        for (int i = 0; i < end; i++) {

            int temp = arr[i];
            int index2 = r.nextInt(arrSize - 1); // index random pour effectuer le swap

            // Swap des deux index
            arr[i] = arr[index2];
            arr[index2] = temp;
        }

    }

    /*
     * Test les algos de tri et mesure le temps d'execution
     *
     * @param tableau sur lequel tester les algos de tris
     * @param quel type de tableau (trié, random ou entre les deux)
     *
     * @return Renvoie un String[] avec le temps d'execution des differents types de tris
     */
    public static String[] getTimes(int[] arr, String arrType) {
        // Lance le calcul de temps par tri
        long start = System.nanoTime();
        int[] arrHeaptree = HeapTree.heapsort(arr);
        long timeHeapTree =  NANOSECONDS.toMicros(System.nanoTime() - start); // temps pour exec de heapTree heapsort
        int[] arrHeapArray = HeapArray.heapsort(arr);
        long timeHeapArray = NANOSECONDS.toMicros(System.nanoTime() - (start + timeHeapTree)); // temps pour heapArray
        int[] arrQuickSort = QuickSort.sort(arr);
        long timeQuickSort = NANOSECONDS.toMicros(System.nanoTime() - start - timeHeapTree - timeHeapArray); // Temps pour le quickSort

        // Renvoie la ligne de resultat de temps par algo
        return new String[]{arrType, String.valueOf(arr.length), String.valueOf(timeHeapTree), String.valueOf(timeHeapArray), String.valueOf(timeQuickSort)};

    }


    public static void main(String[] args) {
        String csvFile = "TriPerfComp.csv"; // Name of the CSV file
        String[] headers = {"Type de donnee", "Taille", "HeapTreeSort", "HeapArraySort", "QuickSort"}; // CSV headers

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Write headers
            writer.write(String.join(",", headers));
            writer.newLine();

            for (int size = 10; size <= 1000000; size *= 10) {

                int[] arr = new int[size]; // cree un arr de 10 a 10 000
                // Boucle qui remplit le tableau de 1 a 10k dans l'ordre
                for (int i = 0; i < size; i++) {
                    arr[i] = i + 1;
                }

                int[] arrTrie = arr.clone();
                int[] arrSemi = arr.clone();
                swapRandom(arrSemi, false, arrTrie.length); // Randomise en partie le tableau
                int[] arrRandom = arr.clone();
                swapRandom(arrRandom, true, arrTrie.length); // Randomisation totale

                // Run getTimes and write results to CSV
                writer.write(String.join(",", getTimes(arrTrie, "Sorted")));
                writer.newLine();
                writer.write(String.join(",", getTimes(arrSemi, "Semi-Random")));
                writer.newLine();
                writer.write(String.join(",", getTimes(arrRandom, "Random")));
                writer.newLine();


            }
            System.out.println("CSV bien créé.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
