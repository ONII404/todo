package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author ITM-10354
 */
public class MezclaDirecta {

    // Método para escribir los registros en un archivo
    public static void escribirArchivo(String nombreArchivo, List<Registro> registros) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Registro registro : registros) {
                writer.write(String.valueOf(registro.getClave()));
                writer.newLine();
            }
        }
    }

    // Método para leer los registros de un archivo
    public static List<Registro> leerArchivo(String nombreArchivo) throws IOException {
        List<Registro> registros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                registros.add(new Registro(Integer.parseInt(linea)));
            }
        }
        return registros;
    }

    // Método para dividir el archivo en bloques más pequeños y ordenar
    public static void ordenarArchivoExterno(String nombreArchivo) throws IOException {
        // Leer los registros del archivo
        List<Registro> registros = leerArchivo(nombreArchivo);
        // Dividir los registros en bloques pequeños que se pueden ordenar en memoria
        int tamañoBloque = 4; // Número de registros que se ordenan en memoria a la vez
        List<File> archivosOrdenados = new ArrayList<>();
        // Crear archivos con bloques ordenados
        for (int i = 0; i < registros.size(); i += tamañoBloque) {
            int fin = Math.min(i + tamañoBloque, registros.size());
            List<Registro> bloque = registros.subList(i, fin);
            bloque.sort(Comparator.comparingInt(Registro::getClave));
            // Escribir el bloque ordenado en un archivo temporal
            File archivoTemporal = new File("bloque_" + archivosOrdenados.size() + ".txt");
            escribirArchivo(archivoTemporal.getName(), bloque);
            archivosOrdenados.add(archivoTemporal);
        }
        // Realizar el merge entre los archivos
        File archivoFinal = new File("archivo_ordenado.txt");
        mergeArchivos(archivosOrdenados, archivoFinal);
        // Mostrar el archivo ordenado
        List<Registro> registrosOrdenados = leerArchivo(archivoFinal.getName());
        registrosOrdenados.forEach(registro -> System.out.println(registro.getClave()));

    }

    // Método para combinar (merge) los bloques ordenados en un solo archivo
    private static void mergeArchivos(List<File> archivos, File archivoSalida) throws IOException {
        PriorityQueue<Registro> cola = new PriorityQueue<>(Comparator.comparingInt(Registro::getClave));
        List<BufferedReader> lectores = new ArrayList<>();
        // Crear BufferedReader para cada archivo
        for (File archivo : archivos) {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            lectores.add(reader);
            String linea = reader.readLine();
            if (linea != null) {
                cola.offer(new Registro(Integer.parseInt(linea)));
            }
        }
        // Escribir el resultado ordenado en archivoSalida
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            while (!cola.isEmpty()) {
                Registro registro = cola.poll();
                writer.write(String.valueOf(registro.getClave()));
                writer.newLine();
                // Leer siguiente elemento de la fuente del registro extraído
                for (int i = 0; i < lectores.size(); i++) {
                    if (lectores.get(i).ready()) {
                        String linea = lectores.get(i).readLine();
                        if (linea != null) {
                            cola.offer(new Registro(Integer.parseInt(linea)));
                        }
                    }
                }
            }
        }
        // Cerrar los lectores
        for (BufferedReader reader : lectores) {
            reader.close();
        }
        // Borrar archivos temporales
        for (File archivo : archivos) {
            archivo.delete();
        }
    }
}
