package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Actividad 5 - Mezcla Directa Erick Humberto Rojas Teran
 *
 */
public class main {

    public static void main(String[] args) {
        String docIn = "ArchivoEntrada.txt";
        // Escritura
        try {
            List<Registro> registros = Arrays.asList(
                    new Registro(34),
                    new Registro(23),
                    new Registro(12),
                    new Registro(59),
                    new Registro(73),
                    new Registro(44),
                    new Registro(8),
                    new Registro(19),
                    new Registro(38),
                    new Registro(59)
            );
            escribirArchivo(docIn, registros);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Lectura
        try {
            BufferedReader bf = new BufferedReader(new FileReader(docIn));
            String palabras = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                palabras = bfRead;
                System.out.println(palabras);
            }
            System.out.println("Termino...");

        } catch (IOException ex) {
            System.out.println("No se encontro archivo");
        }
    }

    // Clase para representar un registro con una clave entera
    static class Registro {

        int clave;

        public Registro(int clave) {
            this.clave = clave;
        }

        public int getClave() {
            return clave;
        }
    }

    // Método para escribir los registros en un archivo
    private static void escribirArchivo(String nombreArchivo, List<Registro> registros) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Registro registro : registros) {
                writer.write(String.valueOf(registro.getClave()));
                writer.newLine();
            }
        }
    }

}
