package app;

/**
 *  Actividad 3 - ShellSort
 *  Erick Humberto Rojas Teran
 *  6 / Mayo / 2025
 */
public class main {

    // Implementación del algoritmo de ordenamiento Shell
    static void shellSort(int arr[]) {
        int n = arr.length;

        // Comienza con un intervalo grande, luego reduce el intervalo gradualmente
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Realiza el ordenamiento por inserción dentro del intervalo dado
            System.out.println("\n--- Nuevo gap: " + gap + " ---");
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                // Mueve los elementos que son mayores que temp hacia adelante
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                // Coloca temp en su posición correcta
                arr[j] = temp;
                System.out.print("Posición " + j + " ← " + temp + ": ");
                printArray(arr);
            }
        }
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
        int arr1[] = {16, 15, 3, 5, 7, 10};
        int arr2[] = {30, 29, 10, 15, 19, 22};
        int arr3[] = {40, 35, 15, 20, 21, 25};
        int arr4[] = {20, 25, 28, 32, 40, 10};
        System.out.println("Array original:");
        printArray(arr4);
        int n = arr4.length;
        shellSort(arr4);
        System.out.println("\nArray ordenado (Metodo shellsort) :");
        printArray(arr4);
    }
}
