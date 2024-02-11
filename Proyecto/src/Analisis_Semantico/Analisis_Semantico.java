package Analisis_Semantico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

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
    }
    
    private static boolean esPalabraReservada(String palabra) {
        String[] palabrasReservadas = {"programa", "inicio", "fin", "leer", "escribir", "si", "sino", 
                                        "mientras", "repetir", "hasta", "entero", "real", "string", 
                                        "Logico", "logico", "Var", "var", "Entonces", "entonces",
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
}
