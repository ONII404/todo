package app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ITM-10354
 */
public class main {

    public static void main(String[] args) {
        List<Registro> registros = null;
        String docIn = "ArchivoEntrada.txt";

        MezclaDirecta mDirecta = new MezclaDirecta();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Introduce numeros enteros separados por espacios:");
            String entrada = scanner.nextLine();

            String[] numerosStrings = entrada.split(" ");  // Dividir por espacios

            for (int i = 0; i < numerosStrings.length; i++) {
                registros = Arrays.asList(
                        new Registro(Integer.parseInt(numerosStrings[i]))
                );
                System.out.println(numerosStrings[i]);
            }

            // Escribir
            mDirecta.escribirArchivo(docIn, registros);
            
            // Ordenar
            mDirecta.ordenarArchivoExterno(docIn);

            scanner.close();

        } catch (Exception e) {
        }

    }
}
