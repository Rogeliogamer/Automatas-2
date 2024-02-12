package Analisis_Semantico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashSet;
import java.io.*;

/**
 *
 * @author Equipo 2
 * @version 06/02/202
 */
public class Analisis_Semantico
{
    public static void main (String [] args)
    {
        String archivoEntrada = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Tokens.txt";
        String archivoSalida = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 8\\Lenguajes Y Autómatas II\\Proyecto\\src\\Recursos\\Tabla de Simbolos.txt";
        String valor = "null";
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 4);
                String primeraParte = partes[0].trim();
                String segundaParte = partes[1].trim();
                String terceraParte = partes[2].trim();
                String cuartaParte = partes[3].trim();
                
                if(primeraParte.equals("entero"))
                {
                    valor = "0";
                }
                else if(primeraParte.equals("real"))
                {
                    valor = "0.0";
                }
                else if(primeraParte.equals("string"))
                {
                    valor = "Null";
                }
                else if(primeraParte.equals("logico"))
                {
                    valor = "True";
                }
                
                if (!esPalabraReservada(primeraParte) && !esNumero(primeraParte)) {
                    bw.write(primeraParte + ",");
                    bw.write(segundaParte + ",");
                    bw.write(valor + ",");
                    bw.write("Main\n");
                }
            }
            
            System.out.println("Procesamiento completado. Se ha generado el archivo: " + archivoSalida);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            eliminarLineasRepetidas(archivoSalida, archivoSalida);
            System.out.println("Líneas repetidas eliminadas correctamente.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        
        try {
            eliminarLineasRepetidas2(archivoSalida);
            System.out.println("Se eliminaron las líneas repetidas correctamente.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
        
        try {
            eliminarLineas(archivoSalida);
            System.out.println("Líneas eliminadas correctamente.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
    
    private static boolean esPalabraReservada(String palabra) {
        String[] palabrasReservadas = {"programa", "inicio", "Inicio", "fin", "Fin", "leer", "escribir", "si", "sino", 
                                        "mientras", "repetir", "hasta", "entero", "real", "string", "cadena",
                                        "Logico", "logico", "Variables", "variables", "Entonces", "entonces",
                                        "Hacer", "hacer", "*", "/", "%", "+", "-", "=", "<", "<=",
                                        ">", ">=", "==", "!=", "&&", "||", "!", "(", ")", ";", ",",
                                        "true", "false"};
        for (String palabraReservada : palabrasReservadas) {
            if (palabra.equals(palabraReservada)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean esNumero(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void eliminarLineasRepetidas(String archivoEntrada, String archivoSalida) throws IOException {
        Set<String> lineasUnicas = new LinkedHashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineasUnicas.add(linea);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            for (String linea : lineasUnicas) {
                bw.write(linea);
                bw.newLine();
            }
        }
    }
    
     public static void eliminarLineasRepetidas2(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Set<String> firstParts = new HashSet<>();
        StringBuilder result = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", 4); // Divide la línea en dos partes utilizando ","
            String firstPart = parts[0]; // La primera parte antes de la ","
            if (!firstParts.contains(firstPart)) {
                // Si esta es la primera vez que vemos esta primera parte, la agregamos al resultado
                result.append(line).append("\n");
                firstParts.add(firstPart);
            }
        }

        // Cerrar el archivo
        reader.close();

        // Escribir el resultado de vuelta al archivo de entrada
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        writer.write(result.toString());
        writer.close();
    }
     
    public static void eliminarLineas(String fileName) throws IOException {
        File inputFile = new File(fileName);
        File tempFile = new File("tempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            // Si la línea no comienza con "," o """
            if (!line.startsWith(",") && !line.startsWith("\"")) {
                writer.write(line + System.getProperty("line.separator")); // Mantener la línea
            }
        }

        writer.close();
        reader.close();

        // Sobreescribir el archivo original con el archivo temporal
        if (!inputFile.delete()) {
            throw new IOException("No se pudo eliminar el archivo original.");
        }
        if (!tempFile.renameTo(inputFile)) {
            throw new IOException("No se pudo renombrar el archivo temporal.");
        }
    }
}
