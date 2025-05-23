package metodoquicksort;

import java.util.Arrays;

/**
 *
 * @author ITM-10354
 */
public class main {

    // Función principal que implementa el algoritmo Quicksort
    static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            // Encuentra la posición del pivote, el array es ordenado alrededor de este pivote
            int pi = partition(arr, low, high);
            System.out.print(pi + " Pivote: " + arr[pi]);
            System.out.println(" " + Arrays.toString(arr));
            // Ordena recursivamente los elementos antes y después del pivote
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    // Función de partición para Quicksort

    static int partition(int arr[], int low, int high) {
        int pivot = arr[high]; // Tomamos el último elemento como pivote
        int i = (low - 1); // Índice del elemento más pequeño
        for (int j = low; j < high; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivot) {
                i++;
                // Intercambia arr[i] y arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Intercambia arr[i+1] y arr[high] (o el pivote)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;

    }
    // Función para imprimir un array

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    // Programa principal para probar el algoritmo

    public static void main(String args[]) {
        // int arr[] = {64, 34, 25, 12, 22, 11, 90};
        int arr[] = {16, 15, 3, 5, 7, 10};
        System.out.println("Array original:");
        printArray(arr);
        int n = arr.length;
        quickSort(arr, 0, n - 1);
        System.out.println("\nArray ordenado (Metodo quicksort):");
        printArray(arr);
    }
}
