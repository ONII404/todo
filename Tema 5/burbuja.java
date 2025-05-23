public class BubbleSort {

    // Método que implementa el ordenamiento de burbuja
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        // Iterar sobre el arreglo
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Últimos i elementos ya están en su lugar
            for (int j = 0; j < n - i - 1; j++) {
                // Intercambiar si el elemento actual es mayor que el siguiente
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar arr[j] y arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            // Si no hubo intercambio en esta pasada, el arreglo ya está ordenado
            if (!swapped) {
                break;
            }
        }
    }

    // Método para imprimir el arreglo
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Método principal
    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Arreglo original:");
        printArray(numbers);

        bubbleSort(numbers);

        System.out.println("Arreglo ordenado:");
        printArray(numbers);
    }
}
