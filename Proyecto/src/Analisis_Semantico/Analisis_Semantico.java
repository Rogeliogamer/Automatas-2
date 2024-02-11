package Analisis_Semantico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 4);
                String primeraParte = partes[0].trim();
                String segundaParte = partes[1].trim();
                String terceraParte = partes[2].trim();
                String cuartaParte = partes[3].trim();
                
                if (!esPalabraReservada(primeraParte) && !esNumero(primeraParte)) {
                    bw.write(primeraParte + ",");
                    bw.write(segundaParte + ",");
                    bw.write(obtenerValor(terceraParte) + ",");
                    bw.write("Main\n");
                }
            }
            
            System.out.println("Procesamiento completado. Se ha generado el archivo: " + archivoSalida);
            
        } catch (IOException e) {
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
    
    private static String obtenerValor(String tipo) {
        switch (tipo.toLowerCase()) {
            case "entero":
                return "0";
            case "real":
                return "0.0";
            case "string":
                return "Null";
            case "logico":
                return "True";
            default:
                return "";
        }
    }
}
