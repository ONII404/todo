package app;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  Actividad 2: QuickSort
 *  Erick Humberto Rojas Teran
 *  5 / Mayo / 2025
 */
public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce números enteros separados por espacios:");
        String entrada = scanner.nextLine();

        String[] numerosStrings = entrada.split(" ");  // Dividir por espacios
        int[] numeros = new int[numerosStrings.length];

        for (int i = 0; i < numerosStrings.length; i++) {
            numeros[i] = Integer.parseInt(numerosStrings[i]);  // Convertir a entero
        }

        // Mostrar array original
        System.out.println("Array original: " + Arrays.toString(numeros));

        // Ordenar con QuickSort
        quicksort(numeros, 0, numeros.length - 1);
        
        scanner.close();
    }

    // Método principal de QuickSort
    private static void quicksort(int[] arry, int primero, int ultimo) {
        if (primero < ultimo) {
            int indicePivote = particion(arry, primero, ultimo);

            System.out.println("Pivote: " + arry[indicePivote]
                    + "\nArray actual: " + Arrays.toString(arry));

            quicksort(arry, primero, indicePivote - 1);   // Ordenar elementos menores al pivote
            quicksort(arry, indicePivote + 1, ultimo);  // Ordenar elementos mayores al pivote
        }
    }

    // Método de partición
    private static int particion(int[] arr, int primero, int ultimo) {
        int medio = primero + (ultimo - primero) / 2; // Elegir pivote 
        int pivote = arr[medio];

        // Mover pivote al final
        intercambiar(arr, medio, ultimo);

        // Índice para seguir la posición donde colocar elementos menores al pivote
        int i = primero - 1;

        // Recorrer el subarray
        for (int j = primero; j < ultimo; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivote) {
                i++;
                intercambiar(arr, i, j);  // Colocarlo en la posición i
            }
        }

        // Colocar el pivote en su posición correcta (después del último elemento menor)
        intercambiar(arr, i + 1, ultimo);
        // Devolver la posición final del pivote
        return i + 1;
    }

    // Método para intercambiar elementos
    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
